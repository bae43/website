package server.world.grid;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import server.world.genome.ast.Program;
import server.world.genome.ast.Rule;
import server.world.genome.ast.Update;
import server.world.genome.parser.GenomeParser;

/** Represents a live critter in world */
public class Critter extends HexCoordinates {
	
	/**The multiplier for all damage done by attacking**/
	public static int BASE_DAMAGE;
	/** Controls how quickly increased offensive or defensive ability affects damage */
	public static double DAMAGE_INC;
	/** How much energy a critter can have per point of size */
	public static int ENERGY_PER_SIZE;
	/** The maximum distance at which food can be sensed */
	public static int MAX_SMELL_DISTANCE;
	/** The value reported when a rock is sensed */
	public static int ROCK_VALUE;
	/** The maximum number of rules that can be run per critter turn */
	public static int MAX_RULES_PER_TURN;
	/** Energy cost of moving (per unit size) */
	public static int MOVE_COST;
	/** Energy cost of attacking (per unit size) */
	public static int ATTACK_COST;
	/** Energy cost of growing (per size and complexity) */
	public static int GROW_COST;
	/** Energy cost of budding (per unit complexity) */
	public static int BUD_COST;
	/** Energy cost of successful mating (per unit complexity) */
	public static int MATE_COST;
	/** Complexity cost of having a rule */
	public static int RULE_COST;
	/** Complexity cost of having an ability point */
	public static int ABILITY_COST;
	/** Energy of a newly birthed critter */
	public static int INITIAL_ENERGY;
	
	/** Index of memory length in array mem */
	public static final int LEN_INDEX = 0;
	/** Index of defense in array mem */
	public static final int DEF_INDEX = 1;
	/** Index of offense in array mem */
	public static final int OFF_INDEX = 2;
	/** Index of size in array mem */
	public static final int SIZE_INDEX = 3;
	/** Index of energy in array mem */
	public static final int ENRG_INDEX = 4;
	/** Index of apearance in array mem */
	public static final int APP_INDEX = 5;
	/** Index of rule counter in array mem */
	public static final int RC_INDEX = 6;	
	
	/** Action defined by user to take. Includes
	 * wait, foward, backward, left, right, eat,
	 * attack, grow, bud, and mate */
	private Action DEFINED_ACTION;	
	/** Sets critter to wait on next turn */
	public void waitOnNext() {
		DEFINED_ACTION = Action.WAIT;
	}
	/** Sets critter to move forward on next turn */
	public void forwardOnNext() {
		DEFINED_ACTION = Action.FORWARD;
	}
	/** Sets critter to move backward on next turn */
	public void backwardOnNext() {
		DEFINED_ACTION = Action.BACKWARD;
	}
	/** Sets critter to turn left on next turn */
	public void leftOnNext() {
		DEFINED_ACTION = Action.LEFT;
	}
	/** Sets critter to turn right on next turn */
	public void rightOnNext() {
		DEFINED_ACTION = Action.RIGHT;
	}	
	/** Sets critter to eat on next turn */
	public void eatOnNext() {
		DEFINED_ACTION = Action.EAT;
	}	
	/** Sets critter to attack on next turn */
	public void attackOnNext() {
		DEFINED_ACTION = Action.ATTACK;
	}	
	/** Sets critter to grow on next turn */
	public void growOnNext() {
		DEFINED_ACTION = Action.GROW;
	}
	/** Sets critter to bud on next turn */
	public void budOnNext() {
		DEFINED_ACTION = Action.BUD;
	}
	/** Sets critter to mate on next turn */
	public void mateOnNext() {
		DEFINED_ACTION = Action.MATE;
	}
	
	/** True if critter is dead */
	private boolean DELETED;
	/** Return whether or not critter is dead */
	public boolean deleted() {
		return DELETED;
	}
	
