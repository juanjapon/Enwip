package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="USUARIO_TIPO")
public class Usuario_Tipo implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="TIPO_ID")
	private int tipo_id;
	@Column(name="NOMBRE_TIPO")
	private String nombre;
	
	public int getTipo_id() {
		return tipo_id;
	}
	public void setTipo_id(int tipo_id) {
		this.tipo_id = tipo_id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
