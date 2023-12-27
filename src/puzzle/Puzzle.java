package puzzle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Represents a puzzle.
 *
 * @author jessmann
 */
public class Puzzle {

    private int cols;
    private int rows;
    private PuzzlePiece pieces[];

    /**
     * Constructor for Puzzle.
     *
     * @param cols The number of columns.
     * @param rows The number of rows.
     * @param pieces An array of puzzle pieces.
     */
    public Puzzle(int cols, int rows, PuzzlePiece[] pieces) {
        this.cols = cols;
        this.rows = rows;
        this.pieces = pieces;
    }

    /**
     * Gets the number of columns in the puzzle.
     *
     * @return The number of columns.
     */
    public int getCols() {
        return this.cols;
    }

    /**
     * Sets the number of columns in the puzzle.
     *
     * @param cols The number of columns.
     */
    public void setCols(int cols) {
        this.cols = cols;
    }

    /**
     * Gets the number of rows in the puzzle.
     *
     * @return The number of rows.
     */
    public int getRows() {
        return this.rows;
    }

    /**
     * Sets the number of rows in the puzzle.
     *
     * @param rows The number of columns.
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * Gets an array of puzzle pieces.
     *
     * @return pieces.
     */
    public PuzzlePiece[] getPieces() {
        return this.pieces != null ? this.pieces : new PuzzlePiece[0];
    }

    /**
     * Sets the pieces of the puzzle.
     *
     * @param pieces An array of puzzle pieces.
     */
    public void setPieces(PuzzlePiece[] pieces) {
        this.pieces = pieces;
    }

    /**
     * Return a String representing the puzzle.
     *
     * @return A String: "Columns: 4 - Rows: 4 Pieces: [...]
     */
    @Override
    public String toString() {
        return "Columns: " + this.cols + " - " + "Rows: " + this.rows
                + "\nPieces:" + showPieces(this.pieces);
    }

    /**
     * Return a String representing the pieces.
     *
     * @return A String: [...]
     */
    private String showPieces(PuzzlePiece[] pieces) {
        StringBuilder sb = new StringBuilder("");
        for (PuzzlePiece piece : pieces) {
            sb.append("\n");
            for (int face : piece.getFaces()) {
                sb.append(face).append(" ");
            }
            sb.delete(sb.length() - 1, sb.length());
        }
        return sb.toString();
    }

    /**
     * Checks if the puzzle has one or two dimensions.
     *
     * @return True if is one-dimensional, false otherwise.
     */
    public boolean isOneDimensional() {
        return (this.getRows() == 1 || this.getCols() == 1);
    }

    /**
     * Loads a puzzle from a file.
     *
     * @param fileName The name of the file containing the puzzle data.
     * @return A Puzzle object representing the loaded puzzle, or null if an
     * error occurs.
     */
    public static Puzzle loadPuzzle(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String firstRow = br.readLine();
            String[] dimensions = firstRow.split(" ");
            int cols = Integer.parseInt(dimensions[0]);
            int rows = Integer.parseInt(dimensions[1]);

            PuzzlePiece[] pieces = new PuzzlePiece[rows * cols];

            int numPieces = cols * rows;
            for (int i = 0; i < numPieces; i++) {
                String line = br.readLine();
                String[] faceValues = line.split(" ");

                if (faceValues.length != 4) {
                    handleError("Invalid piece format.");
                    return null;
                }

                int id = i + 1;
                int[] faces = Arrays.stream(faceValues).mapToInt(Integer::parseInt).toArray();

                PuzzlePiece piece = new PuzzlePiece(id, faces);
                pieces[i] = piece;
            }

            if (numPieces != pieces.length) {
                handleError("The number of pieces does not fit puzzle dimensions.");
                return null;
            }

            return new Puzzle(cols, rows, pieces);

        } catch (IOException e) {
            handleError("Reading file: " + e.getMessage());
            return null;
        }
    }

    /**
     * Handles errors by printing an error message to the console.
     *
     * @param message The error message.
     */
    private static void handleError(String message) {
        System.out.println("Error: " + message);
    }

}
