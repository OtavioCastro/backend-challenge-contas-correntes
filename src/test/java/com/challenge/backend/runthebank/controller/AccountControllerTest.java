package com.challenge.backend.runthebank.controller;

import com.challenge.backend.runthebank.controller.converter.AccountToAccountResponseDTOConverter;
import com.challenge.backend.runthebank.domain.Account;
import com.challenge.backend.runthebank.domain.dtos.*;
import com.challenge.backend.runthebank.factories.domain.account.AccountFactory;
import com.challenge.backend.runthebank.factories.domain.dtos.*;
import com.challenge.backend.runthebank.usecase.account.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AccountControllerTest {

    @Mock
    private final SaveAccountUseCase saveAccountUseCase = mock(SaveAccountUseCase.class);

    @Mock
    private final GetAccountUseCase getAccountUseCase = mock(GetAccountUseCase.class);

    @Mock
    private final UpdateAccountUseCase updateAccountUseCase = mock(UpdateAccountUseCase.class);

    @Mock
    private final DeleteAccountUseCase deleteAccountUseCase = mock(DeleteAccountUseCase.class);

    @Mock
    private final TransferBetweenAccountsUseCase transferBetweenAccountsUseCase = mock(TransferBetweenAccountsUseCase.class);

    @Mock
    private final DepositUseCase depositUseCase = mock(DepositUseCase.class);

    @Mock
    private final WithdrawUseCase withdrawUseCase = mock(WithdrawUseCase.class);

    @Spy
    private final AccountToAccountResponseDTOConverter toAccountResponseDTOConverter = new AccountToAccountResponseDTOConverter();

    @InjectMocks
    AccountController accountController = new AccountController(
            saveAccountUseCase,
            getAccountUseCase,
            updateAccountUseCase,
            deleteAccountUseCase,
            transferBetweenAccountsUseCase,
            depositUseCase,
            withdrawUseCase,
            toAccountResponseDTOConverter
    );

    @Test
    public void shouldSaveAccount(){
        Account accountMock = AccountFactory.createAccount();
        AccountSaveDTO accountSaveDTOMock = AccountSaveDTOFactory.createAccountSaveDTO(accountMock.getAgency(), accountMock.getCostumer().getDocument());

        when(saveAccountUseCase.execute(any(AccountSaveDTO.class))).thenReturn(accountMock);

        AccountResponseDTO accountResponseDTO = accountController.saveAccount(accountSaveDTOMock).getBody();

        assert accountResponseDTO != null;
        assertEquals(accountMock.getAccountId(), accountResponseDTO.accountId());
        verify(saveAccountUseCase, atLeastOnce()).execute(any(AccountSaveDTO.class));
    }

    @Test
    public void shouldGetAccount(){
        Account accountMock = AccountFactory.createAccount();

        when(getAccountUseCase.execute(anyLong())).thenReturn(accountMock);

        AccountResponseDTO accountResponseDTO = accountController.getAccount(accountMock.getAgency()).getBody();

        assert accountResponseDTO != null;
        assertEquals(accountMock.getAccountId(), accountResponseDTO.accountId());
        verify(getAccountUseCase, atLeastOnce()).execute(anyLong());
    }

    @Test
    public void shouldUpdateAccount(){
        AccountUpdateDTO accountUpdateDTOMock = AccountUpdateDTOFactory.createAccountUpdateDTO(12345L, 98765L);

        doNothing().when(updateAccountUseCase).execute(any(AccountUpdateDTO.class));

        accountController.updateAccount(accountUpdateDTOMock);
        verify(updateAccountUseCase, atLeastOnce()).execute(any(AccountUpdateDTO.class));
    }

    @Test
    public void shouldDeleteAccount(){

        doNothing().when(deleteAccountUseCase).execute(anyLong());

        accountController.deleteAccount(12345L);
        verify(deleteAccountUseCase, atLeastOnce()).execute(anyLong());
    }

    @Test
    public void shouldDeposit(){
        AccountBalanceRequestDTO accountBalanceRequestDTOMock = AccountBalanceRequestDTOFactory.createAccountBalanceRequestDTO(12345L, 200.00);
        AccountBalanceResponseDTO accountBalanceResponseDTOMock = AccountBalanceResponseDTOFactory.createAccountBalanceResponseDTO(12345L, 200.00);

        when(depositUseCase.execute(any(AccountBalanceRequestDTO.class))).thenReturn(accountBalanceResponseDTOMock);

        AccountBalanceResponseDTO accountBalanceResponseDTO = accountController.deposit(accountBalanceRequestDTOMock).getBody();

        assert accountBalanceResponseDTO != null;
        assertEquals(accountBalanceResponseDTOMock.agency(), accountBalanceResponseDTO.agency());
        assertEquals(accountBalanceResponseDTOMock.value(), accountBalanceResponseDTO.value());
        verify(depositUseCase, atLeastOnce()).execute(any(AccountBalanceRequestDTO.class));
    }

    @Test
    public void shouldWithdraw(){
        AccountBalanceRequestDTO accountBalanceRequestDTOMock = AccountBalanceRequestDTOFactory.createAccountBalanceRequestDTO(12345L, 190.00);
        AccountBalanceResponseDTO accountBalanceResponseDTOMock = AccountBalanceResponseDTOFactory.createAccountBalanceResponseDTO(12345L, 190.00);

        when(withdrawUseCase.execute(any(AccountBalanceRequestDTO.class))).thenReturn(accountBalanceResponseDTOMock);

        AccountBalanceResponseDTO accountBalanceResponseDTO = accountController.withdraw(accountBalanceRequestDTOMock).getBody();

        assert accountBalanceResponseDTO != null;
        assertEquals(accountBalanceResponseDTOMock.agency(), accountBalanceResponseDTO.agency());
        assertEquals(accountBalanceResponseDTOMock.value(), accountBalanceResponseDTO.value());
        verify(withdrawUseCase, atLeastOnce()).execute(any(AccountBalanceRequestDTO.class));
    }

    @Test
    public void shouldTransferBetweenTwoAccounts(){
        TransferBetweenAccountsResponseDTO transferBetweenAccountsResponseDTOMock = TransferBetweenAccountsResponseDTOFactory.createTransferBetweenAccountsDTO();
        Long agencyDebit = transferBetweenAccountsResponseDTOMock.accountDebit().agency();
        Long agencyCredit = transferBetweenAccountsResponseDTOMock.accountCredit().agency();
        Double transferValue = transferBetweenAccountsResponseDTOMock.transferValue();
        TransferBetweenAccountsRequestDTO transferBetweenAccountsRequestDTOMock = TransferBetweenAccountsRequestDTOFactory.createTransferBetweenAccountsRequestDTO(agencyDebit, agencyCredit, transferValue);

        when(transferBetweenAccountsUseCase.execute(anyLong(), anyLong(), anyDouble())).thenReturn(transferBetweenAccountsResponseDTOMock);

        TransferBetweenAccountsResponseDTO transferBetweenAccountsResponseDTO = accountController.transferBetweenAccounts(transferBetweenAccountsRequestDTOMock).getBody();

        assert transferBetweenAccountsResponseDTO != null;
        assertEquals(transferBetweenAccountsResponseDTOMock.accountDebit().balance(), transferBetweenAccountsResponseDTO.accountDebit().balance());
        assertEquals(transferBetweenAccountsResponseDTOMock.accountCredit().balance(), transferBetweenAccountsResponseDTO.accountCredit().balance());
        assertEquals(transferBetweenAccountsResponseDTOMock.transferValue(), transferBetweenAccountsResponseDTO.transferValue());
        verify(transferBetweenAccountsUseCase, atLeastOnce()).execute(anyLong(), anyLong(), anyDouble());
    }
}
