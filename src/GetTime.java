import ds.SudokuProblem;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


/**
 * Description:
 * Given:
 * Algorithm:
 *
 * @author Andi Gu
 * @course ICS4U
 * @since 3/25/2016
 */
public class GetTime {
    public static void main(String[] args) throws IOException {
        for (int i = 0; i <= 10; i ++) {
            System.out.println("Iteration: " + (i + 1));
            System.out.println("____________");
            Path path = FileSystems.getDefault().getPath("src", "run_times.txt");
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            for (int j = 0; j <= 50; j++) {
                System.out.println("Grid size: " + j);
                SudokuProblem problem = new SudokuProblem(j);
                problem.solve();
                lines.set(j, lines.get(j) + " " + (problem.getSpeed()));
            }
            if(i != 0) { // on first iteration speeds are slower because disk has to accelerate
                Files.write(path, lines, StandardCharsets.UTF_8);
            }
        }
    }
}
