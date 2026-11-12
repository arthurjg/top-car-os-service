package com.topcarservice.coreapi.domain.usecases.ports;

import com.topcarservice.coreapi.domain.entities.Servico;

public interface ServicoInputPort {

	void save(Servico servico);

	void delete(Servico servico);

}
