package com.topcarservice.coreapi.infra.db.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.topcarservice.coreapi.domain.entities.PecaInsumo;
import com.topcarservice.coreapi.infra.db.entities.PecaInsumoDBEntity;

@Component
public class PecaInsumoDBMapper {
	
	@Autowired
	private ModelMapper objectMapper;

	public PecaInsumoDBEntity map(PecaInsumo pecaInsumo) {		
		return objectMapper.map(pecaInsumo, PecaInsumoDBEntity.class);
	}
	
	public PecaInsumo mapTo(PecaInsumoDBEntity pecaInsumoDBEntity) {		
		return objectMapper.map(pecaInsumoDBEntity, PecaInsumo.class);
	}

}
