package com.challenge.backend.runthebank.usecase.account;

import com.challenge.backend.runthebank.domain.Account;
import com.challenge.backend.runthebank.domain.Costumer;
import com.challenge.backend.runthebank.domain.enums.AccountStatus;
import com.challenge.backend.runthebank.gateway.AccountGateway;
import com.challenge.backend.runthebank.usecase.account.impl.DeleteAccountUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.client.HttpClientErrorException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class DeleteAccountUseCaseImplTest {

    @Mock
    private final AccountGateway accountGateway = mock(AccountGateway.class);

    @InjectMocks
    private final DeleteAccountUseCaseImpl deleteCostumerUseCase = new DeleteAccountUseCaseImpl(accountGateway);


    @Test
    public void deleteAccount() {

        when(accountGateway.getAccount(anyLong())).thenReturn(new Account(UUID.randomUUID(), 12345L, 20.0, AccountStatus.ACTIVE, new Costumer()));

        deleteCostumerUseCase.execute(12345L);

        verify(accountGateway, atLeastOnce()).getAccount(anyLong());
        verify(accountGateway, atLeastOnce()).deleteAccount(any());
    }

    @Test
    public void shouldNotFoundAccount(){

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> deleteCostumerUseCase.execute(1234L));

        assertEquals("Conta não cadastrada.", exception.getStatusText());
        verify(accountGateway, atLeastOnce()).getAccount(anyLong());
    }
}
