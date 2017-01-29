package cz.tomanjir.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

    private static final Logger LOG = LoggerFactory.getLogger(MainApp.class);

    public static void main(String... args) {
        LOG.info("Spring context initializing...");
        new ClassPathXmlApplicationContext(new String[]{"config/spring/context.xml"});
        LOG.info("Spring context initialized.");
    }
}