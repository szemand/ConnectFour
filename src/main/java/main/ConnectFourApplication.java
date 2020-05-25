package main;

import com.gluonhq.ignite.guice.GuiceContext;
import com.google.inject.AbstractModule;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.tinylog.Logger;
import statistics.ProfileStatisticsDao;
import util.guice.PersistenceModule;

import javax.inject.Inject;
import java.util.List;

public class ConnectFourApplication extends Application {

    private GuiceContext context = new GuiceContext(this, () -> List.of(
            new AbstractModule() {
                @Override
                protected void configure() {
                    install(new PersistenceModule("connect-four"));
                    bind(ProfileStatisticsDao.class);
                }
            }
    ));

    @Inject
    private FXMLLoader fxmlLoader;

    @Inject
    private ProfileStatisticsDao profileStatisticsDao;

    @Override
    public void start(Stage primaryStage) throws Exception {
        context.init();
        Logger.trace("gameResultDao: {}", profileStatisticsDao);
        fxmlLoader.setLocation(getClass().getResource("/fxml/mainmenu.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Connect Four game");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
