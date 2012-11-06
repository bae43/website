package client.popup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import server.AdminServer;
import server.Server;
import client.App;
import client.controls.Controls;

@SuppressWarnings("serial")
public class Request extends AddUser {

	public Request(final App a) {

		super(a);

		title.setText("Request Account                                 ");

		b1.setText("Back");
		b2.setText("Request");

		//@Override

		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (new String(pass.getPassword()).equals(new String(passcon
						.getPassword()))) {
					Server bs = a.server();
					try {
						if (mod.isSelected()) {

							bs.requestAdminAcc(name.getText(),
									new String(pass.getPassword()));

						} else {
							bs.requestUserAcc(name.getText(),
									new String(pass.getPassword()));

						}

						name.setText("");
						passconTitle.setText("Confirm:");
						passconTitle.setForeground(Controls.fg);
						setVisible(false);

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
		});
	}

}
