package com.topcarservice.coreapi.infra.db.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.topcarservice.coreapi.domain.entities.Servico;
import com.topcarservice.coreapi.infra.db.entities.ServicoDBEntity;

@Component
public class ServicoDBMapper {
	
	@Autowired
	private ModelMapper objectMapper;

	public ServicoDBEntity map(Servico servico) {		
		return objectMapper.map(servico, ServicoDBEntity.class);
	}
	
	public Servico mapTo(ServicoDBEntity servicoDBEntity) {		
		return objectMapper.map(servicoDBEntity, Servico.class);
	}

}
