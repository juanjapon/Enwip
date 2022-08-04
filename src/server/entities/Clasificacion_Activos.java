package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQuery(name = "Clasificacion_Activos.findAll", query = "SELECT n FROM Clasificacion_Activos n")
@Table(name="CLASIFICACION_ACTIVOS")
public class Clasificacion_Activos implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="ACTIVO_ID")
	private int activo_id;
	@Column(name="NOMBRE_ACTIVO")
	private String nombre;
	
	public int getActivo_id() {
		return activo_id;
	}
	public void setActivo_id(int activo_id) {
		this.activo_id = activo_id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
