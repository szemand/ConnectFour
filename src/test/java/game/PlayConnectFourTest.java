package game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayConnectFourTest {

    int[][] testboard1 = {
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,2,0,0},
            {0,0,1,2,1,2,0},
            {0,0,2,1,1,1,2},
            {0,2,1,2,1,2,1}
    };

    int[][] testboard2 = {
            {0,2,1,2,1,2,1},
            {2,2,1,1,1,2,1},
            {2,1,2,2,2,1,2},
            {2,2,1,1,1,2,1},
            {1,1,2,2,2,1,2},
            {2,1,2,1,2,1,2}
    };

    @Test
    void placeInColumn(){
        PlayConnectFour a = new PlayConnectFour();
        a.board = testboard1;
        a.currentPlayer = 1;
        a.placeInColumn(5);
        assertEquals(5, a.lastMoveCol);
        assertEquals(2, a.lastMoveRow);
        assertEquals(1, a.currentPlayer);
        assertTrue(a.isGameOver());

        PlayConnectFour b = new PlayConnectFour();
        b.board = testboard2;
        b.currentPlayer = 2;
        b.placeInColumn(0);
        assertEquals(2, b.currentPlayer);
        assertFalse(b.isGameDraw());
    }

    @Test
    void changeCurrentPlayer(){
        PlayConnectFour c = new PlayConnectFour();
        assertEquals(1, c.currentPlayer);
        c.changeCurrentPlayer(c.currentPlayer);
        assertEquals(2, c.currentPlayer);
    }

    @Test
    void isGameOver() {
        PlayConnectFour d = new PlayConnectFour();
        assertFalse(d.isGameOver());
    }

    @Test
    void isGameDraw() {
        PlayConnectFour e = new PlayConnectFour();
        assertFalse(e.isGameDraw());
    }
}
