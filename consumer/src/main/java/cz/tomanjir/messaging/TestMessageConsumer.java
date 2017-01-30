package cz.tomanjir.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMessageConsumer implements MessageConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(TestMessageConsumer.class);

    @Override
    public void consume(Message message) {
        LOG.info("Message arrived, message={}.", message);
    }
}