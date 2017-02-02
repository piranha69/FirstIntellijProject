package cz.tomanjir.producer;

import cz.tomanjir.messaging.rabbitmq.RabbitMqConnector;
import cz.tomanjir.messaging.rabbitmq.RabbitMqMessage;
import cz.tomanjir.messaging.rabbitmq.RabbitMqQueuePublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

    private static final Logger LOG = LoggerFactory.getLogger(MainApp.class);

    public static void main(String... args) {
        LOG.info("Initializing Spring context...");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[]{"config/spring/context.xml"});
        RabbitMqQueuePublisher publisher = applicationContext.getBean(RabbitMqQueuePublisher.class);
        RabbitMqMessage message = new RabbitMqMessage("Hello World!!!".getBytes());
        publisher.publish(message);//TODO: Remove me! Just for testing.

        RabbitMqConnector connector = applicationContext.getBean(RabbitMqConnector.class);
        connector.disconnect();

        LOG.info("MessagePublisher finished.");
    }
}