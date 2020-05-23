package controller;

import game.PlayConnectFour;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.event.ActionEvent;
import org.tinylog.Logger;

public class GameController {

    private PlayConnectFour connectFour;

    @FXML
    private GridPane playGrid;

    @FXML
    private Label turnInfo;

    private Image EmptyPoint = new Image(getClass().getResource("/images/EmptyPoint.png").toExternalForm());
    private Image RedPoint = new Image(getClass().getResource("/images/RedPoint.png").toExternalForm());
    private Image YellowPoint = new Image(getClass().getResource("/images/YellowPoint.png").toExternalForm());

    private BooleanProperty gameOver = new SimpleBooleanProperty();

    @FXML
    private void initialize() {
        connectFour = new PlayConnectFour();

        turnInfo.setText(connectFour.currentPlayer + ". player's turn!");
        for(int i = 0; i<7; i++){
            for(int j=1; j<7; j++){
                ImageView image = new ImageView(EmptyPoint);
                Pane pane = new Pane(image);
                image.fitWidthProperty().bind(pane.widthProperty());
                image.fitHeightProperty().bind(pane.heightProperty());
                playGrid.add(pane, i, j);
            }
        }

        gameOver.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                turnInfo.setText("Game is over, " + connectFour.currentPlayer + ". player won!");
                for (int i=0; i<7; i++){
                    removeNodeByRowColumnIndex(0, i, playGrid);
                }
            }
        });
    }

    @FXML
    private void onColButtonClicked(ActionEvent event){
        Node source = (Node) event.getSource();
        Logger.info("Button pressed at column number {}", playGrid.getColumnIndex(source));
        connectFour.placeInColumn(playGrid.getColumnIndex(source));
        Logger.info("Players turn: {}", connectFour.currentPlayer);
        reDraw();
    }

    private void reDraw(){
        removeNodeByRowColumnIndex(connectFour.lastMoveRow + 1, connectFour.lastMoveCol, playGrid);
        ImageView image;
        switch (connectFour.board[connectFour.lastMoveRow][connectFour.lastMoveCol]){
            case 1:
                image = new ImageView(RedPoint);
                break;
            case 2:
                image = new ImageView(YellowPoint);
                break;
            default:
                image = new ImageView(EmptyPoint);
        }
        Pane pane = new Pane(image);
        image.fitWidthProperty().bind(pane.widthProperty());
        image.fitHeightProperty().bind(pane.heightProperty());
        playGrid.add(pane, connectFour.lastMoveCol, connectFour.lastMoveRow + 1);
        if (connectFour.isGameOver()){
            gameOver.setValue(true);
        } else {
            turnInfo.setText(connectFour.currentPlayer + ". player's turn!");
        }
    }

    public void removeNodeByRowColumnIndex(final int row,final int column,GridPane gridPane) {

        ObservableList<Node> children = gridPane.getChildren();
        for(Node node : children) {
            if(node instanceof Pane && gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                gridPane.getChildren().remove(node);
                break;
            }
        }
    }
}
