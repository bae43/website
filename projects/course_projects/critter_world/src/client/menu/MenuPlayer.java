package client.menu;

import client.App;

@SuppressWarnings("serial")
public class MenuPlayer extends Menu {

	public MenuPlayer(App a) {
		super(a);
		world.remove(kill);

		open.setEnabled(false);
		// file.remove(open);

		world.remove(uploadToggle);
		world.remove(downloadToggle);

		options.remove(addPlayer);
		options.remove(manage);
		options.remove(requestManager);
	}

}
