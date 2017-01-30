package cz.tomanjir.messaging;

public interface MessageConsumer {
    void consume(Message message);
}
