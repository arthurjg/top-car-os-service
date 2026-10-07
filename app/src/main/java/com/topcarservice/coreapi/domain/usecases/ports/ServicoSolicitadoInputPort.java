package com.topcarservice.coreapi.domain.usecases.ports;

import com.topcarservice.coreapi.domain.entities.ServicoSolicitado;

public interface ServicoSolicitadoInputPort {

	void save(ServicoSolicitado servicoSolicitado);

	void delete(ServicoSolicitado servicoSolicitado);

}
