package server;

import java.rmi.RemoteException;

/**
 * A PlayerServer exposes upload critter permissions to the user.
 */
public class PlayerServerImpl extends ServerImpl implements PlayerServer {

	protected Server bs;
	protected AdminServer as;

	PlayerServerImpl(Data d) {
		super(d);
	}

	@Override
	public void uploadCritter(String critterFileContent) throws RemoteException {
		// DEFAULT_TASK Auto-generated method stub

	}

	@Override
	public String TEST() throws RemoteException {

		return "Player";
	}

}