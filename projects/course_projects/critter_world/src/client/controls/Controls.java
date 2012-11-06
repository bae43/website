package client.controls;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

import server.world.grid.Critter;
import server.world.grid.Grid;
import server.world.grid.Hex;
import client.App;
import client.Map;

@SuppressWarnings("serial")
public class Controls extends JPanel {

	protected int width = 250;
	protected int speed = 1;
	protected boolean playing = false;

	protected Map m;
	protected App frame;
	protected Grid g;
	protected Scrollbar speedBar;
	protected JLabel speedBarTitle;

	Timer t;
	protected Hex h;
	protected Critter c;
	protected boolean critterOn;

	protected JLabel permTitle;
	protected JLabel crittersTitle;
	protected JLabel plantsTitle;
	protected JLabel stepsTitle;

	protected JPanel boxTop;
	protected JButton autoToggle = new JButton("Play");
	protected JButton step = new JButton("Next Turn");
	protected JButton waitToggle = new JButton("Wait On");

	protected JLabel statTitle;
	protected JLabel typeTitle;
	protected JLabel orientTitle;
	protected JLabel energyTitle;
	protected JLabel offenseTitle;
	protected JLabel defenseTitle;
	protected JLabel appearanceTitle;
	protected JLabel sizeTitle;

	protected JPanel ctrlBox;
	private JButton forward;
	private JButton backward;
	private JButton left;
	private JButton right;
	private JButton eat;
	private JButton attack;
	private JButton grow;
	private JButton bud;
	private JButton mate;
	private JButton wait;

	protected JLabel ruleTitle;
	protected JScrollPane rulePane;
	protected JTextArea ruleArea;

	public static final Color fg = new Color(0.7f, .7f, 0.8f);

	public void critOn() {
		critterOn = true;
	}

	public int width() {
		return width;
	}

	public Controls(App frame) {
		this.frame = frame;
		m = frame.getMap();
		g = frame.getGrid();//XXX

		init();
		updateWorldStats();
		play();
	}

	public void init() {

		setBackground(new Color(0f, .06f, .12f, .6f));
		setAlignmentY(Component.TOP_ALIGNMENT);

		setPreferredSize(new Dimension(width, 0));

		space(20);

		JLabel title = new JLabel("World Data");// , JLabel.CENTER);
		title.setFont(new Font("Baskerville Old Face", Font.BOLD, 22));
		title.setForeground(fg);
		title.setAlignmentX(CENTER_ALIGNMENT);
		add(title);

		permTitle = new JLabel("NO MODE SET");
		permTitle.setFont(new Font("Baskerville Old Face", Font.ITALIC, 18));
		permTitle.setForeground(new Color(0.5f, .5f, 0.6f));
		permTitle.setAlignmentX(CENTER_ALIGNMENT);
		add(permTitle);

		space(10);

		stepsTitle = new JLabel(" Time Step: ");
		stepsTitle.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		stepsTitle.setForeground(fg);
		stepsTitle.setAlignmentX(CENTER_ALIGNMENT);
		add(stepsTitle);

		crittersTitle = new JLabel(" Critters: ");
		crittersTitle.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		crittersTitle.setForeground(fg);
		crittersTitle.setAlignmentX(CENTER_ALIGNMENT);
		add(crittersTitle);

		plantsTitle = new JLabel(" Plants: ");
		plantsTitle.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		plantsTitle.setForeground(fg);
		plantsTitle.setAlignmentX(CENTER_ALIGNMENT);
		add(plantsTitle);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		autoToggle.setIcon(new ImageIcon("images/icons/play.png"));
		autoToggle.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		space(2);
		autoToggle.setAlignmentX(CENTER_ALIGNMENT);
		add(autoToggle);

		boxTop = new JPanel();

		boxTop.setOpaque(false);
		GridLayout gl2 = new GridLayout(1, 2);
		gl2.setHgap(5);
		boxTop.setLayout(gl2);
		boxTop.setMaximumSize(new Dimension(width, 0));

		formatButton(waitToggle);
		boxTop.add(waitToggle);

		formatButton(step);
		boxTop.add(step);

		space(4);

		add(boxTop);

		speedBarTitle = new JLabel("60 Turns Per Minute");
		formatLabel(speedBarTitle, 14);

		speedBar = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 1, 16);

