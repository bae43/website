package client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JLayeredPane;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import server.Server;
import server.ServerImpl;
import server.world.grid.Grid;
import client.controls.Controls;
import client.controls.ControlsAdmin;
import client.controls.ControlsObserver;
import client.controls.ControlsPlayer;
import client.menu.MenuAdmin;
import client.menu.MenuObserver;
import client.menu.MenuPlayer;
import client.pane.Credits;
import client.pane.Info;
import client.pane.Login;
import client.pane.RequestManager;
import client.pane.UserManager;
import client.popup.AddUser;
import client.popup.Message;
import client.popup.Quit;
import client.popup.Request;
import client.popup.Restart;

@SuppressWarnings("serial")
public class App extends JApplet {

	public static final Color bg = new Color(0f, .06f, .12f);
	public static final Color grey = new Color(0.7f, .7f, 0.8f);
	public static final Color clear = new Color(0, 0, 0, 0);
	public static final Font font = new Font("Baskerville Old Face",
			Font.PLAIN, 16);
	public static final Font fontB = new Font("Baskerville Old Face",
			Font.BOLD, 16);
	public static final Color err = new Color(.7f, .4f, .4f);

	public static final String HOST = null;

	// public static final int LOGIN_LAYER = 0;

	private Server server;
	public String currentHostName = ServerImpl.HOST_NAME;

	public static Images imgs;
	JPanel splash;
	Box smb;

	private StartMenu sm;
	private JLayeredPane lp;
	private JMenuBar menuBar;
	// private JPanel constantsViewer; //unsupported by interface
	private JPanel loginP, restartP, exitP, newPlayerP, requestP;
	private UserManager managerP;
	private RequestManager requestManagerP;
	private Info info;
	private Credits credits;

	private server.world.grid.Grid g;
	private Map map;
	private Controls ctrls;

	int mode;
	public Message m;

	public Server server() {
		return server;
	}

	public JLayeredPane lp() {
		return lp;
	}

	public void setServer(Server s) {
		this.server = s;
	}

	public JMenuBar menuBar() {
		return menuBar;
	}

	public JPanel loginP() {
		return loginP;
	}

	public JPanel restartP() {
		return restartP;
	}

	public UserManager managerP() {
		return managerP;
	}

	public JPanel exitP() {
		return exitP;
	}

	public JPanel newPlayerP() {
		return newPlayerP;
	}

	public JPanel requestP() {
		return requestP;
	}

	public void init() {

		setDefaults();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = screenSize;//new Dimension(800, 600);
		setSize(screenSize);

		
		setLocation(screenSize.width / 2 - (frameSize.width / 2),
				screenSize.height / 2 - (frameSize.height / 2));

		lp = getLayeredPane();

		splash = new Splash();
		splash.setBounds(0, 0, 800, 600);
		lp().add(splash, 10);

		setVisible(true);
	}

	public void start() {

		g = new Grid(readFile("constants.txt"));

		imgs = new Images(this);
		Sound.playAmbient();
		setMinimumSize(new Dimension(700, 400));
		customCursor();
		repaint();

		// start menu box
		smb = new Box(BoxLayout.X_AXIS);
		add(smb);

		sm = new StartMenu(this);
		smb.add(sm);

		lp().remove(splash);

		JPanel[] panes = new JPanel[4];

		int i = 0;
		newPlayerP = new AddUser(this);
		panes[i] = newPlayerP;
		i++;

		requestP = new Request(this);
		panes[i] = requestP;
		i++;

		restartP = new Restart(this);
		panes[i] = restartP;
		i++;

		exitP = new Quit();
		panes[i] = exitP;
		i++;

		for (JPanel j : panes) {
			i = 10; // always have popups show above full panels
			j.setVisible(false);
			j.setBounds(

			(int) (getWidth() - j.getPreferredSize().getWidth()) / 2,
					(int) (getHeight() - j.getPreferredSize().getHeight()) / 2,
					(int) (j.getPreferredSize().getWidth()),
					(int) (j.getPreferredSize().getHeight()));
			lp().add(j, new Integer(i));
			i++;
		}

	}

	public void world() {

		setMinimumSize(new Dimension(700, 400));
		customCursor();
		repaint();

		remove(smb);

		setBackground(Color.BLACK);

		// setMode(mode);

		map = new Map(this);
		map.setBounds(0, 0, getWidth(), getHeight());
		lp().add(map, new Integer(1));

		// Controls takes layer #2 -> added in setMode()

		JPanel[] panes = new JPanel[5];

		info = new Info(this);
		panes[0] = info;

		credits = new Credits(this);
		panes[1] = credits;

		loginP = new Login(this);
		panes[2] = loginP;

		managerP = new UserManager(this);
		panes[3] = managerP;

		requestManagerP = new RequestManager(this);
		panes[4] = requestManagerP;

		for (JPanel j : panes) {
			int i = 3;
			j.setVisible(false);
			j.setBounds(0, 0, getWidth(), getHeight());
			lp().add(j, new Integer(i));
			i++;

		}
		setVisible(true);
	}

