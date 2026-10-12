package com.topcarservice.coreapi.infra.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.topcarservice.coreapi.adapters.controllers.dto.OrdemServicoRetornoDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class OrdemServicoAtualizadaKafkaProducer implements OrdemServicoAtualizadaProducer {
	
	private final KafkaTemplate<String, OrdemServicoRetornoDTO> kafkaTemplate;
	
	private static final String ORDEM_SERVICO_ATUALIZADA_TOPIC = "ordem-servico-atualizada";

	@Override
	public void sendOSAtualizadaEvent(OrdemServicoRetornoDTO ordemServico) {
		
		kafkaTemplate.send(ORDEM_SERVICO_ATUALIZADA_TOPIC, ordemServico);		
		
	}

}
