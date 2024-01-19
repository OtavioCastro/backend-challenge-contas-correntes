package com.challenge.backend.runthebank.usecase.account;

import com.challenge.backend.runthebank.domain.Account;
import com.challenge.backend.runthebank.factories.domain.account.AccountFactory;
import com.challenge.backend.runthebank.gateway.AccountGateway;
import com.challenge.backend.runthebank.usecase.account.impl.GetAccountUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class GetAccountUseCaseImplTest {

    @Mock
    private final AccountGateway accountGateway = mock(AccountGateway.class);

    @InjectMocks
    private final GetAccountUseCaseImpl getAccountUseCase = new GetAccountUseCaseImpl(accountGateway);

    @Test
    public void shouldGetAccount() {
        final Account accountMock = AccountFactory.createAccount();

        when(accountGateway.getAccount(anyLong())).thenReturn(accountMock);

        Account accountResponse = getAccountUseCase.execute(accountMock.getAgency());

        assertEquals(accountMock.getAccountId(), accountResponse.getAccountId());
        assertEquals(accountMock.getAgency(), accountResponse.getAgency());
        assertEquals(accountMock.getBalance(), accountResponse.getBalance());
        assertEquals(accountMock.getAccountStatus(), accountResponse.getAccountStatus());
        verify(accountGateway, atLeastOnce()).getAccount(anyLong());
    }

    @Test
    public void shouldNotFoundAccount() {
        final Account accountMock = AccountFactory.createAccount();

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> getAccountUseCase.execute(accountMock.getAgency()));

        assertEquals("Conta bancária não encontrada", exception.getStatusText());
        verify(accountGateway, atLeastOnce()).getAccount(anyLong());
    }
}
