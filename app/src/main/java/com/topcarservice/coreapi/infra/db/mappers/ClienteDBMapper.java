package com.topcarservice.coreapi.infra.db.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.topcarservice.coreapi.domain.entities.CPF;
import com.topcarservice.coreapi.domain.entities.Cliente;
import com.topcarservice.coreapi.infra.db.entities.ClienteDBEntity;

@Component
public class ClienteDBMapper {
	
	@Autowired
	private ModelMapper objectMapper;

	public ClienteDBEntity map(Cliente cliente) {			
		return ClienteDBEntity.builder()
				.codigo(cliente.getCodigo())
				.cpf(cliente.getCpf() != null ? cliente.getCpf().getUnformatted() : null)
				.cnpj(cliente.getCnpj())
				.nome(cliente.getNome())
				.build();
	}

	public Cliente mapTo(ClienteDBEntity clienteDBEntity) {
		if(clienteDBEntity.getCpf() != null) {
			return new Cliente(clienteDBEntity.getCodigo(), new CPF(CPF.formatarCPF(clienteDBEntity.getCpf())), clienteDBEntity.getNome());
		} else if(clienteDBEntity.getCnpj() != null) {
			return new Cliente(clienteDBEntity.getCodigo(), clienteDBEntity.getCnpj(), clienteDBEntity.getNome());
		}
		return null;		
	}

}
