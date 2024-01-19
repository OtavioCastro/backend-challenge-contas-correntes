package com.challenge.backend.runthebank.usecase.account;

import com.challenge.backend.runthebank.domain.Account;
import com.challenge.backend.runthebank.factories.domain.account.AccountFactory;
import com.challenge.backend.runthebank.factories.integration.NotificationResponseFactory;
import com.challenge.backend.runthebank.integration.NotificationIntegration;
import com.challenge.backend.runthebank.integration.response.NotificationResponse;
import com.challenge.backend.runthebank.usecase.account.impl.SendNotificationUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SendNotificationUseCaseImplTest {

    @Mock
    private final NotificationIntegration notificationIntegration = mock(NotificationIntegration.class);

    @InjectMocks
    private final SendNotificationUseCaseImpl sendNotificationUseCase = new SendNotificationUseCaseImpl(notificationIntegration);

    @Test
    public void shouldSendNotification(){
        NotificationResponse notificationResponseMock = NotificationResponseFactory.createNotificationResponse();
        Account accountMock = AccountFactory.createAccount();

        when(notificationIntegration.sendNotification()).thenReturn(notificationResponseMock);

        boolean sendNotificationResponse = sendNotificationUseCase.execute(accountMock);

        assertTrue(sendNotificationResponse);
        verify(notificationIntegration, atLeastOnce()).sendNotification();
    }

    @Test
    public void shouldNotSendNotification(){
        Account accountMock = AccountFactory.createAccount();

        when(notificationIntegration.sendNotification()).thenThrow(new HttpServerErrorException(HttpStatus.GATEWAY_TIMEOUT));

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> sendNotificationUseCase.execute(accountMock));

        assertEquals("Não foi possível realizar o envio da notificação para a conta " + accountMock.getAgency(), exception.getStatusText());
        verify(notificationIntegration, atLeastOnce()).sendNotification();
    }
}
