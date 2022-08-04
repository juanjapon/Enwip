package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries( 
		{
		  @NamedQuery(name="Empleados.findByPlanId",query="SELECT d FROM Empleados d WHERE d.finanzas_id= :id"),
		  @NamedQuery(name = "Empleados.findAll", query = "SELECT n FROM Empleados n")
		}	
		
)
@Table(name="EMPLEADOS")
public class Empleados implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="EMPLEADO_ID")
	private int empleado_id;
	@Column(name="CARGO")
	private String cargo;
	@Column(name="SUELDO")
	private int sueldo;
	@Column(name="NUMERO_EMPLEADOS")
	private int numeroEmpleados;
	@Column(name="PRESTACIONES")
	private boolean prestaciones;
	@Column(name="PORC_PRESTACIONES")
	private int porcPrestaciones;
	@Column(name="TOTAL_MENSUAL")
	private int totalMensual;
	@Column(name="FINANZASFINANZAS_ID")
	private int finanzas_id;
	@Column(name="TIPO_DE_CARGOTIPO_CARGO_ID")
	private int tipoCargo_id;

	
	
	public int getEmpleado_id() {
		return empleado_id;
	}
	public void setEmpleado_id(int empleado_id) {
		this.empleado_id = empleado_id;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public int getSueldo() {
		return sueldo;
	}
	public void setSueldo(int sueldo) {
		this.sueldo = sueldo;
	}
	public int getNumeroEmpleados() {
		return numeroEmpleados;
	}
	public void setNumeroEmpleados(int numeroEmpleados) {
		this.numeroEmpleados = numeroEmpleados;
	}
	public boolean getPrestaciones() {
		return prestaciones;
	}
	public void setPrestaciones(boolean prestaciones) {
		this.prestaciones = prestaciones;
	}
	public int getPorcPrestaciones() {
		return porcPrestaciones;
	}
	public void setPorcPrestaciones(int porcPrestaciones) {
		this.porcPrestaciones = porcPrestaciones;
	}
	public int getTotalMensual() {
		return totalMensual;
	}
	public void setTotalMensual(int totalMensual) {
		this.totalMensual = totalMensual;
	}
	public int getFinanzas_id() {
		return finanzas_id;
	}
	public void setFinanzas_id(int finanzas_id) {
		this.finanzas_id = finanzas_id;
	}
	public int getTipoCargo_id() {
		return tipoCargo_id;
	}
	public void setTipoCargo_id(int tipoCargo_id) {
		this.tipoCargo_id = tipoCargo_id;
	}

	
	

}
