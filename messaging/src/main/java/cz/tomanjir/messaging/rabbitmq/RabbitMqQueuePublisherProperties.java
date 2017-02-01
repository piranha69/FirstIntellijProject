package cz.tomanjir.messaging.rabbitmq;

import com.google.common.base.MoreObjects;
import com.rabbitmq.client.AMQP;

import javax.inject.Inject;

public class RabbitMqQueuePublisherProperties extends RabbitMqQueueProperties {

    @Inject
    private AMQP.BasicProperties publishProperties;

    public AMQP.BasicProperties getPublishProperties() {
        return publishProperties;
    }

    public void setPublishProperties(AMQP.BasicProperties publishProperties) {
        this.publishProperties = publishProperties;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("publishProperties", publishProperties)
                .toString();
    }
}