	public void setMode(int i) {

		try {
			lp().remove(ctrls);
		} catch (NullPointerException npe) {
		}

		mode = i;
		if (mode == 0) {
			menuBar = new MenuObserver(this);
			ctrls = new ControlsObserver(this);

		} else if (mode == 1) {
			menuBar = new MenuPlayer(this);
			ctrls = new ControlsPlayer(this);

		} else if (mode == 2) {
			menuBar = new MenuAdmin(this);
			ctrls = new ControlsAdmin(this);
			managerP = new UserManager(this);

			lp().add(managerP, new Integer(10));

		}

		ctrls.setBounds(getWidth() - ctrls.width(), 0, ctrls.width(),
				getHeight());
		lp().add(ctrls, new Integer(2));

		setJMenuBar(menuBar);

		// resets the selected hex in the new controls
		try {
			ctrls.setHex(map.getSelectedHex());
			ctrls.updateHexStats();
		} catch (NullPointerException npe) {
		}

	}

	public int mode() {
		return mode;

	}

	/** returns the map that's displayed with the frame */
	public Map getMap() {
		return map;
	}

	/** returns the frames grid */
	public server.world.grid.Grid getGrid() {
		return g;
	}

	/** sets the frames grid */
	public void setGrid(server.world.grid.Grid grid) {
		g = grid;

	}

	/** returns the controls for this frame */
	public Controls getControls() {
		return new Controls(this);
		//return ctrls; //XXX
	}

	/** Saves the string text to the file name given */
	public static void save(String text, String filename) throws IOException {

		FileOutputStream fstream = new FileOutputStream(filename);
		DataOutputStream out = new DataOutputStream(fstream);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
		try {

			writer.write(text);

		} catch (IOException ioe) {

		} finally {
			writer.close();
		}
	}

	/** Adds a custom cursor loaded from images */
	private void customCursor() {

		setCursor(getToolkit().createCustomCursor(
				new ImageIcon("images/icons/cursor.png").getImage(),
				new Point(0, 0), "Cursor"));

	}

	/** returns the layered pane that contains every component in the frame */
	public JLayeredPane pane() {
		return lp();

	}

	/** returns the login screen to switch permissions */
	public JPanel loginScreen() {
		return loginP;

	}

	/** shows login screen */
	public void showLogin() {
		ctrls.setVisible(false);
		loginP.setBounds(0, 0, getWidth(), (int) (getHeight()));
		loginP.setVisible(true);
	}

	/** shows info screen */
	public void showInfo() {
		ctrls.setVisible(false);
		info.setBounds(0, 0, getWidth(), (int) (getHeight()));
		info.setVisible(true);
	}

	/** shows credits screen */
	public void showCredits() {
		ctrls.setVisible(false);
		credits.setBounds(0, 0, getWidth(), (int) (getHeight()));
		credits.setVisible(true);
	}

	/** shows manager screen */
	public void showManager() {
		ctrls.setVisible(false);
		managerP.updateList();
		managerP.setBounds(0, 0, getWidth(), (int) (getHeight()));
		managerP.setVisible(true);
	}

	/** shows manager screen */
	public void showRequestManager() {
		ctrls.setVisible(false);
		requestManagerP.updateList();
		requestManagerP.setBounds(0, 0, getWidth(), (int) (getHeight()));
		requestManagerP.setVisible(true);
	}

	private static void setDefaults() {

		UIDefaults defaults = UIManager.getDefaults();

		Font font = new Font("Baskerville Old Face", Font.PLAIN, 15);
		Color foreground = new Color(.75f, .75f, .9f);
		Color background = new Color(0, .08f, .16f);
		Color selectionBackground = new Color(0, .15f, .25f);

		defaults.put("Menu.background", background);// XXX doesn't work?
		defaults.put("Menu.foreground", background);
		defaults.put("Menu.selectionBackground", selectionBackground);
		defaults.put("Menu.selectionForeground", foreground);
		defaults.put("Menu.font", font);

		defaults.put("MenuItem.background", background);
		defaults.put("MenuItem.foreground", foreground);
		defaults.put("MenuItem.selectionBackground", selectionBackground);
		defaults.put("MenuItem.selectionForeground", foreground);
		defaults.put("MenuItem.font", font);

		defaults.put("CheckBoxMenuItem.background", background);
		defaults.put("CheckBoxMenuItem.foreground", foreground);
		defaults.put("CheckBoxMenuItem.selectionBackground",
				selectionBackground);
		defaults.put("CheckBoxMenuItem.selectionForeground", foreground);
		defaults.put("CheckBoxMenuItem.font", font);

		defaults.put("Label.font", font);

		defaults.put("OptionPane.Background", background);

		defaults.put("Button.font", font);
		defaults.put("Button.foreground", foreground);
		defaults.put("Button.background", background);
		defaults.put("Button.select", selectionBackground);

		// defaults.put("TextField.font", font);
		// defaults.put("TextField.foreground", foreground);
		// defaults.put("TextField.background", background);
		// defaults.put("Button.selectionForeground", selectionBackground);

	}

	public BufferedReader readFile(String f) {
		BufferedReader br = null;
		try {
			URL source = new URL(getCodeBase(), f);
			br = new BufferedReader(new InputStreamReader(source.openStream()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return br;
	}

}
