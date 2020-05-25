package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.tinylog.Logger;
import statistics.ProfileStatistics;
import statistics.ProfileStatisticsDao;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

public class PlayerSelectController {

    @Inject
    private FXMLLoader fxmlLoader;

    @Inject
    private ProfileStatisticsDao profileStatisticsDao;

    @FXML
    private ComboBox<String> redComboBox;

    @FXML
    private ComboBox<String> yellowComboBox;

    @FXML
    private Label errorMessage;

    public void initialize(){
        List profilelist = profileStatisticsDao.profileNames();
        ObservableList<String> names = FXCollections.observableArrayList();
        names.addAll(profilelist);
        redComboBox.setItems(names);
        yellowComboBox.setItems(names);
    }

    public void switchToPlayGame(ActionEvent event) throws IOException {
        if (isPlayerNamesDifferent()){
            fxmlLoader.setLocation(getClass().getResource("/fxml/game.fxml"));
            Parent root = fxmlLoader.load();
            GameController controller = fxmlLoader.<GameController>getController();
            controller.setNameOne(redComboBox.getValue());
            controller.setNameTwo(yellowComboBox.getValue());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    public void switchToMainMenu(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        fxmlLoader.setLocation(getClass().getResource("/fxml/mainmenu.fxml"));
        Parent root = fxmlLoader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public boolean isPlayerNamesDifferent() throws NullPointerException {
        if (redComboBox.getValue() == null || yellowComboBox.getValue() == null) {
            errorMessage.setText("Please select players!");
            Logger.info("Cant start game with no name(s)");
            return false;
        } else if (redComboBox.getValue().equals(yellowComboBox.getValue())) {
            errorMessage.setText("Please select two different players!");
            Logger.info("Cant start game with same names");
            return false;
        } else {
            return true;
        }
    }

}
