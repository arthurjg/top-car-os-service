package com.topcarservice.coreapi.adapters.controllers.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.topcarservice.coreapi.adapters.controllers.dto.ClienteDTO;
import com.topcarservice.coreapi.domain.entities.CPF;
import com.topcarservice.coreapi.domain.entities.Cliente;

@Component
public class ClienteMapper {
	
	@Autowired
	private ModelMapper objectMapper;

	public Cliente map(ClienteDTO clienteDto) {		
		if(clienteDto.getCpf() != null) {
			return new Cliente(null, new CPF(CPF.formatarCPF(clienteDto.getCpf())), clienteDto.getNome());
		} else if(clienteDto.getCnpj() != null) {
			return new Cliente(null, clienteDto.getCnpj(), clienteDto.getNome());
		}
		return null;
	}

	public ClienteDTO map(Cliente cliente) {
		return objectMapper.map(cliente, ClienteDTO.class);
	}

}
