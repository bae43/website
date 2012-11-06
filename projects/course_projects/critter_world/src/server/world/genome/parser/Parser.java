package server.world.genome.parser;

import java.io.Reader;
import server.world.genome.ast.Node;

/** An interface for parsing a critter program. */
public interface Parser {

	/** Parses a program in the given file.
	 * @param r A reader to read the program
	 * @return The parsed program, or null if the program contains a syntax error. */
	Node parse(Reader r);
}
