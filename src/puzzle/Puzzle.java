package puzzle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author jessmann
 */
public class Puzzle {

    private int cols;
    private int rows;
    private PuzzlePiece pieces[];

    public Puzzle(int cols, int rows, PuzzlePiece[] pieces) {
        this.cols = cols;
        this.rows = rows;
        this.pieces = pieces;
    }

    public int getCols() {
        return this.cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getRows() {
        return this.rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public PuzzlePiece[] getPieces() {
        return this.pieces != null ? this.pieces : new PuzzlePiece[0];
    }

    public void setPieces(PuzzlePiece[] pieces) {
        this.pieces = pieces;
    }

    @Override
    public String toString() {
        return "Columns: " + this.cols + " - " + "Rows: " + this.rows
                + "\nPieces:" + showPieces(this.pieces);
    }

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

    public boolean isOneDimensional() {
        return (this.getRows() == 1 || this.getCols() == 1);
    }

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

    private static void handleError(String message) {
        System.out.println("Error: " + message);
    }

}
