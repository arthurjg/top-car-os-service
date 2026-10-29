package com.topcarservice.coreapi.adapters.gateway;

import org.springframework.stereotype.Component;

import com.topcarservice.coreapi.adapters.controllers.dto.OrdemServicoRetornoDTO;
import com.topcarservice.coreapi.adapters.controllers.mappers.OrdemServicoMapper;
import com.topcarservice.coreapi.domain.entities.OrdemServico;
import com.topcarservice.coreapi.domain.usecases.ports.OrdemServicoOutputEventPort;
import com.topcarservice.coreapi.infra.kafka.producer.OrdemServicoAtualizadaProducer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrdemServicoEventAdapter implements OrdemServicoOutputEventPort {
	
	private final OrdemServicoAtualizadaProducer ordemServicoAtualizadaProducer;
	
	private final OrdemServicoMapper ordemServicoMapper;

	@Override
	public void sendOSAtualizadaEvent(OrdemServico ordemServico) {		
		
		OrdemServicoRetornoDTO ordemServicoRetornoDTO = ordemServicoMapper.map(ordemServico);
		
		ordemServicoAtualizadaProducer.sendOSAtualizadaEvent(ordemServicoRetornoDTO);
		
		log.info("Ordem de serviço atualizada para o status: {} enviada para o Kafka: {}", ordemServico.getStatus(), ordemServico.getCodigo());
	}

}
