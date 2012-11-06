package client.popup;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.App;

@SuppressWarnings("serial")
public class Quit extends Popup {

	
	public Quit() {
		
		setPreferredSize(new Dimension(220,80));
		
		body.setText("Are you sure you wish to exit?");
		b1.setText("Cancel");
		b2.setText("Exit");

		

		
		b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
	}

}
