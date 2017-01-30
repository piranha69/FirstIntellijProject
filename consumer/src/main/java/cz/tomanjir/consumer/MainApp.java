package cz.tomanjir.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

    private static final Logger LOG = LoggerFactory.getLogger(MainApp.class);

    public static void main(String... args) {
        LOG.info("Initializing Spring context...");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[]{"config/spring/context.xml"});

        /*
        RabbitMqConnector connector = applicationContext.getBean(RabbitMqConnector.class);
        connector.disconnect();

        LOG.info("Consumer finished.");
        */
    }
}