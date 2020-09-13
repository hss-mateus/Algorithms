import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public  class Percolation {

    private WeightedQuickUnionUF grid;

    // Side size of the grid
    private int sideSize;

    // Two "virtual" nodes representing the top and bottom rows
    private int topNode = 0;
    private int bottomNode;

    // Array containing open status of nodes
    private boolean[] openNodes;

    // Count of open nodes
    private int openCount = 0;

    public Percolation( int n) {
        if (n < 1)
            throw new IllegalArgumentException();

        var gridSize = n * n;

        // Create a new grid with the two virtual nodes
        grid = new WeightedQuickUnionUF(gridSize + 2);
        sideSize = n;

        // Set the bottom virtual node index
        bottomNode = gridSize + 1;

        // Initialize openNodes to all false
        openNodes = new boolean[gridSize + 2];

        // Change the state of the virtual nodes to open
        openNodes[topNode] = true;
        openNodes[bottomNode] = true;
    }

    // Set the state of a node to open, and union it to opened neighbors
    public void open(int row, int col) {
        // Do nothing if it's already open
        if (isOpen(row, col))
            return;

        var index = indexOf(row, col);

        // Otherwise, set it's open state to true
        openNodes[index] = true;

        // and increment the count of opened nodes
        openCount++;

        // Then, try to union it's neighbor nodes
        tryUnion(index, row + 1, col);
        tryUnion(index, row - 1, col);
        tryUnion(index, row, col + 1);
        tryUnion(index, row, col - 1);
    }

    // Helper method to handle unions to neighbors
    private void tryUnion(int a, int rowB, int colB) {
        // If it doesn't have a top neighbor, then union to the top virtual node
        if (rowB == 0)
            grid.union(a, topNode);

        // Same as above, but for the bottom virutal node
        else if (rowB == sideSize + 1)
            grid.union(a, bottomNode);

        // Else, union to a valid opened node in the left or right
        else if (colB > 0 && colB <= sideSize && isOpen(rowB, colB))
            grid.union(a, indexOf(rowB, colB));
    }

    // Check if it's opened
    public boolean isOpen(int row, int col) {
        return openNodes[indexOf(row, col)];
    }

    // Check if it's connected to the top virtual node
    public boolean isFull(int row, int col) {
        return grid.find(topNode) == grid.find(indexOf(row, col));
    }

    // Get the size of opened nodes
    public int numberOfOpenSites() {
        return openCount;
    }

    // Check if the top and bottom virtual nodes are connected
    public boolean percolates() {
        return grid.find(topNode) == grid.find(bottomNode);
    }

    // Auxiliary method to convert coordinates to an array index
    private int indexOf(int row, int col) {
        if (row < 1 || row > sideSize || col < 1 || col > sideSize)
            throw new IllegalArgumentException();

        return (sideSize * row) - sideSize + col;
    }
}
