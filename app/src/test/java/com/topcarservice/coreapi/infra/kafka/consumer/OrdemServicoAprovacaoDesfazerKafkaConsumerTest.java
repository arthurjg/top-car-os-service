package com.topcarservice.coreapi.infra.kafka.consumer;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.topcarservice.coreapi.adapters.controllers.dto.OrdemServicoOrcamentoDTO;
import com.topcarservice.coreapi.adapters.controllers.dto.OrdemServicoRetornoDTO;
import com.topcarservice.coreapi.domain.entities.OrdemServico;
import com.topcarservice.coreapi.domain.usecases.OrdemServicoUseCase;
import com.topcarservice.coreapi.infra.kafka.producer.OrdemServicoOrcamentoDesfazerKafkaProducer;

@ExtendWith(MockitoExtension.class)
class OrdemServicoAprovacaoDesfazerKafkaConsumerTest {
	
	@InjectMocks
	OrdemServicoAprovacaoDesfazerKafkaConsumer consumer;
	
	@Mock
	private OrdemServicoUseCase ordemServicoUseCase;
	
	@Mock
	private OrdemServicoOrcamentoDesfazerKafkaProducer orcamentoDesfazerKafkaProducer;

	@Test
	void testRecebeEventoOSAprovacaoDesfazer() {
		long codigoOrdemServico = 2L;
		
		var ordemServicoDTO = OrdemServicoRetornoDTO.builder()
				.codigo(codigoOrdemServico)
				.build();
		
		var ordemServico = new OrdemServico();
		ordemServico.setCodigo(codigoOrdemServico);
		
		when(ordemServicoUseCase.buscar(codigoOrdemServico)).thenReturn(ordemServico);
		
		consumer.recebeEventoOSAprovacaoDesfazer(ordemServicoDTO);
		
		verify(orcamentoDesfazerKafkaProducer).sendOSOrcamentoEvent(any(OrdemServicoOrcamentoDTO.class));
	}

}
