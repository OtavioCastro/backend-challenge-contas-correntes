package com.challange.backend.runthebank.repository;

import com.challange.backend.runthebank.domain.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostumerRepository extends JpaRepository<Costumer, String> {
}
