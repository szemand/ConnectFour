package game;

import org.tinylog.Logger;

/**
 * Class responsible for checking if the player have won the game or not
 */
public class WinConditionChecker {

    /**
     * Checks for winning by 4 in-a-row vertically
     * @param board the board the method should analyze
     * @param player the player whose disks the method should look for
     * @return {@code true} if the player's moves had met this condition, {@code false} if not
     */
    private boolean vertCheck(int[][] board, int player){
        for (int i=0; i<board[0].length; i++){
            for (int j=0; j<=board.length-4; j++){
                if (board[j][i] == player && board[j+1][i] == player && board[j+2][i] == player && board[j+3][i] == player){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks for winning by 4 in-a-row horizontally
     * @param board the board the method should analyze
     * @param player the player whose disks the method should look for
     * @return {@code true} if the player's moves had met this condition, {@code false} if not
     */
    private boolean horCheck(int[][] board, int player){
        for (int i=0; i<board.length; i++){
            for (int j=0; j<=board[i].length-4; j++){
                if (board[i][j] == player && board[i][j+1] == player && board[i][j+2] == player && board[i][j+3] == player){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks for winning by 4 in-a-row in an ascending diagonal shape.
     * @param board the board the method should analyze
     * @param player the player whose disks the method should look for
     * @return {@code true} if the player's moves had met this condition, {@code false} if not
     */
    private boolean diagAscCheck(int[][] board, int player){
        for (int i=3; i<board.length; i++){
            for (int j=0; j<=board[i].length-4; j++){
                if (board[i][j] == player && board[i-1][j+1] == player && board[i-2][j+2] == player && board[i-3][j+3] == player){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks for winning by 4 in-a-row in a descending diagonal shape.
     * @param board the board the method should analyze
     * @param player the player whose disks the method should look for
     * @return {@code true} if the player's moves had met this condition, {@code false} if not
     */
    private boolean diagDescCheck(int[][] board, int player){
        for (int i=0; i<=board.length-4; i++){
            for (int j=0; j<=board[i].length-4; j++){
                if (board[i][j] == player && board[i+1][j+1] == player && board[i+2][j+2] == player && board[i+3][j+3] == player){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if the player have won the game by looking for every possible winning formation.
     * @param board the board the method should analyze
     * @param player the player whose disks the method should look for
     * @return {@code true} if the player meets at least one win condition, {@code false} if not
     */
    public boolean checkWin(int[][] board, int player){
        if (
                vertCheck(board, player)
                || horCheck(board, player)
                || diagAscCheck(board, player)
                || diagDescCheck(board, player)
        ) {
            Logger.info("Game is Over!");
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks whether board has remaining holes or not.
     * @param board the board to analyze
     * @return {@code true} if the board is full and the are no empty holes left.
     */
    public boolean checkIfFull(int[][] board) {
        for (int i=0; i<board.length; i++){
            for (int j=0; j<board[i].length; j++){
                if (board[i][j] == 0){
                    return false;
                }
            }
        }
        return true;
    }
}
