package ccom.filmoteca.hibernate.spring.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import ccom.filmoteca.hibernate.spring.model.Usuario;
import ccom.filmoteca.hibernate.spring.repositories.UsuarioRepositories;

@Service
public class UsuarioService {

    @Autowired
    private final UsuarioRepositories usuarioRepositories;

    @Autowired
    public UsuarioService(UsuarioRepositories usuarioRepositories) {
        this.usuarioRepositories = usuarioRepositories;
    }

    @Bean
    public List<Usuario> getUsuario() {
        return usuarioRepositories.findAll();
    }

    public Optional<Usuario> getUsuarioById(Long usuarioId) {

        Optional<Usuario> usuario = usuarioRepositories.findById(usuarioId);

        return usuario;

    }

    public void addNewUsuario(Usuario usuario) {
        Optional<Usuario> usuarioOptional = usuarioRepositories.findUsuarioByEmail(usuario.getEmail());
        if (usuarioOptional.isPresent()) {
            throw new IllegalStateException("El Eamil ya existe en la base de datos");
        }
        usuarioRepositories.save(usuario);
    }

    public void deleteUsuario(Long usuarioId) {

        boolean exists = usuarioRepositories.existsById(usuarioId);
        if (!exists) {
            throw new IllegalStateException("El usuario con Id " + usuarioId + " no existe.");
        }

        usuarioRepositories.deleteById(usuarioId);
    }

    @Transactional
    public void updateUsuario(Long usuarioId, String name, String password, String email) {

        Usuario usuario = usuarioRepositories.findById(usuarioId).orElseThrow(() -> new IllegalStateException(
                "Usuario con id " + usuarioId + " no existe en la base de datos"));
        if (name != null && name.length() > 0 && !Objects.equals(usuario.getName(), name)) {
            usuario.setName(name);
        }
        if (password != null && password.length() > 0 && !Objects.equals(usuario.getPassword(), password)) {
            usuario.setPassword(password);
        }
        if (email != null && email.length() > 0 && !Objects.equals(usuario.getEmail(), email)) {
            usuario.setEmail(email);
        }
    }
}
