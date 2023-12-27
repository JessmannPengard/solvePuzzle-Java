package puzzle;

import java.util.Arrays;

/**
 * Represents a puzzle piece.
 *
 * @author jessmann
 */
public class PuzzlePiece {

    private int id;
    private int faces[];

    /**
     * Constructor for PuzzlePiece.
     *
     * @param id The unique identifier of the puzzle piece.
     * @param faces An array representing the faces of the puzzle piece.
     */
    public PuzzlePiece(int id, int[] faces) {
        this.id = id;
        this.faces = faces;
    }

    /**
     * Gets the unique identifier of the puzzle piece.
     *
     * @return The unique identifier.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets the indentifier of the puzzle piece.
     *
     * @param id The unique identifier of the puzzle piece.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the faces of the puzzle piece.
     *
     * @return An array representing the faces.
     */
    public int[] getFaces() {
        return this.faces;
    }

    /**
     * Sets the faces of the puzzle piece.
     *
     * @param faces An array representing the faces.
     */
    public void setFaces(int[] faces) {
        this.faces = faces;
    }

    /**
     * Return a String representing the piece.
     *
     * @return A String: "Piece: id=1, faces=[1,2,3,4]".
     */
    @Override
    public String toString() {
        return "Piece: " + "id=" + id + ", faces=" + Arrays.toString(faces);
    }

    /**
     * Rotates 90º the puzzle piece.
     */
    public void rotate() {
        int currentFaces[] = this.getFaces();
        int[] rotatedFaces = new int[4];

        for (int i = 0; i < 4; i++) {
            int newIndex = (i + 1) % 4;
            rotatedFaces[newIndex] = currentFaces[i];
        }

        this.setFaces(rotatedFaces);
    }

    /**
     * Rotates the puzzle piece to a specified corner.
     *
     * @param position The corner to which the piece should be rotated.
     */
    public void rotateToCorner(String position) {
        int[] targetPattern = new int[4];

        switch (position) {
            case "top-left" ->
                targetPattern = new int[]{0, 0, -1, -1};
            case "top-right" ->
                targetPattern = new int[]{-1, 0, 0, -1};
            case "bottom-right" ->
                targetPattern = new int[]{-1, -1, 0, 0};
            case "bottom-left" ->
                targetPattern = new int[]{0, -1, -1, 0};
            case "left" ->
                targetPattern = new int[]{0, 0, -1, 0};
            case "right" ->
                targetPattern = new int[]{-1, 0, 0, 0};
            case "top" ->
                targetPattern = new int[]{0, 0, 0, -1};
            case "bottom" ->
                targetPattern = new int[]{0, -1, 0, 0};
            default ->
                throw new AssertionError();
        }

        while (!matchesPattern(this.getFaces(), targetPattern)) {
            rotate();
        }
    }

    /**
     * Rotates the puzzle piece to a specified edge.
     *
     * @param position The edge to which the piece should be rotated.
     */
    public void rotateToEdge(String position) {
        int[] targetPattern = new int[4];

        switch (position) {
            case "left" ->
                targetPattern = new int[]{0, -1, -1, -1};
            case "top" ->
                targetPattern = new int[]{-1, 0, -1, -1};
            case "right" ->
                targetPattern = new int[]{-1, -1, 0, -1};
            case "bottom" ->
                targetPattern = new int[]{-1, -1, -1, 0};
            default ->
                throw new AssertionError();
        }

        while (!matchesPattern(this.getFaces(), targetPattern)) {
            rotate();
        }
    }

    /**
     * Checks if the faces match the given pattern.
     *
     * @param faces An array representing the faces.
     * @param pattern An array representing the pattern.
     *
     * @return True if the faces matches the pattern, false otherwise.
     */
    private boolean matchesPattern(int[] faces, int[] pattern) {
        for (int i = 0; i < 4; i++) {
            if (pattern[i] != -1 && faces[i] != pattern[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @return True if the piece is a corner, false otherwise.
     */
    public boolean isCorner() {
        int numBorders = countBorders();
        return numBorders == 2;
    }

    /**
     *
     * @return True if the piece is a corner in one-dimensional puzzle, false
     * otherwise.
     */
    public boolean isLinearCorner() {
        int numBorders = countBorders();
        return numBorders == 3;
    }

    /**
     *
     * @return True if the piece is an edge, false otherwise.
     */
    public boolean isEdge() {
        int numBorders = countBorders();
        return numBorders == 1;
    }

    /**
     *
     * @return True if the piece is an edge in one-dimensional puzzle, false
     * otherwise.
     */
    public boolean isDoubleEdge() {
        int numBorders = countBorders();
        return numBorders == 2;
    }

    /**
     *
     * @return True if the piece is interior, false otherwise.
     */
    public boolean isInterior() {
        int numBorders = countBorders();
        return numBorders == 0;
    }

    /**
     *
     * @return the number of borders in the piece.
     */
    private int countBorders() {
        int numBorders = 0;
        for (int face : this.faces) {
            if (face == 0) {
                numBorders++;
            }
        }
        return numBorders;
    }

}
