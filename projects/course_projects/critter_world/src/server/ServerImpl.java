package server;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;

import client.App;
import client.controls.Controls;

import server.threadPool.ThreadPool;
import server.world.grid.Grid;

public class ServerImpl implements Server {

	public static final String HOST_NAME = "CW_Server1";

	protected PlayerServer ps;
	protected AdminServer as;

	protected Data data;

	/** Number of worker threads in thread pool */
	private static int NUM_WORKER_THREADS = 5;
	/** Number of requests thread pool can hold at a time */
	private static int RINGBUFFER_SIZE = 20;
	/**
	 * Limited size thread pool where requests are added to be executed
	 */
	private static ThreadPool pool;

	/** File of constants world initializes from */
	private static File constants;
	/** Grid object which contains critter world model */
	private static Grid grid;

	public static void main(String[] args) {

		System.setProperty("java.rmi.server.codebase", ServerImpl.class
				.getProtectionDomain().getCodeSource().getLocation().toString());

		ServerImpl bs;
		Server server;
		PlayerServerImpl ps;
		PlayerServer playerServer;
		AdminServerImpl as;
		AdminServer adminServer;

		try {

			pool = new ThreadPool(NUM_WORKER_THREADS, RINGBUFFER_SIZE);
			// grid = new Grid(constants);

			Data d = new Data();

			bs = new ServerImpl(d);
			server = (Server) UnicastRemoteObject.exportObject(bs, 0);

			ps = new PlayerServerImpl(d);
			playerServer = (PlayerServer) UnicastRemoteObject.exportObject(ps,
					0);

			as = new AdminServerImpl(d);
			adminServer = (AdminServer) UnicastRemoteObject.exportObject(as, 0);

			// set pointers

			bs.ps = ps;
			bs.as = as;

			ps.bs = bs;
			ps.as = as;

			as.bs = bs;
			as.ps = ps;

			final Registry registry = LocateRegistry.getRegistry("localhost");
			registry.rebind(HOST_NAME, server);

			JFrame m = new JFrame(HOST_NAME);
			m.setSize(new Dimension(200, 100));
			m.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			m.setResizable(false);
			JButton b = new JButton("Disconnect");
			b.setFont(new Font("Consolas", Font.BOLD, 24));

			b.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					try {
						registry.unbind(HOST_NAME);
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					System.exit(0);
				}
			});
			m.add(b);
			m.setVisible(true);

			System.out.println("Server " + HOST_NAME + " ready");

		} catch (Exception e) {

			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}

	}

	ServerImpl(Data d) {// , PlayerServerImpl ps, AdminServerImpl as) {

		this.data = d;
		// this.ps = ps;
		// this.as = as;
	}

	@Override
	public int maxColumn() throws RemoteException {
		// DEFAULT_TASK Auto-generated method stub
		return 0;
	}

	@Override
	public int maxRow() throws RemoteException {
		// DEFAULT_TASK Auto-generated method stub
		return 0;
	}

	@Override
	public Cell[] getSubsection(int llCol, int llRow, int numCols, int numRows)
			throws RemoteException {
		// DEFAULT_TASK Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRunning() throws RemoteException {
		// DEFAULT_TASK Auto-generated method stub
		return false;
	}

	@Override
	public long getSimRate() throws RemoteException {
		// DEFAULT_TASK Auto-generated method stub
		return 0;
	}

	@Override
	public int stepsCount() throws RemoteException {
		// DEFAULT_TASK Auto-generated method stub
		return 0;
	}

	@Override
	public int numCritters() throws RemoteException {
		// DEFAULT_TASK Auto-generated method stub
		return 0;
	}

	@Override
	public int numPlants() throws RemoteException {
		// DEFAULT_TASK Auto-generated method stub
		return 0;
	}

	@Override
	public String getCritterProgram(int id) throws RemoteException {
		// DEFAULT_TASK Auto-generated method stub
		return null;
	}

	@Override
	public int[] getCritterMemory(int id) throws RemoteException {
		// DEFAULT_TASK Auto-generated method stub
		return null;
	}

	@Override
	public String getCritterCurrentRule(int id) throws RemoteException {
		// DEFAULT_TASK Auto-generated method stub
		return null;
	}

	@Override
	public Action getCritterAction(int id) throws RemoteException {
		// Auto-generated method stub
		return null;
	}

	@Override
	public String requestUserAcc(String user, String pw) throws RemoteException {
		data.requestsPlayer.put(user, pw);
		return null;
	}

	@Override
	public String requestAdminAcc(String user, String pw)
			throws RemoteException {
		data.requestsAdmin.put(user, pw);
		return "Requested";
	}

	@Override
	public PlayerServer getPlayerServer(String user, String pw)
			throws RemoteException {
		if (data.players.get(user).equals(pw)) {
			return ps;
		}
		return null;
	}

	@Override
	public AdminServer getAdminServer(String user, String pw)
			throws RemoteException {
		if (data.admins.get(user) != null) {
			if (data.admins.get(user).equals(pw)) {
				return as;
			}
		}
		return null;
	}

	@Override
	public int[] getSpeciesAttributes(int species_id) throws RemoteException {
		// Auto-generated method stub
		return null;
	}

	@Override
	public String getSpeciesProgram(int species_id) throws RemoteException {
		// Auto-generated method stub
		return null;
	}

	@Override
	public int[] getLineage(int species_id) throws RemoteException {
		// Auto-generated method stub
		return null;
	}

	@Override
	public String TEST() throws RemoteException {
		return "Basic";
	}

}
