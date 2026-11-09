package com.topcarservice.coreapi.infra.kafka.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.topcarservice.coreapi.adapters.controllers.dto.OrdemServicoOrcamentoDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class OrdemServicoOrcamentoDesfazerKafkaProducer {
	
	private final KafkaTemplate<String, OrdemServicoOrcamentoDTO> kafkaTemplate;
	
	private static final String OS_ORCAMENTO_DESFAZER_TOPIC = "os-orcamento-desfazer";

	public void sendOSOrcamentoEvent(OrdemServicoOrcamentoDTO ordemServicoOrcamentoDTO) {
		
		kafkaTemplate.send(OS_ORCAMENTO_DESFAZER_TOPIC, ordemServicoOrcamentoDTO);		
		
	}

}
