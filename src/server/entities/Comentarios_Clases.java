package server.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries( 
		{ @NamedQuery(name = "Comentarios_Clases.findByClase", query = "SELECT c FROM Comentarios_Clases c WHERE c.clase_id= :id ORDER BY c.comentario_id ASC")}	

)
@Table(name="COMENTARIOS_CLASES")
public class Comentarios_Clases implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="COMENTARIO_ID")
	private int comentario_id;
	@Column(name="CONTENIDO")
	private String contenida;
	@Column(name="USUARIO_ID")
	private int usuario_id;
	@Column(name="CLASE_ID")
	private int clase_id;
	@Column(name="FECHA")
	private Date fecha;
	
	
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public int getComentario_id() {
		return comentario_id;
	}
	public void setComentario_id(int comentario_id) {
		this.comentario_id = comentario_id;
	}
	public String getContenida() {
		return contenida;
	}
	public void setContenida(String contenida) {
		this.contenida = contenida;
	}
	public int getClase_id() {
		return clase_id;
	}
	public void setClase_id(int clase_id) {
		this.clase_id = clase_id;
	}
	public int getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
	

}
