package game;

import org.tinylog.Logger;

public class WinConditionChecker {

    private boolean vertCheck(int[][] board, int player){
        for (int i=0; i<board[0].length; i++){ //7
            for (int j=0; j<=board.length-4; j++){ //6
                if (board[j][i] == player && board[j+1][i] == player && board[j+2][i] == player && board[j+3][i] == player){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean horCheck(int[][] board, int player){
        for (int i=0; i<board.length; i++){ //6
            for (int j=0; j<=board[i].length-4; j++){ //7
                if (board[i][j] == player && board[i][j+1] == player && board[i][j+2] == player && board[i][j+3] == player){
                    return true;
                }
            }
        }
        return false;
    }

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
