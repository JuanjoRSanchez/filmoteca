package ccom.filmoteca.hibernate.spring.repositories;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ccom.filmoteca.hibernate.spring.model.Pelicula;
import ccom.filmoteca.hibernate.spring.model.Usuario;

@Repository
public interface PeliculasRepositories extends JpaRepository<Pelicula, Long> {

    @Query
    Optional<Pelicula> findPeliculaByTitle(String title);
    
    @Query  	
    List<Pelicula> findByUsuarios(Usuario usuario);
}