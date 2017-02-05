package cz.tomanjir.messaging.rabbitmq;

import com.google.common.base.MoreObjects;

import javax.inject.Inject;

public class RabbitMqPublisherProperties extends RabbitMqProperties {

    public enum RabbitMqDeliveryMode {
        TRANSIENT(0),
        PERSISTENT(2);

        private final Integer value;

        RabbitMqDeliveryMode(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }

    @Inject
    private String appId;

    @Inject
    private RabbitMqDeliveryMode deliveryMode;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public RabbitMqDeliveryMode getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(RabbitMqDeliveryMode deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("appId", appId)
                .add("deliveryMode", deliveryMode)
                .toString();
    }
}