/*
 * Class to control the running of the four algorithms.
 */

public class Solve {
	
	/*
	 * Main method
	 */
	public static void main(String[] args) {
		// Create a Grid with the start state from the specification
		Grid startState = new Grid("XXXAXXXBXXXCXXXT", 4, 4);
		
		// Declare a Grid to represent the goal state
		Grid goalState;
		
		// If the correct number of arguments have been provided
		if (args.length == 3) {
			// Get the width and height
			Integer width = Integer.parseInt(args[1]);
			Integer height = Integer.parseInt(args[2]);
			
			// Instantiate the Grid using these arguments
			goalState = new Grid(args[0], width, height);
		} else {
			// Otherwise use the goal state from the specification
			goalState = new Grid("XXXXXABCXXXXXXXT", 4, 4);
		}
		
		// Perform breadth first search (false to not consider the agent)
		BreadthFirstSearch bfs = new BreadthFirstSearch();
		bfs.searchForSolution(startState, goalState, false);
		
		// Perform depth first search (false to not consider the agent)
		DepthFirstSearch dfs = new DepthFirstSearch();
		dfs.searchForSolution(startState, goalState, false);
		
		// Perform iterative deepening search (false to not consider the agent)
		IterativeDeepeningSearch ids = new IterativeDeepeningSearch();
		ids.searchForSolution(startState, goalState, false);
		
		// Perform A* search (false to not consider the agent)
		AStarSearch ass = new AStarSearch();
		ass.searchForSolution(startState, goalState, false);
	}
	
}
