package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="EXPERIENCIA_LABORAL")
public class Experiencia_Laboral implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="EXPERIENCIA_ID")
	private int experiencia_id;
	@Column(name="EXPERIENCIA_NOMBRE")
	private String nombre;
	
	public int getExperiencia_id() {
		return experiencia_id;
	}
	public void setExperiencia_id(int experiencia_id) {
		this.experiencia_id = experiencia_id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
