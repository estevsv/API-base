package com.estevaosegatto.models;

import javax.persistence.Entity;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class Filme extends AbstractEntity{
	private String diretor;
	private String nome;
	private String genero;
	private String atores;
	@Transient
	private Long quantidadeVotos;
	@Transient
	private double mediaVotos;
	
	
	public String getDiretor() {
		return diretor;
	}
	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getAtores() {
		return atores;
	}
	public void setAtores(String atores) {
		this.atores = atores;
	}
	
	public double getMediaVotos() {
		return mediaVotos;
	}
	public void setMediaVotos(double mediaVotos) {
		this.mediaVotos = mediaVotos;
	}
	public Long getQuantidadeVotos() {
		return quantidadeVotos;
	}
	public void setQuantidadeVotos(Long quantidadeVotos) {
		this.quantidadeVotos = quantidadeVotos;
	}

}
