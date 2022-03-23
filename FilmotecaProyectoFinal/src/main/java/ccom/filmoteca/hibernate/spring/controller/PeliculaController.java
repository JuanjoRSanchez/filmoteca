package ccom.filmoteca.hibernate.spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ccom.filmoteca.hibernate.spring.model.Pelicula;
import ccom.filmoteca.hibernate.spring.service.PeliculaService;

@RestController
@RequestMapping(path = "filmania/v1/pelicula")
public class PeliculaController {

    private final PeliculaService peliculaService;

    @Autowired
    public PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @GetMapping(value = "/")
    public List<Pelicula> getListPeliculas() {
        return peliculaService.getPelicula();
    }

    @RequestMapping(value = "{peliculaTitle}")
    public Optional<Pelicula> getPeliculaByTitle(@PathVariable("peliculaTitle") String peliculaTitle) {
        return peliculaService.getPeliculaByTitle(peliculaTitle);

    }

    @PostMapping
    public void registerNewUsuario(@RequestBody Pelicula pelicula) {
        peliculaService.addNewPelicula(pelicula);
    }

    @DeleteMapping(path = "{peliculaTitle}")
    public void deletePelicula(@PathVariable("peliculaTitle") String peliculaTitle) {
        peliculaService.deletePelicula(peliculaTitle);
    }

    @PutMapping(path = "{peliculaTitle}")
    public void updatePelicula(
            @PathVariable("peliculaTitle") String title,
            @RequestParam(required = false) String nameDirector,
            @RequestParam(required = false) String anio) {

        peliculaService.updatePelicula(title, nameDirector, anio);

    }
}
