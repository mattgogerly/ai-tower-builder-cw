public class Solve {
	
	public static void main(String[] args) {
		Grid startState = new Grid();
		
		Grid goalState = createGoalGrid("XXXXXABCXXXXXXXT", 4, 4);
		
		// BreadthFirstSearch bfs = new BreadthFirstSearch();
		// bfs.searchForSolution(startState, goalState, false);
		
		DepthFirstSearch dfs = new DepthFirstSearch();
		dfs.searchForSolution(startState, goalState, false);
		
		IterativeDeepeningSearch ids = new IterativeDeepeningSearch();
		ids.searchForSolution(startState, goalState, false);
		
		AStarSearch ass = new AStarSearch();
		ass.searchForSolution(startState, goalState, false);
	}
	
	public static Grid createGoalGrid(String input, int width, int height) {
		char[][] goalGrid = new char[width][height];
		char[] temp = input.toCharArray();
		
		if ((width * height) != temp.length) {
			System.err.print("Invalid input string");
		}
		
		Integer agentX = null;
		Integer agentY = null;
		
		int count = 0;
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				
				if (temp[count] == 'T') {
					agentX = i;
					agentY = j;
				}
				
				goalGrid[i][j] = temp[count];
				count++;
			}
		}
		
		return new Grid(goalGrid, agentX, agentY);
	}
	
}
