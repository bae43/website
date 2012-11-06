package client;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Splash extends JPanel {

	int comps = 1; // runs from 1 to 105
	Image img;
	Polygon bar;

	// int[] barCenter;
	// int barWidth;

	public Splash() {
		setBackground(Color.black);
		try {
			img = ImageIO.read(new File("images/screens/splash.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// barCenter = new int[] { 400, 200 };

		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(new Color(0, .4f, .4f, .4f));

		if (comps < 105) {
			comps++;
		}

		g.drawImage(img, 120, 100, null);

		Graphics2D g2 = (Graphics2D) g;

		g.setColor(new Color(.15f, .15f, .2f));

		// 104 compostions total
		RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(70, 450,
				105 * 6, 20, 10, 10);
		g2.fill(roundedRectangle);

		g.setColor(new Color(0, .4f, .6f, .4f));

		roundedRectangle = new RoundRectangle2D.Float(70, 450, comps * 6, 20,
				10, 10);
		g2.fill(roundedRectangle);

	}

	/** increments the number of components loaded and sets loading bar */
	public void addComp() {
		comps = comps++;
		Polygon bar = new Polygon();

		bar.addPoint(0, 0);
		bar.addPoint(0, 20);
		bar.addPoint(comps * 5, 20);
		bar.addPoint(comps * 5, 0);

	}
}
