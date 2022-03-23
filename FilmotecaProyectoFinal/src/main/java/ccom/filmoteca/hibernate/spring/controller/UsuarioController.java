package ccom.filmoteca.hibernate.spring.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ccom.filmoteca.hibernate.spring.model.Usuario;
import ccom.filmoteca.hibernate.spring.service.UsuarioService;

@RestController
@RequestMapping(path = "filmania/v1/")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping(value = "/")
    public List<Usuario> getListUsuario() {

        return usuarioService.getUsuario();
    }

    @RequestMapping(value = "{usuarioId}")
    public Optional<Usuario> getUsusarioById(@PathVariable("usuarioId") Long usuarioId) {
        return usuarioService.getUsuarioById(usuarioId);

    }

    @PostMapping
    public void registerNewUsuario(@RequestBody Usuario usuario) {
        usuarioService.addNewUsuario(usuario);
    }

    @DeleteMapping(path = "{usuarioId}")
    public void deleteUsuario(@PathVariable("usuarioId") Long usuarioId) {
        usuarioService.deleteUsuario(usuarioId);
    }

    @PutMapping(path = "{usuarioId}")
    public void updateUsuario(
            @PathVariable("usuarioId") Long usuarioId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) String email) {

        usuarioService.updateUsuario(usuarioId, name, password, email);

    }
}