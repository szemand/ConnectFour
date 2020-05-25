package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.tinylog.Logger;
import statistics.ProfileStatistics;
import statistics.ProfileStatisticsDao;

import javax.inject.Inject;
import java.io.IOException;

public class CreatePlayerController {

    @Inject
    private ProfileStatisticsDao profileStatisticsDao;

    @Inject
    private FXMLLoader fxmlLoader;

    @FXML
    private TextField playerNameField;

    @FXML
    private Label errorLabel;

    public void switchToMainMenu(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        fxmlLoader.setLocation(getClass().getResource("/fxml/mainmenu.fxml"));
        Parent root = fxmlLoader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void addPlayer(ActionEvent event) throws IOException {
        if (playerNameField.getText().isEmpty()) {
            errorLabel.setText("Enter your name!");
        } else {
            ProfileStatistics profile = ProfileStatistics.builder()
                    .playername(playerNameField.getText())
                    .wins(0)
                    .losses(0)
                    .build();
            Logger.info("Inserted enitity: {}", profile);
            profileStatisticsDao.persist(profile);

        }
    }

}
