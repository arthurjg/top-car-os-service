package com.topcarservice.coreapi.infra.db.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.topcarservice.coreapi.domain.entities.ServicoSolicitado;
import com.topcarservice.coreapi.infra.db.entities.ServicoSolicitadoDBEntity;

@Component
public class ServicoSolicitadoDBMapper {
	
	@Autowired
	private ModelMapper objectMapper;

	public ServicoSolicitadoDBEntity map(ServicoSolicitado servicoSolicitado) {		
		return objectMapper.map(servicoSolicitado, ServicoSolicitadoDBEntity.class);
	}
	
	public ServicoSolicitado mapTo(ServicoSolicitadoDBEntity servicoSolicitadoDBEntity) {		
		return objectMapper.map(servicoSolicitadoDBEntity, ServicoSolicitado.class);
	}

}
