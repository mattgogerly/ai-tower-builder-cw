import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AStarSearch {
	
	// List containing the nodes forming the solution
	private List<Node> solution;
	
	/*
	 *  List containing the states that are still yet to be searched. We need this
	 *  to be FIFO so we use a Linked List (or any queue).
	 */
	private LinkedList<Node> unsearchedNodes;
	
	// Boolean to store if we've found a solution
	private boolean solutionFound;
	
	// Int to count the number of nodes visited
	private int counter;
	
	public AStarSearch() {
		solution = new ArrayList<Node>();
		unsearchedNodes = new LinkedList<Node>();
		
		solutionFound = false;
		counter = 0;
	}
	
	public List<Node> searchForSolution(Grid startState, Grid goalState, boolean agentConsidered) {		
		Node initialNode = new Node(startState);
		initialNode.calculateHeuristic(goalState);
		unsearchedNodes.add(initialNode);
		
		Node currentNode = null;
		List<Node> childNodes = new ArrayList<Node>();
		
		while (!unsearchedNodes.isEmpty() && !solutionFound) {			
			currentNode = unsearchedNodes.poll();
			counter++;
			
			if (currentNode.getGrid().compareGrid(goalState, agentConsidered)) {
				solutionFound = true;
				break;
			}
			
			childNodes = currentNode.findChildren();
			
			boolean unexpanded = false;
			for (int i = 0; i < childNodes.size(); i++) {	
				Node child = childNodes.get(i);
				unexpanded = false;
				
				for (Node node : unsearchedNodes) {
					if (child.checkEqual(node)) {
						unexpanded = true;
						break;
					}
				}
				
				if (!unexpanded) {
					child.calculateHeuristic(goalState);
					boolean inserted = false;
					
					for (int j = 0; j < unsearchedNodes.size(); j++) {
						if (child.getHeuristic() < unsearchedNodes.get(j).getHeuristic()) {
							unsearchedNodes.add(j, child);
							inserted = true;
							break;
						}
					}
					
					if (!inserted) {
						unsearchedNodes.add(child);
					}
				}
			}
			
			unsearchedNodes.remove(currentNode);
		}
		
		if (solutionFound) {
			// We now need to go back recursively through node parents until we get back to the start state
			boolean routeFound = false;
			
			solution.add(currentNode);
			while (!routeFound) {
				currentNode = currentNode.getParentNode();
				solution.add(0, currentNode);
				
				if (currentNode.getGrid().compareGrid(startState, true)) {
					routeFound = true;
				}
			}
			
			printSolution();
		} else {
			System.out.println("No solution found.");
		}

		return solution;
	}
	
	private void printSolution() {		
		for (Node n : solution) {
			n.getGrid().printGrid();
			System.out.println();
		}
		
		System.out.println("Nodes visited: " + counter);
	}
	
}
