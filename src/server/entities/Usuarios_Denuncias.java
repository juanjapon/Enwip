package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@IdClass(Usuarios_DenunciasPK.class)
@Table(name="USUARIOS_DENUNCIAS")
public class Usuarios_Denuncias  implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="USUARIOSUSUARIO_ID")
	private int usuario_id;
	@Id
	@Column(name="DENUNCIASRESPUESTASRESPUESTA_ID")
	private int denuncia_id;
	
	public int getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
	public int getDenuncia_id() {
		return denuncia_id;
	}
	public void setDenuncia_id(int denuncia_id) {
		this.denuncia_id = denuncia_id;
	}
	
	

}
