package ds;

import ds.matrix.Matrix;
import ds.nodes.ColumnHeadNode;
import ds.nodes.Node;

/**
 * Description:
 * Given:
 * Algorithm:
 *
 * @author Andi Gu
 * @course ICS4U
 * @since 3/25/2016
 */
public class SudokuProblem {
    private SolutionSet solutionSet;
    private int size;
    private Matrix matrix;
    private ToroidalList grid;
    private long startTime;
    private long speed; // time it took to solve in milliseconds

    public SudokuProblem(int size) {
        startTime = System.currentTimeMillis();
        this.size = size;
        solutionSet = new SolutionSet(size);
        matrix = new Matrix(size);
        grid = new ToroidalList(matrix);
    }

    public boolean solve() {
        findSolution(0, grid);
        long finishTime = System.currentTimeMillis();
        speed = finishTime - startTime;
        return solutionSet.isFinished();
    }

    public void printSolution() {
        if (solve()) {
            solutionSet.print(matrix, size);
        }
        else {
            System.out.println("No solution!");
        }
    }

    private void findSolution(int k, ToroidalList grid) {
        if (grid.getRoot().getRight() == grid.getRoot()) {
            solutionSet.finish();
            return;
        }
        ColumnHeadNode currentColumn = grid.chooseNextColumn();

        grid.cover(currentColumn);
        Node currentRow = currentColumn.getDown();
        while (currentRow != currentColumn && !solutionSet.isFinished()) {
            solutionSet.insert(k, currentRow);
            Node currentNode = currentRow.getRight();
            while (currentNode != currentRow) {
                grid.cover(currentNode.getHead());
                currentNode = currentNode.getRight();
            }
            findSolution(k + 1, grid);
            solutionSet.remove(k);
            currentNode = currentRow.getLeft();
            while (currentNode != currentRow) {
                grid.uncover(currentNode.getHead());
                currentNode = currentNode.getLeft();
            }
            currentRow = currentRow.getDown();
        }
        grid.uncover(currentColumn);
    }

    public long getSpeed() {
        return speed;
    }
}
