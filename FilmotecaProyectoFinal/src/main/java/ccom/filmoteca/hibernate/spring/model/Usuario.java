package ccom.filmoteca.hibernate.spring.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
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


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


@Entity
@Table(name = "usuario")
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_usuario;

	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaAlta = new Date();
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true )
	@JsonIgnore
	private List<Pelis_usus> pelis_usus = new ArrayList<Pelis_usus>();
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<Rol> roles = new ArrayList<>();
	
	public Usuario() {
		super();
	}

	public Usuario(Long id_usuario, String name, String password, String email, Date fechaAlta,
			List<Pelis_usus> pelis_usus, Collection<Rol> roles) {
		super();
		this.id_usuario = id_usuario;
		this.name = name;
		this.password = password;
		this.email = email;
		this.fechaAlta = fechaAlta;
		this.pelis_usus = pelis_usus;
		this.roles = roles;
	}

	public List<Pelis_usus> getPelis_usus() {
		return pelis_usus;
	}

	public void setPelis_usus(List<Pelis_usus> pelis_usus) {
		this.pelis_usus = pelis_usus;
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

	public Collection<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Rol> roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, fechaAlta, id_usuario, name, password, pelis_usus, roles);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(email, other.email) && Objects.equals(fechaAlta, other.fechaAlta)
				&& Objects.equals(id_usuario, other.id_usuario) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && Objects.equals(pelis_usus, other.pelis_usus)
				&& Objects.equals(roles, other.roles);
	}


	
}
