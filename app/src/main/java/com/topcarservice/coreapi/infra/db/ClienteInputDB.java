package com.topcarservice.coreapi.infra.db;

import org.springframework.stereotype.Component;

import com.topcarservice.coreapi.domain.entities.Cliente;
import com.topcarservice.coreapi.domain.usecases.ports.ClienteInputPort;
import com.topcarservice.coreapi.infra.db.mappers.ClienteDBMapper;
import com.topcarservice.coreapi.infra.db.repositories.ClienteRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ClienteInputDB implements ClienteInputPort {
	
	private final ClienteRepository clienteRepository;
	
	private final ClienteDBMapper clienteDBMapper;

	@Override
	public void save(Cliente cliente) {
		
		var clienteDB = clienteDBMapper.map(cliente);		
		clienteRepository.save(clienteDB);
		
	}

	@Override
	public void delete(Cliente cliente) {
		clienteRepository.delete(clienteDBMapper.map(cliente));		
	}

}
