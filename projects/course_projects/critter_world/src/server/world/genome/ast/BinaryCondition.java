package server.world.genome.ast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import server.world.grid.Critter;

/** A representation of a binary Boolean condition. */
public class BinaryCondition implements Condition {

	Condition l;
	Operator op; 
	Condition r;

	/** Create an AST representation of l op r. */
	public BinaryCondition(Condition l, Operator op, Condition r) {
		this.l = l;
		this.op = op;
		this.r = r;
	}

	@Override
	public boolean eval(Critter c) {
		if (op == Operator.OR)
			return l.eval(c) || r.eval(c);
		return l.eval(c) && r.eval(c);
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
			return new BinaryCondition(l.mutate(rand, prog, mi), op, r);
		}
		else if (nodeNo > leftSize) {
			//mutate right operand
			return new BinaryCondition(l, op, r.mutate(rand, prog, mi));
		}
		else {
			//mutate this node
			switch (rand.nextInt(5)) {
			case 0: //reversing the order of operands
				mi.setCondExprMutation(MutationInfo.CondExprMutation.REVERSE);
				return new BinaryCondition(r, op, l);
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
				return new BinaryCondition(l, mutatedOp, r);
			case 3: //replacing an operand with a copy of another randomly chosen condition found in the program
				mi.setCondExprMutation(MutationInfo.CondExprMutation.DUP);
				Condition chosenCond = prog.randomCond(rand);
				switch (rand.nextInt(2)) {
				case 0: //replace left operand
					return new BinaryCondition(chosenCond, op ,r);
				case 1: //replace right operand
				default:
					return new BinaryCondition(l, op, chosenCond);
				}
			case 4: //replacing an operand with a randomly generated condition
			default:
				mi.setCondExprMutation(MutationInfo.CondExprMutation.RANDOM);
				Condition randomCond = Program.randomCond(rand, prog);
				switch (rand.nextInt(2)) {
				case 0: //replace left operand
					return new BinaryCondition(randomCond, op ,r);
				default: //replace right operand
					return new BinaryCondition(l, op, randomCond);
				}
			}
		}
	}

	@Override
	public void prettyPrint(StringBuffer sb) {
		sb.append("{");
		l.prettyPrint(sb);
		sb.append(" ");
		sb.append(op.toString());
		sb.append(" ");
		r.prettyPrint(sb);
		sb.append("}");
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
		else if (condNo > lCondSize)
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
		int lExprSize = l.exprSize();
		if (exprNo < lExprSize)
			return l.randomExpr(rand);
		else
			return r.randomExpr(rand);
	}

	/** An enumeration of all possible binary condition operators. */
	public static enum Operator {

		OR,
		AND;

		/** The list of operators. */
		public static final List<Operator> VALUES =
				Collections.unmodifiableList(Arrays.asList(values()));
		/** The number of operators. */
		public static final int NUM_OPS = VALUES.size();
		
		public static final Error NOT_OP = new Error("Undefined binary condition operator.");

		@Override
		public String toString() {
			switch (this) {
			case OR: return "or";
			case AND: return "and";
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
		if (!(o instanceof BinaryCondition))
			return false;
		BinaryCondition casted = (BinaryCondition)o;
		return this.l.equals(casted.l) && this.op.equals(casted.op) && this.r.equals(casted.r);
	}
}
