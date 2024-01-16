package com.challenge.backend.runthebank.repository;

import com.challenge.backend.runthebank.repository.entity.CostumerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CostumerRepository extends JpaRepository<CostumerEntity, UUID> {
    CostumerEntity findByDocument(String document);
}
