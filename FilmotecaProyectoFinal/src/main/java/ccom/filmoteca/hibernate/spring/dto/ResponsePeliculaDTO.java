package ccom.filmoteca.hibernate.spring.dto;

public class ResponsePeliculaDTO {
	
	private String id_pelicula;
	
	private String title;
	
	private String anio;
	
	private String nameDirector;
	
	private String nota;
	
	private String comentario;
	
	private Boolean vista;
	
	

	public ResponsePeliculaDTO() {
		super();
	}



	public ResponsePeliculaDTO(String id_pelicula, String title, String anio, String nameDirector, String nota,
			String comentario, Boolean vista) {
		super();
		this.id_pelicula = id_pelicula;
		this.title = title;
		this.anio = anio;
		this.nameDirector = nameDirector;
		this.nota = nota;
		this.comentario = comentario;
		this.vista = vista;
	}



	public String getId_pelicula() {
		return id_pelicula;
	}



	public void setId_pelicula(String id_pelicula) {
		this.id_pelicula = id_pelicula;
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



	public String getNameDirector() {
		return nameDirector;
	}



	public void setNameDirector(String nameDirector) {
		this.nameDirector = nameDirector;
	}



	public String getNota() {
		return nota;
	}



	public void setNota(String nota) {
		this.nota = nota;
	}



	public String getComentario() {
		return comentario;
	}



	public void setComentario(String comentario) {
		this.comentario = comentario;
	}







	public Boolean getVista() {
		return vista;
	}



	public void setVista(Boolean vista) {
		this.vista = vista;
	}



	@Override
	public String toString() {
		return "ResponsePeliculaDTO [id_pelicula=" + id_pelicula + ", title=" + title + ", anio=" + anio
				+ ", nameDirector=" + nameDirector + ", nota=" + nota + ", comentario=" + comentario + ", vista="
				+ vista + "]";
	}
	
	

}
