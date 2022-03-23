package ccom.filmoteca.hibernate.spring.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import ccom.filmoteca.hibernate.spring.model.Pelicula;
import ccom.filmoteca.hibernate.spring.repositories.PeliculasRepositories;

@Service
public class PeliculaService {

    @Autowired
    private PeliculasRepositories peliculasRepositories;

    @Autowired
    public void PeluculaServide(PeliculasRepositories peliculasRepositories) {
        this.peliculasRepositories = peliculasRepositories;
    }

    @Bean
    public List<Pelicula> getPelicula() {
        return peliculasRepositories.findAll();
    }

    public Optional<Pelicula> getPeliculaByTitle(String title) {
        Optional<Pelicula> pelicula = peliculasRepositories.findPeliculaByTitle(title);
        return pelicula;

    }

    public void addNewPelicula(Pelicula pelicula) {
        Optional<Pelicula> peliculaOptional = peliculasRepositories.findPeliculaByTitle(pelicula.getTitle());
        if (peliculaOptional.isPresent()) {
            throw new IllegalStateException("La pelicula ya existe en la base de datos");

        }
        peliculasRepositories.save(pelicula);
    }

    public void deletePelicula(String peliculaTitle) {
        // boolean exists = peliculasRepositories.existsByTitle(peliculaTitle);
        Optional<Pelicula> es = getPeliculaByTitle(peliculaTitle);
        if (es == null) {
            throw new IllegalStateException("La película con título " + peliculaTitle +
                    " no está en la base de datos");
        }
        Optional<Pelicula> pelicula = peliculasRepositories.findPeliculaByTitle(peliculaTitle);
        PeliculasRepositories.delete(pelicula);
    }

    @Transactional
    public void updatePelicula(String title, String nameDirector, String anio) {

        Pelicula pelicula = getPeliculaByTitle(title).orElseThrow(() -> new IllegalStateException(
                "Usuario con id " + title + " no existe en la base de datos"));
        if (title != null && title.length() > 0 && !Objects.equals(pelicula.getTitle(), title)) {
            pelicula.setTitle(title);
        }
        if (nameDirector != null && nameDirector.length() > 0
                && !Objects.equals(pelicula.getNameDirector(), nameDirector)) {
            pelicula.setNameDirector(nameDirector);
        }
        if (anio != null && anio.length() > 0 && !Objects.equals(pelicula.getAnio(), anio)) {
            pelicula.setAnio(anio);
        }
    }

}
