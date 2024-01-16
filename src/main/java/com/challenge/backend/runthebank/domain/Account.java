package com.challenge.backend.runthebank.domain;

import com.challenge.backend.runthebank.domain.enums.AccountStatus;

import java.util.UUID;

public class Account {

    private UUID accountId;
    private Long agency;
    private Double balance = 0.0;
    private AccountStatus accountStatus = AccountStatus.DEACTIVE;
    private Costumer costumer;

    public Account(){}

    public Account(Long agency) {
        this.agency = agency;
    }

    public Account(Long agency, AccountStatus accountStatus, Costumer costumer) {
        this.agency = agency;
        this.accountStatus = accountStatus;
        this.costumer = costumer;
    }

    public Account(UUID accountId, Long agency, Double balance, AccountStatus accountStatus, Costumer costumer) {
        this.accountId = accountId;
        this.agency = agency;
        this.balance = balance;
        this.accountStatus = accountStatus;
        this.costumer = costumer;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public Long getAgency() {
        return agency;
    }

    public void setAgency(Long agency) {
        this.agency = agency;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Costumer getCostumer() {
        return costumer;
    }

    public void setCostumer(Costumer costumer) {
        this.costumer = costumer;
    }

    public void debit(Double value){
        this.balance -= value;
    }

    public void credit(Double value){
        this.balance += value;
    }
}
