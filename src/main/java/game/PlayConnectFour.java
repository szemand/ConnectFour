package game;

public class PlayConnectFour {

    private static final int[] Players = {1, 2};
    private WinConditionChecker checker = new WinConditionChecker();
    public int currentPlayer = Players[0];
    private boolean gamerOver = false;
    public int lastMoveRow;
    public int lastMoveCol;

    public int[][] board = new int[6][7];

    public void placeInColumn(int column) {
        for(int i = 5; i>=0; i--){
            if(board[i][column] == 0){
                board[i][column] = this.currentPlayer;
                lastMoveRow = i;
                lastMoveCol = column;
                if (checker.checkWin(board, this.currentPlayer)){
                    this.gamerOver = true;
                    break;
                }
                changeCurrentPlayer(this.currentPlayer);
                break;
            }
        }
    }

    public void changeCurrentPlayer(int lastplayer){
        if(lastplayer==Players[0]){
            currentPlayer = Players[1];
        } else {
            currentPlayer = Players[0];
        }

    }

    public boolean isGameOver(){
        if (this.gamerOver){
            return true;
        } else {
            return false;
        }
    }


}
