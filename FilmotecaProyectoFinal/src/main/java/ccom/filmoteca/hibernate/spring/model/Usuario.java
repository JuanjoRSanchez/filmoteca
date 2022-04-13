package ccom.filmoteca.hibernate.spring.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
@Table(name = "usuario")
@NoArgsConstructor
@AllArgsConstructor
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
	
	
	@JsonBackReference
	@ManyToMany
	//@ManyToMany(cascade = { CascadeType.ALL}, fetch = FetchType.LAZY )
	@JoinTable(name = "peliculas_usuario", joinColumns =  @JoinColumn(name = "id_usuario"),
	inverseJoinColumns =  @JoinColumn(name = "id_pelicula"))
	private Set<Pelicula> peliculas = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<Rol> roles = new ArrayList<>();

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

	public Collection<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Rol> roles) {
		this.roles = roles;
	}
	
	

	
}
