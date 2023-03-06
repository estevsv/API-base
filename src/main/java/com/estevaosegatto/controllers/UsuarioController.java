package com.estevaosegatto.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estevaosegatto.models.Usuario;
import com.estevaosegatto.repository.UsuarioRepository;
import com.estevaosegatto.util.CustomErrorType;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@SecurityRequirement(name = "Autenticador")
@Tag(name = "Usuários", description = "Gerenciamento de Usuários")
@RequestMapping("usuarios")
public class UsuarioController {
	
	@Autowired
    private UsuarioRepository interfaceUsuarioRepository;
	
	@Autowired
	private final UsuarioRepository usuarioDao;
	
	public UsuarioController(UsuarioRepository usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	@GetMapping
	public ResponseEntity<?> listarTodosUsuariosPadrao(@RequestParam(name = "paginas",required = false, defaultValue="0")int qtdPaginas,
			@RequestParam(name = "quantidade",required = false,defaultValue= "0x7fffffff")int qtdItens) {
		
		Pageable paginacao = PageRequest.of(qtdPaginas, qtdItens, Sort.by(Order.asc("login")));
		List<Usuario> listaUsuarioAuxiliar = StreamSupport.stream(usuarioDao.findAll(paginacao).spliterator(), false)
			    .collect(Collectors.toList());
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		for(Usuario usuario : listaUsuarioAuxiliar)
			if(!usuario.isAdministrador() && usuario.isAtivo())
				listaUsuarios.add(usuario);
		
		return new ResponseEntity<>(listaUsuarios,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Usuario usuario){
		
		List<Usuario> listUsuario = interfaceUsuarioRepository.findByLogin(usuario.getLogin());
		if(listUsuario.size() > 0) {
			return new ResponseEntity<>(new CustomErrorType("Login já existente."), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(usuarioDao.save(usuario),HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> desativar(@PathVariable("id") Long id){
		Usuario usuario = usuarioDao.findById(id).orElse(null);
		
		if(usuario == null) 
			return new ResponseEntity<>(new CustomErrorType("Usuário não encontrado"), HttpStatus.NOT_FOUND);
		
		usuario.setAtivo(false);
		usuarioDao.save(usuario);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){
		if(usuarioDao.findById(usuario.getId()).orElse(null) == null) 
			return new ResponseEntity<>(new CustomErrorType("Usuário não encontrado"), HttpStatus.NOT_FOUND);
		usuarioDao.save(usuario);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
