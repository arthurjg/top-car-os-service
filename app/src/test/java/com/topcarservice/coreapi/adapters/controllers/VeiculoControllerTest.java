package com.topcarservice.coreapi.adapters.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.topcarservice.coreapi.adapters.controllers.dto.VeiculoDTO;
import com.topcarservice.coreapi.adapters.controllers.mappers.VeiculoMapper;
import com.topcarservice.coreapi.domain.entities.Veiculo;
import com.topcarservice.coreapi.domain.usecases.VeiculoUseCase;

@WebMvcTest(VeiculoController.class)
class VeiculoControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	VeiculoUseCase veiculoUseCase;
	
	@MockitoBean
	VeiculoMapper veiculoMapper;
	
	private ObjectMapper objectMapper;
	
	Veiculo veiculo;
	VeiculoDTO veiculoDTO;
	
	@BeforeEach
	public void init() {
		objectMapper = new ObjectMapper();
		Long id = 1L;
		String placa = "QHU-5R66";
		String marca = "Porche";
		String modelo = "Cayenne";
		int ano = 2022;
		veiculo = new Veiculo(id, placa, marca, modelo, ano);
		veiculoDTO = VeiculoDTO.builder()
				.placa(placa)
				.marca(marca)
				.modelo(modelo)
				.ano(ano)
				.build();	
	}

	@Test
	void testCadastrar() throws Exception {						
		
		when(veiculoMapper.map(any(VeiculoDTO.class))).thenReturn(veiculo);
		
		mockMvc.perform(
				post("/admin/veiculos")					
					.contentType(MediaType.APPLICATION_JSON)						
					.content(objectMapper.writeValueAsString(veiculoDTO))
				)		
			.andExpect(status().isCreated());
	}

	@Test
	void testAtualizar() throws JsonProcessingException, Exception {						
		
		when(veiculoMapper.map(any(VeiculoDTO.class))).thenReturn(veiculo);
		
		mockMvc.perform(
				put("/admin/veiculos/{id}", 1L)					
					.contentType(MediaType.APPLICATION_JSON)						
					.content(objectMapper.writeValueAsString(veiculoDTO))
				)		
			.andExpect(status().isNoContent());
	}

	@Test
	void testExcluir() throws JsonProcessingException, Exception {
		
		Long id = 1L;						
		
		when(veiculoUseCase.carregar(id)).thenReturn(veiculo);
		
		mockMvc.perform(
				delete("/admin/veiculos/{id}", id)						
				)		
			.andExpect(status().isNoContent());
	}

}
