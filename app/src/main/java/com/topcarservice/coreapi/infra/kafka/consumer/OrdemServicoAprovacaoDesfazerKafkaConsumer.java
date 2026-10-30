package com.topcarservice.coreapi.infra.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.topcarservice.coreapi.adapters.controllers.dto.OrdemServicoOrcamentoDTO;
import com.topcarservice.coreapi.adapters.controllers.dto.OrdemServicoRetornoDTO;
import com.topcarservice.coreapi.domain.entities.OrdemServico;
import com.topcarservice.coreapi.domain.entities.StatusOrdemServico;
import com.topcarservice.coreapi.domain.usecases.OrdemServicoUseCase;
import com.topcarservice.coreapi.infra.kafka.producer.OrdemServicoOrcamentoDesfazerKafkaProducer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrdemServicoAprovacaoDesfazerKafkaConsumer {
	
	private static final String OS_APROVACAO_DESFAZER_TOPIC = "ordem-servico-aprovacao-desfazer";
	
	private final OrdemServicoUseCase ordemServicoUseCase;	
	
	private final OrdemServicoOrcamentoDesfazerKafkaProducer orcamentoDesfazerKafkaProducer;
	
	@KafkaListener(topics = OS_APROVACAO_DESFAZER_TOPIC, groupId = "${spring.kafka.consumer.group-id}")
	public void recebeEventoOSAprovacaoDesfazer(OrdemServicoRetornoDTO ordemServicoDTO) {
		
		log.info("Ordem Serviço aprovação desfazer recebida no tópico: {}.", ordemServicoDTO.getCodigo());	
		
		OrdemServicoOrcamentoDTO ordemServicoOrcamentoDTO = new OrdemServicoOrcamentoDTO();
		ordemServicoOrcamentoDTO.setCodigoOrdemServico(ordemServicoDTO.getCodigo());
		
		OrdemServico ordemServico = ordemServicoUseCase.buscar(ordemServicoDTO.getCodigo());
		
		log.info("Enviando evento de remoção de Orçamento...");	
		
		orcamentoDesfazerKafkaProducer.sendOSOrcamentoEvent(ordemServicoOrcamentoDTO);
		
		try {
			ordemServicoUseCase.atualizar(ordemServico, StatusOrdemServico.EM_DIAGNOSTICO.getDescricao());
		} catch (Exception e) {			
			e.printStackTrace();
		}			
		
		log.info("Ordem de serviço atualizada para status: {}.", StatusOrdemServico.EM_DIAGNOSTICO.getDescricao());	
	}

}
