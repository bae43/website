package server.world.grid;

/** Represents edible plant in world */
public class Plant extends HexCoordinates {	
	
	/** How much energy is obtained by eating a plant */
	public static int ENERGY_PER_PLANT;
	/** How many plants are created from nothing on each simulation time step */
	public static int PLANTS_CREATED_PER_TURN;
	/** The probability of a plant sprouting an adjacent plant on a given simulation time step */
	public static double PLANT_GROW_PROB;
	
	/** True if plant has been eaten during time step */
	private boolean DELETED;
	/** Return whether or not critter is dead */
	public boolean deleted() {
		return DELETED;
	}
	/** Flags plant as deleted */
	public void flagDeleted() {
		DELETED = true;
	}	
	
	/** Energy yieled when consumed by critter */
	private int energy;
	
	/** Constructs plant with Grid g, column c, and row r */
	public Plant(Grid g, int c, int r) {
		super(g, c, r);
		energy = ENERGY_PER_PLANT;
	}
	
	/** Returns energy yielded by plant when consumed */
	public int getFood() {
		return energy;
	}

	/** Attempts to sprout on randomly selected adjacent hex */
	public void sproutAdjacent() {
		if (Math.random() < grid.plantGrowProb())
			sproutOnHex(getAdjacent((int) (Math.random() * 6)));
	}

	/** Sprouts new plant on given hex if empty */
	private void sproutOnHex(Hex h) {
		if (h != null && !h.hasRock() && !h.hasPlant())
			h.addPlant();
	}

}
