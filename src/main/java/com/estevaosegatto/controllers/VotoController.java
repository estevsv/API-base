package com.estevaosegatto.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estevaosegatto.models.Usuario;
import com.estevaosegatto.models.Voto;
import com.estevaosegatto.repository.FilmeRepository;
import com.estevaosegatto.repository.VotoRepository;
import com.estevaosegatto.services.ServiceUsuario;
import com.estevaosegatto.util.CustomErrorType;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@SecurityRequirement(name = "Autenticador")
@Tag(name = "Voto", description = "Gerenciamento de Votos")
@RequestMapping("voto")
public class VotoController {
	
	@Autowired
	private ServiceUsuario serviceUsuario;
		
	@Autowired
	private FilmeRepository filmeDao;
	
	@Autowired
	private final VotoRepository votoDao;
	
	public VotoController(VotoRepository votoDao) {
		this.votoDao = votoDao;
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Voto voto){
				
		if(filmeDao.findById(voto.getIdFilme()).orElse(null) == null) 
			return new ResponseEntity<>(new CustomErrorType("Filme não encontrado"), HttpStatus.NOT_FOUND);
		
		if(voto.getVotoFilme() < 0 || voto.getVotoFilme() > 4) 
			return new ResponseEntity<>(new CustomErrorType("Voto Inválido."), HttpStatus.BAD_REQUEST);
		
		Usuario usuarioLogado = serviceUsuario.retornaUsuarioLogado();
		
		if(usuarioLogado.isAdministrador()) {
			return new ResponseEntity<>(new CustomErrorType("Apenas usuários não administradores podem votar em filmes."), HttpStatus.BAD_REQUEST);
		}
		
		//Ignora caso o usuário tenha preenchido o campo idUsuario, usando apenas o ID do usuário usado na operação
		voto.setIdUsuario(usuarioLogado.getId());
		
		//Caso o usuário já tenha votado neste filme anteriormente, atualiza o voto implicitamente
		Voto votoAuxiliar = voto;
		List<Voto> listaVotoUsuario = StreamSupport.stream(votoDao.findByIdUsuario(voto.getIdUsuario()).spliterator(), false)
			    .collect(Collectors.toList());
		
		for(Voto votoList : listaVotoUsuario) {
			if(votoList.getIdFilme() == voto.getIdFilme() && votoList.getIdUsuario() == voto.getIdUsuario()) {
				votoAuxiliar = votoList;
				votoAuxiliar.setVotoFilme(voto.getVotoFilme());
			}
		}
		
		return new ResponseEntity<>(votoDao.save(votoAuxiliar),HttpStatus.OK);
	}
}
	