package com.topcarservice.coreapi.adapters.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.topcarservice.coreapi.adapters.controllers.dto.OrdemServicoDTO;
import com.topcarservice.coreapi.adapters.controllers.dto.OrdemServicoItemSolicitadoDTO;
import com.topcarservice.coreapi.adapters.controllers.dto.OrdemServicoRetornoDTO;
import com.topcarservice.coreapi.adapters.controllers.mappers.OrdemServicoMapper;
import com.topcarservice.coreapi.domain.entities.Cliente;
import com.topcarservice.coreapi.domain.entities.OrdemServico;
import com.topcarservice.coreapi.domain.entities.PecaInsumo;
import com.topcarservice.coreapi.domain.entities.Servico;
import com.topcarservice.coreapi.domain.entities.StatusOrdemServico;
import com.topcarservice.coreapi.domain.entities.Veiculo;
import com.topcarservice.coreapi.domain.usecases.ClienteUseCase;
import com.topcarservice.coreapi.domain.usecases.OrdemServicoUseCase;
import com.topcarservice.coreapi.domain.usecases.PecaInsumoUseCase;
import com.topcarservice.coreapi.domain.usecases.ServicoUseCase;
import com.topcarservice.coreapi.domain.usecases.VeiculoUseCase;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "Ordem de Serviço", description = "Gerenciamento de ordens de serviço")
@Slf4j
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
			
			var ordemServicoSalva = ordemServicoUseCase.cadastrar(ordemServico);
			
			log.info("Ordem de serviço cadastrada com sucesso: {}", ordemServicoSalva.getCodigo());
			
			return ResponseEntity.created(URI.create("/admin/ordem-servicos/" + ordemServicoSalva.getCodigo())).build();			
		} catch (IllegalArgumentException e) {			
			return ResponseEntity.notFound().build();
		}	
	}	
	
	@PostMapping("/{id}/servicos")
	public ResponseEntity<OrdemServicoRetornoDTO> incluirServico(@PathVariable("id") Long codigo,
			@RequestBody @Validated OrdemServicoItemSolicitadoDTO ordemServicoSolicitadoDTO) {	
		
		try {
			OrdemServico ordemServico = ordemServicoUseCase.carregar(codigo);	
			
			Servico servico = servicoUseCase.carregar(ordemServicoSolicitadoDTO.getCodigoItem());
			
			OrdemServico ordemServicoSalva = ordemServicoUseCase.incluirServico(ordemServico, servico);
			
			OrdemServicoRetornoDTO ordemservicoResponse = ordemServicoMapper.map(ordemServicoSalva);
			
			return ResponseEntity.ok().body(ordemservicoResponse);
		} catch (IllegalArgumentException e) {			
			return ResponseEntity.notFound().build();
		} catch (IllegalStateException e) {			
			return ResponseEntity.badRequest().build();
		}				
	}
	
	@PostMapping("/{id}/pecas-insumos")
	public ResponseEntity<OrdemServicoRetornoDTO> incluirPecaInsumo(@PathVariable("id") Long codigo,
			@RequestBody @Validated OrdemServicoItemSolicitadoDTO ordemServicoSolicitadoDTO) {	
		
		try {
			OrdemServico ordemServico = ordemServicoUseCase.carregar(codigo);	
			
			PecaInsumo pecaInsumo = pecaInsumoUseCase.carregar(ordemServicoSolicitadoDTO.getCodigoItem());			
			
			var ordemServicoSalva = 
					ordemServicoUseCase.incluirPecaInsumo(ordemServico, pecaInsumo, ordemServicoSolicitadoDTO.getQuantidade());
			
			OrdemServicoRetornoDTO ordemservicoResponse = ordemServicoMapper.map(ordemServicoSalva);
			
			return ResponseEntity.ok().body(ordemservicoResponse);
		} catch (IllegalArgumentException e) {			
			log.error("Erro ao incluir peça/insumo na ordem de serviço: {}", e.getMessage());
			return ResponseEntity.notFound().build();
		} catch (IllegalStateException e) {			
			log.error("Erro ao incluir peça/insumo na ordem de serviço: {}", e.getMessage());
			return ResponseEntity.badRequest().build();
		}			
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<OrdemServicoRetornoDTO> atualizarStatus(@PathVariable("id") Long codigo, 
			@RequestParam String status) {			
		
		try {
			OrdemServico ordemServico = ordemServicoUseCase.carregar(codigo);	
			
			ordemServicoUseCase.atualizar(ordemServico, status); 
			
			OrdemServicoRetornoDTO ordemservicoResponse = ordemServicoMapper.map(ordemServico);
			
			log.info("Ordem de serviço atualizada com sucesso: {}", ordemServico.getCodigo());
			
			return ResponseEntity.ok().body(ordemservicoResponse);
		} catch (IllegalArgumentException e) {			
			return ResponseEntity.notFound().build();
		} catch (IllegalStateException e) {			
			return ResponseEntity.badRequest().build();
		}	
	}	
	
	@PutMapping("/{id}/diagnostico")
	public ResponseEntity<OrdemServicoRetornoDTO> finalizarDiagnostico(@PathVariable("id") Long codigo) {			
		
		try {
			OrdemServico ordemServico = ordemServicoUseCase.carregar(codigo);	
			
			ordemServicoUseCase.finalizarDiagnostico(ordemServico); 
			
			OrdemServicoRetornoDTO ordemservicoResponse = ordemServicoMapper.map(ordemServico);
			
			log.info("Ordem de serviço atualizada com sucesso: {}", ordemServico.getCodigo());
			
			return ResponseEntity.ok().body(ordemservicoResponse);
		} catch (IllegalArgumentException e) {			
			log.error("Erro ao finalizar diagnostico da ordem de serviço: {}", e.getMessage());
			return ResponseEntity.notFound().build();
		} catch (IllegalStateException e) {			
			log.error("Erro ao finalizar diagnostico da ordem de serviço: {}", e.getMessage());
			return ResponseEntity.badRequest().build();
		}	
	}	
	
	@PutMapping("/{id}/aprovacao")
	public ResponseEntity<OrdemServicoRetornoDTO> aprovacao(@PathVariable("id") Long codigo) {			
		
		try {
			OrdemServico ordemServico = ordemServicoUseCase.carregar(codigo);	
			
			ordemServicoUseCase.atualizar(ordemServico, StatusOrdemServico.EM_EXECUCAO.getDescricao()); 
			
			OrdemServicoRetornoDTO ordemservicoResponse = ordemServicoMapper.map(ordemServico);
			
			log.info("Ordem de serviço aprovada com sucesso: {}", ordemServico.getCodigo());
			
			return ResponseEntity.ok().body(ordemservicoResponse);	
			
		} catch (IllegalArgumentException e) {	
			log.error("Erro ao aprovar a ordem de serviço: {}", e.getMessage());
			return ResponseEntity.notFound().build();
		} catch (IllegalStateException e) {			
			log.error("Erro ao aprovar a ordem de serviço: {}", e.getMessage());
			return ResponseEntity.badRequest().build();
		}	
	}	

}
