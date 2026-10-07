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

import com.topcarservice.coreapi.adapters.controllers.dto.VeiculoDTO;
import com.topcarservice.coreapi.adapters.controllers.mappers.VeiculoMapper;
import com.topcarservice.coreapi.domain.entities.Cliente;
import com.topcarservice.coreapi.domain.entities.Veiculo;
import com.topcarservice.coreapi.domain.usecases.ClienteUseCase;
import com.topcarservice.coreapi.domain.usecases.VeiculoUseCase;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(name = "Veículo", description = "Gerenciamento de veículos")
@AllArgsConstructor
@RestController
@RequestMapping("/admin/veiculos")
public class VeiculoController {
	
	VeiculoUseCase veiculoUseCase;	
	
	ClienteUseCase clienteUseCase;
	
	VeiculoMapper veiculoMapper;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void salvar(@RequestBody @Validated VeiculoDTO veiculoDto) throws Exception {		
		
		Veiculo veiculo = veiculoMapper.map(veiculoDto);	
		
		Cliente cliente = clienteUseCase.carregar(veiculoDto.getCodigoCliente());
		//veiculo.setCliente(cliente); TODO TEST ALTERAR
		
		veiculoUseCase.cadastrar(veiculo);		
	}	
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(@PathVariable("id") Long codigo,
			@RequestBody @Validated VeiculoDTO veiculoDto) {	
		
		Veiculo veiculo = veiculoMapper.map(veiculoDto);	
		
		Cliente cliente = clienteUseCase.carregar(veiculoDto.getCodigoCliente());
		//veiculo.setCliente(cliente); TODO TEST ALTERAR
		
		veiculoUseCase.cadastrar(veiculo);	
		
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable("id") Long codigo) {	
		
		Veiculo veiculo = veiculoUseCase.carregar(codigo);
		veiculoUseCase.excluir(veiculo);	
		
		return ResponseEntity.noContent().build();
	}

}
