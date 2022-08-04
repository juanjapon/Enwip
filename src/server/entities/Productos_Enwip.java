package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="PRODUCTOS_ENWIP")
public class Productos_Enwip implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="PRODUCTO_ID")
	private int producto_id;
	@Column(name="NOMBRE")
	private String nombre;
	
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
	

}
