package ccom.filmoteca.hibernate.spring.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "pelis_usus")
public class Pelis_usus implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name =  "usuarioId", referencedColumnName =  "id_usuario", updatable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JsonIgnore
	private Usuario usuario;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name =  "pelicualId", referencedColumnName =   "id_pelicula", updatable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Pelicula pelicula;

	private String nota;
	
	private String comentario;
	
	private boolean vista;
	
	public Pelis_usus(Long id, Usuario usuario, Pelicula pelicula, String nota, String comentario, boolean vista) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.pelicula = pelicula;
		this.nota = nota;
		this.comentario = comentario;
		this.vista = vista;
	}

	public Pelis_usus() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public boolean isVista() {
		return vista;
	}

	public void setVista(boolean vista) {
		this.vista = vista;
	}

	@Override
	public int hashCode() {
		return Objects.hash(comentario, id, nota, pelicula, usuario, vista);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pelis_usus other = (Pelis_usus) obj;
		return Objects.equals(comentario, other.comentario) && Objects.equals(id, other.id)
				&& Objects.equals(nota, other.nota) && Objects.equals(pelicula, other.pelicula)
				&& Objects.equals(usuario, other.usuario) && vista == other.vista;
	}

	@Override
	public String toString() {
		return "Pelis_usus [id=" + id + ", usuario=" + usuario + ", pelicula=" + pelicula + ", nota=" + nota
				+ ", comentario=" + comentario + ", vista=" + vista + "]";
	}





	
	
	
}