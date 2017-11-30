public class Solve {
	
	public static void main(String[] args) {
		Grid startState = new Grid("XXXAXXXBXXXCXXXT", 4, 4);
		Grid goalState;
		
		if (args.length == 3) {
			Integer width = Integer.parseInt(args[1]);
			Integer height = Integer.parseInt(args[2]);
			
			goalState = new Grid(args[0], width, height);
		} else {
			goalState = new Grid("XXXXXABCXXXXXXXT", 4, 4);
		}
		
		//BreadthFirstSearch bfs = new BreadthFirstSearch();
		//bfs.searchForSolution(startState, goalState, true);
		
		//DepthFirstSearch dfs = new DepthFirstSearch();
		//dfs.searchForSolution(startState, goalState, true);
		
		//IterativeDeepeningSearch ids = new IterativeDeepeningSearch();
		//ids.searchForSolution(startState, goalState, true);
		
		//AStarSearch ass = new AStarSearch();
		//ass.searchForSolution(startState, goalState, true);
	}
	
}
