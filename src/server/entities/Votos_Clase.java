package server.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(Votos_ClasePK.class)
@Table(name="VOTOS_CLASE")
public class Votos_Clase implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="USUARIO_ID")
	private int usuario_id;
	@Id
	@Column(name="CLASE_ID")
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
