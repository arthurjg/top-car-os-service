package com.topcarservice.coreapi.infra.kafka.consumer;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.topcarservice.coreapi.adapters.controllers.dto.OrdemServicoRetornoDTO;
import com.topcarservice.coreapi.domain.entities.OrdemServico;
import com.topcarservice.coreapi.domain.entities.StatusOrdemServico;
import com.topcarservice.coreapi.domain.usecases.OrdemServicoUseCase;

@ExtendWith(MockitoExtension.class)
class OrdemServicoAtualizadaKafkaConsumerTest {
	
	@InjectMocks
	OrdemServicoAtualizadaKafkaConsumer consumer;
	
	@Mock
	OrdemServicoUseCase ordemServicoUseCase;

	@Test
	void testRecebeEventoOSAtualizada() throws Exception {		
		
		long codigoOrdemServico = 2L;
		
		var ordemServicoDTO = OrdemServicoRetornoDTO.builder()
				.codigo(codigoOrdemServico)
				.build();		
		
		var ordemServico = new OrdemServico();
		ordemServico.setCodigo(codigoOrdemServico);
		ordemServico.setStatus(StatusOrdemServico.EM_DIAGNOSTICO);
		
		when(ordemServicoUseCase.buscar(codigoOrdemServico)).thenReturn(ordemServico);
		
		consumer.recebeEventoOSAtualizada(ordemServicoDTO);
		
		verify(ordemServicoUseCase).atualizar(ordemServico, StatusOrdemServico.FINALIZADA.getDescricao());
	}

	

}
