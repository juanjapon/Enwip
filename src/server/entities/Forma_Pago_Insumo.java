package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQuery(name = "Forma_Pago_Insumo.findAll", query = "SELECT n FROM Forma_Pago_Insumo n")
@Table(name="FORMA_PAGO_INSUMO")
public class Forma_Pago_Insumo implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="FORMA_ID")
	private int forma_id;
	@Column(name="DESCRIPCION")
	private String descripcion;
	
	
	public int getForma_id() {
		return forma_id;
	}
	public void setForma_id(int forma_id) {
		this.forma_id = forma_id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}
