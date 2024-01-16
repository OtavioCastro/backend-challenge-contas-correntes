package com.challenge.backend.runthebank.repository.converter;

import com.challenge.backend.runthebank.domain.Costumer;
import com.challenge.backend.runthebank.repository.entity.CostumerEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CostumerToCostumerEntityConverter {

    private final ModelMapper modelMapper;

    public CostumerToCostumerEntityConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CostumerEntity convert(Costumer costumer){return modelMapper.map(costumer, CostumerEntity.class);}
}
