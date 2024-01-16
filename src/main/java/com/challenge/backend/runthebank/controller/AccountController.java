package com.challenge.backend.runthebank.controller;

import com.challenge.backend.runthebank.controller.converter.AccountToAccountResponseDTOConverter;
import com.challenge.backend.runthebank.domain.Account;
import com.challenge.backend.runthebank.domain.dtos.*;
import com.challenge.backend.runthebank.usecase.account.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final SaveAccountUseCase saveAccountUseCase;
    private final GetAccountUseCase getAccountUseCase;
    private final UpdateAccountUseCase updateAccountUseCase;
    private final DeleteAccountUseCase deleteAccountUseCase;
    private final TransferBetweenAccountsUseCase transferBetweenAccountsUseCase;
    private final DepositUseCase depositUseCase;
    private final WithdrawUseCase withdrawUseCase;
    private final AccountToAccountResponseDTOConverter toAccountResponseDTOConverter;

    public AccountController(SaveAccountUseCase saveAccountUseCase,
                             GetAccountUseCase getAccountUseCase,
                             UpdateAccountUseCase updateAccountUseCase,
                             DeleteAccountUseCase deleteAccountUseCase,
                             TransferBetweenAccountsUseCase transferBetweenAccountsUseCase,
                             DepositUseCase depositUseCase,
                             WithdrawUseCase withdrawUseCase,
                             AccountToAccountResponseDTOConverter toAccountResponseDTOConverter) {
        this.saveAccountUseCase = saveAccountUseCase;
        this.getAccountUseCase = getAccountUseCase;
        this.updateAccountUseCase = updateAccountUseCase;
        this.deleteAccountUseCase = deleteAccountUseCase;
        this.transferBetweenAccountsUseCase = transferBetweenAccountsUseCase;
        this.depositUseCase = depositUseCase;
        this.withdrawUseCase = withdrawUseCase;
        this.toAccountResponseDTOConverter = toAccountResponseDTOConverter;
    }

    @PostMapping
    public ResponseEntity<AccountResponseDTO> saveAccount(@RequestBody AccountSaveDTO accountSaveDTO){
        return ResponseEntity.ok(
                toAccountResponseDTOConverter.convert(saveAccountUseCase.execute(accountSaveDTO))
        );
    }

    @GetMapping("/{agency}")
    public ResponseEntity<AccountResponseDTO> getAccount(@PathVariable Long agency){
        return ResponseEntity.ok(
                toAccountResponseDTOConverter.convert(getAccountUseCase.execute(agency))
        );
    }

    @PutMapping
    public ResponseEntity<Void> updateAccount(@RequestBody AccountUpdateDTO accountUpdateDTO){
        updateAccountUseCase.execute(accountUpdateDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable UUID accountId){
        deleteAccountUseCase.execute(accountId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/deposit")
    public ResponseEntity<AccountBalanceResponseDTO> deposit(@RequestBody AccountBalanceRequestDTO accountBalanceRequestDTO){
        return ResponseEntity.ok(depositUseCase.execute(accountBalanceRequestDTO));
    }

    @PostMapping("withdraw")
    public ResponseEntity<AccountBalanceResponseDTO> withdraw(@RequestBody AccountBalanceRequestDTO accountBalanceRequestDTO){
        return ResponseEntity.ok(withdrawUseCase.execute(accountBalanceRequestDTO));
    }

    @PostMapping("/transfer")
    public ResponseEntity<Void> transferBetweenAccounts(@RequestBody TransferBetweenAccountsDTO transferBetweenAccountsDTO){
        transferBetweenAccountsUseCase.execute(
                transferBetweenAccountsDTO.agencyDebit(),
                transferBetweenAccountsDTO.agencyCredit(),
                transferBetweenAccountsDTO.transferValue());
        return ResponseEntity.noContent().build();
    }
}
