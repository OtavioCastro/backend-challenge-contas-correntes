package com.challenge.backend.runthebank.controller;

import com.challenge.backend.runthebank.controller.converter.AccountToAccountResponseDTOConverter;
import com.challenge.backend.runthebank.domain.dtos.*;
import com.challenge.backend.runthebank.usecase.account.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "Cadastra uma conta de cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Conta cadastrada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountResponseDTO.class)) }),
            @ApiResponse(responseCode = "422", description = "Número de conta já cadastrado")
    })
    @PostMapping
    public ResponseEntity<AccountResponseDTO> saveAccount(@RequestBody @Valid AccountSaveDTO accountSaveDTO){
        return ResponseEntity.ok(
                toAccountResponseDTOConverter.convert(saveAccountUseCase.execute(accountSaveDTO))
        );
    }

    @Operation(summary = "Obtém uma conta cadastrada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Conta encontrada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountResponseDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Conta não encontrada")
    })
    @GetMapping("/{agency}")
    public ResponseEntity<AccountResponseDTO> getAccount(
            @Parameter(description = "Número da agência")
            @Valid
            @NotBlank(message = "O número da agência não pode ser vazio")
            @PathVariable Long agency){
        return ResponseEntity.ok(
                toAccountResponseDTOConverter.convert(getAccountUseCase.execute(agency))
        );
    }

    @Operation(summary = "Atualiza uma conta cadastrada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Conta atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Conta não encontrada")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping
    public ResponseEntity<Void> updateAccount(@RequestBody @Valid AccountUpdateDTO accountUpdateDTO){
        updateAccountUseCase.execute(accountUpdateDTO);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Exclui uma conta cadastrada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Conta excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Conta não encontrada")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deleteAccount(
            @NotBlank(message = "O número da agência não pode ser vazio")
            @PathVariable Long agency){
        deleteAccountUseCase.execute(agency);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Realiza Depósito em conta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Depósito realizado com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AccountBalanceResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Conta não encontrada")
    })
    @PostMapping("/deposit")
    public ResponseEntity<AccountBalanceResponseDTO> deposit(@RequestBody AccountBalanceRequestDTO accountBalanceRequestDTO){
        return ResponseEntity.ok(depositUseCase.execute(accountBalanceRequestDTO));
    }

    @Operation(summary = "Realiza Saque da conta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Depósito realizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AccountBalanceResponseDTO.class))),
            @ApiResponse(responseCode = "422", description = "Saldo insuficiente"),
            @ApiResponse(responseCode = "404", description = "Conta não encontrada")
    })
    @PostMapping("withdraw")
    public ResponseEntity<AccountBalanceResponseDTO> withdraw(@RequestBody AccountBalanceRequestDTO accountBalanceRequestDTO){
        return ResponseEntity.ok(withdrawUseCase.execute(accountBalanceRequestDTO));
    }

    @Operation(summary = "Realiza transferência entre duas contas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transferência realizada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TransferBetweenAccountsResponseDTO.class))),
            @ApiResponse(responseCode = "422", description = "Saldo insuficiente"),
            @ApiResponse(responseCode = "404", description = "Conta não encontrada")
    })
    @PostMapping("/transfer")
    public ResponseEntity<TransferBetweenAccountsResponseDTO> transferBetweenAccounts(@RequestBody TransferBetweenAccountsRequestDTO transferBetweenAccountsRequestDTO){
        TransferBetweenAccountsResponseDTO transferBetweenAccountsResponseDTO = transferBetweenAccountsUseCase.execute(
                transferBetweenAccountsRequestDTO.agencyDebit(),
                transferBetweenAccountsRequestDTO.agencyCredit(),
                transferBetweenAccountsRequestDTO.transferValue());
        return ResponseEntity.ok(transferBetweenAccountsResponseDTO);
    }
}
