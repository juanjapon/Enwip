package server.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(Usuarios_Inversion_IdeasPK.class)
@Table(name="USUARIOS_INVERSION_IDEAS")
public class Usuarios_Inversion_Ideas implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="USUARIO_ID")
	private int usuario_id;
	@Id
	@Column(name="IDEA_ID")
	private int idea_id;
	@Column(name="INVERSION")
	private int inversion;
	
	public int getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
	public int getIdea_id() {
		return idea_id;
	}
	public void setIdea_id(int idea_id) {
		this.idea_id = idea_id;
	}
	public int getInversion() {
		return inversion;
	}
	public void setInversion(int inversion) {
		this.inversion = inversion;
	}
	
	
}
