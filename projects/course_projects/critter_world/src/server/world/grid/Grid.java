package server.world.grid;

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


/** Represents critter world grid */
public class Grid {	
	
	/** The maximum column index in the world map */
	public static int MAX_COLUMN;
	/**  The maximum row index in the world map */
	public static int MAX_ROW;
	/** Number of hexes in grid */
	private static int NUM_HEXES;
	
	/** Assigns from file all constants for world */
	private void initializeConstants(BufferedReader f) {

		Scanner sc = new Scanner(f);
		
		while (sc.hasNext()) {
			String c = sc.next();				
			if (c.equals("BASE_DAMAGE"))
				Critter.BASE_DAMAGE = sc.nextInt();
			else if (c.equals("DAMAGE_INC"))
				Critter.DAMAGE_INC = sc.nextDouble();
			else if (c.equals("ENERGY_PER_PLANT"))
				Plant.ENERGY_PER_PLANT = sc.nextInt();
			else if(c.equals("ENERGY_PER_SIZE"))
				Critter.ENERGY_PER_SIZE = sc.nextInt();
			else if (c.equals("FOOD_PER_SIZE"))
				Carcass.FOOD_PER_SIZE = sc.nextInt();
			else if (c.equals("MAX_SMELL_DISTANCE"))
				Critter.MAX_SMELL_DISTANCE = sc.nextInt();
			else if (c.equals("ROCK_VALUE"))
				Critter.ROCK_VALUE = sc.nextInt();
			else if (c.equals("MAX_COLUMN"))
				MAX_COLUMN = sc.nextInt();
			else if (c.equals("MAX_ROW"))
				MAX_ROW = sc.nextInt();
			else if (c.equals("MAX_RULES_PER_TURN"))
				Critter.MAX_RULES_PER_TURN = sc.nextInt();
			else if (c.equals("PLANTS_CREATED_PER_TURN"))
				Plant.PLANTS_CREATED_PER_TURN = sc.nextInt();
			else if (c.equals("PLANT_GROW_PROB"))
				Plant.PLANT_GROW_PROB = sc.nextDouble();
			else if (c.equals("MOVE_COST"))
				Critter.MOVE_COST = sc.nextInt();
			else if (c.equals("ATTACK_COST"))
				Critter.ATTACK_COST = sc.nextInt();
			else if (c.equals("GROW_COST"))
				Critter.GROW_COST = sc.nextInt();
			else if (c.equals("BUD_COST"))
				Critter.BUD_COST = sc.nextInt();
			else if (c.equals("MATE_COST"))
				Critter.MATE_COST = sc.nextInt();
			else if (c.equals("RULE_COST"))
				Critter.RULE_COST = sc.nextInt();
			else if (c.equals("ABILITY_COST"))
				Critter.ABILITY_COST = sc.nextInt();
			else if (c.equals("INITIAL_ENERGY"))
				Critter.INITIAL_ENERGY = sc.nextInt();
			else
				System.err.println(c + " unrecognized");
			sc.nextLine();
		}		
		NUM_HEXES = (MAX_COLUMN + 1) * (MAX_ROW + 1 - (MAX_COLUMN + 1)/2);
	}
	
	/** If true causes all critters to only wait each turn */
	public boolean WAIT_ONLY_ON;
		
	/** All hexes that can sprout new plant from nothing */
	private ArrayList<Hex> plantable;
	/** All plants in world */
	private ArrayList<Plant> plants;
	/** All critters in world */
	private ArrayList<Critter> critters;	
	/** Number of critters in world */
	private int numCritters;	

	/** All hexes in grid accessible by indexing with column c and row r */
	private Hex[][] hexes;	
	/** Current TimeStep world is on */
	private int currentTimeStep;
	
	/** Constructs new grid from constants with contents from file */
	public Grid(BufferedReader bufferedReader, BufferedReader c) {
		initialize(c);
		generateFromFile(bufferedReader);
	}
	/** Constructs new grid from constants with random contents */
	public Grid(BufferedReader bufferedReader) {
		initialize(bufferedReader);
		generateRandom();
	}
	
	/** Initializes constants and fields of grid */ //XXX
	private void initialize(BufferedReader bufferedReader) {
		initializeConstants(bufferedReader);
		hexes = new Hex[MAX_COLUMN + 1][MAX_ROW + 1];
		currentTimeStep = 0;
		numCritters = 0;
		critters = new ArrayList<Critter>(NUM_HEXES);
		plants = new ArrayList<Plant>(NUM_HEXES);
		plantable = new ArrayList<Hex>(NUM_HEXES);		
	}
	
