package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries( 
		{
		  @NamedQuery(name="Preguntas.findByMyId",query="SELECT d FROM Preguntas d WHERE d.usuario_id= :id"),
		  @NamedQuery(name = "Preguntas.findPreguntas", query = "SELECT u FROM Preguntas u WHERE u.contenido LIKE :texto1 or u.contenido LIKE :texto2 or u.contenido LIKE :texto3"),
		  @NamedQuery(name = "Preguntas.findAll", query = "SELECT n FROM Preguntas n WHERE n.usuario_id<>:id")
		}	
		
)
@Table(name="PREGUNTAS")
public class Preguntas implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="PREGUNTA_ID")
	private int pregunta_id;
	@Column(name="USUARIOSUSUARIO_ID")
	private int usuario_id;
	@Column(name="MEJOR_RESPUESTA")
	private boolean mejorRespuesta;
	@Column(name="CONTENIDO")
	private String contenido;
	@Column(name="AREAS_DE_ESTUDIOAREA_ESTUDIO_ID")
	private int areaEstudio_id;
	
	
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
	public boolean isMejorRespuesta() {
		return mejorRespuesta;
	}
	public void setMejorRespuesta(boolean mejorRespuesta) {
		this.mejorRespuesta = mejorRespuesta;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public int getAreaEstudio_id() {
		return areaEstudio_id;
	}
	public void setAreaEstudio_id(int areaEstudio_id) {
		this.areaEstudio_id = areaEstudio_id;
	}
	
	

}
