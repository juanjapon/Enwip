package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="PRODUCTOS_ENWIP_USUARIOS")
public class Productos_Enwip_Usuarios implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="PRODUCTOS_ENWIPPRODUCTO_ID")
	private int producto_id;
	@Column(name="USUARIOSUSUARIO_ID")
	private int usuario_id;
	
	public int getProducto_id() {
		return producto_id;
	}
	public void setProducto_id(int producto_id) {
		this.producto_id = producto_id;
	}
	public int getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
	

}
