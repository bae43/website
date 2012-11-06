package server.world.genome.ast;

import java.util.InputMismatchException;

public class MutationInfo {

	public static enum ProgMutation {
		NONE,
		REMOVE,
		DUP,
		SWAP,
		MUTATE;

		@Override
		public String toString() {
			switch(this) {
			case NONE: return "(none)";
			case REMOVE: return "remove a rule";
			case DUP: return "duplicate a rule";
			case SWAP: return "interchange rule order";
			case MUTATE: return "mutate a rule";
			default: throw new InputMismatchException("Unknown mutation type.");
			}
		}
	}

	public static enum RuleMutation {
		NONE,
		COND,
		CMD;

		@Override
		public String toString() {
			switch(this) {
			case NONE: return "(none)";
			case COND: return "condition";
			case CMD: return "command";
			default: throw new InputMismatchException("Unknown mutation type.");
			}
		}
	}

	public static enum CondExprMutation {
		NONE,
		REVERSE,
		REPLACE,
		CHANGE,
		NUM,
		DUP,
		RANDOM;

		@Override
		public String toString() {
			switch(this) {
			case NONE: return "(none)";
			case REVERSE: return "reverse order of operands";
			case REPLACE: return "replace with one of operands";
			case CHANGE: return "change operation or action";
			case NUM: return "change number";
			case DUP: return "replace an operand with a copy of existing node";
			case RANDOM: return "replace an operand with a generated node";
			default: throw new InputMismatchException("Unknown mutation type.");
			}
		}
	}

	private ProgMutation progMtt;
	private RuleMutation ruleMtt;
	private CondExprMutation condExprMtt;
	
	public MutationInfo() {
		this.progMtt = ProgMutation.NONE;
		this.ruleMtt = RuleMutation.NONE;
		this.condExprMtt = CondExprMutation.NONE;
	}

	public void setProgMutation(ProgMutation type) {
		this.progMtt = type;
	}

	public void setRuleMutation(RuleMutation type) {
		this.ruleMtt = type;
	}

	public void setCondExprMutation(CondExprMutation type) {
		this.condExprMtt = type;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Rule set mutation: ");
		sb.append(progMtt);
		sb.append('\n');
		sb.append("Rule mutation: ");
		sb.append(ruleMtt);
		sb.append('\n');
		sb.append("Condition or expression mutation: ");
		sb.append(condExprMtt);
		sb.append('\n');
		return sb.toString();
	}
}
