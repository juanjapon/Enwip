package server.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries( 
		{
		  @NamedQuery(name="Cursos.findByAdmin",query="SELECT d FROM Cursos d WHERE d.administrador_id= :id"),
		  @NamedQuery(name = "Cursos.findCharlas", query = "SELECT u FROM Cursos u WHERE u.nombre LIKE :texto1 or u.nombre LIKE :texto2 or u.nombre LIKE :texto3 or u.contenido LIKE :texto4  or u.contenido LIKE :texto5  or u.contenido LIKE :texto6 or u.descripcion LIKE :texto7  or u.descripcion LIKE :texto8  or u.descripcion LIKE :texto9"),
		  @NamedQuery(name = "Cursos.findAll", query = "SELECT n FROM Cursos n")
		}	
		
)
@Table(name="CURSOS")
public class Cursos implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="CURSO_ID")
	private int curso_id;
	@Column(name="DESCRIPCION_CONFERENCISTA")
	private String descripcionConferencista;
	@Column(name="NOMBRE_CURSO")
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
	public int getCurso_id() {
		return curso_id;
	}
	public void setCurso_id(int curso_id) {
		this.curso_id = curso_id;
	}
	public String getDescripcionConferencista() {
		return descripcionConferencista;
	}
	public void setDescripcionConferencista(String descripcionConferencista) {
		this.descripcionConferencista = descripcionConferencista;
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
