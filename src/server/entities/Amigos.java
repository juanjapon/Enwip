package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries( 
		{
		  @NamedQuery(name="Amigos.findOkById",query="SELECT d FROM Amigos d WHERE d.amigo_id= :id OR d.usuario_id= :id"),
		  @NamedQuery(name="Amigos.findSolicitudes",query="SELECT d FROM Amigos d WHERE d.amigo_id= :id"),
		  @NamedQuery(name = "Amigos.findAll", query = "SELECT n FROM Amigos n")
		}	
		
)
@IdClass(AmigosPK.class)
@Table(name="AMIGOS")
public class Amigos implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="AMIGOUSUARIO_ID")
	private int amigo_id;
	@Id
	@Column(name="USUARIOSUSUARIO_ID")
	private int usuario_id;
	@Column(name="ESTADO_SOLICITUDESTADO_ID")
	private int solicitudEstado_id;
	@Column(name="FECHA_CREACION")
	private java.util.Date fechaCreacion;
	
	
	public int getAmigo_id() {
		return amigo_id;
	}
	public void setAmigo_id(int amigo_id) {
		this.amigo_id = amigo_id;
	}
	public int getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
	public int getSolicitudEstado_id() {
		return solicitudEstado_id;
	}
	public void setSolicitudEstado_id(int solicitudEstado_id) {
		this.solicitudEstado_id = solicitudEstado_id;
	}
	public java.util.Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(java.util.Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	

}
