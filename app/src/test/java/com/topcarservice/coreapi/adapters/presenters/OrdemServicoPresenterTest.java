package com.topcarservice.coreapi.adapters.presenters;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.topcarservice.coreapi.adapters.controllers.dto.OrdemServicoRetornoDTO;
import com.topcarservice.coreapi.adapters.controllers.mappers.OrdemServicoMapper;
import com.topcarservice.coreapi.domain.entities.OrdemServico;
import com.topcarservice.coreapi.domain.usecases.OrdemServicoUseCase;

@WebMvcTest(OrdemServicoPresenter.class)
class OrdemServicoPresenterTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	OrdemServicoUseCase ordemServicoUseCase;
	
	@MockitoBean
	OrdemServicoMapper ordemServicoMapper;

	@Test
	void testBuscar() throws JsonProcessingException, Exception {
		
		Long id = 1L;		
		
		OrdemServicoRetornoDTO ordemServicoDTO = OrdemServicoRetornoDTO.builder()	
				.codigo(id)
				.build();
		
		OrdemServico ordemServico = new OrdemServico();
		
		when(ordemServicoUseCase.carregar(id)).thenReturn(ordemServico);
		
		when(ordemServicoMapper.map(any(OrdemServico.class))).thenReturn(ordemServicoDTO);
		
		mockMvc.perform(
				get("/public/ordem-servicos/{id}", id)
                	.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())				
				.andExpect(jsonPath("$.codigo").value(id));
	}

}
