package client.pane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import client.App;

@SuppressWarnings("serial")
public class ConstantsViewer extends JPanel{


	App a;

	public ConstantsViewer(App a) {
		this.a = a;
		run();

	}

	public void run() {
		setVisible(true);
		//setOpaque(false);
		setBackground(new Color(0f, .06f, .12f,.1f));
		setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());


		setAlignmentY(Component.TOP_ALIGNMENT);

		add(Box.createRigidArea(new Dimension(0, a.getHeight() / 4)));

		JLabel title = new JLabel("EDIT CONSTANTS:\n");
		title.setFont(new Font("Baskerville Old Face", Font.BOLD, 22));
		title.setForeground(new Color(0.8f, 0.8f, .8f));
		add(title);
		
		add(Box.createRigidArea(new Dimension(0, 20)));

		JTextArea textArea;
		textArea = new JTextArea("test");
		textArea.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		textArea.setOpaque(false);
		//info.setAlignmentX(RIGHT_ALIGNMENT);
		add(textArea);
		
		add(Box.createRigidArea(new Dimension(0, 15)));


		textArea.setAlignmentX(RIGHT_ALIGNMENT);
		add(textArea);

//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		Dimension labelSize = this.getPreferredSize();
//		setLocation(screenSize.width / 2 - (labelSize.width / 2),
//				screenSize.height / 2 - (labelSize.height / 2));

	}

	
}
