package com.topcarservice.coreapi.infra.db;

import org.springframework.stereotype.Component;

import com.topcarservice.coreapi.domain.entities.PecaInsumo;
import com.topcarservice.coreapi.domain.usecases.ports.PecaInsumoInputPort;
import com.topcarservice.coreapi.infra.db.mappers.PecaInsumoDBMapper;
import com.topcarservice.coreapi.infra.db.repositories.PecaInsumoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class PecaInsumoInputDB implements PecaInsumoInputPort {
	
	private final PecaInsumoRepository pecaInsumoRepository;
	
	private final PecaInsumoDBMapper pecaInsumoDBMapper;

	@Override
	public void save(PecaInsumo pecaInsumo) {
		
		var pecaInsumoDB = pecaInsumoDBMapper.map(pecaInsumo);		
		pecaInsumoRepository.save(pecaInsumoDB);
		
	}

	@Override
	public void delete(PecaInsumo pecaInsumo) {
		pecaInsumoRepository.delete(pecaInsumoDBMapper.map(pecaInsumo));		
	}

}
