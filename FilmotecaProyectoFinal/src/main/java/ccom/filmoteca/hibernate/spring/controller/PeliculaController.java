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
import org.springframework.web.bind.annotation.RestController;

import ccom.filmoteca.hibernate.spring.dto.AddPeliculaUsuarioDirector;
import ccom.filmoteca.hibernate.spring.model.Pelicula;
import ccom.filmoteca.hibernate.spring.service.PeliculaService;

@RestController
@RequestMapping(path = "filmania/v1/pelicula")
public class PeliculaController {


	@Autowired
	private final PeliculaService peliculaService;

	@Autowired
	public PeliculaController(PeliculaService peliculaService) {
		this.peliculaService = peliculaService;
	}

	@GetMapping(value = "/")
	public List<Pelicula> getListPeliculas() {
		return peliculaService.getPelicula();
	}

	@RequestMapping(value = "{peliculaId}")
	public Pelicula getPeliculaById(@PathVariable("peliculaId") Long peliculaId) {
		return peliculaService.getPeliculaById(peliculaId);
		
	}
	
	@RequestMapping(value = "title/{peliculaTitle}")
	public Optional<Pelicula> getPeliculaByTitle(@PathVariable("peliculaTitle") String peliculaTitle) {
		return peliculaService.getPeliculaByTitle(peliculaTitle);
	}

	//Añadir película
	/*
	@PostMapping(consumes = {"application/json"})
	public Pelicula registerNewPelicula(@RequestBody Pelicula pelicula) {
		return peliculaService.addNewPelicula(pelicula);
		
	}
	*/
	
	//Añadir película
	@PostMapping
	public void registerNewPelicula(@RequestBody AddPeliculaUsuarioDirector addPeliculaUsuarioDirector) {
			peliculaService.addNewPeliculaUsuarioDirector(addPeliculaUsuarioDirector);
			
	}
	
	@DeleteMapping(value = "{idPelicula}")
	public void deletePelicula(@PathVariable("idPelicula") Long idPelicula) {
		peliculaService.deletePelicula(idPelicula);
	}

	@PutMapping
	public void updatePelicula(@RequestBody Pelicula pelicula ) {

		peliculaService.updatePelicula(pelicula);

	}
	
	
	
	
}
