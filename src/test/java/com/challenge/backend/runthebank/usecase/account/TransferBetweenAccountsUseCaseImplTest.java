package com.challenge.backend.runthebank.usecase.account;

import com.challenge.backend.runthebank.domain.Account;
import com.challenge.backend.runthebank.domain.dtos.TransferBetweenAccountsResponseDTO;
import com.challenge.backend.runthebank.factories.domain.account.AccountFactory;
import com.challenge.backend.runthebank.gateway.AccountGateway;
import com.challenge.backend.runthebank.usecase.account.impl.TransferBetweenAccountsUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransferBetweenAccountsUseCaseImplTest {

    @Mock
    private final AccountGateway accountGateway = mock(AccountGateway.class);

    @Mock
    private final GetAccountUseCase getAccountUseCase = mock(GetAccountUseCase.class);

    @Mock
    private final SendNotificationUseCase sendNotificationUseCase = mock(SendNotificationUseCase.class);

    @InjectMocks
    private final TransferBetweenAccountsUseCaseImpl transferBetweenAccountsUseCase = new TransferBetweenAccountsUseCaseImpl(
            accountGateway,
            getAccountUseCase,
            sendNotificationUseCase
    );

    @Test
    public void shouldTransferBetweenAccounts(){
        Account accountToDebitMock = AccountFactory.createAccount();
        Account accountToCreditMock = AccountFactory.createAccountToCredit();
        Double transferValue = 2.00;
        Double accountToDebitOlderBalance = accountToDebitMock.getBalance();
        Double accountToCreditOlderBalance = accountToCreditMock.getBalance();

        when(getAccountUseCase.execute(accountToDebitMock.getAgency())).thenReturn(accountToDebitMock);
        when(getAccountUseCase.execute(accountToCreditMock.getAgency())).thenReturn(accountToCreditMock);
        doNothing().when(accountGateway).updateAccountBalance(any(Account.class));
        when(sendNotificationUseCase.execute(accountToDebitMock)).thenReturn(true);
        when(sendNotificationUseCase.execute(accountToCreditMock)).thenReturn(true);

        TransferBetweenAccountsResponseDTO transferBetweenAccountsResponseDTO = transferBetweenAccountsUseCase.execute(accountToDebitMock.getAgency(), accountToCreditMock.getAgency(), 2.00);

        assertEquals((accountToDebitOlderBalance - transferValue), transferBetweenAccountsResponseDTO.accountDebit().balance());
        assertEquals((accountToCreditOlderBalance + transferValue), transferBetweenAccountsResponseDTO.accountCredit().balance());
        assertEquals(transferValue, transferBetweenAccountsResponseDTO.transferValue());
        assertTrue(transferBetweenAccountsResponseDTO.accountDebit().notificationSend());
        assertTrue(transferBetweenAccountsResponseDTO.accountCredit().notificationSend());

        verify(getAccountUseCase, atLeastOnce()).execute(anyLong());
        verify(accountGateway, atLeastOnce()).updateAccountBalance(any(Account.class));
        verify(sendNotificationUseCase, atLeastOnce()).execute(any(Account.class));
    }

    @Test
    public void shouldntHaveSuficientBalance(){
        Account accountToDebitMock = AccountFactory.createAccount();
        Account accountToCreditMock = AccountFactory.createAccountToCredit();
        Double transferValue = 400.00;

        when(getAccountUseCase.execute(accountToDebitMock.getAgency())).thenReturn(accountToDebitMock);
        when(getAccountUseCase.execute(accountToCreditMock.getAgency())).thenReturn(accountToCreditMock);

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> transferBetweenAccountsUseCase.execute(accountToDebitMock.getAgency(), accountToCreditMock.getAgency(), transferValue));

        assertEquals("Saldo insuficiente para a transação", exception.getStatusText());
        verify(getAccountUseCase, atLeastOnce()).execute(anyLong());
    }

    @Test
    public void shouldFindDeactiveAccount(){
        Account accountDeactiveMock = AccountFactory.createDeactiveAccount();
        Account accountActiveMock = AccountFactory.createAccountToCredit();
        Double transferValue = 400.00;

        when(getAccountUseCase.execute(accountDeactiveMock.getAgency())).thenReturn(accountDeactiveMock);

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> transferBetweenAccountsUseCase.execute(accountDeactiveMock.getAgency(), accountActiveMock.getAgency(), transferValue));

        assertEquals("A conta " + accountDeactiveMock.getAgency() + " está desativada.", exception.getStatusText());
        verify(getAccountUseCase, atLeastOnce()).execute(anyLong());
    }
}
