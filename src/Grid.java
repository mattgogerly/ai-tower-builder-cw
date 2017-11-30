import java.util.Arrays;

public class Grid {
	private char[][] grid; // 2D Array to store the state of the grid
	
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
	 * Constructor to initialise a new Grid with the provided state
	 */
	public Grid(String input, int width, int height) {
		this.grid = new char[width][height];
		char[] temp = input.toCharArray();
		
		if ((width * height) != temp.length) {
			System.err.print("Invalid input string");
		}
		
		int count = 0;
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				
				if (temp[count] == 'T') {
					this.agentX = i;
					this.agentY = j;
				}
				
				this.grid[i][j] = temp[count];
				count++;
			}
		}
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
		
		// Initialise a new array of the correct size
		grid = new char[width][height];
		
		char[][] existingGrid = g.getGrid();
		
		// Loop over each row then column and set each element equal to the same element in our new array
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				grid[x][y] = existingGrid[x][y];
			}
		}
		
		// Set the agents position
		agentX = g.getAgentX();
		agentY = g.getAgentY();
	}
	
	/*
	 * Constructor to create a grid from a given 2D array of chars with the x,y of the agent
	 */
	public Grid(char[][] grid, int agentX, int agentY) {
		// Set the width and height
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
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				// Print the element at that position
				System.out.print( grid[x][y] + " " );
			}
			
			// Print next line
			System.out.println();
		}
		
		System.out.println();
	}
	
	/*
	 * Method to move the agent by a specified x or y distance
	 */
	public boolean moveAgent(int x, int y) {
		boolean successful = false;
		
		// We only want to be able to move the agent one tile at a time
		if (x > 1 || x < -1 || y > 1 || y < -1) {
			System.err.println("Cannot move the agent more than one tile at a time!");
			return successful;
		}
		
		// x will be non-zero if the movement is horizontal
		if (x != 0) {
			
			// Sanity checking. Cannot move outside of the grid.
			if (x > 0 && agentX < width - 1) {
				
				// Move right. Set the current agent position's tile to the contents of the tile to the right
				grid[agentX][agentY] = grid[agentX + 1][agentY];
				
				// Set the right hand tile to the agent character
				grid[agentX + 1][agentY] = AGENT_CHAR;
				
				// Set the new agent x position
				agentX = agentX + 1;
				
				successful = true;
			} else if (x < 0 && agentX > 0) {
				// Move left. Set the current agent position's tile to the contents of the tile to the left
				grid[agentX][agentY] = grid[agentX - 1][agentY];
				
				// Set the right hand tile to the agent character
				grid[agentX - 1][agentY] = AGENT_CHAR;
				
				// Set the new agent x position
				agentX = agentX - 1;
				
				successful = true;
			}
			
		} else if (y != 0) {
			
			// Sanity checking. Cannot move outside of the grid.
			if (y > 0 && agentY < height - 1) {
				// Move down. Set the current agent position's tile to the contents of the tile below it
				grid[agentX][agentY] = grid[agentX][agentY + 1];
				
				// Set the below tile to the agent character
				grid[agentX][agentY + 1] = AGENT_CHAR;
				
				// Set the new agent y position
				agentY = agentY + 1;
				
				successful = true;
			} else if (y < 0 && agentY > 0) {
				// Move up. Set the current agent position's tile to the contents of the tile above it
				grid[agentX][agentY] = grid[agentX][agentY - 1];
				
				// Set the above tile to the agent character.
				grid[agentX][agentY - 1] = AGENT_CHAR;
				
				// Set the new agent y position
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
		
		// Assume they're equal to begin with
		boolean equal = true;
		char[][] compGrid = g.getGrid();		
		
		// If we're considering the agent we can just use deep equals
		if (agentConsidered) {
			if (!Arrays.deepEquals(grid, compGrid)) {
				equal = false;
			}
		} else {
			// Otherwise we have to use this beast...
			
			// Loop over the rows and columns
			for (int y = 0; y < this.getHeight(); y++) {
				for (int x = 0; x < this.getWidth(); x++) {		
					
					// If the elements are not the same at this position
					if (grid[x][y] != compGrid[x][y]) {
						// And if neither of the tiles is the agent character
						if (!((grid[x][y] == AGENT_CHAR && compGrid[x][y] == EMPTY_CHAR) || (grid[x][y] == EMPTY_CHAR && compGrid[x][y] == AGENT_CHAR))) {
							equal = false;
						}
					}
				}
			}
		}
			
		return equal;
	}
	
	/*
	 * Method to find a char in the grid
	 */
	public int[] findInGrid(char toFind) {
		// Array to store the x,y position
		int[] pos = new int[2];
		
		// Loop over the rows and columns
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				
				// If we've found it then set position array elements and break
				if (grid[x][y] == toFind) {
					pos[0] = x;
					pos[1] = y;
					
					break;
				}
			}
		}
		
		return pos;
	}
	
	/*
	 * Method to return the width of the grid
	 */
	public int getWidth() {
		return width;
	}
	
	/*
	 * Method to return the height of the grid
	 */
	public int getHeight() {
		return height;
	}
	
	/*
	 * Method to return the char array representing the grid
	 */
	public char[][] getGrid() {
		return grid;
	}
	
	/*
	 * Method to return the x position of the agent
	 */
	public int getAgentX() {
		return agentX;
	}
	
	/*
	 * Method to return the y position of the agent
	 */
	public int getAgentY() {
		return agentY;
	}
}
