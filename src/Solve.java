
public class Solve {
	public static void main(String[] args) {
		Grid grid = new Grid();
		Grid grid2 = new Grid();
		
		grid.printGrid();
		grid2.printGrid();
		
		System.out.println(grid.compareGrid(grid2));
	}
}
