package cz.tomanjir.messaging.rabbitmq;

import com.rabbitmq.client.AMQP;
import cz.tomanjir.messaging.Message;

public class RabbitMqMessage implements Message {

    private final AMQP.BasicProperties properties;
    private final byte[] bytes;

    public RabbitMqMessage(byte[] bytes) {
        this(null, bytes);
    }

    public RabbitMqMessage(AMQP.BasicProperties properties, byte[] bytes) {
        this.properties = properties;
        this.bytes = bytes;
    }

    public AMQP.BasicProperties getProperties() {
        return properties;
    }

    @Override
    public byte[] getBytes() {
        return bytes;
    }
}
