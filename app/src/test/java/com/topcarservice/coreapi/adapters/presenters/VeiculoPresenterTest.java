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
import com.topcarservice.coreapi.adapters.controllers.dto.VeiculoDTO;
import com.topcarservice.coreapi.adapters.controllers.mappers.VeiculoMapper;
import com.topcarservice.coreapi.domain.entities.Veiculo;
import com.topcarservice.coreapi.domain.usecases.VeiculoUseCase;

@WebMvcTest(VeiculoPresenter.class)
class VeiculoPresenterTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	VeiculoUseCase veiculoUseCase;
	
	@MockitoBean
	VeiculoMapper veiculoMapper;

	@Test
	void testBuscar() throws JsonProcessingException, Exception {
		
		Long id = 1L;
		String placa = "QHU-5R66";
		String marca = "Porche";
		String modelo = "Cayenne";
		int ano = 2022;
		
		VeiculoDTO veiculoDTO = VeiculoDTO.builder()
				.placa(placa)
				.marca(marca)
				.modelo(modelo)
				.ano(ano)
				.build();
		
		Veiculo veiculo = new Veiculo(id, placa, marca, modelo, ano);
		
		when(veiculoUseCase.carregar(id)).thenReturn(veiculo);
		
		when(veiculoMapper.mapTo(any(Veiculo.class))).thenReturn(veiculoDTO);
		
		mockMvc.perform(
				get("/admin/veiculos/{id}", 1L)
                	.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())				
				.andExpect(jsonPath("$.placa").value(placa));
	}

}
