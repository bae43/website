package server.world.genome.ast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import server.world.grid.Critter;

public class ConstantExpr implements Expr {

	protected Terminal name;
	
	public ConstantExpr(Terminal name) {
		this.name = name;
	}

	@Override
	public int eval(Critter c) {
		if (name == Terminal.DAMAGE)
			return c.damage();
		return c.food();
	}

	@Override
	public int size() {
		return 1;
	}

	@Override
	public int condSize() {
		return 0;
	}

	@Override
	public int exprSize() {
		return 1;
	}

	@Override
	public Expr mutate(Random rand, Program prog, MutationInfo mi) {
		Terminal name = Terminal.VALUES.get(
				rand.nextInt(Terminal.NUM_NAMES));
		mi.setCondExprMutation(MutationInfo.CondExprMutation.CHANGE);
		return new ConstantExpr(name);
	}

	@Override
	public void prettyPrint(StringBuffer sb) {
		sb.append(name);
	}

	@Override
	public Condition randomCond(Random rand) {
		return null;
	}

	@Override
	public Expr randomExpr(Random rand) {
		return this;
	}

	/** An enumeration of all possible constants. */
	public static enum Terminal {

		DAMAGE,
		FOOD;

		/** The list of sensors. */
		public static final List<Terminal> VALUES =
				Collections.unmodifiableList(Arrays.asList(values()));
		/** The number of operators. */
		public static final int NUM_NAMES = VALUES.size();
		
		public static final Error NOT_NAME = new Error("Undefined constant expression name.");

		@Override
		public String toString() {
			switch (this) {
			case DAMAGE: return "damage";
			case FOOD: return "food";
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
		if (!(o instanceof ConstantExpr))
			return false;
		ConstantExpr casted = (ConstantExpr)o;
		return this.name.equals(casted.name);
	}
}