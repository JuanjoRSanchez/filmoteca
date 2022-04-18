package ccom.filmoteca.hibernate.spring.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "pelicula")
public class Pelicula implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pelicula;

	@Column(unique = true)
    private String title;
	
    @Column(name = "anio")
    private String anio;
    
    @Column(name = "nota")
    private String nota;
    
    //@JsonManagedReference
    @ManyToMany
    //@ManyToMany(mappedBy = "peliculas", fetch = FetchType.LAZY)  
    private Set<Usuario> usuarios = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "id_director")
    private Director director;
    
    public Pelicula() {
    }
	
	public Pelicula(Long id_pelicula, String title, String anio, String nota, Set<Usuario> usuarios,
			Director director) {
		super();
		this.id_pelicula = id_pelicula;
		this.title = title;
		this.anio = anio;
		this.nota = nota;
		this.usuarios = usuarios;
		this.director = director;
	}


	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public Long getId_pelicula() {
		return id_pelicula;
	}

	public void setId_pelicula(Long id_pelicula) {
		this.id_pelicula = id_pelicula;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

	
	
   
}
