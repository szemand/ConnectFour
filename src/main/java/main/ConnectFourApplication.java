package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ConnectFourApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(ConnectFourApplication.class.getResource("/fxml/game.fxml"));
        primaryStage.setTitle("Connect Four game");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
