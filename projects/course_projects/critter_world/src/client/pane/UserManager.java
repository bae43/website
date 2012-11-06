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

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import server.AdminServer;

import client.App;

@SuppressWarnings("serial")
public class UserManager extends JPanel {

	App a;
	JPanel users;
	JPanel lower;
	JScrollPane sp;

	public UserManager(App a) {
		this.a = a;
		run();

	}

	public void run() {
		setOpaque(false);
		setVisible(false);
		setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		setBackground(new Color(0f, .06f, .12f, .2f));

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		add(Box.createRigidArea(new Dimension(0, 30)));

		JLabel title = new JLabel("Manage Users:");
		title.setFont(new Font("Baskerville Old Face", Font.BOLD, 22));
		title.setForeground(new Color(0.7f, 0.7f, .8f));
		add(title, CENTER_ALIGNMENT);

		add(Box.createRigidArea(new Dimension(0, 15)));

		lower = new JPanel();
		lower.setOpaque(false);
		lower.setLayout(new BoxLayout(lower, BoxLayout.X_AXIS));

		add(lower);

		lower.add(Box.createRigidArea(new Dimension(200, 0)));

		updateList();

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
				String[] admins = as.getAdminList();
				if (admins != null) {
					for (String admin : admins) {
						users.add(new UserData(admin, true));
					}

				}

				String[] players = as.getPlayerList();
				if (players != null) {
					for (String player : players) {
						users.add(new UserData(player, false));
					}

				}
			}

		} catch (Exception e1) {
		}

		sp = new JScrollPane(users);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setVisible(true);
		sp.setMaximumSize(new Dimension(500, 500));

		sp.setBackground(new Color(0f, .06f, .12f, 1f));

		lower.add(sp, CENTER_ALIGNMENT);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension labelSize = this.getPreferredSize();
		setLocation(screenSize.width / 2 - (labelSize.width / 2),
				screenSize.height / 2 - (labelSize.height / 2));

	}

	private class UserData extends JPanel {

		UserData(final String name, final boolean isMod) {

			final JPanel this0 = this;
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

			add(Box.createRigidArea(new Dimension(0, 6)));
			GridLayout g = new GridLayout(1, 5);
			setMaximumSize(new Dimension(500, 32));
			g.setHgap(20);

			setBackground(new Color(0, 0, 0, 0));

			JPanel m = new JPanel();
			m.setBackground(new Color(0, 0, 0, 0));
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

			JButton delete = new JButton("Remove");

			delete.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					if (a.server() instanceof AdminServer) {
						AdminServer as = (AdminServer) a.server();
						try {
							if (isMod) {

								as.removeAdmin(name);
								System.out.println("removed moderator " + name);
							} else {

								as.removePlayer(name);
								System.out.println("removed player " + name);
							}

						} catch (RemoteException e1) {
							e1.printStackTrace();
						}

						this0.setVisible(false);
					}
				}
			});
			m.add(delete);

			m.add(new JLabel());

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