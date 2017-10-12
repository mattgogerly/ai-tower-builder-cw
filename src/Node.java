import java.util.ArrayList;

public class Node {
	// The state of the grid at this node
	private Grid grid;
	
	// List of child grids from this node
	private ArrayList<Node> childNodes;
	
	// The parent node of this node
	private Node parent;
	
	/*
	 * Constructor to create a new Node with initial grid and empty list of child nodes.
	 */
	public Node(Grid grid) {
		this.grid = grid;
		this.childNodes = new ArrayList<Node>();
	}
	
	/*
	 * Method to find the children of the node. Tries to move the agent each direction and adds 
	 * the new node to the list if successful.
	 */
	public ArrayList<Node> findChildren() {
		Grid currentState; // Grid to be initialised to the current grid state each try
		
		// Initialise the grid to the current state and try moving the agent right
		currentState = new Grid(grid);
		if (currentState.moveAgent(1, 0)) {
			// Create a new node with the new state
			Node newChild = new Node(currentState);
			// Set the new node's parent to this
			newChild.setParentNode(this);
			// Add the new node to this node's list of children
			childNodes.add(newChild);
		}
		
		// Initialise the grid to the current state and try moving the agent left
		currentState = new Grid(grid);
		if (currentState.moveAgent(-1, 0)) {
			Node newChild = new Node(currentState);
			newChild.setParentNode(this);
			childNodes.add(newChild);
		}
		
		// Initialise the grid to the current state and try moving the agent down
		currentState = new Grid(grid);
		if (currentState.moveAgent(0, 1)) {
			Node newChild = new Node(currentState);
			newChild.setParentNode(this);
			childNodes.add(newChild);
		}
		
		// Initialise the grid to the current state and try moving the agent up
		currentState = new Grid(grid);
		if (currentState.moveAgent(0, -1)) {
			Node newChild = new Node(currentState);
			newChild.setParentNode(this);
			childNodes.add(newChild);
		}
		
		// Return the list of child nodes for this node
		return childNodes;
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
}