	/** Generates contents of world from definitions in file f */
	private void generateFromFile(BufferedReader bufferedReader) {
		for (int c = 0; c != MAX_COLUMN + 1; c++)
			for (int r = (int) Math.ceil((double) c / 2); 2*r <= c + (2*MAX_ROW - 
					MAX_COLUMN) + (MAX_COLUMN % 2 == 0 && c % 2 != 0 ? 1 : 0); r++) {
				hexes[c][r] = new Hex(this, c, r);
				plantable.add(hexes[c][r]);
			}
		Scanner sc = null;
		sc = new Scanner(bufferedReader);
		while (sc.hasNext()) {
			String type = sc.next();
			String fn = null;
			int dir = 0;
			boolean critter = type.equals("critter");
			if (critter)
				fn = sc.next();
			int row = sc.nextInt();
			int col = sc.nextInt();
			if (critter)
				dir = sc.nextInt();
			Hex h = hexAt(col, row);
			if (h == null)
				System.err.printf("%s at c: %s r: %s failed - out of bounds\n", type, col, row);
			else
				if (type.equals("rock")) {
					if (h.hasRock() || h.hasCritter())
						System.err.printf("rock at c: %s r: %s failed - not empty\n", col, row);
					else
						h.addRock();
				} else if (type.equals("plant")) {
					if (h.hasRock() || h.hasPlant())
						System.err.printf("plant at c: %s r: %s failed - not plantable\n", col, row);
					else
						h.addPlant();
				} else if (type.equals("critter")) {
					if (h.hasRock() || h.hasCritter())
						System.err.printf("critter at c: %s r: %s failed - not empty\n", col, row);
					else
						h.addNewCritter(new Critter(this, col, row, dir, new File(fn)));
				} else
					System.err.println(type + " unrecognized");
		}
	}
	/** Generates contents of world randomly */
	public void generateRandom() {
		double rockProb = Math.random() / 10;
		double plantProb = Math.random() / 5;
		for (int c = 0; c != MAX_COLUMN + 1; c++)
			for (int r = (int) Math.ceil((double) c / 2); 2*r <= c + (2*MAX_ROW - 
					MAX_COLUMN) + (MAX_COLUMN % 2 == 0 && c % 2 != 0 ? 1 : 0); r++) {
				hexes[c][r] = new Hex(this, c, r);
				double roll = Math.random();
				if (roll < rockProb)
					hexes[c][r].addRock();
				else if (roll > 1.0 - plantProb)
					hexes[c][r].addPlant();
				else
					plantable.add(hexes[c][r]);
			}
	}
	
	/** Progresses world forward by one time step */
	public void step() {
		currentTimeStep++;		
		int[] turnOrder = randomPermutation(new int[] {critters.size(), plants.size(), Plant.PLANTS_CREATED_PER_TURN});		
		int critterIndex = 0;
		int plantIndex = 0;		
		for (int i = 0;  i != turnOrder.length; i++)
			switch (turnOrder[i]) {
			case 0:
				if (critterExists(critterIndex))
					critters.get(critterIndex).takeTurn();
				critterIndex++;
				break;
			case 1:
				if (plantExists(plantIndex))
					plants.get(plantIndex).sproutAdjacent();
				plantIndex++;
				break;
			case 2:
				if (plantable.size() != 0)
					plantable.get((int) (Math.random() * plantable.size())).addPlant();
				break;
			}
		while (critterIndex != critters.size()) {
			if (critterExists(critterIndex))
				critters.get(critterIndex).takeTurn();
			critterIndex++;
		}
		clearDeadCritters();
		clearEatenPlants();
	}
	
	/** Returns a random permutation of a set represented by array a. The
	 * array {1, 2, 3} would be equivalent to the set [0, 1, 1, 2, 2, 2]*/
	private int[] randomPermutation(int[] a) {
		int size = 0;
		for (int i = 0; i != a.length; i++)
			size += a[i];
		int[] ret = new int[size];
		int retIndex = 0;
		for (int i = 0; i != a.length; i++)
			for (int j = 0; j != a[i]; j++)
				ret[retIndex++] = i;		
		for (int i = 0; i != ret.length; i++) {
			int r = (int) (Math.random() * ret.length);
			int temp = ret[i];
			ret[i] = ret[r];
			ret[r] = temp;
		}		
		return ret;
	}
	
	/** Returns whether or not critter included in line for
	 * action for time step still exists or has been killed */
	private boolean critterExists(int i) {
		return !critters.get(i).deleted();
	}
	/** Returns whether or not plant included in line for
	 * action for time step still exists or has been eaten */
	private boolean plantExists(int i) {
		return !plants.get(i).deleted();
	}
	/** Removes all dead critters from list of critters */
	private void clearDeadCritters() {
		Iterator<Critter> critterIter = critters.iterator();
		while (critterIter.hasNext())
			if (critterIter.next().deleted())
				critterIter.remove();		
	}
	/** Removes all eaten plants from list of plants */
	private void clearEatenPlants() {
		Iterator<Plant> plantIter = plants.iterator();
		while (plantIter.hasNext())
			if (plantIter.next().deleted())
				plantIter.remove();		
	}
	
	/** Returns plant growth probablity adjusted for number of critters */
	public double plantGrowProb() {
		return 1 - Math.pow(1 - Plant.PLANT_GROW_PROB, 1.0 / numCritters);
	}

	/** Adds n to number of critters */
	public void adjustCritters(int n) {
		numCritters += n;
	}	
	/** Returns number of critters in world */
	public int crittersOnBoard() {
		return numCritters;
	}
	/** Returns number of plants in world */
	public int plantsOnBoard() {
		return plants.size();
	}
	
	/** Appends critter to array of all critters */
	public void appendCritter(Critter c) {
		critters.add(c);
	}
	/** Appends plant to array of all plants */
	public void appendPlant(Plant p) {
		plants.add(p);
	}
	/** Appends plantable hex to array of all plantables */
	public void appendPlantable(Hex h) {
		plantable.add(h);
	}
	/** Deletes plantable hex from array of all plantables */
	public void removePlantable(Hex h) {
		plantable.remove(h);
	}
	
	/** Returns current time step world grid is on */
	public int getCurrentTimeStep() {
		return currentTimeStep;
	}
	/** Returns hex in grid at column c and row r otherwise
	 * returns null if c, r is off edge of grid */
	public Hex hexAt(int c, int r) {
		try {
			return hexes[c][r];
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}		
	}

}
