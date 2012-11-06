package server.world.genome.ast;

import java.util.Random;

import server.world.grid.Critter;

/** An interface representing an arithmetic expression in a critter program. */
public interface Expr extends Node {

	/** Evaluates the int value of this expression.
	 * @param c The critter to be evaluated for
	 * @return The int value of this condition */
	int eval(Critter c);

	@Override
	Expr mutate(Random rand, Program prog, MutationInfo mi);
}
