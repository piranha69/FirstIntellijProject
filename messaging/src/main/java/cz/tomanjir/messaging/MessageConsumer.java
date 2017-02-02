package cz.tomanjir.messaging;

public interface MessageConsumer<T extends Message> {
    void consume(T message);
}
