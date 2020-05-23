package game;

public class PlayConnectFour {

    private static final int[] Players = {1, 2};
    public int currentPlayer = Players[0];
    public boolean isGamerOver = false;
    public int lastMoveRow;
    public int lastMoveCol;

    public int[][] board = new int[6][7];

    public void placeInColumn(int column, int player) {
        for(int i = 5; i>=0; i--){
            if(board[i][column] == 0){
                board[i][column] = player;
                lastMoveRow = i;
                lastMoveCol = column;
                changeCurrentPlayer(player);
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


}
