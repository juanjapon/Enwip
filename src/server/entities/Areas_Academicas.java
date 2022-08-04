package server.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="AREAS_ACADEMICAS")
public class Areas_Academicas implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="AREA_ID")
	private int area_id;
	@Column(name="NOMBRE")
	private String nombre;
	
	
	public int getArea_id() {
		return area_id;
	}
	public void setArea_id(int area_id) {
		this.area_id = area_id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
