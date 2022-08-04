package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries( { @NamedQuery(name = "Usuarios.findUsuario", query = "SELECT u FROM Usuarios u WHERE u.eMail =:correo"),
	@NamedQuery(name = "Usuarios.findUsuarioByEmailAndPass", query = "SELECT u FROM Usuarios u WHERE u.eMail =:correo AND u.password= :clave"),
	@NamedQuery(name = "Usuarios.findUsuarios", query = "SELECT u FROM Usuarios u WHERE u.nombre LIKE :texto1 or u.nombre LIKE :texto2 or u.nombre LIKE :texto3 or u.apellido LIKE :texto4  or u.apellido LIKE :texto5  or u.apellido LIKE :texto6"),
	@NamedQuery(name = "Usuarios.findUsuarioReankeadosRespuestas", query = "SELECT u FROM Usuarios u WHERE u.ranking !=0 ORDER BY u.ranking ASC")	
})
@Table(name="USUARIOS")
public class Usuarios implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="USUARIO_ID")
	private int usuario_id;
	@Column(name="NOMBRE")
	private String nombre;
	@Column(name="APELLIDO")
	private String apellido;
	@Column(name="U_PASSWORD")
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
	private String eMail;
	@Column(name="FECHA_DE_NACIMIENTO")
	private java.util.Date fechaNacimiento;
	@Column(name="RESENA_PERSONAL")
	private String reseñaPersonal;
	@Column(name="USUARIO_TIPOTIPO_ID")
	private int tipoUsuario_id;
	@Column(name="ESTUDIO_ID")
	private int estudios_id;
	@Column(name="EXPERIENCIA_LABORALEXPERIENCIA_ID")
	private int experiencia_id;
	@Column(name="BANDERA_ACTIVACION")
	private boolean bandera;
	@Column(name="FOTO")
	private String foto;
	@Column(name="FECHA_DE_CREACION")
	private java.util.Date fechaCreacion;
	@Column(name="ULTIMA_FECHA_INGRESO")
	private java.util.Date ultimaFechaIngreso;
	@Column(name="CALIFICACIONES_PERMITIDAS")
	private int calificacionesPermitidas;
	@Column(name="CAPACIDAD_DE_RESPONDER")
	private boolean capacidadResponder;
	@Column(name="TIPO_INVERSIONISTATIPO_ID")
	private int inversionistaTipo_id;
	@Column(name="NIVEL_INVERSIONNIVEL_ID")
	private int nivelInversion_id;
	@Column(name="TIPO_INVERSIONTIPO_ID")
	private int tipoInversion_id;
	@Column(name="PAISESCC_FIPS_INVERSION")
	private String paisInversion;
	@Column(name="FECHA_USUARIO_PAGO")
	private java.util.Date pagoFecha;
	@Column(name="FECHA_CADUCIDAD_INSCRIPCION")
	private java.util.Date caducidadInscripcionFecha;
	@Column(name="CIUDAD")
	private String ciudad;
	@Column(name="CODIGO_ACTIVACION")
	private String codigoActivacion;
	@Column(name="RANKING_RESPUESTAS")
	private int ranking;
	
	public void sumarRankingRespuestas(){
		ranking++;
	}
	public void restarRankingRespuestas(){
		ranking--;
	}
	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	public int getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
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
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public java.util.Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(java.util.Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getReseñaPersonal() {
		return reseñaPersonal;
	}
	public void setReseñaPersonal(String reseñaPersonal) {
		this.reseñaPersonal = reseñaPersonal;
	}
	public int getTipoUsuario_id() {
		return tipoUsuario_id;
	}
	public void setTipoUsuario_id(int tipoUsuario_id) {
		this.tipoUsuario_id = tipoUsuario_id;
	}
	public int getEstudios_id() {
		return estudios_id;
	}
	public void setEstudios_id(int estudios_id) {
		this.estudios_id = estudios_id;
	}
	public int getExperiencia_id() {
		return experiencia_id;
	}
	public void setExperiencia_id(int experiencia_id) {
		this.experiencia_id = experiencia_id;
	}
	public boolean isBandera() {
		return bandera;
	}
	public void setBandera(boolean bandera) {
		this.bandera = bandera;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public java.util.Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(java.util.Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public java.util.Date getUltimaFechaIngreso() {
		return ultimaFechaIngreso;
	}
	public void setUltimaFechaIngreso(java.util.Date ultimaFechaIngreso) {
		this.ultimaFechaIngreso = ultimaFechaIngreso;
	}
	public int getCalificacionesPermitidas() {
		return calificacionesPermitidas;
	}
	public void setCalificacionesPermitidas(int calificacionesPermitidas) {
		this.calificacionesPermitidas = calificacionesPermitidas;
	}
	public boolean isCapacidadResponder() {
		return capacidadResponder;
	}
	public void setCapacidadResponder(boolean capacidadResponder) {
		this.capacidadResponder = capacidadResponder;
	}
	public int getInversionistaTipo_id() {
		return inversionistaTipo_id;
	}
	public void setInversionistaTipo_id(int inversionistaTipo_id) {
		this.inversionistaTipo_id = inversionistaTipo_id;
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
	public java.util.Date getPagoFecha() {
		return pagoFecha;
	}
	public void setPagoFecha(java.util.Date pagoFecha) {
		this.pagoFecha = pagoFecha;
	}
	public java.util.Date getCaducidadInscripcionFecha() {
		return caducidadInscripcionFecha;
	}
	public void setCaducidadInscripcionFecha(
			java.util.Date caducidadInscripcionFecha) {
		this.caducidadInscripcionFecha = caducidadInscripcionFecha;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getCodigoActivacion() {
		return codigoActivacion;
	}
	public void setCodigoActivacion(String codigoActivacion) {
		this.codigoActivacion = codigoActivacion;
	}
	

}
