package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.tinylog.Logger;

import javax.inject.Inject;
import java.io.IOException;

public class MainMenuController {

    @Inject
    private FXMLLoader fxmlLoader;


    public void switchToPlay(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        fxmlLoader.setLocation(getClass().getResource("/fxml/playerselect.fxml"));
        Parent root = fxmlLoader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void switchToCreatePlayer(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        fxmlLoader.setLocation(getClass().getResource("/fxml/createplayer.fxml"));
        Parent root = fxmlLoader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void switchToStatistics(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        fxmlLoader.setLocation(getClass().getResource("/fxml/statistics.fxml"));
        Parent root = fxmlLoader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void handleExit(ActionEvent event) {
        Logger.info("Exiting...");
        Platform.exit();
    }
}
