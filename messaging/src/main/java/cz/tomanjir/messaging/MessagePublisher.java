package cz.tomanjir.messaging;

public interface MessagePublisher<T extends Message> {
    void publish(T message);
}