		Dimension barD = new Dimension();
		barD.setSize(150, 0);
		speedBar.setMaximumSize(barD);

		speedBar.setBackground(App.bg);
		speedBar.setLocation(150, 100);

		speedBar.setBlockIncrement(16);

		add(speedBar);

		space(10);

		initButtons();

		/* *******************BEGIN STATS****************** */

		statTitle = new JLabel("Selected: None ");
		formatLabel(statTitle, 18);
		statTitle.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));

		typeTitle = new JLabel(" ");
		formatLabel(typeTitle, 18);
		typeTitle.setFont(new Font("Baskerville Old Face", Font.ITALIC, 18));

		orientTitle = new JLabel();
		formatMemLabel(orientTitle);

		energyTitle = new JLabel();
		formatMemLabel(energyTitle);

		offenseTitle = new JLabel();
		formatMemLabel(offenseTitle);
		;

		defenseTitle = new JLabel();
		formatMemLabel(defenseTitle);

		appearanceTitle = new JLabel();
		formatMemLabel(appearanceTitle);

		sizeTitle = new JLabel();
		formatMemLabel(sizeTitle);

		ctrlBox.setAlignmentX(CENTER_ALIGNMENT);
		add(ctrlBox);
		space(10);

		ruleTitle = new JLabel("Rule Set: \n");
		ruleTitle.setFont(new Font("Baskerville Old Face", Font.ITALIC, 16));
		ruleTitle.setForeground(fg);
		ruleTitle.setAlignmentX(CENTER_ALIGNMENT);
		ruleTitle.setVisible(false);
		add(ruleTitle);

		// In a container that uses a BorderLayout:
		ruleArea = new JTextArea(" ");
		ruleArea.setFont(new Font("Consolas", Font.PLAIN, 14));
		ruleArea.setEditable(false);
		ruleArea.setBackground(App.bg);
		ruleArea.setForeground(App.grey);

		rulePane = new JScrollPane(ruleArea);
		rulePane.setVisible(false);
		rulePane.setMinimumSize(new Dimension(0, 120));
		rulePane.setBackground(new Color(0, .03f, .06f, .3f));

		add(rulePane, CENTER_ALIGNMENT);

		/* /*******************END STATS*********************************** */

		space(frame.getHeight());

		/*
		 * /******************** BEGIN LISTENERS ***********************
		 */

		waitToggle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String text = (String) e.getActionCommand();
				if (text.equals("Wait On")) {
					waitToggle.setText("Wait Off");
					g.WAIT_ONLY_ON = true;
				} else {
					waitToggle.setText("Wait On");
					g.WAIT_ONLY_ON = false;
				}
			}

		});

		autoToggle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String text = (String) e.getActionCommand();
				if (text.equals("Play")) {
					autoToggle.setText("Pause");
					autoToggle.setIcon(new ImageIcon("images/icons/pause.png"));
					playing = true;
					step.setEnabled(false);

					ctrlBox.setVisible(false);
					// rulePane.setVisible(false);
					// ruleTitle.setVisible(false);

				} else {
					autoToggle.setText("Play");
					autoToggle.setIcon(new ImageIcon("images/icons/play.png"));
					playing = false;

					try { // catch when no hex is selected
						if (m.getSelectedHex().hasCritter()) {
							ctrlBox.setVisible(true);
						}
					} catch (Exception ex) {
					}
					step.setEnabled(true);
				}
				m.startStopAuto();

			}

		});

		step.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				updateAll();

			}

		});

		speedBar.addAdjustmentListener(new AdjustmentListener() {
			public void adjustmentValueChanged(AdjustmentEvent ae) {
				t.stop();
				speed = speedBar.getValue();
				play();
				speedBarTitle.setText((60*speed) +" Turns Per Minute");
			}
		});

		forward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.forwardOnNext();
				updateAll();

			}
		});

		backward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.backwardOnNext();
				updateAll();

			}
		});

		left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.leftOnNext();
				updateAll();

			}
		});

		right.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.rightOnNext();
				updateAll();
			}
		});

		eat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.eatOnNext();
				updateAll();

			}
		});

		attack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.attackOnNext();
				updateAll();

			}
		});

		grow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.growOnNext();
				updateAll();

			}
		});

		bud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.budOnNext();
				updateAll();

			}
		});

		mate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.mateOnNext();
				updateAll();

			}
		});

		wait.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.waitOnNext();
				updateAll();
			}
		});
	}

	/* /******************* END LISTENERS ******************************* */

	/** begins animation based on speed bars value */
	public void play() {

		t = new Timer((int) (1000 / speed),

		new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (playing == true) {
					updateAll();
				}
			}
		});

		t.start();

	}

	/** resets all data visible on the map */
	public void updateAll() {
		g.step();
		resetHex();
		updateHexStats();
		updateWorldStats();
		m.repaint();

	}

	/** resets the selected hex after critter moves */
	public void resetHex() {
		// only move hex if critter was initially selected
		if (critterOn) {
			int col = c.getColumn();
			int r = c.getRow();
			h = g.hexAt(col, r);
			m.setSelectedHex(h);
			m.repaint();
		}
	}

	public int getSpeed() {
		return speed;
	}

	public void setCritterSelected(boolean a) {
		critterOn = a;
	}

	public void setHex(Hex h) {
		this.h = h;
		updateHexStats();
	}

	/** updates the selected hex stats shown on the right panel */
	public void updateHexStats() {

		StringBuffer type = new StringBuffer(" ");
		StringBuffer orient = new StringBuffer(" ");
		StringBuffer energy = new StringBuffer(" ");
		StringBuffer offense = new StringBuffer(" ");
		StringBuffer defense = new StringBuffer(" ");
		StringBuffer appearance = new StringBuffer(" ");
		StringBuffer size = new StringBuffer(" ");

		if (h != null) {

			boolean empty = true;

			orientTitle.setText("");
			energyTitle.setText("");
			offenseTitle.setText("");
			defenseTitle.setText("");
			appearanceTitle.setText("");
			sizeTitle.setText("");

			statTitle.setText("Selected: (" + h.getColumn() + "," + h.getRow()
					+ ")");

			ctrlBox.setVisible(false);
			rulePane.setVisible(false);
			ruleTitle.setVisible(false);

			if (!h.isEmpty()) {

				type.append("  Type: ");

				if (h.hasRock()) {
					type = type.append("Rock ");

				} else {
					if (h.hasPlant()) {
						type = type.append("Plant ");
						empty = false;

					}
					if (h.hasCarcass()) {
						if (!empty) {
							type = type.append("/ ");
						}
						type = type.append("Carcass ");
					}
					if (h.hasCritter()) {
						c = h.getCritter();

						if (!empty) {
							type = type.append("/ ");
						}
						type = type.append("Critter ");

						orient = new StringBuffer("Orientation: "
								+ Integer.toString((h.getCritter()
										.getOrientation())));
						energy = new StringBuffer("Energy: "
								+ Integer.toString(h.getCritter().getEnergy()));
						offense = new StringBuffer("Offense: "
								+ Integer.toString(h.getCritter().getOffense()));
						defense = new StringBuffer("Defense: "
								+ Integer.toString(h.getCritter().getDefense()));
						appearance = new StringBuffer("Appearance: "
								+ Integer.toString(h.getCritter()
										.getAppearance()));
						size = new StringBuffer("Size: "
								+ Integer.toString(h.getCritter().getSize()));

						orientTitle.setText(orient.toString());
						energyTitle.setText(energy.toString());
						offenseTitle.setText(offense.toString());
						defenseTitle.setText(defense.toString());
						appearanceTitle.setText(appearance.toString());
						sizeTitle.setText(size.toString());

						rulePane.setVisible(true);
						ruleTitle.setVisible(true);
						try {

							StringBuffer rules = new StringBuffer();
							frame.getMap().getSelectedHex().getCritter()
									.getGenome().prettyPrint(rules);

							Scanner scan = new Scanner(rules.toString());
							StringBuffer rulesDisp = new StringBuffer();
							int cr = frame.getMap().getSelectedHex()
									.getCritter().getRuleNumber();

							for (int i = 0; scan.hasNextLine(); i++) {

								if (i == cr) {
									rulesDisp.append(">> ");
								} else
									rulesDisp.append("   ");
								rulesDisp.append((i + 1) + ". "
										+ scan.nextLine() + "   \n");
							}
							ruleArea.setText(rulesDisp.toString());

						} catch (Exception e) {
							e.printStackTrace();
							ruleArea.setText("Error Loading Rules");

						}

						if (playing) {
							ctrlBox.setVisible(false);
						} else {
							ctrlBox.setVisible(true);
						}

					} else {
						ctrlBox.setVisible(false);
					}
				}
			} else {
				ctrlBox.setVisible(false);
				rulePane.setVisible(false);
				ruleTitle.setVisible(false);
			}

		}

		typeTitle.setText(type.toString());

	}

	/** updates the world stats in the right panel */
	public void updateWorldStats() {

		stepsTitle.setText("     Time Step: " + g.getCurrentTimeStep());
		crittersTitle.setText("     Critters: " + g.crittersOnBoard());
		plantsTitle.setText("     Plants: " + g.plantsOnBoard());

	}

	/** initializes the hidden buttons for critter */
	public void initButtons() {

		Dimension maxDimB = new Dimension(width - 10, 0);

		GridLayout gl = new GridLayout(5, 2);
		gl.setHgap(2);
		gl.setVgap(2);
		ctrlBox = new JPanel(gl);

		ctrlBox.setOpaque(false);
		ctrlBox.setMaximumSize(maxDimB);

		Dimension buttonSize = new Dimension(this.getWidth() / 2, 20);

		forward = new JButton("Forward");
		formatButton(forward);
		forward.setMinimumSize(buttonSize);

		ctrlBox.add(forward);

		backward = new JButton("Backward");
		formatButton(backward);
		backward.setMinimumSize(buttonSize);
		ctrlBox.add(backward);

		left = new JButton("Left");
		formatButton(left);
		left.setMinimumSize(buttonSize);
		ctrlBox.add(left);

		right = new JButton("Right");
		formatButton(right);
		right.setMinimumSize(buttonSize);
		ctrlBox.add(right);

		eat = new JButton("Eat");
		formatButton(eat);
		eat.setMinimumSize(buttonSize);
		ctrlBox.add(eat);

		attack = new JButton("Attack");
		formatButton(attack);
		attack.setMinimumSize(buttonSize);
		ctrlBox.add(attack);

		bud = new JButton("Bud");
		formatButton(bud);
		bud.setMinimumSize(buttonSize);
		ctrlBox.add(bud);

		mate = new JButton("Mate");
		formatButton(mate);
		mate.setMinimumSize(buttonSize);
		ctrlBox.add(mate);

		grow = new JButton("Grow");
		formatButton(grow);
		grow.setMinimumSize(buttonSize);
		ctrlBox.add(grow);

		wait = new JButton("Wait");
		formatButton(wait);
		wait.setMinimumSize(buttonSize);
		ctrlBox.add(wait);

		ctrlBox.setVisible(false);

	}

	public void formatLabel(JLabel label, int size) {
		label.setFont(new Font("Baskerville Old Face", Font.PLAIN, size));
		label.setForeground(new Color(0.7f, .7f, 0.8f));
		label.setAlignmentX(CENTER_ALIGNMENT);
		add(label);
	}

	public void formatMemLabel(JLabel label) {
		label.setFont(new Font("Baskerville Old Face", Font.PLAIN, 16));
		label.setForeground(new Color(0.7f, .7f, 0.8f));
		label.setAlignmentX(RIGHT_ALIGNMENT);
		add(label);
	}

	public void formatButton(JButton button) {

		button.setMinimumSize(new Dimension(120, 30));

	}

	public void space(int s) {
		add(Box.createRigidArea(new Dimension(0, s)));
	}

}
