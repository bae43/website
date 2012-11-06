package client.popup;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JPanel;

import server.AdminServer;

import client.App;

@SuppressWarnings("serial")
public class RemoveUser extends Popup {

	
	public RemoveUser(final App a, final JPanel user, final String name,final boolean isMod) {
		
		setPreferredSize(new Dimension(220,130));
		
		title.setText("Remove User");
		body.setText("Permanently remove " + name + "?");
		b1.setText("Cancel");
		b2.setText("Continue");

		

		b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (a.server() instanceof AdminServer) {
					AdminServer as = (AdminServer) a.server();
					try {
						if (isMod) {

							as.removeAdmin(name);
						} else {
							as.removePlayer(name);
						}
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
					
					user.setVisible(false);
				}
				
			}
		});
	}

}
