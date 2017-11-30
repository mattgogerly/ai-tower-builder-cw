import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BreadthFirstSearch {
	
	// Stack containing the nodes forming the solution
	private Stack<Node> solution;
	
	/*
	 *  List containing the states that are still yet to be searched. We need this
	 *  to be FIFO for Breadth First Search so we use a Linked List.
	 */
	private LinkedList<Node> fringe;
	
	// Boolean to store if we've found a solution
	private boolean solutionFound;
	
	// Int to count the number of nodes visited
	private int counter;
	
	/*
	 * Constructor to initialise a new BFS
	 */
	public BreadthFirstSearch() {
		solution = new Stack<Node>();
		fringe = new LinkedList<Node>();
		
		solutionFound = false;
		counter = 0;
	}
	
	/*
	 * Method to search for a solution given a start state, goal state and whether we're considering
	 * the agent's position as part of the goal
	 */
	public List<Node> searchForSolution(Grid startState, Grid goalState, boolean agentConsidered) {	
		// Initialise the initial node and add it to the fringe  
		Node initialNode = new Node(startState);
		fringe.add(initialNode);
		
		// Set up our helper Node and List of children  
		Node currentNode = null;
		List<Node> childNodes = new ArrayList<Node>();
		
		// While we don't have a solution and there are nodes in the fringe
		while (!fringe.isEmpty() && !solutionFound) {			
			// Get the current node and remove it from the fringe
			currentNode = fringe.poll();
			counter++;
			
			// If this node is our goal then break 
			if (currentNode.getGrid().compareGrid(goalState, agentConsidered)) {
				solutionFound = true;
				break;
			}
			
			// Otherwise get the children of this node
			childNodes = currentNode.findChildren();
			
			// For each Node in the list of children
			for (Node n : childNodes) {
				fringe.add(n);
			}
		}
		
		// If we've found a solution
		if (solutionFound) {
			// We now need to go back recursively through node parents until we get back to the start state
			boolean routeFound = false;
			
			// Add the last node to the solution stack
			solution.push(currentNode);
			
			// While we haven't finished the route
			while (!routeFound) {
				// Find the current node's parent and add it to the stack
				currentNode = currentNode.getParentNode();
				solution.push(currentNode);
				
				// If this node is the start state then we've found our route
				if (currentNode.getGrid().compareGrid(startState, true)) {
					routeFound = true;
				}
			}
			
			// Print the solution
			printSolution();
		} else {
			// Otherwise there is no solution
			System.out.println("No solution found.");
		}

		return solution;
	}
	
	/*                                                                           
	 * Method to print our solution and some statistics                          
	 */                                                                          
	private void printSolution() {                                               
		// Save the size of the stack since we'll be popping it                  
		int moves = solution.size() - 1;                                         
		
		// While the stack isn't empty pop it, get the grid and print it         
		while (!solution.isEmpty()) {  
			Node n = solution.pop();
			System.out.println("Move #" + n.getDepth());
			n.getGrid().printGrid();                                                                              
		}                                                                        
		                                                                         
		System.out.println("Nodes visited: " + counter);                         
		System.out.println("Number of moves from start to goal state: " + moves);
	}
	
}
