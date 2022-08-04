package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="USUARIOS_CLASES")
public class Usuarios_Clases implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="USUARIOSUSUARIO_ID")
	private int usuario_id;
	@Column(name="CLASESCLASE_ID")
	private int clase_id;
	
	public int getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
	public int getClase_id() {
		return clase_id;
	}
	public void setClase_id(int clase_id) {
		this.clase_id = clase_id;
	}
	

}
