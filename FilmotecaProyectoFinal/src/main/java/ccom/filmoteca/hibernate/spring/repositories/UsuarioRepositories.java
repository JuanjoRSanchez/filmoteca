package ccom.filmoteca.hibernate.spring.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ccom.filmoteca.hibernate.spring.model.Usuario;

@Repository
public interface UsuarioRepositories extends JpaRepository<Usuario, Long> {

    @Query
    Optional<Usuario> findUsuarioByEmail(String email);
    
    @Query
    Optional<Usuario>  findByPassword(String password);
    
    
}