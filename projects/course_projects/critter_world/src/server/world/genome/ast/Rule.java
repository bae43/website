package server.world.genome.ast;

import java.util.Random;

/** A representation of a critter rule. */
public class Rule implements Node {

	protected Condition cond;
	protected Command cmd;

	public Rule(Condition cond, Command cmd) {
		this.cond = cond;
		this.cmd = cmd;
	}

	public Condition cond() {
		return cond;
	}

	public Command cmd() {
		return cmd;
	}

	@Override
	public int size() {
		return cond.size() + cmd.size() + 1;
	}

	@Override
	public int condSize() {
		return cond.condSize() + cmd.condSize();
	}

	@Override
	public int exprSize() {
		return cond.exprSize() + cmd.exprSize();
	}

	@Override
	public Rule mutate(Random rand, Program prog, MutationInfo mi) {
		switch (rand.nextInt(2)) {
		case 0: //changing the condition
			mi.setRuleMutation(MutationInfo.RuleMutation.COND);
			return new Rule(cond.mutate(rand, prog, mi), cmd);
		case 1: //changing the command
		default:
			mi.setRuleMutation(MutationInfo.RuleMutation.CMD);
			return new Rule(cond, cmd.mutate(rand, prog, mi));
		}
	}

	@Override
	public void prettyPrint(StringBuffer sb) {
		cond.prettyPrint(sb);
		sb.append(" --> ");
		cmd.prettyPrint(sb);
		sb.append(';');
		sb.append('\n');
	}

	@Override
	public Condition randomCond(Random rand) {
		int condSize = condSize();
		if (condSize == 0)
			return null;
		int condNo = rand.nextInt(condSize);
		if (condNo < cond.condSize())
			return cond.randomCond(rand);
		else
			return cmd.randomCond(rand);
	}

	@Override
	public Expr randomExpr(Random rand) {
		int exprSize = exprSize();
		if (exprSize == 0)
			return null;
		int exprNo = rand.nextInt(exprSize);
		if (exprNo < cond.exprSize())
			return cond.randomExpr(rand);
		else
			return cmd.randomExpr(rand);
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		prettyPrint(sb);
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Rule))
			return false;
		Rule casted = (Rule)o;
		return this.cond.equals(casted.cond) && this.cmd.equals(casted.cmd);
	}
}
