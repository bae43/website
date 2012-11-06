package client;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import client.pane.Login;
import client.popup.Message;

@SuppressWarnings("serial")
public class StartMenu extends JPanel {

	private App a;
	JPanel loginP;
	private JLayeredPane lp;
	private JButton connB;
	private JButton quitB;
	Image bg;
	Image title;
	Message m;
	private final int space = 20;

	public StartMenu(App a) {
		this.a = a;
		init();

	}

	/** initializes the menu */
	public void init() {

		super.setMinimumSize(new Dimension(650, 200));

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		Box b = new Box(BoxLayout.X_AXIS);
		add(b);

		lp = new JLayeredPane();
		b.add(lp);
		setBackground(Color.BLACK);

		try {
			bg = ImageIO.read(new File("images/screens/bg.png"));
			title = ImageIO.read(new File("images/screens/title.png"));
		} catch (Exception e) {
			System.out.println("image(s) not found");
		}

		Icon quitI = new ImageIcon("images/icons/quit.png");
		quitB = new JButton("Quit", quitI);
		format(quitB);

		connB = new JButton("Log in");
		format(connB);

		setButtons();
		initLogin();

		repaint();

		connB.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Sound.clickSound();
				loginP.setVisible(true);
				repaint();


			}
		});

		quitB.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				Sound.clickSound();
				System.exit(0);

			}
		});
	}

	/** returns output for painting all components in the map */
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		int x = (getWidth() - bg.getWidth(null)) / 2;
		int y = (getHeight() - bg.getHeight(null)) / 2;

		g.drawImage(bg, x, y, null);
		g.drawImage(title, 0, 0, null);

		setButtons();

	}

	public void initLogin() {

		loginP = new Login(a);
		loginP.setVisible(false);

		add(loginP, new Integer(3));
	}

	/** sets the location of buttons in the window */
	private void setButtons() {
		quitB.setBounds(getWidth() - (quitB.getWidth() + space), getHeight()
				- (quitB.getHeight() + space), 150, 50);

		connB.setBounds(getWidth() - (connB.getWidth() + space), getHeight()
				- (connB.getHeight() + space + (quitB.getHeight() + space)),
				150, 50);

	}

	/** formats the file chooser to custom colors and fonts */
	public void formatFC(Component[] comps) {
		for (Component comp : comps) {

			// include sub components of window
			if (comp instanceof Container)
				formatFC(((Container) comp).getComponents());
			try {
				comp.setFont(new Font("Bookman Old Style", 1, 13));
				comp.setBackground(App.bg);

			} catch (Exception e) {
			}
		}
	}

	/** formats and adds buttons to the panel */
	public void format(JButton b) {

		b.setFont(new Font("Baskerville Old Face", Font.PLAIN, 16));
		b.setBackground(App.bg);
		b.setForeground(new Color(0.4f, 0.7f, 0.8f));
		lp.add(b, 1);

	}
}
