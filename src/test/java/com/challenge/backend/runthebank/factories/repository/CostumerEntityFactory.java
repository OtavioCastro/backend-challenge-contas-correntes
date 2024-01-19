package com.challenge.backend.runthebank.factories.repository;

import com.challenge.backend.runthebank.domain.enums.TipoPessoa;
import com.challenge.backend.runthebank.repository.entity.CostumerEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CostumerEntityFactory {

    public static CostumerEntity createCostumerEntity(){
        return new CostumerEntity(
                UUID.fromString("1853c3dc-a8ea-4afc-84bd-a0090f308194"),
                "123.456.789-00",
                "Fulano",
                "Rua Teste",
                "12345",
                TipoPessoa.PF,
                new ArrayList<>()
        );
    }
}
