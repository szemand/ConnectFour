package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.IOException;

public class MainMenuController {

    public void switchToPlay(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/playerselect.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void switchToCreatePlayer(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/createplayer.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void switchToStatistics(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/statistics.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void handleExit(ActionEvent event) {
        Logger.info("Exiting...");
        Platform.exit();
    }
}
