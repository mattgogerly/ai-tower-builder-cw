import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearch {
	
	// List containing the nodes forming the solution
	private List<Node> solution;
	
	/*
	 *  Stack containing the states that are still yet to be searched. We need this
	 *  to be LIFO for Depth First Search so we use a Stack.
	 */
	private Stack<Node> unsearchedNodes;
	
	// Boolean to store if we've found a solution
	private boolean solutionFound;
	
	// Int to count the number of nodes visited
	private int counter;
	
	public DepthFirstSearch() {
		solution = new ArrayList<Node>();
		unsearchedNodes = new Stack<Node>();
		
		solutionFound = false;
		counter = 0;
	}
	
	public List<Node> searchForSolution(Grid startState, Grid goalState, boolean agentConsidered) {		
		Node initialNode = new Node(startState);
		unsearchedNodes.add(initialNode);
		
		Node currentNode = null;
		List<Node> childNodes = new ArrayList<Node>();
		
		while (!unsearchedNodes.isEmpty() && !solutionFound) {			
			currentNode = unsearchedNodes.pop();
			counter++;
			
			if (currentNode.getGrid().compareGrid(goalState, agentConsidered)) {
				solutionFound = true;
				break;
			}
			
			childNodes = currentNode.findChildren();
			Collections.shuffle(childNodes);
			
			for (int i = 0; i < childNodes.size(); i++) {				
				unsearchedNodes.add(childNodes.get(i));
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
		} else {
			System.out.println("No solution found.");
		}
		
		printSolution();
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
