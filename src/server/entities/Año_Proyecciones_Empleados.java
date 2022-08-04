package server.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries( 
		{
		  @NamedQuery(name="Año_Proyecciones_Empleados.findByCostoId",query="SELECT d FROM Año_Proyecciones_Empleados d WHERE d.empleadoId= :id"),
		  @NamedQuery(name = "Año_Proyecciones_Empleados.findAll", query = "SELECT n FROM Año_Proyecciones_Empleados n")
		}	
		
)
@IdClass(Año_Proyecciones_EmpleadosPK.class)
@Table(name="AÑO_PROYECCIONES_EMPLEADOS")
public class Año_Proyecciones_Empleados implements Serializable {
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="AÑO_ID")
	private int añoId;
	@Id
	@Column(name="EMPLEADOSEMPLEADO_ID")
	private int empleadoId;
	@Column(name="MES_1")
	private boolean mes1;
	@Column(name="MES_2")
	private boolean mes2;
	@Column(name="MES_3")
	private boolean mes3;
	@Column(name="MES_4")
	private boolean mes4;
	@Column(name="MES_5")
	private boolean mes5;
	@Column(name="MES_6")
	private boolean mes6;
	@Column(name="MES_7")
	private boolean mes7;
	@Column(name="MES_8")
	private boolean mes8;
	@Column(name="MES_9")
	private boolean mes9;
	@Column(name="MES_10")
	private boolean mes10;
	@Column(name="MES_11")
	private boolean mes11;
	@Column(name="MES_12")
	private boolean mes12;
	@Column(name="PORCENTAJE_INCREMENTO")
	private int porcentajeIncremento;
	
	
	public int getPorcentajeIncremento() {
		return porcentajeIncremento;
	}
	public void setPorcentajeIncremento(int porcentajeIncremento) {
		this.porcentajeIncremento = porcentajeIncremento;
	}
	public int getAñoId() {
		return añoId;
	}
	public void setAñoId(int añoId) {
		this.añoId = añoId;
	}
	public int getEmpleadoId() {
		return empleadoId;
	}
	public void setEmpleadoId(int empleadoId) {
		this.empleadoId = empleadoId;
	}
	public boolean isMes1() {
		return mes1;
	}
	public void setMes1(boolean mes1) {
		this.mes1 = mes1;
	}
	public boolean isMes2() {
		return mes2;
	}
	public void setMes2(boolean mes2) {
		this.mes2 = mes2;
	}
	public boolean isMes3() {
		return mes3;
	}
	public void setMes3(boolean mes3) {
		this.mes3 = mes3;
	}
	public boolean isMes4() {
		return mes4;
	}
	public void setMes4(boolean mes4) {
		this.mes4 = mes4;
	}
	public boolean isMes5() {
		return mes5;
	}
	public void setMes5(boolean mes5) {
		this.mes5 = mes5;
	}
	public boolean isMes6() {
		return mes6;
	}
	public void setMes6(boolean mes6) {
		this.mes6 = mes6;
	}
	public boolean isMes7() {
		return mes7;
	}
	public void setMes7(boolean mes7) {
		this.mes7 = mes7;
	}
	public boolean isMes8() {
		return mes8;
	}
	public void setMes8(boolean mes8) {
		this.mes8 = mes8;
	}
	public boolean isMes9() {
		return mes9;
	}
	public void setMes9(boolean mes9) {
		this.mes9 = mes9;
	}
	public boolean isMes10() {
		return mes10;
	}
	public void setMes10(boolean mes10) {
		this.mes10 = mes10;
	}
	public boolean isMes11() {
		return mes11;
	}
	public void setMes11(boolean mes11) {
		this.mes11 = mes11;
	}
	public boolean isMes12() {
		return mes12;
	}
	public void setMes12(boolean mes12) {
		this.mes12 = mes12;
	}
	
}
