package ccom.filmoteca.hibernate.spring.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


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
    
    @JsonIgnore
    @OneToMany(mappedBy = "pelicula", orphanRemoval = true)
    private List<Pelis_usus> pelis_usus = new ArrayList<Pelis_usus>();
    
    @ManyToOne()
    @JoinColumn(name = "id_director")
    private Director director;
    
    public Pelicula() {
    }
	
	public Pelicula(Long id_pelicula, String title, String anio,
			List<Pelis_usus> pelis_usus, Director director) {
		super();
		this.id_pelicula = id_pelicula;
		this.title = title;
		this.anio = anio;
		this.pelis_usus = pelis_usus;
		this.director = director;
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


	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

	public List<Pelis_usus> getPelis_usus() {
		return pelis_usus;
	}

	public void setPelis_usus(List<Pelis_usus> pelis_usus) {
		this.pelis_usus = pelis_usus;
	}	
   
}
