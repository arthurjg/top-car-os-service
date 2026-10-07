package com.topcarservice.coreapi.domain.usecases.ports;

import java.util.Optional;

import com.topcarservice.coreapi.domain.entities.CPF;
import com.topcarservice.coreapi.domain.entities.Cliente;

public interface ClienteOutputPort {

	Optional<Cliente> findById(Long codigo);
	
	Optional<Cliente> findByCPF(CPF cpf);

}
