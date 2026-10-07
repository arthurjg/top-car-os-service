package com.topcarservice.coreapi.adapters.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.topcarservice.coreapi.adapters.controllers.dto.ServicoDTO;
import com.topcarservice.coreapi.adapters.controllers.mappers.ServicoMapper;
import com.topcarservice.coreapi.domain.entities.Servico;
import com.topcarservice.coreapi.domain.usecases.ServicoUseCase;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(name = "Serviço", description = "Gerenciamento de serviços")
@AllArgsConstructor
@RestController
@RequestMapping("/admin/servicos")
public class ServicoController {
	
	ServicoUseCase servicoUseCase;	
	
	ServicoMapper servicoMapper;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void salvar(@RequestBody @Validated ServicoDTO servicoDto) throws Exception {		
		
		Servico servico = servicoMapper.map(servicoDto);		
		
		servicoUseCase.cadastrar(servico);		
	}	
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(@PathVariable("id") Long codigo,
			@RequestBody @Validated ServicoDTO servicoDto) {	
		
		Servico servico = servicoMapper.map(servicoDto);		
		
		servico.setCodigo(codigo);
		
		servicoUseCase.cadastrar(servico);	
		
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable("id") Long codigo) {	
		
		Servico servico = servicoUseCase.carregar(codigo);
		servicoUseCase.excluir(servico);	
		
		return ResponseEntity.noContent().build();
	}

}
