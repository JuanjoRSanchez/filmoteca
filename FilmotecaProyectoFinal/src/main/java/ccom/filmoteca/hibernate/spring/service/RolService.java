package ccom.filmoteca.hibernate.spring.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ccom.filmoteca.hibernate.spring.model.Rol;
import ccom.filmoteca.hibernate.spring.repositories.RolRepositories;

@Service
@Transactional
public class RolService {

	@Autowired
	RolRepositories rolRepositories;
	public Optional<Rol> getByRolNombre(String rolNombre){
		return rolRepositories.findByNombre(rolNombre);
	}
	public void save(Rol rol) {
		rolRepositories.save(rol);
	}
}
