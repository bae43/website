package server.world.genome.ast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import server.world.grid.Critter;

public class IndexedExpr implements Expr {

	protected Terminal name;
	protected Expr index;

	public IndexedExpr(Terminal name, Expr index) {
		this.name = name;
		this.index = index;
	}

	@Override
	public int eval(Critter c) {
		if (name == Terminal.MEM)
			return c.getMemAt(index.eval(c));
		if (name == Terminal.RANDOM)
			return (int) (Math.random() * index.eval(c));
		if (name == Terminal.NEARBY)
			return c.nearby(index.eval(c));
		return c.ahead(index.eval(c));
	}

	@Override
	public int size() {
		return index.size() + 1;
	}

	@Override
	public int condSize() {
		return index.condSize();
	}

	@Override
	public int exprSize() {
		return index.exprSize() + 1;
	}

	@Override
	public Expr mutate(Random rand, Program prog, MutationInfo mi) {
		int nodeNo = rand.nextInt(size());
		if (nodeNo < index.size())
			return new IndexedExpr(name, index.mutate(rand, prog, mi));
		else {
			switch (rand.nextInt(4)) {
			case 0: //replacing the condition with one of its operands
				mi.setCondExprMutation(MutationInfo.CondExprMutation.REPLACE);
				return index;
			case 1: //changing the operation to another operation
				mi.setCondExprMutation(MutationInfo.CondExprMutation.CHANGE);
				Terminal mutatedName = Terminal.VALUES.get(
						rand.nextInt(Terminal.NUM_NAMES));
				return new IndexedExpr(mutatedName, index);
			case 2: //replacing an operand with a copy of another randomly chosen expression found in the program
				mi.setCondExprMutation(MutationInfo.CondExprMutation.DUP);
				return new IndexedExpr(name, prog.randomExpr(rand));
			case 3: //replacing an operand with a randomly generated expression
			default:
				mi.setCondExprMutation(MutationInfo.CondExprMutation.RANDOM);
				return new IndexedExpr(name, Program.randomExpr(rand, prog));
			}
		}
	}

	@Override
	public void prettyPrint(StringBuffer sb) {
		sb.append(name);
		sb.append("[");
		index.prettyPrint(sb);
		sb.append("]");
	}

	@Override
	public Condition randomCond(Random rand) {
		int condSize = condSize();
		if (condSize == 0)
			return null;
		return index.randomCond(rand);
	}

	@Override
	public Expr randomExpr(Random rand) {
		int exprSize = exprSize();
		if (exprSize == 0)
			return null;
		int exprNo = rand.nextInt(exprSize);
		if (exprNo < index.exprSize())
			return index.randomExpr(rand);
		else
			return this;
	}

	/** An enumeration of all possible indexed expressions. */
	public static enum Terminal {

		MEM,
		RANDOM,
		NEARBY,
		AHEAD;

		/** The list of sensors. */
		public static final List<Terminal> VALUES =
				Collections.unmodifiableList(Arrays.asList(values()));
		/** The number of operators. */
		public static final int NUM_NAMES = VALUES.size();
		
		public static final Error NOT_NAME = new Error("Undefined indexed expression name.");

		@Override
		public String toString() {
			switch (this) {
			case MEM: return "mem";
			case RANDOM: return "random";
			case NEARBY: return "nearby";
			case AHEAD: return "ahead";
			default: throw NOT_NAME;
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
		if (!(o instanceof IndexedExpr))
			return false;
		IndexedExpr casted = (IndexedExpr)o;
		return this.name.equals(casted.name) && this.index.equals(casted.index);
	}
}
