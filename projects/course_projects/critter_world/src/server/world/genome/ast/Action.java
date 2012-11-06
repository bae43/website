package server.world.genome.ast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Action implements Node {
	
	protected Terminal action;

	public static final int WAIT = 10;
	public static final int FORWARD = 11;
	public static final int BACKWARD = 12;
	public static final int LEFT = 13;
	public static final int RIGHT = 14;
	public static final int EAT = 15;
	public static final int ATTACK = 16;
	public static final int GROW = 17;
	public static final int BUD = 18;
	public static final int MATE = 19;

	public static final Error NOT_ACTION = new Error("Action ID does not match any of the defined actions.");

	public Action(Terminal action) {
		this.action = action;
	}

	public Terminal getAction() {
		return action;
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
		return 0;
	}

	@Override
	public Action mutate(Random rand, Program prog, MutationInfo mi) {
		Terminal action = Terminal.VALUES.get(
				rand.nextInt(Terminal.NUM_ACTIONS));
		mi.setCondExprMutation(MutationInfo.CondExprMutation.CHANGE);
		return new Action(action);
	}

	@Override
	public void prettyPrint(StringBuffer sb) {
		sb.append(action.toString());
	}

	@Override
	public Condition randomCond(Random rand) {
		return null;
	}

	@Override
	public Expr randomExpr(Random rand) {
		return null;
	}
	
	/** An enumeration of all possible actions. */
	public static enum Terminal {
		WAIT,
		FORWARD,
		BACKWARD,
		LEFT,
		RIGHT,
		EAT,
		ATTACK,
		GROW,
		BUD,
		MATE;

		/** The list of actions. */
		public static final List<Terminal> VALUES =
				Collections.unmodifiableList(Arrays.asList(values()));
		/** The number of actions. */
		public static final int NUM_ACTIONS = VALUES.size();

		public static final Error NOT_ACTION = new Error("Undefined action");

		@Override
		public String toString() {
			switch (this) {
			case WAIT: return "wait";
			case FORWARD: return "forward";
			case BACKWARD: return "backward";
			case LEFT: return "left";
			case RIGHT: return "right";
			case EAT: return "eat";
			case ATTACK: return "attack";
			case GROW: return "grow";
			case BUD: return "bud";
			case MATE: return "mate";
			default: throw NOT_ACTION;
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
		if (!(o instanceof Action))
			return false;
		Action casted = (Action)o;
		return this.action.equals(casted.action);
	}
}
