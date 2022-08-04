package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries( { @NamedQuery(name = "Nivel_Inversion.findAll", query = "SELECT n FROM Nivel_Inversion n"),
	 @NamedQuery(name = "Nivel_Inversion.findByNombre", query = "SELECT n FROM Nivel_Inversion n WHERE n.rango= :nombre")})
@Table(name="NIVEL_INVERSION")
public class Nivel_Inversion implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="NIVEL_ID")
	private int nivel_id;
	@Column(name="RANGO")
	private String rango;
	
	public int getNivel_id() {
		return nivel_id;
	}
	public void setNivel_id(int nivel_id) {
		this.nivel_id = nivel_id;
	}
	public String getRango() {
		return rango;
	}
	public void setRango(String rango) {
		this.rango = rango;
	}
	

}
