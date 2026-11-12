package com.topcarservice.coreapi.domain.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.topcarservice.coreapi.domain.entities.OrdemServico;
import com.topcarservice.coreapi.domain.entities.Servico;
import com.topcarservice.coreapi.domain.entities.ServicoSolicitado;
import com.topcarservice.coreapi.domain.usecases.ports.ServicoSolicitadoInputPort;
import com.topcarservice.coreapi.domain.usecases.ports.ServicoSolicitadoOutputPort;

@ExtendWith(MockitoExtension.class)
class ServicoSolicitadoUseCaseTest {
	
	@InjectMocks
	ServicoSolicitadoUseCase useCase;
	
	@Mock
	ServicoSolicitadoInputPort inputPort;
	
	@Mock
	ServicoSolicitadoOutputPort outputPort;
	
	ServicoSolicitado servicoSolicitado;
	
	@BeforeEach
	void setUp() throws Exception {
		
		Servico servico = new Servico(1L, "troca pneus", new BigDecimal(150.0), 60L);
		
		servicoSolicitado = new ServicoSolicitado(servico);
			
	}

	@Test
	void testSalvar() {					
		
		var ordemServico = new OrdemServico();
		useCase.cadastrar(servicoSolicitado, ordemServico);
		
		verify(inputPort).save(servicoSolicitado, ordemServico);
	}	

	@Test
	void testCarregar() {
		
		long codigo = 1L;		
		
		when(outputPort.findById(codigo)).thenReturn(Optional.of(servicoSolicitado));
		
		ServicoSolicitado servicoResult = useCase.carregar(codigo);
		
		assertNotNull(servicoResult);
		assertEquals(servicoSolicitado.getCodigo(), servicoResult.getCodigo());
		
	}

	@Test
	void testExcluir() {				
		
		useCase.excluir(servicoSolicitado);
		
		verify(inputPort).delete(servicoSolicitado);
	}

}
