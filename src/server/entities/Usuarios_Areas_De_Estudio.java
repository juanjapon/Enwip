package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="USUARIOS_AREAS_DE_ESTUDIO")
public class Usuarios_Areas_De_Estudio implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="USUARIOSUSUARIO_ID")
	private int usuario_id;
	@Column(name="AREAS_DE_ESTUDIOAREA_ESTUDIO_ID")
	private int areaEstuido_id;
	
	public int getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
	public int getAreaEstuido_id() {
		return areaEstuido_id;
	}
	public void setAreaEstuido_id(int areaEstuido_id) {
		this.areaEstuido_id = areaEstuido_id;
	}
	

}
