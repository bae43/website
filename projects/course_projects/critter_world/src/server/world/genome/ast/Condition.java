package server.world.genome.ast;

import java.util.Random;

import server.world.grid.Critter;

/** An interface representing a Boolean condition in a critter program. */
public interface Condition extends Node {

	/** Evaluates the Boolean value of this condition.
	 * @param c The critter to be evaluated for
	 * @return The Boolean value of this condition */
	boolean eval(Critter c);
	
	@Override
	Condition mutate(Random rand, Program prog, MutationInfo mi);
}
