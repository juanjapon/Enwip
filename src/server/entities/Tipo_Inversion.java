package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries( { @NamedQuery(name = "Tipo_Inversion.findAll", query = "SELECT t FROM Tipo_Inversion t"),
	@NamedQuery(name = "Tipo_Inversion.findByNombre", query = "SELECT t FROM Tipo_Inversion t WHERE t.nombre= :nombre")})
@Table(name="TIPO_INVERSION")
public class Tipo_Inversion implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="TIPO_ID")
	private int tipo_id;
	@Column(name="NOMBRE")
	private String nombre;
	
	public int getTipo_id() {
		return tipo_id;
	}
	public void setTipo_id(int tipo_id) {
		this.tipo_id = tipo_id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

}
