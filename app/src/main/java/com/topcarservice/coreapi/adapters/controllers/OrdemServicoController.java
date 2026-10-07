package com.topcarservice.coreapi.adapters.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.topcarservice.coreapi.adapters.controllers.dto.OrdemServicoDTO;
import com.topcarservice.coreapi.adapters.controllers.dto.OrdemServicoItemSolicitadoDTO;
import com.topcarservice.coreapi.adapters.controllers.dto.OrdemServicoOrcamentoDTO;
import com.topcarservice.coreapi.adapters.controllers.dto.OrdemServicoRetornoDTO;
import com.topcarservice.coreapi.adapters.controllers.mappers.OrdemServicoMapper;
import com.topcarservice.coreapi.domain.entities.Cliente;
import com.topcarservice.coreapi.domain.entities.OrdemServico;
import com.topcarservice.coreapi.domain.entities.PecaInsumo;
import com.topcarservice.coreapi.domain.entities.Servico;
import com.topcarservice.coreapi.domain.entities.Veiculo;
import com.topcarservice.coreapi.domain.usecases.ClienteUseCase;
import com.topcarservice.coreapi.domain.usecases.OrdemServicoUseCase;
import com.topcarservice.coreapi.domain.usecases.PecaInsumoUseCase;
import com.topcarservice.coreapi.domain.usecases.ServicoUseCase;
import com.topcarservice.coreapi.domain.usecases.VeiculoUseCase;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(name = "Ordem de Serviço", description = "Gerenciamento de ordens de serviço")
@AllArgsConstructor
@RestController
@RequestMapping("/admin/ordem-servicos")
public class OrdemServicoController {
	
	OrdemServicoUseCase ordemServicoUseCase;	
	
	ClienteUseCase clienteUseCase;
	
	VeiculoUseCase veiculoUseCase;
	
	ServicoUseCase servicoUseCase;
	
	PecaInsumoUseCase pecaInsumoUseCase;
	
	OrdemServicoMapper ordemServicoMapper;	
	
	@PostMapping
	public ResponseEntity<Void> salvar(@RequestBody @Validated OrdemServicoDTO ordemServicoDto) {			
		
		try {
			Cliente cliente = clienteUseCase.carregar(ordemServicoDto.getCodigoCliente());
			
			Veiculo veiculo = veiculoUseCase.carregar(ordemServicoDto.getCodigoVeiculo());
			
			OrdemServico ordemServico = new OrdemServico(cliente, veiculo);						
			
			var ordemServicoSalva =  ordemServicoUseCase.cadastrar(ordemServico);
			return ResponseEntity.created(URI.create("/admin/ordem-servicos/" + ordemServicoSalva.getCodigo())).build();			
		} catch (IllegalArgumentException e) {			
			return ResponseEntity.notFound().build();
		}	
	}	
	
	@PostMapping("/{id}/servicos")
	public ResponseEntity<OrdemServicoOrcamentoDTO> incluirServico(@PathVariable("id") Long codigo,
			@RequestBody @Validated OrdemServicoItemSolicitadoDTO ordemServicoSolicitadoDto) {	
		
		try {
			OrdemServico ordemServico = ordemServicoUseCase.carregar(codigo);	
			
			Servico servico = servicoUseCase.carregar(ordemServicoSolicitadoDto.getCodigoItem());
			
			OrdemServicoOrcamentoDTO orcamento = null;
					//ordemServicoUseCase.incluirServico(ordemServico, servico); TODO TEST ALTERAR
			return ResponseEntity.ok().body(orcamento);
		} catch (IllegalArgumentException e) {			
			return ResponseEntity.notFound().build();
		} catch (IllegalStateException e) {			
			return ResponseEntity.badRequest().build();
		}				
	}
	
	@PostMapping("/{id}/pecas-insumos")
	public ResponseEntity<OrdemServicoOrcamentoDTO> incluirPecaInsumo(@PathVariable("id") Long codigo,
			@RequestBody @Validated OrdemServicoItemSolicitadoDTO ordemServicoSolicitadoDto) {	
		
		try {
			OrdemServico ordemServico = ordemServicoUseCase.carregar(codigo);	
			
			PecaInsumo pecaInsumo = pecaInsumoUseCase.carregar(ordemServicoSolicitadoDto.getCodigoItem());
			
			OrdemServicoOrcamentoDTO orcamento = null;
					//ordemServicoUseCase.incluirPecaInsumo(ordemServico, pecaInsumo, ordemServicoSolicitadoDto.getQuantidade()); TODO TEST ALTERAR
			return ResponseEntity.ok().body(orcamento);
		} catch (IllegalArgumentException e) {			
			return ResponseEntity.notFound().build();
		} catch (IllegalStateException e) {			
			return ResponseEntity.badRequest().build();
		}			
	}
	
	@PutMapping("/{id}/aprovacao")
	public ResponseEntity<OrdemServicoRetornoDTO> enviarAprovacao(@PathVariable("id") Long codigo) {			
		
		try {
			OrdemServico ordemServico = ordemServicoUseCase.carregar(codigo);	
			
			//ordemServicoUseCase.enviarAprovacao(ordemServico); TODO TEST ALTERAR
			
			OrdemServicoRetornoDTO ordemservicoResponse = ordemServicoMapper.map(ordemServico);
			
			return ResponseEntity.ok().body(ordemservicoResponse);
		} catch (IllegalArgumentException e) {			
			return ResponseEntity.notFound().build();
		} catch (IllegalStateException e) {			
			return ResponseEntity.badRequest().build();
		}	
	}	

}
