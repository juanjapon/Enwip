package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries( 
		{
		  @NamedQuery(name="Inversion_Activo_Fijos.findByPlanId",query="SELECT d FROM Inversion_Activo_Fijos d WHERE d.finanzas_id= :id"),
		  @NamedQuery(name = "Inversion_Activo_Fijos.findAll", query = "SELECT n FROM Inversion_Activo_Fijos n")
		}	
		
)
@Table(name="INVERSION_ACTIVO_FIJOS")
public class Inversion_Activo_Fijos implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="INVERSION_ACTIVO_FIJO_ID")
	private int inversionActivoFijo_id;
	@Column(name="FINANZASFINANZAS_ID")
	private int finanzas_id;
	@Column(name="DESCRIPCION")
	private String descripcion;
	@Column(name="CANTIDAD")
	private int cantidad;
	@Column(name="COSTO_UNITARIO")
	private int costoUnitario;
	@Column(name="CLASIFICACION_ACTIVOSACTIVO_ID")
	private int clasificacionActivo_id;
	@Column(name="MOMENTO_INVERSION")
	private int momentoInversion;
	@Column(name="FORMA_PAGOFORMA_ID")
	private int formaPago_id;
	
	public int getInversionActivoFijo_id() {
		return inversionActivoFijo_id;
	}
	public void setInversionActivoFijo_id(int inversionActivoFijo_id) {
		this.inversionActivoFijo_id = inversionActivoFijo_id;
	}
	public int getFinanzas_id() {
		return finanzas_id;
	}
	public void setFinanzas_id(int finanzas_id) {
		this.finanzas_id = finanzas_id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getCostoUnitario() {
		return costoUnitario;
	}
	public void setCostoUnitario(int costoUnitario) {
		this.costoUnitario = costoUnitario;
	}
	public int getClasificacionActivo_id() {
		return clasificacionActivo_id;
	}
	public void setClasificacionActivo_id(int clasificacionActivo_id) {
		this.clasificacionActivo_id = clasificacionActivo_id;
	}
	public int getMomentoInversion() {
		return momentoInversion;
	}
	public void setMomentoInversion(int momentoInversion) {
		this.momentoInversion = momentoInversion;
	}
	public int getFormaPago_id() {
		return formaPago_id;
	}
	public void setFormaPago_id(int formaPago_id) {
		this.formaPago_id = formaPago_id;
	}
	
	

}
