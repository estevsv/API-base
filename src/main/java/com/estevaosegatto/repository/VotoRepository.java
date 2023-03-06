package com.estevaosegatto.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.estevaosegatto.models.Voto;

public interface VotoRepository extends PagingAndSortingRepository<Voto, Long>{
	List<Voto> findByIdUsuario(Long idUsuario);
}
