package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries( { @NamedQuery(name = "Meses.findAll", query = "SELECT m FROM Meses m") })
@Table(name="MESES")
public class Meses implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="MES")
	private String mes;

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}
	
	

}
