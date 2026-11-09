package com.topcarservice.coreapi.infra.kafka.producer;

import com.topcarservice.coreapi.adapters.controllers.dto.OrdemServicoRetornoDTO;

public interface OrdemServicoAtualizadaProducer {
	
	void sendOSAtualizadaEvent(OrdemServicoRetornoDTO ordemServico);

}
