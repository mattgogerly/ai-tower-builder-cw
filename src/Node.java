import java.util.ArrayList;

public class Node implements Comparable<Node> {
	// The state of the grid at this node
	private Grid grid;
	
	// List of child grids from this node
	private ArrayList<Node> childNodes;
	
	// The parent node of this node
	private Node parent;
	
	// The depth of this node
	private int depth;
	
	// The heuristic calculated
	private float heuristic;
	
	/*
	 * Constructor to create a new Node with initial grid and empty list of child nodes.
	 */
	public Node(Grid grid) {
		this.grid = grid;
		this.childNodes = new ArrayList<Node>();
		this.depth = 0;
		this.heuristic = 0;
	}
	
	/*
	 * Method to find the children of the node. Tries to move the agent each direction and adds 
	 * the new node to the list if successful.
	 */
	public ArrayList<Node> findChildren() {
		Grid currentState; // Grid to be initialised to the current grid state each try
		
		// Initialise the grid to the current state and try moving the agent up
		currentState = new Grid(grid);
		if (currentState.moveAgent(0, -1)) {
			// Create a new node with the new state
			Node newChild = new Node(currentState);
			// Set the new node's parent to this
			newChild.setParentNode(this);
			newChild.setDepth(this.getDepth() + 1);
			// Add the new node to this node's list of children
			childNodes.add(newChild);
		}
		
		// Initialise the grid to the current state and try moving the agent down
		currentState = new Grid(grid);
		if (currentState.moveAgent(0, 1)) {
			Node newChild = new Node(currentState);
			newChild.setParentNode(this);
			newChild.setDepth(this.getDepth() + 1);
			childNodes.add(newChild);
		}
		
		// Initialise the grid to the current state and try moving the agent left
		currentState = new Grid(grid);
		if (currentState.moveAgent(-1, 0)) {
			Node newChild = new Node(currentState);
			newChild.setParentNode(this);
			newChild.setDepth(this.getDepth() + 1);
			childNodes.add(newChild);
		}
		
		// Initialise the grid to the current state and try moving the agent right
		currentState = new Grid(grid);
		if (currentState.moveAgent(1, 0)) {
			Node newChild = new Node(currentState);
			newChild.setParentNode(this);
			newChild.setDepth(this.getDepth() + 1);
			childNodes.add(newChild);
		}
		
		// Return the list of child nodes for this node
		return childNodes;
	}
	
	/*
	 * Method to calculate the heuristic value for this node
	 */
	public void calculateHeuristic(Grid goalState) {
		Grid currentState = this.getGrid();
		
		// ints to store the distance between the current position of a char and its goal position
		int distance = 0;
		int totalDistance = 0;
		
		// int array to store the x,y of the current/goal positions
		int[] currentPos = new int[2];
		int[] requiredPos = new int[2];
		
		// Find the distance between the current position of A and it's goal position
		currentPos = currentState.findInGrid('A');
		requiredPos = goalState.findInGrid('A');
		distance = Math.abs(currentPos[0] - requiredPos[0]) + Math.abs(currentPos[1] - requiredPos[1]);
		totalDistance += distance;
		
		// Find the distance between the current position of B and it's goal position
		currentPos = currentState.findInGrid('B');
		requiredPos = goalState.findInGrid('B');
		distance = Math.abs(currentPos[0] - requiredPos[0]) + Math.abs(currentPos[1] - requiredPos[1]);
		totalDistance += distance;
		
		// Find the distance between the current position of C and it's goal position
		currentPos = currentState.findInGrid('C');
		requiredPos = goalState.findInGrid('C');
		distance = Math.abs(currentPos[0] - requiredPos[0]) + Math.abs(currentPos[1] - requiredPos[1]);
		totalDistance += distance;
		
		// Our heuristic is the total distance + the current depth (reduced since it's a small grid)
		heuristic = totalDistance + depth / 4;
	}
	
	/*
	 * Method to get the heuristic value for this node
	 */
	public float getHeuristic() {
		return heuristic;
	}
	
	/*
	 * Method to set the parent node of this node to the one provided
	 */
	public void setParentNode(Node node) {
		this.parent = node;
	}
	
	/*
	 * Method to return the parent node of this node
	 */
	public Node getParentNode() {
		return parent;
	}
	
	/*
	 * Method to set the depth of this node
	 */
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	/*
	 * Method to get the depth of this node
	 */
	public int getDepth() {
		return depth;
	}
	
	/*
	 * Method to return the state of the grid at this node
	 */
	public Grid getGrid() {
		return grid;
	}
	
	/*
	 * Method to check if the grid at this node is equal to the grid at a specified grid
	 */
	public boolean checkEqual(Node node) {
		return grid.compareGrid(node.getGrid(), true);
	}
	
	/*
	 * Method to compare two Nodes (used for queue adding)
	 */
	public int compareTo(Node b) {
		if (this.getHeuristic() < b.getHeuristic()) {
			return -1;
		} else if (this.getHeuristic() > b.getHeuristic()) {
			return 1;
		}
		
		return 0;
	}
}
