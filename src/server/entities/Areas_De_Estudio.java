package server.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries( { @NamedQuery(name = "Areas_De_Estudio.findAll", query = "SELECT a FROM Areas_De_Estudio a"),
	@NamedQuery(name = "Areas_De_Estudio.findByNombre", query = "SELECT a FROM Areas_De_Estudio a WHERE a.nombre= :nombre")})
@Table(name="AREAS_DE_ESTUDIO")
public class Areas_De_Estudio implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="AREA_ESTUDIO_ID")
	private int areaEstudio_id;
	@Column(name="NOMBRE_AREA_ESTUDIO")
	private String nombre;
	
	
	public int getAreaEstudio_id() {
		return areaEstudio_id;
	}
	public void setAreaEstudio_id(int areaEstudio_id) {
		this.areaEstudio_id = areaEstudio_id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
