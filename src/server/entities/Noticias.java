package server.entities;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@NamedQueries( { @NamedQuery(name = "Noticias.findAll", query = "SELECT n FROM Noticias n ORDER BY n.noticia_id DESC")})
@Table(name="NOTICIAS")
public class Noticias implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="NOTICIA_ID")
	private int noticia_id;
	@Column(name="CONTENIDO")
	private String contenido;
	@Column(name="IMAGEN")
	private String imagen;
	@Column(name="ADMINISTRADOR_ID")
	private int administrador_id;
	@Column(name="FECHA")
	private Date fecha;
	
	
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public int getNoticia_id() {
		return noticia_id;
	}
	public void setNoticia_id(int noticia_id) {
		this.noticia_id = noticia_id;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public int getAdministrador_id() {
		return administrador_id;
	}
	public void setAdministrador_id(int administrador_id) {
		this.administrador_id = administrador_id;
	}
	
	

}
