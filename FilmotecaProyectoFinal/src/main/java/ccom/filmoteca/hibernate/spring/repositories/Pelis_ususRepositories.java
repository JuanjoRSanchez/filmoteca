package ccom.filmoteca.hibernate.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ccom.filmoteca.hibernate.spring.model.Pelicula;
import ccom.filmoteca.hibernate.spring.model.Pelis_usus;
import ccom.filmoteca.hibernate.spring.model.Usuario;

@Repository
public interface Pelis_ususRepositories extends JpaRepository<Pelis_usus, Long>{

	
	@Query
	List<Pelis_usus> findByUsuario(Usuario usuario);	
//	
//	@Query
//    Pelis_usus findByPelicula(Pelicula pelicula);
	
	Pelis_usus findByPelicula(Pelicula pelicula);
	
 
	
	
}
