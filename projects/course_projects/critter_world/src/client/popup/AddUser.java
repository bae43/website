package client.popup;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;

import server.AdminServer;

import client.App;
import client.controls.Controls;

@SuppressWarnings("serial")
public class AddUser extends Popup {

	App a;
	JLabel nameTitle;
	final protected TextField name;

	final JRadioButton mod;
	JRadioButton player;

	final protected JLabel passTitle;
	final protected JPasswordField pass;

	final protected JLabel passconTitle;
	final protected JPasswordField passcon;

	public AddUser(final App a) {

		this.a = a;

		setPreferredSize(new Dimension(380, 250));

		title.setText("Add New Users                                 ");

		remove(b1);
		remove(b2);

		// nameBox.setAlignmentX(LEFT_ALIGNMENT);
		GridLayout g = new GridLayout(4, 2);
		g.setHgap(20);
		g.setVgap(15);
		JPanel ctrlBox = new JPanel(g);
		ctrlBox.setOpaque(false);

		mod = new JRadioButton("Moderator");
		mod.setFont(App.font);
		mod.setOpaque(false);
		mod.setForeground(new Color(.6f, .6f, .8f));
		mod.setActionCommand("Moderator");

		player = new JRadioButton("Player");
		player.setActionCommand("Player");
		player.setFont(App.font);
		player.setForeground(new Color(.6f, .6f, .8f));
		player.setOpaque(false);
		player.setSelected(true);

		ButtonGroup group = new ButtonGroup();
		group.add(mod);
		group.add(player);
		add(mod);
		add(player);

		nameTitle = new JLabel("User Name:");
		nameTitle.setFont(App.font);
		nameTitle.setAlignmentX(LEFT_ALIGNMENT);
		nameTitle.setForeground(new Color(.6f, .6f, .8f));
		ctrlBox.add(nameTitle);

		name = new TextField();
		name.setFont(new Font("Baskerville Old Face", Font.BOLD, 14));
		ctrlBox.add(name);

		passTitle = new JLabel("User Password:");
		passTitle.setFont(App.font);
		passTitle.setAlignmentX(LEFT_ALIGNMENT);
		passTitle.setForeground(new Color(.6f, .6f, .8f));

		ctrlBox.add(passTitle);

		pass = new JPasswordField();
		pass.setEchoChar('•');
		pass.setFont(App.font);
		ctrlBox.add(pass);

		passconTitle = new JLabel("Confirm:");
		passconTitle.setFont(App.font);
		passconTitle.setAlignmentX(LEFT_ALIGNMENT);
		passconTitle.setForeground(new Color(.6f, .6f, .8f));

		ctrlBox.add(passconTitle);

		passcon = new JPasswordField();
		passcon.setEchoChar('•');
		passcon.setFont(App.font);
		ctrlBox.add(passcon);

		b1.setText("Back");
		b2.setText("Enter");

		ctrlBox.add(b1);
		ctrlBox.add(b2);

		add(ctrlBox);

		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				passconTitle.setText("Confirm:");
				passconTitle.setForeground(Controls.fg);

				name.setText("");
				pass.setText("");
				passcon.setText("");

			}
		});

		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (a.server() instanceof AdminServer) {
					if (new String(pass.getPassword()).equals(new String(
							passcon.getPassword()))) {
						AdminServer as = (AdminServer) a.server();
						try {
							if (mod.isSelected()) {

								as.requestAdminAcc(name.getText(), new String(
										pass.getPassword()));
								as.addAdmin(name.getText());

							} else {
								as.requestUserAcc(name.getText(), new String(
										pass.getPassword()));
								as.addPlayer(name.getText());
							}

							System.out.println("Added " + name.getText());
							name.setText("");
							passconTitle.setText("Confirm:");
							passconTitle.setForeground(Controls.fg);

						} catch (RemoteException e1) {

							e1.printStackTrace();
						}
					} else {
						System.out.println(pass.getPassword());
						System.out.println(passcon.getPassword());
						passconTitle.setText(">>Confirm:");
						passconTitle.setForeground(App.err);
					}

					pass.setText("");
					passcon.setText("");
				}
			}
		});
	}

}
