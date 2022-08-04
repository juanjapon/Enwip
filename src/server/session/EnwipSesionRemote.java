package server.session;

import java.util.Vector;

import javax.ejb.*;

import server.entities.Noticias;
import server.entities.Usuarios;

@Remote
public interface EnwipSesionRemote {
	public String login(String user,String password) throws UsuarioException;

	public String activarCuenta(String parameter);

	public String cargarNombre();

	public void remove();

	public Vector<Noticias> cargarNoticias();

	public String cargarPerfil();


	public void actualizarPerfil(String reseña, String nombre,
			String apellidos, String pais, String ciudad, String direccion,
			String telefono, String fecha, int genero, int estudios, int area,
			int tipoInversionista, int tipoInversion, int nivelInversion,
			int reseñap, int paisp, int ciudadp, int direccionp, int generop,
			int telefonop, int emailp, int fechap, int estudiosp);


	public String cargarGrupos();

	public String cargarComentarios(int id);

	public void guardarComentario(int grupoId, String comentario);

	public void crearGrupo(String nombre, String descripcion);

	public String cargarNombreEquipo(String planId);

	public void crearIdea(String nombre,String idea, String nacimiento, String pais,
			String ciudad, String area, String tipoInversion,
			String tipoInversionista, String nivelInversion);

	public String cargarIdeas();

	public String cargarDatosDeIdea(int parseInt);

	public int crearPlan(String ideaPlan);

	public int guardarNombreEquipo(String nombre, String equipo,
			String planId);

	public String cargarResumen(String planId);

	public String cargarCompetencia(String planId);

	public String cargarEmpresa(String planId);

	public String cargarEntradaMercado(String planId);

	public String cargarPrecioPS(String planId);

	public String cargarProduccionServuccion(String planId);

	public String cargarProductosServicios(String planId);

	public String cargarMercado(String planId);

	public String cargarMercadoObjetivo(String planId);

	public String cargarPotencialMercado(String planId);

	public String cargarViabilidadJuridica(String planId);

	public String cargarObligacionesTributarias(String planId);

	public String cargarOtrosLegales(String planId);

	public int guardarCompetencia(String competencia, String planId);

	public int guardarEntradaMercado(String entradaMercado, String planId);

	public int guardarMercadoObjetivo(String mercadoObjetivo, String planId);

	public int guardarPrecioPS(String precioPS, String planId);

	public int guardarProduccionServuccion(String produccionServuccion,
			String planId);

	public int guardarProductosServicios(String productosServicios,
			String planId);

	public int guardarEmpresa(String empresa, String planId);

	public int guardarMercado(String mercado, String planId);

	public int guardarResumen(String resumen, String planId);

	public int guardarPotencialMercado(String potencialMercado, String planId);

	public int guardarViabilidadJuridica(String viabilidadJuridica,
			String planId);

	public int guardarObligacionesTributarias(
			String obligacionesTributarias, String planId);

	public int guardarOtrosLegales(String otrosLegales, String planId);

	public String cargarRiesgosContingencias(String parameter);

	public String guardarRiesgosContingencias(String[][] mRiesgosContingencias,
			String planId);

	public String cargarPlanes();

	public String cargarDatosDePlan(int parseInt);

	public String cargarAmigos();

	public String cargarDatosDeAmigo(int parseInt);

	public String cargarAmigosDeAmigo(int parseInt);

	public String cargarGruposDeAmigo(int id);

	public String cargarIdeasDeAmigo(int id);

	public String cargarMensajes();

	public void enviarMensaje(String para, String asunto, String cuerpo);

	public String cargarMensajesEnviados();

	public String cargarMensaje(int id);

	public String crearPlanFinanciero(String id);

	public String guardarProductos(String[][] productos, String planId);

	public String cargarMediosComunicacion(String parameter);

	public String guardarMedios(String[][] medios, String planId);

	public String cargarInsumosProcesos(String planId);

	public String guardarInsumosProcesos(String[][] insumosProcesos,String planId);

	public String cargarActivosFijos(String planId);

	public String guardarActivosFijos(String[][] activosFijos, String planId);

	public String cargarInversionPreoperativa(String planId);

	public String guardarInversionPreoperativa(
			String[][] inversionPreoperativa, String planId);

	public String cargarCostosFijos(String planId);

	public String guardarCostosFijos(String[][] costosFijos, String planId);

	public String cargarGastosFijosAdministracion(String planId);

