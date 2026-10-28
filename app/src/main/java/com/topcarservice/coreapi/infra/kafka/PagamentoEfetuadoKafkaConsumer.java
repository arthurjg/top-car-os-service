package com.topcarservice.coreapi.infra.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.topcarservice.coreapi.adapters.controllers.dto.PagamentoDTO;
import com.topcarservice.coreapi.domain.entities.OrdemServico;
import com.topcarservice.coreapi.domain.entities.StatusOrdemServico;
import com.topcarservice.coreapi.domain.usecases.OrdemServicoUseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class PagamentoEfetuadoKafkaConsumer {
	
	private static final String PAGAMENTO_EFETUADO_TOPIC = "pagamento-efetuado";
	
	private final OrdemServicoUseCase ordemServicoUseCase;
	
	@KafkaListener(topics = PAGAMENTO_EFETUADO_TOPIC, groupId = "${spring.kafka.consumer.group-id}")
	public void recebeEventoOSAtualizada(PagamentoDTO pagamentoDTO) {
		
		log.info("Pagamento efetuado recebido no tópico para os: {}.", pagamentoDTO.getCodigoOrdemServico() );
		
		OrdemServico ordemServico = ordemServicoUseCase.buscar(pagamentoDTO.getCodigoOrdemServico() );
		
		ordemServicoUseCase.atualizar(ordemServico, StatusOrdemServico.ENTREGUE.getDescricao());			
		
		log.info("Ordem de serviço atualizada para status: {}.", StatusOrdemServico.ENTREGUE.getDescricao());	
		
	}

}
