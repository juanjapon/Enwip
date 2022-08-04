package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries( { @NamedQuery(name = "Dias.findAll", query = "SELECT d FROM Dias d") })
@Table(name="DIAS")
public class Dias implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="DIA")
	private int dia;

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}
	
	

}
