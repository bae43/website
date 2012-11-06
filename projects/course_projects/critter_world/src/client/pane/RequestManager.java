package client.pane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import server.AdminServer;

import client.App;

@SuppressWarnings("serial")
public class RequestManager extends JPanel {

	App a;
	JPanel users;
	JPanel lower;
	JScrollPane sp;
	Set<String> approvedAdmins = new HashSet<String>();
	Set<String> approvedPlayers = new HashSet<String>();
	Set<String> rejectAdmins = new HashSet<String>();
	Set<String> rejectPlayers = new HashSet<String>();

	public RequestManager(App a) {

		this.a = a;
		run();

	}

	public void run() {
		setOpaque(false);
		setVisible(false);
		setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
		setBackground(new Color(0f, .06f, .12f, .2f));

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		add(Box.createRigidArea(new Dimension(0, 30)));

		JLabel title = new JLabel("Manage Requests:");
		title.setFont(new Font("Baskerville Old Face", Font.BOLD, 22));
		title.setForeground(new Color(0.7f, 0.7f, .8f));
		add(title, CENTER_ALIGNMENT);

		add(Box.createRigidArea(new Dimension(0, 15)));

		JLabel header = new JLabel(
				"           Approve        Reject            Hold");
		header.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		header.setForeground(new Color(0.6f, 0.6f, .7f));
		add(header, CENTER_ALIGNMENT);

		lower = new JPanel();
		lower.setOpaque(false);
		lower.setLayout(new BoxLayout(lower, BoxLayout.X_AXIS));

		add(lower);

		lower.add(Box.createRigidArea(new Dimension(200, 0)));

		updateList();

		add(Box.createRigidArea(new Dimension(0, 25)));

		JButton cancel = new JButton("Cancel");

		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				a.getControls().setVisible(true);
				setVisible(false);
			}
		});
		add(cancel);

		JButton ok = new JButton("OK");

		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (a.server() instanceof AdminServer) {
					AdminServer as = (AdminServer) a.server();

					
					try {

						// //APPROVE

						// XXX
						// java.util.ConcurrentModificationException
						try {
							for (String n : approvedAdmins) {
								as.addAdmin(n);
								approvedAdmins.remove(n);

							}
						} catch (NullPointerException npe) {
						}
						try {
							for (String n : approvedPlayers) {
								as.addPlayer(n);
								approvedAdmins.remove(n);
							}
						} catch (NullPointerException npe) {
						}

						// //REJECT						
						try {
							for (String n : rejectAdmins) {
								as.rejectAdmin(n);
								rejectAdmins.remove(n);
							}
						} catch (NullPointerException npe) {
						}

						try {
							for (String n : rejectPlayers) {
								as.rejectPlayer(n);
								rejectPlayers.remove(n);
							}
						} catch (NullPointerException npe) {
						}

					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
					a.getControls().setVisible(true);
					setVisible(false);
				}

			}
		});

		// add(cancel);
		// add(ok);

		GridLayout gl = new GridLayout(1, 2);
		gl.setHgap(500);
		JPanel botBox = new JPanel(gl);
		botBox.setOpaque(false);
		// botBox.setMaximumSize(new Dimension(500, 0));

		botBox.add(cancel);
		botBox.add(ok);

		add(botBox, CENTER_ALIGNMENT);
		
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				a.getControls().setVisible(true);
				setVisible(false);
			}
		});


	}

	public void updateList() {

		try {

			lower.remove(sp);
			users = new JPanel();
			users.setBackground(new Color(0, 0, 0, 0));
			users.setLayout(new BoxLayout(users, BoxLayout.Y_AXIS));

			if (a.server() instanceof AdminServer) {

				AdminServer as = ((AdminServer) a.server());
				String[] admins = as.getAdminRequests();
				if (admins != null) {
					for (String admin : admins) {
						users.add(new UserData(admin, true));
					}

				}

				String[] players = as.getPlayerRequests();
				if (players != null) {
					for (String player : players) {
						users.add(new UserData(player, false));
					}

				}
			}

		} catch (Exception e1) {
		}

		sp = new JScrollPane(users);
		// sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setVisible(true);
		sp.setMaximumSize(new Dimension(500, 500));
		sp.setMinimumSize(new Dimension(500, 500));

		sp.setBackground(new Color(0f, .06f, .12f, 1f));

		lower.add(sp, CENTER_ALIGNMENT);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension labelSize = this.getPreferredSize();
		setLocation(screenSize.width / 2 - (labelSize.width / 2),
				screenSize.height / 2 - (labelSize.height / 2));

	}

	private class UserData extends JPanel {

		UserData(final String name, final boolean isMod) {

			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

			add(Box.createRigidArea(new Dimension(0, 6)));
			GridLayout g = new GridLayout(1, 5);
			setMaximumSize(new Dimension(500, 32));
			g.setHgap(20);

			setBackground(App.bg);

			JPanel m = new JPanel();
			m.setBackground(App.bg);
			add(m);
			m.setLayout(g);

			JLabel nameTitle = new JLabel("   " + name);
			nameTitle.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
			nameTitle.setForeground(new Color(0.6f, 0.6f, .8f));
			m.add(nameTitle);

			JLabel type = new JLabel();
			if (isMod) {
				type.setText("Moderator");
			} else {
				type.setText("Player");
			}

			type.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
			type.setForeground(new Color(0.4f, 0.4f, .6f));
			m.add(type);

			JRadioButton approve = new JRadioButton();
			approve.setBackground(App.bg);

			approve.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					try {
						if (isMod) {
							rejectAdmins.remove(name);
							approvedAdmins.add(name);
						} else {
							rejectPlayers.remove(name);
							approvedPlayers.add(name);
						}

					} catch (NullPointerException npe) {
					}
				}
			});

			JRadioButton reject = new JRadioButton();
			reject.setBackground(App.bg);

			reject.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						if (isMod) {
							rejectAdmins.add(name);
							approvedAdmins.remove(name);
						} else {
							rejectPlayers.add(name);
							approvedPlayers.remove(name);
						}
					} catch (NullPointerException npe) {
					}
				}

			});

			JRadioButton hold = new JRadioButton();
			hold.setBackground(App.bg);
			hold.setSelected(true);

			hold.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						if (isMod) {
							rejectAdmins.remove(name);

						} else {
							rejectPlayers.remove(name);

						}
					} catch (NullPointerException npe) {
					}
				}

			});

			ButtonGroup group = new ButtonGroup();
			group.add(approve);
			group.add(reject);
			group.add(hold);

			m.add(approve);
			m.add(reject);
			m.add(hold);

			setVisible(true);
			


		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		setBounds(0, 0, getWidth(), getHeight());

		GradientPaint gradientPaint = new GradientPaint(0, 0, new Color(0,
				0.03f, .08f, 0.5f), getWidth() / 3, getHeight(), new Color(0,
				0.03f, .08f, 0.9f));
		if (g instanceof Graphics2D) {
			Graphics2D graphics2D = (Graphics2D) g;
			graphics2D.setPaint(gradientPaint);
			graphics2D.fillRect(0, 0, getWidth(), getHeight());
		}
	}
}