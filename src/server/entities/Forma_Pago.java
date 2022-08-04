package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQuery(name = "Forma_Pago.findAll", query = "SELECT n FROM Forma_Pago n")
@Table(name="FORMA_PAGO")
public class Forma_Pago implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="FORMA_ID")
	private int forma_id;
	@Column(name="NOMBRE_FORMA")
	private String nombre;
	
	public int getForma_id() {
		return forma_id;
	}
	public void setForma_id(int forma_id) {
		this.forma_id = forma_id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
