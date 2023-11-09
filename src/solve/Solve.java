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
            PuzzleSolver solver = new PuzzleSolver(puzzle);
            solver.solve();
            String solutions = solver.getSolutionsAsString();
            System.out.println(solutions);

        }
    }
}
