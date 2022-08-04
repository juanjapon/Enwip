package server.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries( { @NamedQuery(name = "Administradores.findUsuario", query = "SELECT u FROM Administradores u WHERE u.nombre =:nombre"),
	@NamedQuery(name = "Administradores.findUsuarioByNombreAndPass", query = "SELECT u FROM Administradores u WHERE u.nombre =:nombre AND u.password= :clave")})
@Table(name="ADMINISTRADORES")
public class Administradores implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="ADMINISTRADOR_ID")
	private int administrador_id;
	@Column(name="NOMBRE")
	private String nombre;
	@Column (name="A_PASSWORD")
	private String password;
	public int getAdministrador_id() {
		return administrador_id;
	}
	public void setAdministrador_id(int administrador_id) {
		this.administrador_id = administrador_id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
