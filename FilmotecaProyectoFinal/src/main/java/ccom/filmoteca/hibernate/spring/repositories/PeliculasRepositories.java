package ccom.filmoteca.hibernate.spring.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ccom.filmoteca.hibernate.spring.model.Pelicula;

@Repository
public interface PeliculasRepositories extends JpaRepository<Pelicula, Long> {

    @Query
    Optional<Pelicula> findPeliculaByTitle(String title);

    static void delete(Optional<Pelicula> pelicula) {

    };

}