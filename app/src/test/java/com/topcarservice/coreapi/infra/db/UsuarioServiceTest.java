package com.topcarservice.coreapi.infra.db;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.topcarservice.coreapi.domain.Usuario;
import com.topcarservice.coreapi.infra.db.mappers.UsuarioDetailsMapper;
import com.topcarservice.coreapi.infra.db.repositories.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {
	
	@InjectMocks
	UsuarioService usuarioService;
	
	@Mock
	UsuarioRepository usuarioRepository;
	
	@Mock
	UsuarioDetailsMapper usuarioMapper;

	@Test
	void testLoadUserByUsername() {
		
		String login = "testuser";
		
		Usuario usuarioEntity = Usuario.builder()
				.login(login)			
				.build();
		
		UserDetails userDetais = 
				new User(login, "123456", List.of(new SimpleGrantedAuthority("ROLE_USER")) );		
		
		when(usuarioRepository.findByLogin(login)).thenReturn(usuarioEntity);
		
		when(usuarioMapper.convertUsuario(usuarioEntity)).thenReturn(userDetais);
		
		UserDetails udResult = usuarioService.loadUserByUsername(login);
		
		assertNotNull(udResult);		
		
	}

}
