package com.topcarservice.coreapi.infra.db.mappers;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
	
	public List<ServicoSolicitado> mapTo(List<ServicoSolicitadoDBEntity> servicoSolicitadosDBEntity) {	
		var servicoSolicitadosList = Optional.ofNullable(servicoSolicitadosDBEntity).orElse(Collections.emptyList());
		return servicoSolicitadosList.stream()
				.map(ServicoSolicitadoDBEntity -> mapTo(ServicoSolicitadoDBEntity))
				.toList();
	}	
	
	public List<ServicoSolicitadoDBEntity> map(List<ServicoSolicitado> servicoSolicitados) {	
		var servicoSolicitadosList = Optional.ofNullable(servicoSolicitados).orElse(Collections.emptyList());
		return servicoSolicitadosList.stream()
				.map(ServicoSolicitado -> map(ServicoSolicitado))
				.toList();
	}	

}
