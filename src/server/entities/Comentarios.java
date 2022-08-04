package server.entities;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@NamedQueries( 
		{ @NamedQuery(name = "Comentarios.findByGrupo", query = "SELECT c FROM Comentarios c WHERE c.grupo_id= :id ORDER BY c.comentario_id ASC")}	

)
@Table(name="COMENTARIOS")
public class Comentarios implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="COMENTARIO_ID")
	private int comentario_id;
	@Column(name="CONTENIDO")
	private String contenida;
	@Column(name="GRUPOS_USUARIOSGRUPOSGRUPO_ID")
	private int grupo_id;
	@Column(name="GRUPOS_USUARIOSUSUARIOSUSUARIO_ID")
	private int usuario_id;
	@Column(name="FECHA")
	private Date fecha;
	
	
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public int getComentario_id() {
		return comentario_id;
	}
	public void setComentario_id(int comentario_id) {
		this.comentario_id = comentario_id;
	}
	public String getContenida() {
		return contenida;
	}
	public void setContenida(String contenida) {
		this.contenida = contenida;
	}
	public int getGrupo_id() {
		return grupo_id;
	}
	public void setGrupo_id(int grupo_id) {
		this.grupo_id = grupo_id;
	}
	public int getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
	
	

}
