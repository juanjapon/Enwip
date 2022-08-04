package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries( 
		{ @NamedQuery(name = "Mensajes.findMyId", query = "SELECT g FROM Mensajes g WHERE g.usuario_id= :id"),
			@NamedQuery(name = "Mensajes.findAll", query = "SELECT g FROM Mensajes g")
		}

)
@Table(name="MENSAJES")
public class Mensajes implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="MENSAJE_ID")
	private int mensaje_id;
	@Column(name="USUARIOSUSUARIO_ID")
	private int usuario_id;
	@Column(name="ASUNTO")
	private String asunto;
	@Column(name="CUERPO")
	private String cuerpo;
	@Column(name="FECHA")
	private java.util.Date fecha;
	@Column(name="TIPO_MENSAJETIPO_ID")
	private int tipoMensaje_id;
	@Column(name="GLOBO")
	private boolean globo;
	
	
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
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getCuerpo() {
		return cuerpo;
	}
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}


	public java.util.Date getFecha() {
		return fecha;
	}
	public void setFecha(java.util.Date fecha) {
		this.fecha = fecha;
	}
	public int getTipoMensaje_id() {
		return tipoMensaje_id;
	}
	public void setTipoMensaje_id(int tipoMensaje_id) {
		this.tipoMensaje_id = tipoMensaje_id;
	}

	public boolean isGlobo() {
		return globo;
	}
	public void setGlobo(boolean globo) {
		this.globo = globo;
	}
	
	

}
