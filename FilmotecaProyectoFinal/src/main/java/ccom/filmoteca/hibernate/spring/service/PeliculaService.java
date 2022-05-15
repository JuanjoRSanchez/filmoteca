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
import ccom.filmoteca.hibernate.spring.dto.ResponsePeliculaDTO;
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
	private DirectorService directorService;

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
		System.out.println(addpeliculadto);
		String result = "ok";
		Pelis_usus pelis_usus = new Pelis_usus();

		Usuario usuario = usuarioService.getUsuarioByMail(addpeliculadto.getEmailusuario());
		pelis_usus.setUsuario(usuario);

		Pelicula pelicula = new Pelicula();

		// Optional<Pelicula> peliculaOp =
		// getPeliculaByTitle(addpeliculadto.getTitle());
		Optional<Pelicula> peliculaOp = Optional
				.ofNullable(peliculasRepositories.findByTitle(addpeliculadto.getTitle()));
		if (peliculaOp.isEmpty()) {
			pelicula.setTitle(addpeliculadto.getTitle());
			pelicula.setAnio(addpeliculadto.getAnio());
			Optional<Director> directorOp = directorRepository.findDirectorByName(addpeliculadto.getNombreDirector());
			Director director1 = new Director();
			if (directorOp.isEmpty()) {
				result = "ok";
				// System.out.println("El director no existe");
				director1.setName(addpeliculadto.getNombreDirector());
				directorRepository.save(director1);
				pelicula.setDirector(director1);

			} else {
				Director director = directorOp.orElseThrow();
				pelicula.setDirector(director);
			}
			pelicula.setTitle(addpeliculadto.getTitle());
			pelicula.setAnio(addpeliculadto.getAnio());

		} else {
			pelicula = peliculaOp.orElseThrow();

		}
		peliculasRepositories.save(pelicula);
		pelis_usus.setComentario(addpeliculadto.getComentario());
		pelis_usus.setNota(addpeliculadto.getNota());
		pelis_usus.setUsuario(usuario);
		pelis_usus.setPelicula(pelicula);
		pelis_usus.setVista(addpeliculadto.getVista());

		pelis_ususRepositories.save(pelis_usus);

		return result;
	}

	public Optional<Pelicula> getPeliculaByTitle(String title) {

		Optional<Pelicula> pelicula = Optional.of(peliculasRepositories.findByTitle(title));
		if (pelicula != null) {

			return pelicula;
		}

		return pelicula;
	}

	public AddPeliculaDTO getPeliculaUsusById(Long id) {

		Pelis_usus pelis_usus = pelis_ususRepositories.findById(id).orElseThrow();
		AddPeliculaDTO addPeliculaDTO = new AddPeliculaDTO();
		if (pelis_usus != null) {
			Director director = new Director();
			director = directorRepository.findById(pelis_usus.getPelicula().getDirector().getId_director())
					.orElseThrow();

			addPeliculaDTO.setId(pelis_usus.getId());
			addPeliculaDTO.setAnio(pelis_usus.getPelicula().getAnio());
			addPeliculaDTO.setComentario(pelis_usus.getComentario());
			addPeliculaDTO.setNombreDirector(director.getName());
			addPeliculaDTO.setNota(pelis_usus.getNota());
			addPeliculaDTO.setNombreDirector(pelis_usus.getPelicula().getDirector().getName());
			addPeliculaDTO.setTitle(pelis_usus.getPelicula().getTitle());
			addPeliculaDTO.setVista(pelis_usus.isVista());
			return addPeliculaDTO;
		}
		return addPeliculaDTO;
	}

	public String deletePeliculaById(Long id) {
		String result = "";
		Pelicula pelicula = new Pelicula();
		// pelicula = getPeliculaById(id);

		Pelis_usus pelis_usus = new Pelis_usus();
		pelis_usus = getPeliculaUsuById(id);

		pelicula = peliculasRepositories.findById(pelis_usus.getPelicula().getId_pelicula()).orElseThrow();

		if (pelis_usus != null) {

			pelis_ususRepositories.deleteById(id);
			result = "ok";
		}

		return result;
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

	public List<AddPeliculaDTO> getPeliculasAddDTO(String emailUsuario) {
		List<AddPeliculaDTO> listaDTO = new ArrayList<AddPeliculaDTO>();

		Usuario usuario = new Usuario();
		usuario = usuarioService.getUsuarioByMail(emailUsuario);
		List<Pelis_usus> pOptional = pelis_ususRepositories.findByUsuario(usuario);
		if (!pOptional.isEmpty()) {
			for (Pelis_usus pelis_usus : pOptional) {
				Director director = new Director();
				director = directorService.getDirectorById(pelis_usus.getPelicula().getDirector().getId_director())
						.orElseThrow();
				AddPeliculaDTO addPeliculaDTO = new AddPeliculaDTO();
				addPeliculaDTO.setId(pelis_usus.getId());
				addPeliculaDTO.setAnio(pelis_usus.getPelicula().getAnio());
				addPeliculaDTO.setComentario(pelis_usus.getComentario());
				addPeliculaDTO.setNombreDirector(director.getName());
				addPeliculaDTO.setNota(pelis_usus.getNota());
				addPeliculaDTO.setNombreDirector(pelis_usus.getPelicula().getDirector().getName());
				addPeliculaDTO.setTitle(pelis_usus.getPelicula().getTitle());
				addPeliculaDTO.setVista(pelis_usus.isVista());
				listaDTO.add(addPeliculaDTO);
			}
		}
		return listaDTO;
	}
	public Pelis_usus getPeliculaUsuByTitle(Long y) {
		Pelis_usus pelis_usus = pelis_ususRepositories.findById(y).orElseThrow();
		return pelis_usus;
	}
	public Pelis_usus getPeliculaUsuById(Long y) {
		Pelis_usus pelis_usus = pelis_ususRepositories.findById(y).orElseThrow();
		return pelis_usus;
	}

	public List<Pelicula> getPeliculaTitulo(String titulo) {
		List<Pelicula> peliculas = peliculasRepositories.findByTitleStartingWith(titulo);
		return peliculas;
	}

	public List<String> getPeliculaTituloAJAX(String tituloPelicula) {
		List<String> resultList = new ArrayList<String>();
		List<Pelicula> peliculas = new ArrayList<Pelicula>();
		peliculas = (List<Pelicula>) peliculasRepositories.findByTitleStartingWith(tituloPelicula);
		for (Pelicula peli : peliculas) {
			resultList.add(peli.getTitle());
		}
		return resultList;
	}

	public String updatePeliculaUsu(AddPeliculaDTO addPeliculaDTO, Long id) {
		String result = "ok";
		Pelis_usus pelis_usus = pelis_ususRepositories.findById(id).orElseThrow();
		pelis_usus.setComentario(addPeliculaDTO.getComentario());
		pelis_usus.setNota(addPeliculaDTO.getNota());
		pelis_usus.setVista(addPeliculaDTO.getVista());

		Pelicula pelicula = peliculasRepositories.findByTitle(addPeliculaDTO.getTitle());

		pelicula.setAnio(addPeliculaDTO.getAnio());
		pelicula.setTitle(addPeliculaDTO.getTitle());
		
		peliculasRepositories.save(pelicula);
		pelis_usus.setPelicula(pelicula);
		pelis_ususRepositories.save(pelis_usus);

		return result;
	}

}
