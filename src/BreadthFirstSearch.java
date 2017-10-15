import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BreadthFirstSearch {
	
	// List containing the nodes forming the solution
	private List<Node> solution;
	
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
		unsearchedNodes = new LinkedList<Node>();
		
		List<Node> childNodes = new ArrayList<Node>();
		
		Node initialNode = new Node(startState);
		unsearchedNodes.add(initialNode);
		
		Node currentNode = null;
		
		while (!unsearchedNodes.isEmpty() && !solutionFound) {			
			currentNode = unsearchedNodes.poll();
			counter++;
			
			if (currentNode.getGrid().compareGrid(goalState, agentConsidered)) {
				solutionFound = true;
				break;
			}
			
			childNodes = currentNode.findChildren();
			
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
		
		System.out.println(solution.get(solution.size() - 1));
		
		System.out.println("Nodes visited: " + counter);
	}
	
}
