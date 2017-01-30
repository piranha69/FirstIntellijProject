package cz.tomanjir.messaging.rabbitmq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import cz.tomanjir.messaging.Message;
import cz.tomanjir.messaging.MessageConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class RabbitMqMessageReceiver {

    private static final Logger LOG = LoggerFactory.getLogger(RabbitMqMessagePublisher.class);

    @Inject
    public RabbitMqMessageReceiver(RabbitMqConnector connector, String queueName, MessageConsumer messageHandler) {
        Channel channel = connector.getMediator();
        createQueueIfNeeded(channel, queueName);
        registerConsumer(channel, queueName, messageHandler);
    }

    private void createQueueIfNeeded(Channel channel, String queueName) {
        LOG.info("Declaring RabitMQ queue {}...", queueName);
        try {
            channel.queueDeclare(queueName, false, false, false, null);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new IllegalStateException("Failed to declare RabitMQ queue " + queueName + "!", e);
        }
    }

    private void registerConsumer(Channel channel, String queueName, MessageConsumer messageHandler) {
        LOG.info("Registering RabitMQ message consumer on {} queue...", queueName);
        try {
            channel.basicConsume(queueName, true, new RabbitMqMessageConsumer(channel, messageHandler));
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new IllegalStateException("Failed to register RabitMQ message consumer on " + queueName + " queue!", e);
        }
    }

    private static class RabbitMqMessageConsumer extends DefaultConsumer {

        private final MessageConsumer messageConsumer;

        private RabbitMqMessageConsumer(Channel channel, MessageConsumer messageHandler) {
            super(channel);
            this.messageConsumer = messageHandler;
        }

        @Override
        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
            LOG.info("Recieved {}.", new String(body, StandardCharsets.UTF_8));
            Message message = null; //TODO: Get message from byte[]
            messageConsumer.consume(message);
        }
    }
}