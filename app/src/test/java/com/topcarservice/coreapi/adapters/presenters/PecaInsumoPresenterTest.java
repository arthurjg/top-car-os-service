package com.topcarservice.coreapi.adapters.presenters;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.topcarservice.coreapi.adapters.controllers.dto.PecaInsumoDTO;
import com.topcarservice.coreapi.adapters.controllers.mappers.PecaInsumoMapper;
import com.topcarservice.coreapi.domain.entities.PecaInsumo;
import com.topcarservice.coreapi.domain.usecases.PecaInsumoUseCase;

@WebMvcTest(PecaInsumoPresenter.class)
class PecaInsumoPresenterTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	PecaInsumoUseCase pecaInsumoUseCase;
	
	@MockitoBean
	PecaInsumoMapper pecaInsumoMapper;

	@Test
	void testBuscar() throws JsonProcessingException, Exception {
		
		Long id = 1L;
		String nome = "Pastilha de roda";
		String descricao = "Pastilha de freio para veículos de passeio";
		
		PecaInsumoDTO pecaInsumoDTO = PecaInsumoDTO.builder()
				.nome(nome)
				.descricao(nome)				
				.build();
		
		var pecaInsumo = 
				new PecaInsumo(id, nome, descricao, "Brembo", "Ceramic", 50, new BigDecimal("150.00"));
		
		when(pecaInsumoUseCase.carregar(id)).thenReturn(pecaInsumo);
		
		when(pecaInsumoMapper.mapTo(any(PecaInsumo.class))).thenReturn(pecaInsumoDTO);
		
		mockMvc.perform(
				get("/admin/peca-insumos/{id}", id)
                	.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())				
				.andExpect(jsonPath("$.nome").value(nome));
	}

}
