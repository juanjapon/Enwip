package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries( 
		{ @NamedQuery(name = "Grupos_Usuarios.findMyId", query = "SELECT g FROM Grupos_Usuarios g WHERE g.usuario_id= :id")}

)
@Table(name="GRUPOS_USUARIOS")
public class Grupos_Usuarios implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="GRUPOSGRUPO_ID")
	private int grupo_id;
	@Column(name="USUARIOSUSUARIO_ID")
	private int usuario_id;
	
	public int getGrupo_id() {
		return grupo_id;
	}
	public void setGrupo_id(int grupo_id) {
		this.grupo_id = grupo_id;
	}
	public int getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
	
	

}
