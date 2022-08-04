package server.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries( 
		{
		  @NamedQuery(name="Clases.findByAdmin",query="SELECT d FROM Clases d WHERE d.administrador_id= :id"),
		  @NamedQuery(name = "Clases.findClases", query = "SELECT u FROM Clases u WHERE u.nombre LIKE :texto1 or u.nombre LIKE :texto2 or u.nombre LIKE :texto3 or u.contenido LIKE :texto4  or u.contenido LIKE :texto5  or u.contenido LIKE :texto6 or u.descripcion LIKE :texto7  or u.descripcion LIKE :texto8  or u.descripcion LIKE :texto9"),
		  @NamedQuery(name = "Clases.findAll", query = "SELECT n FROM Clases n")
		}	
		
)
@Table(name="CLASES")
public class Clases implements Serializable {
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="CLASE_ID")
	private int clase_id;
	@Column(name="NOMBRE_CLASE")
	private String nombre;
	@Column(name="NOMBRE_PROFESOR")
	private String nombreProfesor;
	@Column(name="CONTENIDO")
	private String contenido;
	@Column(name="DESCRIPCION")
	private String descripcion;
	@Column(name="ADMINISTRADORESADMINISTRADOR_ID")
	private int administrador_id;
	@Column(name="AUTORIZADOSUSUARIO_TIPO")
	private int usuarioAutorizado;
	@Column(name="CALIFICACION")
	private int calificacion;
	@Column(name="NEGATIVOS")
	private int negativos;
	
	public void sumarNegativos(){
		negativos++;
	}
	
	public int getNegativos() {
		return negativos;
	}
	public void setNegativos(int negativos) {
		this.negativos = negativos;
	}
	public int getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}
	public int getClase_id() {
		return clase_id;
	}
	public void setClase_id(int clase_id) {
		this.clase_id = clase_id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombreProfesor() {
		return nombreProfesor;
	}
	public void setNombreProfesor(String nombreProfesor) {
		this.nombreProfesor = nombreProfesor;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getAdministrador_id() {
		return administrador_id;
	}
	public void setAdministrador_id(int administrador_id) {
		this.administrador_id = administrador_id;
	}
	public int getUsuarioAutorizado() {
		return usuarioAutorizado;
	}
	public void setUsuarioAutorizado(int usuarioAutorizado) {
		this.usuarioAutorizado = usuarioAutorizado;
	}
	
	
	

}
