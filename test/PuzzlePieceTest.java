
import org.junit.Test;
import static org.junit.Assert.*;
import puzzle.PuzzlePiece;

/**
 * Test class for the PuzzlePiece class. Focuses on methods related to the
 * creation and manipulation of PuzzlePiece instances.
 *
 * @author jessmann
 */
public class PuzzlePieceTest {

    /**
     * Verifies that the constructor and getters work correctly when setting the
     * id and faces of a puzzle piece.
     */
    @Test
    public void testConstructorAndGetters() {
        int[] faces = {1, 2, 3, 4};
        PuzzlePiece piece = new PuzzlePiece(1, faces);

        assertEquals(1, piece.getId());
        assertArrayEquals(faces, piece.getFaces());
    }

    /**
     * Verifies that the setter methods correctly set the id and faces of a
     * puzzle piece.
     */
    @Test
    public void testSetters() {
        int[] faces = {1, 2, 3, 4};
        PuzzlePiece piece = new PuzzlePiece(1, faces);

        piece.setId(2);
        piece.setFaces(new int[]{5, 6, 7, 8});

        assertEquals(2, piece.getId());
        assertArrayEquals(new int[]{5, 6, 7, 8}, piece.getFaces());
    }

    /**
     * Verifies that the rotate method correctly rotates the faces of a puzzle
     * piece.
     */
    @Test
    public void testRotate() {
        int[] initialFaces = {1, 2, 3, 4};
        PuzzlePiece piece = new PuzzlePiece(1, initialFaces);

        piece.rotate();

        int[] expectedFaces = {4, 1, 2, 3};
        assertArrayEquals(expectedFaces, piece.getFaces());
    }

    /**
     * Verifies that the rotateToCorner method correctly rotates the piece to
     * the specified corner.
     */
    @Test
    public void testRotateToCorner() {
        int[] topLeftPattern = {0, 0, -1, -1};
        int[] testFaces = {1, 2, 0, 0};
        PuzzlePiece piece = new PuzzlePiece(1, testFaces);
        piece.rotateToCorner("top-left");
        assertTrue(checkPattern(piece, topLeftPattern));

        int[] topRightPattern = {-1, 0, 0, -1};
        testFaces = new int[]{1, 2, 0, 0};
        piece = new PuzzlePiece(1, testFaces);
        piece.rotateToCorner("top-right");
        assertTrue(checkPattern(piece, topRightPattern));

        int[] bottomLeftPattern = {0, -1, -1, 0};
        testFaces = new int[]{1, 2, 0, 0};
        piece = new PuzzlePiece(1, testFaces);
        piece.rotateToCorner("bottom-left");
        assertTrue(checkPattern(piece, bottomLeftPattern));

        int[] bottomRightPattern = {-1, -1, 0, 0};
        testFaces = new int[]{0, 1, 2, 0};
        piece = new PuzzlePiece(1, testFaces);
        piece.rotateToCorner("bottom-right");
        assertTrue(checkPattern(piece, bottomRightPattern));

        int[] topPattern = {0, 0, 0, -1};
        testFaces = new int[]{1, 0, 0, 0};
        piece = new PuzzlePiece(1, testFaces);
        piece.rotateToCorner("top");
        assertTrue(checkPattern(piece, topPattern));

        int[] bottomPattern = {0, -1, 0, 0};
        testFaces = new int[]{1, 0, 0, 0};
        piece = new PuzzlePiece(1, testFaces);
        piece.rotateToCorner("bottom");
        assertTrue(checkPattern(piece, bottomPattern));

        int[] leftPattern = {0, 0, -1, 0};
        testFaces = new int[]{1, 0, 0, 0};
        piece = new PuzzlePiece(1, testFaces);
        piece.rotateToCorner("left");
        assertTrue(checkPattern(piece, leftPattern));

        int[] rightPattern = {-1, 0, 0, 0};
        testFaces = new int[]{0, 1, 0, 0};
        piece = new PuzzlePiece(1, testFaces);
        piece.rotateToCorner("right");
        assertTrue(checkPattern(piece, rightPattern));
    }

    /**
     * Verifies that the rotateToEdge method correctly rotates the piece to the
     * specified edge.
     */
    @Test
    public void testRotateToEdge() {
        int[] leftPattern = {0, -1, -1, -1};
        int[] testFaces = {1, 2, 3, 0};
        PuzzlePiece piece = new PuzzlePiece(1, testFaces);
        piece.rotateToEdge("left");
        assertTrue(checkPattern(piece, leftPattern));

        int[] rightPattern = {-1, -1, 0, -1};
        testFaces = new int[]{1, 2, 3, 0};
        piece = new PuzzlePiece(1, testFaces);
        piece.rotateToEdge("right");
        assertTrue(checkPattern(piece, rightPattern));

        int[] topPattern = {-1, 0, -1, -1};
        testFaces = new int[]{1, 2, 3, 0};
        piece = new PuzzlePiece(1, testFaces);
        piece.rotateToEdge("top");
        assertTrue(checkPattern(piece, topPattern));

        int[] bottomPattern = {-1, -1, -1, 0};
        testFaces = new int[]{1, 0, 2, 3};
        piece = new PuzzlePiece(1, testFaces);
        piece.rotateToEdge("bottom");
        assertTrue(checkPattern(piece, bottomPattern));
    }

