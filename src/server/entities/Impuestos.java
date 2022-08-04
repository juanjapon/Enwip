package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries( 
		{
		  @NamedQuery(name="Impuestos.findByPlanId",query="SELECT d FROM Impuestos d WHERE d.finanzas_id= :id"),
		  @NamedQuery(name = "Impuestos.findAll", query = "SELECT n FROM Impuestos n")
		}	
		
)
@Table(name="IMPUESTOS")
public class Impuestos implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="IMPUESTO_ID")
	private int impuesto_id;
	@Column(name="FINANZASFINANZAS_ID")
	private int finanzas_id;
	@Column(name="NOMBRE_IMPUESTO")
	private String nombre;
	@Column(name="PORCENTAJE")
	private int porcentaje;
	
	
	public int getImpuesto_id() {
		return impuesto_id;
	}
	public void setImpuesto_id(int impuesto_id) {
		this.impuesto_id = impuesto_id;
	}
	public int getFinanzas_id() {
		return finanzas_id;
	}
	public void setFinanzas_id(int finanzas_id) {
		this.finanzas_id = finanzas_id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(int porcentaje) {
		this.porcentaje = porcentaje;
	}
	
	

}
