package ccom.filmoteca.hibernate.spring;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ccom.filmoteca.hibernate.spring.model.Pelicula;
import ccom.filmoteca.hibernate.spring.model.Usuario;

@SpringBootApplication
public class FilmotecaProyectoFinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmotecaProyectoFinalApplication.class, args);
		
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ManyToOne");
//		
//		EntityManager em = emf.createEntityManager();
//		
//		em.getTransaction().begin();
//		
//		Usuario usuario = new Usuario("Pedro", "Alcaraz", "12345");
//		
//		em.persist(usuario);
//		
//		Pelicula pelicula = new Pelicula("El Padrino", "Coppola", "1972");
//		pelicula.setUsuario(usuario);
//		
//		em.persist(pelicula);
//		
//		em.flush();
//		
//		em.getTransaction().commit();
//		
//		em.close();
		
	}

}