	public String guardarGastosFijosAdministracion(
			String[][] gastosFijosAdministracion, String planId);

	public String cargarGastosVentas(String planId);

	public String guardarGastosVentas(String[][] gastosVentas, String planId);

	public String cargarEmpleados(String planId);

	public String guardarEmpleados(String[][] empleados, String planId);

	public String cargarImpuestos(String planId);

	public String guardarImpuestos(String[][] impuestos, String planId);

	public String cargarProyeccion(String planId);

	public String guardarProyeccion(String mes, int ano, int numAnos, int planId);

	public String cargarProyeccionProductos(String planId);

	public int getNumeroDeProductos(String planId);

	public int getNumeroDeAños(String planId);

	public String guardarProyeccionProductos(String[][] proyProductos,
			String planId);

	public String cargarProyeccionGastosFijos(String planId);

	public int getNumeroDeGastosFijos(String planId);

	public String guardarProyeccionGastosFijos(String[][] proyGastosFijos,
			String planId);

	public String cargarProyeccionCostosFijos(String planId);

	public String cargarProyeccionVentas(String planId);

	public String cargarProyeccionEmpleados(String planId);

	public int getNumeroDeCostosFijos(String planId);

	public String guardarProyeccionCostosFijos(String[][] proyCostosFijos,
			String planId);

	public int getNumeroDeVentas(String planId);

	public String guardarProyeccionVentas(String[][] proyVentas, String planId);

	public int getNumeroDeEmpleados(String planId);

	public String guardarProyeccionEmpleados(String[][] proyEmpleados,
			String planId);

	public String cargarCuaderno();

	public String cargarDatosDeCuaderno(int id);

	public void guardarCuaderno(int id, String contenido);

	public String cargarPreguntas();

	public String cargarRespuestaDePregunta(int id);

	public void guardarRespuesta(int id, String respuesta);

	public String cargarMisPreguntas();

	public String guardarPregunta(String pregunta, int area);

	public String cargarClases();

	public String cargarDatosDeClase(int id);

	public String cargarCharlas();

	public String cargarDatosDeCharla(int id);

	public String getNombreId();

	public String getDatosPlanDeNegocio(int planId);

	public void setFoto(String name);

	public String cargarImagen();

	public String cargarDatosIdea(int id);

	public void guardarModificacionIdea(int id, String nombre, String idea,
			String nacimiento, String pais, String ciudad, String area,
			String tipoInversion, String tipoInversionista,
			String nivelInversion);

	public String crearPestana(String nombre);

	public String eliminarCuaderno(int id);

	public String eliminarRiesgoContingencia(String id);

	public String eliminarProducto(String id);

	public String eliminarInsumo(String id);

	public String eliminarInversionPreoperativa(String id);

	public String eliminarActivo(String id);

	public String eliminarCostoFijo(String id);

	public String eliminarGastoFijo(String id);

	public String eliminarMedio(String id);

	public String eliminarGastoVenta(String id);

	public String eliminarEmpleado(String id);

	public String eliminarImpuesto(String id);

	public String cargarFormularioColaborador(int id);

	public void guardarColaborador(int idea, int colaborador, int tipo);

	public String cargarColaboradores();

	public String cargarColaborados();

	public String cargarDatosDeUsuario(int id);

	public String cargarPlanesColaborados(String id);

	public String cargarEdicionPerfil();

	public void guardarComentarioClase(int id, String comentario);

	public void guardarComentarioCharla(int id, String comentario);

	public String votarCharla(int id);

	public String votarClase(int id);

	public String marcarMejor(int id);

	public String cargarRankingRespuestas();

	public String denunciarRespuesta(int id);

	public String cargarDenunciasRespuestas();

	public String cargarVotacionIdeas();

	public String votarIdea(int parseInt);

	public String cargarInvertirIdeas();

	public String invertirIdea(int id, int monto);

	public String buscarUsuarios(String texto);

	public String buscarCharlas(String texto);

	public String buscarClases(String texto);

	public String buscarGrupos(String texto);

	public String buscarPreguntas(String texto);

	public String agregarAmigo(int id);

	public String cargarSolicitudes();

	public String aceptarSolicitud(int id);

	public String rechazarSolicitud(int id);

	public String votarNoCharla(int id);

	public String votarNoClase(int id);

	public String eliminarPlan(String id);

	public String eliminarIdea(String id);





	

	




}
