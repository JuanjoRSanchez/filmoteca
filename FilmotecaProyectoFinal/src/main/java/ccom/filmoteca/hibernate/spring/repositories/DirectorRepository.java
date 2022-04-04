package ccom.filmoteca.hibernate.spring.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ccom.filmoteca.hibernate.spring.model.Director;

public interface DirectorRepository extends JpaRepository<Director, Long> {

	@Query
	Optional<Director> findDirectorByName(String name);
	
	static void delete(Optional<Director> director) {
		
		
	}
	
}
