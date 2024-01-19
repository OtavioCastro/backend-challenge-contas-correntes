package com.challenge.backend.runthebank.factories.domain.dtos;

import com.challenge.backend.runthebank.domain.dtos.CostumerUpdateDTO;

public class CostumerUpdateDTOFactory {

    public static CostumerUpdateDTO createCostumerUpdateDTOFactory(String document, String name, String address, String password){
        return new CostumerUpdateDTO(
                document,
                name,
                address,
                password
        );
    }
}
