package server.world.genome.ast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import server.world.grid.Critter;

public class RelationalCondition implements Condition {

	protected Expr l;
	protected Operator op;
	protected Expr r;


	public RelationalCondition(Expr l, Operator op, Expr r) {
		this.l = l;
		this.op = op;
		this.r = r;
	}

	@Override
	public boolean eval(Critter c) {
		if (op == Operator.LT)
			return l.eval(c) < r.eval(c);
		if (op == Operator.LE)
			return l.eval(c) <= r.eval(c);
		if (op == Operator.EQ)
			return l.eval(c) == r.eval(c);
		if (op == Operator.GE)
			return l.eval(c) >= r.eval(c);
		if (op == Operator.GT)
			return l.eval(c) > r.eval(c);
		return l.eval(c) != r.eval(c);
	}

	@Override
	public int size() {
		return l.size() + r.size() + 1;
	}

	@Override
	public int condSize() {
		return l.condSize() + r.condSize() + 1;
	}

	@Override
	public int exprSize() {
		return l.exprSize() + r.exprSize();
	}

	@Override
	public Condition mutate(Random rand, Program prog, MutationInfo mi) {
		int nodeNo = rand.nextInt(size());
		int leftSize = l.size();
		if (nodeNo < leftSize) {
			//mutate left operand
			return new RelationalCondition(l.mutate(rand, prog, mi), op, r);
		}
		else if (nodeNo > leftSize) {
			//mutate right operand
			return new RelationalCondition(l, op, r.mutate(rand, prog, mi));
		}
		else {
			//mutate this node
			switch (rand.nextInt(4)) {
			case 0: //reversing the order of operands
				mi.setCondExprMutation(MutationInfo.CondExprMutation.REVERSE);
				return new RelationalCondition(r, op, l);
			case 1: //change the operation
				mi.setCondExprMutation(MutationInfo.CondExprMutation.CHANGE);
				Operator mutatedOp = Operator.VALUES.get(
						rand.nextInt(Operator.NUM_OPS));
				return new RelationalCondition(l, mutatedOp, r);
			case 2: //replacing an operand with a copy of another randomly chosen expression found in the program
				mi.setCondExprMutation(MutationInfo.CondExprMutation.DUP);
				Expr chosenExpr = prog.randomExpr(rand);
				switch (rand.nextInt(2)) {
				case 0: //replace left operand
					return new RelationalCondition(chosenExpr, op ,r);
				case 1: //replace right operand
				default:
					return new RelationalCondition(l, op, chosenExpr);
				}
			case 3: //replacing an operand with a randomly generated expression
			default:
				mi.setCondExprMutation(MutationInfo.CondExprMutation.RANDOM);
				Expr randomExpr = Program.randomExpr(rand, prog);
				switch (rand.nextInt(2)) {
				case 0: //replace left operand
					return new RelationalCondition(randomExpr, op ,r);
				case 1: //replace right operand
				default:
					return new RelationalCondition(l, op, randomExpr);
				}
			}
		}
	}

	@Override
	public void prettyPrint(StringBuffer sb) {
		l.prettyPrint(sb);
		sb.append(" ");
		sb.append(op.toString());
		sb.append(" ");
		r.prettyPrint(sb);
	}

	@Override
	public Condition randomCond(Random rand) {
		int conditionSize = condSize();
		if (conditionSize == 0)
			return null;
		int conditionNo = rand.nextInt(conditionSize);
		int lConditionSize = l.condSize();
		if (conditionNo < lConditionSize)
			return l.randomCond(rand);
		else if (conditionNo > lConditionSize)
			return r.randomCond(rand);
		else
			return this;
	}

	@Override
	public Expr randomExpr(Random rand) {
		int exprSize = exprSize();
		if (exprSize == 0)
			return null;
		int exprNo = rand.nextInt(exprSize);
		if (exprNo < l.exprSize())
			return l.randomExpr(rand);
		else
			return r.randomExpr(rand);
	}

	/** An enumeration of all possible binary relational operators. */
	public static enum Operator {

		LT,
		LE,
		EQ,
		GE,
		GT,
		NE;

		/** The list of operators. */
		public static final List<Operator> VALUES =
				Collections.unmodifiableList(Arrays.asList(values()));
		/** The number of operators. */
		public static final int NUM_OPS = VALUES.size();
		
		public static final Error NOT_OP = new Error("Undefined relational operator.");

		@Override
		public String toString() {
			switch (this) {
			case LT: return "<";
			case LE: return "<=";
			case EQ: return "=";
			case GE: return ">=";
			case GT: return ">";
			case NE: return "!=";
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
		if (!(o instanceof RelationalCondition))
			return false;
		RelationalCondition casted = (RelationalCondition)o;
		return this.l.equals(casted.l) && this.op.equals(casted.op) && this.r.equals(casted.r);
	}
}
