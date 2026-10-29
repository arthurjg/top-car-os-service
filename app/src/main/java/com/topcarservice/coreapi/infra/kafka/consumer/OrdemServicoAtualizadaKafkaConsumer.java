package com.topcarservice.coreapi.infra.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.topcarservice.coreapi.adapters.controllers.dto.OrdemServicoRetornoDTO;
import com.topcarservice.coreapi.domain.entities.OrdemServico;
import com.topcarservice.coreapi.domain.entities.StatusOrdemServico;
import com.topcarservice.coreapi.domain.usecases.OrdemServicoUseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrdemServicoAtualizadaKafkaConsumer {
	
	private static final String ORDEM_SERVICO_FINALIZADA_TOPIC = "ordem-servico-finalizada";
	
	private final OrdemServicoUseCase ordemServicoUseCase;	
	
	@KafkaListener(topics = ORDEM_SERVICO_FINALIZADA_TOPIC, groupId = "${spring.kafka.consumer.group-id}")
	public void recebeEventoOSAprovada(OrdemServicoRetornoDTO ordemServicoDTO) {
		
		log.info("Ordem Serviço finalizada recebida no tópico: {}.", ordemServicoDTO.getCodigo());		
		
		OrdemServico ordemServico = ordemServicoUseCase.buscar(ordemServicoDTO.getCodigo());
		
		try {
			ordemServicoUseCase.atualizar(ordemServico, StatusOrdemServico.FINALIZADA.getDescricao());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
		log.info("Ordem de serviço atualizada para status: {}.", StatusOrdemServico.FINALIZADA.getDescricao());	
	}

}
