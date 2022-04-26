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

import ccom.filmoteca.hibernate.spring.dto.AddPeliculaDTO;
import ccom.filmoteca.hibernate.spring.dto.AddPeliculaUsuarioDirector;
import ccom.filmoteca.hibernate.spring.dto.PeliculaDTO;
import ccom.filmoteca.hibernate.spring.model.Pelicula;
import ccom.filmoteca.hibernate.spring.model.Pelis_usus;
import ccom.filmoteca.hibernate.spring.model.Usuario;
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
	
	@GetMapping(value = "/lista/{emailUsuario}")
	public List<Pelis_usus> getListPeliculasUsuario(@PathVariable("emailUsuario") String emailUsuario) {
		return peliculaService.getPeliculas(emailUsuario);
	}
	// Devuelve una pelicula de un usuario 
	@GetMapping(value = "/pelicula")
	public Pelis_usus getListPeliculaUsuario(@RequestParam(name= "email") String email, @RequestParam(name="titulo") String titulo) {
		return peliculaService.getPelicula_Usu(email, titulo);
	}
	
	// Modificar una pelicula_usu
//	@PostMapping(value = "/mod")
//	public String updatePeliculaUsu(@RequestBody String id) {
//		
//	}
	
	
	@PostMapping(value = "/add")
	public String setPelicula(@RequestBody AddPeliculaDTO addpeliculadto) {
		return peliculaService.setPelicula(addpeliculadto);
	}
	
//	@RequestMapping(value = "/titulo/{titulo}")
//	public Pelicula getPeliculaByTitulooo(@PathVariable("titulo") String titulo) {
//		return peliculaService.getPeliculaTitulo(titulo);
//	}

	@DeleteMapping(value = "title")
	public void deletePeliculaByTitle(@RequestBody PeliculaDTO peliculaDTO) {
		peliculaService.deletePeliculaByTitle(peliculaDTO);
	}

//	
//	@PutMapping
//	public void updatePelicula(@RequestBody Pelicula pelicula ) {
//
//		peliculaService.updatePelicula(pelicula);
//
//	}
	
	
	
	
}
