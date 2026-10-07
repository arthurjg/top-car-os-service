package com.topcarservice.coreapi.infra.db;

import org.springframework.stereotype.Component;

import com.topcarservice.coreapi.domain.entities.PecaInsumoSolicitado;
import com.topcarservice.coreapi.domain.usecases.ports.PecaInsumoSolicitadoInputPort;
import com.topcarservice.coreapi.infra.db.mappers.PecaInsumoSolicitadoDBMapper;
import com.topcarservice.coreapi.infra.db.repositories.PecaInsumoSolicitadoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class PecaInsumoSolicitadoInputDB implements PecaInsumoSolicitadoInputPort {
	
	private final PecaInsumoSolicitadoRepository pecaInsumoSolicitadoRepository;
	
	private final PecaInsumoSolicitadoDBMapper pecaInsumoSolicitadoDBMapper;

	@Override
	public void save(PecaInsumoSolicitado pecaInsumoSolicitado) {
		
		var pecaInsumoSolicitadoDB = pecaInsumoSolicitadoDBMapper.map(pecaInsumoSolicitado);		
		pecaInsumoSolicitadoRepository.save(pecaInsumoSolicitadoDB);
		
	}

	@Override
	public void delete(PecaInsumoSolicitado pecaInsumoSolicitado) {
		pecaInsumoSolicitadoRepository.delete(pecaInsumoSolicitadoDBMapper.map(pecaInsumoSolicitado));		
	}

}
