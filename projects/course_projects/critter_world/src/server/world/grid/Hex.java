package server.world.grid;

/** Representation of a hex and everything on it */
public class Hex extends HexCoordinates {
	
	/** Determines if hex has been traversed in BFS for food */
	public boolean TRAVERSED = false;
	/** Distance of shortest path from critter to hex */
	public int DISTANCE = 0;
	/** Direction from critter with shortest distance to hex */
	public int DIRECTION = 0;
	/** Returns if hex can be traversed by food BFS */
	public boolean traversable() {
		return !hasRock() && !hasCritter() && !TRAVERSED;
	}
	
	
	/** Determines if hex contains a rock or not */
	private boolean rock;	
	/** Carcass on hex otherwise null */
	private Carcass carcass;
	/** Plant on hex otherwise null */
	private Plant plant;
	/** Critter on hex otherwise null */
	private Critter critter;
	
	/** Constructs new empty hex with column, row, and grid it exists on */
	public Hex(Grid g, int c, int r) {
		super(g, c, r);
		rock = false;
		carcass = null;
		plant = null;
		critter = null;
	}
	
	/** Adds rock to this hex */
	public void addRock() {
		rock = true;
		grid.removePlantable(this);
	}	
	/** Creates newly birthed critter on this
	 * hex. Adds critter to end of turn queue */
	public void addNewCritter(Critter c) {
		critter = c;
		grid.appendCritter(critter);
		grid.adjustCritters(1);
	}
	/** Adds existing critter to this hex */
	public void addCritter(Critter c) {
		critter = c;
	}
	/** Sprouts new plant on this hex. Adds plant to list of
	 * plants and removes hex from list of plantable hexes */
	public void addPlant() {
		plant = new Plant(grid, column, row);
		grid.appendPlant(plant);
		grid.removePlantable(this);
	}
	
	/** Decomposes dead critter on this hex to carcass.
	 * Sets critter in critter turn queue to null */
	public void deleteCritter() {
		if (hasCarcass())
			carcass.pileCarcass(critter);
		else
			carcass = new Carcass(critter);
		grid.adjustCritters(-1);
		critter = null;
	}
	/** Removes and returns critter from this hex */
	public Critter removeCritter() {
		Critter ret = critter;
		critter = null;
		return ret;
	}
	/** Removes plant and carcass on this hex if any. Removes (flags
	 * for deletion) plant from list of plants and add hex to list of
	 *  plantable hexes. Returns total food value removed from hex */
	public int removeFood() {
		int ret = 0;
		if (hasPlant()) {
			plant.flagDeleted();
			grid.appendPlantable(this);
			ret += plant.getFood();
			plant = null;
		}
		if (hasCarcass()) {
			ret += carcass.getFood();
			carcass = null;			
		}
		return ret;
	}
	
	/** Returns critter on hex if exists otherwise null */
	public Critter getCritter() {
		return critter;
	}
	/** Returns amount of food on this hex */
	public int getFood() {
		return (plant == null ? 0 : plant.getFood()) + 
				(carcass == null ? 0 : carcass.getFood());
	}
	
	/** Returns if hex is empty or not */
	public boolean isEmpty() { 
		return !hasRock() && !hasPlant() && !hasCarcass() && !hasCritter();
	}
	/** Returns if hex contains rock or not */
	public boolean hasRock() { 
		return rock; 
	}
	/** Returns if hex contains plant or not */
	public boolean hasPlant() { 
		return plant != null; 
	}
	/** Returns if hex contains carcass or not */
	public boolean hasCarcass() { 
		return carcass != null; 
	}
	/** Returns if hex contains food or not */
	public boolean hasFood() {
		return hasPlant() || hasCarcass();
	}
	/** Returns if hex has critter or not */
	public boolean hasCritter() { 
		return critter != null; 
	}

}
