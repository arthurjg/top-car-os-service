package com.topcarservice.coreapi.infra.db.mappers;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
	
	public List<PecaInsumoSolicitadoDBEntity> map(List<PecaInsumoSolicitado> pecaInsumoSolicitados) {		
		var pecaInsumoSolicitadosList = Optional.ofNullable(pecaInsumoSolicitados).orElse(Collections.emptyList());
		return pecaInsumoSolicitadosList.stream()
				.map(pecaInsumoSolicitado -> map(pecaInsumoSolicitado))
				.toList();
	}	
	
	public List<PecaInsumoSolicitado> mapTo(List<PecaInsumoSolicitadoDBEntity> pecaInsumoSolicitadosDBEntity) {		
		var pecaInsumoSolicitadosList = Optional.ofNullable(pecaInsumoSolicitadosDBEntity).orElse(Collections.emptyList());
		return pecaInsumoSolicitadosList.stream()
				.map(PecaInsumoSolicitadoDBEntity -> mapTo(PecaInsumoSolicitadoDBEntity))
				.toList();
	}	

}
