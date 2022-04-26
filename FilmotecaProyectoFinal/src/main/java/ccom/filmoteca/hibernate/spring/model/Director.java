package ccom.filmoteca.hibernate.spring.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "director")
public class Director implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_director;
	
	@Column
	private String name;
	
	@Column
	private String lastname;
	
	@Column
	private String anio;
	
	@OneToMany(mappedBy = "director", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	Set<Pelicula> peliculas = new HashSet<>();
	

	public Director() {
		
	}

	public Director(Long id_director, String name, String lastname, String anio, Set<Pelicula> peliculas) {
		super();
		this.id_director = id_director;
		this.name = name;
		this.lastname = lastname;
		this.anio = anio;
		this.peliculas = peliculas;
	}

	
	public Long getId_director() {
		return id_director;
	}

	public void setId_director(Long id_director) {
		this.id_director = id_director;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public Set<Pelicula> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(Set<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}

	@Override
	public String toString() {
		return "Director [id_director=" + id_director + ", name=" + name + ", lastname=" + lastname + ", anio=" + anio
				+ ", peliculas=" + peliculas + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(anio, id_director, lastname, name, peliculas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Director other = (Director) obj;
		return Objects.equals(anio, other.anio) && Objects.equals(id_director, other.id_director)
				&& Objects.equals(lastname, other.lastname) && Objects.equals(name, other.name)
				&& Objects.equals(peliculas, other.peliculas);
	}
	
	

}
