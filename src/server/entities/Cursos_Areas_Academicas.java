package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="CURSOS_AREAS_ACADEMICAS")
public class Cursos_Areas_Academicas implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="CURSOSCURSO_ID")
	private int curso_id;
	@Column(name="AREAS_ACADEMICASAREA_ID")
	private int area_id;
	
	public int getCurso_id() {
		return curso_id;
	}
	public void setCurso_id(int curso_id) {
		this.curso_id = curso_id;
	}
	public int getArea_id() {
		return area_id;
	}
	public void setArea_id(int area_id) {
		this.area_id = area_id;
	}
	
	
}
