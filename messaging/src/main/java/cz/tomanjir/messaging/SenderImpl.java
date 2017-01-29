package cz.tomanjir.messaging;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import cz.tomanjir.common.service.Service;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class SenderImpl implements Sender, Service {

    private static final Logger LOG = LoggerFactory.getLogger(SenderImpl.class);

    private final ConnectionFactory connectionFactory;
    private final String queueName;

    private Connection connection;
    private Channel channel;

    @Inject
    public SenderImpl(ConnectionFactory connectionFactory, String queueName) {
        this.connectionFactory = connectionFactory;
        this.queueName = queueName;
    }

    @Override
    public void initService() {
        LOG.info("Initializing service...");

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

        LOG.info("Initialized service.");
    }

    @Override
    public void send(Message message) {
        try {
            channel.basicPublish(StringUtils.EMPTY, queueName, null, message.getBytes());
            LOG.info("Sent {}.", message.toString());
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void destroyService() {
        LOG.info("Destroying service...");

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

        LOG.info("Destroyed service.");
    }
}