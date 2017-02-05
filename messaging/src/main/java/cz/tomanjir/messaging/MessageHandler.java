package cz.tomanjir.messaging;

public interface MessageHandler<T extends Message> {
    void handle(T message);
}