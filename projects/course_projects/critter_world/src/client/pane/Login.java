package client.pane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import server.Server;
import server.world.grid.Grid;
import client.App;
import client.Sound;
import client.popup.Message;

@SuppressWarnings("serial")
public class Login extends JPanel {

	App a;
	boolean startup;

	private final JComboBox viewPick;

	JLabel nameTitle;
	TextField name;
	JLabel passTitle;
	JPasswordField password;
	JLabel serverTitle;
	TextField serverName;

	JButton quit;
	JButton go;

	public Login(final App a) {

		this.a = a;
		setOpaque(false);

		if (a.getMap() == null) {
			startup = true;
		} else {
			startup = false;
		}

		Box b = new Box(BoxLayout.Y_AXIS);
		add(b);

		b.add(Box.createRigidArea(new Dimension(0, 250)));

		JLabel logTitle = new JLabel("Login:");
		logTitle.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		logTitle.setForeground(new Color(.6f, .6f, .8f));
		logTitle.setAlignmentX(RIGHT_ALIGNMENT);
		b.add(logTitle);

		b.add(Box.createRigidArea(new Dimension(0, 30)));

		String[] views = { "Observer", "Player", "Moderator" };

		viewPick = new JComboBox(views);
		viewPick.setPreferredSize(new Dimension(50, 30));
		viewPick.setAlignmentX(CENTER_ALIGNMENT);
		b.add(viewPick);

		b.add(Box.createRigidArea(new Dimension(0, 20)));

		Box nameBox = new Box(BoxLayout.X_AXIS);
		// nameBox.setAlignmentX(LEFT_ALIGNMENT);
		b.add(nameBox);

		nameTitle = new JLabel("Username:               ");
		nameTitle.setFont(App.fontB);
		nameTitle.setAlignmentX(LEFT_ALIGNMENT);
		nameTitle.setForeground(new Color(.6f, .6f, .8f));
		nameBox.add(nameTitle);

		name = new TextField();
		name.setFont(new Font("Baskerville Old Face", Font.BOLD, 14));
		b.add(name);

		b.add(Box.createRigidArea(new Dimension(0, 10)));

		Box passBox = new Box(BoxLayout.X_AXIS);
		b.add(passBox);

		passTitle = new JLabel("Password:               ");
		passTitle.setFont(App.fontB);
		passTitle.setAlignmentX(LEFT_ALIGNMENT);
		passTitle.setForeground(new Color(.6f, .6f, .8f));
		passBox.add(passTitle);

		password = new JPasswordField();
		password.setEchoChar('•');
		password.setFont(App.fontB);
		b.add(password);

		b.add(Box.createRigidArea(new Dimension(0, 10)));

		Box serverBox = new Box(BoxLayout.X_AXIS);
		// nameBox.setAlignmentX(LEFT_ALIGNMENT);
		b.add(serverBox);

		serverTitle = new JLabel("Server Name:            ");
		serverTitle.setFont(App.fontB);
		serverTitle.setAlignmentX(LEFT_ALIGNMENT);
		serverTitle.setForeground(new Color(.6f, .6f, .8f));
		serverBox.add(serverTitle);

		serverName = new TextField();
		serverName.setFont(new Font("Baskerville Old Face", Font.BOLD, 14));

		// try{
		// serverName.setText(registry.list()[0];);
		// }catch(Exception e){serverName.setText("");}
		serverName.setText(a.currentHostName);
		b.add(serverName);

		b.add(Box.createRigidArea(new Dimension(0, 20)));

		Box buttBox = new Box(BoxLayout.X_AXIS);
		buttBox.setAlignmentX(LEFT_ALIGNMENT);
		b.add(buttBox);

		quit = new JButton("Return");
		buttBox.add(quit);

		buttBox.add(Box.createRigidArea(new Dimension(30, 0)));

		go = new JButton("Log in");
		buttBox.add(go);

		nameTitle.setVisible(false);
		name.setVisible(false);
		passTitle.setVisible(false);
		password.setVisible(false);

		setVisible(true);
		repaint();

		viewPick.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String mode = (String) viewPick.getSelectedItem();

				if (mode.equals("Observer")) {
					nameTitle.setVisible(false);
					name.setVisible(false);

					passTitle.setVisible(false);
					password.setVisible(false);

				} else {
					nameTitle.setVisible(true);
					name.setVisible(true);

					passTitle.setVisible(true);
					password.setVisible(true);
				}
			}
		});
		// allows the enter key to login after entering password
		password.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					login();

				}
			}
		});

		// allows the enter key to login after entering password
		serverName.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					login();

				}
			}
		});

		quit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				reset();

				try {
					a.getControls().setVisible(true);
				} catch (NullPointerException npe) {
				}
			}
		});

		go.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Sound.clickSound();
				passTitle.setForeground(App.err);
				login();

			}
		});

	}

	// adds a black gradient as a background
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		GradientPaint gradientPaint = new GradientPaint(0, 0, new Color(0, 0,
				0, 0), 0, getHeight(), new Color(0, 0.03f, .08f, 1f));
		if (g instanceof Graphics2D) {
			Graphics2D graphics2D = (Graphics2D) g;
			graphics2D.setPaint(gradientPaint);
			graphics2D.fillRect(0, 0, getWidth(), getHeight());
		}
	}

	private void login() {

		Message m = a.m;
		try {
			a.lp().remove(m);// remove old messages
		}

		catch (NullPointerException npe) {
		}

		try {

			long t = System.currentTimeMillis();

			Registry registry = LocateRegistry.getRegistry();
			Server s = (Server) registry.lookup(serverName.getText());

			int mode = viewPick.getSelectedIndex();

			if (mode == 1) {
				s = s.getPlayerServer(name.getText(),
						new String(password.getPassword()));

			} else if (mode == 2) {

				s = s.getAdminServer(name.getText(), new String(password.getPassword()));

			}

			

			if (s != null) {
				a.setServer(s);

				// failed login, do nothing
			} else {
				return;
			}

			System.out.println("Connected to Server " + serverName.getText()
					+ " (" + ((double) (System.currentTimeMillis() - t) / 1000)
					+ " s)");

			if (startup) {
				removeAll();
				a.setGrid(new Grid(a.readFile("constants.txt")));
				a.world();

			}

			a.setMode(mode);
			a.getControls().setVisible(true);
			setVisible(false);
			a.currentHostName = serverName.getText();

			name.setText("");
			passTitle.setForeground(new Color(.6f, .6f, .8f));

		} catch (Exception e) {
			passTitle.setForeground(new Color(.6f, .6f, .8f));
			m = new client.popup.Message("Error:", "Server Not Found");
			m.setBounds(
					(int) (getWidth() - m.getPreferredSize().getWidth()) / 2,
					(int) (getHeight() - m.getPreferredSize().getHeight()) / 2,
					(int) (m.getPreferredSize().getWidth()),
					(int) (m.getPreferredSize().getHeight()));
			a.lp().add(m, new Integer(9));
			m.setVisible(true);
		}finally{
			password.setText("");
		}
	}

	public void setSelectedMode(int i) {
		viewPick.setSelectedIndex(i);
	}

	public int getSelectedMode(int i) {
		return viewPick.getSelectedIndex();
	}

	public void reset() {
		serverName.setText(a.currentHostName);
		viewPick.setSelectedIndex(0);
		name.setText("");
		password.setText("");
		passTitle.setForeground(new Color(.6f, .6f, .8f));
		setVisible(false);

	}
}
