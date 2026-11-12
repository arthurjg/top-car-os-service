package com.topcarservice.coreapi.infra.db;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.topcarservice.coreapi.domain.entities.Servico;
import com.topcarservice.coreapi.domain.usecases.ports.ServicoOutputPort;
import com.topcarservice.coreapi.infra.db.mappers.ServicoDBMapper;
import com.topcarservice.coreapi.infra.db.repositories.ServicoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ServicoOutputDB implements ServicoOutputPort {
	
	private final ServicoRepository servicoRepository;
	
	private final ServicoDBMapper servicoDBMapper;	

	@Override
	public Optional<Servico> findById(Long codigo) {		
		
		return servicoRepository.findById(codigo)
				.map(servicoDBMapper::mapTo);
	}

}

