package client;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Images {

	App a;
	
	Image[] critterImgs;
	Image[] carcassImgs;
	Image[] plantImgs;
	Image[] rockImgs;
	Image[] arrow;

	private int critterFrames = 31;
	private int carcassFrames = 8;
	private int plantFrames = 32;
	private int rockFrames = 23;

	
//	private int critterFrames = 1;
//	private int carcassFrames = 1;
//	private int plantFrames = 1;
//	private int rockFrames = 1;

	public Images(App a) {
		this.a = a;
		System.out.println("Loading Images...");
		critterImgs = loadImgs("critter", critterFrames);
		carcassImgs = loadImgs("carcass", carcassFrames);
		plantImgs = loadImgs("plant", plantFrames);
		rockImgs = loadImgs("rock", rockFrames);
		arrow = loadImgs("arrow", 6);
		System.out.println("Images Done Loading");
	}

	/** loads all images into arrays needed for animation */
	public Image[] loadImgs(String type, int count) {
		Image[] imgs = new Image[count];
		BufferedImage img = null;

		for (int i = 0; i < count; i++) {
			a.splash.repaint();
			try {
				String filename = "images\\" + type + "\\" + type + i + ".png";
				img = ImageIO.read(new File(filename));
				imgs[i] = img;
			} catch (IOException e) {
				System.out.println("Error loading " + type + i + " image");
			}
		}

		return imgs;
	}

	/** returns a scaled image given an input image and output size */
	public static BufferedImage resize(BufferedImage img, int size) {
		int w = img.getWidth();
		int h = img.getHeight();

		BufferedImage dimg = new BufferedImage(size, size, img.getType());
		Graphics2D g = dimg.createGraphics();

		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(img, 0, 0, size, size, 0, 0, w, h, null);
		g.dispose();

		return dimg;
	}

	/** returns frame of the critter animation */
	public Image getCritterImg(int i, int size) {

		return resize((BufferedImage) critterImgs[i], size);

	}

	/** returns frame of the carcass animation */
	public Image getCarcassImg(int i, int size) {
		return resize((BufferedImage) carcassImgs[i], size);
	}

	/** returns frame of the plant animation */
	public Image getPlantImg(int i, int size) {
		return resize((BufferedImage) plantImgs[i], size);
	}

	/** returns a rock image */
	public Image getRockImg(int i, int size) {
		return resize((BufferedImage) rockImgs[i], 2 * size);
	}

	/** returns a rotated arrow and size */
	public Image getArrow(int i, int size) {
		return resize((BufferedImage) arrow[i], 2 * size);
	}

	public int critterFrames() {
		return critterFrames;
	}

	public int carcassFrames() {
		return carcassFrames;
	}

	public int plantFrames() {
		return plantFrames;
	}

	public int rockFrames() {
		return rockFrames;
	}

}
