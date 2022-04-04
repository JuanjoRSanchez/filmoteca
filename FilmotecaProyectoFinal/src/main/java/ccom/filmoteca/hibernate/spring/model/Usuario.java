package ccom.filmoteca.hibernate.spring.model;


import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
@Table(name = "usuario")
public class Usuario implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_usuario;

	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaAlta = new Date();
	
	
	//@JsonBackReference
	@ManyToMany
	//@ManyToMany(cascade = { CascadeType.ALL}, fetch = FetchType.LAZY )
	@JoinTable(name = "peliculas_usuario", joinColumns =  @JoinColumn(name = "id_usuario"),
	inverseJoinColumns =  @JoinColumn(name = "id_pelicula"))
	private Set<Pelicula> peliculas = new HashSet<>();
	
	public Usuario(Long id_usuario, String name, String password, String email, Date fechaAlta,
			Set<Pelicula> peliculas) {
		this.id_usuario = id_usuario;
		this.name = name;
		this.password = password;
		this.email = email;
		this.fechaAlta = fechaAlta;
		this.peliculas = peliculas;
	}
	
	public Usuario() {
		
	}

	public Long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Set<Pelicula> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(Set<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}

	
}
