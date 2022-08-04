package server.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries( { @NamedQuery(name = "Estudios.findAll", query = "SELECT e FROM Estudios e"),
	 @NamedQuery(name = "Estudios.findByNombre", query = "SELECT e FROM Estudios e WHERE e.estudio= :nombre"),
	 @NamedQuery(name = "Estudios.findById", query = "SELECT e FROM Estudios e WHERE e.estudio_id= :id")
})
@Table(name="ESTUDIOS")
public class Estudios implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="ESTUDIO_ID")
	private int estudio_id;
	@Column(name="ESTUDIO")
	private String estudio;
	
	public int getEstudio_id() {
		return estudio_id;
	}
	public void setEstudio_id(int estudio_id) {
		this.estudio_id = estudio_id;
	}
	public String getEstudio() {
		return estudio;
	}
	public void setEstudio(String estudio) {
		this.estudio = estudio;
	}
	
	
}
