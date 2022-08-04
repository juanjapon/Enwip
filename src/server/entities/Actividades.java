package server.entities;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name="ACTIVIDADES")
public class Actividades implements Serializable{
	private static final long serialVersionUID=1L;
	
	@Id
	@Column(name="USUARIOSUSUARIO_ID")
	private int usuario_Id;
	@Column(name="CONTENIDO")
	private String contenido;
	@Column(name="FECHA")
	private java.util.Date fecha;
	
	public int getUsuario_Id() {
		return usuario_Id;
	}
	public void setUsuario_Id(int usuario_Id) {
		this.usuario_Id = usuario_Id;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public java.util.Date getFecha() {
		return fecha;
	}
	public void setFecha(java.util.Date fecha) {
		this.fecha = fecha;
	}
}
