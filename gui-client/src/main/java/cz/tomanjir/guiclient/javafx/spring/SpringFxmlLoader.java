package cz.tomanjir.guiclient.javafx.spring;

import javafx.fxml.FXMLLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.net.URL;

public class SpringFxmlLoader {

    private static final Logger LOG = LoggerFactory.getLogger(SpringFxmlLoader.class);

    private final ApplicationContext context;

    public SpringFxmlLoader(ApplicationContext context) {
        this.context = context;
    }

    public <T> T load(URL url) {
        LOG.info("Loading {} resource...", url);
        FXMLLoader loader = new FXMLLoader(url);
        loader.setControllerFactory(context::getBean);
        try {
            T result = loader.load();
            LOG.info("Loaded {} resource.", url);
            return result;
        } catch (IOException ioException) {
            LOG.error("Failed to load {} resource.!", url, ioException);
            throw new RuntimeException(ioException);
        }
    }
}