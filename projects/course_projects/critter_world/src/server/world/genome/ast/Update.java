package server.world.genome.ast;

import java.util.Random;

import server.world.grid.Critter;

public class Update implements Node {

	Expr indexExpr;
	Expr expr;
	
	public Update(Expr indexExpr, Expr expr) {
		this.indexExpr = indexExpr;
		this.expr = expr;
	}
	
	public int indexEval(Critter c) {
		return indexExpr.eval(c);
	}
	
	public int eval(Critter c) {
		return expr.eval(c);
	}

	@Override
	public int size() {
		return indexExpr.size() + expr.size() + 1;
	}

	@Override
	public int condSize() {
		return indexExpr.condSize() + expr.condSize();
	}

	@Override
	public int exprSize() {
		return indexExpr.exprSize() + expr.exprSize();
	}

	@Override
	public Update mutate(Random rand, Program prog, MutationInfo mi) {
		int nodeNo = rand.nextInt(size());
		int indexExprSize = indexExpr.size();
		if (nodeNo < indexExprSize) {
			//mutate indexExpr
			return new Update(indexExpr.mutate(rand, prog, mi), expr);
		}
		else if (nodeNo > indexExprSize) {
			//mutate expr
			return new Update(indexExpr, expr.mutate(rand, prog, mi));
		}
		else {
			//mutate this node
			switch (rand.nextInt(3)) {
			case 0: //reversing the order of operands
				mi.setCondExprMutation(MutationInfo.CondExprMutation.REVERSE);
				return new Update(expr, indexExpr);
			case 1: //replacing an operand with a copy of another randomly chosen expression found in the program
				mi.setCondExprMutation(MutationInfo.CondExprMutation.DUP);
				Expr chosenExpr = prog.randomExpr(rand);
				switch (rand.nextInt(2)) {
				case 0: //replace indexExpr
					return new Update(chosenExpr, expr);
				case 1: //replace expr
				default:
					return new Update(indexExpr, chosenExpr);
				}
			case 2: //replacing an operand with a randomly generated expression
			default:
				mi.setCondExprMutation(MutationInfo.CondExprMutation.RANDOM);
				Expr randomExpr = Program.randomExpr(rand, prog);
				switch (rand.nextInt(2)) {
				case 0: //replace indexExpr
					return new Update(randomExpr, expr);
				default: //replace expr
					return new Update(indexExpr, randomExpr);
				}
			}
		}
	}

	@Override
	public void prettyPrint(StringBuffer sb) {
		sb.append("mem[");
		indexExpr.prettyPrint(sb);
		sb.append("] := ");
		expr.prettyPrint(sb);
	}

	@Override
	public Condition randomCond(Random rand) {
		int condSize = condSize();
		if (condSize == 0)
			return null;
		int conditionNo = rand.nextInt(condSize);
		if (conditionNo < indexExpr.condSize())
			return indexExpr.randomCond(rand);
		else
			return expr.randomCond(rand);
	}

	@Override
	public Expr randomExpr(Random rand) {
		int exprSize = exprSize();
		if (exprSize == 0)
			return null;
		int exprNo = rand.nextInt(exprSize);
		if (exprNo < indexExpr.exprSize())
			return indexExpr.randomExpr(rand);
		else
			return expr.randomExpr(rand);
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		prettyPrint(sb);
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Update))
			return false;
		Update casted = (Update)o;
		return this.indexExpr.equals(casted.indexExpr) && this.expr.equals(casted.expr);
	}
}
