package ccom.filmoteca.hibernate.spring.dto;

import java.util.Objects;

import ccom.filmoteca.hibernate.spring.model.Pelis_usus;

public class PeliculaDTO {

	private String email_usuario;
	
	private String titulo_pelicula;
	
	private Pelis_usus pelis_usus;

	public PeliculaDTO() {
		
	}

	public PeliculaDTO(String email_usuario, String titulo_pelicula, Pelis_usus pelis_usus) {
		this.email_usuario = email_usuario;
		this.titulo_pelicula = titulo_pelicula;
		this.pelis_usus = pelis_usus;
	}

	public String getEmail_usuario() {
		return email_usuario;
	}

	public void setEmail_usuario(String email_usuario) {
		this.email_usuario = email_usuario;
	}

	public String getTitulo_pelicula() {
		return titulo_pelicula;
	}

	public void setTitulo_pelicula(String titulo_pelicula) {
		this.titulo_pelicula = titulo_pelicula;
	}



	public Pelis_usus getPelis_usus() {
		return pelis_usus;
	}



	public void setPelis_usus(Pelis_usus pelis_usus) {
		this.pelis_usus = pelis_usus;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email_usuario, pelis_usus, titulo_pelicula);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PeliculaDTO other = (PeliculaDTO) obj;
		return Objects.equals(email_usuario, other.email_usuario) && Objects.equals(pelis_usus, other.pelis_usus)
				&& Objects.equals(titulo_pelicula, other.titulo_pelicula);
	}

	@Override
	public String toString() {
		return "PeliculaDTO [email_usuario=" + email_usuario + ", titulo_pelicula=" + titulo_pelicula + ", pelis_usus="
				+ pelis_usus + "]";
	}





	

}
