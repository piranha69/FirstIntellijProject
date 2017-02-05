package cz.tomanjir.guiclient.javafx;

import cz.tomanjir.guiclient.javafx.spring.SpringFxmlLoader;
import cz.tomanjir.messaging.Connector;
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

    private ApplicationContext applicationContext;

    @Override
    public void init() throws Exception {
        LOG.info("Initializing Spring context...");
        applicationContext = new ClassPathXmlApplicationContext("config/spring/gui-client-context.xml");
        LOG.info("Initialized Spring context.");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        LOG.info("Starting home-system GuiClient...");

        SpringFxmlLoader loader = new SpringFxmlLoader(applicationContext);
        Parent root = loader.load(getClass().getClassLoader().getResource("ui/javafx/Main.fxml"));

        primaryStage.setTitle("Home System");
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();

        LOG.info("Started home-system GuiClient.");
    }

    @Override
    public void stop() throws Exception {
        Connector connector = applicationContext.getBean(Connector.class);
        connector.disconnect();
    }
}