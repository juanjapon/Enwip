package server.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="AREAS_INVERSION")
public class Areas_Inversion implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="USUARIOSUSUARIO_ID")
	private int usuario_id;
	@Column(name="AREAS_DE_ESTUDIOAREA_ESTUDIO_ID")
	private int areaEstudio_id;
	
	
	public int getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
	public int getAreaEstudio_id() {
		return areaEstudio_id;
	}
	public void setAreaEstudio_id(int areaEstudio_id) {
		this.areaEstudio_id = areaEstudio_id;
	}
	
	

}
