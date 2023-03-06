package com.estevaosegatto.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.estevaosegatto.models.Usuario;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long>{
	List<Usuario> findByLogin(String login);
}
