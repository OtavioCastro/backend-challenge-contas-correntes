package com.challenge.backend.runthebank.gatweway;

import com.challenge.backend.runthebank.domain.Account;
import com.challenge.backend.runthebank.domain.dtos.AccountUpdateDTO;
import com.challenge.backend.runthebank.factories.domain.account.AccountFactory;
import com.challenge.backend.runthebank.factories.domain.dtos.AccountUpdateDTOFactory;
import com.challenge.backend.runthebank.factories.repository.AccountEntityFactory;
import com.challenge.backend.runthebank.factories.repository.CostumerEntityFactory;
import com.challenge.backend.runthebank.repository.AccountRepository;
import com.challenge.backend.runthebank.repository.CostumerRepository;
import com.challenge.backend.runthebank.repository.converter.AccountEntityToAccountConverter;
import com.challenge.backend.runthebank.repository.converter.AccountToAccountEntityConverter;
import com.challenge.backend.runthebank.repository.entity.AccountEntity;
import com.challenge.backend.runthebank.repository.entity.CostumerEntity;
import com.challenge.backend.runthebank.repository.impl.AccountGatewayImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AccountGatewayImplTest {

    @Mock
    private final AccountRepository accountRepository = mock(AccountRepository.class);

    @Mock
    private final CostumerRepository costumerRepository = mock(CostumerRepository.class);

    @Spy
    private final AccountEntityToAccountConverter toAccountConverter = new AccountEntityToAccountConverter(new ModelMapper());

    @Spy
    private final AccountToAccountEntityConverter toAccountEntityConverter = new AccountToAccountEntityConverter(new ModelMapper());

    @InjectMocks
    private final AccountGatewayImpl accountGateway = new AccountGatewayImpl(accountRepository, costumerRepository, toAccountConverter, toAccountEntityConverter);

    @Test
    public void shouldSaveAccount(){
        Account accountMock = AccountFactory.createAccount();
        AccountEntity accountEntityMock = toAccountEntityConverter.convert(accountMock);
        CostumerEntity costumerEntityMock = CostumerEntityFactory.createCostumerEntity();

        when(accountRepository.save(any(AccountEntity.class))).thenReturn(accountEntityMock);
        when(costumerRepository.findByDocument(anyString())).thenReturn(costumerEntityMock);

        Account accountResponse = accountGateway.saveAccount(accountMock);

        assertEquals(1, costumerEntityMock.getAccounts().size());
        assertEquals(accountMock.getAccountId(), accountResponse.getAccountId());

        verify(accountRepository, atLeastOnce()).save(any(AccountEntity.class));
        verify(costumerRepository, atLeastOnce()).findByDocument(anyString());
    }

    @Test
    public void shouldGetAccount(){
        AccountEntity accountEntityMock = AccountEntityFactory.createAccountEntity();

        when(accountRepository.findByAgency(anyLong())).thenReturn(accountEntityMock);

        Account accountResponse = accountGateway.getAccount(12345L);

        assertEquals(accountEntityMock.getAccountId(), accountResponse.getAccountId());
        verify(accountRepository, atLeastOnce()).findByAgency(anyLong());
    }

    @Test
    public void shouldNotFindAccount(){

        Account accountResponse = accountGateway.getAccount(12345L);

        assertNull(accountResponse);
        verify(accountRepository, atLeastOnce()).findByAgency(anyLong());
    }

    @Test
    public void shouldUpdateAccount() {
        AccountEntity accountEntityMock = AccountEntityFactory.createAccountEntity();
        Long oldAgencyValue = accountEntityMock.getAgency();
        Long newAgencyValue = 67890L;
        AccountUpdateDTO accountUpdateDTOMock = AccountUpdateDTOFactory.createAccountUpdateDTO(oldAgencyValue, newAgencyValue);

        when(accountRepository.findByAgency(anyLong())).thenReturn(accountEntityMock);

        accountGateway.updateAccount(accountUpdateDTOMock);

        assertEquals(newAgencyValue, accountEntityMock.getAgency());
        verify(accountRepository, atLeastOnce()).findByAgency(anyLong());
    }

    @Test
    public void shouldUpdateBalance(){
        AccountEntity accountEntityMock = AccountEntityFactory.createAccountEntity();
        Double oldAccountBalance = accountEntityMock.getBalance();
        Account accountMock = AccountFactory.createAccount();
        accountMock.setBalance(2000.00);

        when(accountRepository.findByAgency(anyLong())).thenReturn(accountEntityMock);

        accountGateway.updateAccountBalance(accountMock);
        assertNotEquals(oldAccountBalance, accountEntityMock.getBalance());
        assertEquals(accountMock.getBalance(), accountEntityMock.getBalance());
        verify(accountRepository, atLeastOnce()).save(any(AccountEntity.class));
    }

    @Test
    public void shouldDeleteAccount(){
        doNothing().when(accountRepository).deleteById(any(UUID.class));

        accountGateway.deleteAccount(UUID.randomUUID());

        verify(accountRepository, atLeastOnce()).deleteById(any(UUID.class));
    }
}
