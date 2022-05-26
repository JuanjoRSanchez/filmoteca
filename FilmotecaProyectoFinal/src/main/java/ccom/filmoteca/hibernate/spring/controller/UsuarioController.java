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
    
//    private final UsuarioRepositories usuarioRepositories;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

//    @Autowired
//    public UsuarioController(UsuarioService usuarioService, UsuarioRepositories usuarioRepositories) {
//		this.usuarioService = usuarioService;
//		this.usuarioRepositories = usuarioRepositories;
//	}


	@GetMapping(value = "/")
    public List<Usuario> getListUsuario() {
        return usuarioService.getUsuario();
    }

    @RequestMapping(value = "{usuarioId}")
    public Optional<Usuario> getUsusarioById(@PathVariable("usuarioId") Long usuarioId) {
        return usuarioService.getUsuarioById(usuarioId);
    }
    
    @RequestMapping(value = "email/{emailUsuario}")
    public Usuario getUsusarioByEmail(@PathVariable("emailUsuario") String emailUsuario) {
        return usuarioService.getUsuarioByMail(emailUsuario);
    }
    
    
    @RequestMapping(value ="/login" , params={"email","password"} )
    public int getUsusarioLogin(@RequestParam String email, String password) {
        return usuarioService.getUsuarioLog(email, password);
    }
    
//    @PostMapping(value ="/loginJWT" , params={"email","password"} )
//    public UsuarioDTO getUsusarioLoginJWT(@RequestParam String email, String password) {
//    	UsuarioDTO usuarioDTO = new UsuarioDTO();
//    	Usuario usuario = new Usuario();
//    	usuario = usuarioService.getUsuarioLoginJWT(email, password);
//    	if(usuario.getEmail() != "") {
//    		String token = getJWTToken(email);
//        	usuarioDTO.setEmail(usuario.getEmail());
//        	usuarioDTO.setName(usuario.getName());
//        	usuarioDTO.setToken(token);		
//    	}
//    	return usuarioDTO;
//    }

//    
//    private String getJWTToken(String username) {
//		String secretKey = "mySecretKey";
//		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
//				.commaSeparatedStringToAuthorityList("ROLE_USER");
//		
//		String token = Jwts
//				.builder()
//				.setId("softtekJWT")
//				.setSubject(username)
//				.claim("authorities",
//						grantedAuthorities.stream()
//								.map(GrantedAuthority::getAuthority)
//								.collect(Collectors.toList()))
//				.setIssuedAt(new Date(System.currentTimeMillis()))
//				.setExpiration(new Date(System.currentTimeMillis() + 600000))
//				.signWith(SignatureAlgorithm.HS512,
//						secretKey.getBytes()).compact();
//
//		return "Bearer " + token;
//	}
    @PostMapping
    public int registerNewUsuario(@RequestBody Usuario usuario) {
         return usuarioService.addNewUsuario(usuario);
    }

    @DeleteMapping(value = "{usuarioId}")
    public void deleteUsuario(@PathVariable("usuarioId") Long usuarioId) {
        usuarioService.deleteUsuario(usuarioId);
    }

    @PutMapping
    public Usuario updateUsuario(@RequestBody Usuario usuario) {
        return usuarioService.updateUsuario(usuario);
    }
}