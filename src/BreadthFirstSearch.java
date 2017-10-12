import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BreadthFirstSearch {
	
	// List containing the nodes forming the solution
	private List<Node> solution;
	
	
	// List of nodes we've visited already
	private List<Node> visitedNodes;
	
	/*
	 *  List containing the states that are still yet to be searched. We need this
	 *  to be FIFO for Breadth First Search so we use a Linked List.
	 */
	private LinkedList<Node> unsearchedNodes;
	
	// Boolean to store if we've found a solution
	private boolean solutionFound;
	
	// Int to count the number of nodes visited
	private int counter;
	
	public List<Node> searchForSolution(Grid startState, Grid goalState, boolean agentConsidered) {
		solutionFound = false;
		counter = 0;
		
		solution = new ArrayList<Node>();
		visitedNodes = new ArrayList<Node>();
		unsearchedNodes = new LinkedList<Node>();
		
		List<Node> childNodes = new ArrayList<Node>();
		
		Node initialNode = new Node(startState);
		unsearchedNodes.add(initialNode);
		
		Node currentNode = null;
		boolean visited = false;
		
		while (!unsearchedNodes.isEmpty() && !solutionFound) {			
			currentNode = unsearchedNodes.poll();
			counter++;
			
			if (currentNode.getGrid().compareGrid(goalState, agentConsidered)) {
				solutionFound = true;
				break;
			}
			
			childNodes = currentNode.findChildren();
			
			for (int i = 0; i < childNodes.size(); i++) {				
				visited = false;
				for (Node n : visitedNodes) {
					// Check if we've already visited this child - if we have break out of the loop
					if (childNodes.get(i).checkEqual(n)) {
						visited = true;
						break;
					}
				}
				
				for (Node n : unsearchedNodes) {
					// Check if we've already visited this node but haven't searched it yet - if we have break out of loop
					if (childNodes.get(i).checkEqual(n)) {
						visited = true;
						break;
					}
				}
				
				// If we haven't already been to this node then add it to our list of unsearched nodes
				if (!visited) {
					unsearchedNodes.add(childNodes.get(i));
				}
			}
			
			visitedNodes.add(currentNode);
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
		System.out.println("Nodes visited: " + counter);
		
		for (Node n : solution) {
			n.getGrid().printGrid();
			System.out.println();
		}
	}
	
}
