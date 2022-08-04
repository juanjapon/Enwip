package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries( { @NamedQuery(name = "Idea_De_Negocio.findAll", query = "SELECT n FROM Idea_De_Negocio n")})
@Table(name="IDEA_DE_NEGOCIO")
public class Idea_De_Negocio implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="IDEA_ID")
	private int idea_id;
	@Column(name="NOMBRE_IDEA")
	private String nombre;
	@Column(name="IDEA")
	private String idea;
	@Column(name="CALIFICACION")
	private int calificacion;
	@Column(name="NUMERO_DE_CALIFICACIONES")
	private int numeroCalificaciones;
	@Column(name="NACIMIENTO")
	private String nacimiento;
	@Column(name="PAISESCC_FIPS")
	private String pais;
	@Column(name="CIUDAD")
	private String ciudad;
	@Column(name="AREAS_DE_ESTUDIOAREA_ESTUDIO_ID")
	private int areaEstudio_id;
	@Column(name="NIVEL_INVERSIONNIVEL_ID")
	private int nivelInversion_id;
	@Column(name="TIPO_INVERSIONTIPO_ID")
	private int tipoInversion_id;
	@Column(name="TIPO_INVERSIONISTATIPO_ID")
	private int tipoInversionista_id;
	@Column(name="INVERSION_RECOGIDA")
	private int inversionRecogida;
	@Column(name="COSTO_INVERSION")
	private int costoInversion;
	
	
	
	public int getInversionRecogida() {
		return inversionRecogida;
	}
	public void setInversionRecogida(int inversionRecogida) {
		this.inversionRecogida = inversionRecogida;
	}
	public int getCostoInversion() {
		return costoInversion;
	}
	public void setCostoInversion(int costoInversion) {
		this.costoInversion = costoInversion;
	}
	public int getIdea_id() {
		return idea_id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setIdea_id(int idea_id) {
		this.idea_id = idea_id;
	}
	public String getIdea() {
		return idea;
	}
	public void setIdea(String idea) {
		this.idea = idea;
	}
	public int getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}
	public int getNumeroCalificaciones() {
		return numeroCalificaciones;
	}
	public void setNumeroCalificaciones(int numeroCalificaciones) {
		this.numeroCalificaciones = numeroCalificaciones;
	}
	public String getNacimiento() {
		return nacimiento;
	}
	public void setNacimiento(String nacimiento) {
		this.nacimiento = nacimiento;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public int getAreaEstudio_id() {
		return areaEstudio_id;
	}
	public void setAreaEstudio_id(int areaEstudio_id) {
		this.areaEstudio_id = areaEstudio_id;
	}
	public int getNivelInversion_id() {
		return nivelInversion_id;
	}
	public void setNivelInversion_id(int nivelInversion_id) {
		this.nivelInversion_id = nivelInversion_id;
	}
	public int getTipoInversion_id() {
		return tipoInversion_id;
	}
	public void setTipoInversion_id(int tipoInversion_id) {
		this.tipoInversion_id = tipoInversion_id;
	}
	public int getTipoInversionista_id() {
		return tipoInversionista_id;
	}
	public void setTipoInversionista_id(int tipoInversionista_id) {
		this.tipoInversionista_id = tipoInversionista_id;
	}
	
	

}
