package cz.tomanjir.messaging;

public interface Connector {
    void connect();
    void disconnect();
    Object getMediator();
}
