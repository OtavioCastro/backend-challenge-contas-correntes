package com.challenge.backend.runthebank.gatweway;

import com.challenge.backend.runthebank.domain.Costumer;
import com.challenge.backend.runthebank.domain.dtos.CostumerUpdateDTO;
import com.challenge.backend.runthebank.factories.domain.dtos.CostumerUpdateDTOFactory;
import com.challenge.backend.runthebank.factories.repository.CostumerEntityFactory;
import com.challenge.backend.runthebank.repository.CostumerRepository;
import com.challenge.backend.runthebank.repository.converter.CostumerEntityToCostumerConverter;
import com.challenge.backend.runthebank.repository.converter.CostumerToCostumerEntityConverter;
import com.challenge.backend.runthebank.repository.entity.CostumerEntity;
import com.challenge.backend.runthebank.repository.impl.CostumerGatewayImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CostumerGatewayImplTest {

    @Mock
    private final CostumerRepository costumerRepository = mock(CostumerRepository.class);

    @Spy
    private final CostumerEntityToCostumerConverter toCostumerConverter = new CostumerEntityToCostumerConverter(new ModelMapper());

    @Spy
    private final CostumerToCostumerEntityConverter toCostumerEntityConverter = new CostumerToCostumerEntityConverter(new ModelMapper());

    @InjectMocks
    private final CostumerGatewayImpl costumerGateway = new CostumerGatewayImpl(costumerRepository, toCostumerConverter, toCostumerEntityConverter);

    @Test
    public void shouldSaveCostumer(){
        CostumerEntity costumerEntityMock = CostumerEntityFactory.createCostumerEntity();
        Costumer costumerMock = toCostumerConverter.convert(costumerEntityMock);

        when(costumerRepository.save(any(CostumerEntity.class))).thenReturn(costumerEntityMock);

        Costumer costumerResponse = costumerGateway.saveCostumer(costumerMock);

        assertEquals(costumerEntityMock.getCostumerId(), costumerResponse.getCostumerId());
        verify(costumerRepository, atLeastOnce()).save(any(CostumerEntity.class));
    }

    @Test
    public void shouldGetCostumer(){
        CostumerEntity costumerEntityMock = CostumerEntityFactory.createCostumerEntity();

        when(costumerRepository.findByDocument(anyString())).thenReturn(costumerEntityMock);

        Costumer costumerResponse = costumerGateway.getCostumer("123.456.789-00");

        assertEquals(costumerEntityMock.getCostumerId(), costumerResponse.getCostumerId());
        verify(costumerRepository, atLeastOnce()).findByDocument(anyString());
    }

    @Test
    public void shouldUpdateCostumerName(){
        CostumerEntity costumerEntityMock = CostumerEntityFactory.createCostumerEntity();
        String costumerOldName = costumerEntityMock.getName();
        String costumerOldAddress = costumerEntityMock.getAddress();
        String costumerOldPassword = costumerEntityMock.getPassword();
        String newCostumerName = "New Costumer Name";
        CostumerUpdateDTO costumerUpdateDTOMock = CostumerUpdateDTOFactory.createCostumerUpdateDTOFactory(costumerEntityMock.getDocument(), newCostumerName, "", "");

        when(costumerRepository.findByDocument(anyString())).thenReturn(costumerEntityMock);

        costumerGateway.updateCostumer(costumerUpdateDTOMock);

        assertNotEquals(costumerOldName, costumerEntityMock.getName());
        assertEquals(newCostumerName, costumerEntityMock.getName());

        assertEquals(costumerOldAddress, costumerEntityMock.getAddress());

        assertEquals(costumerOldPassword, costumerEntityMock.getPassword());

        verify(costumerRepository, atLeastOnce()).findByDocument(anyString());
    }

    @Test
    public void shouldUpdateCostumerAddressAndPassword(){
        CostumerEntity costumerEntityMock = CostumerEntityFactory.createCostumerEntity();
        String costumerOldName = costumerEntityMock.getName();
        String costumerOldAddress = costumerEntityMock.getAddress();
        String costumerOldPassword = costumerEntityMock.getPassword();
        String newCostumerAddress = "New Costumer Address";
        String newCostumerPassword = "New Costumer Password";
        CostumerUpdateDTO costumerUpdateDTOMock = CostumerUpdateDTOFactory.createCostumerUpdateDTOFactory(costumerEntityMock.getDocument(), "", newCostumerAddress, newCostumerPassword);

        when(costumerRepository.findByDocument(anyString())).thenReturn(costumerEntityMock);

        costumerGateway.updateCostumer(costumerUpdateDTOMock);

        assertEquals(costumerOldName, costumerEntityMock.getName());

        assertNotEquals(costumerOldAddress, costumerEntityMock.getAddress());
        assertEquals(newCostumerAddress, costumerEntityMock.getAddress());

        assertNotEquals(costumerOldPassword, costumerEntityMock.getPassword());
        assertEquals(newCostumerPassword, costumerEntityMock.getPassword());

        verify(costumerRepository, atLeastOnce()).findByDocument(anyString());
    }

    @Test
    public void shouldDeleteCostumer(){

        doNothing().when(costumerRepository).deleteById(any(UUID.class));

        costumerGateway.deleteCostumer(UUID.randomUUID());

        verify(costumerRepository, atLeastOnce()).deleteById(any(UUID.class));
    }
}
