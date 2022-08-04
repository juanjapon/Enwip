package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="USUARIOS_CURSOS")
public class Usuarios_Cursos implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="USUARIOSUSUARIO_ID")
	private int usuario_id;
	@Column(name="CURSOSCURSO_ID")
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
