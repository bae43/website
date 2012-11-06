package client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import server.world.grid.Grid;
import server.world.grid.Hex;



/** Contains world grid display component of Frame */
@SuppressWarnings("serial")
public class Map extends JPanel {

	private static final int MAX_SIZE = 256;
	private static final int MIN_SIZE = 6;

	private double size = 48.00;
	private double buffer = 5.00;

	private int[] origin = new int[2];
	private int mouseLocX;
	private int mouseLocY;
	private int lastLocX = 0;
	private int lastLocY = 0;
	private int mouseR;
	private int mouseC;

	private int rockBorder = 1;

	private int frame = 0;
	boolean pauseAuto;
	boolean pauseAnim;
	int animSpeed;
	int PlaySpeed;

	private Timer t;
	private Hex selected = null;
	private App f;
	private Images imgs;

	public Map(App m) {
		this.f = m;
		initialize();

		startAnimation();
	}

	/** sets all initializations for the map */
	private void initialize() {
		setBackground(Color.BLACK);
		imgs = App.imgs;
		animSpeed = 1;
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		origin[0] = 100;
		origin[1] = (int) (dim.getHeight() - 100);
		pauseAnim = false;
		getMouseLoc();
		setupZoom();
		setupLook();
		setupHexSelect();

		repaint();
	}

	/** returns the exact size of the major radius of the board hexes */
	public double sizeExact() {
		return size;
	}

	/** returns the exact size of the space between board hexes */
	public double bufferExact() {
		return buffer;
	}

	/** Sets the thickness of rock border */
	public void setBorder(int b) {
		rockBorder = b;
	}

	/** sets the map's origin (lower left corner) */
	public void setOrigin(int[] o) {
		origin = o;
	}

	/** returns the timer used for animating pieces */
	public Timer getTimer() {
		return t;
	}

	/** toggles animation */
	public void startStopAnim() {
		pauseAnim = !pauseAnim;
	}

	/** toggles the auto play */
	public void startStopAuto() {
		pauseAuto = !pauseAuto;
	}

	/** gets the selected hex */
	public Hex getSelectedHex() {
		return selected;
	}

	/** sets the selected hex to the input hex */
	public void setSelectedHex(Hex hex) {
		selected = hex;
	}

