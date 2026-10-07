package com.topcarservice.coreapi.infra.db;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.topcarservice.coreapi.domain.entities.CPF;
import com.topcarservice.coreapi.domain.entities.Cliente;
import com.topcarservice.coreapi.domain.usecases.ports.ClienteOutputPort;
import com.topcarservice.coreapi.infra.db.mappers.ClienteDBMapper;
import com.topcarservice.coreapi.infra.db.repositories.ClienteRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ClienteOutputDB implements ClienteOutputPort {
	
	private final ClienteRepository clienteRepository;
	
	private final ClienteDBMapper clienteDBMapper;	

	@Override
	public Optional<Cliente> findById(Long codigo) {		
		
		return clienteRepository.findById(codigo)
				.map(clienteDBMapper::mapTo);
	}

	@Override
	public Optional<Cliente> findByCPF(CPF cpf) {		
		return clienteRepository.findByCpf(cpf.toString())
				.map(clienteDBMapper::mapTo);
	}

}

