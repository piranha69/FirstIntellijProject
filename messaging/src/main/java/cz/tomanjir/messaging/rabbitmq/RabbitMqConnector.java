package cz.tomanjir.messaging.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import cz.tomanjir.common.service.Service;
import cz.tomanjir.messaging.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMqConnector implements Connector<Channel>, Service {

    private static final Logger LOG = LoggerFactory.getLogger(RabbitMqConnector.class);

    private final ConnectionFactory connectionFactory;

    private Connection connection;
    private Channel channel;

    @Inject
    public RabbitMqConnector(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public void initService() {
        connect();
    }

    @Override
    public void connect() {
        LOG.info("Connecting to RabbitMQ...");

        try {
            connection = connectionFactory.newConnection();
        } catch (IOException | TimeoutException e) {
            LOG.error(e.getMessage(), e);
            throw new IllegalStateException(e);
        }

        try {
            channel = connection.createChannel();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new IllegalStateException(e);
        }

        LOG.info("Connected to RabbitMQ.");
    }

    @Override
    public Channel getMediator() {
        return channel;
    }

    @Override
    public void destroyService() {
        disconnect();
    }

    @Override
    public void disconnect() {
        LOG.info("Disconnecting from RabbitMQ...");

        try {
            channel.close();
        } catch (IOException | TimeoutException e) {
            LOG.error(e.getMessage(), e);
            return;
        }

        try {
            connection.close();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            return;
        }

        LOG.info("Disconnected from RabbitMQ.");
    }
}
