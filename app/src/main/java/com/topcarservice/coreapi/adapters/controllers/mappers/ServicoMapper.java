package com.topcarservice.coreapi.adapters.controllers.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.topcarservice.coreapi.adapters.controllers.dto.ServicoDTO;
import com.topcarservice.coreapi.domain.entities.Servico;

@Component
public class ServicoMapper {
	
	@Autowired
	private ModelMapper objectMapper;

	public Servico map(ServicoDTO servicoDto) {		
		return objectMapper.map(servicoDto, Servico.class);
	}
	
	public ServicoDTO mapTo(Servico servico) {		
		return objectMapper.map(servico, ServicoDTO.class);
	}

}
