package com.topcarservice.coreapi.domain.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.topcarservice.coreapi.domain.entities.OrdemServico;
import com.topcarservice.coreapi.domain.entities.PecaInsumo;
import com.topcarservice.coreapi.domain.entities.PecaInsumoSolicitado;
import com.topcarservice.coreapi.domain.usecases.ports.PecaInsumoSolicitadoInputPort;
import com.topcarservice.coreapi.domain.usecases.ports.PecaInsumoSolicitadoOutputPort;

@ExtendWith(MockitoExtension.class)
class PecaInsumoSolicitadoUseCaseTest {
	
	@InjectMocks
	PecaInsumoSolicitadoUseCase useCase;
	
	@Mock
	PecaInsumoSolicitadoInputPort inputPort;
	
	@Mock
	PecaInsumoSolicitadoOutputPort outputPort;
	
	PecaInsumoSolicitado pecaInsumoSolicitado;
	
	@BeforeEach
	void setUp() throws Exception {
		
		var pecaInsumo = new PecaInsumo(1L, 
				"Pastilha de roda", 
				"Pastilha de freio para veículos de passeio", 
				"Brembo", 
				"Ceramic", 50, new java.math.BigDecimal("150.00"));
		
		pecaInsumoSolicitado = new PecaInsumoSolicitado(pecaInsumo, 2);
			
	}

	@Test
	void testSalvar() {			
		
		var ordemServico = new OrdemServico();
		
		useCase.cadastrar(pecaInsumoSolicitado, ordemServico);
		
		verify(inputPort).save(pecaInsumoSolicitado, ordemServico);
	}	

	@Test
	void testCarregar() {
		
		long codigo = 1L;		
		
		when(outputPort.findById(codigo)).thenReturn(Optional.of(pecaInsumoSolicitado));
		
		PecaInsumoSolicitado pecaInsumoSolicitadoResult = useCase.carregar(codigo);
		
		assertNotNull(pecaInsumoSolicitadoResult);
		assertEquals(pecaInsumoSolicitado.getCodigo(), pecaInsumoSolicitadoResult.getCodigo());		
	}

	@Test
	void testExcluir() {				
		
		useCase.excluir(pecaInsumoSolicitado);
		
		verify(inputPort).delete(pecaInsumoSolicitado);
	}

}
