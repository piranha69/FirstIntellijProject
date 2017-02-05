package cz.tomanjir.consumer.messaging.handler;

import cz.tomanjir.messaging.Message;
import cz.tomanjir.messaging.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogMessageHandler implements MessageHandler {

    private static final Logger LOG = LoggerFactory.getLogger(LogMessageHandler.class);

    @Override
    public void handle(Message message) {
        LOG.info("Message arrived, message={}.", message);
    }
}