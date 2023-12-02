# Puzzle Solver

Puzzle Solver is a JAVA-based application that solves puzzles provided as text files. It includes a command-line interface (`solve.jar`). The application is designed to handle puzzles represented by a grid of pieces.

## Features

- Solve puzzles from text files
- Command-line interface
- Unit tests for the Puzzle and PuzzlePiece classes

## Getting Started

### Prerequisites

- Java 19 or later

### Installation

No explicit installation is required for the Puzzle Solver. Simply ensure you have Java installed on your system.


## Usage

### Command-Line interface:

    java -jar solve.jar path/to/puzzle.txt

## File Format

The puzzle file should follow a specific format. The first line of the file specifies the dimensions of the puzzle (width and height), and each subsequent line represents a piece. The numbers on each line represent the faces of the piece. For example:

```console
3 2
1 2 3 4
4 3 2 1
1 2 3 4
4 3 2 1
1 2 3 4
4 3 2 1
```

In this example, the puzzle has a width of 3 and a height of 2. Each of the following lines represents a puzzle piece, with the numbers indicating the faces of the piece.