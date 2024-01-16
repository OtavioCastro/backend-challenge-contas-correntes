package com.challenge.backend.runthebank.integration.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class NotificationResponse implements Serializable {

    @JsonProperty("messageSent")
    private boolean messageSent;

    public NotificationResponse(){}

    public NotificationResponse(boolean messageSent) {
        this.messageSent = messageSent;
    }

    public boolean getMessageSent() {
        return messageSent;
    }

    public void setMessageSent(boolean messageSent) {
        this.messageSent = messageSent;
    }
}
