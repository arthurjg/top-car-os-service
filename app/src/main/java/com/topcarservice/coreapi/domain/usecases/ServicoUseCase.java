package com.topcarservice.coreapi.domain.usecases;

import com.topcarservice.coreapi.domain.entities.Servico;
import com.topcarservice.coreapi.domain.usecases.ports.ServicoInputPort;
import com.topcarservice.coreapi.domain.usecases.ports.ServicoOutputPort;


public class ServicoUseCase {
	
	private final ServicoInputPort inputPort;
	private final ServicoOutputPort outputPort;
	
	public ServicoUseCase(ServicoInputPort inputPort, ServicoOutputPort outputPort) {
		this.inputPort = inputPort;
		this.outputPort = outputPort;
	}

	public void cadastrar(Servico servico) {
		inputPort.save(servico);		
	}

	public Servico carregar(Long codigo) {
		
		return outputPort.findById(codigo)
				.orElseThrow(() -> new IllegalArgumentException("Servico não encontrado"));
	}

	public void excluir(Servico servico) {
		inputPort.delete(servico);		
	}

}
