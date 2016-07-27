import ds.SudokuProblem;

import java.util.Scanner;

public class Solve {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int gridLength = sc.nextInt();
        while (gridLength > 50) {
            System.err.println("Numbers over 50 take up too much memory. Enter another number.");
            gridLength = sc.nextInt();
        }

        SudokuProblem problem = new SudokuProblem(gridLength);
        problem.printSolution();
        System.out.println("Solved in " + problem.getSpeed() + " milliseconds.");
        sc.close();
    }
}
