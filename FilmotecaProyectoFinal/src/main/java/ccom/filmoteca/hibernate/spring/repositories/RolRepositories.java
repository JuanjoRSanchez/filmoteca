package ccom.filmoteca.hibernate.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ccom.filmoteca.hibernate.spring.model.Rol;

@Repository
public interface RolRepositories extends JpaRepository<Rol, Long>{
	

}
