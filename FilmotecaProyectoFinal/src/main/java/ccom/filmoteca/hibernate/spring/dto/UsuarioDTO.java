package ccom.filmoteca.hibernate.spring.dto;

public class UsuarioDTO {

	private String name;
	
	private String email;
	
	private String token;
	
	
	public UsuarioDTO() {
	}

	public UsuarioDTO(String name, String email, String token) {

		this.name = name;
		this.email = email;
		this.token = token;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "UsuarioDTO [name=" + name + ", email=" + email + ", token=" + token + "]";
	}
	
	
	
	
}
