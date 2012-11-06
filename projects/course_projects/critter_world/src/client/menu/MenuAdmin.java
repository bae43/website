package client.menu;

import client.App;

@SuppressWarnings("serial")
public class MenuAdmin extends Menu {

	public MenuAdmin(App a) {
		super(a);
		options.remove(request);

	}

}
