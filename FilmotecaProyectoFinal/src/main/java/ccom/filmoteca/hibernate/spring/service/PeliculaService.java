package ccom.filmoteca.hibernate.spring.service;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ccom.filmoteca.hibernate.spring.dto.AddPeliculaUsuarioDirector;
import ccom.filmoteca.hibernate.spring.model.Director;
import ccom.filmoteca.hibernate.spring.model.Pelicula;
import ccom.filmoteca.hibernate.spring.model.Usuario;
import ccom.filmoteca.hibernate.spring.repositories.DirectorRepository;
import ccom.filmoteca.hibernate.spring.repositories.PeliculasRepositories;
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
	public void PeluculaServide(PeliculasRepositories peliculasRepositories) {
		this.peliculasRepositories = peliculasRepositories;
	}

	
	public List<Pelicula> getPelicula() {
		return peliculasRepositories.findAll();
	}

	public Optional<Pelicula> getPeliculaByTitle(String title) {
		Optional<Pelicula> pelicula = peliculasRepositories.findPeliculaByTitle(title);
		return pelicula;

	}
	
	public Pelicula getPeliculaById(Long id) {
	
		Optional <Pelicula> pelicula = peliculasRepositories.findById(id);	
		if (!pelicula.isPresent()) {
			throw new IllegalStateException("La pelicula no existe en la base de datos");
		}
		
		return pelicula.get();
	}

	public Pelicula addNewPelicula(Pelicula pelicula) {
		
		Optional<Pelicula> peliculaOptional = peliculasRepositories.findPeliculaByTitle(pelicula.getTitle());
		if (peliculaOptional.isPresent()) {
			throw new IllegalStateException("La pelicula ya existe en la base de datos");
		}
		
		return peliculasRepositories.save(pelicula);
	}
	
	public void addNewPeliculaUsuarioDirector(AddPeliculaUsuarioDirector addPeliculaUsuarioDirector) {
		Usuario usuario = new Usuario();
		usuario.setId_usuario(addPeliculaUsuarioDirector.getId_usuario());
		Director director = new Director(); 
		director.setId_director(addPeliculaUsuarioDirector.getId_director());
		Pelicula pelicula = new Pelicula();
		
		Optional<Pelicula> peliculaOptional = peliculasRepositories.findPeliculaByTitle(addPeliculaUsuarioDirector.getTitle());
		if (peliculaOptional.isPresent()) {
			throw new IllegalStateException("La pelicula ya existe en la base de datos");

		}
		else if(usuarioRepositories.findById(addPeliculaUsuarioDirector.getId_usuario()) != null && directorRepository.findById(addPeliculaUsuarioDirector.getId_director()) != null) {
			pelicula.getUsuarios().add(usuario);
			pelicula.setDirector(director);
			
			pelicula.setAnio(addPeliculaUsuarioDirector.getAnio());
			pelicula.setTitle(addPeliculaUsuarioDirector.getTitle());
			
			peliculasRepositories.save(pelicula);
		}
		
	}
	
	public void deletePelicula(Long idPelicula) {
		Optional<Pelicula> peliculaBd = peliculasRepositories.findById(idPelicula);
		if (peliculaBd.isEmpty()) {
			throw new IllegalStateException("La película con id " + idPelicula + " no está en la base de datos");
		}			
		
		
		peliculasRepositories.delete(peliculaBd.get());
	}

	@Transactional
	public void updatePelicula(Pelicula peliculaInput) {
		Pelicula pelicula = getPeliculaByTitle(peliculaInput.getTitle()).orElseThrow(
				() -> new IllegalStateException("Usuario con id " + peliculaInput.getTitle() + " no existe en la base de datos"));
		if (peliculaInput.getTitle() != null && peliculaInput.getTitle().length() > 0 && !Objects.equals(pelicula.getTitle(), peliculaInput.getTitle())) {
			pelicula.setTitle(peliculaInput.getTitle());
		}
		if (peliculaInput.getDirector().getName() != null && peliculaInput.getDirector().getName().length() > 0
				&& !Objects.equals(pelicula.getDirector().getName(), peliculaInput.getDirector().getName())) {
			pelicula.setDirector(null);
		}
		if (peliculaInput.getAnio() != null && peliculaInput.getAnio().length() > 0 && !Objects.equals(pelicula.getAnio(), peliculaInput.getAnio())) {
			pelicula.setAnio(peliculaInput.getAnio());
		}
	}
	
	

}
