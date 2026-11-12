package com.topcarservice.coreapi.domain.usecases.ports;

import com.topcarservice.coreapi.domain.entities.OrdemServico;
import com.topcarservice.coreapi.domain.entities.ServicoSolicitado;

public interface ServicoSolicitadoInputPort {

	void save(ServicoSolicitado servicoSolicitado, OrdemServico ordemServico);

	void delete(ServicoSolicitado servicoSolicitado);

}
