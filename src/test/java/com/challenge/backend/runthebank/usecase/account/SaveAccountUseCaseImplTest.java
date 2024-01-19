package com.challenge.backend.runthebank.usecase.account;

import com.challenge.backend.runthebank.domain.Account;
import com.challenge.backend.runthebank.domain.Costumer;
import com.challenge.backend.runthebank.domain.dtos.AccountSaveDTO;
import com.challenge.backend.runthebank.factories.domain.account.AccountFactory;
import com.challenge.backend.runthebank.factories.domain.costumer.CostumerFactory;
import com.challenge.backend.runthebank.factories.domain.dtos.AccountSaveDTOFactory;
import com.challenge.backend.runthebank.gateway.AccountGateway;
import com.challenge.backend.runthebank.usecase.account.impl.SaveAccountUseCaseImpl;
import com.challenge.backend.runthebank.usecase.costumer.GetCostumerUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class SaveAccountUseCaseImplTest {

    @Mock
    private final AccountGateway accountGateway = mock(AccountGateway.class);
    @Mock
    private final GetCostumerUseCase getCostumerUseCase = mock(GetCostumerUseCase.class);

    @InjectMocks
    private final SaveAccountUseCaseImpl saveAccountUseCase = new SaveAccountUseCaseImpl(accountGateway, getCostumerUseCase);

    @Test
    public void shouldSaveAccount(){
        final Costumer costumerMock = CostumerFactory.createCostumer();
        final Account accountMock = AccountFactory.createAccount();
        final AccountSaveDTO accountSaveDTOMock = AccountSaveDTOFactory.createAccountSaveDTO(accountMock.getAgency(), costumerMock.getDocument());

        when(getCostumerUseCase.execute(anyString())).thenReturn(costumerMock);
        when(accountGateway.saveAccount(any(Account.class))).thenReturn(accountMock);

        Account accountResponse = saveAccountUseCase.execute(accountSaveDTOMock);

        assertEquals(accountMock.getAgency(), accountResponse.getAgency());
        assertEquals(costumerMock.getDocument(), accountResponse.getCostumer().getDocument());
        verify(getCostumerUseCase, atLeastOnce()).execute(anyString());
        verify(accountGateway, atLeastOnce()).getAccount(anyLong());
        verify(accountGateway, atLeastOnce()).saveAccount(any(Account.class));
    }

    @Test
    public void shouldExistsAccount(){
        final Costumer costumerMock = CostumerFactory.createCostumer();
        final Account accountMock = AccountFactory.createAccount();
        final AccountSaveDTO accountSaveDTOMock = AccountSaveDTOFactory.createAccountSaveDTO(accountMock.getAgency(), costumerMock.getDocument());

        when(accountGateway.getAccount(anyLong())).thenReturn(accountMock);

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> saveAccountUseCase.execute(accountSaveDTOMock));

        assertEquals("Número de conta já cadastrado.", exception.getStatusText());
        verify(accountGateway, atLeastOnce()).getAccount(anyLong());
    }
}
