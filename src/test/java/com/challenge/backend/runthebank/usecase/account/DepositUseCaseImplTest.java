package com.challenge.backend.runthebank.usecase.account;

import com.challenge.backend.runthebank.domain.Account;
import com.challenge.backend.runthebank.domain.dtos.AccountBalanceRequestDTO;
import com.challenge.backend.runthebank.domain.dtos.AccountBalanceResponseDTO;
import com.challenge.backend.runthebank.factories.domain.account.AccountFactory;
import com.challenge.backend.runthebank.factories.domain.dtos.AccountBalanceRequestDTOFactory;
import com.challenge.backend.runthebank.gateway.AccountGateway;
import com.challenge.backend.runthebank.usecase.account.impl.DepositUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class DepositUseCaseImplTest {

    @Mock
    private final AccountGateway accountGateway = mock(AccountGateway.class);

    @InjectMocks
    private final DepositUseCaseImpl depositUseCase = new DepositUseCaseImpl(accountGateway);

    @Test
    public void shouldDeposit(){
        final Account accountMock = AccountFactory.createAccount();
        final Double oldValue = accountMock.getBalance();
        final Double depositValue = 100.00;
        final AccountBalanceRequestDTO accountBalanceRequestDTOMock = AccountBalanceRequestDTOFactory.createAccountBalanceRequestDTO(accountMock.getAgency(), depositValue);

        when(accountGateway.getAccount(anyLong())).thenReturn(accountMock);

        AccountBalanceResponseDTO accountBalanceResponseDTO = depositUseCase.execute(accountBalanceRequestDTOMock);

        assertEquals((oldValue + depositValue), accountBalanceResponseDTO.value());
        assertEquals(accountMock.getAgency(), accountBalanceResponseDTO.agency());
        verify(accountGateway, atLeastOnce()).getAccount(anyLong());
        verify(accountGateway, atLeastOnce()).updateAccountBalance(any(Account.class));
    }

    @Test
    public void shouldNotFoundAccount(){
        final Account accountMock = AccountFactory.createAccount();
        AccountBalanceRequestDTO accountBalanceRequestDTOMock = new AccountBalanceRequestDTO(accountMock.getAgency(), 100.00);

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> depositUseCase.execute(accountBalanceRequestDTOMock));

        assertEquals("Conta não cadastrada.", exception.getStatusText());
        verify(accountGateway, atLeastOnce()).getAccount(anyLong());
    }
}
