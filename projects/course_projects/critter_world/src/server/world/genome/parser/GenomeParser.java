package server.world.genome.parser;

import server.world.genome.ast.*;
import server.world.genome.ast.Number;
import server.world.genome.tokenizer.Tokenizer;

import java.io.Reader;
import java.util.LinkedList;

/** Contains Parser for Critter Language. **/
public class GenomeParser implements Parser {
	
	/** Takes space seperated string of allowed tokens. Returns
	 * next token if exists and is valid otherwise throws error. 
	 * @throws SyntaxErrorException */
	private String consume(String s) throws SyntaxErrorException {
		return check(s) ? t.next().toString() : null;		
	}
	/** Takes space seperated string of allowed tokens. Returns
	 * true if token exists and is valid otherwise throws error. 
	 * @throws SyntaxErrorException */
	private boolean check(String s) throws SyntaxErrorException {
		String[] exps = s.split(" ");
		String obs = "<nothing>";
		if (t.hasNext())
			if (t.peek().isNum())
				obs = "<number>";
			else
				obs = t.peek().toString();
		for (String exp : exps)
			if (obs.equals(exp))
				return true;
		throw new SyntaxErrorException("observed: " + obs + "\nexpected: " + s);
	}
	
	/** Sets tokenizer in genome parser to t */
	public void setTokenizer(Tokenizer t) {
		this.t = t;
	}	
	/** Tokenizer from file of critter program */
	private Tokenizer t;
	/** Parses critter genome from Reader
	 * by calling parse methods recursively */
	@Override
	public Node parse(Reader r) {
		setTokenizer(new Tokenizer(r));
		try {
			return parseProgram();
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/** Parses a program production */
	public Program parseProgram() throws SyntaxErrorException {
		LinkedList<Rule> r = new LinkedList<Rule>();		
		while (t.hasNext())
			r.add(parseRule());
		return new Program(r);
	}	
	/** Parses a rule production */
	public Rule parseRule() throws SyntaxErrorException {
		Condition cn = parseCondition();
		consume("-->");
		Command cm = parseCommand();
		consume(";");
		return new Rule(cn, cm);
	}
	
	/** Parses a condition production */
	public Condition parseCondition() throws SyntaxErrorException {
		Condition c = parseConjunction();
		while (t.hasNext() && t.peek().isLogicalOr())
			c = new BinaryCondition(c, BinaryCondition.Operator.valueOf(
					consume("or").toUpperCase()), parseConjunction());
		return c;
	}
	/** Parses a conjunction production */
	public Condition parseConjunction() throws SyntaxErrorException {
		Condition r = parseRelation();
		while (t.hasNext() && t.peek().isLogicalAnd())
			r = new BinaryCondition(r, BinaryCondition.Operator.valueOf(
					t.next().toString().toUpperCase()), parseRelation());
		return r;
	}	
	/** Parses a relation production */
	public Condition parseRelation() throws SyntaxErrorException {
		if (t.hasNext() && t.peek().isLeftBrace()) {
			t.next();
			Condition c = parseCondition();
			consume("}");
			return c;
		}
		return new RelationalCondition(parseExpr(), parseRel(), parseExpr());
	}	
	/** Parses a rel production */
	public RelationalCondition.Operator parseRel() throws SyntaxErrorException {
		String s = consume("< <= = >= > !=");
		if (s.equals("<"))
			s = "LT";
		else if (s.equals("<="))
			s = "LE";
		else if (s.equals("="))
			s = "EQ";
		else if (s.equals(">="))
			s = "GE";
		else if (s.equals(">"))
			s = "GT";
		else if (s.equals("!="))
			s = "NE";			
		return RelationalCondition.Operator.valueOf(s);
	}
	
	/** Parses an expr production */
	public Expr parseExpr() throws SyntaxErrorException {
		return parseTerm();
	}
	/** Parses a term production */
	public Expr parseTerm() throws SyntaxErrorException {
		Expr f = parseFactor();
		while (t.hasNext() && t.peek().isAddOp())
			f = new BinaryExpression(f, parseAddop(), parseFactor());
		return f;
	}
	/** Parses an addop production */
	public BinaryExpression.Operator parseAddop() throws SyntaxErrorException {		
		String s = consume("+ -");
		if (s.equals("+"))
			s = "PLUS";
		else
			s = "MINUS";
		return BinaryExpression.Operator.valueOf(s);
	}	
	/** Parses a factor production */
	public Expr parseFactor() throws SyntaxErrorException {
		Expr a = parseAtom();
		while (t.hasNext() && t.peek().isMulOp())
			a = new BinaryExpression(a, parseMulop(), parseAtom());	
		return a;
	}	
	/** Parses a mulop production */
	public BinaryExpression.Operator parseMulop() {
		String s = t.next().toString();
		if (s.equals("*"))
			s = "MUL";
		else if (s.equals("/"))
			s = "DIV";
		else
			s = "MOD";
		return BinaryExpression.Operator.valueOf(s);
	}	
	/** Parses an atom production */
	public Expr parseAtom() throws SyntaxErrorException {
		check("<number> mem random ( nearby ahead damage food");
		if (t.peek().isSensor())
			return parseSensor();
		if (t.peek().isNum())
			return new Number(Integer.parseInt(t.next().toString()));
		else if (t.peek().isMem() || t.peek().isRandom()) {
			String s = t.next().toString().toUpperCase();
			consume("[");
			Expr i = parseExpr();
			consume("]");
			return new IndexedExpr(IndexedExpr.Terminal.valueOf(s), i);
		}
		else {
			t.next();
			Expr e = parseExpr();
			consume(")");
			return e;
		}
	}
	/** Parses a sensor production */
	public Expr parseSensor() throws SyntaxErrorException {
		String s = consume("nearby ahead damage food");
		if (s.equals("nearby") || s.equals("ahead")) {
			consume("[");
			Expr i = parseExpr();
			consume("]");
			return new IndexedExpr(IndexedExpr.Terminal.valueOf(s.toUpperCase()), i);
		}
		else
			return new ConstantExpr(ConstantExpr.Terminal.valueOf(s.toUpperCase()));
	}
	
	/** Parses a command production */
	public Command parseCommand() throws SyntaxErrorException {
		check("mem wait forward backward left right eat attack grow bud mate");
		LinkedList<Update> u = new LinkedList<Update>();
		Action a = null;
		while (t.hasNext() && t.peek().isMem())
			u.add(parseUpdate());
		if (t.hasNext() && t.peek().isNotSemicolon())
			a = parseAction();
		check("; <nothing>");
		return new Command(u, a);
	}	
	/** Parses an update production */
	public Update parseUpdate() throws SyntaxErrorException {
		consume("mem");
		consume("[");
		Expr i = parseExpr();
		consume("]");
		consume(":=");
		Expr e = parseExpr();
		return new Update(i, e);		
	}	
	/** Parses an action production */
	public Action parseAction() throws SyntaxErrorException {
		return new Action(Action.Terminal.valueOf(consume(
				"wait forward backward left right eat attack grow bud mate").toUpperCase()));
	}

}
