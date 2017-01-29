package cz.tomanjir.producer;

import cz.tomanjir.messaging.SenderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

    private static final Logger LOG = LoggerFactory.getLogger(MainApp.class);

    public static void main(String... args) {
        LOG.info("Spring context initializing...");

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[]{"config/spring/context.xml"});
        SenderImpl sender = applicationContext.getBean(SenderImpl.class);
        sender.send("Hello World"::getBytes);//TODO: Remove me! Just for testing.
        sender.destroyService();

        LOG.info("Spring context initialized.");
    }
}