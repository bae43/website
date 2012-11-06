package server;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * An AdminServer exposes the administrative interface to the critter system.
 */
public class AdminServerImpl extends PlayerServerImpl implements AdminServer {

	protected PlayerServer ps;
	protected Server bs;

	public AdminServerImpl(Data d) {
		super(d);
	}

	@Override
	public void loadWorld(String worldFileContent) throws RemoteException {
		// Auto-generated method stub

	}

	@Override
	public void simStep() throws RemoteException {
		// Auto-generated method stub

	}

	@Override
	public void startSim() throws RemoteException {
		// Auto-generated method stub

	}

	@Override
	public void pauseSim() throws RemoteException {
		// Auto-generated method stub

	}

	@Override
	public void resetSim() throws RemoteException {
		// Auto-generated method stub

	}

	@Override
	public void setSimRate(long rate) throws RemoteException {
		// Auto-generated method stub

	}

	@Override
	public void killAll() throws RemoteException {
		// Auto-generated method stub

	}

	@Override
	public void kill(int id) throws RemoteException {
		// Auto-generated method stub

	}

	@Override
	public void control(int id, Action a) throws RemoteException {
		// Auto-generated method stub

	}

	@Override
	public boolean uploadsOn() throws RemoteException {
		// Auto-generated method stub
		return false;
	}

	@Override
	public void setCritterUploads(boolean on) throws RemoteException {
		// Auto-generated method stub

	}

	@Override
	public boolean downloadsOn() throws RemoteException {
		// Auto-generated method stub
		return false;
	}

	@Override
	public void setCritterDownloads(boolean on) throws RemoteException {
		// Auto-generated method stub

	}

	@Override
	public String[] getPlayerList() throws RemoteException {
		ArrayList<String> array = new ArrayList<String>(data.players.keySet());
		return array.toArray(new String[array.size()]);
	}

	@Override
	public String[] getPlayerRequests() throws RemoteException {
		ArrayList<String> array = new ArrayList<String>(
				data.requestsPlayer.keySet());
		return array.toArray(new String[array.size()]);
	}

	@Override
	public void addPlayer(String name) throws RemoteException {

		data.players().put(name, data.requestsPlayer.get(name));
		data.requestsPlayer.remove(name);

	}

	@Override
	public void rejectPlayer(String name) throws RemoteException {
		data.requestsPlayer.remove(name);
	

	}

	@Override
	public void removePlayer(String name) throws RemoteException {
		data.players.remove(name);

	}

	@Override
	public String[] getAdminList() throws RemoteException {
		ArrayList<String> array = new ArrayList<String>(data.admins.keySet());
		return array.toArray(new String[array.size()]);

	}

	@Override
	public String[] getAdminRequests() throws RemoteException {
		ArrayList<String> array = new ArrayList<String>(
				data.requestsAdmin.keySet());
		return array.toArray(new String[array.size()]);
	}

	@Override
	public void addAdmin(String name) throws RemoteException {
		data.admins().put(name, data.requestsAdmin.get(name));
		data.requestsAdmin.remove(name);
	}

	@Override
	public void rejectAdmin(String name) throws RemoteException {
		data.requestsAdmin.remove(name);
		System.out.println("removed " + name);

	}

	@Override
	public void removeAdmin(String name) throws RemoteException {
		data.admins.remove(name);
	}



}