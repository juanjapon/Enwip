package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries( { @NamedQuery(name = "A�os.findAll", query = "SELECT a FROM A�os a") })
@Table(name="A�OS")
public class A�os implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="A�O")
	private int a�o;

	public int getA�o() {
		return a�o;
	}

	public void setA�o(int a�o) {
		this.a�o = a�o;
	}
	
	

}
