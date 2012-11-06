package client.controls;

import client.App;

@SuppressWarnings("serial")
public class ControlsObserver extends Controls {

	public ControlsObserver(App frame) {
		super(frame);

		permTitle.setText(" Observer ");

		remove(autoToggle);
		remove(boxTop);

		remove(speedBarTitle);
		remove(speedBar);
		remove(ctrlBox);

	}

}
