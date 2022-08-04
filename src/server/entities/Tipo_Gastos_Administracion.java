package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries( 
		{
		  @NamedQuery(name = "Tipo_Gastos_Administracion.findAll", query = "SELECT n FROM Tipo_Gastos_Administracion n")
		}	
		
)
@Table(name="TIPO_GASTOS_ADMINISTRACION")
public class Tipo_Gastos_Administracion implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="GASTO_ID")
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
