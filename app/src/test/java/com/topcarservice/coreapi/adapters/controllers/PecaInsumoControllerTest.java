package com.topcarservice.coreapi.adapters.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.topcarservice.coreapi.adapters.controllers.dto.PecaInsumoDTO;
import com.topcarservice.coreapi.adapters.controllers.mappers.PecaInsumoMapper;
import com.topcarservice.coreapi.domain.entities.PecaInsumo;
import com.topcarservice.coreapi.domain.usecases.PecaInsumoUseCase;

@WebMvcTest(PecaInsumoController.class)
class PecaInsumoControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	PecaInsumoUseCase pecaInsumoUseCase;
	
	@MockitoBean
	PecaInsumoMapper pecaInsumoMapper;
	
	private ObjectMapper objectMapper;
	
	PecaInsumo pecaInsumo;
	PecaInsumoDTO param;
	
	@BeforeEach
	public void init() {
		objectMapper = new ObjectMapper();
		Long id = 1L;
		String nome = "Pastilha de roda";
		String descricao = "Pastilha de freio para veículos de passeio";
		
		param = PecaInsumoDTO.builder()
				.nome(nome)
				.descricao(nome)				
				.build();
		
		pecaInsumo = new PecaInsumo(id, nome, descricao, "Brembo", 
				"Ceramic", 50, new BigDecimal("150.00"));
	}

	@Test
	void testCadastrar() throws Exception {				
		
		when(pecaInsumoMapper.map(any(PecaInsumoDTO.class))).thenReturn(pecaInsumo);
		
		mockMvc.perform(
				post("/admin/peca-insumos")					
					.contentType(MediaType.APPLICATION_JSON)						
					.content(objectMapper.writeValueAsString(param))
				)		
			.andExpect(status().isCreated());
	}

	@Test
	void testAtualizar() throws JsonProcessingException, Exception {					
		
		when(pecaInsumoMapper.map(any(PecaInsumoDTO.class))).thenReturn(pecaInsumo);
		
		mockMvc.perform(
				put("/admin/peca-insumos/{id}", 1L)					
					.contentType(MediaType.APPLICATION_JSON)						
					.content(objectMapper.writeValueAsString(param))
				)		
			.andExpect(status().isNoContent());
	}

	@Test
	void testExcluir() throws JsonProcessingException, Exception {
		
		Long id = 1L;						
		
		when(pecaInsumoUseCase.carregar(id)).thenReturn(pecaInsumo);
		
		mockMvc.perform(
				delete("/admin/peca-insumos/{id}", id)						
				)		
			.andExpect(status().isNoContent());
	}

}
