package com.topcarservice.coreapi.infra.db.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.topcarservice.coreapi.domain.entities.PecaInsumoSolicitado;
import com.topcarservice.coreapi.infra.db.entities.PecaInsumoSolicitadoDBEntity;

@Component
public class PecaInsumoSolicitadoDBMapper {
	
	@Autowired
	private ModelMapper objectMapper;

	public PecaInsumoSolicitadoDBEntity map(PecaInsumoSolicitado pecaInsumoSolicitado) {		
		return objectMapper.map(pecaInsumoSolicitado, PecaInsumoSolicitadoDBEntity.class);
	}
	
	public PecaInsumoSolicitado mapTo(PecaInsumoSolicitadoDBEntity pecaInsumoSolicitadoDBEntity) {		
		return objectMapper.map(pecaInsumoSolicitadoDBEntity, PecaInsumoSolicitado.class);
	}

}
