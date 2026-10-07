package com.topcarservice.coreapi.infra.db;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.topcarservice.coreapi.domain.entities.ServicoSolicitado;
import com.topcarservice.coreapi.domain.usecases.ports.ServicoSolicitadoOutputPort;
import com.topcarservice.coreapi.infra.db.mappers.ServicoSolicitadoDBMapper;
import com.topcarservice.coreapi.infra.db.repositories.ServicoSolicitadoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ServicoSolicitadoOutputDB2 implements ServicoSolicitadoOutputPort {
	
	private final ServicoSolicitadoRepository servicoSolicitadoRepository;
	
	private final ServicoSolicitadoDBMapper servicoSolicitadoDBMapper;	

	@Override
	public Optional<ServicoSolicitado> findById(Long codigo) {		
		
		return servicoSolicitadoRepository.findById(codigo)
				.map(servicoSolicitadoDBMapper::mapTo);
	}

}

