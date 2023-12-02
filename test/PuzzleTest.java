
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import puzzle.Puzzle;
import puzzle.PuzzlePiece;

public class PuzzleTest {

    @Test
    public void testConstructorAndGetters() {
        Puzzle puzzle = new Puzzle(1, 2, null);
        puzzle.setCols(3);
        puzzle.setRows(2);

        assertEquals(3, puzzle.getCols());
        assertEquals(2, puzzle.getRows());
        assertEquals(0, puzzle.getPieces().length);
    }

    @Test
    public void testSetters() {
        Puzzle puzzle = new Puzzle(1, 2, null);
        PuzzlePiece piece = new PuzzlePiece(1, new int[]{1, 2, 3, 4});
        puzzle.setCols(3);
        puzzle.setRows(3);
        puzzle.setPieces(Arrays.asList(piece).toArray(new PuzzlePiece[0]));

        assertEquals(3, puzzle.getCols());
        assertEquals(3, puzzle.getRows());
        assertEquals(Arrays.asList(piece), Arrays.asList(puzzle.getPieces()));
    }

    @Test
    public void testIsOneDimensional() {
        Puzzle puzzle1DX = new Puzzle(1, 3, null);
        Puzzle puzzle1DY = new Puzzle(3, 1, null);
        Puzzle puzzle2D = new Puzzle(4, 4, null);

        assertTrue(puzzle1DX.isOneDimensional());
        assertTrue(puzzle1DY.isOneDimensional());
        assertFalse(puzzle2D.isOneDimensional());
    }
}
