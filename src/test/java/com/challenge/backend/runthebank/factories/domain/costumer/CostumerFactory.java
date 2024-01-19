package com.challenge.backend.runthebank.factories.domain.costumer;

import com.challenge.backend.runthebank.domain.Costumer;
import com.challenge.backend.runthebank.domain.dtos.CostumerSaveDTO;
import com.challenge.backend.runthebank.domain.enums.TipoPessoa;

import java.util.List;
import java.util.UUID;

public class CostumerFactory {

    public static Costumer createCostumer(){
        return new Costumer(
                UUID.fromString("1853c3dc-a8ea-4afc-84bd-a0090f308194"),
                "Fulano",
                "123.456.789-00",
                "Rua Teste",
                "12345",
                TipoPessoa.PF,
                List.of()
        );
    }

    public static Costumer createCostumerToTransfer(){
        return new Costumer(
                UUID.fromString("f0d84a0c-6e9b-4a34-b33b-0f952b12fe50"),
                "Ciclano",
                "987.654.321-00",
                "Rua XPTO",
                "56789",
                TipoPessoa.PF,
                List.of()
        );
    }

    public static Costumer createCostumerFromCostumerSaveDTO(UUID costumerUUID, CostumerSaveDTO costumerSaveDTO){
        return new Costumer(
                costumerUUID,
                costumerSaveDTO.name(),
                costumerSaveDTO.document(),
                costumerSaveDTO.address(),
                costumerSaveDTO.password(),
                costumerSaveDTO.tipoPessoa(),
                List.of()
        );
    }
}
