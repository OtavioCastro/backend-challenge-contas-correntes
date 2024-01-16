package com.challange.backend.runthebank.domain;

import com.challange.backend.runthebank.domain.enums.AccountStatus;

import java.math.BigDecimal;

public class Account {

    private Long agency;
    private BigDecimal balance = BigDecimal.ZERO;
    private AccountStatus accountStatus = AccountStatus.DEACTIVE;

    public Account(Long agency) {
        this.agency = agency;
    }

    public Account(Long agency, AccountStatus accountStatus) {
        this.agency = agency;
        this.accountStatus = accountStatus;
    }

    public Long getAgency() {
        return agency;
    }

    public void setAgency(Long agency) {
        this.agency = agency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }
}
