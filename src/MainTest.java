import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void deleteDigitsThatAlreadyExistInContainer() throws NoPossibilitiesException {

        int[][] sudoku = {
                {1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}

        };

        Square[][] squares = Main.createSquaresFromInts(sudoku);

        Main.deleteDigitsThatAlreadyExistInContainer(squares);

        assertEquals(squares[2][2].possibilities.get(0), 2);
        assertEquals(squares[5][0].possibilities.get(0), 2);
        assertEquals(squares[0][5].possibilities.get(0), 2);
    }

    @Test
    void setDigitsThatHaveOnlyOnePossibleSquare() throws NoPossibilitiesException {

        int[][] sudoku = {
                {0, 0, 3, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 3, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 8, 0, 9},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        Square[][] squares = Main.createSquaresFromInts(sudoku);

        Main.deleteDigitsThatAlreadyExistInContainer(squares);

        Main.setDigitsThatHaveOnlyOnePossibleSquare(squares);

        assertEquals(squares[2][7].possibilities.get(0), 3);
        assertTrue(squares[2][7].solved());

    }

    @Test
    void deleteDigitsThatMustBeInOtherPlaceInContainerFromRow() throws NoPossibilitiesException {

        int[][] sudoku = {
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

        Square[][] squares = Main.createSquaresFromInts(sudoku);

        Main.deleteDigitsThatAlreadyExistInContainer(squares);

        Main.setDigitsThatHaveOnlyOnePossibleSquare(squares);

        assertTrue(squares[0][7].possibilities.contains(3));

        Main.deleteDigitsThatMustBeInOtherPlaceInContainer(squares);

        assertFalse(squares[0][7].possibilities.contains(3));

    }


    @Test
    void deleteDigitsThatMustBeInOtherPlaceInContainerFromColumn() throws NoPossibilitiesException {

        int[][] sudoku = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 5},
                {0, 0, 0, 0, 0, 0, 0, 2, 4},
                {0, 0, 0, 0, 0, 0, 0, 6, 7}
        };

        Square[][] squares = Main.createSquaresFromInts(sudoku);

        Main.deleteDigitsThatAlreadyExistInContainer(squares);

        Main.setDigitsThatHaveOnlyOnePossibleSquare(squares);

        assertTrue(squares[0][6].possibilities.contains(3));

        Main.deleteDigitsThatMustBeInOtherPlaceInContainer(squares);

        assertFalse(squares[0][6].possibilities.contains(3));

    }


    @Test
    void deleteDigitsThatMustBeInOtherPlaceInContainerFromBlock() throws NoPossibilitiesException {

        int[][] sudoku = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 3, 0},
                {5, 6, 7, 0, 0, 0, 8, 0, 9},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        Square[][] squares = Main.createSquaresFromInts(sudoku);

        Main.deleteDigitsThatAlreadyExistInContainer(squares);

        Main.setDigitsThatHaveOnlyOnePossibleSquare(squares);

        assertTrue(squares[0][5].possibilities.contains(3));

        Main.deleteDigitsThatMustBeInOtherPlaceInContainer(squares);

        assertFalse(squares[0][5].possibilities.contains(3));

    }

    @Test
    void cloning() {
        int[][] sudoku = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 3, 0},
                {5, 6, 7, 0, 0, 0, 8, 0, 9},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        Square[][] squares = Main.createSquaresFromInts(sudoku);
        Square[][] clone = Main.cloneSquares(squares);
        clone[0][0].solve(7);

        assertEquals(squares[0][0].possibilities.size(), 9);
        assertEquals(clone[0][0].possibilities.size(), 1);
    }

    @Test
    void exception() {
        boolean exceptionStopsMethod = true;
        int[][] sudoku = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 3, 3, 0},
                {5, 6, 7, 0, 0, 0, 8, 0, 9},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        Square[][] squares = Main.createSquaresFromInts(sudoku);
        Main.isSudokuSolved(squares);
        exceptionStopsMethod = false;
        //System.out.println("kept running");
        assertFalse(exceptionStopsMethod);
    }

    @Test
    void hasMistakeTest() {

        int[][] sudoku = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 3, 3, 0},
                {5, 6, 7, 0, 0, 0, 8, 0, 9},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        Square[][] squares = Main.createSquaresFromInts(sudoku);


        assertTrue(Main.hasSudokuMistake(squares));
    }
}