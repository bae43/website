package server.world.genome.ast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import server.world.grid.Critter;

public class BinaryExpression implements Expr {

	Expr l;
	Operator op;
	Expr r;

	public BinaryExpression(Expr l, Operator op, Expr r) {
		this.l = l;
		this.op = op;
		this.r = r;
	}

	@Override
	public int eval(Critter c) {
		if (op == Operator.PLUS)
			return l.eval(c) + r.eval(c);
		if (op == Operator.MINUS)
			return l.eval(c) - r.eval(c);
		if (op == Operator.MUL)
			return l.eval(c) * r.eval(c);
		if (op == Operator.DIV)
			return l.eval(c) / r.eval(c);
		return l.eval(c) % r.eval(c);
	}

	@Override
	public int size() {
		return l.size() + r.size() + 1;
	}

	@Override
	public int condSize() {
		return l.condSize() + r.condSize();
	}

	@Override
	public int exprSize() {
		return l.exprSize() + r.exprSize() + 1;
	}

	@Override
	public Expr mutate(Random rand, Program prog, MutationInfo mi) {
		int nodeNo = rand.nextInt(size());
		int leftSize = l.size();
		if (nodeNo < leftSize) {
			//mutate left operand
			return new BinaryExpression(l.mutate(rand, prog, mi), op, r);
		}
		else if (nodeNo > leftSize) {
			//mutate right operand
			return new BinaryExpression(l, op, r.mutate(rand, prog, mi));
		}
		else {
			//mutate this node
			switch (rand.nextInt(5)) {
			case 0: //reversing the order of operands
				mi.setCondExprMutation(MutationInfo.CondExprMutation.REVERSE);
				return new BinaryExpression(r, op, l);
			case 1: //replacing the condition with one of its operands
				mi.setCondExprMutation(MutationInfo.CondExprMutation.REPLACE);
				switch (rand.nextInt(2)) {
				case 0: //choose left operand
					return l;
				case 1: //choose right operand
				default:
					return r;
				}
			case 2: //changing the operation
				mi.setCondExprMutation(MutationInfo.CondExprMutation.CHANGE);
				Operator mutatedOp = Operator.VALUES.get(
						rand.nextInt(Operator.NUM_OPS));
				return new BinaryExpression(l, mutatedOp, r);
			case 3: //replacing an operand with a copy of another randomly chosen expression found in the program
				mi.setCondExprMutation(MutationInfo.CondExprMutation.DUP);
				Expr chosenExpr = prog.randomExpr(rand);
				switch (rand.nextInt(2)) {
				case 0: //replace left operand
					return new BinaryExpression(chosenExpr, op ,r);
				case 1: //replace right operand
				default:
					return new BinaryExpression(l, op, chosenExpr);
				}
			case 4: //replacing an operand with a randomly generated expression
			default:
				mi.setCondExprMutation(MutationInfo.CondExprMutation.RANDOM);
				Expr randomExpr = Program.randomExpr(rand, prog);
				switch (rand.nextInt(2)) {
				case 0: //replace left operand
					return new BinaryExpression(randomExpr, op ,r);
				default: //replace right operand
					return new BinaryExpression(l, op, randomExpr);
				}
			}
		}
	}

	@Override
	public void prettyPrint(StringBuffer sb) {
		sb.append("(");
		l.prettyPrint(sb);
		sb.append(" ");
		sb.append(op.toString());
		sb.append(" ");
		r.prettyPrint(sb);
		sb.append(")");
	}

	@Override
	public Condition randomCond(Random rand) {
		int condSize = condSize();
		if (condSize == 0)
			return null;
		int condNo = rand.nextInt(condSize);
		int lCondSize = l.condSize();
		if (condNo < lCondSize)
			return l.randomCond(rand);
		else
			return r.randomCond(rand);
	}

	@Override
	public Expr randomExpr(Random rand) {
		int exprSize = exprSize();
		if (exprSize == 0)
			return null;
		int exprNo = rand.nextInt(exprSize);
		int lExprSize = l.exprSize();
		if (exprNo < lExprSize)
			return l.randomExpr(rand);
		else if (exprNo > lExprSize)
			return r.randomExpr(rand);
		else
			return this;
	}

	/** An enumeration of all possible arithmetic operators. */
	public static enum Operator {

		PLUS,
		MINUS,
		MUL,
		DIV,
		MOD;

		/** The list of operators. */
		public static final List<Operator> VALUES =
				Collections.unmodifiableList(Arrays.asList(values()));
		/** The number of operators. */
		public static final int NUM_OPS = VALUES.size();
		
		public static final Error NOT_OP = new Error("Undefined arithmetic operator.");

		@Override
		public String toString() {
			switch (this) {
			case PLUS: return "+";
			case MINUS: return "-";
			case MUL: return "*";
			case DIV: return "/";
			case MOD: return "mod";
			default: throw NOT_OP;
			}
		}
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		prettyPrint(sb);
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof BinaryExpression))
			return false;
		BinaryExpression casted = (BinaryExpression)o;
		return this.l.equals(casted.l) && this.op.equals(casted.op) && this.r.equals(casted.r);
	}
}
