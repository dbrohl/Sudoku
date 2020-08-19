import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
//    static int[][] sudoku = {
//            {0, 0, 8, 2, 0, 0, 0, 5, 0},
//            {5, 0, 0, 0, 0, 0, 0, 7, 0},
//            {0, 2, 0, 0, 1, 0, 9, 0, 0},
//            {4, 8, 0, 1, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 7, 0, 4, 0, 0},
//            {2, 0, 0, 0, 9, 0, 0, 6, 0},
//            {0, 0, 7, 0, 5, 0, 0, 4, 0},
//            {0, 0, 0, 0, 0, 0, 0, 1, 6},
//            {0, 3, 5, 0, 0, 0, 0, 0, 8}
//    };
    //leeres Sudoku
    static int[][] sudoku = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 2, 4, 0, 3, 0, 0, 0, 0},
            {5, 6, 7, 0, 0, 0, 8, 0, 9},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

//    //BACKUP
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
//    static int[][] sudoku1 = {
//            {0, 0, 9, 7, 0, 0, 0, 0, 3},
//            {0, 0, 4, 5, 0, 0, 0, 9, 0},
//            {1, 5, 0, 0, 9, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 1, 0, 0, 0},
//            {0, 0, 0, 0, 8, 0, 0, 6, 1},
//            {0, 0, 2, 0, 0, 0, 8, 0, 0},
//            {0, 6, 0, 2, 0, 4, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 7, 0, 0},
//            {7, 9, 0, 0, 0, 0, 0, 5, 0}};

    static Square[][] squares = new Square[sudoku.length][sudoku[0].length];

    public static void main(String[] args) {

        //initialize
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku[0].length; j++) {
                if (sudoku[i][j] != 0) {
                    squares[i][j] = new Square(i, j, sudoku[i][j]);
                } else {
                    squares[i][j] = new Square(i, j);
                }
            }
        }

        //go through all the blocks, rows and columns and eliminate wrong possibilities
        while (true) {

            //delete numbers that can't be in a square
            for (Container container : Container.values()) {
                for (int i = 0; i < 9; i++) {
                    List<Integer> existingNumbers = new ArrayList<>();

                    doForEachIn(container, i, (square) -> {
                        if (square.solved()) {
                            existingNumbers.add(square.possibilities.get(0));
                        }
                    });
                    doForEachIn(container, i, (square) -> {
                        square.delete(existingNumbers);
                    });
                }

            }

            //commit number to square if no other square can host it
            for (Container container : Container.values()) {
                for (int containerIndex = 0; containerIndex < 9; containerIndex++) {
                    List<Square>[] squaresContainingNumbersArray = new ArrayList[9];
                    for (int j = 1; j < 10; j++) {
                        squaresContainingNumbersArray[j - 1] = new ArrayList<>();
                        final int finalJ = j;
                        doForEachIn(container, containerIndex, (square) -> {
                            if (square.possibilities.contains(finalJ)) {
                                squaresContainingNumbersArray[finalJ - 1].add(square);
                            }
                        });
                    }
                    for (int j = 0; j < squaresContainingNumbersArray.length; j++) {
                        if (squaresContainingNumbersArray[j].size() == 1 && ! squaresContainingNumbersArray[j].get(0).solved()) {
                            squaresContainingNumbersArray[j].get(0).solve(j + 1);
                        }
                        if (squaresContainingNumbersArray[j].size() == 2 || squaresContainingNumbersArray[j].size() == 3) {
                            boolean numbersInRow=true;
                            boolean numbersInColumn=true;
                            boolean numbersInBlock=true;
                            for (int k = 1; k < squaresContainingNumbersArray[j].size(); k++) {

                                //in a row
                                numbersInRow = numbersInRow && squaresContainingNumbersArray[j].get(k).x == squaresContainingNumbersArray[j].get(k - 1).x;
                                //in a column
                                numbersInColumn = numbersInColumn && squaresContainingNumbersArray[j].get(k).y == squaresContainingNumbersArray[j].get(k - 1).y;
                                //in a block
                                numbersInBlock = numbersInBlock &&

                            }

                            if (container == Container.BLOCK && numbersInRow) {
                                final int finalJ = j;
                                doForEachIn(Container.ROW, squaresContainingNumbersArray[j].get(0).x, (square) -> {
                                    square.delete(Arrays.asList(finalJ + 1));
                                }, Container.BLOCK, containerIndex);
                            }

                            if (container == Container.BLOCK && numbersInColumn) {
                                final int finalJ = j;
                                doForEachIn(Container.COLUMN, squaresContainingNumbersArray[j].get(0).y, (square) -> {
                                    square.delete(Arrays.asList(finalJ + 1));
                                }, Container.BLOCK, containerIndex);
                            }
                        }


                    }
                }

            }
            printSudoku();
        }


    }

    static void doForEachIn(Container container, int index, Interface method) {

        doForEachIn(container, index, method, Container.BLOCK, -1);
    }

    static void doForEachIn(Container container, int index, Interface method, Container excludedContainer, int excludedIndex) {
        switch (container) {
            case BLOCK:
                for (int i = (index / 3) * 3; i < (index / 3) * 3 + 3; i++) {
                    for (int j = (index % 3) * 3; j < (index % 3) * 3 + 3; j++) {

                        if(excludedContainer==Container.ROW && i!=excludedIndex) {
                            continue;
                        }
                        if(excludedContainer==Container.COLUMN && j!=excludedIndex) {
                            continue;
                        }
                        method.doSomething(squares[i][j]);
                    }
                }
                break;
            case ROW:
                for (int i = 0; i < squares[0].length; i++) {
                    if(excludedContainer==Container.BLOCK && excludedIndex!=-1 && i/3==excludedIndex%3) {
                        continue;
                    }
                    method.doSomething(squares[index][i]);
                }
                break;
            case COLUMN:
                for (int i = 0; i < squares.length; i++) {
                    if(excludedContainer==Container.BLOCK && excludedIndex!=-1 && i/3==excludedIndex/3) {
                        continue;
                    }
                    method.doSomething(squares[i][index]);
                }
                break;
        }

    }

    interface Interface {
        void doSomething(Square square);
    }

    static void printSudoku() {
        for (int x = 0; x < 9; x++) {
            if (x % 3 == 0) {
                System.out.println("-------------------------");
            }
            for (int y = 0; y < 9; y++) {
                if (y % 3 == 0) {
                    System.out.print("| ");
                }
                System.out.print((squares[x][y].solved() ? squares[x][y].possibilities.get(0) : " ") + " ");
                if (y == 8) {
                    System.out.print("| ");
                }

            }

            System.out.println();
            if (x == 8) {
                System.out.println("-------------------------");
            }
        }

        System.out.println();
    }
}
