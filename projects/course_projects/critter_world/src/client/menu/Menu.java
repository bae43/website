package client.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import server.world.grid.Critter;
import server.world.grid.Grid;
import server.world.grid.Hex;
import client.App;
import client.Sound;


@SuppressWarnings("serial")
public class Menu extends JMenuBar {

	// JMenu worldTitle, fileTitle, optionsTitle, infoTitle;
	protected JMenu world, options;

	protected JMenu open, save;

	protected JMenuItem kill, addPlayer, request, manage,requestManager;
	protected JCheckBoxMenuItem uploadToggle,downloadToggle;

	// JMenuItem reset, quit, oCritter, oWorld, sCritter, sWorld;

	Menu(final App a) {

		// generic menus the same for every view
		JMenu menu;
		JMenuItem menuItem;
		JCheckBoxMenuItem cbMenuItem;

		setFont(new Font("Baskerville Old Face", 16, 16));
		// menuBar.setBackground(new Color(0, .06f, .12f, .8f));

		/* /*************** World ************** */
		world = new JMenu("World");
		world.setBackground(new Color(0, .06f, .12f, .8f));

		world.setForeground(Color.gray);
		world.setFont(new Font("Baskerville Old Face", 16, 16));
		world.setMnemonic(KeyEvent.VK_M);

		add(world);

		menuItem = new JMenuItem("Restart World", new ImageIcon(
				"images//icons//world.png"));
		menuItem.setMnemonic(KeyEvent.VK_T);

		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sound.clickSound();

				a.restartP().setVisible(true);

			}

		});

		world.add(menuItem);

		kill = new JMenuItem("Kill All", new ImageIcon(
				"images//icons//icon.png"));
		world.add(kill);

		menuItem = new JMenuItem("Exit", new ImageIcon("images/icons/quit.png"));
		menuItem.setMnemonic(KeyEvent.VK_E);

		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				a.exitP().setVisible(true);

			}
		});

		/* /************* OPEN ********************* */

		open = new JMenu("Open");
		open.setBackground(new Color(0, .06f, .12f, .8f));
		open.setMnemonic(KeyEvent.VK_O);

		menuItem = new JMenuItem("Critter");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				ActionEvent.ALT_MASK));

		menuItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Sound.clickSound();
				JFileChooser fc;
				try {
					fc = new JFileChooser(System.getProperty("user.dir")
							+ "/critters");
				} catch (Exception ex) {
					fc = new JFileChooser(System.getProperty("user.dir"));
				}

				FileNameExtensionFilter txtFilter = new FileNameExtensionFilter(
						"Critter File (.txt)", "txt");

				fc.setFileFilter(txtFilter);

				if (fc.showOpenDialog(a) == JFileChooser.APPROVE_OPTION) {
					Sound.clickSound();

					File critter = fc.getSelectedFile();
					Hex s = a.getMap().getSelectedHex();
					if (!s.hasCritter()) {
						s.addNewCritter(new Critter(a.getGrid(), s.getColumn(),
								s.getRow(), (int) (Math.random() * 6), critter));
					}
					a.getControls().critOn();
					a.getControls().updateHexStats();
					a.getMap().repaint();

				}
			}

		});

		open.add(menuItem);

		menuItem = new JMenuItem("World");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
				ActionEvent.ALT_MASK));

		menuItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Sound.clickSound();

				JFileChooser fc;
				try {
					fc = new JFileChooser(System.getProperty("user.dir")
							+ "/worlds");
				} catch (Exception ex) {
					fc = new JFileChooser(System.getProperty("user.dir"));
				}

				FileNameExtensionFilter txtFilter = new FileNameExtensionFilter(
						"World File (.txt)", "txt");

				fc.setFileFilter(txtFilter);

				if (fc.showOpenDialog(a) == JFileChooser.APPROVE_OPTION) {
					Sound.clickSound();

					// Loads in same window
					a.setGrid(new Grid(a.readFile(fc.getName()), a
							.readFile("constants.txt")));

					a.pane().remove(a.getMap());
					a.pane().remove(a.getControls());
					a.world();
					a.getMap().repaint();

				}
			}

		});
		open.add(menuItem);

		world.add(open);

		/* /************* SAVE ********************* */

		save = new JMenu("Save");
		save.setBackground(new Color(0, .06f, .12f, .8f));
		save.setMnemonic(KeyEvent.VK_S);

		menuItem = new JMenuItem("Critter");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				ActionEvent.ALT_MASK));
		// RESUME add save to be toggled enabled

		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Sound.clickSound();

				StringBuffer rules = new StringBuffer();
				try {
					if (a.getMap().getSelectedHex().hasCritter()) {
						a.getMap().getSelectedHex().getCritter().getGenome()
								.prettyPrint(rules);
					} else {
						return;
					}
				} catch (NullPointerException npe) {
				}

				JFileChooser fc = new JFileChooser(System
						.getProperty("user.dir"));

				if (fc.showSaveDialog(a) == JFileChooser.APPROVE_OPTION) {
					try {
						String filename = fc.getSelectedFile().toString();
						App.save(rules.toString(),
								filename.endsWith(".txt") ? filename : filename
										+ ".txt");
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			}
		});

		save.add(menuItem);

		menuItem = new JMenuItem("World");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
				ActionEvent.ALT_MASK));
		menuItem.setEnabled(false);

		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Sound.clickSound();

				String world = "not implemented yet";

				JFileChooser fc = new JFileChooser(System
						.getProperty("user.dir"));

				if (fc.showSaveDialog(a) == JFileChooser.APPROVE_OPTION) {
					try {
						App.save(world.toString(), fc.getSelectedFile()
								.toString() + ".txt");
						System.out.println(fc.getSelectedFile().toString()
								+ ".txt");
					} catch (IOException e) {
						e.printStackTrace();
						System.out.println("couldn't save file");
					}
				}
			}
		});
		save.add(menuItem);
		world.add(save);

		world.addSeparator();

		uploadToggle = new JCheckBoxMenuItem("Disable User Uploads");
		uploadToggle.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				boolean selected = ((AbstractButton) e.getSource()).getModel()
						.isSelected();
				if (selected) {

					System.out.println("Disabled Uploads");

				} else {
					System.out.println("Enabled Uploads");
				}
			}

		});
		world.add(uploadToggle);
		
		downloadToggle = new JCheckBoxMenuItem("Disable User Downloads");
		downloadToggle.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				boolean selected = ((AbstractButton) e.getSource()).getModel()
						.isSelected();
				if (selected) {

					System.out.println("Disabled Downloads");

				} else {
					System.out.println("Enabled Downloads");
				}
			}

		});
		world.add(downloadToggle);

		world.addSeparator();

		menuItem = new JMenuItem("Exit", new ImageIcon("images/icons/quit.png"));
		menuItem.setMnemonic(KeyEvent.VK_E);

		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Sound.clickSound();

				a.exitP().setVisible(true);

			}

		});

		world.add(menuItem);

		/* /*************** Options ************** */
		options = new JMenu("Options");
		setBackground(new Color(0, .06f, .12f, .2f));
		options.setForeground(Color.gray);
		options.setFont(new Font("Baskerville Old Face", 16, 16));

		menuItem = new JMenuItem("Log in");

		menuItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				a.showLogin();

			}

		});

		options.add(menuItem);

		addPlayer = new JMenuItem("Add User");

		addPlayer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				a.newPlayerP().setVisible(true);

			}

		});

		options.add(addPlayer);
		
		request = new JMenuItem("Request Account");

		request.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				a.requestP().setVisible(true);

			}

		});

		options.add(request);

		manage = new JMenuItem("Manage Players");

		manage.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				
				a.showManager();

			}

		});

		options.add(manage);
		
		requestManager = new JMenuItem("Manage Requests");

		requestManager.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				
				a.showRequestManager();

			}

		});

		options.add(requestManager);

		options.addSeparator();

		cbMenuItem = new JCheckBoxMenuItem("Hide World Data");
		cbMenuItem.setMnemonic(KeyEvent.VK_H);
		cbMenuItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				boolean selected = ((AbstractButton) e.getSource()).getModel()
						.isSelected();
				if (selected) {

					a.getControls().setVisible(false);
				} else {

					a.getControls().setVisible(true);

				}
			}

		});
		options.add(cbMenuItem);

		cbMenuItem = new JCheckBoxMenuItem("Hide Rock Border");
		cbMenuItem.setMnemonic(KeyEvent.VK_R);

		cbMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean selected = ((AbstractButton) e.getSource()).getModel()
						.isSelected();
				if (selected) {

					a.getMap().setBorder(0);
					a.getMap().repaint();
				} else {

					a.getMap().setBorder(1);
					a.getMap().repaint();

				}

			}

		});
		options.add(cbMenuItem);

		cbMenuItem = new JCheckBoxMenuItem("Pause Animation");
		cbMenuItem.setMnemonic(KeyEvent.VK_A);

		cbMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a.getMap().startStopAnim();
			}

		});
		options.add(cbMenuItem);

		options.addSeparator();

		menuItem = new JMenuItem("Zoom In");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_UP,
				ActionEvent.CTRL_MASK));

		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				a.getMap().zoom(228.8818359375, 23.84185791015625); // max hex &
																	// buffer

			}

		});

		options.add(menuItem);

		menuItem = new JMenuItem("Zoom Out");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,
				ActionEvent.CTRL_MASK));

		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				a.getMap().zoom(6.442450944000005, 0.6710886400000003); // min
																		// hex &
				// buffer

			}

		});

		options.add(menuItem);

		menuItem = new JMenuItem("Focus on Selected");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
				ActionEvent.CTRL_MASK));

		menuItem.setMnemonic(KeyEvent.VK_F);

		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				a.getMap().recenter();

			}

		});

		options.add(menuItem);

		add(options);

		/* /*************** Info ************** */
		menu = new JMenu("Info");
		setBackground(new Color(0, .06f, .12f, .2f));
		menu.setForeground(Color.gray);
		menu.setFont(new Font("Baskerville Old Face", 16, 16));

		menuItem = new JMenuItem("Instructions");
		menuItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				a.showInfo();
			}

		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Credits");
		menuItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				a.showCredits();
			}

		});

		menu.add(menuItem);

		add(menu);
	}
}
