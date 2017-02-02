package cz.tomanjir.messaging;

public interface Connector<T> {
    void connect();
    void disconnect();
    T getMediator();
}
