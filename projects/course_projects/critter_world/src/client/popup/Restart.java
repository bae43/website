package client.popup;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import server.world.grid.Grid;
import client.App;


@SuppressWarnings("serial")
public class Restart extends Popup {

	App a;
	
	public Restart(final App a) {
		this.a = a;
		title.setText("Restart World?");
		body.setText("(This cannot be undone)");
		b1.setText("Cancel");
		b2.setText("Restart");

		setPreferredSize(new Dimension(240,100));

		
		b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				a.setGrid(new Grid(a.readFile("constants.txt")));
				a.pane().remove(a.getControls());
				a.pane().remove(a.getMap());
				a.world();
				
			}
		});
	}

}