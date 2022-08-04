package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="ESTADO_SOLICITUD")
public class Estado_Solicitud implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="ESTADO_ID")
	private int estado_id;
	@Column(name="NOMBRE_ESTADO")
	private String nombre;
	
	public int getEstado_id() {
		return estado_id;
	}
	public void setEstado_id(int estado_id) {
		this.estado_id = estado_id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
