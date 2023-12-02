
import org.junit.Test;
import static org.junit.Assert.*;
import puzzle.PuzzlePiece;

public class PuzzlePieceTest {

    @Test
    public void testConstructorAndGetters() {
        int[] faces = {1, 2, 3, 4};
        PuzzlePiece piece = new PuzzlePiece(1, faces);

        assertEquals(1, piece.getId());
        assertArrayEquals(faces, piece.getFaces());
    }

    @Test
    public void testSetters() {
        int[] faces = {1, 2, 3, 4};
        PuzzlePiece piece = new PuzzlePiece(1, faces);

        piece.setId(2);
        piece.setFaces(new int[]{5, 6, 7, 8});

        assertEquals(2, piece.getId());
        assertArrayEquals(new int[]{5, 6, 7, 8}, piece.getFaces());
    }

    @Test
    public void testRotate() {
        int[] initialFaces = {1, 2, 3, 4};
        PuzzlePiece piece = new PuzzlePiece(1, initialFaces);

        piece.rotate();

        int[] expectedFaces = {4, 1, 2, 3};
        assertArrayEquals(expectedFaces, piece.getFaces());
    }

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

    @Test
    public void testRotateToEdge() {
    }

    @Test
    public void testIsCorner() {
    }

    @Test
    public void testIsLinearCorner() {
    }

    @Test
    public void testIsEdge() {
    }

    @Test
    public void testIsDoubleEdge() {
    }

    @Test
    public void testIsInterior() {
    }

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
