package ccom.filmoteca.hibernate.spring.dto;

import java.util.Objects;

public class AddPeliculaDTO {

	
	
	private String emailusuario;
	
	private String nombreDirector;
	
	private String title;
	
	private String anio;
	
	private String comentario;
	
	private String nota;
	
	private Boolean vista;

	public AddPeliculaDTO() {
		
	}
	
	public AddPeliculaDTO(String emailusuario, String nombreDirector, String title, String anio, String comentario,
			String nota, Boolean vista) {
		super();
		this.emailusuario = emailusuario;
		this.nombreDirector = nombreDirector;
		this.title = title;
		this.anio = anio;
		this.comentario = comentario;
		this.nota = nota;
		this.vista = vista;
	}

	public Boolean getVista() {
		return vista;
	}

	public void setVista(Boolean vista) {
		this.vista = vista;
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

	@Override
	public int hashCode() {
		return Objects.hash(anio, comentario, emailusuario, nombreDirector, nota, title, vista);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddPeliculaDTO other = (AddPeliculaDTO) obj;
		return Objects.equals(anio, other.anio) && Objects.equals(comentario, other.comentario)
				&& Objects.equals(emailusuario, other.emailusuario)
				&& Objects.equals(nombreDirector, other.nombreDirector) && Objects.equals(nota, other.nota)
				&& Objects.equals(title, other.title) && Objects.equals(vista, other.vista);
	}

	@Override
	public String toString() {
		return "AddPeliculaDTO [emailusuario=" + emailusuario + ", nombreDirector=" + nombreDirector + ", title="
				+ title + ", anio=" + anio + ", comentario=" + comentario + ", nota=" + nota + ", vista=" + vista + "]";
	}

}
