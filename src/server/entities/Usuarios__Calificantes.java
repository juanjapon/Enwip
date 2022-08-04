package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="USUARIOS_CALIFICANTES")
public class Usuarios__Calificantes implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="IDEA_DE_NEGOCIOIDEA_ID")
	private int idea_id;
	@Column(name="USUARIOSUSUARIO_ID")
	private int usuario_id;
	
	public int getIdea_id() {
		return idea_id;
	}
	public void setIdea_id(int idea_id) {
		this.idea_id = idea_id;
	}
	public int getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
	

	

}
