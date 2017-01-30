package cz.tomanjir.messaging.rabbitmq;

import com.rabbitmq.client.Channel;
import cz.tomanjir.messaging.Message;
import cz.tomanjir.messaging.MessagePublisher;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.IOException;

public class RabbitMqMessagePublisher implements MessagePublisher {

    private static final Logger LOG = LoggerFactory.getLogger(RabbitMqMessagePublisher.class);

    private final Channel channel;
    private final String queueName;

    @Inject
    public RabbitMqMessagePublisher(RabbitMqConnector connector, String queueName) {
        this.channel = connector.getMediator();
        this.queueName = queueName;
        createQueueIfNeeded();
    }

    private void createQueueIfNeeded() {
        LOG.info("Declaring RabitMQ queue {}...", queueName);
        try {
            channel.queueDeclare(queueName, false, false, false, null);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new IllegalStateException("Failed to declare RabitMQ queue " + queueName + "!", e);
        }
    }

    @Override
    public void publish(Message message) {
        try {
            channel.basicPublish(StringUtils.EMPTY, queueName, null, message.getBytes());
            LOG.info("Sent {}.", message.toString());
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}