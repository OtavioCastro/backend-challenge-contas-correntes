package com.challenge.backend.runthebank.repository.entity;

import com.challenge.backend.runthebank.domain.enums.AccountStatus;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "ACCOUNT")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID accountId;
    @Column(unique = true)
    private Long agency;
    private Double balance;
    private AccountStatus accountStatus;

    public AccountEntity() {
    }

    public AccountEntity(UUID accountId, Long agency, Double balance, AccountStatus accountStatus, CostumerEntity costumer) {
        this.accountId = accountId;
        this.agency = agency;
        this.balance = balance;
        this.accountStatus = accountStatus;
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

}
