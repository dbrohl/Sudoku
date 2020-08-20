import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void deleteDigitsThatAlreadyExistInContainer() {

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
    void setDigitsThatHaveOnlyOnePossibleSquare() {

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
    void deleteDigitsThatMustBeInOtherPlaceInContainerFromRow() {

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
    void deleteDigitsThatMustBeInOtherPlaceInContainerFromColumn() {

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
    void deleteDigitsThatMustBeInOtherPlaceInContainerFromBlock() {

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


}