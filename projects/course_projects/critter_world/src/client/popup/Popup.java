package client.popup;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.App;
import client.controls.Controls;

@SuppressWarnings("serial")
public class Popup extends JPanel {

	protected JLabel title, body;
	protected Box b, buttBox;
	protected JButton b1, b2;

	Popup() {

		setOpaque(false);

		b = new Box(BoxLayout.Y_AXIS);
		add(b);

		b.add(Box.createRigidArea(new Dimension(0, 5)));

		title = new JLabel();
		title.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		title.setForeground(new Color(0.75f, 0.75f, .9f));
		b.add(title);

		b.add(Box.createRigidArea(new Dimension(0, 5)));

		body = new JLabel();
		body.setFont(new Font("Baskerville Old Face", Font.PLAIN, 16));
		body.setForeground(Controls.fg);
		b.add(body);

		b.add(Box.createRigidArea(new Dimension(0, 10)));

		buttBox = new Box(BoxLayout.X_AXIS);
		buttBox.setAlignmentX(LEFT_ALIGNMENT);
		b.add(buttBox);

		b1 = new JButton();
		buttBox.add(b1);

		buttBox.add(Box.createRigidArea(new Dimension(20, 0)));

		b2 = new JButton();
		buttBox.add(b2);
		
		b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// setBounds(0, 0, a.getWidth(), (int)(a.getHeight()));

		GradientPaint gradientPaint = new GradientPaint(0, 0, new Color(0, 0f,
				.08f, 0.9f), getWidth(), 0, new Color(0, 0.03f, .08f, 0f));
		if (g instanceof Graphics2D) {
			Graphics2D graphics2D = (Graphics2D) g;
			graphics2D.setPaint(gradientPaint);
			graphics2D.fillRect(0, 0, getWidth(), getHeight());
		}
	}
}
