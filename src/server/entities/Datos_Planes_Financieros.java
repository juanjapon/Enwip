package server.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries( 
		{
		  @NamedQuery(name="Datos_Planes_Financieros.findById",query="SELECT d FROM Datos_Planes_Financieros d WHERE d.datos_id= :id")}	

)
@Table(name="DATOS_PLANES_FINANCIEROS")
public class Datos_Planes_Financieros implements Serializable{
	private static final long serialVersionUID=1L;
	
	@Id
	@Column(name="DATOS_PLANES_FINANCIEROS_ID")
	private int datos_id;
	@Column(name="PRODUCTOS")
	private String productos;
	@Column(name="MEDIOS_COMUNICACION")
	private String mediosComunicacion;
	@Column(name="INSUMOS_PROCESOS_AL_DESTAJO")
	private String insumosProcesos;
	@Column(name="INVERSION_ACTIVOS_FIJOS")
	private String activosFijos;
	@Column(name="COSTOS_FIJOS")
	private String costosFijos;
	@Column(name="GASTOS_FIJOS_ADMINISTRACION")
	private String gastosFijosAdministracion;
	@Column(name="GASTOS_VENTAS")
	private String gastosVentas;
	@Column(name="EMPLEADOS")
	private String empleados;
	@Column(name="INVERSION_PREOPERATIVA")
	private String inversionPreoperativa;
	@Column(name="IMPUESTOS")
	private String impuestos;
	@Column(name="DATOS_PROYECCION")
	private String datosProyeccion;
	@Column(name="PROYECCION_PRODUCTOS")
	private String proyeccionProductos;
	@Column(name="PROYECCION_COSTOS")
	private String proyeccionCostos;
	@Column(name="PROYECCION_GASTOS")
	private String proyeccionGastos;
	@Column(name="PROYECCION_VENTAS")
	private String proyeccionVentas;
	@Column(name="PROYECCION_EMPLEADOS")
	private String proyeccionEmpleados;
	
	

	
	public String getProyeccionProductos() {
		return proyeccionProductos;
	}
	public void setProyeccionProductos(String proyeccionProductos) {
		this.proyeccionProductos = proyeccionProductos;
	}
	public String getProyeccionCostos() {
		return proyeccionCostos;
	}
	public void setProyeccionCostos(String proyeccionCostos) {
		this.proyeccionCostos = proyeccionCostos;
	}
	public String getProyeccionGastos() {
		return proyeccionGastos;
	}
	public void setProyeccionGastos(String proyeccionGastos) {
		this.proyeccionGastos = proyeccionGastos;
	}
	public String getProyeccionVentas() {
		return proyeccionVentas;
	}
	public void setProyeccionVentas(String proyeccionVentas) {
		this.proyeccionVentas = proyeccionVentas;
	}
	public String getProyeccionEmpleados() {
		return proyeccionEmpleados;
	}
	public void setProyeccionEmpleados(String proyeccionEmpleados) {
		this.proyeccionEmpleados = proyeccionEmpleados;
	}
	public String getActivosFijos() {
		return activosFijos;
	}
	public void setActivosFijos(String activosFijos) {
		this.activosFijos = activosFijos;
	}
	public String getCostosFijos() {
		return costosFijos;
	}
	public void setCostosFijos(String costosFijos) {
		this.costosFijos = costosFijos;
	}
	public String getGastosFijosAdministracion() {
		return gastosFijosAdministracion;
	}
	public void setGastosFijosAdministracion(String gastosFijosAdministracion) {
		this.gastosFijosAdministracion = gastosFijosAdministracion;
	}
	public String getGastosVentas() {
		return gastosVentas;
	}
	public void setGastosVentas(String gastosVentas) {
		this.gastosVentas = gastosVentas;
	}
	public String getEmpleados() {
		return empleados;
	}
	public void setEmpleados(String empleados) {
		this.empleados = empleados;
	}
	public String getInversionPreoperativa() {
		return inversionPreoperativa;
	}
	public void setInversionPreoperativa(String inversionPreoperativa) {
		this.inversionPreoperativa = inversionPreoperativa;
	}
	public String getImpuestos() {
		return impuestos;
	}
	public void setImpuestos(String impuestos) {
		this.impuestos = impuestos;
	}
	public String getDatosProyeccion() {
		return datosProyeccion;
	}
	public void setDatosProyeccion(String datosProyeccion) {
		this.datosProyeccion = datosProyeccion;
	}
	public String getMediosComunicacion() {
		return mediosComunicacion;
	}
	public void setMediosComunicacion(String mediosComunicacion) {
		this.mediosComunicacion = mediosComunicacion;
	}
	public int getDatos_id() {
		return datos_id;
	}
	public void setDatos_id(int datos_id) {
		this.datos_id = datos_id;
	}
	public String getProductos() {
		return productos;
	}
	public void setProductos(String productos) {
		this.productos = productos;
	}
	public String getInsumosProcesos() {
		return insumosProcesos;
	}
	public void setInsumosProcesos(String insumosProcesos) {
		this.insumosProcesos = insumosProcesos;
	}

}
