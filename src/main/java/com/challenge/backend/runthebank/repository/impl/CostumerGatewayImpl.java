package com.challenge.backend.runthebank.repository.impl;

import com.challenge.backend.runthebank.domain.Costumer;
import com.challenge.backend.runthebank.domain.dtos.CostumerUpdateDTO;
import com.challenge.backend.runthebank.gateway.CostumerGateway;
import com.challenge.backend.runthebank.repository.CostumerRepository;
import com.challenge.backend.runthebank.repository.converter.CostumerEntityToCostumerConverter;
import com.challenge.backend.runthebank.repository.converter.CostumerToCostumerEntityConverter;
import com.challenge.backend.runthebank.repository.entity.CostumerEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class CostumerGatewayImpl implements CostumerGateway {

    private final CostumerRepository costumerRepository;
    private final CostumerEntityToCostumerConverter toCostumerConverter;
    private final CostumerToCostumerEntityConverter toCostumerEntityConverter;

    public CostumerGatewayImpl(CostumerRepository costumerRepository,
                               CostumerEntityToCostumerConverter toCostumerConverter,
                               CostumerToCostumerEntityConverter toCostumerEntityConverter) {
        this.costumerRepository = costumerRepository;
        this.toCostumerConverter = toCostumerConverter;
        this.toCostumerEntityConverter = toCostumerEntityConverter;
    }

    @Override
    public Costumer saveCostumer(Costumer costumer) {
        CostumerEntity costumerEntity = costumerRepository.save(toCostumerEntityConverter.convert(costumer));
        return toCostumerConverter.convert(costumerEntity);
    }

    @Override
    public Costumer getCostumer(String document) {
        return Optional.ofNullable(costumerRepository.findByDocument(document))
                .map(toCostumerConverter::convert)
                .orElse(null);
    }

    @Override
    public void updateCostumer(CostumerUpdateDTO costumerUpdateDTO) {
        CostumerEntity costumerEntity = costumerRepository.findByDocument(costumerUpdateDTO.document());
        costumerEntity.setName(costumerUpdateDTO.name());
        costumerEntity.setAddress(costumerUpdateDTO.address());
        costumerEntity.setPassword(costumerUpdateDTO.password());
    }

    @Override
    public void deleteCostumer(UUID costumerId) {
        costumerRepository.deleteById(costumerId);
    }
}
