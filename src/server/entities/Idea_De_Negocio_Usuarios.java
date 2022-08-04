package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries( 
		{ @NamedQuery(name = "Idea_De_Negocio_Usuarios.findMyId", query = "SELECT g FROM Idea_De_Negocio_Usuarios g WHERE g.usuario_id= :id"),
			@NamedQuery(name = "Idea_De_Negocio_Usuarios.findNoMyId", query = "SELECT g FROM Idea_De_Negocio_Usuarios g WHERE g.usuario_id!= :id")	
		}

)
@Table(name="IDEA_DE_NEGOCIO_USUARIOS")
public class Idea_De_Negocio_Usuarios implements Serializable{
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
