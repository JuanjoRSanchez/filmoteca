package ccom.filmoteca.hibernate.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ccom.filmoteca.hibernate.spring.dto.AddPeliculaDTO;
import ccom.filmoteca.hibernate.spring.dto.AddPeliculaUsuarioDirector;
import ccom.filmoteca.hibernate.spring.dto.PeliculaDTO;
import ccom.filmoteca.hibernate.spring.model.Director;
import ccom.filmoteca.hibernate.spring.model.Pelicula;
import ccom.filmoteca.hibernate.spring.model.Pelis_usus;
import ccom.filmoteca.hibernate.spring.model.Usuario;
import ccom.filmoteca.hibernate.spring.repositories.DirectorRepository;
import ccom.filmoteca.hibernate.spring.repositories.PeliculasRepositories;
import ccom.filmoteca.hibernate.spring.repositories.Pelis_ususRepositories;
import ccom.filmoteca.hibernate.spring.repositories.UsuarioRepositories;

@Service
public class PeliculaService {

	@Autowired
	private UsuarioRepositories usuarioRepositories;

	@Autowired
	private DirectorRepository directorRepository;

	@Autowired
	private PeliculasRepositories peliculasRepositories;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private Pelis_ususRepositories pelis_ususRepositories;

	@Autowired
	public PeliculaService(UsuarioRepositories usuarioRepositories, DirectorRepository directorRepository,
			PeliculasRepositories peliculasRepositories, Pelis_ususRepositories pelis_ususRepositories) {
		this.usuarioRepositories = usuarioRepositories;
		this.directorRepository = directorRepository;
		this.peliculasRepositories = peliculasRepositories;
		this.pelis_ususRepositories = pelis_ususRepositories;
	}

	public List<Pelicula> getPelicula() {
		return peliculasRepositories.findAll();
	}

	public Pelicula getPeliculaById(Long id) {

		Optional<Pelicula> pelicula = peliculasRepositories.findById(id);
		if (!pelicula.isPresent()) {
			throw new IllegalStateException("La pelicula no existe en la base de datos");
		}

		return pelicula.get();
	}

	public Pelicula addNewPelicula(Pelicula pelicula) {
		Director director = new Director();
		director = directorRepository.findDirectorByName(pelicula.getDirector().getName()).orElseThrow();

		Optional<Pelicula> peliculaOptional = Optional
				.ofNullable(peliculasRepositories.findByTitle(pelicula.getTitle()));

		if (director != null) {
			pelicula.setDirector(director);

		}
		if (peliculaOptional.isPresent()) {

			throw new IllegalStateException("La pelicula ya existe en la base de datos");
		}
		return peliculasRepositories.save(pelicula);
	}

	public String setPelicula(AddPeliculaDTO addpeliculadto) {
		String result = "ok";
		Pelis_usus pelis_usus = new Pelis_usus();
		
		Usuario  usuario = usuarioService.getUsuarioByMail(addpeliculadto.getEmailusuario());
		pelis_usus.setUsuario(usuario);
		
		Pelicula pelicula = new Pelicula();
		Optional<Pelicula>  peliculaOp = Optional.ofNullable(getPeliculaTitulo(addpeliculadto.getTitle()));
		if(peliculaOp.isEmpty()) {
			pelicula.setTitle(addpeliculadto.getTitle());
			pelicula.setAnio(addpeliculadto.getAnio());
			
			Optional <Director> directorOp = directorRepository.findDirectorByName(addpeliculadto.getNombreDirector());
			if(directorOp.isEmpty()) {
				result = "El director no existe";
			}else {
				Director director = directorOp.orElseThrow();
				pelicula.setDirector(director);
			}
			peliculasRepositories.save(pelicula);
		}else {
			pelicula = peliculaOp.orElseThrow();
		}
		pelis_usus.setComentario(addpeliculadto.getComentario());
		pelis_usus.setNota(addpeliculadto.getNota());
		pelis_usus.setUsuario(usuario);
		pelis_usus.setPelicula(pelicula);
		pelis_usus.setVista(addpeliculadto.getVista());
		
		pelis_ususRepositories.save(pelis_usus);
		
		return result;
	}

	public Pelicula getPeliculaByTitle(String title) {

		Pelicula pelicula = peliculasRepositories.findByTitle(title);
		if (pelicula != null) {

			return pelicula;
		}

		return pelicula;
	}

	public boolean deletePeliculaByTitle(PeliculaDTO peliculaDTO) {

		String titulo = peliculaDTO.getTitulo_pelicula();

		Pelicula peliculaBd = peliculasRepositories.findByTitle(titulo);

		Usuario usuario = usuarioService.getUsuarioByMail(peliculaDTO.getEmail_usuario());

		if (peliculaBd == null) {
			throw new IllegalStateException("La película con título " + titulo + " no está en la base de datos");

		} else {
			pelis_ususRepositories.findByUsuario(usuario);
			Pelis_usus pelis_usus = pelis_ususRepositories.findByPelicula(peliculaBd);
			pelis_ususRepositories.delete(pelis_usus);
			return true;
		}
	}

	public List<Pelis_usus> getPeliculas(String emailUsuario) {
		Usuario usuario = new Usuario();
		usuario = usuarioService.getUsuarioByMail(emailUsuario);
		List<Pelis_usus> pOptional = pelis_ususRepositories.findByUsuario(usuario);
		return pOptional;
	}

//	public Optional<Pelicula> getPeliculaByTitle(String title) {
//		Optional<Pelicula> pelicula = peliculasRepositories.findPeliculaByTitle(title);
//		return pelicula;
//
//	}

	public Pelis_usus getPeliculaUsuByTitle(Long y) {

		Pelis_usus pelis_usus = pelis_ususRepositories.findById(y).orElseThrow();

		return pelis_usus;
	}
	public Pelicula getPeliculaTitulo(String titulo) {

		Pelicula pelicula = peliculasRepositories.findByTitle(titulo);

		return pelicula;
	}
	 public Pelis_usus getPelicula_Usu(String emailUsuario, String titulo) {
		 Pelis_usus peli_usu = new Pelis_usus();
		 Usuario usuario = new Usuario();
		 usuario = usuarioService.getUsuarioByMail(emailUsuario);
		 
		 Optional<Pelicula>  pelicula = Optional.ofNullable(new Pelicula());
		 pelicula = Optional.ofNullable(getPeliculaByTitle(titulo));
		 
		 if (pelicula.isEmpty()) {
			 
		 }
		 
		 return peli_usu;
	 }

}
