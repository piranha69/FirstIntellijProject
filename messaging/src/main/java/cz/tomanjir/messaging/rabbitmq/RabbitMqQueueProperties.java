package cz.tomanjir.messaging.rabbitmq;

import com.google.common.base.MoreObjects;

import javax.inject.Inject;
import java.util.Map;

public class RabbitMqQueueProperties {

    @Inject
    private String queue;

    @Inject
    private boolean durable;

    @Inject
    private boolean exclusive;

    @Inject
    private boolean autoDelete;

    @Inject
    private Map<String, Object> arguments;

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public boolean isDurable() {
        return durable;
    }

    public void setDurable(boolean durable) {
        this.durable = durable;
    }

    public boolean isExclusive() {
        return exclusive;
    }

    public void setExclusive(boolean exclusive) {
        this.exclusive = exclusive;
    }

    public boolean isAutoDelete() {
        return autoDelete;
    }

    public void setAutoDelete(boolean autoDelete) {
        this.autoDelete = autoDelete;
    }

    public Map<String, Object> getArguments() {
        return arguments;
    }

    public void setArguments(Map<String, Object> arguments) {
        this.arguments = arguments;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("queue", queue)
                .add("durable", durable)
                .add("exclusive", exclusive)
                .add("autoDelete", autoDelete)
                .add("arguments", arguments)
                .toString();
    }
}