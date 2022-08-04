package server.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries( 
		{
		  @NamedQuery(name="Cuaderno_Apuntes.findById",query="SELECT d FROM Cuaderno_Apuntes d WHERE d.usuario_id= :id"),
		  @NamedQuery(name = "Cuaderno_Apuntes.findAll", query = "SELECT n FROM Cuaderno_Apuntes n")
		}	
		
)
@IdClass(Cuaderno_ApuntesPK.class)
@Table(name="CUADERNO_APUNTES")
public class Cuaderno_Apuntes implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="CUADERNO_APUNTES_ID")
	private int cuaderno_id;
	@Id
	@Column(name="USUARIO_ID")
	private int usuario_id;
	@Column(name="NOMBRE_PESTAÑA")
	private String nombre;
	@Column(name="CONTENIDO_PESTAÑA")
	private String contenido;
	public int getCuaderno_id() {
		return cuaderno_id;
	}
	public void setCuaderno_id(int cuaderno_id) {
		this.cuaderno_id = cuaderno_id;
	}
	public int getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	

}