    /**
     * Verifies that the isCorner method correctly identifies whether a piece is
     * a corner or not.
     */
    @Test
    public void testIsCorner() {
        int[] testFaces = {0, 0, 1, 2};
        PuzzlePiece piece = new PuzzlePiece(1, testFaces);
        assertTrue(piece.isCorner());

        testFaces = new int[]{0, 1, 2, 3};
        piece = new PuzzlePiece(1, testFaces);
        assertFalse(piece.isCorner());

        testFaces = new int[]{1, 2, 3, 4};
        piece = new PuzzlePiece(1, testFaces);
        assertFalse(piece.isCorner());

        testFaces = new int[]{0, 0, 0, 1};
        piece = new PuzzlePiece(1, testFaces);
        assertFalse(piece.isCorner());
    }

    /**
     * Verifies that the isLinearCorner method correctly identifies whether a
     * piece is a linear corner or not.
     */
    @Test
    public void testIsLinearCorner() {
        int[] testFaces = {0, 1, 0, 0};
        PuzzlePiece piece = new PuzzlePiece(1, testFaces);
        assertTrue(piece.isLinearCorner());

        testFaces = new int[]{0, 1, 2, 3};
        piece = new PuzzlePiece(1, testFaces);
        assertFalse(piece.isLinearCorner());

        testFaces = new int[]{1, 2, 3, 4};
        piece = new PuzzlePiece(1, testFaces);
        assertFalse(piece.isLinearCorner());

        testFaces = new int[]{0, 0, 1, 2};
        piece = new PuzzlePiece(1, testFaces);
        assertFalse(piece.isLinearCorner());
    }

    /**
     * Verifies that the isEdge method correctly identifies whether a piece is
     * an edge or not.
     */
    @Test
    public void testIsEdge() {
        int[] testFaces = {0, 1, 2, 3};
        PuzzlePiece piece = new PuzzlePiece(1, testFaces);
        assertTrue(piece.isEdge());

        testFaces = new int[]{0, 1, 0, 0};
        piece = new PuzzlePiece(1, testFaces);
        assertFalse(piece.isEdge());

        testFaces = new int[]{1, 2, 3, 4};
        piece = new PuzzlePiece(1, testFaces);
        assertFalse(piece.isEdge());

        testFaces = new int[]{0, 0, 1, 2};
        piece = new PuzzlePiece(1, testFaces);
        assertFalse(piece.isEdge());
    }

    /**
     * Verifies that the isDoubleEdge method correctly identifies whether a
     * piece is a double edge or not.
     */
    @Test
    public void testIsDoubleEdge() {
        int[] testFaces = {0, 1, 0, 2};
        PuzzlePiece piece = new PuzzlePiece(1, testFaces);
        assertTrue(piece.isDoubleEdge());

        testFaces = new int[]{0, 1, 0, 0};
        piece = new PuzzlePiece(1, testFaces);
        assertFalse(piece.isDoubleEdge());

        testFaces = new int[]{1, 2, 3, 4};
        piece = new PuzzlePiece(1, testFaces);
        assertFalse(piece.isDoubleEdge());

        testFaces = new int[]{0, 1, 2, 3};
        piece = new PuzzlePiece(1, testFaces);
        assertFalse(piece.isDoubleEdge());
    }

    /**
     * Verifies that the isInterior method correctly identifies whether a piece
     * is an interior piece or not.
     */
    @Test
    public void testIsInterior() {
        int[] testFaces = {1, 2, 3, 4};
        PuzzlePiece piece = new PuzzlePiece(1, testFaces);
        assertTrue(piece.isInterior());

        testFaces = new int[]{0, 1, 0, 0};
        piece = new PuzzlePiece(1, testFaces);
        assertFalse(piece.isInterior());

        testFaces = new int[]{1, 0, 3, 0};
        piece = new PuzzlePiece(1, testFaces);
        assertFalse(piece.isInterior());

        testFaces = new int[]{0, 1, 2, 3};
        piece = new PuzzlePiece(1, testFaces);
        assertFalse(piece.isInterior());
    }

    /**
     * Helper method to check if the pattern of a puzzle piece matches the
     * target pattern.
     */
    private boolean checkPattern(PuzzlePiece piece, int[] targetPattern) {
        try {
            java.lang.reflect.Method method = PuzzlePiece.class.getDeclaredMethod("matchesPattern", int[].class, int[].class);
            method.setAccessible(true);
            return (boolean) method.invoke(piece, piece.getFaces(), targetPattern);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
