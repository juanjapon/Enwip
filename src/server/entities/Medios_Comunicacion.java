package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries( 
		{
		  @NamedQuery(name="Medios_Comunicacion.findByPlanId",query="SELECT d FROM Medios_Comunicacion d WHERE d.finanzas_id= :id"),
		  @NamedQuery(name = "Medios_Comunicacion.findAll", query = "SELECT n FROM Medios_Comunicacion n")
		}	
		
)
@Table(name="MEDIOS_COMUNICACION")
public class Medios_Comunicacion implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="MEDIO_ID")
	private int medio_id;
	@Column(name="FINANZASFINANZAS_ID")
	private int finanzas_id;
	@Column(name="NOMBRE_INVERSION")
	private String nombreInversion;
	@Column(name="VALOR_INVERSION")
	private int valorInversion;
	
	
	public int getMedio_id() {
		return medio_id;
	}
	public void setMedio_id(int medio_id) {
		this.medio_id = medio_id;
	}
	public int getFinanzas_id() {
		return finanzas_id;
	}
	public void setFinanzas_id(int finanzas_id) {
		this.finanzas_id = finanzas_id;
	}
	public String getNombreInversion() {
		return nombreInversion;
	}
	public void setNombreInversion(String nombreInversion) {
		this.nombreInversion = nombreInversion;
	}
	public int getValorInversion() {
		return valorInversion;
	}
	public void setValorInversion(int valorInversion) {
		this.valorInversion = valorInversion;
	}
	
}
