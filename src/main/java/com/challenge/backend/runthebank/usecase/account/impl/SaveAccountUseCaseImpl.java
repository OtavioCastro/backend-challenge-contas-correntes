package com.challenge.backend.runthebank.usecase.account.impl;

import com.challenge.backend.runthebank.domain.Account;
import com.challenge.backend.runthebank.domain.Costumer;
import com.challenge.backend.runthebank.domain.dtos.AccountSaveDTO;
import com.challenge.backend.runthebank.domain.enums.AccountStatus;
import com.challenge.backend.runthebank.gateway.AccountGateway;
import com.challenge.backend.runthebank.gateway.CostumerGateway;
import com.challenge.backend.runthebank.usecase.account.SaveAccountUseCase;
import com.challenge.backend.runthebank.usecase.costumer.GetCostumerUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import static java.util.Objects.nonNull;

@Service
public class SaveAccountUseCaseImpl implements SaveAccountUseCase {

    private final AccountGateway accountGateway;
    private final GetCostumerUseCase getCostumerUseCase;

    public SaveAccountUseCaseImpl(AccountGateway accountGateway,
                                  GetCostumerUseCase getCostumerUseCase) {
        this.accountGateway = accountGateway;
        this.getCostumerUseCase = getCostumerUseCase;
    }

    @Override
    public Account execute(AccountSaveDTO accountSaveDTO) {
        validateExistentAccount(accountSaveDTO.agency());
        Costumer costumer = getCostumerUseCase.execute(accountSaveDTO.document());
        Account account = new Account(
                accountSaveDTO.agency(),
                AccountStatus.ACTIVE,
                costumer
        );
        return accountGateway.saveAccount(account);
    }

    private void validateExistentAccount(Long agency) {
        if(nonNull(accountGateway.getAccount(agency))){
            throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "Número de conta já cadastrado.");
        }
    }
}
