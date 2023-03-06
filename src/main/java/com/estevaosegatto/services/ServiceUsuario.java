package com.estevaosegatto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.estevaosegatto.models.Usuario;
import com.estevaosegatto.repository.UsuarioRepository;

@Component
public class ServiceUsuario {
	
	@Autowired
    private UsuarioRepository interfaceUsuarioRepository;
	
	public Usuario retornaUsuarioLogado() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();
		Usuario usuario = interfaceUsuarioRepository.findByLogin(username).get(0);
		
		return usuario;
	}
}
