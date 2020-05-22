package controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class GameController {

    @FXML
    private GridPane playGrid;

    private Image EmptyPoint = new Image(getClass().getResource("/images/EmptyPoint.png").toExternalForm());

    @FXML
    private void initialize() {

        for(int i = 0; i<7; i++){
            for(int j=0; j<7; j++){
                ImageView image = new ImageView(EmptyPoint);
                Pane pane = new Pane(image);
                image.fitWidthProperty().bind(pane.widthProperty());
                image.fitHeightProperty().bind(pane.heightProperty());
                playGrid.add(pane, i, j);
            }
        }

    }

}
