package server.world.grid;

/** Represents one or more edible bodies of dead critters  */
public class Carcass {
	
	/** How much food is created per point of size when a critter dies */
	public static int FOOD_PER_SIZE;	
	/** Energy yieled when consumed by critter */
	private int energy;
	/** Number of carcasses in this carcass pile */
	private int numCarcass;
	
	/** Constructs carcass energy value FOOD_PER_SIZE * size s */
	public Carcass(Critter c) {
		pileCarcass(c);
	}	
	/** Adds additional food value from another carcass to 
	 * this carcass to represent a pile of carcasses */
	public void pileCarcass(Critter c) {
		energy += FOOD_PER_SIZE * c.getSize();
		numCarcass++;
	}
	/** Returns energy yielded by carcass when consumed */
	public int getFood() {
		return energy;
	}	
	/** Returns number of carcasses in this carcass pile */
	public int getNumCarcass() {
		return numCarcass;
	}

}
