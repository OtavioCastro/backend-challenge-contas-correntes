package com.challenge.backend.runthebank.usecase.costumer;

import com.challenge.backend.runthebank.domain.Costumer;
import com.challenge.backend.runthebank.factories.domain.costumer.CostumerFactory;
import com.challenge.backend.runthebank.gateway.CostumerGateway;
import com.challenge.backend.runthebank.usecase.costumer.impl.GetCostumerUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class GetCostumerUseCaseImplTest {

    @Mock
    private final CostumerGateway costumerGateway = mock(CostumerGateway.class);

    @InjectMocks
    private final GetCostumerUseCaseImpl getCostumerUseCase = new GetCostumerUseCaseImpl(costumerGateway);

    @Test
    public void shouldGetCostumer() {
        Costumer costumerMock = CostumerFactory.createCostumer();

        when(costumerGateway.getCostumer(anyString())).thenReturn(costumerMock);

        Costumer costumerResponse = getCostumerUseCase.execute(costumerMock.getDocument());

        assertEquals(costumerMock.getCostumerId(), costumerResponse.getCostumerId());
        verify(costumerGateway, atLeastOnce()).getCostumer(anyString());
    }

    @Test
    public void shouldNotFoundCostumer() {
        final Costumer costumerMock = CostumerFactory.createCostumer();

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> getCostumerUseCase.execute(costumerMock.getDocument()));

        assertEquals("Cliente não encontrado.", exception.getStatusText());
        verify(costumerGateway, atLeastOnce()).getCostumer(anyString());

    }
}
