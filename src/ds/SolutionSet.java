package ds;

import ds.matrix.Matrix;
import ds.nodes.Node;

class SolutionSet {
    private Node[] solution;
    private boolean finished;

    SolutionSet(int grid_size) {
        solution = new Node[(int) Math.pow(grid_size, 2)];
        finished = false;
    }

    void finish() {
        finished = true;
    }

    boolean isFinished() {
        return finished;
    }

    void insert(int index, Node node) {
        solution[index] = node;
    }

    void remove(int index) {
        if (!finished) {
            solution[index] = null;
        }
    }

    void print(Matrix matrix, int grid_size) {
        int[][] grid = new int[grid_size][grid_size];
        for (Node node : solution) {
            int[] location = matrix.getLocation(node.getLocation()[0]);
            grid[location[0]][location[1]] = matrix.getValue(node.getLocation()[0]);
        }
        for (int[] row : grid) {
            for (int value : row) {
                if(value >= 10) {
                    System.out.print(value + " ");
                }
                else {
                    System.out.print(value + "  ");
                }
            }
            System.out.println();
        }
    }
}
