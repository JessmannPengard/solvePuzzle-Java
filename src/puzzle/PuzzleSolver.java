package puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author jessmann
 */
public class PuzzleSolver {

    private final Puzzle puzzle;
    private final List<PuzzlePiece[][]> solutions = new ArrayList<>();

    public PuzzleSolver(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    public String getSolutionsAsString() {
        StringBuilder result = new StringBuilder();
        result.append("\nSolution(s):\n");
        for (PuzzlePiece[][] solution : solutions) {
            for (PuzzlePiece[] row : solution) {
                for (PuzzlePiece piece : row) {
                    result.append(piece != null ? String.format("%-4d", piece.getId()) : "null ");
                }
                result.append("\n");
            }
            result.append("\n");
        }
        return result.toString();
    }

    public void solve() {
        solvePuzzle(0, 0, new PuzzlePiece[puzzle.getRows()][puzzle.getCols()], new ArrayList<>());
    }

    private void solvePuzzle(int row, int col, PuzzlePiece[][] currentSolution, List<PuzzlePiece> usedPieces) {
        int numPieces = this.puzzle.getPieces().length;

        // Calculate next row and column
        int nextRow = row;
        int nextCol = col + 1;
        if (nextCol == this.puzzle.getCols()) {
            nextRow++;
            nextCol = 0;
        }

        // Base case: If we've placed all the pieces, we found a solution
        if (usedPieces.size() == this.puzzle.getPieces().length) {
            // Save the current solution to the solutions array
            saveSolution(currentSolution);
            return;
        }

        PuzzlePiece[] pieces = this.puzzle.getPieces();

        if (row == 0 && col == 0) {
            // Find fixed top left corner to avoid rotated solutions
            PuzzlePiece fixedCornerPiece = findFixedCornerPiece(pieces);

            // Add to used pieces
            usedPieces.add(fixedCornerPiece);

            // Place the piece in the current solution
            currentSolution[row][col] = fixedCornerPiece;

            // Recursively try to solve the puzzle with the updated solution
            solvePuzzle(nextRow, nextCol, currentSolution, usedPieces);
        } else {
            // Iterate through all pieces and try placing them
            for (int i = 0; i < numPieces; i++) {
                PuzzlePiece currentPiece = pieces[i];

                if (!usedPieces.contains(currentPiece) && tryPiece(row, col, currentPiece, currentSolution)) {
                    // Add to used pieces
                    usedPieces.add(currentPiece);

                    // Place the piece in the current solution
                    currentSolution[row][col] = currentPiece;

                    // Recursively try to solve the puzzle with the updated solution
                    solvePuzzle(nextRow, nextCol, currentSolution, usedPieces);

                    // Backtrack: Undo the changes made for backtracking
                    currentSolution[row][col] = null;
                    usedPieces.remove(usedPieces.size() - 1);

                }
            }
        }
    }

    private PuzzlePiece findFixedCornerPiece(PuzzlePiece[] pieces) {
        for (PuzzlePiece piece : pieces) {
            if (this.puzzle.isOneDimensional()) {
                if (piece.isLinearCorner()) {
                    if (this.puzzle.getRows() == 1) {
                        piece.rotateToCorner("left");
                    } else {
                        piece.rotateToCorner("top");
                    }
                    return piece;
                }
            } else {
                if (piece.isCorner()) {
                    piece.rotateToCorner("top-left");
                    return piece;
                }
            }
        }
        return null;
    }

    private boolean tryPiece(int row, int col, PuzzlePiece piece, PuzzlePiece[][] solution) {
        int width = this.puzzle.getCols();
        int height = this.puzzle.getRows();

        PuzzlePiece topPiece = (row > 0) ? solution[row - 1][col] : null;
        PuzzlePiece leftPiece = (col > 0) ? solution[row][col - 1] : null;

        if (this.puzzle.isOneDimensional()) {
            // One-dimensional puzzle
            if (height == 1) {
                // X linear
                if (col == 0) {
                    // Left Corner
                    if (piece.isLinearCorner()) {
                        piece.rotateToCorner("left");
                        return true;
                    }
                } else if (col < width - 1) {
                    // Interior X linear
                    if (piece.isDoubleEdge()) {
                        if (leftPiece.getFaces()[2] == piece.getFaces()[0]) {
                            return true;
                        }
                        for (int i = 0; i < 3; i++) {
                            piece.rotate();
                            if (leftPiece.getFaces()[2] == piece.getFaces()[0]) {
                                return true;
                            }
                        }
                    }
                } else {
                    // Right Corner
                    if (piece.isLinearCorner()) {
                        piece.rotateToCorner("right");
                        return true;
                    }
                }
            } else {
                // Y linear
                if (row == 0) {
                    // Top corner
                    if (piece.isLinearCorner()) {
                        piece.rotateToCorner("top");
                        return true;
                    }
                } else if (row < height - 1) {
                    // Interior Y linear
                    if (piece.isDoubleEdge()) {
                        if (topPiece.getFaces()[3] == piece.getFaces()[1]) {
                            return true;
                        }
                        for (int i = 0; i < 3; i++) {
                            piece.rotate();
                            if (topPiece.getFaces()[3] == piece.getFaces()[1]) {
                                return true;
                            }
                        }
                    }
                } else {
                    // Bottom corner
                    if (piece.isLinearCorner()) {
                        piece.rotateToCorner("bottom");
                        return true;
                    }
                }
            }
        } else {
            // Square or rectangular puzzle
            if (row == 0) {
                // First row
                if (col == 0) {
                    // Top Left Corner
                    if (piece.isCorner()) {
                        piece.rotateToCorner("top-left");
                        return true;
                    }
                } else if (col == width - 1) {
                    // Top Right Corner
                    if (piece.isCorner()) {
                        piece.rotateToCorner("top-right");
                        if (leftPiece.getFaces()[2] == piece.getFaces()[0]) {
                            return true;
                        }
                    }
                } else {
                    // Top Edge
                    if (piece.isEdge()) {
                        piece.rotateToEdge("top");
                        if (leftPiece.getFaces()[2] == piece.getFaces()[0]) {
                            return true;
                        }
                    }
                }
            } else if (row == height - 1) {
                // Last row
                if (col == 0) {
                    // Bottom Left Corner
                    if (piece.isCorner()) {
                        piece.rotateToCorner("bottom-left");
                        if (topPiece.getFaces()[3] == piece.getFaces()[1]) {
                            return true;
                        }
                    }
                } else if (col == width - 1) {
                    // Bottom Right Corner
                    if (piece.isCorner()) {
                        piece.rotateToCorner("bottom-right");
                        if (topPiece.getFaces()[3] == piece.getFaces()[1]
                                && leftPiece.getFaces()[2] == piece.getFaces()[0]) {
                            return true;
                        }
                    }
                } else {
                    // Bottom Edge
                    if (piece.isEdge()) {
                        piece.rotateToEdge("bottom");
                        if (topPiece.getFaces()[3] == piece.getFaces()[1]
                                && leftPiece.getFaces()[2] == piece.getFaces()[0]) {
                            return true;
                        }
                    }
                }
            } else {
                // Intermediate rows
                if (col == 0) {
                    // Left Edge
                    if (piece.isEdge()) {
                        piece.rotateToEdge("left");
                        if (topPiece.getFaces()[3] == piece.getFaces()[1]) {
                            return true;
                        }
                    }
                } else if (col == width - 1) {
                    // Right Edge
                    if (piece.isEdge()) {
                        piece.rotateToEdge("right");
                        if (topPiece.getFaces()[3] == piece.getFaces()[1]
                                && leftPiece.getFaces()[2] == piece.getFaces()[0]) {
                            return true;
                        }
                    }
                } else {
                    // Interior
                    if (piece.isInterior()) {
                        if (topPiece.getFaces()[3] == piece.getFaces()[1]
                                && leftPiece.getFaces()[2] == piece.getFaces()[0]) {
                            return true;
                        }
                        for (int i = 0; i < 3; i++) {
                            piece.rotate();
                            if (topPiece.getFaces()[3] == piece.getFaces()[1]
                                    && leftPiece.getFaces()[2] == piece.getFaces()[0]) {
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    private void saveSolution(PuzzlePiece[][] currentSolution) {
        int numRows = currentSolution.length;
        int numCols = currentSolution[0].length;

        PuzzlePiece[][] newSolution = new PuzzlePiece[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            newSolution[i] = Arrays.copyOf(currentSolution[i], numCols);
        }

        this.solutions.add(newSolution);
    }

}
