package com.challenge.backend.runthebank.repository.impl;

import com.challenge.backend.runthebank.domain.Account;
import com.challenge.backend.runthebank.domain.dtos.AccountUpdateDTO;
import com.challenge.backend.runthebank.gateway.AccountGateway;
import com.challenge.backend.runthebank.repository.AccountRepository;
import com.challenge.backend.runthebank.repository.CostumerRepository;
import com.challenge.backend.runthebank.repository.converter.AccountEntityToAccountConverter;
import com.challenge.backend.runthebank.repository.converter.AccountToAccountEntityConverter;
import com.challenge.backend.runthebank.repository.entity.AccountEntity;
import com.challenge.backend.runthebank.repository.entity.CostumerEntity;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AccountGatewayImpl implements AccountGateway {

    private final AccountRepository accountRepository;
    private final CostumerRepository costumerRepository;
    private final AccountEntityToAccountConverter toAccountConverter;
    private final AccountToAccountEntityConverter toAccountEntityConverter;

    public AccountGatewayImpl(AccountRepository accountRepository,
                              CostumerRepository costumerRepository,
                              AccountEntityToAccountConverter toAccountConverter,
                              AccountToAccountEntityConverter toAccountEntityConverter) {
        this.accountRepository = accountRepository;
        this.costumerRepository = costumerRepository;
        this.toAccountConverter = toAccountConverter;
        this.toAccountEntityConverter = toAccountEntityConverter;
    }

    @Override
    @Transactional
    public Account saveAccount(Account account) {
        AccountEntity accountEntity = accountRepository.save(toAccountEntityConverter.convert(account));

        saveAccountToCostumer(account, accountEntity);

        return toAccountConverter.convert(accountEntity);
    }

    private void saveAccountToCostumer(Account account, AccountEntity accountEntity) {
        CostumerEntity costumerEntity = costumerRepository.findByDocument(account.getCostumer().getDocument());
        costumerEntity.getAccounts().add(accountEntity);
        costumerRepository.save(costumerEntity);
    }

    @Override
    public Account getAccount(Long agency) {
        return Optional.ofNullable(accountRepository.findByAgency(agency))
                .map(toAccountConverter::convert)
                .orElse(null);
    }

    @Override
    @Transactional
    public void updateAccount(AccountUpdateDTO accountUpdateDTO) {
        AccountEntity accountEntity = accountRepository.findByAgency(accountUpdateDTO.agency());
        accountEntity.setAgency(accountUpdateDTO.newAgency());
        accountRepository.save(accountEntity);
    }

    @Override
    @Transactional
    public void updateAccountBalance(Account account) {
        AccountEntity accountEntity = accountRepository.findByAgency(account.getAgency());
        accountEntity.setBalance(account.getBalance());
        accountRepository.save(accountEntity);
    }

    @Override
    @Transactional
    public void deleteAccount(UUID accountId) {
        accountRepository.deleteById(accountId);
    }
}
