package com.topcarservice.coreapi.domain.usecases;

import com.topcarservice.coreapi.domain.entities.ServicoSolicitado;
import com.topcarservice.coreapi.domain.usecases.ports.ServicoSolicitadoInputPort;
import com.topcarservice.coreapi.domain.usecases.ports.ServicoSolicitadoOutputPort;


public class ServicoSolicitadoUseCase {
	
	private final ServicoSolicitadoInputPort inputPort;
	private final ServicoSolicitadoOutputPort outputPort;
	
	public ServicoSolicitadoUseCase(ServicoSolicitadoInputPort inputPort, ServicoSolicitadoOutputPort outputPort) {
		this.inputPort = inputPort;
		this.outputPort = outputPort;
	}

	public void cadastrar(ServicoSolicitado servicoSolicitado) {
		inputPort.save(servicoSolicitado);		
	}

	public ServicoSolicitado carregar(Long codigo) {
		
		return outputPort.findById(codigo)
				.orElseThrow(() -> new IllegalArgumentException("ServicoSolicitado não encontrado"));
	}

	public void excluir(ServicoSolicitado servicoSolicitado) {
		inputPort.delete(servicoSolicitado);		
	}

}
