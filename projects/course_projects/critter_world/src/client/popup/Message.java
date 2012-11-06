package client.popup;

import java.awt.Dimension;

@SuppressWarnings("serial")
public class Message extends Popup {

	public Message(String title, String message) {

		setPreferredSize(new Dimension(180, 100));
		super.title.setText(title);
		super.body.setText(message);
		buttBox.remove(b2);
		b1.setText("Dismiss");

	}

}
