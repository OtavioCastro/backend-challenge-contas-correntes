package com.challenge.backend.runthebank.integration;

import com.challenge.backend.runthebank.integration.response.NotificationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "notification", url = "${run-the-bank.url.notification}")
public interface NotificationIntegration {

    @GetMapping
    NotificationResponse sendNotification();
}
