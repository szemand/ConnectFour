package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import statistics.ProfileStatistics;
import statistics.ProfileStatisticsDao;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

public class StatisticsController {

    @FXML
    private TableView<ProfileStatistics> profileTable;

    @FXML
    private TableColumn<ProfileStatistics, String> playernameCol;

    @FXML
    private TableColumn<ProfileStatistics, Integer> winsCol;

    @FXML
    private TableColumn<ProfileStatistics, Integer> lossesCol;

    @Inject
    private ProfileStatisticsDao profileStatisticsDao;

    @Inject
    private FXMLLoader fxmlLoader;

    @FXML
    private void initialize(){

        profileStatisticsDao.findAll()
                .stream()
                .forEach(System.out::println);

        List<ProfileStatistics> profilelist = profileStatisticsDao.allProfiles();

        playernameCol.setCellValueFactory(new PropertyValueFactory<>("playername"));
        winsCol.setCellValueFactory(new PropertyValueFactory<>("wins"));
        lossesCol.setCellValueFactory(new PropertyValueFactory<>("losses"));

        ObservableList<ProfileStatistics> observableResult = FXCollections.observableArrayList();
        observableResult.addAll(profilelist);

        profileTable.setItems(observableResult);
    }

    public void switchToMainMenu(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        fxmlLoader.setLocation(getClass().getResource("/fxml/mainmenu.fxml"));
        Parent root = fxmlLoader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
