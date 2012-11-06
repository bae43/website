package server.world.genome.ast;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/** A representation of a critter program. */
public class Program implements Node {

	protected List<Rule> rules;

	public Program(List<Rule> rules) {
		this.rules = rules;
	}
	
	public Iterator<Rule> rules() {
		return rules.iterator();
	}
	
	public int rulesSize() {
		return rules.size();
	}

	@Override
	public int size() {
		int size = 0;
		for (Rule rule : rules)
			size += rule.size();
		return size + 1;
	}

	@Override
	public int condSize() {
		int size = 0;
		for (Rule rule : rules)
			size += rule.condSize();
		return size;
	}

	@Override
	public int exprSize() {
		int size = 0;
		for (Rule rule : rules)
			size += rule.exprSize();
		return size;
	}
	
	public Program mutate() {
		return mutate(new MutationInfo());
	}

	public Program mutate(MutationInfo mi) {
		return mutate(new Random(), this, mi);
	}

	@Override
	public Program mutate(Random rand, Program prog, MutationInfo mi) {
		List<Rule> mutatedRules = new LinkedList<Rule>(rules);
		//Pick a rule.
		int ruleNo = 0;
		if (rules.size() != 0) {
			ruleNo = rand.nextInt(rules.size());
			Rule rule;
			//Pick a mutation.
			switch (rand.nextInt(4)) {
			case 0: //removing a rule
				mutatedRules.remove(ruleNo);
				mi.setProgMutation(MutationInfo.ProgMutation.REMOVE);
				break;
			case 1: //duplicating a rule
				rule = mutatedRules.get(ruleNo);
				mutatedRules.add(ruleNo, rule);
				mi.setProgMutation(MutationInfo.ProgMutation.DUP);
				break;
			case 2: //interchanging the order of two rules
				rule = mutatedRules.get(ruleNo);
				int mutatedRuleNo = rand.nextInt(mutatedRules.size());
				Rule mutatedRule = mutatedRules.get(mutatedRuleNo);
				mutatedRules.set(ruleNo, mutatedRule);
				mutatedRules.set(mutatedRuleNo, rule);
				mi.setProgMutation(MutationInfo.ProgMutation.SWAP);
				break;
			case 3: //mutating a rule
				rule = mutatedRules.get(ruleNo);
				rule = rule.mutate(rand, prog, mi);
				mutatedRules.set(ruleNo, rule);
				mi.setProgMutation(MutationInfo.ProgMutation.MUTATE);
				break;
			}
			return new Program(mutatedRules);
		}
		return this;
	}

	@Override
	public void prettyPrint(StringBuffer sb) {
		for (Rule rule : rules)
			rule.prettyPrint(sb);
	}

	@Override
	public Condition randomCond(Random rand) {
		int condSize = condSize();
		if (condSize == 0)
			return null;
		int condNo = rand.nextInt(condSize);
		for (Rule rule : rules) {
			int ruleCondSize = rule.condSize();
			if (condNo < ruleCondSize)
				return rule.randomCond(rand);
			else
				condNo -= ruleCondSize;
		}
		throw new MutationException("A condition expected but not found");
	}

	@Override
	public Expr randomExpr(Random rand) {
		int exprSize = exprSize();
		if (exprSize == 0)
			return null;
		int exprNo = rand.nextInt(exprSize);
		for (Rule rule : rules) {
			int ruleExprSize = rule.exprSize();
			if (exprNo < ruleExprSize)
				return rule.randomExpr(rand);
			else
				exprNo -= ruleExprSize;
		}
		throw new MutationException("An expression expected but not found");
	}

	public static Condition randomCond(Random rand, Program prog) {
		//select condition type
		switch (rand.nextInt(2)) {
		case 0: //binary condition
			Condition binl = prog.randomCond(rand);
			BinaryCondition.Operator binop = BinaryCondition.Operator.VALUES.get(
					rand.nextInt(BinaryCondition.Operator.NUM_OPS));
			Condition binr = prog.randomCond(rand);
			return new BinaryCondition(binl, binop, binr);
		case 1: //relational condition
		default:
			Expr rell = prog.randomExpr(rand);
			RelationalCondition.Operator relop = RelationalCondition.Operator.VALUES.get(
					rand.nextInt(RelationalCondition.Operator.NUM_OPS));
			Expr relr = prog.randomExpr(rand);
			return new RelationalCondition(rell, relop, relr);
		}
	}

	public static Expr randomExpr(Random rand, Program prog) {
		Expr zero = new Number(0);

		//select expression type
		switch (rand.nextInt(3)) {
		case 0: //binary expression
			Expr binl = (rand.nextInt(2) == 0 ?
					prog.randomExpr(rand) : zero);
			BinaryExpression.Operator binop = BinaryExpression.Operator.VALUES.get(
					rand.nextInt(BinaryExpression.Operator.NUM_OPS));
			Expr binr = (rand.nextInt(2) == 0 ?
					prog.randomExpr(rand) : zero);
			return new BinaryExpression(binl, binop, binr);
		case 1: //constant expression
			ConstantExpr.Terminal constName = ConstantExpr.Terminal.VALUES.get(
					rand.nextInt(ConstantExpr.Terminal.NUM_NAMES));
			return new ConstantExpr(constName);
		case 2: //indexed expression
		default:
			IndexedExpr.Terminal indexName = IndexedExpr.Terminal.VALUES.get(
					rand.nextInt(IndexedExpr.Terminal.NUM_NAMES));
			Expr indexExpr = (rand.nextInt(2) == 0 ?
					prog.randomExpr(rand) : zero);
			return new IndexedExpr(indexName, indexExpr);
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
		if (!(o instanceof Program))
			return false;
		Program casted = (Program)o;
		if (this.rules.size() != casted.rules.size())
			return false;
		for (Iterator<Rule> i1 = this.rules.iterator(), i2 = casted.rules.iterator();
				i1.hasNext() && i2.hasNext(); ) {
			Rule r1 = i1.next();
			Rule r2 = i2.next();
			if (!r1.equals(r2))
				return false;
		}
		return true;
	}
}
