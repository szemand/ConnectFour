package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WinConditionCheckerTest {

    int[][] board = {
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,2,0,0},
            {0,0,1,2,1,2,0},
            {0,0,2,1,1,1,2},
            {0,2,1,2,1,2,1}
    };

    int[][] board2 = {
            {1,2,1,2,1,2,1},
            {1,2,1,1,1,2,1},
            {2,1,2,2,2,1,2},
            {1,2,1,1,1,2,1},
            {2,1,2,2,2,1,2},
            {2,1,2,1,2,1,2}
    };

    int[][] board3 = {
            {1, 2, 2, 2, 1},
            {1, 2, 1, 1, 1},
            {2, 1, 1, 1, 1},
    };

    int[][] board4 = {
            {1, 2, 0},
            {2, 2, 0}
    };

    @Test
    void checkWin(){
        WinConditionChecker winConditionChecker = new WinConditionChecker();
        assertFalse(winConditionChecker.checkWin(board, 1));
        assertFalse(winConditionChecker.checkWin(board2, 2));
        assertFalse(winConditionChecker.checkWin(board3, 2));
        assertTrue(winConditionChecker.checkWin(board3, 1));
        assertFalse(winConditionChecker.checkWin(board4, 2));
    }

    @Test
    void checkIfFull(){
        WinConditionChecker winConditionChecker = new WinConditionChecker();
        assertFalse(winConditionChecker.checkIfFull(board));
        assertTrue(winConditionChecker.checkIfFull(board2));
        assertTrue(winConditionChecker.checkIfFull(board3));
        assertFalse(winConditionChecker.checkIfFull(board4));
    }
}
