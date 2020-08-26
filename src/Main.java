import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    //Cornelias Sudoku
//    static int[][] sudoku = {
//            {0, 0, 0, 0, 0, 0, 0, 0, 2},
//            {0, 0, 9, 6, 0, 0, 0, 0, 0},
//            {0, 5, 3, 0, 0, 0, 9, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0, 8},
//            {9, 0, 2, 0, 0, 0, 0, 5, 6},
//            {0, 0, 0, 0, 7, 5, 4, 0, 0},
//            {0, 0, 0, 5, 0, 0, 0, 0, 0},
//            {7, 0, 0, 0, 8, 0, 3, 0, 0},
//            {0, 0, 8, 2, 4, 0, 0, 0, 9}
//
//    };

//leeres Sudoku
//    static int[][] sudoku = {
//            {0, 0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0, 0}
//
//    };
//    static int[][] sudoku = {
//            {0, 0, 0, 0, 0, 0, 0, 0, 0},
//            {1, 2, 4, 0, 3, 0, 0, 0, 0},
//            {5, 6, 7, 0, 0, 0, 8, 0, 9},
//            {0, 0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0, 0}
//    };

//    static int[][] sudoku = {
//            {0, 6, 9, 0, 0, 0, 3, 0, 2},
//            {1, 0, 0, 9, 3, 0, 0, 0, 0},
//            {2, 0, 0, 0, 0, 6, 1, 0, 5},
//            {0, 8, 0, 0, 1, 0, 6, 0, 0},
//            {0, 5, 0, 6, 2, 9, 0, 3, 0},
//            {0, 0, 1, 0, 8, 0, 0, 2, 0},
//            {4, 0, 8, 7, 0, 0, 0, 0, 3},
//            {0, 0, 0, 0, 6, 3, 0, 0, 8},
//            {3, 0, 6, 0, 0, 0, 9, 4, 0}};
//Jans Sudoku
    static int[][] sudoku = {
            {0, 0, 9, 7, 0, 0, 0, 0, 3},
            {0, 0, 4, 5, 0, 0, 0, 9, 0},
            {1, 5, 0, 0, 9, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 8, 0, 0, 6, 1},
            {0, 0, 2, 0, 0, 0, 8, 0, 0},
            {0, 6, 0, 2, 0, 4, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 7, 0, 0},
            {7, 9, 0, 0, 0, 0, 0, 5, 0}};


    public static void main(String[] args) {

        //initialize
        Square[][] squares = createSquaresFromInts(sudoku);

        recursiveSolution(squares, 1);
        end(squares, false);
    }

    static void end(Square[][] squares, boolean success) {
        if (success) {
            System.out.println("final solution");
        } else {
            System.out.println("no solution");
        }
        printSudoku(squares);
        System.exit(0);
    }

    static void recursiveSolution(Square[][] squares, int depth){

        while (! isSudokuSolved(squares) && ! hasSudokuMistake(squares)) {
            boolean changed;

            //go through all the blocks, rows and columns and eliminate wrong possibilities
            try {
                //delete numbers that can't be in a square
                changed = deleteDigitsThatAlreadyExistInContainer(squares);

                //commit number to square if no other square can host it
                changed |= setDigitsThatHaveOnlyOnePossibleSquare(squares);

                changed |= deleteDigitsThatMustBeInOtherPlaceInContainer(squares);
            } catch (NoPossibilitiesException noPossibilitiesException) {
                return;
            }


            if (! changed && ! isSudokuSolved(squares)) {
                int minimalX = 0;
                int minimalY = 0;
                int minimalPossibilities = 9;
                for (int y = 0; y < squares.length; y++) {
                    for (int x = 0; x < squares[0].length; x++) {
                        if (squares[y][x].possibilities.size() <= minimalPossibilities && squares[y][x].possibilities.size() > 1) {
                            minimalX = x;
                            minimalY = y;
                            minimalPossibilities = squares[y][x].possibilities.size();
                        }
                    }
                }


                for (int possibilityIndex = 0; possibilityIndex < squares[minimalY][minimalX].possibilities.size(); possibilityIndex++) {
                    Square[][] squaresClone = cloneSquares(squares);
                    System.out.println("Level of guessing: " + depth);
                    System.out.println("The chosen square is square " + minimalY + ":" + minimalX + ". It has " + minimalPossibilities + " possibilities left. ");
                    squaresClone[minimalY][minimalX].solve(squares[minimalY][minimalX].possibilities.get(possibilityIndex));
                    recursiveSolution(squaresClone, depth + 1);
                }
                System.out.println("We made a wrong guess at Level " + (depth-1) + " or before.");
                return;
            }

            printSudoku(squares);
        }
        if (hasSudokuMistake(squares)) {
            return;
        }

        if (isSudokuSolved(squares)) {

            end(squares, true);
        }


        System.out.println("Nora says this will never happen");


    }

    static Square[][] createSquaresFromInts(int[][] sudoku) {
        Square[][] squares = new Square[sudoku.length][sudoku[0].length];
        for (int y = 0; y < sudoku.length; y++) {
            for (int x = 0; x < sudoku[0].length; x++) {
                if (sudoku[y][x] != 0) {
                    squares[y][x] = new Square(y, x, sudoku[y][x]);
                } else {
                    squares[y][x] = new Square(y, x);
                }
            }
        }
        return squares;
    }


    static boolean deleteDigitsThatAlreadyExistInContainer(Square[][] squares) throws NoPossibilitiesException {
        boolean changed = false;
        for (Container container : Container.values()) {
            for (int containerIndex = 0; containerIndex < 9; containerIndex++) {
                List<Integer> existingNumbers = new ArrayList<>();

                doForEachIn(squares, container, containerIndex, (square) -> {
                    if (square.solved()) {
                        existingNumbers.add(square.possibilities.get(0));
                    }
                    return false;
                });
                changed |= doForEachIn(squares, container, containerIndex, (square) -> {
                    return square.delete(existingNumbers);
                });
            }

        }
        return changed;
    }

    static boolean setDigitsThatHaveOnlyOnePossibleSquare(Square[][] squares) throws NoPossibilitiesException {
        boolean changed = false;
        for (Container container : Container.values()) {
            for (int containerIndex = 0; containerIndex < 9; containerIndex++) {
                List<Square>[] squaresContainingNumbersArray = new ArrayList[9];
                for (int digit = 1; digit < 10; digit++) {
                    squaresContainingNumbersArray[digit - 1] = new ArrayList<>();
                    final int finalDigit = digit;
                    doForEachIn(squares, container, containerIndex, (square) -> {
                        if (square.possibilities.contains(finalDigit)) {
                            squaresContainingNumbersArray[finalDigit - 1].add(square);
                        }
                        return false;
                    });
                }
                for (int digit = 1; digit <= squaresContainingNumbersArray.length; digit++) {
                    if (squaresContainingNumbersArray[digit - 1].size() == 1 && ! squaresContainingNumbersArray[digit - 1].get(0).solved()) {
                        changed = true;
                        System.out.print(digit + " can be only in one square in "+container.toString().toLowerCase()+" "+containerIndex+": ");
                        squaresContainingNumbersArray[digit - 1].get(0).solve(digit);
                    }
                }
            }
        }
        return changed;

    }

    // This method makes sure that if there are only 2 or three squares left in a container a
    // that can host a particular number and all of these squares also lie in a different container b
    // all others squares in b no longer have that number as a possibility.
    static boolean deleteDigitsThatMustBeInOtherPlaceInContainer(Square[][] squares) throws NoPossibilitiesException {
        boolean changed = false;
        for (Container container : Container.values()) {
            for (int containerIndex = 0; containerIndex < 9; containerIndex++) {
                List<Square>[] squaresContainingNumbersArray = new ArrayList[9];
                for (int digit = 1; digit < 10; digit++) {
                    squaresContainingNumbersArray[digit - 1] = new ArrayList<>();
                    final int finalDigit = digit;
                    doForEachIn(squares, container, containerIndex, (square) -> {
                        if (square.possibilities.contains(finalDigit)) {
                            squaresContainingNumbersArray[finalDigit - 1].add(square);
                        }
                        return false;
                    });
                }
                for (int digit = 1; digit <= squaresContainingNumbersArray.length; digit++) {
                    if (squaresContainingNumbersArray[digit - 1].size() == 2 || squaresContainingNumbersArray[digit - 1].size() == 3) {
                        boolean numbersInRow = true;
                        boolean numbersInColumn = true;
                        boolean numbersInBlock = true;
                        for (int k = 1; k < squaresContainingNumbersArray[digit - 1].size(); k++) {

                            //in a row
                            numbersInRow = numbersInRow && squaresContainingNumbersArray[digit - 1].get(k).x == squaresContainingNumbersArray[digit - 1].get(k - 1).x;
                            //in a column
                            numbersInColumn = numbersInColumn && squaresContainingNumbersArray[digit - 1].get(k).y == squaresContainingNumbersArray[digit - 1].get(k - 1).y;
                            //in a block
                            numbersInBlock = numbersInBlock &&
                                    ((squaresContainingNumbersArray[digit - 1].get(k).y / 3) == (squaresContainingNumbersArray[digit - 1].get(k - 1).y / 3) &&
                                            (squaresContainingNumbersArray[digit - 1].get(k).x / 3) == (squaresContainingNumbersArray[digit - 1].get(k - 1).x / 3));

                        }

                        if (container == Container.BLOCK && numbersInRow) {
                            final int finalDigit = digit;
                            changed |= doForEachIn(squares, Container.ROW, squaresContainingNumbersArray[digit - 1].get(0).x, (square) -> {
                                return square.delete(Arrays.asList(finalDigit));
                            }, Container.BLOCK, containerIndex);
                        }

                        if (container == Container.BLOCK && numbersInColumn) {
                            final int finalDigit = digit;
                            changed |= doForEachIn(squares, Container.COLUMN, squaresContainingNumbersArray[digit - 1].get(0).y, (square) -> {
                                return square.delete(Arrays.asList(finalDigit));
                            }, Container.BLOCK, containerIndex);
                        }

                        if (container == Container.ROW && numbersInBlock) {
                            final int finalDigit = digit;
                            changed |= doForEachIn(squares, Container.BLOCK, (squaresContainingNumbersArray[digit - 1].get(0).x / 3) * 3 + squaresContainingNumbersArray[digit - 1].get(0).y / 3, (square) -> {
                                return square.delete(Arrays.asList(finalDigit));
                            }, Container.ROW, squaresContainingNumbersArray[digit - 1].get(0).x);
                        }

                        if (container == Container.COLUMN && numbersInBlock) {
                            final int finalDigit = digit;
                            changed |= doForEachIn(squares, Container.BLOCK, (squaresContainingNumbersArray[digit - 1].get(0).x / 3) * 3 + squaresContainingNumbersArray[digit - 1].get(0).y / 3, (square) -> {
                                return square.delete(Arrays.asList(finalDigit));
                            }, Container.COLUMN, squaresContainingNumbersArray[digit - 1].get(0).y);
                        }
                    }


                }
            }

        }
        return changed;
    }

    static boolean doForEachIn(Square[][] squares, Container container, int index, Interface method) throws NoPossibilitiesException {

        return doForEachIn(squares, container, index, method, Container.BLOCK, - 1);
    }

    static boolean doForEachIn(Square[][] squares, Container container, int index, Interface method, Container excludedContainer, int excludedIndex) throws NoPossibilitiesException {
        boolean changed = false;
        switch (container) {
            case BLOCK:
                for (int y = (index / 3) * 3; y < (index / 3) * 3 + 3; y++) {
                    for (int x = (index % 3) * 3; x < (index % 3) * 3 + 3; x++) {

                        if (excludedContainer == Container.ROW && y == excludedIndex) {
                            continue;
                        }
                        if (excludedContainer == Container.COLUMN && x == excludedIndex) {
                            continue;
                        }
                        changed |= method.doSomething(squares[y][x]);
                    }
                }
                break;
            case ROW:
                for (int x = 0; x < squares[0].length; x++) {
                    if (excludedContainer == Container.BLOCK && excludedIndex != - 1 && x / 3 == excludedIndex % 3) {
                        continue;
                    }
                    changed |= method.doSomething(squares[index][x]);
                }
                break;
            case COLUMN:
                for (int y = 0; y < squares.length; y++) {
                    if (excludedContainer == Container.BLOCK && excludedIndex != - 1 && y / 3 == excludedIndex / 3) {
                        continue;
                    }
                    changed |= method.doSomething(squares[y][index]);
                }
                break;
        }
        return changed;

    }

    interface Interface {
        boolean doSomething(Square square) throws NoPossibilitiesException;
    }

    static void printSudoku(Square[][] squares) {
        for (int y = 0; y < 9; y++) {
            if (y % 3 == 0) {
                System.out.println("-------------------------");
            }
            for (int x = 0; x < 9; x++) {
                if (x % 3 == 0) {
                    System.out.print("| ");
                }
                System.out.print((squares[y][x].solved() ? squares[y][x].possibilities.get(0) : " ") + " ");
                if (x == 8) {
                    System.out.print("| ");
                }

            }

            System.out.println();
            if (y == 8) {
                System.out.println("-------------------------");
            }
        }

        System.out.println();
    }

    //returns true iff sudoku is filled with numbers
    static boolean isSudokuSolved(Square[][] squares) {
        boolean isSolved = true;
        for (Container container : Container.values()) {
            for (int containerIndex = 0; containerIndex < 9; containerIndex++) {
                int[] digitArray = new int[9];
                for (int digitArrayIndex = 0; digitArrayIndex < digitArray.length; digitArrayIndex++) {
                    digitArray[digitArrayIndex] = 0;
                }
                try {
                    doForEachIn(squares, container, containerIndex, (square) -> {
                        if (square.solved()) {
                            digitArray[square.possibilities.get(0) - 1]++;
                        }
                        return false;
                    });
                } catch (NoPossibilitiesException e) {
                    System.out.println("Dorothee says I will never happen.");
                }

                for (int digitArrayIndex = 0; digitArrayIndex < digitArray.length; digitArrayIndex++) {
                    if (digitArray[digitArrayIndex] == 0) {
                        return false;
                    }
                }
            }
        }
        return true;

    }

    static boolean hasSudokuMistake(Square[][] squares) {
        for (Container container : Container.values()) {
            for (int containerIndex = 0; containerIndex < 9; containerIndex++) {
                int[] digitArray = new int[9];
                for (int digitArrayIndex = 0; digitArrayIndex < digitArray.length; digitArrayIndex++) {
                    digitArray[digitArrayIndex] = 0;
                }
                try {
                    doForEachIn(squares, container, containerIndex, (square) -> {
                        if (square.solved()) {
                            digitArray[square.possibilities.get(0) - 1]++;
                        }
                        return false;
                    });
                } catch (NoPossibilitiesException e) {
                    System.out.println("Dorothee says I will never happen.");
                }

                for (int digitArrayIndex = 0; digitArrayIndex < digitArray.length; digitArrayIndex++) {
                    if (digitArray[digitArrayIndex] > 1) {
                        System.out.println("There are too many " +(digitArrayIndex+1)+ " in " +container.toString().toLowerCase() + " " + containerIndex);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    static Square[][] cloneSquares(Square[][] squares) {
        Square[][] clone = new Square[squares.length][squares[0].length];
        for (int y = 0; y < squares.length; y++) {
            for (int x = 0; x < squares[0].length; x++) {
                clone[y][x] = squares[y][x].cloneSquare();
            }
        }
        return clone;
    }
}
