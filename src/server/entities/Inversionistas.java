package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="INVERSIONISTAS")
public class Inversionistas implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="INVERSIONISTA_ID")
	private int inversionista_id;
	@Column(name="NOMBRE")
	private String nombre;
	@Column(name="APELLIDO")
	private String apellido;
	@Column(name="I_PASSWORD")
	private String password;
	@Column(name="DIRECCION")
	private String direccion;
	@Column(name="PAISESCC_FIPS")
	private String pais;
	@Column(name="GENEROGENERO_ID")
	private int genero_id;
	@Column(name="TELEFONO")
	private String telefono;
	@Column(name="E_MAIL")
	private String e_mail;
	@Column(name="FECHA_DE_NACIMIENTO")
	private java.util.Date nacimiento;
	@Column(name="FECHA_DE_CREACION")
	private java.util.Date creacion;
	@Column(name="ULTIMA_FECHA_INGRESO")
	private java.util.Date ultimaFechaIngreso;
	@Column(name="TIPO_INVERSIONISTATIPO_ID")
	private int tipoInversionista_id;
	@Column(name="NIVEL_INVERSIONNIVEL_ID")
	private int nivelInversion_id;
	@Column(name="TIPO_INVERSIONTIPO_ID")
	private int tipoInversion_id;
	@Column(name="PAISESCC_FIPS_INVERSION")
	private String paisInversion;
	@Column(name="ADMINISTRADORESADMINISTRADOR_ID")
	private int administrador_id;
	
	
	public int getInversionista_id() {
		return inversionista_id;
	}
	public void setInversionista_id(int inversionista_id) {
		this.inversionista_id = inversionista_id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public int getGenero_id() {
		return genero_id;
	}
	public void setGenero_id(int genero_id) {
		this.genero_id = genero_id;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getE_mail() {
		return e_mail;
	}
	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}
	public java.util.Date getNacimiento() {
		return nacimiento;
	}
	public void setNacimiento(java.util.Date nacimiento) {
		this.nacimiento = nacimiento;
	}
	public java.util.Date getCreacion() {
		return creacion;
	}
	public void setCreacion(java.util.Date creacion) {
		this.creacion = creacion;
	}
	public java.util.Date getUltimaFechaIngreso() {
		return ultimaFechaIngreso;
	}
	public void setUltimaFechaIngreso(java.util.Date ultimaFechaIngreso) {
		this.ultimaFechaIngreso = ultimaFechaIngreso;
	}
	public int getTipoInversionista_id() {
		return tipoInversionista_id;
	}
	public void setTipoInversionista_id(int tipoInversionista_id) {
		this.tipoInversionista_id = tipoInversionista_id;
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
	public String getPaisInversion() {
		return paisInversion;
	}
	public void setPaisInversion(String paisInversion) {
		this.paisInversion = paisInversion;
	}
	public int getAdministrador_id() {
		return administrador_id;
	}
	public void setAdministrador_id(int administrador_id) {
		this.administrador_id = administrador_id;
	}
	
	

}
