package com.topcarservice.coreapi.infra.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.topcarservice.coreapi.adapters.controllers.dto.OrdemServicoOrcamentoDTO;
import com.topcarservice.coreapi.domain.entities.OrdemServico;
import com.topcarservice.coreapi.domain.entities.StatusOrdemServico;
import com.topcarservice.coreapi.domain.usecases.OrdemServicoUseCase;
import com.topcarservice.coreapi.infra.kafka.producer.OrdemServicoOrcamentoDesfazerKafkaProducer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrcamentoGeradoKafkaConsumer {
	
	private static final String ORCAMENTO_GERADO_TOPIC = "orcamento-gerado";
	
	private final OrdemServicoUseCase ordemServicoUseCase;
	
	private final OrdemServicoOrcamentoDesfazerKafkaProducer orcamentoDesfazerKafkaProducer;
	
	@KafkaListener(topics = ORCAMENTO_GERADO_TOPIC, groupId = "${spring.kafka.consumer.group-id}")
	public void recebeEventoOSAtualizada(OrdemServicoOrcamentoDTO ordemServicoOrcamentoDTO) {
		
		log.info("Orçamento gerado recebido no tópico: {}.", ordemServicoOrcamentoDTO.getCodigoOrdemServico());
		
		OrdemServico ordemServico = ordemServicoUseCase.buscar(ordemServicoOrcamentoDTO.getCodigoOrdemServico());
		
		try {					
			
			if(ordemServicoOrcamentoDTO.getCodigoOrdemServico() % 2 != 0 ) {
				throw new Exception("Erro ao atualizar Ordem de Serviço [cpf inexistente] "); 
			}
			
			ordemServicoUseCase.atualizar(ordemServico, StatusOrdemServico.AGUARDANDO_APROVACAO.getDescricao());
			
			log.info("Ordem de serviço atualizada para status: {}.", StatusOrdemServico.AGUARDANDO_APROVACAO.getDescricao());
		} catch (Exception e) {
			log.error("Erro ao atualizar Ordem de serviço: {}. Desfazendo atualização de status...", e.getMessage());
			
			orcamentoDesfazerKafkaProducer.sendOSOrcamentoEvent(ordemServicoOrcamentoDTO);
		}					
		
	}

}
