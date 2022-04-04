package ccom.filmoteca.hibernate.spring.dto;

public class AddPeliculaUsuarioDirector {

	private Long id_usuario;
	
	private Long id_director;
	
	private String title;
	
	private String anio;

	public AddPeliculaUsuarioDirector() {
		
	}

	public AddPeliculaUsuarioDirector(Long id_usuario, Long id_director, String title, String anio) {
		this.id_usuario = id_usuario;
		this.id_director = id_director;
		this.title = title;
		this.anio = anio;
	}

	public Long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public Long getId_director() {
		return id_director;
	}

	public void setId_director(Long id_director) {
		this.id_director = id_director;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}
	
	
	
	
}
