package client.menu;

import client.App;

@SuppressWarnings("serial")
public class MenuObserver extends Menu {

	public MenuObserver(App a) {
		super(a);

		
		world.remove(kill);
		world.remove(open);
		world.remove(uploadToggle);
		world.remove(downloadToggle);
		
		options.remove(addPlayer);
		options.remove(manage);
		options.remove(requestManager);

		//file.remove(open);

	}

}
