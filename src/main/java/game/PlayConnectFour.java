package game;

/**
 * Class representing the game board in a 2D matrix, and providing several information about the game state.
 */
public class PlayConnectFour {

    private static final int[] Players = {1, 2};
    private WinConditionChecker checker = new WinConditionChecker();
    public int currentPlayer = Players[0];
    private boolean gameOver = false;
    private boolean gameDraw = false;
    public int lastMoveRow;
    public int lastMoveCol;

    /**
     * The 2D matrix, representing the original 6-row 7-column board.
     */
    public int[][] board = new int[6][7];

    /**
     * Places a disk in the selected column of the board.
     * Automatically sets the next player's turn after checking if the game ended.
     * @param column the column to drop the disk in
     */
    public void placeInColumn(int column) {
        if (!gameOver && !gameDraw) {
            for(int i = 5; i>=0; i--){
                if(board[i][column] == 0){
                    board[i][column] = this.currentPlayer;
                    lastMoveRow = i;
                    lastMoveCol = column;
                    if (checker.checkWin(board, this.currentPlayer)){
                        this.gameOver = true;
                        break;
                    } else if (checker.checkIfFull(board)){
                        this.gameDraw = true;
                        break;
                    }
                    changeCurrentPlayer(this.currentPlayer);
                    break;
                }
            }
        }
    }

    /**
     * Changes the turns between the two players.
     * @param lastplayer the player's number in the matrix, whose turn just ended
     */
    public void changeCurrentPlayer(int lastplayer){
        if(lastplayer==Players[0]){
            currentPlayer = Players[1];
        } else {
            currentPlayer = Players[0];
        }

    }

    /**
     * Returns whether the game is over or not.
     * @return {@code true} if the game is over, {@code false} if not
     */
    public boolean isGameOver(){
        if (this.gameOver){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns whether the game resulted in a draw or not.
     * Should only occour if there ar no empty holes left
     * @return {@code true} if the game is a draw, {@code false} in every other case
     */
    public boolean isGameDraw(){
        if (this.gameDraw){
            return true;
        } else {
            return false;
        }
    }


}
