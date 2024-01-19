package com.challenge.backend.runthebank.usecase.account;

import com.challenge.backend.runthebank.domain.Account;
import com.challenge.backend.runthebank.domain.dtos.AccountUpdateDTO;
import com.challenge.backend.runthebank.factories.domain.account.AccountFactory;
import com.challenge.backend.runthebank.factories.domain.dtos.AccountUpdateDTOFactory;
import com.challenge.backend.runthebank.gateway.AccountGateway;
import com.challenge.backend.runthebank.usecase.account.impl.UpdateAccountUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class UpdateAccountUseCaseImplTest {

    @Mock
    private final AccountGateway accountGateway = mock(AccountGateway.class);

    @InjectMocks
    private final UpdateAccountUseCaseImpl updateAccountUseCase = new UpdateAccountUseCaseImpl(accountGateway);

    @Test
    public void shouldUpdateAccount(){
        Account accountMock = AccountFactory.createAccount();
        AccountUpdateDTO accountUpdateDTOMock = AccountUpdateDTOFactory.createAccountUpdateDTO(accountMock.getAgency(), 645321L);

        when(accountGateway.getAccount(anyLong())).thenReturn(accountMock);
        doNothing().when(accountGateway).updateAccount(any(AccountUpdateDTO.class));

        updateAccountUseCase.execute(accountUpdateDTOMock);

        verify(accountGateway, atLeastOnce()).getAccount(anyLong());
        verify(accountGateway, atLeastOnce()).updateAccount(any(AccountUpdateDTO.class));
    }

    @Test
    public void shouldNotFoundAccount() {
        Account accountMock = AccountFactory.createAccount();
        AccountUpdateDTO accountUpdateDTOMock = AccountUpdateDTOFactory.createAccountUpdateDTO(accountMock.getAgency(), 645321L);

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> updateAccountUseCase.execute(accountUpdateDTOMock));

        assertEquals("Conta não cadastrada.", exception.getStatusText());
        verify(accountGateway, atLeastOnce()).getAccount(anyLong());
    }
}
