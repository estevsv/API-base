package com.estevaosegatto.models;

import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Voto extends AbstractEntity{
	private Long idUsuario;
	private Long idFilme;
	private Long votoFilme;

	
	
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Long getIdFilme() {
		return idFilme;
	}
	public void setIdFilme(Long idFilme) {
		this.idFilme = idFilme;
	}
	public Long getVotoFilme() {
		return votoFilme;
	}
	public void setVotoFilme(Long votoFilme) {
		this.votoFilme = votoFilme;
	}
}
