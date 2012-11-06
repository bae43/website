package client.controls;

import client.App;

@SuppressWarnings("serial")
public class ControlsPlayer extends Controls {

	public ControlsPlayer(App frame) {
		super(frame);

		permTitle.setText(" Player ");

		remove(autoToggle);
		remove(boxTop);

		remove(speedBarTitle);
		remove(speedBar);

		remove(ctrlBox);

	}

}
