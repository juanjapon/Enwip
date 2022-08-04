package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries( 
		{ @NamedQuery(name = "Usuarios_Planes_De_Negocios.findMyId", query = "SELECT g FROM Usuarios_Planes_De_Negocios g WHERE g.usuario_id= :id")}

)
@Table(name="USUARIOS_PLANES_DE_NEGOCIOS")
public class Usuarios_Planes_De_Negocios implements Serializable{
	private static final long serialVersionUID=1L;

	
	@Column(name="USUARIOSUSUARIO_ID")
	private int usuario_id;

	@Id
	@Column(name="PLANES_DE_NEGOCIOSPLAN_ID")
	private int plan_id;
	
	public int getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
	public int getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}
	

}
