package server.world.genome.ast;

import java.util.Random;

/** An interface representing a node in the abstract syntax tree of a program. */
public interface Node {

	/** @return The number of nodes in this AST, including the current node. */
	int size();
	
	/** @return The number of condition nodes in this AST, including the current node. */
	int condSize();

	/** @return The number of expression nodes in this AST, including the current node. */
	int exprSize();
	
	/** Return a version of the same AST with one random mutation in it.
	 * May have side effects on the original AST.
	 * @param rand A pseudorandom number generator
	 * @param prog The program containing this node
	 * @param mi Information on mutation applied to prog
	 * (for selecting a random node in the program) */
	Node mutate(Random rand, Program prog, MutationInfo mi);

	/** Appends the program represented by this node prettily to
	 * the given StringBuffer.
	 * @param rand A pseudorandom number generator
	 * @param sb The StringBuffer to be appended */
	void prettyPrint(StringBuffer sb);
 
	/** Choose a random condition in this AST.
	 * @param rand A pseudorandom number generator
	 * @return A random condition in this AST,
	 * or null if the subtree does not contain a condition */
	Condition randomCond(Random rand);

	/** Choose a random expression in this AST.
	 * @param rand A pseudorandom number generator
	 * @return A random expression in this AST,
	 * or null if the subtree does not contain an expression */
	Expr randomExpr(Random rand);
}
