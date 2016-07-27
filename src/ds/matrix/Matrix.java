package ds.matrix;

public class Matrix {
    private Row[] grid;
    private int numRows;
    private int numColumns;

    public Matrix(int gridSize) {
        int gridSizeSquared = (int) Math.pow(gridSize, 2); // grid size squared is used many more times (put it in a variable for speed)
        numRows = gridSizeSquared * gridSize; // grid size cubed
        numColumns = 3 * gridSizeSquared + 2 * gridSize; // number of constraints
        grid = new Row[numRows];

        int currentRowIndex = 0;
        for (int row = 0; row < gridSize; row++) { // loop through options for rows
            for (int column = 0; column < gridSize; column++) { // loop through options for columns
                for (int value = 0; value < gridSize; value++) { // loop through options for number values
                    Row currentRow = new Row(numColumns, value, row, column);

                    int columnCellIndex = row * gridSize + column;
                    int columnRowIndex = gridSizeSquared + row * gridSize + value;
                    int columnColIndex = 2 * gridSizeSquared + column * gridSize + value;
                    currentRow.fill(columnCellIndex);
                    currentRow.fill(columnRowIndex);
                    currentRow.fill(columnColIndex);

                    if (row == column) { // on the diagonal running left -> right
                        currentRow.fill(3 * gridSizeSquared + value);
                    }
                    if (row == gridSize - column - 1) { // on the diagonal running right -> left
                        currentRow.fill(3 * gridSizeSquared + gridSize + value);
                    }
                    grid[currentRowIndex] = currentRow;
                    currentRowIndex++;
                }
            }
        }
    }

    public int getNumRows() {
        return numRows;
    }

    public Boolean getValAt(int[] position) {
        return grid[position[0]].getValAt(position[1]);
    }

    public int getNumColumns() {
        return numColumns;
    }

    public int getValue(int rowNumber) {
        return grid[rowNumber].getValue();
    }

    public int[] getLocation(int rowNumber) {
        return grid[rowNumber].getLocation();
    }

    /* For debugging purposes
    public void print() {
    for (Row row : grid) {
            row.printRow();
            System.out.println();
        }
    }
     */

}
