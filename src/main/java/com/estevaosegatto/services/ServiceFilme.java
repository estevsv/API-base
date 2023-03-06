package com.estevaosegatto.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.estevaosegatto.models.Filme;
import com.estevaosegatto.models.Voto;
import com.estevaosegatto.repository.FilmeRepository;
import com.estevaosegatto.repository.VotoRepository;
import com.estevaosegatto.util.OrderFilmeByVoto;

@Component
public class ServiceFilme implements Comparator<Filme> 
{
	@Autowired
	private FilmeRepository filmeDao;
	
	@Autowired
	private VotoRepository votoDao;
	
    @Override
    public int compare(Filme f1, Filme f2) {
        return f1.getNome().compareToIgnoreCase(f2.getNome());
    }
    
	public <T> List<List<T>> getPages(Collection<T> c, Integer pageSize, int pageNum) {
	    if (c == null)
	        return new ArrayList<List<T>>();
	    List<T> list = new ArrayList<T>(c);
	    if (pageSize == null || pageSize <= 0 || pageSize > list.size())
	        pageSize = list.size();
	    List<List<T>> pages = new ArrayList<List<T>>(pageNum);
        pages.add(list.subList(pageNum * pageSize, Math.min(++pageNum * pageSize, list.size())));
	    return pages;
	}
	
	public List<Filme> filtraFilmes(String diretor,String nome,String genero,String atores, Pageable paginacao){
		List<Filme> listaFilmes = StreamSupport.stream(filmeDao.findAll().spliterator(), false)
			    .collect(Collectors.toList()); 
	
		if(!diretor.isEmpty()) {
			listaFilmes = listaFilmes.stream()
				     .filter(item -> item.getDiretor().equals(diretor))
				     .collect(Collectors.toList());
		}
		
		if(!nome.isEmpty()) {
			listaFilmes = listaFilmes.stream()
				     .filter(item -> item.getNome().equals(nome))
				     .collect(Collectors.toList());
		}
		
		if(!genero.isEmpty()) {
			listaFilmes = listaFilmes.stream()
				     .filter(item -> item.getGenero().equals(genero))
				     .collect(Collectors.toList());
		}
		
		if(!atores.isEmpty()) {
			listaFilmes = listaFilmes.stream()
				     .filter(item -> item.getAtores().equals(atores))
				     .collect(Collectors.toList());
		}
	
		
		return listaFilmes;	
	}
	
	public List<Filme> ordenaFilmes(List<Filme> listaFilmes,String ordenacao){
		
		switch (ordenacao) {
		case "voto":
			listaFilmes.sort(new OrderFilmeByVoto());
			break;

		default://ordenação Alfabética
			listaFilmes.sort(new ServiceFilme());
			break;
		}
		
		return listaFilmes;
	}
	
	public List<Filme> setMediaVotoToListFilme(List<Filme> listaFilmes){
		for(Filme filme : listaFilmes) {
			filme.setMediaVotos(mediaVotoPorFilme(filme));
		}
		return listaFilmes;
	}
	
	public List<Filme> setQuantidadeVotoToListFilme(List<Filme> listaFilmes){
		List<Voto> listaVoto = StreamSupport.stream(votoDao.findAll().spliterator(), false)
			    .collect(Collectors.toList());
		for(Filme filme : listaFilmes) {
			Long cont = Long.valueOf(0);
			for(Voto voto : listaVoto) {
				if(filme.getId().equals(voto.getIdFilme())) {
					cont++;
				}
			}
			filme.setQuantidadeVotos(cont);
		}
		return listaFilmes;
	}
	
	public double mediaVotoPorFilme(Filme filme) {
		double media = 0;
		
		List<Voto> listaVoto = StreamSupport.stream(votoDao.findAll().spliterator(), false)
			    .collect(Collectors.toList());
		int contador = 0;
		
		for (Voto voto : listaVoto) {
			if(filme.getId().equals(voto.getIdFilme())) {
				media += voto.getVotoFilme();
				contador++;
			}
		}
		
		if(contador == 0)
			contador++;
		return media/contador;
	}
}