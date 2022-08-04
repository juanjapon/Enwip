package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries( { @NamedQuery(name = "Años.findAll", query = "SELECT a FROM Años a") })
@Table(name="AÑOS")
public class Años implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="AÑO")
	private int año;

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}
	
	

}
