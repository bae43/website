package server;

import java.rmi.RemoteException;

/**
 * A PlayerServer exposes upload critter permissions to the user.
 */
public interface PlayerServer extends Server {

	/**
	 * Upload a critter to the server.
	 * @param critterFileContent The string content of the critter definition file.
	 * @throws RemoteException
	 */
	public void uploadCritter(String critterFileContent) throws RemoteException;
}