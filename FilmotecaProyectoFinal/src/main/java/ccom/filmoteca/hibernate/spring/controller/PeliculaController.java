package ccom.filmoteca.hibernate.spring.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ccom.filmoteca.hibernate.spring.dto.AddPeliculaDTO;
import ccom.filmoteca.hibernate.spring.model.Pelicula;
import ccom.filmoteca.hibernate.spring.model.Pelis_usus;
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
	
	@GetMapping(value = "/listas/{emailUsuario}")
	public List<AddPeliculaDTO> getListPeliculasUsuarios(@PathVariable("emailUsuario") String emailUsuario) {
				return peliculaService.getPeliculasAddDTO(emailUsuario);
	}

	@GetMapping(value = "/{idPelicula}")
	public AddPeliculaDTO getListPeliculasUsuarioById(@PathVariable("idPelicula") Long idPelicula) {
		
		return peliculaService.getPeliculaUsusById(idPelicula);
	}
	
//	@RequestMapping(value = "titulo/{tituloPelicula}")
//	public Pelicula  getPeliculabyTitle(@PathVariable("tituloPelicula") String tituloPelicula) {	
//		Pelicula pelicula = new Pelicula();	
//		pelicula = peliculaService.getPeliculaTitulo(tituloPelicula);
//	
//		return pelicula;
//	}
	
//	@RequestMapping(value = "titulo/{tituloPelicula}")
//	public List<Pelicula>   getPeliculabyTitleAJAX(@PathVariable("tituloPelicula") String tituloPelicula) {	
//		List<Pelicula>  pelicula = new ArrayList<Pelicula>();	
//		pelicula = peliculaService.getPeliculaTitulo(tituloPelicula);
//	
//		return pelicula;
//	}
//	
	@RequestMapping(value = "titulo/{tituloPelicula}")
	public List<AddPeliculaDTO>   getPeliculabyTitleAJAX(@PathVariable("tituloPelicula") String tituloPelicula) {	
		List<AddPeliculaDTO> listaDTO = new ArrayList<AddPeliculaDTO>();
		List<Pelicula>  pelicula = new ArrayList<Pelicula>();	
		pelicula = peliculaService.getPeliculaTitulo(tituloPelicula);
		for(Pelicula p:pelicula) 
		{
			AddPeliculaDTO addPeliculaDTO = new AddPeliculaDTO();
			addPeliculaDTO.setAnio(p.getAnio());
			addPeliculaDTO.setTitle(p.getTitle());
			addPeliculaDTO.setNombreDirector(p.getDirector().getName());
			listaDTO.add(addPeliculaDTO);
		}
	
		return listaDTO;
	}
	
	@RequestMapping(value = "id/{IdPelicula}")
	public Pelicula  getPeliculaById(@PathVariable("IdPelicula") Long IdPelicula) {	
		Pelicula pelicula = new Pelicula();	
		pelicula = peliculaService.getPeliculaById(IdPelicula);
	
		return pelicula;
	}
	
	@RequestMapping(value = "/peli/{tituloPelicula}")
	public List<String>  getPeliculaAJAX(@PathVariable("tituloPelicula") String tituloPelicula) {	
		List<String> resultList = new ArrayList<String>();
		resultList = peliculaService.getPeliculaTituloAJAX(tituloPelicula);
		
		return resultList;
	}
	
//	@RequestMapping(value = "/peli/{tituloPelicula}")
//	public List<String>  getPeliculasByAnio(@PathVariable("tituloPelicula") String tituloPelicula) {	
//		List<String> resultList = new ArrayList<String>();	
//		resultList = peliculaService.getPeliculaTituloAJAX(tituloPelicula);
//		return resultList;
//	}
	
	// Modificar una pelicula_usu
	@PutMapping(value = "/update/{id}")
	public String updatePeliculaUsus( @PathVariable Long id,@RequestBody AddPeliculaDTO addPeliculaDTO) {			
		return peliculaService.updatePeliculaUsu(addPeliculaDTO, id);
	}
	
	@PostMapping(value = "/add")
	public String setPelicula(@RequestBody AddPeliculaDTO addpeliculadto) {
		return peliculaService.setPelicula(addpeliculadto);
	}

	@DeleteMapping(value = "/borrar/{id}")
	public String deletePeliculaById( @PathVariable("id") Long id) {
		return  peliculaService.deletePeliculaById(id);
	}
	
}
