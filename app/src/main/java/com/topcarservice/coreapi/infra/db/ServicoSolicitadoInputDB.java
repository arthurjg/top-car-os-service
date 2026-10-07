package com.topcarservice.coreapi.infra.db;

import org.springframework.stereotype.Component;

import com.topcarservice.coreapi.domain.entities.ServicoSolicitado;
import com.topcarservice.coreapi.domain.usecases.ports.ServicoSolicitadoInputPort;
import com.topcarservice.coreapi.infra.db.mappers.ServicoSolicitadoDBMapper;
import com.topcarservice.coreapi.infra.db.repositories.ServicoSolicitadoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ServicoSolicitadoInputDB implements ServicoSolicitadoInputPort {
	
	private final ServicoSolicitadoRepository servicoSolicitadoRepository;
	
	private final ServicoSolicitadoDBMapper servicoSolicitadoDBMapper;

	@Override
	public void save(ServicoSolicitado servicoSolicitado) {
		
		var servicoSolicitadoDB = servicoSolicitadoDBMapper.map(servicoSolicitado);		
		servicoSolicitadoRepository.save(servicoSolicitadoDB);
		
	}

	@Override
	public void delete(ServicoSolicitado servicoSolicitado) {
		servicoSolicitadoRepository.delete(servicoSolicitadoDBMapper.map(servicoSolicitado));		
	}

}
