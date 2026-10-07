package com.topcarservice.coreapi.domain.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.topcarservice.coreapi.domain.entities.Servico;
import com.topcarservice.coreapi.domain.usecases.ports.ServicoInputPort;
import com.topcarservice.coreapi.domain.usecases.ports.ServicoOutputPort;

@ExtendWith(MockitoExtension.class)
class ServicoUseCaseTest {
	
	@InjectMocks
	ServicoUseCase useCase;
	
	@Mock
	ServicoInputPort inputPort;
	
	@Mock
	ServicoOutputPort outputPort;

	@Test
	void testCadastrar() {
		Servico servico = new Servico(1L, "troca pneus", new BigDecimal(150.0));
		useCase.cadastrar(servico);
		
		verify(inputPort).save(servico);
	}
	
	@Test
	void testCarregar() {
		
		long codigo = 1L;
		
		Servico servico = new Servico(codigo, "troca pneus", new BigDecimal(150.0));
		
		when(outputPort.findById(codigo)).thenReturn(Optional.of(servico));
		
		Servico servicoResult = useCase.carregar(codigo);
		
		assertNotNull(servicoResult);
		assertEquals(servico.getNome(), servicoResult.getNome());
	}

}
