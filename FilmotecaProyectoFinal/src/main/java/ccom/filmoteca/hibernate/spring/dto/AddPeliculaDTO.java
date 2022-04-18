package ccom.filmoteca.hibernate.spring.dto;

public class AddPeliculaDTO {

	
	private String emailusuario;
	
	private String nombreDirector;
	
	private String title;
	
	private String anio;
	
	private String comentario;
	
	private String nota;

	public AddPeliculaDTO() {
		
	}

	

	public AddPeliculaDTO(String emailusuario, String nombreDirector, String title, String anio, String comentario,
			String nota) {
		super();
		this.emailusuario = emailusuario;
		this.nombreDirector = nombreDirector;
		this.title = title;
		this.anio = anio;
		this.comentario = comentario;
		this.nota = nota;
	}



	public String getEmailusuario() {
		return emailusuario;
	}



	public void setEmailusuario(String emailusuario) {
		this.emailusuario = emailusuario;
	}



	public String getNombreDirector() {
		return nombreDirector;
	}

	public void setNombreDirector(String nombreDirector) {
		this.nombreDirector = nombreDirector;
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

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	
	
	
	
}
