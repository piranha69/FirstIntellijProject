package cz.tomanjir.messaging.rabbitmq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import cz.tomanjir.common.id.IdGenerator;
import cz.tomanjir.messaging.MessagePublisher;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Date;

public class RabbitMqQueuePublisher implements MessagePublisher<RabbitMqMessage> {

    private static final Logger LOG = LoggerFactory.getLogger(RabbitMqQueuePublisher.class);

    private final Channel channel;

    private final String queue;
    private final AMQP.BasicProperties propertiesTemplate;
    private final IdGenerator<String> idGenerator;

    @Inject
    public RabbitMqQueuePublisher(RabbitMqConnector connector, RabbitMqQueuePublisherProperties properties, IdGenerator<String> idGenerator) {
        this.channel = connector.getMediator();
        this.queue = properties.getQueue();
        this.propertiesTemplate = createPropertiesTemplate(properties);
        this.idGenerator = idGenerator;

        createQueueIfNeeded(properties);
    }

    private AMQP.BasicProperties createPropertiesTemplate(RabbitMqQueuePublisherProperties properties) {
        return new AMQP.BasicProperties.Builder()
                .appId(properties.getAppId())
                .deliveryMode(properties.getDeliveryMode().getValue())
                .build();
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
    public void publish(RabbitMqMessage message) {
        AMQP.BasicProperties props = createBasicProperties(message);
        byte[] body = message.getBytes();
        try {
            channel.basicPublish(StringUtils.EMPTY, queue, props, body);
            LOG.info("Sent {}.", message.toString());
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private AMQP.BasicProperties createBasicProperties(RabbitMqMessage message) {
        String id = idGenerator.nextId();
        return propertiesTemplate.builder()
                .messageId(id)
                .timestamp(new Date())
                .build();
    }
}