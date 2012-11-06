package server.world.grid;

/** Represents objects with hexagonal coordinates column and row */
public class HexCoordinates {	
	
	/** Grid this hex is on */
	protected Grid grid;	
	/** Column in hexagonal coordinates */
	protected int column;
	/** Row in hexagonal coordinates */
	protected int row;
	
	/** Constructs hex object with grid, column, and row */
	public HexCoordinates(Grid g, int c, int r) {
		grid = g;
		column = c;
		row = r;
	}
	
	/** Returns column this hex object is on */
	public int getColumn() {
		return column;
	}	
	/** Returns row this hex object is on */
	public int getRow() {
		return row;
	}
	
	/** Returns adjacent hex based on orientation. Returns 
	 * null if orientation is invalid or if hex is off edge */
	public Hex getAdjacent(int o) {
		switch (o) {
			case 0: return grid.hexAt(column, row + 1);
			case 1:	return grid.hexAt(column + 1, row + 1);
			case 2:	return grid.hexAt(column + 1, row);
			case 3:	return grid.hexAt(column, row - 1);
			case 4:	return grid.hexAt(column - 1, row - 1);
			case 5:	return grid.hexAt(column - 1, row);
		}
		return null;
	}
	
}
