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
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.App;

@SuppressWarnings("serial")
public class Info extends JPanel {

	App a;

	public Info(App a) {
		this.a = a;
		run();
	}

	public void run() {
		setOpaque(false);
		setVisible(false);
		setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());

		setAlignmentX(Component.CENTER_ALIGNMENT);

		add(Box.createRigidArea(new Dimension(0, a.getHeight() / 4)));

		setLayout

		(new BoxLayout(this, BoxLayout.Y_AXIS));

		JLabel title = new JLabel("Instructions: ");
		title.setFont(new Font("Baskerville Old Face", Font.ITALIC, 22));
		title.setForeground(new Color(0.8f, 0.8f, .8f));
		add(title);

		add(Box.createRigidArea(new Dimension(0, 20)));

		JLabel info;
		info = new JLabel("  Right click a critter to view it's stats\n");
		info.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		info.setForeground(new Color(0.6f, 0.6f, .8f));
		info.setAlignmentX(LEFT_ALIGNMENT);
		add(info);
		
		JLabel info2;
		info2 = new JLabel("  Left click and drag to pan");
		info2.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		info2.setForeground(new Color(0.6f, 0.6f, .8f));
		info2.setAlignmentX(LEFT_ALIGNMENT);
		add(info2);
		
		JLabel info3;
		info3 = new JLabel("  Scroll to zoom in or out");
		info3.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		info3.setForeground(new Color(0.6f, 0.6f, .8f));
		info3.setAlignmentX(LEFT_ALIGNMENT);
		add(info3);

//		info.setText("  Left click and drag to pan");
//		add(info);
//
//		info.setText("  Scroll to zoom in or out");
//		add(info);

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

		// setBounds(0, 0, a.getWidth(), (int)(a.getHeight()));

		GradientPaint gradientPaint = new GradientPaint(0, 0, new Color(0,
				0.03f, .08f, 0.5f), getWidth() / 3, getHeight(), new Color(0,
				0.03f, .08f, 0.9f));
		if (g instanceof Graphics2D) {
			Graphics2D graphics2D = (Graphics2D) g;
			graphics2D.setPaint(gradientPaint);
			graphics2D.fillRect(0, 0, getWidth(), getHeight());
		}
	}
}