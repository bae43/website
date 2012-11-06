package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Hashtable;

public interface Server extends Remote {



	//world information
	/**
	 * 
	 * @return MAX_COLUMN constant
	 */
	public int maxColumn() throws RemoteException;

	/**
	 * 
	 * @return MAX_ROW constant
	 */
	public int maxRow() throws RemoteException;

	/**
	 * Returns an array of all nonempty cells within the specified rectangular
         * subsection of the world.
	 * @param llCol The column index of the lower-left cell.
	 * @param llRow The row index of the lower-left cell.
	 * @param numCols The number of columns in this subsection.
	 * @param numRows The number of rows in this subsection.
	 * @return An array of all nonempty cells within the specified subsection.
	 * @throws RemoteException
	 */
	public Cell[] getSubsection(int llCol, int llRow, int numCols, int numRows)
			throws RemoteException;

	//simulation information
	/**
	 * 
	 * @return {@code true} if the simulation is automatically advancing.
	 * @throws RemoteException
	 */
	public boolean isRunning() throws RemoteException;

	/**
	 * Returns the simulation rate.
	 * @return Maximum number of steps per minute.
	 * @throws RemoteException
	 */
	public long getSimRate() throws RemoteException;

	/**
	 * 
	 * @return The current time step of the world.
	 */
	public int stepsCount() throws RemoteException;

	/**
	 * 
	 * @return The number of critters in the world.
	 */
	public int numCritters() throws RemoteException;

	/**
	 * 
	 * @return The number of plants in the world.
	 */
	public int numPlants() throws RemoteException;

	//critter query interface
	/**
	 * Returns the pretty-print of the program of the specified critter.
	 * @param id Critter ID
	 * @return The pretty-print of the critter's program, or {@code null} if
	 * the critter does not exist or there is no program associated with the critter.
	 * @throws RemoteException
	 */
	public String getCritterProgram(int id) throws RemoteException;

	/**
	 * Returns the special-purpose entries (i.e., first several entries) of a critter's memory.
	 * @param id Critter ID
	 * @return The special-purpose entries of a critter's memory, or {@code null} if
	 * the critter does not exist.
	 * @throws RemoteException
	 */
	public int[] getCritterMemory(int id) throws RemoteException;

	/**
	 * Returns the pretty-print of the most recently executed rule of a critter.
	 * @param id Critter ID
	 * @return The pretty-print of the critter's most recently executed rule, or {@code null} if
	 * the critter does not exist or it did not execute a rule in the last time step.
	 * @throws RemoteException
	 */
	public String getCritterCurrentRule(int id) throws RemoteException;

	/**
	 * Returns an action a critter should take, overriding regular behavior.
	 * @param id CritterID
	 * @return The overriding action, or {@code null} if the critter should
	 * follow its regular behavior.
	 * @throws RemoteException
	 */
	public Action getCritterAction(int id) throws RemoteException;

	/**
	 * An enum listing all possible actions.
	 */
	public enum Action {
		WAIT, FORWARD, BACKWARD, LEFT, RIGHT,
		EAT, ATTACK, GROW, BUD, MATE
	}

	//login interface
	/**
	 * Submit a request for a user account.
	 * @param user The username
	 * @param pw The password
	 * @return A string indicating the outcome of the request.
	 * @throws RemoteException
	 */
	public String requestUserAcc(String user, String pw) throws RemoteException;

	/**
	 * Submit a request for an admin account.
	 * @param user The username
	 * @param pw The password
	 * @return A string indicating the outcome of the request.
	 * @throws RemoteException
	 */
	public String requestAdminAcc(String user, String pw) throws RemoteException;

	/**
	 * Returns a reference to a remote PlayerServer implementation. If the login is invalid, the return is null and a RemoteException is thrown
	 * @param user The username
	 * @param pw The password
	 * @return The PlayerServer, or {@code null} if login is invalid.
	 * @throws RemoteException
	 */
	public PlayerServer getPlayerServer(String user, String pw) throws RemoteException;

	/**
	 * Returns a reference to a remote AdminServer implementation. If the login is invalid, the return is null and a RemoteException is thrown
	 * @param user The username
	 * @param pw The password
	 * @return The AdminServer, or {@code null} if login is invalid.
	 * @throws RemoteException
	 */
	public AdminServer getAdminServer(String user, String pw) throws RemoteException;

	//species query interface
	/**
	 * Returns an array of length 3 containing the species's memory length,
	 * defense, and offense.
	 * @param species_id Species ID
	 * @return The attributes of the species.
	 * @throws RemoteException
	 */
	public int[] getSpeciesAttributes(int species_id) throws RemoteException;

	/**
	 * Returns the pretty-print of the program associated with the specified species.
	 * @param species_id Species ID
	 * @return The pretty-print of the species's program.
	 * @throws RemoteException
	 */
	public String getSpeciesProgram(int species_id) throws RemoteException;

	/**
	 * Returns an array tracing the species's lineage back as far as possible.
	 * The first element in the array is the species id, the second is the
	 * id of the species it was generated from (the "mother's" species in the case
	 * of new species generated by mating), and so on. Thus, each species id in the
	 * array will occur only once.
	 * @param species_id Initial species ID
	 * @return Lineage information
	 * @throws RemoteException
	 */
	public int[] getLineage(int species_id) throws RemoteException;
	
	
	//REMOVE
	public String TEST() throws RemoteException;

	
}
