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
@RequestMapping(path = "filmania/v1/usuario")
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

//    @RequestMapping(value ="/log" , params={"email","password"} )
//    public Boolean getUsusarioLog(@RequestParam String email, String password) {
//        return usuarioService.getUsuarioLog(email, password);
//    }
    
    @RequestMapping(value ="/login" , params={"email","password"} )
    public Usuario getUsusarioLogin(@RequestParam String email, String password) {
        return usuarioService.getUsuarioLogin(email, password).orElseThrow();
    }
    
    @PostMapping
    public void registerNewUsuario(@RequestBody Usuario usuario) {
    	
        usuarioService.addNewUsuario(usuario);
    }

    @DeleteMapping(path = "{usuarioId}")
    public void deleteUsuario(@PathVariable("usuarioId") Long usuarioId) {
        usuarioService.deleteUsuario(usuarioId);
    }

    @PutMapping()
    public Usuario updateUsuario(@RequestBody Usuario usuario) {
        return usuarioService.updateUsuario(usuario);
    }
}