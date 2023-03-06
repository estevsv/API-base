package com.estevaosegatto.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.estevaosegatto.models.Filme;

public interface FilmeRepository extends PagingAndSortingRepository<Filme, Long>{
}
