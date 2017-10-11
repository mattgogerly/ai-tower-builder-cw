
public class Grid {
	private char[][] grid; // 3D Array to store the state of the grid
	
	/*
	 * The width and height of the grid
	 */
	private final int width = 4;
	private final int height = 4;
	
	/*
	 * The X and Y coordinates of the agent
	 */
	private int agentX;
	private int agentY;
	
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
		
		for (int i = 0; i < height - 1; i++) {
			for (int j = 0; j < width; j++ ) {
				grid[j][i] = EMPTY_CHAR;
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
	public Grid(Grid existingGrid) {
		
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
			System.out.print( "\n" );
		}
		
		System.out.print( "\n" );
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
			} else {
				System.err.println("Cannot move the agent outside of the grid!");
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
			} else {
				System.err.println("Cannot move the agent outside of the grid!");
			}
			
		}
		
		return successful;
	}
}
