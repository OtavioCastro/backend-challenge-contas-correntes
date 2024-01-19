package com.challenge.backend.runthebank.factories.domain.dtos;

import com.challenge.backend.runthebank.domain.dtos.CostumerSaveDTO;
import com.challenge.backend.runthebank.domain.enums.TipoPessoa;

public class CostumerSaveDTOFactory {

    public static CostumerSaveDTO createCostumerSaveDTOPF(){
        return new CostumerSaveDTO(
                "Beltrano",
                "662.456.789-55",
                "Rua dos testes",
                "passwordDosTestes",
                TipoPessoa.PF
        );
    }

    public static CostumerSaveDTO createCostumerSaveDTOPJ(){
        return new CostumerSaveDTO(
                "Pessoa Juridica Teste",
                "784.541.522-87",
                "Rua dos jurídicos",
                "passwordDosJuridicos",
                TipoPessoa.PJ
        );
    }
}
