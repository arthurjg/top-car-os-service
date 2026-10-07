package com.topcarservice.coreapi.infra.db.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.topcarservice.coreapi.domain.entities.OrdemServico;
import com.topcarservice.coreapi.infra.db.entities.ClienteDBEntity;
import com.topcarservice.coreapi.infra.db.entities.OrdemServicoDBEntity;
import com.topcarservice.coreapi.infra.db.entities.VeiculoDBEntity;

@Component
public class OrdemServicoDBMapper {
	
	@Autowired
	private ModelMapper objectMapper;

	public OrdemServicoDBEntity map(OrdemServico ordemServico) {		
		return OrdemServicoDBEntity.builder()
				.status(ordemServico.getStatus().getCodigo())
				.dataAbertura(ordemServico.getDataAbertura())
				.cliente(objectMapper.map(ordemServico.getCliente(), ClienteDBEntity.class))
				.veiculo(objectMapper.map(ordemServico.getVeiculo(), VeiculoDBEntity.class))
				.build();				
	}
	
	public OrdemServico mapTo(OrdemServicoDBEntity ordemServicoDBEntity) {		
		return objectMapper.map(ordemServicoDBEntity, OrdemServico.class);
	}

}
