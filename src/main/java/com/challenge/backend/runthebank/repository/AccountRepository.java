package com.challenge.backend.runthebank.repository;

import com.challenge.backend.runthebank.repository.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {
    AccountEntity findByAgency(Long agency);
}
