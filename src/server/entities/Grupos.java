package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries( 
		{   @NamedQuery(name = "Grupos.findGrupos", query = "SELECT u FROM Grupos u WHERE u.nombre LIKE :texto1 or u.nombre LIKE :texto2 or u.nombre LIKE :texto3 or u.descripcion LIKE :texto7  or u.descripcion LIKE :texto8  or u.descripcion LIKE :texto9"),
			@NamedQuery(name = "Grupos.findByNombreDescripcion", query = "SELECT g FROM Grupos g WHERE g.nombre= :nombre AND g.descripcion= :descripcion")}	

)
@Table(name="GRUPOS")
public class Grupos implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="GRUPO_ID")
	private int grupo_id;
	@Column(name="NOMBRE")
	private String nombre;
	@Column(name="DESCRIPCION")
	private String descripcion;
	@Column(name="USUARIOSUSUARIO_ID_ADMINISTRADOR")
	private int usuarioAdministrador_id;
	
	
	public int getGrupo_id() {
		return grupo_id;
	}
	public void setGrupo_id(int grupo_id) {
		this.grupo_id = grupo_id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getUsuarioAdministrador_id() {
		return usuarioAdministrador_id;
	}
	public void setUsuarioAdministrador_id(int usuarioAdministrador_id) {
		this.usuarioAdministrador_id = usuarioAdministrador_id;
	}
	
	
	

}
