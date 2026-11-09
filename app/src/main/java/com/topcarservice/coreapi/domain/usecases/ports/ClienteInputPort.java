package com.topcarservice.coreapi.domain.usecases.ports;

import com.topcarservice.coreapi.domain.entities.Cliente;

public interface ClienteInputPort {

	void save(Cliente cliente);

	void delete(Cliente cliente);

}
