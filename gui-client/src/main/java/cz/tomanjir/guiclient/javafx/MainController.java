package cz.tomanjir.guiclient.javafx;

import cz.tomanjir.messages.Messages;
import cz.tomanjir.messaging.Message;
import cz.tomanjir.messaging.MessagePublisher;
import cz.tomanjir.messaging.rabbitmq.RabbitMqMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class MainController {

    private static final Logger LOG = LoggerFactory.getLogger(MainController.class);

    private MessagePublisher<Message> messagePublisher;

    @FXML
    private TextField messageField;

    @Inject
    public MainController(MessagePublisher<Message> messagePublisher) {
        this.messagePublisher = messagePublisher;
    }

    public void handleSendButtonAction(ActionEvent actionEvent) {
        String message = messageField.getText();

        Messages.TextMessage textMessage = Messages.TextMessage.newBuilder()
                .setText(message)
                .build();

        LOG.info("Publishing {} message...", message);
        messagePublisher.publish(new RabbitMqMessage(textMessage.toByteArray()));
        LOG.info("Published {} message.", message);

        messageField.clear();
        messageField.requestFocus();
    }
}