	/** returns output for painting all components in the map */
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		// resets side panel bounds
//		f.getControls().setBounds(f.getWidth() - f.getControls().width(), 0,
//				f.getControls().width(), f.getHeight());
//f.menuBar().getHeight() - 5
		setBounds(0,20 , f.getWidth(), f.getHeight());
		drawBorder(g);
		drawBoard(g);
		drawSelected(g);
		drawHexes(g);

	}

	/** draws all the blank hex based on the size and dimensions given */
	public void drawBoard(Graphics g) {

		for (int c = 0; c != Grid.MAX_COLUMN + 1; c++)
			for (int r = (int) Math.ceil((double) c / 2); 2 * r <= c
					+ (2 * Grid.MAX_ROW - Grid.MAX_COLUMN)
					+ (Grid.MAX_COLUMN % 2 == 0 && c % 2 != 0 ? 1 : 0); r++)
				drawBlankHex(g, c, r);
	}

	/** Draws rock border around grid */
	public void drawBorder(Graphics g) {

		Image rock = imgs.getRockImg(frame % imgs.rockFrames(), (int) size);
		int[] bounds = hexToRectCoord(Grid.MAX_COLUMN, Grid.MAX_ROW);

		// bottom
		for (int y = -rockBorder; y < 0; y++) {
			for (int x = -rockBorder; x < Grid.MAX_COLUMN + rockBorder + 1; x++) {
				drawRock(g, x, y, rock);
			}
		}

		// top
		for (int y = bounds[1] + 1; y < bounds[1] + rockBorder + 1; y++) {
			for (int x = -rockBorder; x < bounds[0] + rockBorder + 1; x++) {
				// System.out.println( x + " " + y);
				drawRock(g, x, y, rock);
			}
		}

		// left
		for (int y = -rockBorder; y < bounds[1] + rockBorder; y++) {
			for (int x = -rockBorder; x < 0; x++) {
				drawRock(g, x, y, rock);
			}
		}

		// right
		for (int y = -rockBorder; y < bounds[1] + rockBorder; y++) {
			for (int x = bounds[0] + 1; x < bounds[0] + rockBorder + 1; x++) {
				drawRock(g, x, y, rock);
			}
		}
	}

	/** draws a single rock at coordinates (x,y) on grid */
	private void drawRock(Graphics g, int x, int y, Image rock) {
		double rad = (size) + (buffer);
		double height = (rad * Math.sqrt(3));

		int xPoint = (int) (origin[0] + x * 1.5 * rad);
		int yPoint = (int) (x % 2 == 0 ? (origin[1] - y * height - buffer / 2)
				: (origin[1] - y * height - buffer / 2 - height / 2));

		if (inWindow(xPoint, yPoint)) {
			draw(g, rock, x, y);

		}

	}

	/** Draws all hexes in grid */
	public void drawHexes(Graphics g) {
		for (int c = 0; c != Grid.MAX_COLUMN + 1; c++)
			for (int r = (int) Math.ceil((double) c / 2); 2 * r <= c
					+ (2 * Grid.MAX_ROW - Grid.MAX_COLUMN)
					+ (Grid.MAX_COLUMN % 2 == 0 && c % 2 != 0 ? 1 : 0); r++)
				drawHex(g, f.getGrid().hexAt(c, r));
	}

	/** highlights the selected hex */
	public void drawSelected(Graphics g) {
		if (selected != null) {
			int c = selected.getColumn();
			int r = selected.getRow();

			int[] coords = hexToRectCoord(c, r);
			int x = coords[0];
			int y = coords[1];

			double rad = size + buffer;
			double height = (rad * Math.sqrt(3));
			int xPoint = (int) (origin[0] + x * 1.5 * rad);
			int yPoint = (int) (x % 2 == 0 ? (origin[1] - y * height - buffer / 2)
					: (origin[1] - y * height - buffer / 2 - height / 2));

			// main hex
			g.setColor(new Color(22, 22, 38));
			Polygon hex = hex((int) size, xPoint, yPoint);
			g.fillPolygon(hex);

			// side shading
			if (size > 16) {
				g.setColor(new Color(32, 32, 50));
				g.fillPolygon(hexShade(hex));
			}

			// outline
			g.setColor(new Color(20, 40, 140));
			g.drawPolygon(hex);

		}

	}

	/** draws a blank unselected hex */
	public void drawBlankHex(Graphics g, int c, int r) {

		int[] coords = hexToRectCoord(c, r);
		int x = coords[0];
		int y = coords[1];

		double rad = size + buffer;
		double height = (rad * Math.sqrt(3));
		int xPoint = (int) (origin[0] + x * 1.5 * rad);
		int yPoint = (int) (x % 2 == 0 ? (origin[1] - y * height - buffer / 2)
				: (origin[1] - y * height - buffer / 2 - height / 2));

		if (inWindow(xPoint, yPoint)) {

			// main hex
			g.setColor(new Color(20, 20, 20));
			Polygon hex = hex((int) size, xPoint, yPoint);
			g.fillPolygon(hex);

			// border
			if (size > 16) {
				g.setColor(new Color(40, 40, 40));
				g.fillPolygon(hexShade(hex));
			}
		}
	}

	/** returns true if coordinates xPoint and yPoint from origin are in window */
	public boolean inWindow(int xPoint, int yPoint) {

		return (xPoint + size > 0 && xPoint - size < f.getWidth()
				&& yPoint + size > 0 && yPoint - size < f.getHeight());
	}

	/** begins the animation for all pieces on the map */
	public void startAnimation() {

		t = new Timer(80,

		new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (sizeExact() > 16 && pauseAnim == false) {

					frame++;
					repaint();

				}
			}
		});

		t.start();
	}

	/** returns a hex of radius size centered at x,y */
	public static Polygon hex(int size, int x, int y) {
		Polygon hex = new Polygon();
		for (int i = 0; i < 6; i++) {
			int xPoint = (int) (x + size * Math.cos(i * 2 * Math.PI / 6));
			int yPoint = (int) (y + size * Math.sin(i * 2 * Math.PI / 6));
			hex.addPoint(xPoint, yPoint);
		}

		return hex;
	}

	/**
	 * makes a shadow for the given hex
	 * 
	 * @param hex
	 * @return 8 sided Polygon shadow
	 */
	public static Polygon hexShade(Polygon hex) {

		int[] x = hex.xpoints;
		int[] y = hex.ypoints;

		int[] xShad = new int[8];
		int[] yShad = new int[8];

		// xPer and yPer -> perspective offset of shadow
		int d = x[0] - x[3];
		int xPer = d / 20;
		int yPer = d / 40;

		xShad[0] = x[2];
		xShad[1] = x[1];
		xShad[2] = x[0];
		xShad[3] = x[5];

		xShad[7] = (int) (x[2] + xPer);
		xShad[6] = (int) (x[1] + xPer);
		xShad[5] = (int) (x[0] + xPer);
		xShad[4] = (int) (x[5] + xPer);

		yShad[0] = y[2];
		yShad[1] = y[1];
		yShad[2] = y[0];
		yShad[3] = y[5];

		yShad[7] = (int) (y[2] + yPer);
		yShad[6] = (int) (y[1] + yPer);
		yShad[5] = (int) (y[0] + yPer);
		yShad[4] = (int) (y[5] + yPer);

		Polygon shape = new Polygon();
		for (int i = 0; i < 8; i++) {
			shape.addPoint(xShad[i], yShad[i]);
		}
		return shape;
	}

	/** Draws all components of hex h */
	public void drawHex(Graphics g, Hex h) {

		int c = h.getColumn();
		int r = h.getRow();

		int[] coords = hexToRectCoord(c, r);
		int x = coords[0];
		int y = coords[1];

		double rad = size + buffer;
		double height = (rad * Math.sqrt(3));
		int xPoint = (int) (origin[0] + x * 1.5 * rad);
		int yPoint = (int) (x % 2 == 0 ? (origin[1] - y * height - buffer / 2)
				: (origin[1] - y * height - buffer / 2 - height / 2));

		if (inWindow(xPoint, yPoint)) {

			Image img = null;

			if (!h.isEmpty()) {

				if (h.hasRock()) {
					img = imgs.getRockImg(frame % imgs.rockFrames(),
							(int) (size * .8));
					drawRock(g, x, y, img);
				} else {
					if (h.hasPlant()) {
						img = imgs.getPlantImg(frame % imgs.plantFrames(),
								(int) (size * 2));

						draw(g, img, x, y);
					}
					if (h.hasCarcass()) {
						img = imgs.getCarcassImg(frame % imgs.carcassFrames(),
								(int) (size));
						draw(g, img, x, y);

					}
					if (h.hasCritter()) {

						Image arrow = imgs.getArrow(h.getCritter()
								.getOrientation(), (int) (size));
						draw(g, arrow, x, y);
						img = imgs
								.getCritterImg(
										frame % imgs.critterFrames(),
										(int) (2 * size - size
												* Math.pow(h.getCritter()
														.getSize(), -1)));

						draw(g, img, x, y);
					}
				}
			}

			return;
		}
	}

	/** draws the image img at the point (x,y) on the grid */
	private void draw(Graphics g, Image img, int x, int y) {

		int imgSize = img.getHeight(null);
		double rad = (size + buffer);

		double height = (rad * Math.sqrt(3));
		int xPoint = (int) (origin[0] + x * 1.5 * rad - imgSize / 2 + .5);
		int yPoint = (int) (x % 2 == 0 ? (origin[1] - y * height - buffer / 2
				- imgSize / 2 + .5) : (origin[1] - y * height - buffer / 2
				- height / 2 - imgSize / 2 + .5));

		g.drawImage(img, xPoint, yPoint, null);

	}

	/**
	 * Recenters the map on the selected hex and zooms in to the initial size if
	 * zoomed out more
	 */
	public void recenter() {
		try {

			int r = selected.getRow();
			int c = selected.getColumn();

			if (size < 48 * Math.pow(1.25, 3)) {
				size = 48 * Math.pow(1.25, 3);
				buffer = 5 * Math.pow(1.25, 3);
			}

			int w = f.getControls().isVisible() ? (getWidth() - f.getControls()
					.width()) / 2 : (getWidth()) / 2;
			int h = getHeight() / 2;

			int originX = (int) (1.5 * c * (size + buffer));
			int originY = (int) ((-.5 * c + r) * (size + buffer) * Math.sqrt(3));

			setOrigin(new int[] { w - originX, h + originY });

			repaint();
		} catch (NullPointerException e) {
			System.out.println("No Hex Selected");
		}
	}

	public void zoom(double size, double buffer) {

		this.size = size;
		this.buffer = buffer;

		int dimsX = (int) (Grid.MAX_COLUMN * (size + buffer) * 1.5);
		int dimsY = (int) (Grid.MAX_ROW * (size + buffer) * Math.sqrt(3) / 2);

		int w;
		if (f.getControls().isVisible()) {
			w = f.getWidth() - f.getControls().width();
		} else {
			w = f.getWidth();
		}
		setOrigin(new int[] { (int) ((w / 2 - dimsX / 2)),
				(int) ((f.getHeight() / 2 + dimsY / 2)) });

		repaint();
	}

	/**
	 * Takes in coordinates in (r,c) and translates them to (x,y)
	 * 
	 * ex: (7,5) -> (5,4) ; (4,2) -> (2,3) ; (1,0) -> (0,1)
	 * 
	 * @param r
	 * @param c
	 * @return (x,y) coords array
	 */
	public static int[] hexToRectCoord(int c, int r) {

		int x = c;
		int y = (int) (r - .5 * c);

		return new int[] { x, y };
	}

	/**
	 * returns distance from mouse to the center of Hex h returns MAX_VALUE if
	 * invalid hex
	 * 
	 * @param h
	 * @return distance from mouse to center of hex
	 */
	public int distance(Hex h) {

		try {
			int r = h.getRow();
			int c = h.getColumn();

			int[] center = hexToRectCoord(c, r);

			int x = center[0];
			int y = center[1];

			double rad = size + buffer;
			double height = (rad * Math.sqrt(3));
			int xPoint = (int) (origin[0] + x * 1.5 * rad);
			int yPoint = (int) (x % 2 == 0 ? (origin[1] - y * height - buffer / 2)
					: (origin[1] - y * height - buffer / 2 - height / 2));

			return (int) Math.sqrt(Math.pow(xPoint - mouseLocX, 2)
					+ Math.pow(yPoint - mouseLocY, 2));
		} catch (Exception e) {
			return Integer.MAX_VALUE;
		}

	}

	/* /******************* Begin Listeners ******* */

	/** sets mouseR and mouseC to within (+/-) 1 hex */
	private void getMouseLoc() {
		addMouseMotionListener(new MouseMotionListener() {
			public void mouseMoved(MouseEvent e) {
				mouseLocX = e.getX();
				mouseLocY = e.getY();

				double rad = (size + buffer);
				double d = (Math.sqrt(3) * rad);

				mouseC = (int) ((mouseLocX) / (1.5 * rad) - origin[0]
						/ (1.5 * rad) + .5);
				mouseR = (int) (-(mouseLocY) / d + .5
						* ((mouseLocX) / (1.5 * rad) - origin[0] / (1.5 * rad))
						+ origin[1] / d + .5);
			}

			@Override
			public void mouseDragged(MouseEvent arg0) {
				// no event
			}
		});
	}

	/** sets up the zoom with the mouse wheel */
	private void setupZoom() {
		addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {

				double zoom = 1.25;
				double zoomInv = 1 / zoom;

				// zoom in
				if (e.getWheelRotation() < 0 && size * zoom < MAX_SIZE) {
					size = size * zoom;
					buffer = buffer * zoom;
					origin[0] = (int) (mouseLocX - zoom
							* (mouseLocX - origin[0]));
					origin[1] = (int) (mouseLocY - zoom
							* (mouseLocY - origin[1]));

					// zoom out
				} else if (e.getWheelRotation() > 0
						&& size * zoomInv > MIN_SIZE) {
					size = size * zoomInv;
					buffer = buffer * zoomInv;
					origin[0] = (int) (mouseLocX - zoomInv
							* (mouseLocX - origin[0]));
					origin[1] = (int) (mouseLocY - zoomInv
							* (mouseLocY - origin[1]));
				}
				repaint();

			}
		});
	}

	/**
	 * selects the closest hex in the grid from the mouse position when the LMB
	 * is clicked
	 */
	private void setupHexSelect() {
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {

				// if RMB is pressed and mouse is over map, set hex to selected
				if (e.getModifiersEx() == 1024
						&& (f.getControls().isVisible() ? e.getX()
								+ f.getControls().width() < f.getWidth() : e
								.getX() < f.getWidth())) {

					int min = Integer.MAX_VALUE;

					// mouseC and mouseR are only accurate to (+/-) 1 hexes
					// --> Runs in CONSTANT TIME [O(1)] without searching
					// through all tiles
					for (int i = mouseC - 1; i <= mouseC + 1; i++) {

						for (int j = mouseR - 1; j <= mouseR + 1; j++) {

							Hex h = f.getGrid().hexAt(i, j);
							int dis = distance(h);

							if (dis < min) {

								min = dis;
								selected = h;

							}

						}

					}

					f.getControls().setHex(selected);

					// keeps track if initial click was on critter
					// allows for hex to have critter, but critterOn
					// = false

					if (selected.hasCritter()) {
						f.getControls().setCritterSelected(true);
					} else
						f.getControls().setCritterSelected(false);

					repaint();
				}
			}
		});
	}

	/** sets up functions to pan around the map */
	private void setupLook() {
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {

				lastLocX = e.getX();
				lastLocY = e.getY();
			}
		});

		/** pans the map when the RMB is held and mouse is dragged */
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {

				if (e.getModifiersEx() == MouseEvent.BUTTON3_DOWN_MASK) {

					int x = e.getX();
					int y = e.getY();
					int moveX = x - lastLocX;
					int moveY = y - lastLocY;
					lastLocX = x;
					lastLocY = y;
					origin[0] = origin[0] + moveX;
					origin[1] = origin[1] + moveY;
					repaint();

				}
			}

		});

	}

}
