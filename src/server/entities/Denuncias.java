package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries( 
		{
		  
		  @NamedQuery(name = "Denuncias.findAll", query = "SELECT n FROM Denuncias n")
		}	
		
)
@Table(name="DENUNCIAS")
public class Denuncias implements Serializable {
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="RESPUESTASRESPUESTA_ID")
	private int respuesta_id;
	@Column(name="CANTIDAD_DENUNCIAS")
	private int cantidadDenuncias;
	
	public int getRespuesta_id() {
		return respuesta_id;
	}
	public void setRespuesta_id(int respuesta_id) {
		this.respuesta_id = respuesta_id;
	}
	public int getCantidadDenuncias() {
		return cantidadDenuncias;
	}
	public void setCantidadDenuncias(int cantidadDenuncias) {
		this.cantidadDenuncias = cantidadDenuncias;
	}
	
	
}
