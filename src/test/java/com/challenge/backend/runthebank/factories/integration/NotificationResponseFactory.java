package com.challenge.backend.runthebank.factories.integration;

import com.challenge.backend.runthebank.integration.response.NotificationResponse;

public class NotificationResponseFactory {

    public static NotificationResponse createNotificationResponse(){
        return new NotificationResponse(true);
    }
}
