package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries( { @NamedQuery(name = "Genero.findAll", query = "SELECT g FROM Genero g") })
@Table(name="GENERO")
public class Genero implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="GENERO_ID")
	private int genero_id;
	@Column(name="GENERO")
	private String genero;
	
	
	public int getGenero_id() {
		return genero_id;
	}
	public void setGenero_id(int genero_id) {
		this.genero_id = genero_id;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	

}
