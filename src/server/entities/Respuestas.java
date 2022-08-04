package server.entities;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@NamedQueries( 
		{
		  @NamedQuery(name="Respuestas.findByMyPreg",query="SELECT d FROM Respuestas d WHERE d.pregunta_id= :id"),
		  @NamedQuery(name = "Respuestas.findAll", query = "SELECT n FROM Respuestas n")
		}	
		
)
@Table(name="RESPUESTAS")
public class Respuestas implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="RESPUESTA_ID")
	private int respuesta_id;
	@Column(name="PREGUNTASPREGUNTA_ID")
	private int pregunta_id;
	@Column(name="USUARIOSUSUARIO_ID")
	private int usuario_id;
	@Column(name="CONTENIDO")
	private String contenido;
	@Column(name="MEJOR_RESPUESTA")
	private boolean mejorRespuesta;
	@Column(name="FECHA")
	private Date fecha;
	
	
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getRespuesta_id() {
		return respuesta_id;
	}
	public void setRespuesta_id(int respuesta_id) {
		this.respuesta_id = respuesta_id;
	}
	public int getPregunta_id() {
		return pregunta_id;
	}
	public void setPregunta_id(int pregunta_id) {
		this.pregunta_id = pregunta_id;
	}
	public int getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public boolean isMejorRespuesta() {
		return mejorRespuesta;
	}
	public void setMejorRespuesta(boolean mejorRespuesta) {
		this.mejorRespuesta = mejorRespuesta;
	}
	

}
