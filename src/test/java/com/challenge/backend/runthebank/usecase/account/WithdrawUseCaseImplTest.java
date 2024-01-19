package com.challenge.backend.runthebank.usecase.account;

import com.challenge.backend.runthebank.domain.Account;
import com.challenge.backend.runthebank.domain.dtos.AccountBalanceRequestDTO;
import com.challenge.backend.runthebank.domain.dtos.AccountBalanceResponseDTO;
import com.challenge.backend.runthebank.factories.domain.account.AccountFactory;
import com.challenge.backend.runthebank.factories.domain.dtos.AccountBalanceRequestDTOFactory;
import com.challenge.backend.runthebank.gateway.AccountGateway;
import com.challenge.backend.runthebank.usecase.account.impl.WithdrawUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class WithdrawUseCaseImplTest {

    @Mock
    private final AccountGateway accountGateway = mock(AccountGateway.class);

    @InjectMocks
    private final WithdrawUseCaseImpl withdrawUseCase = new WithdrawUseCaseImpl(accountGateway);

    @Test
    public void shouldWithdraw(){
        Account accountMock = AccountFactory.createAccount();
        Double accountMockOlderBalance = accountMock.getBalance();
        Double withdrawValue = 3.00;
        AccountBalanceRequestDTO accountBalanceRequestDTOMock = AccountBalanceRequestDTOFactory.createAccountBalanceRequestDTO(accountMock.getAgency(), withdrawValue);

        when(accountGateway.getAccount(anyLong())).thenReturn(accountMock);
        doNothing().when(accountGateway).updateAccountBalance(any(Account.class));

        AccountBalanceResponseDTO accountBalanceResponseDTO = withdrawUseCase.execute(accountBalanceRequestDTOMock);

        assertEquals((accountMockOlderBalance - withdrawValue), accountBalanceResponseDTO.value());
        verify(accountGateway, atLeastOnce()).getAccount(anyLong());
        verify(accountGateway, atLeastOnce()).updateAccountBalance(any(Account.class));
    }

    @Test
    public void shouldntHaveSuficientBalance(){
        Account accountMock = AccountFactory.createAccount();
        Double withdrawValue = 400.00;
        AccountBalanceRequestDTO accountBalanceRequestDTOMock = AccountBalanceRequestDTOFactory.createAccountBalanceRequestDTO(accountMock.getAgency(), withdrawValue);

        when(accountGateway.getAccount(anyLong())).thenReturn(accountMock);

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> withdrawUseCase.execute(accountBalanceRequestDTOMock));

        assertEquals("Saldo insuficiente para a transação", exception.getStatusText());
        verify(accountGateway, atLeastOnce()).getAccount(anyLong());
    }
}
