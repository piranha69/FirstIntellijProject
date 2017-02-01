package cz.tomanjir.messaging.rabbitmq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import cz.tomanjir.messaging.Message;
import cz.tomanjir.messaging.MessagePublisher;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.IOException;

public class RabbitMqQueuePublisher implements MessagePublisher {

    private static final Logger LOG = LoggerFactory.getLogger(RabbitMqQueuePublisher.class);

    private final Channel channel;

    private final String queue;
    private final AMQP.BasicProperties publishProperties;

    @Inject
    public RabbitMqQueuePublisher(RabbitMqConnector connector, RabbitMqQueuePublisherProperties properties) {
        this.channel = connector.getMediator();
        this.queue = properties.getQueue();
        this.publishProperties = properties.getPublishProperties();
        createQueueIfNeeded(properties);
    }

    private void createQueueIfNeeded(RabbitMqQueueProperties properties) {
        try {
            channel.queueDeclare(queue, properties.isDurable(), properties.isExclusive(), properties.isAutoDelete(), properties.getArguments());
            LOG.info("Declared RabitMQ queue {}.", properties);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new IllegalStateException("Failed to declare RabitMQ queue " + properties + "!", e);
        }
    }

    @Override
    public void publish(Message message) {
        try {
            channel.basicPublish(StringUtils.EMPTY, queue, publishProperties, message.getBytes());
            LOG.info("Sent {}.", message.toString());
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}