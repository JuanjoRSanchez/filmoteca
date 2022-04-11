package ccom.filmoteca.hibernate.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ccom.filmoteca.hibernate.spring.model.Director;
import ccom.filmoteca.hibernate.spring.model.Usuario;
import ccom.filmoteca.hibernate.spring.repositories.DirectorRepository;

@Service
public class DirectorService {

	@Autowired
	private final DirectorRepository directorRepository;
	
	@Autowired
	public DirectorService (DirectorRepository directorRepository) {
		this.directorRepository = directorRepository;
	}
	
	public List<Director> getDirector(){	
		return directorRepository.findAll();
	}
	
	public Optional<Director> getDirectorById(Long IdDirector){
		
		Optional<Director> director = directorRepository.findById(IdDirector);
		
		return director;
	}
	

	
	
	public Director addNewDirector(Director director) {		
		 Optional<Director> directorOptional = directorRepository.findDirectorByName(director.getName());
	        if (directorOptional.isPresent()) {
	            throw new IllegalStateException("El director ya existe en la base de datos");
	        }
	       return directorRepository.save(director);
	}

	public void deleteDirectorById(Long idDirector) {
		boolean exists = directorRepository.existsById(idDirector);
        if (!exists) {
            throw new IllegalStateException("El director con Id " + idDirector + " no existe.");
        }
        directorRepository.deleteById(idDirector);	
	}
	
	public Director updateDirector(Director director) {
		Director directorBd = directorRepository.findById(director.getId_director()).orElseThrow(() -> new IllegalStateException(
                "Director con id " + director.getId_director() + " no existe en la base de datos"));
        if (director.getName() != null && director.getName().length() > 0 && !directorBd.getName().equals(director.getName())) {
        	directorBd.setName(director.getName());
        }
        if (director.getLastname() != null && director.getLastname().length() > 0 && !directorBd.getLastname().equals(director.getLastname())) {
        	directorBd.setLastname(director.getLastname());
        }
        if (director.getAnio() != null && director.getAnio().length() > 0 && !directorBd.getAnio().equals(director.getAnio())) {
        	directorBd.setAnio(director.getAnio());
        }

        return directorRepository.save(director);
        
    }

}
