package com.topcarservice.coreapi.infra.db;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.topcarservice.coreapi.domain.entities.PecaInsumo;
import com.topcarservice.coreapi.domain.usecases.ports.PecaInsumoOutputPort;
import com.topcarservice.coreapi.infra.db.mappers.PecaInsumoDBMapper;
import com.topcarservice.coreapi.infra.db.repositories.PecaInsumoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class PecaInsumoOutputDB implements PecaInsumoOutputPort {
	
	private final PecaInsumoRepository pecaInsumoRepository;
	
	private final PecaInsumoDBMapper pecaInsumoDBMapper;	

	@Override
	public Optional<PecaInsumo> findById(Long codigo) {		
		
		return pecaInsumoRepository.findById(codigo)
				.map(pecaInsumoDBMapper::mapTo);
	}

}

