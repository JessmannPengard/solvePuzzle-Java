package solve;

import puzzle.Puzzle;
import puzzle.PuzzleSolver;

/**
 *
 * @author jessmann
 */
public class Solve {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Ussage: solve [filename]");
            return;
        }

        String fileName = args[0];
        Puzzle puzzle = Puzzle.loadPuzzle(fileName);

        if (puzzle != null) {
            System.out.println(puzzle.toString());
            System.out.println("Solving...");

            PuzzleSolver solver = new PuzzleSolver(puzzle);

            long startTime = System.currentTimeMillis();
            solver.solve();
            long endTime = System.currentTimeMillis();

            long executionTime = endTime - startTime;

            String solutions = solver.getSolutionsAsString();
            System.out.println(solutions);
            System.out.println("Solved in " + executionTime / 1000 + " secs.");

        }
    }
}
