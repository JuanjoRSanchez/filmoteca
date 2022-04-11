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

   
    public List<Usuario> getUsuario() {
        return usuarioRepositories.findAll();
    }

    public Optional<Usuario> getUsuarioById(Long usuarioId) {

        Optional<Usuario> usuario = usuarioRepositories.findById(usuarioId);

        return usuario;

    }
//    public Boolean getUsuarioLog(Usuario usuario) {
//
//    	Optional<Usuario> usuarioOp = usuarioRepositories.findUsuarioByEmail( usuario.getEmail()) ;
//    	
//    	Optional<Usuario> usuarioOp1 = usuarioRepositories.findByPassword( usuario.getPassword()) ;
//    	    	 	
//    	if(!usuarioOp.isEmpty() && !usuarioOp1.isEmpty()) {
//    		if(usuarioOp.equals(usuarioOp1)) 
//        	{
//    			return true;
//    		
//        	}	
//    	}
//        return false;
//
//    }
    
    public Boolean getUsuarioLog(String email, String password) {

    	Optional<Usuario> usuarioOp = usuarioRepositories.findUsuarioByEmail( email) ;
    	
    	Optional<Usuario> usuarioOp1 = usuarioRepositories.findByPassword( password) ;
    	    	 	
    	if(!usuarioOp.isEmpty() && !usuarioOp1.isEmpty()) {
    		if(usuarioOp.equals(usuarioOp1)) 
        	{
    			return true;
    		
        	}	
    	}
        return false;

    }
    
    public Optional<Usuario> getUsuarioByIdSeguro(Long usuarioId) {

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

    public Usuario updateUsuario(Usuario usuario) {

        Usuario usuarioBd = usuarioRepositories.findById(usuario.getId_usuario()).orElseThrow(() -> new IllegalStateException(
                "Usuario con id " + usuario.getId_usuario() + " no existe en la base de datos"));
        if (usuario.getName() != null && usuario.getName().length() > 0 && !usuarioBd.getName().equals(usuario.getName())) {
        	usuarioBd.setName(usuario.getName());
        }
        if (usuario.getPassword() != null && usuario.getPassword().length() > 0 && !usuarioBd.getPassword().equals(usuario.getPassword())) {
        	usuarioBd.setPassword(usuario.getPassword());
        }
        if (usuario.getEmail() != null && usuario.getEmail().length() > 0 && !usuarioBd.getEmail().equals(usuario.getEmail())) {
        	usuarioBd.setEmail(usuario.getEmail());
        }

        return usuarioRepositories.save(usuarioBd);
        
    }
}
