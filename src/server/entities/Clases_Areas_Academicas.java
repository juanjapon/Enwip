package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="CLASES_AREAS_ACADEMICAS")
public class Clases_Areas_Academicas implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="CLASESCLASE_ID")
	private int clase_id;
	@Column(name="AREAS_ACADEMICASAREA_ID")
	private int area_id;
	
	public int getClase_id() {
		return clase_id;
	}
	public void setClase_id(int clase_id) {
		this.clase_id = clase_id;
	}
	public int getArea_id() {
		return area_id;
	}
	public void setArea_id(int area_id) {
		this.area_id = area_id;
	}
	
	

}
