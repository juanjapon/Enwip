package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries( 
		{
		  @NamedQuery(name="Costos_Fijos.findByPlanId",query="SELECT d FROM Costos_Fijos d WHERE d.finanzas_id= :id"),
		  @NamedQuery(name = "Costos_Fijos.findAll", query = "SELECT n FROM Costos_Fijos n")
		}	
		
)
@Table(name="COSTOS_FIJOS")
public class Costos_Fijos implements Serializable{
	private static final long  serialVersionUID=1L;

	@Id
	@Column(name="COSTO_FIJO_ID")
	private int costoFijo_id;
	@Column(name="FINANZASFINANZAS_ID")
	private int finanzas_id;
	@Column(name="TIPO_COSTOS_FIJOSTIPO_ID")
	private int tipoCostos_id;
	@Column(name="COSTO_MENSUAL")
	private int costoMensual;

	
	
	public int getCostoFijo_id() {
		return costoFijo_id;
	}
	public void setCostoFijo_id(int costoFijo_id) {
		this.costoFijo_id = costoFijo_id;
	}
	public int getFinanzas_id() {
		return finanzas_id;
	}
	public void setFinanzas_id(int finanzas_id) {
		this.finanzas_id = finanzas_id;
	}
	public int getTipoCostos_id() {
		return tipoCostos_id;
	}
	public void setTipoCostos_id(int tipoCostos_id) {
		this.tipoCostos_id = tipoCostos_id;
	}
	public int getCostoMensual() {
		return costoMensual;
	}
	public void setCostoMensual(int costoMensual) {
		this.costoMensual = costoMensual;
	}

	
	
	

}
