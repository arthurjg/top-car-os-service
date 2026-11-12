package com.topcarservice.coreapi.adapters.controllers.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.topcarservice.coreapi.adapters.controllers.dto.OrdemServicoPecaInsumoSolicitadoDTO;
import com.topcarservice.coreapi.domain.entities.PecaInsumoSolicitado;

@Component
public class PecaInsumoSolicitadoMapper {
	
	@Autowired
	private ModelMapper objectMapper;	
	
	public OrdemServicoPecaInsumoSolicitadoDTO mapTo(PecaInsumoSolicitado pecaInsumo) {		
		return objectMapper.map(pecaInsumo, OrdemServicoPecaInsumoSolicitadoDTO.class);
	}

}
