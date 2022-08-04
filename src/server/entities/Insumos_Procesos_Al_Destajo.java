package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries( 
		{
		  @NamedQuery(name="Insumos_Procesos_Al_Destajo.findByPlanId",query="SELECT d FROM Insumos_Procesos_Al_Destajo d WHERE d.producto_id= :id"),
		  @NamedQuery(name = "Insumos_Procesos_Al_Destajo.findAll", query = "SELECT n FROM Insumos_Procesos_Al_Destajo n")
		}	
		
)
@Table(name="INSUMOS_PROCESOS_AL_DESTAJO")
public class Insumos_Procesos_Al_Destajo implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="INSUMO_PROCESO_ID")
	private int insumoProceso_id;
	@Column(name="PRODUCTOSPRODUCTO_ID")
	private int producto_id;
	@Column(name="NOMBRE")
	private String nombre;
	@Column(name="COSTO")
	private int costo;
	@Column(name="INVENTARIO")
	private int inventario;
	@Column(name="FORMA_PAGO_INSUMOFORMA_ID")
	private int formaPago_id;
	
	public int getInsumoProceso_id() {
		return insumoProceso_id;
	}
	public void setInsumoProceso_id(int insumoProceso_id) {
		this.insumoProceso_id = insumoProceso_id;
	}
	public int getProducto_id() {
		return producto_id;
	}
	public void setProducto_id(int producto_id) {
		this.producto_id = producto_id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCosto() {
		return costo;
	}
	public void setCosto(int costo) {
		this.costo = costo;
	}
	public int getInventario() {
		return inventario;
	}
	public void setInventario(int inventario) {
		this.inventario = inventario;
	}
	public int getFormaPago_id() {
		return formaPago_id;
	}
	public void setFormaPago_id(int formaPago_id) {
		this.formaPago_id = formaPago_id;
	}
	
	

}
