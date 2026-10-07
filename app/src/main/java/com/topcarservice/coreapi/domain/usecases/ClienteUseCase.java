package com.topcarservice.coreapi.domain.usecases;

import com.topcarservice.coreapi.domain.entities.CPF;
import com.topcarservice.coreapi.domain.entities.Cliente;
import com.topcarservice.coreapi.domain.usecases.ports.ClienteInputPort;
import com.topcarservice.coreapi.domain.usecases.ports.ClienteOutputPort;


public class ClienteUseCase {
	
	private final ClienteInputPort inputPort;
	private final ClienteOutputPort outputPort;
	
	public ClienteUseCase(ClienteInputPort inputPort, ClienteOutputPort outputPort) {
		this.inputPort = inputPort;
		this.outputPort = outputPort;
	}

	public void cadastrar(Cliente cliente) {
		inputPort.save(cliente);		
	}

	public Cliente carregar(Long codigo) {
		
		return outputPort.findById(codigo)
				.orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
	}	

	public void excluir(Cliente cliente) {
		inputPort.delete(cliente);		
	}
	
	public Cliente carregarPeloCPF(CPF cpf) {
		
		return outputPort.findByCPF(cpf)
				.orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
	}

}
