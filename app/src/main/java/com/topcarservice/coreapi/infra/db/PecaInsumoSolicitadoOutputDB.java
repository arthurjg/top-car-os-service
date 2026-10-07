package com.topcarservice.coreapi.infra.db;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.topcarservice.coreapi.domain.entities.PecaInsumoSolicitado;
import com.topcarservice.coreapi.domain.usecases.ports.PecaInsumoSolicitadoOutputPort;
import com.topcarservice.coreapi.infra.db.mappers.PecaInsumoSolicitadoDBMapper;
import com.topcarservice.coreapi.infra.db.repositories.PecaInsumoSolicitadoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class PecaInsumoSolicitadoOutputDB implements PecaInsumoSolicitadoOutputPort {
	
	private final PecaInsumoSolicitadoRepository pecaInsumoSolicitadoRepository;
	
	private final PecaInsumoSolicitadoDBMapper pecaInsumoSolicitadoDBMapper;	

	@Override
	public Optional<PecaInsumoSolicitado> findById(Long codigo) {		
		
		return pecaInsumoSolicitadoRepository.findById(codigo)
				.map(pecaInsumoSolicitadoDBMapper::mapTo);
	}

}

