package server.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries( 
		{
		  @NamedQuery(name="Gastos_Fijos_Administracion.findByPlanId",query="SELECT d FROM Gastos_Fijos_Administracion d WHERE d.finanzas_id= :id"),
		  @NamedQuery(name = "Gastos_Fijos_Administracion.findAll", query = "SELECT n FROM Gastos_Fijos_Administracion n")
		}	
		
)
@Table(name="GASTOS_FIJOS_ADMINISTRACION")
public class Gastos_Fijos_Administracion implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="GASTO_FIJO_ID")
	private int gastoFijo_id;
	@Column(name="FINANZASFINANZAS_ID")
	private int finanzas_id;
	@Column(name="TIPO_GASTOS_ADMINISTRACIONGASTO_ID")
	private int gasto_id;
	@Column(name="COSTO_MENSUAL")
	private int costoMensual;

	
	
	public int getGastoFijo_id() {
		return gastoFijo_id;
	}
	public void setGastoFijo_id(int gastoFijo_id) {
		this.gastoFijo_id = gastoFijo_id;
	}
	public int getFinanzas_id() {
		return finanzas_id;
	}
	public void setFinanzas_id(int finanzas_id) {
		this.finanzas_id = finanzas_id;
	}
	public int getGasto_id() {
		return gasto_id;
	}
	public void setGasto_id(int gasto_id) {
		this.gasto_id = gasto_id;
	}
	public int getCostoMensual() {
		return costoMensual;
	}
	public void setCostoMensual(int costoMensual) {
		this.costoMensual = costoMensual;
	}

	
	

}
