package controller;

import game.PlayConnectFour;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import org.tinylog.Logger;
import statistics.ProfileStatisticsDao;

import javax.inject.Inject;
import java.io.IOException;

public class GameController {

    @Inject
    private ProfileStatisticsDao profileStatisticsDao;

    @Inject
    private FXMLLoader fxmlLoader;

    private PlayConnectFour connectFour;

    private String[] playernames = new String[2];

    @FXML
    private GridPane playGrid;

    @FXML
    private Label turnInfo;

    @FXML
    private Label redInfo;

    @FXML
    private Label yellowInfo;

    @FXML
    private Button backToMain;

    private Image EmptyPoint = new Image(getClass().getResource("/images/EmptyPoint.png").toExternalForm());
    private Image RedPoint = new Image(getClass().getResource("/images/RedPoint.png").toExternalForm());
    private Image YellowPoint = new Image(getClass().getResource("/images/YellowPoint.png").toExternalForm());

    private BooleanProperty gameOver = new SimpleBooleanProperty();
    private BooleanProperty gameDraw = new SimpleBooleanProperty();

    @FXML
    private void initialize() {

        Platform.runLater(() -> {
            turnInfo.setText(playernames[connectFour.currentPlayer-1] + "'s turn!");
        });
        connectFour = new PlayConnectFour();

        backToMain.setVisible(false);

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
                turnInfo.setText("Game is over, " + playernames[connectFour.currentPlayer-1]+ " won!");
                profileStatisticsDao.updateWinner(playernames[connectFour.currentPlayer-1]);
                profileStatisticsDao.updateLoser(playernames[1-(connectFour.currentPlayer-1)]);
                backToMain.setVisible(true);
                backToMain.setDisable(false);
            }
        });
        gameDraw.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                turnInfo.setText("Game Over! Draw!");
                backToMain.setVisible(true);
                backToMain.setDisable(false);
            }
        });

        redInfo.setText("Red: "+playernames[0]);
        yellowInfo.setText("Yellow: "+playernames[1]);
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
        } else if (connectFour.isGameDraw()){
            gameDraw.setValue(true);
        } else {
            turnInfo.setText(playernames[connectFour.currentPlayer-1] + "'s turn!");
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

    public void setNameOne(String name) {
        this.playernames[0] = name;
        Logger.info("name = {}", this.playernames[0]);
    }

    public void setNameTwo(String name) {
        this.playernames[1] = name;
        Logger.info("name = {}", this.playernames[1]);
    }

    public void backToMain(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        fxmlLoader.setLocation(getClass().getResource("/fxml/mainmenu.fxml"));
        Parent root = fxmlLoader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
