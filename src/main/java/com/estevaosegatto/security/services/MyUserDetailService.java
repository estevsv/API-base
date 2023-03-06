package com.estevaosegatto.security.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.estevaosegatto.models.Usuario;
import com.estevaosegatto.repository.UsuarioRepository;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
    private UsuarioRepository interfaceUsuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		 
		List<Usuario> listUsuario = interfaceUsuarioRepository.findByLogin(login);
        if (listUsuario.size() == 0 || !listUsuario.get(0).isAtivo()) {
            throw new UsernameNotFoundException(login);
        }
        
		return new User(listUsuario.get(0).getLogin(), listUsuario.get(0).getSenha(), new ArrayList<>());
	}

}
