package com.challange.backend.runthebank.repository.entity;

import com.challange.backend.runthebank.domain.enums.AccountStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "ACCOUNT")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID accountId;
    private Long agency;
    private BigDecimal balance;
    private AccountStatus accountStatus;
}
