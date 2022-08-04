package server.entities;

import java.io.Serializable;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries( 
		{ @NamedQuery(name = "Para.findMyId", query = "SELECT g FROM Para g WHERE g.usuario_id= :id")}

)
@IdClass(ParaPK.class)
@Table(name="PARA")
public class Para  implements Serializable{
	private static final long serialVersionUID=1L;
	
	@Id
	@Column(name="MENSAJESMENSAJE_ID")
	private int mensaje_id;
	@Id
	@Column(name="USUARIO_ID")
	private int usuario_id;
	@Column(name="BANDERA")
	
	private boolean bandera;
	
	public boolean isBandera() {
		return bandera;
	}
	public void setBandera(boolean bandera) {
		this.bandera = bandera;
	}
	public int getMensaje_id() {
		return mensaje_id;
	}
	public void setMensaje_id(int mensaje_id) {
		this.mensaje_id = mensaje_id;
	}
	public int getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
}
