import java.util.Arrays;

public class Grid {
	private char[][] grid; // 3D Array to store the state of the grid
	
	/*
	 * The width and height of the grid
	 */
	private int width = 4;
	private int height = 4;
	
	/*
	 * The X and Y coordinates of the agent
	 */
	private Integer agentX;
	private Integer agentY;
	
	/*
	 * Chars to represent empty tiles and the agent tile
	 */
	private static final char EMPTY_CHAR = 'X';
	private static final char AGENT_CHAR = 'T';
	
	/*
	 * Constructor to initialise a new Grid with the defined start state
	 */
	public Grid() {
		grid = new char[width][height];
		
		for (int y = 0; y < height - 1; y++) {
			for (int x = 0; x < width; x++ ) {
				grid[x][y] = EMPTY_CHAR;
			}
		}
		
		// Initialise the blocks to their starting positions (bottom left of the grid)
		grid[0][height - 1] = 'A';
		grid[1][height - 1] = 'B';
		grid[2][height - 1] = 'C';
		
		// Agent is initialised in bottom right
		grid[3][height - 1] = AGENT_CHAR;
		agentX = width - 1;
		agentY = height - 1;
	}
	
	/*
	 * Constructor that copies an existing grid to a new one.
	 */
	public Grid(Grid g) {
		if (g.agentX == null || g.agentY == null) {
			System.err.println("No agent was specified!");
		}
		
		width = g.getWidth();
		height = g.getHeight();
		
		grid = new char[width][height];
		
		char[][] existingGrid = g.getGrid();
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				grid[x][y] = existingGrid[x][y];
			}
		}
		
		agentX = g.getAgentX();
		agentY = g.getAgentY();
	}
	
	
	/*
	 * Constructor to create a grid from a given 2D array of chars with the x,y of agent
	 */
	public Grid(char[][] grid, int agentX, int agentY) {
		height = grid.length;
		width = grid[0].length;
		
		this.grid = grid;
		this.agentX = agentX;
		this.agentY = agentY;
	}
	
	/*
	 * Method to print out the current state of the grid to the console
	 */
	public void printGrid()
	{
		for ( int y = 0; y < height; y++)
		{
			for ( int x = 0; x < width; x++ )
			{
				// Print the element at that position
				System.out.print( grid[x][y] + " " );
			}
			
			// Print next line
			System.out.println();
		}
		
		System.out.println();
	}
	
	public boolean moveAgent(int x, int y) {
		boolean successful = false;
		
		if (x > 1 || x < -1 || y > 1 || y < -1) {
			System.err.println("Cannot move the agent more than one tile at a time!");
			return successful;
		}
		
		// x will be non-zero if the movement is horizontal
		if (x != 0) {
			
			// Sanity checking. Cannot move outside of the grid.
			if (x > 0 && agentX < width - 1) {
				grid[agentX][agentY] = grid[agentX + 1][agentY];
				grid[agentX + 1][agentY] = AGENT_CHAR;
				
				agentX = agentX + 1;
				
				successful = true;
			} else if (x < 0 && agentX > 0) {
				grid[agentX][agentY] = grid[agentX - 1][agentY];
				grid[agentX - 1][agentY] = AGENT_CHAR;
				
				agentX = agentX - 1;
				
				successful = true;
			}
			
		} else if (y != 0) {
			
			// Sanity checking. Cannot move outside of the grid.
			if (y > 0 && agentY < height - 1) {
				grid[agentX][agentY] = grid[agentX][agentY + 1];
				grid[agentX][agentY + 1] = AGENT_CHAR;
				
				agentY = agentY + 1;
				
				successful = true;
			} else if (y < 0 && agentY > 0) {
				grid[agentX][agentY] = grid[agentX][agentY - 1];
				grid[agentX][agentY - 1] = AGENT_CHAR;
				
				agentY = agentY - 1;
				
				successful = true;
			}
			
		}
		
		return successful;
	}
	
	/*
	 * Method to compare two grids. Returns true if equal, false otherwise.
	 */
	public boolean compareGrid(Grid g, boolean agentConsidered) {	
		// If they're not the same dimensions they can't be the same so return false.
		if (this.getWidth() != g.getWidth() || this.getHeight() != g.getHeight()) {
			return false;
		}
		
		boolean equal = true;
		char[][] compGrid = g.getGrid();		
		
		if (agentConsidered) {
			if (!Arrays.deepEquals(grid, compGrid)) {
				equal = false;
			}
		} else {
			for (int y = 0; y < this.getHeight(); y++) {
				for (int x = 0; x < this.getWidth(); x++) {					
					if (grid[x][y] != compGrid[x][y]) {
						if (!((grid[x][y] == AGENT_CHAR && compGrid[x][y] == EMPTY_CHAR) || (grid[x][y] == EMPTY_CHAR && compGrid[x][y] == AGENT_CHAR))) {
							equal = false;
						}
					}
				}
			}
		}
			
		return equal;
	}
	
	public int[] findInGrid(char toFind) {
		int[] pos = new int[2];
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (grid[x][y] == toFind) {
					pos[0] = x;
					pos[1] = y;
					
					break;
				}
			}
		}
		
		return pos;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public char[][] getGrid() {
		return grid;
	}
	
	public int getAgentX() {
		return agentX;
	}
	
	public int getAgentY() {
		return agentY;
	}
}
