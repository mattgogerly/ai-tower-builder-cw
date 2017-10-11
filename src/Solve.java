public class Solve {
	
	public static void main(String[] args) {
		Grid startState = new Grid();
		
		char[][] goalGrid = createGoalGrid(3, 3);
		Grid goalState = new Grid(goalGrid, 3, 3);
		
		/*BreadthFirstSearch bfs = new BreadthFirstSearch();
		bfs.searchForSolution(startState, goalState);*/
		
		System.out.println("Running DFS...");
		
		DepthFirstSearch dfs = new DepthFirstSearch();
		dfs.searchForSolution(startState, goalState);
	}
	
	public static char[][] createGoalGrid(int agentX, int agentY) {
		char[][] goalGrid = new char[4][4];
		
		for (int i = 0; i < 4; i++) {
			goalGrid[0][i] = 'X';
		}
		
		goalGrid[1][0] = 'X';
		goalGrid[1][1] = 'A';
		goalGrid[1][2] = 'B';
		goalGrid[1][3] = 'C';
		
		goalGrid[2][0] = 'X';
		goalGrid[2][1] = 'X';
		goalGrid[2][2] = 'X';
		goalGrid[2][3] = 'X';
		
		goalGrid[3][0] = 'X';
		goalGrid[3][1] = 'X';
		goalGrid[3][2] = 'X';
		goalGrid[3][3] = 'X';
		
		goalGrid[agentX][agentY] = 'T';
		
		return goalGrid;
	}
	
}
