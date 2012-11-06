package server;

import java.util.Hashtable;
import java.util.LinkedList;

public class Data {

	protected Hashtable<String, String> admins = new Hashtable<String, String>();
	protected Hashtable<String, String> players = new Hashtable<String, String>();

	protected Hashtable<String, String> requestsAdmin = new Hashtable<String, String>();
	protected Hashtable<String, String> requestsPlayer = new Hashtable<String, String>();

	Data() {

		if (ServerImpl.HOST_NAME.equals("CW_Server1")) {
			
			admins.put("asdf", "asdf");
			admins.put("Bryce", "lobster");
			admins.put("Nipat", "cremeBrulee");
			players.put(" ", " ");
			players.put("player", "player");

			requestsAdmin.put("tom", "a");
			requestsAdmin.put("george", "a");
			requestsAdmin.put("dick", "a");
			requestsAdmin.put("harry", "a");
			requestsAdmin.put("bill", "a");
			requestsAdmin.put("kevin", "a");
		} else {
			admins.put("f", "f");
			requestsAdmin.put("1", "a");
			requestsAdmin.put("2", "a");
			requestsAdmin.put("3", "a");
			requestsAdmin.put("4", "a");
			requestsAdmin.put("5", "a");
			requestsAdmin.put("6", "a");
			requestsAdmin.put("7", "a");
			requestsAdmin.put("8", "123");
			requestsAdmin.put("9", "123");
			requestsAdmin.put("10", "123");
			requestsAdmin.put("11", "123");
			requestsAdmin.put("12", "123");
			requestsAdmin.put("13", "123");
			requestsAdmin.put("14", "123");
			requestsAdmin.put("15", "a");
			requestsAdmin.put("16", "a");
			requestsAdmin.put("17", "a");
			requestsAdmin.put("18", "123");
			requestsAdmin.put("19", "123");
			requestsAdmin.put("20", "123");
		}

	}

	public Hashtable<String, String> admins() {
		return admins;
	}

	public Hashtable<String, String> players() {
		return admins;
	}

}
