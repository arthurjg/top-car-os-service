package com.topcarservice.coreapi.adapters.controllers.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.topcarservice.coreapi.adapters.controllers.dto.PecaInsumoDTO;
import com.topcarservice.coreapi.domain.entities.PecaInsumo;

@Component
public class PecaInsumoMapper {
	
	@Autowired
	private ModelMapper objectMapper;

	public PecaInsumo map(PecaInsumoDTO pecaInsumoDto) {		
		return objectMapper.map(pecaInsumoDto, PecaInsumo.class);
	}

	public PecaInsumoDTO mapTo(PecaInsumo pecaInsumo) {
		return objectMapper.map(pecaInsumo, PecaInsumoDTO.class);
	}

}
