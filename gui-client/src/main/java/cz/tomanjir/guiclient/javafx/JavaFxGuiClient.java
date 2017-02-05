package cz.tomanjir.guiclient.javafx;

import cz.tomanjir.guiclient.javafx.spring.SpringFxmlLoader;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JavaFxGuiClient extends Application {

    private static final Logger LOG = LoggerFactory.getLogger(JavaFxGuiClient.class);

    public static void main(String... args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        LOG.info("Starting home-system GuiClient...");

        LOG.info("Initializing Spring context...");
        ApplicationContext springContext = new ClassPathXmlApplicationContext("config/spring/gui-client-context.xml");
        LOG.info("Initialized Spring context.");

        SpringFxmlLoader loader = new SpringFxmlLoader(springContext);
        Parent root = loader.load(getClass().getResource("main-window.fxml"));

        primaryStage.setTitle("Home System");
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();

        LOG.info("Started home-system GuiClient.");
    }
}