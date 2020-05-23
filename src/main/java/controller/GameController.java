package controller;

import game.PlayConnectFour;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import org.tinylog.Logger;
import game.PlayConnectFour;

public class GameController {

    private PlayConnectFour connectFour;

    @FXML
    private GridPane playGrid;

    private Image EmptyPoint = new Image(getClass().getResource("/images/EmptyPoint.png").toExternalForm());
    private Image RedPoint = new Image(getClass().getResource("/images/RedPoint.png").toExternalForm());
    private Image YellowPoint = new Image(getClass().getResource("/images/YellowPoint.png").toExternalForm());

    @FXML
    private void initialize() {
        connectFour = new PlayConnectFour();

        for(int i = 0; i<7; i++){
            for(int j=1; j<7; j++){
                ImageView image = new ImageView(EmptyPoint);
                Pane pane = new Pane(image);
                image.fitWidthProperty().bind(pane.widthProperty());
                image.fitHeightProperty().bind(pane.heightProperty());
                playGrid.add(pane, i, j);
            }
        }
    }

    @FXML
    private void onColButtonClicked(ActionEvent event){
        Node source = (Node) event.getSource();
        Logger.info("Button pressed at column number {}", playGrid.getColumnIndex(source));
        connectFour.placeInColumn(playGrid.getColumnIndex(source), connectFour.currentPlayer);
        Logger.info("Next player:{}", connectFour.currentPlayer);
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
    }

    public void removeNodeByRowColumnIndex(final int row,final int column,GridPane gridPane) {

        ObservableList<Node> childrens = gridPane.getChildren();
        for(Node node : childrens) {
            if(node instanceof Pane && gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                gridPane.getChildren().remove(node);
                break;
            }
        }
    }
}
