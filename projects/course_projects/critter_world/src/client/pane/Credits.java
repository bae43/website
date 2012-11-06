package client.pane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.App;

@SuppressWarnings("serial")
public class Credits extends JPanel {
	
	App a;

	public Credits(App a) {
		this.a = a;
		run();

	}

	public void run() {
		setOpaque(false);
		setVisible(false);
		setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		setBackground(new Color(0f, .06f, .12f, .2f));

		setAlignmentY(Component.TOP_ALIGNMENT);
		
		add(Box.createRigidArea(new Dimension(0, a.getHeight() / 4)));

		JLabel title = new JLabel("Credits:");
		title.setFont(new Font("Baskerville Old Face", Font.ITALIC, 22));
		title.setForeground(new Color(0.7f, 0.7f, .8f));
		add(title);

		add(Box.createRigidArea(new Dimension(0, 15)));
		
		JLabel info;

		info = new JLabel(
				"Critter World \n (2012) Bryce Evans & Nipat Tuntasood \n \n All images (c) 2012 Bryce Evans \n \nMusic: \n"
						+ "Tzikas, Jannis.  \"In the Heaven's Eye.\"  Daze of our Lives.\n"
						+ "      Adapt. Tim Schuldt. Suntrip Records, Gentbrugge, \n"
						+ "      Belgium, 2009. Web. 18 Apr. 2012.");
		info.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		info.setForeground(new Color(0.6f, 0.6f, .8f));
		add(info);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension labelSize = this.getPreferredSize();
		setLocation(screenSize.width / 2 - (labelSize.width / 2),
				screenSize.height / 2 - (labelSize.height / 2));

		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				a.getControls().setVisible(true);
				setVisible(false);
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);


		GradientPaint gradientPaint = new GradientPaint(0, 0, new Color(0,
				0.03f, .08f, 0.5f), getWidth()/3, getHeight(), new Color(0, 0.03f, .08f,
				0.9f));
		if (g instanceof Graphics2D) {
			Graphics2D graphics2D = (Graphics2D) g;
			graphics2D.setPaint(gradientPaint);
			graphics2D.fillRect(0, 0, getWidth(), getHeight());
		}
	}
}