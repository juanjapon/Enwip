package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="PRIVACIDAD")
public class Privacidad implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="USUARIO_ID")
	private int usuario_id;
	@Column(name="NOMBRE")
	private int nombre;
	@Column(name="APELLIDO")
	private int apellido;
	@Column(name="DIRECCION")
	private int direccion;
	@Column(name="PAIS")
	private int pais;
	@Column(name="GENERO")
	private int genero;
	@Column(name="CIUDAD")
	private int ciudad;
	@Column(name="TELEFONO")
	private int telefono;
	@Column(name="E_MAIL")
	private int eMail;
	@Column(name="FECHA_DE_NACIMIENTO")
	private int fechaNacimiento;
	@Column(name="RESEÑA_PERSONAL")
	private int reseñaPersonal;
	@Column(name="ESTUDIO")
	private int estudios;
	
	
	public int getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
	public int getNombre() {
		return nombre;
	}
	public void setNombre(int nombre) {
		this.nombre = nombre;
	}
	public int getApellido() {
		return apellido;
	}
	public void setApellido(int apellido) {
		this.apellido = apellido;
	}
	public int getDireccion() {
		return direccion;
	}
	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}
	public int getPais() {
		return pais;
	}
	public void setPais(int pais) {
		this.pais = pais;
	}
	public int getGenero() {
		return genero;
	}
	public void setGenero(int genero) {
		this.genero = genero;
	}
	public int getCiudad() {
		return ciudad;
	}
	public void setCiudad(int ciudad) {
		this.ciudad = ciudad;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public int geteMail() {
		return eMail;
	}
	public void seteMail(int eMail) {
		this.eMail = eMail;
	}
	public int getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(int fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public int getReseñaPersonal() {
		return reseñaPersonal;
	}
	public void setReseñaPersonal(int reseñaPersonal) {
		this.reseñaPersonal = reseñaPersonal;
	}
	public int getEstudios() {
		return estudios;
	}
	public void setEstudios(int estudios) {
		this.estudios = estudios;
	}
	
	

}
