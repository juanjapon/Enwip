package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries( 
		{
		  @NamedQuery(name="Datos_Planes_De_Negocios.findById",query="SELECT d FROM Datos_Planes_De_Negocios d WHERE d.datos_id= :id")}	

)
@Table(name="DATOS_PLANES_DE_NEGOCIOS")
public class Datos_Planes_De_Negocios implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="DATOS_ID")
	private int datos_id;
	@Column(name="NOMBRE")
	private String nombre;
	@Column(name="EQUIPO")
	private String equipo;
	@Column(name="RESUMEN_EJECUTIVO")
	private String resumenEjecutivo;
	@Column(name="MERCADO")
	private String mercado;
	@Column(name="COMPETENCIA")
	private String competencia;
	@Column(name="EMPRESA")
	private String empresa;
	@Column(name="PRODUCTOS_SERVICIOS")
	private String productosServicios;
	@Column(name="PRODUCCION_SERVUCCION")
	private String produccionServuccion;
	@Column(name="PRECIO_PRODUCTOSOSERVICIOS")
	private String precioProductosOServicios;
	@Column(name="MERCADO_OBJETIVO")
	private String mercadoObjetivo;
	@Column(name="ENTRADA_MERCADO")
	private String entradaMercado;
	@Column(name="POTENCIAL_MERCADO")
	private String potencialMercado;
	@Column(name="VIABILIDAD_JURIDICA")
	private String viabilidadJuridica;
	@Column(name="OBLIGACIONES_TRIBUTARIAS")
	private String obligacionesTributarias;
	@Column(name="OTROS_LEGALES")
	private String otrosLegales;
	@Column(name="RIESGOS_CONTINGENCIAS")
	private String riesgosContiengencias;
	public int getDatos_id() {
		return datos_id;
	}
	public void setDatos_id(int datos_id) {
		this.datos_id = datos_id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEquipo() {
		return equipo;
	}
	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}
	public String getResumenEjecutivo() {
		return resumenEjecutivo;
	}
	public void setResumenEjecutivo(String resumenEjecutivo) {
		this.resumenEjecutivo = resumenEjecutivo;
	}
	public String getMercado() {
		return mercado;
	}
	public void setMercado(String mercado) {
		this.mercado = mercado;
	}
	public String getCompetencia() {
		return competencia;
	}
	public void setCompetencia(String competencia) {
		this.competencia = competencia;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getProductosServicios() {
		return productosServicios;
	}
	public void setProductosServicios(String productosServicios) {
		this.productosServicios = productosServicios;
	}
	public String getProduccionServuccion() {
		return produccionServuccion;
	}
	public void setProduccionServuccion(String produccionServuccion) {
		this.produccionServuccion = produccionServuccion;
	}
	public String getPrecioProductosOServicios() {
		return precioProductosOServicios;
	}
	public void setPrecioProductosOServicios(String precioProductosOServicios) {
		this.precioProductosOServicios = precioProductosOServicios;
	}
	public String getMercadoObjetivo() {
		return mercadoObjetivo;
	}
	public void setMercadoObjetivo(String mercadoObjetivo) {
		this.mercadoObjetivo = mercadoObjetivo;
	}
	public String getEntradaMercado() {
		return entradaMercado;
	}
	public void setEntradaMercado(String entradaMercado) {
		this.entradaMercado = entradaMercado;
	}
	public String getPotencialMercado() {
		return potencialMercado;
	}
	public void setPotencialMercado(String potencialMercado) {
		this.potencialMercado = potencialMercado;
	}
	public String getViabilidadJuridica() {
		return viabilidadJuridica;
	}
	public void setViabilidadJuridica(String viabilidadJuridica) {
		this.viabilidadJuridica = viabilidadJuridica;
	}
	public String getObligacionesTributarias() {
		return obligacionesTributarias;
	}
	public void setObligacionesTributarias(String obligacionesTributarias) {
		this.obligacionesTributarias = obligacionesTributarias;
	}
	public String getOtrosLegales() {
		return otrosLegales;
	}
	public void setOtrosLegales(String otrosLegales) {
		this.otrosLegales = otrosLegales;
	}
	public String getRiesgosContiengencias() {
		return riesgosContiengencias;
	}
	public void setRiesgosContiengencias(String riesgosContiengencias) {
		this.riesgosContiengencias = riesgosContiengencias;
	}

}
