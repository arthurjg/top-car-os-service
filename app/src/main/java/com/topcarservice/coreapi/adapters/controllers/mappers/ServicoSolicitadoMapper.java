package com.topcarservice.coreapi.adapters.controllers.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.topcarservice.coreapi.adapters.controllers.dto.OrdemServicoSolicitadoRetornoDTO;
import com.topcarservice.coreapi.domain.entities.ServicoSolicitado;

@Component
public class ServicoSolicitadoMapper {
	
	@Autowired
	private ModelMapper objectMapper;	
	
	public OrdemServicoSolicitadoRetornoDTO mapTo(ServicoSolicitado servico) {		
		return objectMapper.map(servico, OrdemServicoSolicitadoRetornoDTO.class);
	}

}
