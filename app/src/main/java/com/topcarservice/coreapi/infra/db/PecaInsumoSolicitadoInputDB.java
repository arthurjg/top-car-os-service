package com.topcarservice.coreapi.infra.db;

import org.springframework.stereotype.Component;

import com.topcarservice.coreapi.domain.entities.OrdemServico;
import com.topcarservice.coreapi.domain.entities.PecaInsumoSolicitado;
import com.topcarservice.coreapi.domain.usecases.ports.PecaInsumoSolicitadoInputPort;
import com.topcarservice.coreapi.infra.db.mappers.PecaInsumoSolicitadoDBMapper;
import com.topcarservice.coreapi.infra.db.repositories.OrdemServicoRepository;
import com.topcarservice.coreapi.infra.db.repositories.PecaInsumoSolicitadoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class PecaInsumoSolicitadoInputDB implements PecaInsumoSolicitadoInputPort {
	
	private final PecaInsumoSolicitadoRepository pecaInsumoSolicitadoRepository;
	
	private final PecaInsumoSolicitadoDBMapper pecaInsumoSolicitadoDBMapper;
	
	private final OrdemServicoRepository ordemServicoRepository;

	@Override
	public void save(PecaInsumoSolicitado pecaInsumoSolicitado, OrdemServico ordemServico) {
		
		var ordemServicoDBEntity = ordemServicoRepository.findById(ordemServico.getCodigo())
			.orElseThrow(() -> new IllegalArgumentException("OrdemServico não encontrada"));
		
		var pecaInsumoSolicitadoDB = pecaInsumoSolicitadoDBMapper.map(pecaInsumoSolicitado);	
		pecaInsumoSolicitadoDB.setOrdemServico(ordemServicoDBEntity);
		pecaInsumoSolicitadoRepository.save(pecaInsumoSolicitadoDB);
		
	}

	@Override
	public void delete(PecaInsumoSolicitado pecaInsumoSolicitado) {
		pecaInsumoSolicitadoRepository.delete(pecaInsumoSolicitadoDBMapper.map(pecaInsumoSolicitado));		
	}

}
