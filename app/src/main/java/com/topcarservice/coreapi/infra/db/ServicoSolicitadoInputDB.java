package com.topcarservice.coreapi.infra.db;

import org.springframework.stereotype.Component;

import com.topcarservice.coreapi.domain.entities.OrdemServico;
import com.topcarservice.coreapi.domain.entities.ServicoSolicitado;
import com.topcarservice.coreapi.domain.usecases.ports.ServicoSolicitadoInputPort;
import com.topcarservice.coreapi.infra.db.mappers.ServicoSolicitadoDBMapper;
import com.topcarservice.coreapi.infra.db.repositories.OrdemServicoRepository;
import com.topcarservice.coreapi.infra.db.repositories.ServicoSolicitadoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ServicoSolicitadoInputDB implements ServicoSolicitadoInputPort {
	
	private final ServicoSolicitadoRepository servicoSolicitadoRepository;
	
	private final ServicoSolicitadoDBMapper servicoSolicitadoDBMapper;
	
	private final OrdemServicoRepository ordemServicoRepository;

	@Override
	public void save(ServicoSolicitado servicoSolicitado, OrdemServico ordemServico) {
		
		var ordemServicoDBEntity = ordemServicoRepository.findById(ordemServico.getCodigo())
				.orElseThrow(() -> new IllegalArgumentException("OrdemServico não encontrada"));
		
		var servicoSolicitadoDB = servicoSolicitadoDBMapper.map(servicoSolicitado);		
		servicoSolicitadoDB.setOrdemServico(ordemServicoDBEntity);
		servicoSolicitadoRepository.save(servicoSolicitadoDB);
		
	}

	@Override
	public void delete(ServicoSolicitado servicoSolicitado) {
		servicoSolicitadoRepository.delete(servicoSolicitadoDBMapper.map(servicoSolicitado));		
	}

}
