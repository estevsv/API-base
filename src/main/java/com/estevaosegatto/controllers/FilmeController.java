package com.estevaosegatto.controllers;

import com.estevaosegatto.models.Filme;
import com.estevaosegatto.models.Usuario;
import com.estevaosegatto.repository.FilmeRepository;
import com.estevaosegatto.services.ServiceFilme;
import com.estevaosegatto.services.ServiceUsuario;
import com.estevaosegatto.util.CustomErrorType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "Autenticador")
@Tag(name = "Filmes", description = "Gerenciamento de Filmes")
@RequestMapping("filmes")
public class FilmeController {
	
	@Autowired
	private ServiceFilme serviceFilme;
	
	@Autowired
	private ServiceUsuario serviceUsuario;
		
	@Autowired
	private final FilmeRepository filmeDao;
	
	public FilmeController(FilmeRepository filmeDao) {
		this.filmeDao = filmeDao;
	}
	
	@GetMapping
	public ResponseEntity<?> listarTodos(@RequestParam(name = "diretor",required = false, defaultValue="")String diretor,
			@RequestParam(name = "nome",required = false,defaultValue= "")String nome,
			@RequestParam(name = "genero",required = false,defaultValue= "")String genero,
			@RequestParam(name = "atores",required = false,defaultValue= "")String atores,
			@RequestParam(name = "paginas",required = false, defaultValue="0")int numPagina,
			@RequestParam(name = "quantidade",required = false,defaultValue= "0x7fffffff")int qtdItens,
			@RequestParam(name = "ordenacao",required = false,defaultValue= "alfabetica")String tipoOrdenacao)
	{
		
		Pageable paginacao = PageRequest.of(numPagina, qtdItens, Sort.unsorted());
				
		List<Filme> listaFilmes = serviceFilme.filtraFilmes(diretor,nome,genero,atores, paginacao);
		
		listaFilmes = serviceFilme.setQuantidadeVotoToListFilme(listaFilmes);
		listaFilmes = serviceFilme.setMediaVotoToListFilme(listaFilmes);
		
		//Tipo de Ordenações: alfabetica/voto
		listaFilmes =  serviceFilme.ordenaFilmes(listaFilmes,"alfabetica");
		if(!tipoOrdenacao.equals("alfabetica"))
			listaFilmes =  serviceFilme.ordenaFilmes(listaFilmes,tipoOrdenacao);
		
	    return new ResponseEntity<>(serviceFilme.getPages(listaFilmes,qtdItens,numPagina++),HttpStatus.OK);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> retornaFilmePorId(@PathVariable("id") Long id) {
		Filme filme = filmeDao.findById(id).orElse(null);
		
		if(filme == null) 
			return new ResponseEntity<>(new CustomErrorType("Filme não encontrado"), HttpStatus.NOT_FOUND);
		
		filme.setMediaVotos(serviceFilme.mediaVotoPorFilme(filme));
		
		return new ResponseEntity<>(filme,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Filme filme){
		
		Usuario usuarioLogado = serviceUsuario.retornaUsuarioLogado();
		if(!usuarioLogado.isAdministrador()) {
			return new ResponseEntity<>(new CustomErrorType("Somente um usuário administrador pode realizar esse cadastro"), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(filmeDao.save(filme),HttpStatus.OK);
	}	
}
