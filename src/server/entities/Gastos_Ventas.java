package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries( 
		{
		  @NamedQuery(name="Gastos_Ventas.findByPlanId",query="SELECT d FROM Gastos_Ventas d WHERE d.finanzas_id= :id"),
		  @NamedQuery(name = "Gastos_Ventas.findAll", query = "SELECT n FROM Gastos_Ventas n")
		}	
		
)
@Table(name="GASTOS_VENTAS")
public class Gastos_Ventas implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="GASTO_VENTA_ID")
	private int gastos_id;
	@Column(name="FINANZASFINANZAS_ID")
	private int finanzas_id;
	@Column(name="TIPO_GASTO_VENTASTIPO_GASTO_ID")
	private int tipoGasto_id;
	@Column(name="COSTO_MENSUAL")
	private int costoMensual;

	
	
	public int getGastos_id() {
		return gastos_id;
	}
	public void setGastos_id(int gastos_id) {
		this.gastos_id = gastos_id;
	}
	public int getFinanzas_id() {
		return finanzas_id;
	}
	public void setFinanzas_id(int finanzas_id) {
		this.finanzas_id = finanzas_id;
	}
	public int getTipoGasto_id() {
		return tipoGasto_id;
	}
	public void setTipoGasto_id(int tipoGasto_id) {
		this.tipoGasto_id = tipoGasto_id;
	}
	public int getCostoMensual() {
		return costoMensual;
	}
	public void setCostoMensual(int costoMensual) {
		this.costoMensual = costoMensual;
	}

	
	

}
