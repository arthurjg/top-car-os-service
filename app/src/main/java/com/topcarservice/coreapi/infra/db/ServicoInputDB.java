package com.topcarservice.coreapi.infra.db;

import org.springframework.stereotype.Component;

import com.topcarservice.coreapi.domain.entities.Servico;
import com.topcarservice.coreapi.domain.usecases.ports.ServicoInputPort;
import com.topcarservice.coreapi.infra.db.mappers.ServicoDBMapper;
import com.topcarservice.coreapi.infra.db.repositories.ServicoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ServicoInputDB implements ServicoInputPort {
	
	private final ServicoRepository servicoRepository;
	
	private final ServicoDBMapper servicoDBMapper;

	@Override
	public void save(Servico servico) {
		
		var servicoDB = servicoDBMapper.map(servico);		
		servicoRepository.save(servicoDB);
		
	}

	@Override
	public void delete(Servico servico) {
		servicoRepository.delete(servicoDBMapper.map(servico));		
	}

}
