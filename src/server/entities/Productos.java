package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries( 
		{
		  @NamedQuery(name="Productos.findByPlanId",query="SELECT d FROM Productos d WHERE d.finanzas_id= :id"),
		  @NamedQuery(name = "Productos.findAll", query = "SELECT n FROM Productos n")
		}	
		
)
@Table(name="PRODUCTOS")
public class Productos implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="PRODUCTO_ID")
	private int producto_id;
	@Column(name="FINANZASFINANZAS_ID")
	private int finanzas_id;
	@Column(name="NOMBRE_PRODUCTO")
	private String nombre;
	@Column(name="PRECIO_VENTA")
	private int precioVenta;
	@Column(name="CONTADO")
	private int contado;
	@Column(name="A_30_DIAS")
	private int a30;
	@Column(name="A_60_DIAS")
	private int a60;
	@Column(name="A_90_DIAS")
	private int a90;
	@Column(name="A_120_DIAS")
	private int a120;
	@Column(name="A_150_DIAS")
	private int a150;
	@Column(name="PORCENTAJE_COMISION")
	private int porcentajeComision;

	
	public int getProducto_id() {
		return producto_id;
	}
	public void setProducto_id(int producto_id) {
		this.producto_id = producto_id;
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
	public int getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(int precioVenta) {
		this.precioVenta = precioVenta;
	}
	public int getContado() {
		return contado;
	}
	public void setContado(int contado) {
		this.contado = contado;
	}
	public int getA30() {
		return a30;
	}
	public void setA30(int a30) {
		this.a30 = a30;
	}
	public int getA60() {
		return a60;
	}
	public void setA60(int a60) {
		this.a60 = a60;
	}
	public int getA90() {
		return a90;
	}
	public void setA90(int a90) {
		this.a90 = a90;
	}
	public int getA120() {
		return a120;
	}
	public void setA120(int a120) {
		this.a120 = a120;
	}
	public int getA150() {
		return a150;
	}
	public void setA150(int a150) {
		this.a150 = a150;
	}
	public int getPorcentajeComision() {
		return porcentajeComision;
	}
	public void setPorcentajeComision(int porcentajeComision) {
		this.porcentajeComision = porcentajeComision;
	}

	
	

}
