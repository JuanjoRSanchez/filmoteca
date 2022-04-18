package ccom.filmoteca.hibernate.spring.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ccom.filmoteca.hibernate.spring.model.Director;
import ccom.filmoteca.hibernate.spring.model.Usuario;
import ccom.filmoteca.hibernate.spring.service.DirectorService;

@RestController
@RequestMapping(path = "filmania/v1/director")
public class DirectorController {

	private final DirectorService directorService;
	
	@Autowired
	public DirectorController(DirectorService directorService) {
		this.directorService = directorService;
	}
	
	@GetMapping(value = "/")
    public List<Director> getListDirector() {
        return directorService.getDirector();
    }

	@RequestMapping(value = "AJAX/{letterDirector}")
    public Director getDirectorAJAX(@PathVariable("letterDirector") String letterDirector) {
		  	
		return directorService.getDirectorAJAX(letterDirector);
    }
	
    @RequestMapping(value = "{directorID}")
    public Optional<Director> getDirectorById(@PathVariable("directorID") Long directorID) {
        return directorService.getDirectorById(directorID);
    }
    
   

    @PostMapping
    public void registerNewDirector(@RequestBody Director director) {
    	directorService.addNewDirector(director);
    }

    @DeleteMapping(path = "{directorId}")
    public void deleteDirectorById(@PathVariable("directorId") Long directorId) {
    	directorService.deleteDirectorById(directorId);
    }

    @PutMapping()
    public Director updateDirector(@RequestBody Director director) {
        return directorService.updateDirector(director);
    }

	
}
