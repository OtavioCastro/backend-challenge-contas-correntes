package com.challenge.backend.runthebank.usecase.account.impl;

import com.challenge.backend.runthebank.domain.Account;
import com.challenge.backend.runthebank.integration.NotificationIntegration;
import com.challenge.backend.runthebank.integration.response.NotificationResponse;
import com.challenge.backend.runthebank.usecase.account.SendNotificationUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class SendNotificationUseCaseImpl implements SendNotificationUseCase {

    private final NotificationIntegration notificationIntegration;

    public SendNotificationUseCaseImpl(NotificationIntegration notificationIntegration) {
        this.notificationIntegration = notificationIntegration;
    }

    @Override
    public Boolean execute(Account account) {
        try{
            NotificationResponse notificationResponse = notificationIntegration.sendNotification();
            return notificationResponse.getMessageSent();
        } catch (Exception ex){
            throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "Não foi possível realizar o envio da notificação para a conta " + account.getAgency());
        }
    }
}
