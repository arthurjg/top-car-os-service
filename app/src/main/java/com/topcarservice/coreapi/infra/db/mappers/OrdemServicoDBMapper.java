package com.topcarservice.coreapi.infra.db.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.topcarservice.coreapi.domain.entities.Cliente;
import com.topcarservice.coreapi.domain.entities.OrdemServico;
import com.topcarservice.coreapi.domain.entities.StatusOrdemServico;
import com.topcarservice.coreapi.domain.entities.Veiculo;
import com.topcarservice.coreapi.infra.db.entities.ClienteDBEntity;
import com.topcarservice.coreapi.infra.db.entities.OrdemServicoDBEntity;
import com.topcarservice.coreapi.infra.db.entities.VeiculoDBEntity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class OrdemServicoDBMapper {
	
	
	private ModelMapper objectMapper;
	
	private ServicoSolicitadoDBMapper servicoSolicitadoDBMapper;
	
	private PecaInsumoSolicitadoDBMapper pecaInsumoSolicitadoDBMapper;	

	public OrdemServicoDBEntity map(OrdemServico ordemServico) {		
		return OrdemServicoDBEntity.builder()
				.codigo(ordemServico.getCodigo())
				.status(ordemServico.getStatus().getCodigo())
				.dataAbertura(ordemServico.getDataAbertura())
				.cliente(objectMapper.map(ordemServico.getCliente(), ClienteDBEntity.class))
				.veiculo(objectMapper.map(ordemServico.getVeiculo(), VeiculoDBEntity.class))				
				.build();				
	}
	
	public OrdemServico mapTo(OrdemServicoDBEntity ordemServicoDBEntity) {			
		
		return new OrdemServico(
				ordemServicoDBEntity.getCodigo(),
				StatusOrdemServico.fromCodigo(ordemServicoDBEntity.getStatus()),
				objectMapper.map(ordemServicoDBEntity.getCliente(), Cliente.class),
				objectMapper.map(ordemServicoDBEntity.getVeiculo(), Veiculo.class),
				servicoSolicitadoDBMapper.mapTo(ordemServicoDBEntity.getServicos()),
				pecaInsumoSolicitadoDBMapper.mapTo(ordemServicoDBEntity.getPecasInsumos()),
				ordemServicoDBEntity.getDataAbertura()
			);
		
	}

}
