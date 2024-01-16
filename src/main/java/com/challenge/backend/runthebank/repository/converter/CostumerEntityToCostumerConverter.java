package com.challenge.backend.runthebank.repository.converter;

import com.challenge.backend.runthebank.domain.Costumer;
import com.challenge.backend.runthebank.repository.entity.CostumerEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CostumerEntityToCostumerConverter {
    private final ModelMapper modelMapper;

    public CostumerEntityToCostumerConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Costumer convert(CostumerEntity costumerEntity){return modelMapper.map(costumerEntity, Costumer.class);}
}
