package com.topcarservice.coreapi.infra.kafka.consumer;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.topcarservice.coreapi.adapters.controllers.dto.OrdemServicoOrcamentoDTO;
import com.topcarservice.coreapi.domain.entities.OrdemServico;
import com.topcarservice.coreapi.domain.entities.StatusOrdemServico;
import com.topcarservice.coreapi.domain.usecases.OrdemServicoUseCase;
import com.topcarservice.coreapi.infra.kafka.producer.OrdemServicoOrcamentoDesfazerKafkaProducer;

@ExtendWith(MockitoExtension.class)
class OrcamentoGeradoKafkaConsumerTest {
	
	@InjectMocks
	OrcamentoGeradoKafkaConsumer consumer;
	
	@Mock
	private OrdemServicoUseCase ordemServicoUseCase;
	
	@Mock
	private OrdemServicoOrcamentoDesfazerKafkaProducer orcamentoDesfazerKafkaProducer;

	@Test
	void testRecebeEventoOSAtualizada() {
		
		long codigoOrdemServico = 2L;
		
		var ordemServicoOrcamentoDTO = OrdemServicoOrcamentoDTO.builder()
				.codigoOrdemServico(codigoOrdemServico)
				.build();
		
		var ordemServico = new OrdemServico();
		ordemServico.setCodigo(codigoOrdemServico);
		
		when(ordemServicoUseCase.buscar(codigoOrdemServico)).thenReturn(ordemServico);
		
		consumer.recebeEventoOSAtualizada(ordemServicoOrcamentoDTO);
		
		verify(ordemServicoUseCase).atualizar(ordemServico, StatusOrdemServico.AGUARDANDO_APROVACAO.getDescricao());
		
	}

}
