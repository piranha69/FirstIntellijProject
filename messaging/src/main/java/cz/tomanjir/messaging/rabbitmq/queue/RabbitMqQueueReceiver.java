package cz.tomanjir.messaging.rabbitmq.queue;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import cz.tomanjir.messages.Messages;
import cz.tomanjir.messaging.Message;
import cz.tomanjir.messaging.MessageHandler;
import cz.tomanjir.messaging.rabbitmq.RabbitMqConnector;
import cz.tomanjir.messaging.rabbitmq.RabbitMqProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.IOException;

public class RabbitMqQueueReceiver {

    private static final Logger LOG = LoggerFactory.getLogger(RabbitMqQueueReceiver.class);

    @Inject
    public RabbitMqQueueReceiver(RabbitMqConnector connector, RabbitMqProperties properties, MessageHandler messageHandler) {
        Channel channel = connector.getMediator();
        createQueueIfNeeded(channel, properties);
        registerConsumer(channel, properties, messageHandler);
    }

    private void createQueueIfNeeded(Channel channel, RabbitMqProperties properties) {
        try {
            channel.queueDeclare(properties.getQueue(), properties.isDurable(), properties.isExclusive(), properties.isAutoDelete(), properties.getArguments());
            LOG.info("Declared RabitMQ queue {}.", properties);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new IllegalStateException("Failed to declare RabitMQ queue " + properties + "!", e);
        }
    }

    private void registerConsumer(Channel channel, RabbitMqProperties properties, MessageHandler messageHandler) {
        String queue = properties.getQueue();
        try {
            channel.basicConsume(queue, true, new RabbitMqMessageConsumer(channel, messageHandler));
            LOG.info("Registered RabitMQ message consumer for {} queue...", queue);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new IllegalStateException("Failed to register RabitMQ message consumer on " + queue + " queue!", e);
        }
    }

    private static class RabbitMqMessageConsumer extends DefaultConsumer {

        private final MessageHandler messageHandler;

        private RabbitMqMessageConsumer(Channel channel, MessageHandler messageHandler) {
            super(channel);
            this.messageHandler = messageHandler;
        }

        @Override
        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
            Messages.TextMessage textMessage = Messages.TextMessage.parseFrom(body);

            LOG.info("Received message -> body={}.", textMessage.getText());

            Message message = null; //TODO: Get message from byte[]
            messageHandler.handle(message);
        }
    }
}