	/** Constructs critter with Grid g, column c, row r and 
	 * orientation o from file f containing mem and genome */
	public Critter(Grid g, int c, int r, int o, File f) {
		super(g, c, r);
		orientation = o;
		initializeTags();
		readFile(f);	
	}	
	/** Reads memory and genome from file and assigns it to
	 * critter. Ignores blank lines and commented out lines */
	private void readFile(File f) {
		Scanner sc = null;
		try {
			sc = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int a0 = 0, a1 = 0, a2 = 0, a3 = 0, a4 = 0, a5 = 0;
		for (int i = 0; i != 6; i++) {
			String line = sc.nextLine();
			if (line.equals(""))
				i--;
			else {
				Scanner sc2 = new Scanner(line);
				String m = sc2.next();
				if (m.equals("memsize:"))
					a0 = sc2.nextInt();
				else if (m.equals("defense:"))
					a1 = sc2.nextInt();
				else if (m.equals("offense:"))
					a2 = sc2.nextInt();
				else if (m.equals("size:"))
					a3 = sc2.nextInt();
				else if (m.equals("energy:"))
					a4 = sc2.nextInt();
				else if (m.equals("appearance:"))
					a5 = sc2.nextInt();
				else
					i--;
			}
		}
		mem = new int[a0];
		mem[0] = a0; mem[1] = a1; mem[2] = a2; mem[3] = a3; mem[4] = a4; mem[5] = a5;
		StringBuilder prog = new StringBuilder();
		while (sc.hasNext()) {
			String l = sc.nextLine();
			if (!l.equals("") && !(l.charAt(0) == '/' && l.charAt(1) == '/'))
				prog.append(l);
		}
		genome = (Program) new GenomeParser().parse(new StringReader(prog.toString()));		
	}
	
	/** Initializes counters and tags in critter */
	public void initializeTags() {
		damageDealt = 0;
		mating = false;
		ruleNumber = -1;
	}
	
	/** Rule number last executed. Negative
	 * one if no rule in program was executed */
	private int ruleNumber;
	
	/** Direction on grid critter is facing */
	private int orientation;
	/** True if critter is attempting to mate */
	private boolean mating;
	/** Damage dealt by critter in previous time step */
	private int damageDealt;
	/** Memory of critter */
	private int[] mem;
	/** Genome of critter */
	private Program genome;
	
	/** Constructs critter with Grid g, column c, and row r */
	public Critter(Grid g, int c, int r, int o, int[] m, Program ge) {
		super(g, c, r);
		orientation = o;
		initializeTags();
		mem = m;
		genome = ge;
	}
	
	/** Returns orientation of critter */
	public int getOrientation() {
		return orientation;
	}
	/** Returns genome of critter */
	public Program getGenome() {
		return genome;
	}
	/** Sets memory stored at i to n. Sets closest
	 * memory if i indexes to a memory out of bounds */
	public void setMemAt(int i, int n) {
		try {
			mem[i] = n;
		} catch (ArrayIndexOutOfBoundsException e) {
			mem[i < 0 ? 0 : getMemLength() - 1] = n;
		}
	}
	/** Returns memory stored at index i. Returns closest
	 * memory if i indexes to a memory out of bounds */
	public int getMemAt(int i) {
		try {
			return mem[i];
		} catch (ArrayIndexOutOfBoundsException e) {
			return mem[i < 0 ? 0 : getMemLength() - 1];
		}
	}
	/** Returns length of critter memory */
	public int getMemLength() {
		return mem[LEN_INDEX];
	}
	/** Returns defensive ability of critter */
	public int getDefense() {
		return mem[DEF_INDEX];
	}
	/** Returns offensive ability of critter */
	public int getOffense() {
		return mem[OFF_INDEX];
	}
	/** Returns size of critter */
	public int getSize() {
		return mem[SIZE_INDEX];
	}	
	/** Returns energy of critter */
	public int getEnergy() {
		return mem[ENRG_INDEX];
	}
	/** Returns appearance of critter */
	public int getAppearance() {
		return mem[APP_INDEX];
	}
	/** Returns rule counter of critter */
	public int getRuleCounter() {
		return mem[RC_INDEX];
	}
	/** Returns whether or not critter 
	 * attempted to mate in current timestep */
	public boolean getMating() {
		return mating;
	}
	/** Returns rule number last executed. Negative one
	 * if none of the rules were executed by critter */
	public int getRuleNumber() {
		return ruleNumber;
	}

	/** Calculates and returns complexity of critter */
	public int complexity() {
		return genome.rulesSize() * RULE_COST + (getOffense() + getDefense()) * ABILITY_COST;
	}	

	/** Decreases energy of critter by n */
	public void decreaseEnergy(int n) {
		mem[ENRG_INDEX] -= n;
		checkIfDead();
	}

	/** Turn taken by critter in each time step */
	public void takeTurn() {
		damageDealt = 0;
		mating = false;
		if (DEFINED_ACTION != null)
			performAction(DEFINED_ACTION);
		else
			if (grid.WAIT_ONLY_ON)
				doNothing();
			else
				nextCommand();
		DEFINED_ACTION = null;
	}	
	/** Performs action denoted by String a */
	private void performAction(Action a) {		
		if (a.equals(Action.FORWARD))
			forward();
		else if (a.equals(Action.BACKWARD))
			backward();
		else if (a.equals(Action.LEFT))
			left();
		else if (a.equals(Action.RIGHT))
			right();
		else if (a.equals(Action.EAT))
			eat();
		else if (a.equals(Action.ATTACK))
			attack();
		else if (a.equals(Action.GROW))
			grow();
		else if (a.equals(Action.BUD))
			bud();
		else if (a.equals(Action.MATE))
			mate();
		else
			doNothing();
	}

	/** Finds next command with true condition to execute. Repeats process if
	 * command contains no action up to 1000 times after which critter waits */
	private void nextCommand() {
		mem[RC_INDEX] = 0;
		ruleNumber = -1;
		for (int i = 0; i != MAX_RULES_PER_TURN; i++) {
			int ruleNum = -1;
			Iterator<Rule> ruleIter = genome.rules();
			while(ruleIter.hasNext()) {
				ruleNum++;
				Rule a = ruleIter.next();
				if (a.cond().eval(this)) {
					Iterator<Update> updateIter = a.cmd().updates();
					while (updateIter.hasNext()) {
						Update u = updateIter.next();
						setMemAt(u.indexEval(this), u.eval(this));
					}
					if (a.cmd().action() != null) {
						performAction(Action.valueOf(a.cmd().action().toString().toUpperCase()));
						ruleNumber = ruleNum;
						return;
					} else {
						mem[RC_INDEX]++;
						break;
					}
				}
			}
			mem[RC_INDEX]++;
		}
		doNothing();
	}
	
	/** Causes critter to take no action */
	private void doNothing() {
		decreaseEnergy(getSize());
	}

	/** Moves critter forward if possible otherwise fails */
	private void forward() {
		moveToHex(getAdjacent(orientation));	
	}
	/** Moves critter backward if possible otherwise fails */
	private void backward() {
		moveToHex(getAdjacent((orientation + 3) % 6));
	}	
	/** Moves critter to Hex h if possible. Spends 
	 * energy regardless if move is succesful or not */
	private void moveToHex(Hex h) {
		decreaseEnergy(MOVE_COST * getSize());
		if (h != null && !h.hasCritter() && !h.hasRock()) {	
			h.addCritter(grid.hexAt(column, row).removeCritter());
			column = h.column;
			row = h.row;
		}
	}

	/** Rotates critter 60 degrees to the left */
	private void left() {
		decreaseEnergy(getSize());
		orientation = (orientation + 5) % 6;
	}
	/** Rotates critter 60 degrees to the right */
	private void right() {
		decreaseEnergy(getSize());
		orientation = (orientation + 1) % 6;
	}
	
	/** Causes critter to eat all, if any, food on hex */
	private void eat() {
		decreaseEnergy(getSize());
		mem[ENRG_INDEX] += grid.hexAt(column, row).removeFood();
		if(mem[ENRG_INDEX] > ENERGY_PER_SIZE * mem[SIZE_INDEX])
			mem[ENRG_INDEX] = ENERGY_PER_SIZE * mem[SIZE_INDEX];
	}

	/** Causes critter to attack hex in front of it */
	private void attack() {
		Hex h = getAdjacent(orientation);
		decreaseEnergy(ATTACK_COST * getSize());
		if (h != null && h.hasCritter()) {
			damageDealt = (int) (BASE_DAMAGE * getSize() 
					* logisticFunction(DAMAGE_INC * (getSize() * getOffense() - 
							h.getCritter().getSize() * h.getCritter().getDefense())));
			h.getCritter().decreaseEnergy(damageDealt);
		}
	}
	/** Mathematic function for critter attack/defense system */
	private double logisticFunction(double d) {
		return (1 / (1 + Math.exp(-d)));
	}

	/** Grows critter */
	private void grow() {
		decreaseEnergy(GROW_COST * getSize() * complexity());
		mem[SIZE_INDEX]++;
	}
	
	/** Creates new critter offspring as a result of budding */
	private void bud() {
		decreaseEnergy(BUD_COST * complexity());
		birthCritter(spawnMemory(getMemLength(), getDefense(), 
				getOffense()), mutateGenome(genome));
	}
	/** Attempts to create new critter offspring by mating with
	 * critter, if any, in front of it */
	private void mate() {
		decreaseEnergy(MATE_COST * complexity());
		mating = true;
		Hex h = getAdjacent(orientation);
		if (h != null && h.getCritter() != null && h.getCritter().getMating() && 
				Math.abs(h.getCritter().getOrientation() - orientation) == 3) {				
			int[] m = spawnMemory(Math.random() < 0.5 ? getMemLength() : h.getCritter().getMemLength(), 
					Math.random() < 0.5 ? getDefense() : h.getCritter().getDefense(), 
							Math.random() < 0.5 ? getOffense() : h.getCritter().getOffense());
			Program g = mergeGenome(h.getCritter());
			if (Math.random() < 0.5)
				birthCritter(m, g);
			else
				h.getCritter().birthCritter(m, g);
		}
	}
	/** Births new critter in the hex behind this critter with 
	 * given memory and genome. Does nothing if hex is occupied */
	private void birthCritter(int[] m, Program g) {
		Hex h = getAdjacent((orientation + 3) % 6);
		if (h != null && !h.hasRock() && !h.hasCritter())
			h.addNewCritter(new Critter(grid, h.column, h.row, 
					(int) (Math.random() * 6), m, g));
	}
	/** Returns memory with size and first three values passed from
	 * parent critter(s) and all other values determined by constants */
	private int[] spawnMemory(int len, int def, int off) {
		int[] ret = new int[len];
		ret[LEN_INDEX] = len;
		ret[DEF_INDEX] = def;
		ret[OFF_INDEX] = off;
		ret[SIZE_INDEX] = 1;
		ret[ENRG_INDEX] = INITIAL_ENERGY;
		ret[APP_INDEX] = 1;
		ret[RC_INDEX] = 0;
		return ret;
	}
	/** Returns genome merged from genome of given critter */
	private Program mergeGenome(Critter c) {
		LinkedList<Rule> r = new LinkedList<Rule>();
		Iterator<Rule> i = genome.rules();
		Iterator<Rule> j = c.getGenome().rules();
		while (i.hasNext() && j.hasNext())
			if (Math.random() < 0.5) {
				r.add(i.next());
				j.next();
			} else {
				i.next();
				r.add(j.next());
			}
		if (Math.random() < 0.5)
			while (i.hasNext())
				r.add(i.next());
		else
			while (j.hasNext())
				r.add(j.next());
		return mutateGenome(new Program(r));
	}
	/** Returns genome mutated a number of times */
	private Program mutateGenome(Program g) {
		Program ret = g;
		while (Math.random() < 0.5)
			ret = ret.mutate();
		return ret;
	}

	/** Replaces critter with carcass constructed from
	 * size if energy of critter has dropped below zero*/
	private void checkIfDead() {
		if (getEnergy() <= 0) {
			DELETED = true;
			grid.hexAt(column, row).deleteCritter();
		}
	}
	
	/** Returns contents of adjacent hex given by orientation dir */
	public int nearby(int dir) {
		return senseHex(getAdjacent(dir % 6));
	}	
	/** Returns contents of hex dist spaces ahead */
	public int ahead(int dist) {
		switch (orientation) {
			case 0: return senseHex(grid.hexAt(column, row + dist));
			case 1:	return senseHex(grid.hexAt(column + dist, row + dist));
			case 2:	return senseHex(grid.hexAt(column + dist, row));
			case 3:	return senseHex(grid.hexAt(column, row - dist));
			case 4:	return senseHex(grid.hexAt(column - dist, row - dist));
			case 5:	return senseHex(grid.hexAt(column - dist, row));
		}
		return ROCK_VALUE;
	}
	/** Returns integer denoting sensed contents of hex h */
	private int senseHex(Hex h) {
		if (h == null || h.hasRock())
			return ROCK_VALUE;
		if (h.hasCritter())
			return h.getCritter().getAppearance();
		return -h.getFood();
	}
	/** Returns damage dealt in previous time step */
	public int damage() {
		return damageDealt;
	}
	
	/** LinkedList queue for food BFS */
	private LinkedList<Hex> frontier = new LinkedList<Hex>();	
	/** BFS for food which finds distance of shortest path to
	 * a hex containing food and returns six times that distance
	 * plus direction from critter that minimizes that distance */
	public int food() {
		Hex h0 = grid.hexAt(column, row);
		if (h0.hasFood())
			return 1;
		LinkedList<Hex> blackNodes = new LinkedList<Hex>();
		h0.TRAVERSED = true;
		blackNodes.add(h0);
		if (0 < MAX_SMELL_DISTANCE) {
			for (int i = 0; i != 6; i++) {
				Hex h = getAdjacent(i);
				if (h.traversable()) {
					if (h.hasFood()) {
						h0.TRAVERSED = false;
						for (Hex hb : frontier)
							hb.TRAVERSED = false;
						return 6 * h.DISTANCE + h.DIRECTION;
					}
					h.TRAVERSED = true;
					h.DISTANCE = 1;
					h.DIRECTION = i;
					frontier.push(h);
				}
			}
			while (!frontier.isEmpty()) {
				Hex h = frontier.pop();
				blackNodes.add(h);
				if (h.DISTANCE < MAX_SMELL_DISTANCE)
					for (int i = 0; i != 6; i++) {
						Hex h2 = h.getAdjacent(i);
						if (h2.traversable()) {
							if (h2.hasFood()) {
								for (Hex hb : blackNodes)
									hb.TRAVERSED = false;
								for (Hex hb : frontier)
									hb.TRAVERSED = false;
								return 6 * h2.DISTANCE + h2.DIRECTION;
							}
							h2.TRAVERSED = true;
							h2.DISTANCE = h.DISTANCE + 1;
							h2.DIRECTION = h.DIRECTION;
							frontier.push(h2);
						}
					}
			}
		}
		return 0;
	}
	
	/** An enumeration of all critter actions */
	public static enum Action {
		WAIT,
		FORWARD,
		BACKWARD,
		LEFT,
		RIGHT,
		EAT,
		ATTACK,
		GROW,
		BUD,
		MATE;
	}
	
}
