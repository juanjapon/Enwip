package server.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(Negativos_CharlasPK.class)
@Table(name="NEGATIVOS_CHARLAS")
public class Negativos_Charlas implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="USUARIO_ID")
	private int usuario_id;
	@Id
	@Column(name="CURSO_ID")
	private int curso_id;
	
	public int getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
	public int getCurso_id() {
		return curso_id;
	}
	public void setCurso_id(int curso_id) {
		this.curso_id = curso_id;
	}

}
