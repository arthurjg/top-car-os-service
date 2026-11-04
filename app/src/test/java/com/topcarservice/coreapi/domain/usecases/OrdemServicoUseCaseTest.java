package com.topcarservice.coreapi.domain.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.topcarservice.coreapi.domain.entities.OrdemServico;
import com.topcarservice.coreapi.domain.entities.PecaInsumo;
import com.topcarservice.coreapi.domain.entities.Servico;
import com.topcarservice.coreapi.domain.entities.StatusOrdemServico;
import com.topcarservice.coreapi.domain.usecases.ports.OrdemServicoInputPort;
import com.topcarservice.coreapi.domain.usecases.ports.OrdemServicoOutputDBPort;
import com.topcarservice.coreapi.domain.usecases.ports.OrdemServicoOutputEventPort;

@ExtendWith(MockitoExtension.class)
class OrdemServicoUseCaseTest {
	
	@InjectMocks
	OrdemServicoUseCase useCase;
	
	@Mock
	OrdemServicoInputPort inputPort;
	
	@Mock
	OrdemServicoOutputDBPort outputPort;
	
	@Mock
	OrdemServicoOutputEventPort outputEventPort;
	
	@Mock
	ServicoSolicitadoUseCase servicoSolicitadoUseCase;
	
	@Mock
	PecaInsumoSolicitadoUseCase pecaInsumoSolicitadoUseCase;
	
	OrdemServico ordemServico;
	
	@BeforeEach
	void setUp(){		
		ordemServico = new OrdemServico();			
	}

	@Test
	void testCadastrar() {
				
		
		useCase.cadastrar(ordemServico);
		
		verify(inputPort).save(ordemServico);
	}
	
	@Test
	void testCarregar() {
		
		long codigo = 1L;		
		
		when(outputPort.findById(codigo)).thenReturn(Optional.of(ordemServico));
		
		OrdemServico ordemServicoResult = useCase.carregar(codigo);
		
		assertNotNull(ordemServicoResult);
		assertEquals(ordemServico.getCodigo(), ordemServicoResult.getCodigo());
	}
	
	@Test
	void testBuscar() {
		
		long codigo = 1L;		
		
		when(outputPort.findAggregateById(codigo)).thenReturn(Optional.of(ordemServico));
		
		OrdemServico ordemServicoResult = useCase.buscar(codigo);
		
		assertNotNull(ordemServicoResult);
		assertEquals(ordemServico.getCodigo(), ordemServicoResult.getCodigo());
	}
	
	@Test
	void testExcluir() {		
		
		useCase.excluir(ordemServico);
		
		verify(inputPort).delete(ordemServico);
	}
	
	@Test
	void testListar() {				
		
		when(outputPort.findAll()).thenReturn(List.of(ordemServico));
		
		List<OrdemServico> ordemServicoList = useCase.listar();
		
		assertNotNull(ordemServicoList);
		assertTrue(ordemServicoList.size() == 1);
	}
	
	@Test
	void testAtualizar() {		
		
		useCase.atualizar(ordemServico, StatusOrdemServico.EM_EXECUCAO.getDescricao());
		
		verify(inputPort).save(ordemServico);
		verify(outputEventPort).sendOSAtualizadaEvent(ordemServico);
		
	}
	
	@Test
	void testFinalizarDiagnostico() {	
		
		PecaInsumo pecaInsumo = 
				new PecaInsumo(1L, "Pastilha de roda", 
						"Pastilha de freio para veículos de passeio", 
						"Brembo", "Ceramic", 50, new BigDecimal("150.00"));	
		
		Servico servico = 
				new Servico(1L, "troca pneus", new BigDecimal(150.0), 60L);		
		
		try {
			useCase.incluirServico(ordemServico, servico);
			useCase.incluirPecaInsumo(ordemServico, pecaInsumo, 1);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		ordemServico.setStatus(StatusOrdemServico.EM_DIAGNOSTICO);
		
		useCase.finalizarDiagnostico(ordemServico);
		
		verify(outputEventPort).sendOSAtualizadaEvent(ordemServico);
		
	}
	
	@Test
	void testIncluirPecaInsumo() {	
		
		PecaInsumo pecaInsumo = 
				new PecaInsumo(1L, "Pastilha de roda", 
						"Pastilha de freio para veículos de passeio", 
						"Brembo", "Ceramic", 50, new BigDecimal("150.00"));			
		
		try {
			useCase.incluirPecaInsumo(ordemServico, pecaInsumo, 1);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		var pecaInsumoSolicitado = ordemServico.getPecasInsumos().stream()
			.findFirst().get();
		
		verify(pecaInsumoSolicitadoUseCase).cadastrar(pecaInsumoSolicitado, ordemServico);
		
	}
	
	@Test
	void testIncluirServico() {	
		
		Servico servico = 
				new Servico(1L, "troca pneus", new BigDecimal(150.0), 60L);		
		
		try {
			useCase.incluirServico(ordemServico, servico);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		var servicoSolicitado = ordemServico.getServicos().stream()
			.findFirst().get();
		
		verify(servicoSolicitadoUseCase).cadastrar(servicoSolicitado, ordemServico);
		
	}

}
