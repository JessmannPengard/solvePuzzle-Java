package puzzle;

import java.util.Arrays;

/**
 *
 * @author jessmann
 */
public class PuzzlePiece {

    private int id;
    private int faces[];

    public PuzzlePiece(int id, int[] faces) {
        this.id = id;
        this.faces = faces;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int[] getFaces() {
        return this.faces;
    }

    public void setFaces(int[] faces) {
        this.faces = faces;
    }

    @Override
    public String toString() {
        return "Piece: " + "id=" + id + ", faces=" + Arrays.toString(faces);
    }

    public void rotate() {
        int currentFaces[] = this.getFaces();
        int[] rotatedFaces = new int[4];

        for (int i = 0; i < 4; i++) {
            int newIndex = (i + 1) % 4;
            rotatedFaces[newIndex] = currentFaces[i];
        }

        this.setFaces(rotatedFaces);
    }

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
            default ->
                throw new AssertionError();
        }

        while (!matchesPattern(this.getFaces(), targetPattern)) {
            rotate();
        }
    }

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

    private boolean matchesPattern(int[] faces, int[] pattern) {
        for (int i = 0; i < 4; i++) {
            if (pattern[i] != -1 && faces[i] != pattern[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean isCorner() {
        int numBorders = countBorders();
        return numBorders == 2;
    }

    public boolean isEdge() {
        int numBorders = countBorders();
        return numBorders == 1;
    }

    public boolean isInterior() {
        int numBorders = countBorders();
        return numBorders == 0;
    }

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
