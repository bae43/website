package server.world.genome.ast;

import java.util.Random;

import server.world.grid.Critter;

public class Number implements Expr {

	protected int value;

	public Number(int value) {
		this.value = value;
	}

	@Override
	public int eval(Critter c) {
		return value;
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
		int denom = rand.nextInt();
		while (denom == 0)
			denom = rand.nextInt();
		int adjustment = Integer.MAX_VALUE / denom;
		mi.setCondExprMutation(MutationInfo.CondExprMutation.NUM);
		return new Number(value + adjustment);
	}

	@Override
	public void prettyPrint(StringBuffer sb) {
		sb.append(value);
	}


	@Override
	public Condition randomCond(Random rand) {
		return null;
	}

	@Override
	public Expr randomExpr(Random rand) {
		return this;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		prettyPrint(sb);
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Number))
			return false;
		Number casted = (Number)o;
		return this.value == casted.value;
	}
}
