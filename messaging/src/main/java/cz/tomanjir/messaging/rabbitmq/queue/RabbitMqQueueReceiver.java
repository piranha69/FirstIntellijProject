package cz.tomanjir.messaging.rabbitmq.queue;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import cz.tomanjir.messaging.Connector;
import cz.tomanjir.messaging.Message;
import cz.tomanjir.messaging.MessageConsumer;
import cz.tomanjir.messaging.rabbitmq.RabbitMqConnector;
import cz.tomanjir.messaging.rabbitmq.RabbitMqProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class RabbitMqQueueReceiver {

    private static final Logger LOG = LoggerFactory.getLogger(RabbitMqQueueReceiver.class);

    @Inject
    public RabbitMqQueueReceiver(RabbitMqConnector connector, RabbitMqProperties properties, MessageConsumer messageHandler) {
        Channel channel = connector.getMediator();
        createQueueIfNeeded(channel, properties);
        registerConsumer(connector, channel, properties, messageHandler);
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

    private void registerConsumer(Connector connector, Channel channel, RabbitMqProperties properties, MessageConsumer messageHandler) {
        String queue = properties.getQueue();
        try {
            channel.basicConsume(queue, true, new RabbitMqMessageConsumer(connector, channel, messageHandler));
            LOG.info("Registered RabitMQ message consumer for {} queue...", queue);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new IllegalStateException("Failed to register RabitMQ message consumer on " + queue + " queue!", e);
        }
    }

    private static class RabbitMqMessageConsumer extends DefaultConsumer {

        private static final String KILL_SIGNAL = "KILL_SIGNAL";

        private final Connector connector;
        private final MessageConsumer messageConsumer;

        private RabbitMqMessageConsumer(Connector connector, Channel channel, MessageConsumer messageHandler) {
            super(channel);
            this.connector = connector;
            this.messageConsumer = messageHandler;
        }

        @Override
        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
            LOG.info("Received {}.", new String(body, StandardCharsets.UTF_8));

            String stringMessage = new String(body, StandardCharsets.UTF_8);
            if (KILL_SIGNAL.equals(stringMessage)) {
                LOG.warn("Received KILL_SIGNAL, stopping the consumer...");
                connector.disconnect();
                LOG.warn("Received KILL_SIGNAL, stopped consumer!");
            } else {
                //TODO: Get message from byte[]
                Message message = null;
                messageConsumer.consume(message);
            }
        }
    }
}