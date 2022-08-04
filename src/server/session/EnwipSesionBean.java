package server.session;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.ejb.*;
import javax.persistence.*;

import server.entities.*;


@Stateful
public class EnwipSesionBean implements EnwipSesionRemote{

	@PersistenceContext(unitName = "Enwip")
	private EntityManager manager;
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	private Usuarios usuario;
	
	
	
	public String login(String user, String password) throws UsuarioException {
		String listo="encontrado";
		
		Query query=manager.createNamedQuery("Usuarios.findUsuarioByEmailAndPass");
		query.setParameter("correo", user);
		System.out.println(user+" en bean");
		query.setParameter("clave", password);
		 List usuarioz=query.getResultList();
		 if(usuarioz.size()==0){
			 throw new UsuarioException("Correo y Contraseña incorrectos");
		 }
		
		  usuario=(Usuarios) (usuarioz.get(0));
		 	 
		 if(!usuario.isBandera()){
			 listo="falta activar";
		 }
		
		
		return listo;
	}
	
	@Remove
	public void remove(){
		System.out.println("estamos en el remove del bean");
	}


	public String activarCuenta(String clave) {
		
		String res="listo";
		
		 
		 if(usuario.getCodigoActivacion().equals(clave)){
			usuario.setBandera(true);
			manager.merge(usuario);
			Privacidad privacidad = new Privacidad();
			
			privacidad.setApellido(1);
			privacidad.setCiudad(3);
			privacidad.setDireccion(3);
			privacidad.seteMail(3);
			privacidad.setEstudios(2);
			privacidad.setFechaNacimiento(3);
			privacidad.setGenero(2);
			privacidad.setNombre(1);
			privacidad.setPais(2);
			privacidad.setReseñaPersonal(2);
			privacidad.setTelefono(3);
			privacidad.setUsuario_id(usuario.getUsuario_id());
			
			manager.persist(privacidad);
		 }
		 else{
			 res="El codigo no es correcto";
			 remove();
		 }
		 
		 
		return res;
	}

	
	public String cargarNombre() {
		
		return usuario.getNombre()+" "+usuario.getApellido();
	}

	
	public Vector<Noticias> cargarNoticias() {
		List liste= manager.createNamedQuery("Noticias.findAll").getResultList();
		
		Vector<Noticias> erg = new Vector<Noticias>();
		for (Object o : liste) {
			Noticias noticia = (Noticias) o;
			
			erg.add(noticia);

		}
		
		return erg;
	}

	
	public String cargarPerfil() {
		StringBuilder objSecciones;
        //Crea un array de objetos JSON con cada tema de la tabla
          objSecciones = new StringBuilder("[");    
          objSecciones.append("{\"Ciudad\":\"");
          objSecciones.append(usuario.getCiudad());
          objSecciones.append("\",\"Pais\":\"");
          
          Query query=manager.createNamedQuery("Paises.findById");
  		  query.setParameter("id", usuario.getPais());
  		  
  		  
  		 List paises=query.getResultList();

  		
  		  Paises pais=(Paises) (paises.get(0));
  		  
          objSecciones.append(pais.getNombre());
          objSecciones.append("\",\"Direccion\":\"");
          objSecciones.append(usuario.getDireccion());
          objSecciones.append("\",\"Genero\":\"");
          objSecciones.append((usuario.getGenero_id()==1?"Mujer":"Hombre"));
          objSecciones.append("\",\"Telefono\":\"");
          objSecciones.append(usuario.getTelefono());
          objSecciones.append("\",\"Nombre\":\"");
          objSecciones.append(usuario.getNombre());
          objSecciones.append("\",\"Apellido\":\"");
          objSecciones.append(usuario.getApellido());
          objSecciones.append("\",\"Foto\":\"");
          objSecciones.append(usuario.getFoto());
          objSecciones.append("\",\"Reseña\":\"");
          objSecciones.append(usuario.getReseñaPersonal());
          objSecciones.append("\",\"EMail\":\"");
          objSecciones.append(usuario.geteMail());
          objSecciones.append("\",\"Experiencia\":\"");
          objSecciones.append(manager.find(Areas_De_Estudio.class, usuario.getExperiencia_id()).getNombre());
          objSecciones.append("\",\"TipoInversionista\":\"");
          if(usuario.getInversionistaTipo_id()!=0){
          objSecciones.append(manager.find(Tipo_Inversionista.class, usuario.getInversionistaTipo_id()).getNombre());
          }
          else{
              objSecciones.append("null");
              }
          objSecciones.append("\",\"TipoInversion\":\"");
          if(usuario.getTipoInversion_id()!=0){
          objSecciones.append(manager.find(Tipo_Inversion.class, usuario.getTipoInversion_id()).getNombre());
          }
          else{
              objSecciones.append("null");
              }
          objSecciones.append("\",\"NivelInversion\":\"");
          if(usuario.getNivelInversion_id()!=0){
          objSecciones.append(manager.find(Nivel_Inversion.class, usuario.getNivelInversion_id()).getRango());
          }
          else{
              objSecciones.append("null");
              }
          objSecciones.append("\",\"Fecha\":\"");
          objSecciones.append( dateFormat.format(usuario.getFechaNacimiento()).toString().substring(0, 10));
          
         query=manager.createNamedQuery("Estudios.findById");
  		  query.setParameter("id", usuario.getEstudios_id());
  		  
  		  
  		 List estudios=query.getResultList();

  		
  		  Estudios estudio=(Estudios) (estudios.get(0));
          
          objSecciones.append("\",\"Estudio\":\"");
          objSecciones.append(estudio.getEstudio());
          objSecciones.append("\"},");
                   
          

                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}

	
	public void actualizarPerfil(String reseña, String nombre,
			String apellidos, String pais, String ciudad, String direccion,
			String telefono, String fecha, int genero, int estudios, int area,
			int tipoInversionista, int tipoInversion, int nivelInversion, int reseñap, int paisp,
			int ciudadp, int direccionp, int generop, int telefonop,
			int emailp, int fechap, int estudiosp) {
		Privacidad privacidad=manager.find(Privacidad.class, usuario.getUsuario_id());
		
		if(privacidad==null){
		privacidad = new Privacidad();
		}
		privacidad.setApellido(1);
		privacidad.setCiudad(ciudadp);
		privacidad.setDireccion(direccionp);
		privacidad.seteMail(emailp);
		privacidad.setEstudios(estudiosp);
		privacidad.setFechaNacimiento(fechap);
		privacidad.setGenero(generop);
		privacidad.setNombre(1);
		privacidad.setPais(paisp);
		privacidad.setReseñaPersonal(reseñap);
		privacidad.setTelefono(telefonop);
		privacidad.setUsuario_id(usuario.getUsuario_id());
		System.out.println("reseña en bean "+reseña);
		usuario.setReseñaPersonal(reseña);
		usuario.setNombre(nombre);
		usuario.setApellido(apellidos);
		usuario.setPais(pais);
		usuario.setCiudad(ciudad);
		usuario.setDireccion(direccion);
		usuario.setTelefono(telefono);
		try {
			Date fechanacimiento=dateFormat.parse(fecha);
			usuario.setFechaNacimiento(fechanacimiento);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		usuario.setGenero_id(genero);
		usuario.setEstudios_id(estudios);
		usuario.setExperiencia_id(area);
		usuario.setTipoInversion_id(tipoInversion);
		usuario.setInversionistaTipo_id(tipoInversionista);
		usuario.setNivelInversion_id(nivelInversion);
		manager.persist(privacidad);
		//manager.persist(usuario);
		manager.merge(usuario);
		
		
		
	}

	


	
	public String cargarGrupos() {
		
		StringBuilder objSecciones;
		
		Query query=manager.createNamedQuery("Grupos_Usuarios.findMyId");
		  query.setParameter("id", usuario.getUsuario_id());
		  
		  List liste=query.getResultList();
		 Vector<Grupos_Usuarios> grupos=new Vector<Grupos_Usuarios>();
		 
		 
			for (Object o : liste) {
				Grupos_Usuarios grupo = (Grupos_Usuarios) o;
				
				grupos.add(grupo);

			}
		
		
		Grupos auxiliar;
        //Crea un array de objetos JSON con cada tema de la tabla
          objSecciones = new StringBuilder("[");  
          for(int i=0;i<grupos.size();i++){
          objSecciones.append("{\"Id\":\"");
          auxiliar=manager.find(Grupos.class,(grupos.get(i).getGrupo_id()));
          objSecciones.append(auxiliar.getGrupo_id());        
          objSecciones.append("\",\"Nombre\":\"");
          objSecciones.append(auxiliar.getNombre());
          objSecciones.append("\"},");
          }
          

                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}

	
	public String cargarComentarios(int id) {
StringBuilder objSecciones;
		
		Query query=manager.createNamedQuery("Comentarios.findByGrupo");
		  query.setParameter("id", id);
		  
		  List liste=query.getResultList();
		 Vector<Comentarios> comentarios=new Vector<Comentarios>();
		 
		 
			for (Object o : liste) {
				Comentarios comentario = (Comentarios) o;
				
				comentarios.add(comentario);

			}
			
		
		
		Grupos grupo=manager.find(Grupos.class, id);
        //Crea un array de objetos JSON con cada tema de la tabla
          objSecciones = new StringBuilder("[");  
          for(int i=0;i<comentarios.size();i++){
          objSecciones.append("{\"Grupo\":\"");
          objSecciones.append(grupo.getNombre());
          objSecciones.append("\",\"Id\":\"");
          objSecciones.append(id);
          objSecciones.append("\",\"Comentario\":\"");
          objSecciones.append(comentarios.get(i).getContenida());
          objSecciones.append("\",\"Fecha\":\"");
          objSecciones.append(comentarios.get(i).getFecha().toString().substring(0,10));
          objSecciones.append("\",\"usuario\":\"");
          objSecciones.append(manager.find(Usuarios.class, (comentarios.get(i).getUsuario_id())).getNombre()+" "+manager.find(Usuarios.class, (comentarios.get(i).getUsuario_id())).getApellido());
          objSecciones.append("\"},");
          }
          
          if(comentarios.size()==0){
        	  objSecciones.append("{\"Grupo\":\"");
              objSecciones.append(grupo.getNombre());  
              objSecciones.append("\",\"Id\":\"");
              objSecciones.append(id);
              objSecciones.append("\",\"Comentario\":\"");
              objSecciones.append("");
              objSecciones.append("\",\"Fecha\":\"");
              objSecciones.append("");
              objSecciones.append("\",\"usuario\":\"");
              objSecciones.append("");
              objSecciones.append("\"},");
          }

                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}


	public void guardarComentario(int grupoId, String comentario) {
		Comentarios comentarios=new Comentarios();
		comentarios.setContenida(comentario.replace("\n", "<br>"));
		comentarios.setFecha(new Date());
		comentarios.setGrupo_id(grupoId);
		comentarios.setUsuario_id(usuario.getUsuario_id());
		manager.merge(comentarios);
		
	}

	
	public void crearGrupo(String nombre, String descripcion) {
		Grupos grupo=new Grupos();
		grupo.setDescripcion(descripcion.replace("\n", "<br>"));
		grupo.setNombre(nombre);
		grupo.setUsuarioAdministrador_id(usuario.getUsuario_id());

		manager.persist(grupo);

		 Query query=manager.createNamedQuery("Grupos.findByNombreDescripcion");
 		  query.setParameter("nombre", grupo.getNombre());
 		  query.setParameter("descripcion", grupo.getDescripcion());
 		  
 		  
 		 List grups=query.getResultList();

 		
 		  grupo=(Grupos) (grups.get(0));
		System.out.println(grupo.getGrupo_id()+" este si es el id");
		Grupos_Usuarios gus=new Grupos_Usuarios();
		gus.setGrupo_id(grupo.getGrupo_id());
		gus.setUsuario_id(usuario.getUsuario_id());
		manager.merge(gus);
		
		
	}


	public String cargarNombreEquipo(String planId) {
		StringBuilder objSecciones;
		
		Planes_De_Negocios plan=manager.find(Planes_De_Negocios.class, Integer.parseInt(planId));
		
		
		
        //Crea un array de objetos JSON con cada tema de la tabla
          objSecciones = new StringBuilder("[");    
          
          
          Query query=manager.createNamedQuery("Datos_Planes_De_Negocios.findById");
  		  query.setParameter("id", 1);
  		  
  		  
  		 List datosr=query.getResultList();

  		
  		Datos_Planes_De_Negocios datos=(Datos_Planes_De_Negocios) (datosr.get(0));
  		  
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("datosNombre");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(datos.getNombre());
          objSecciones.append("\"},");
          
         objSecciones.append("{\"dato\":\"");  		  
          objSecciones.append("datosEquipo");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(datos.getEquipo());
          objSecciones.append("\"},");
          
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("Nombre");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(plan.getNombre());
          objSecciones.append("\"},");
          
         objSecciones.append("{\"dato\":\"");  		  
          objSecciones.append("Equipo");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(plan.getEquipo());

          objSecciones.append("\"},");
          
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("planId");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(plan.getPlan_id());
          objSecciones.append("\"},");
                   
          

                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}


	public void crearIdea(String nombre,String idea, String nacimiento, String pais,
			String ciudad, String area, String tipoInversion,
			String tipoInversionista, String nivelInversion) {
		
			Idea_De_Negocio ideaDeNegocio=new Idea_De_Negocio();
			
			Query query=manager.createNamedQuery("Idea_De_Negocio.findAll");
			 System.out.println(pais);
			 List ideas=query.getResultList();
			 
			 Vector<Idea_De_Negocio> ideasV=new Vector<Idea_De_Negocio>();
			 
			 
				for (Object o : ideas) {
					Idea_De_Negocio ideaa = (Idea_De_Negocio) o;
					
					ideasV.add(ideaa);

				}
				int mayor=0;
				for(int i=0;i<ideasV.size();i++){
					if(mayor<=ideasV.get(i).getIdea_id()){
						mayor=ideasV.get(i).getIdea_id();
					}
				}
					
			 
		
		int inversionistaId;
		int tipoInversionId;
		int nivelInversionId;
		int areaId;
		
		
		
		
		
		
		 query=manager.createNamedQuery("Paises.findByNombre");
		 query.setParameter("nombre", pais);
		 System.out.println(pais);
		 List paises=query.getResultList();
		 Paises paisO=(Paises) (paises.get(0));
		 pais=paisO.getFips();
		 
		 
		 query=manager.createNamedQuery("Areas_De_Estudio.findByNombre");
		 query.setParameter("nombre", area);
		 System.out.println(area);
		 List areas=query.getResultList();
		 Areas_De_Estudio areaa=(Areas_De_Estudio) (areas.get(0));
		 areaId=areaa.getAreaEstudio_id();
		 

			
			ideaDeNegocio.setIdea_id(mayor+1);
			ideaDeNegocio.setNombre(nombre.replace("\n", "<br>"));
			ideaDeNegocio.setIdea(idea.replace("\n", "<br>"));
			ideaDeNegocio.setNacimiento(nacimiento.replace("\n", "<br>"));
			ideaDeNegocio.setCiudad(ciudad);
			ideaDeNegocio.setPais(pais);
			ideaDeNegocio.setAreaEstudio_id(areaId);
			
		 query=manager.createNamedQuery("Tipo_Inversionista.findByNombre");
		 query.setParameter("nombre", tipoInversionista);
		 List inversionistas=query.getResultList();
		 Tipo_Inversionista inversionista=(Tipo_Inversionista) (inversionistas.get(0));
		 inversionistaId=inversionista.getTipo_id();
		 ideaDeNegocio.setTipoInversionista_id(inversionistaId);
		 query=manager.createNamedQuery("Tipo_Inversion.findByNombre");
		 query.setParameter("nombre", tipoInversion);
		 List inversion=query.getResultList();
		 Tipo_Inversion inversionO=(Tipo_Inversion) (inversion.get(0));
		 tipoInversionId=inversionO.getTipo_id();
		 ideaDeNegocio.setTipoInversion_id(tipoInversionId);
		 query=manager.createNamedQuery("Nivel_Inversion.findByNombre");
		 query.setParameter("nombre", nivelInversion);
		 List nivelesInversion=query.getResultList();
		 Nivel_Inversion nInversion=(Nivel_Inversion) (nivelesInversion.get(0));
		 nivelInversionId=nInversion.getNivel_id();
		 ideaDeNegocio.setNivelInversion_id(nivelInversionId);
		 
		 
		
		manager.persist(ideaDeNegocio);
		
		
		Idea_De_Negocio_Usuarios ideaUsuario=new Idea_De_Negocio_Usuarios();
		ideaUsuario.setIdea_id(ideaDeNegocio.getIdea_id());
		ideaUsuario.setUsuario_id(usuario.getUsuario_id());
		manager.persist(ideaUsuario);
		
	}

	
	public String cargarIdeas() {
        StringBuilder objSecciones;
		
		Query query=manager.createNamedQuery("Idea_De_Negocio_Usuarios.findMyId");
		  query.setParameter("id", usuario.getUsuario_id());
		  
		  List liste=query.getResultList();
		 Vector<Idea_De_Negocio_Usuarios> ideas=new Vector<Idea_De_Negocio_Usuarios>();
		 
		 
			for (Object o : liste) {
				Idea_De_Negocio_Usuarios idea = (Idea_De_Negocio_Usuarios) o;
				
				ideas.add(idea);

			}
		
		
			Idea_De_Negocio auxiliar;
        //Crea un array de objetos JSON con cada tema de la tabla
          objSecciones = new StringBuilder("[");  
          for(int i=0;i<ideas.size();i++){
          objSecciones.append("{\"Id\":\"");
          auxiliar=manager.find(Idea_De_Negocio.class,(ideas.get(i).getIdea_id()));
          objSecciones.append(auxiliar.getIdea_id());        
          objSecciones.append("\",\"Nombre\":\"");
          objSecciones.append(auxiliar.getNombre());
          objSecciones.append("\"},");
          }
          

                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}

	
	public String cargarDatosDeIdea(int id) {
StringBuilder objSecciones;
		
			
		
		
		Idea_De_Negocio idea=manager.find(Idea_De_Negocio.class, id);
        //Crea un array de objetos JSON con cada tema de la tabla
          objSecciones = new StringBuilder("[");  
         
          objSecciones.append("{\"Nombre\":\"");
          objSecciones.append(idea.getNombre());
          objSecciones.append("\",\"Id\":\"");
          objSecciones.append(idea.getIdea_id());
          objSecciones.append("\",\"Idea\":\"");
          objSecciones.append(idea.getIdea());
          objSecciones.append("\",\"Nacimiento\":\"");
          objSecciones.append(idea.getNacimiento());
          objSecciones.append("\",\"Pais\":\"");
          objSecciones.append(manager.find(Paises.class, idea.getPais()).getNombre());
          objSecciones.append("\",\"Ciudad\":\"");
          objSecciones.append(idea.getCiudad());
          objSecciones.append("\",\"Votos\":\"");
          objSecciones.append(idea.getCalificacion());
          objSecciones.append("\",\"Inversion\":\"");
          objSecciones.append(idea.getInversionRecogida());
          objSecciones.append("\",\"Votado\":\"");
          if(manager.find(Usuarios_Votacion_Ideas.class, new Usuarios_Votacion_IdeasPK(usuario.getUsuario_id(),id))==null){
          	objSecciones.append(false);
          }
          else{
          	objSecciones.append(true);
          }
          objSecciones.append("\",\"Area\":\"");
          objSecciones.append(manager.find(Areas_De_Estudio.class, idea.getAreaEstudio_id()).getNombre());
          objSecciones.append("\",\"TipoInversionista\":\"");
          objSecciones.append(manager.find(Tipo_Inversionista.class, idea.getTipoInversionista_id()).getNombre());
          objSecciones.append("\",\"TipoInversion\":\"");
          objSecciones.append(manager.find(Tipo_Inversion.class, idea.getTipoInversion_id()).getNombre());
          objSecciones.append("\",\"NivelInversion\":\"");
          objSecciones.append(manager.find(Nivel_Inversion.class, idea.getNivelInversion_id()).getRango());
          objSecciones.append("\"},");
          
          


                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}

	
	public int crearPlan(String ideaPlan) {
		
		//actividad
		Planes_De_Negocios plan=new Planes_De_Negocios();
		plan.setIdea_id(Integer.parseInt(ideaPlan));
		plan.setFechaInicio(new Date());
		plan.setNombre("");
		plan.setEquipo("");
		plan.setResumenEjecutivo("");
		plan.setMercado("");
		plan.setCompetencia("");
		plan.setEmpresa("");
		plan.setProductosServicios("");
		plan.setProduccionServuccion("");
		plan.setPrecioProductosOServicios("");
		plan.setMercadoObjetivo("");
		plan.setEntradaMercado("");
		plan.setPotencialMercado("");
		plan.setViabilidadJuridica("");
		plan.setObligacionesTributarias("");
		plan.setOtrosLegales("");
		plan.setPlanTerminado(false);
		Query query=manager.createNamedQuery("Planes_De_Negocios.findAll");
		 
		 List planes=query.getResultList();
		 Vector<Planes_De_Negocios> planesV=new Vector<Planes_De_Negocios>();
		 
		 
			for (Object o : planes) {
				Planes_De_Negocios ideaa = (Planes_De_Negocios) o;
				
				planesV.add(ideaa);

			}
			int mayor=0;
			if(planesV.size()>0)
			for(int i=0;i<planesV.size();i++){
				if(mayor<=planesV.get(i).getPlan_id()){
					mayor=planesV.get(i).getPlan_id();
				}
			}
			
			else{
				mayor=-1;
			}
			plan.setPlan_id(mayor+1);
			manager.persist(plan);
			Usuarios_Planes_De_Negocios planUsuario=new Usuarios_Planes_De_Negocios();
			planUsuario.setPlan_id(plan.getPlan_id());
			planUsuario.setUsuario_id(usuario.getUsuario_id());
			manager.persist(planUsuario);
			return plan.getPlan_id();
		
	}

	
	public int guardarNombreEquipo(String nombre, String equipo,
			String planId) {
		Planes_De_Negocios plan=manager.find(Planes_De_Negocios.class, Integer.parseInt(planId));
		plan.setNombre(nombre.replace("\n", "<br>"));
		plan.setEquipo(equipo.replace("\n", "<br>"));
		manager.persist(plan);
		
		return plan.getPlan_id();
	}

	
	public String cargarResumen(String planId) {
    StringBuilder objSecciones;
		
		Planes_De_Negocios plan=manager.find(Planes_De_Negocios.class, Integer.parseInt(planId));
		
		
		
        //Crea un array de objetos JSON con cada tema de la tabla
          objSecciones = new StringBuilder("[");    
          
          
          Query query=manager.createNamedQuery("Datos_Planes_De_Negocios.findById");
  		  query.setParameter("id", 1);
  		  
  		  
  		 List datosr=query.getResultList();

  		
  		Datos_Planes_De_Negocios datos=(Datos_Planes_De_Negocios) (datosr.get(0));
  		  
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("datosResumen");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(datos.getResumenEjecutivo());
          objSecciones.append("\"},");
          
          
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("Resumen");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(plan.getResumenEjecutivo());
          objSecciones.append("\"},");
          
          
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("planId");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(plan.getPlan_id());
          objSecciones.append("\"},");
                   
          

                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}

	
	public String cargarCompetencia(String planId) {
StringBuilder objSecciones;
		
		Planes_De_Negocios plan=manager.find(Planes_De_Negocios.class, Integer.parseInt(planId));
		
		
		
        //Crea un array de objetos JSON con cada tema de la tabla
          objSecciones = new StringBuilder("[");    
          
          
          Query query=manager.createNamedQuery("Datos_Planes_De_Negocios.findById");
  		  query.setParameter("id", 1);
  		  
  		  
  		 List datosr=query.getResultList();

  		
  		Datos_Planes_De_Negocios datos=(Datos_Planes_De_Negocios) (datosr.get(0));
  		  
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("datosCompetencia");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(datos.getCompetencia());
          objSecciones.append("\"},");
          
          
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("Competencia");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(plan.getCompetencia());
          objSecciones.append("\"},");
          
          
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("planId");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(plan.getPlan_id());
          objSecciones.append("\"},");
                   
          

                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}

	
	public String cargarEmpresa(String planId) {
StringBuilder objSecciones;
		
		Planes_De_Negocios plan=manager.find(Planes_De_Negocios.class, Integer.parseInt(planId));
		
		
		
        //Crea un array de objetos JSON con cada tema de la tabla
          objSecciones = new StringBuilder("[");    
          
          
          Query query=manager.createNamedQuery("Datos_Planes_De_Negocios.findById");
  		  query.setParameter("id", 1);
  		  
  		  
  		 List datosr=query.getResultList();

  		
  		Datos_Planes_De_Negocios datos=(Datos_Planes_De_Negocios) (datosr.get(0));
  		  
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("datosEmpresa");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(datos.getEmpresa());
          objSecciones.append("\"},");
          
          
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("Empresa");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(plan.getEmpresa());
          objSecciones.append("\"},");
          
          
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("planId");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(plan.getPlan_id());
          objSecciones.append("\"},");
                   
          

                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}

	
	public String cargarEntradaMercado(String planId) {
		
StringBuilder objSecciones;
		
		Planes_De_Negocios plan=manager.find(Planes_De_Negocios.class, Integer.parseInt(planId));
		
		
		
        //Crea un array de objetos JSON con cada tema de la tabla
          objSecciones = new StringBuilder("[");    
          
          
          Query query=manager.createNamedQuery("Datos_Planes_De_Negocios.findById");
  		  query.setParameter("id", 1);
  		  
  		  
  		 List datosr=query.getResultList();

  		
  		Datos_Planes_De_Negocios datos=(Datos_Planes_De_Negocios) (datosr.get(0));
  		  
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("datosEntradaMercado");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(datos.getEntradaMercado());
          objSecciones.append("\"},");
          
          
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("EntradaMercado");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(plan.getEntradaMercado());
          objSecciones.append("\"},");
          
          
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("planId");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(plan.getPlan_id());
          objSecciones.append("\"},");
                   
          

                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}

	
	public String cargarPrecioPS(String planId) {
		
StringBuilder objSecciones;
		
		Planes_De_Negocios plan=manager.find(Planes_De_Negocios.class, Integer.parseInt(planId));
		
		
		
        //Crea un array de objetos JSON con cada tema de la tabla
          objSecciones = new StringBuilder("[");    
          
          
          Query query=manager.createNamedQuery("Datos_Planes_De_Negocios.findById");
  		  query.setParameter("id", 1);
  		  
  		  
  		 List datosr=query.getResultList();

  		
  		Datos_Planes_De_Negocios datos=(Datos_Planes_De_Negocios) (datosr.get(0));
  		  
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("datosPrecioPS");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(datos.getPrecioProductosOServicios());
          objSecciones.append("\"},");
          
          
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("PrecioPS");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(plan.getPrecioProductosOServicios());
          objSecciones.append("\"},");
          
          
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("planId");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(plan.getPlan_id());
          objSecciones.append("\"},");
                   
          

                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}

	
	public String cargarProduccionServuccion(String planId) {
		
StringBuilder objSecciones;
		
		Planes_De_Negocios plan=manager.find(Planes_De_Negocios.class, Integer.parseInt(planId));
		
		
		
        //Crea un array de objetos JSON con cada tema de la tabla
          objSecciones = new StringBuilder("[");    
          
          
          Query query=manager.createNamedQuery("Datos_Planes_De_Negocios.findById");
  		  query.setParameter("id", 1);
  		  
  		  
  		 List datosr=query.getResultList();

  		
  		Datos_Planes_De_Negocios datos=(Datos_Planes_De_Negocios) (datosr.get(0));
  		  
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("datosProduccionServuccion");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(datos.getProduccionServuccion());
          objSecciones.append("\"},");
          
          
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("ProduccionServuccion");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(plan.getProduccionServuccion());
          objSecciones.append("\"},");
          
          
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("planId");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(plan.getPlan_id());
          objSecciones.append("\"},");
                   
          

                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}

	
	public String cargarProductosServicios(String planId) {
		
StringBuilder objSecciones;
		
		Planes_De_Negocios plan=manager.find(Planes_De_Negocios.class, Integer.parseInt(planId));
		
		
		
        //Crea un array de objetos JSON con cada tema de la tabla
          objSecciones = new StringBuilder("[");    
          
          
          Query query=manager.createNamedQuery("Datos_Planes_De_Negocios.findById");
  		  query.setParameter("id", 1);
  		  
  		  
  		 List datosr=query.getResultList();

  		
  		Datos_Planes_De_Negocios datos=(Datos_Planes_De_Negocios) (datosr.get(0));
  		  
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("datosProductosServicios");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(datos.getProductosServicios());
          objSecciones.append("\"},");
          
          
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("ProductosServicios");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(plan.getProductosServicios());
          objSecciones.append("\"},");
          
          
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("planId");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(plan.getPlan_id());
          objSecciones.append("\"},");
                   
          

                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}

	
	public String cargarMercado(String planId) {
		
StringBuilder objSecciones;
		
		Planes_De_Negocios plan=manager.find(Planes_De_Negocios.class, Integer.parseInt(planId));
		
		
		
        //Crea un array de objetos JSON con cada tema de la tabla
          objSecciones = new StringBuilder("[");    
          
          
          Query query=manager.createNamedQuery("Datos_Planes_De_Negocios.findById");
  		  query.setParameter("id", 1);
  		  
  		  
  		 List datosr=query.getResultList();

  		
  		Datos_Planes_De_Negocios datos=(Datos_Planes_De_Negocios) (datosr.get(0));
  		  
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("datosMercado");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(datos.getMercado());
          objSecciones.append("\"},");
          
          
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("Mercado");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(plan.getMercado());
          objSecciones.append("\"},");
          
          
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("planId");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(plan.getPlan_id());
          objSecciones.append("\"},");
                   
          

                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}

	
	public String cargarMercadoObjetivo(String planId) {
		
StringBuilder objSecciones;
		
		Planes_De_Negocios plan=manager.find(Planes_De_Negocios.class, Integer.parseInt(planId));
		
		
		
        //Crea un array de objetos JSON con cada tema de la tabla
          objSecciones = new StringBuilder("[");    
          
          
          Query query=manager.createNamedQuery("Datos_Planes_De_Negocios.findById");
  		  query.setParameter("id", 1);
  		  
  		  
  		 List datosr=query.getResultList();

  		
  		Datos_Planes_De_Negocios datos=(Datos_Planes_De_Negocios) (datosr.get(0));
  		  
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("datosMercadoObjetivo");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(datos.getMercadoObjetivo());
          objSecciones.append("\"},");
          
          
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("MercadoObjetivo");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(plan.getMercadoObjetivo());
          objSecciones.append("\"},");
          
          
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("planId");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(plan.getPlan_id());
          objSecciones.append("\"},");
                   
          

                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}

	
	public String cargarPotencialMercado(String planId) {
		
StringBuilder objSecciones;
		
		Planes_De_Negocios plan=manager.find(Planes_De_Negocios.class, Integer.parseInt(planId));
		
		
		
        //Crea un array de objetos JSON con cada tema de la tabla
          objSecciones = new StringBuilder("[");    
          
          
          Query query=manager.createNamedQuery("Datos_Planes_De_Negocios.findById");
  		  query.setParameter("id", 1);
  		  
  		  
  		 List datosr=query.getResultList();

  		
  		Datos_Planes_De_Negocios datos=(Datos_Planes_De_Negocios) (datosr.get(0));
  		  
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("datosPotencialMercado");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(datos.getPotencialMercado());
          objSecciones.append("\"},");
          
          
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("PotencialMercado");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(plan.getPotencialMercado());
          objSecciones.append("\"},");
          
          
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("planId");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(plan.getPlan_id());
          objSecciones.append("\"},");
                   
          

                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}

	
	public String cargarViabilidadJuridica(String planId) {
		
StringBuilder objSecciones;
		
		Planes_De_Negocios plan=manager.find(Planes_De_Negocios.class, Integer.parseInt(planId));
		
		
		
        //Crea un array de objetos JSON con cada tema de la tabla
          objSecciones = new StringBuilder("[");    
          
          
          Query query=manager.createNamedQuery("Datos_Planes_De_Negocios.findById");
  		  query.setParameter("id", 1);
  		  
  		  
  		 List datosr=query.getResultList();

  		
  		Datos_Planes_De_Negocios datos=(Datos_Planes_De_Negocios) (datosr.get(0));
  		  
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("datosViabilidadJuridica");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(datos.getViabilidadJuridica());
          objSecciones.append("\"},");
          
          
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("ViabilidadJuridica");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(plan.getViabilidadJuridica());
          objSecciones.append("\"},");
          
          
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("planId");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(plan.getPlan_id());
          objSecciones.append("\"},");
                   
          

                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}

	
	public String cargarObligacionesTributarias(String planId) {
		
StringBuilder objSecciones;
		
		Planes_De_Negocios plan=manager.find(Planes_De_Negocios.class, Integer.parseInt(planId));
		
		
		
        //Crea un array de objetos JSON con cada tema de la tabla
          objSecciones = new StringBuilder("[");    
          
          
          Query query=manager.createNamedQuery("Datos_Planes_De_Negocios.findById");
  		  query.setParameter("id", 1);
  		  
  		  
  		 List datosr=query.getResultList();

  		
  		Datos_Planes_De_Negocios datos=(Datos_Planes_De_Negocios) (datosr.get(0));
  		  
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("datosObligacionesTributarias");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(datos.getObligacionesTributarias());
          objSecciones.append("\"},");
          
          
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("ObligacionesTributarias");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(plan.getObligacionesTributarias());
          objSecciones.append("\"},");
          
          
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("planId");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(plan.getPlan_id());
          objSecciones.append("\"},");
                   
          

                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}

	
	public String cargarOtrosLegales(String planId) {
		
StringBuilder objSecciones;
		
		Planes_De_Negocios plan=manager.find(Planes_De_Negocios.class, Integer.parseInt(planId));
		
		
		
        //Crea un array de objetos JSON con cada tema de la tabla
          objSecciones = new StringBuilder("[");    
          
          
          Query query=manager.createNamedQuery("Datos_Planes_De_Negocios.findById");
  		  query.setParameter("id", 1);
  		  
  		  
  		 List datosr=query.getResultList();

  		
  		Datos_Planes_De_Negocios datos=(Datos_Planes_De_Negocios) (datosr.get(0));
  		  
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("datosOtrosLegales");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(datos.getOtrosLegales());
          objSecciones.append("\"},");
          
          
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("OtrosLegales");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(plan.getOtrosLegales());
          objSecciones.append("\"},");
          
          
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("planId");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(plan.getPlan_id());
          objSecciones.append("\"},");
                   
          

                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}

	
	public int guardarCompetencia(String competencia, String planId) {
		
		Planes_De_Negocios plan=manager.find(Planes_De_Negocios.class, Integer.parseInt(planId));
		plan.setCompetencia(competencia.replace("\n", "<br>"));
		
		manager.persist(plan);
		
		return plan.getPlan_id();
	}

	
	public int guardarEntradaMercado(String entradaMercado, String planId) {
		
		Planes_De_Negocios plan=manager.find(Planes_De_Negocios.class, Integer.parseInt(planId));
		plan.setEntradaMercado(entradaMercado.replace("\n", "<br>"));
		
		manager.persist(plan);
		
		return plan.getPlan_id();
	}

	
	public int guardarMercadoObjetivo(String mercadoObjetivo, String planId) {
		
		Planes_De_Negocios plan=manager.find(Planes_De_Negocios.class, Integer.parseInt(planId));
		plan.setMercadoObjetivo(mercadoObjetivo.replace("\n", "<br>"));
		
		manager.persist(plan);
		
		return plan.getPlan_id();
	}

	
	public int guardarPrecioPS(String precioPS, String planId) {
		
		Planes_De_Negocios plan=manager.find(Planes_De_Negocios.class, Integer.parseInt(planId));
		plan.setPrecioProductosOServicios(precioPS.replace("\n", "<br>"));
		
		manager.persist(plan);
		
		return plan.getPlan_id();
	}

	
	public int guardarProduccionServuccion(String produccionServuccion,
			String planId) {
		
		Planes_De_Negocios plan=manager.find(Planes_De_Negocios.class, Integer.parseInt(planId));
		plan.setProduccionServuccion(produccionServuccion.replace("\n", "<br>"));
		
		manager.persist(plan);
		
		return plan.getPlan_id();
	}

	
	public int guardarProductosServicios(String productosServicios,
			String planId) {
		
		Planes_De_Negocios plan=manager.find(Planes_De_Negocios.class, Integer.parseInt(planId));
		plan.setProductosServicios(productosServicios.replace("\n", "<br>"));
		
		manager.persist(plan);
		
		return plan.getPlan_id();
	}

	
	public int guardarEmpresa(String empresa, String planId) {
		
		Planes_De_Negocios plan=manager.find(Planes_De_Negocios.class, Integer.parseInt(planId));
		plan.setEmpresa(empresa.replace("\n", "<br>"));
		
		manager.persist(plan);
		
		return plan.getPlan_id();
	}

	
	public int guardarMercado(String mercado, String planId) {
		
		Planes_De_Negocios plan=manager.find(Planes_De_Negocios.class, Integer.parseInt(planId));
		plan.setMercado(mercado.replace("\n", "<br>"));
		
		manager.persist(plan);
		
		return plan.getPlan_id();
	}

	
	public int guardarResumen(String resumen, String planId) {
		
		Planes_De_Negocios plan=manager.find(Planes_De_Negocios.class, Integer.parseInt(planId));
		plan.setResumenEjecutivo(resumen.replace("\n", "<br>"));
		
		manager.persist(plan);
		
		return plan.getPlan_id();
	}

	
	public int guardarPotencialMercado(String potencialMercado, String planId) {
		
		Planes_De_Negocios plan=manager.find(Planes_De_Negocios.class, Integer.parseInt(planId));
		plan.setPotencialMercado(potencialMercado.replace("\n", "<br>"));
		
		manager.persist(plan);
		
		return plan.getPlan_id();
	}

	
	public int guardarViabilidadJuridica(String viabilidadJuridica,
			String planId) {
		
		Planes_De_Negocios plan=manager.find(Planes_De_Negocios.class, Integer.parseInt(planId));
		plan.setViabilidadJuridica(viabilidadJuridica.replace("\n", "<br>"));
		
		manager.persist(plan);
		
		return plan.getPlan_id();
	}

	
	public int guardarObligacionesTributarias(String obligacionesTributarias,
			String planId) {
		
		Planes_De_Negocios plan=manager.find(Planes_De_Negocios.class, Integer.parseInt(planId));
		plan.setObligacionesTributarias(obligacionesTributarias.replace("\n", "<br>"));
		
		manager.persist(plan);
		
		return plan.getPlan_id();
	}

	
	public int guardarOtrosLegales(String otrosLegales, String planId) {
		
		Planes_De_Negocios plan=manager.find(Planes_De_Negocios.class, Integer.parseInt(planId));
		plan.setOtrosLegales(otrosLegales.replace("\n", "<br>"));
		
		manager.persist(plan);
		
		return plan.getPlan_id();
	}


	public String cargarRiesgosContingencias(String planId) {
		
StringBuilder objSecciones;
		
		
		
		
        Query query=manager.createNamedQuery("Datos_Planes_De_Negocios.findById");
		  query.setParameter("id", 1);
		  
		  
		 List datosr=query.getResultList();

		
		Datos_Planes_De_Negocios datos=(Datos_Planes_De_Negocios) (datosr.get(0));
		
        //Crea un array de objetos JSON con cada tema de la tabla
          objSecciones = new StringBuilder("[");    

  		
  		  query=manager.createNamedQuery("Riesgos_Contingencias.findByPlanId");
		  query.setParameter("id", Integer.parseInt(planId));
		  
		  List liste=query.getResultList();
		  
		  Vector<Riesgos_Contingencias> riesgosContingencias = new Vector<Riesgos_Contingencias>();
			for (Object o : liste) {
				Riesgos_Contingencias riesgoContingencia = (Riesgos_Contingencias) o;
				
				riesgosContingencias.add(riesgoContingencia);

			}
  		  
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("datosRiesgosContingencias");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(datos.getRiesgosContiengencias());
          objSecciones.append("\",\"planId\":\"");  		  
          objSecciones.append("planId");
          objSecciones.append("\",\"valorPlanId\":\"");  		  
          objSecciones.append(planId);
          objSecciones.append("\"},");
          
          for(int i=0;i<riesgosContingencias.size();i++){
  		  objSecciones.append("{\"id\":\"");
          objSecciones.append(riesgosContingencias.get(i).getRiesgoContingencia_id());
          objSecciones.append("\",\"riesgo\":\"");  		  
          objSecciones.append(riesgosContingencias.get(i).getRiesgo());
          objSecciones.append("\",\"contingencia\":\"");  		  
          objSecciones.append(riesgosContingencias.get(i).getContingencia());
          objSecciones.append("\"},");
          
                   
          }

                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}


	public String guardarRiesgosContingencias(String[][] mRiesgosContingencias,
			String planId) {
		//Actividad
		for(int i=0;i<mRiesgosContingencias.length;i++){
			
			if(Integer.parseInt(mRiesgosContingencias[i][0])<100){
				
				Query query=manager.createNamedQuery("Riesgos_Contingencias.findAll");
				 List riesgosContingencias=query.getResultList();
				 
				 Vector<Riesgos_Contingencias> riesgosContingenciasV=new Vector<Riesgos_Contingencias>();
				 
				 
					for (Object o : riesgosContingencias) {
						Riesgos_Contingencias rcont = (Riesgos_Contingencias) o;
						
						riesgosContingenciasV.add(rcont);

					}
					int mayor=100;
					if(riesgosContingenciasV.size()>0)
					for(int j=0;j<riesgosContingenciasV.size();j++){
						if(mayor<=riesgosContingenciasV.get(j).getRiesgoContingencia_id()){
							mayor=riesgosContingenciasV.get(j).getRiesgoContingencia_id();
						}
					}
					else{
						mayor=99;
					}
				Riesgos_Contingencias riesCont=new Riesgos_Contingencias();
				riesCont.setRiesgoContingencia_id(mayor+1);
				riesCont.setRiesgo(mRiesgosContingencias[i][1].replace("\n", "<br>"));
				riesCont.setContingencia(mRiesgosContingencias[i][2].replace("\n", "<br>"));
				riesCont.setPlan_id(Integer.parseInt(planId));
				manager.persist(riesCont);	
					
			}
			
			else{
				Riesgos_Contingencias riesCont= manager.find(Riesgos_Contingencias.class,Integer.parseInt(mRiesgosContingencias[i][0]));
				riesCont.setRiesgo(mRiesgosContingencias[i][1].replace("\n", "<br>"));
				riesCont.setContingencia(mRiesgosContingencias[i][2].replace("\n", "<br>"));
				manager.persist(riesCont);	
			}
			
			
			

		}
		Planes_De_Negocios plan=manager.find(Planes_De_Negocios.class, Integer.parseInt(planId));
		plan.setPlanTerminado(true);
		manager.persist(plan);
		
		return "Felicitaciones terminaste la parte escrita de tu plan, ahora puedes comenzar con la parte financiera";
	}
           
	public String cargarPlanes() {
		  StringBuilder objSecciones;
			
			Query query=manager.createNamedQuery("Usuarios_Planes_De_Negocios.findMyId");
			  query.setParameter("id", usuario.getUsuario_id());
			  
			  List liste=query.getResultList();
			 Vector<Usuarios_Planes_De_Negocios> planes=new Vector<Usuarios_Planes_De_Negocios>();
			 
			 
				for (Object o : liste) {
					Usuarios_Planes_De_Negocios plan = (Usuarios_Planes_De_Negocios) o;
					System.out.println(plan.getPlan_id()+" "+plan.getUsuario_id());
					planes.add(plan);

				}
			
			
				Planes_De_Negocios auxiliar;
	        //Crea un array de objetos JSON con cada tema de la tabla
	          objSecciones = new StringBuilder("[");  
	          for(int i=0;i<planes.size();i++){
	        	  auxiliar=manager.find(Planes_De_Negocios.class,(planes.get(i).getPlan_id()));
	          objSecciones.append("{\"Id\":\"");	          
	          objSecciones.append(auxiliar.getPlan_id());        
	          objSecciones.append("\",\"Nombre\":\"");
	          objSecciones.append(auxiliar.getNombre());
	          objSecciones.append("\",\"EscritoTerminado\":\"");
	          objSecciones.append(auxiliar.isPlanTerminado());
	          objSecciones.append("\",\"Fecha\":\"");
	          objSecciones.append(auxiliar.getFechaInicio().toString().substring(0, 10));
	          objSecciones.append("\"},");
	          }
	          

	                      
	        //Sustituye la última coma por el carácter de cierre del array
	          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
			
			return objSecciones.toString();
	}

	
	public String cargarDatosDePlan(int id) {
StringBuilder objSecciones;
		
			System.out.println("antes de crearlo");
		FinanzasProcesadas finan=new FinanzasProcesadas(id,manager);
		System.out.println("despues de crearlo");
		Planes_De_Negocios plan=manager.find(Planes_De_Negocios.class, id);
        //Crea un array de objetos JSON con cada tema de la tabla
          objSecciones = new StringBuilder("[");  
         
          objSecciones.append("{\"Nombre\":\"");
          objSecciones.append(plan.getNombre());
          objSecciones.append("\",\"planId\":\"");
          objSecciones.append(plan.getPlan_id());
          objSecciones.append("\",\"Equipo\":\"");
          objSecciones.append(plan.getEquipo());
          objSecciones.append("\",\"Resumen\":\"");
          objSecciones.append(plan.getResumenEjecutivo());
          objSecciones.append("\",\"Mercado\":\"");
          objSecciones.append(plan.getMercado());
          objSecciones.append("\",\"Competencia\":\"");
          objSecciones.append(plan.getCompetencia());
          objSecciones.append("\",\"Empresa\":\"");
          objSecciones.append(plan.getEmpresa());
          objSecciones.append("\",\"ProductosServicios\":\"");
          objSecciones.append(plan.getProductosServicios());
          objSecciones.append("\",\"ProduccionServuccion\":\"");
          objSecciones.append(plan.getProduccionServuccion());
          objSecciones.append("\",\"PrecioPS\":\"");
          objSecciones.append(plan.getPrecioProductosOServicios());
          objSecciones.append("\",\"MercadoObjetivo\":\"");
          objSecciones.append(plan.getMercadoObjetivo());
          objSecciones.append("\",\"EntradaMercado\":\"");
          objSecciones.append(plan.getEntradaMercado());
          objSecciones.append("\",\"PotencialMercado\":\"");
          objSecciones.append(plan.getPotencialMercado());
          objSecciones.append("\",\"ViabilidadJuridica\":\"");
          objSecciones.append(plan.getViabilidadJuridica());
          objSecciones.append("\",\"ObligacionesTributarias\":\"");
          objSecciones.append(plan.getObligacionesTributarias());
          objSecciones.append("\",\"OtrosLegales\":\"");
          objSecciones.append(plan.getOtrosLegales());
          objSecciones.append("\",\"EstadoPerdidaGanancias\":");
          double ventas[]=finan.getVentas();
          if(ventas==null){
        	  objSecciones.append("\"\"");
          }
          else{
        	  
        	  objSecciones.append("[");
              for(int i=0;i<ventas.length;i++){
          		  objSecciones.append("{\"Ano\":\"");
                  objSecciones.append(ventas[i]);
                  objSecciones.append("\"},");
                  
                           
                  }
        	  objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
        	  
        	  
          }
          objSecciones.append(",\"EstadoDeResultados\":");
          
           double[] costosVentas=finan.getCostosVentas();
           double[] costosFijos=finan.getCostosFijos();
           double[] gastosAdministracion=finan.getGastosAdministracion();
           double[] manoDeObra=finan.getManoDeObra();
          if(ventas==null){
        	  objSecciones.append("\"\"");
          }
          else{
        	  objSecciones.append("{\"costosVentas\":");
        	  objSecciones.append("[");
              for(int i=0;i<costosVentas.length;i++){
          		  objSecciones.append("{\"Ano\":\"");
                  objSecciones.append(costosVentas[i]);
                  objSecciones.append("\"},");
                  
                           
                  }
        	  objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
        	  
        	  objSecciones.append(",\"costosFijos\":");
        	  objSecciones.append("[");
              for(int i=0;i<costosFijos.length;i++){
          		  objSecciones.append("{\"Ano\":\"");
                  objSecciones.append(costosFijos[i]);
                  objSecciones.append("\"},");
                  
                           
                  }
        	  objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
        	  
        	  objSecciones.append(",\"gastosAdministracion\":");
        	  objSecciones.append("[");
              for(int i=0;i<gastosAdministracion.length;i++){
          		  objSecciones.append("{\"Ano\":\"");
                  objSecciones.append(gastosAdministracion[i]);
                  objSecciones.append("\"},");
                  
                           
                  }
        	  objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
        	  
        	  objSecciones.append(",\"manoDeObra\":");
        	  objSecciones.append("[");
              for(int i=0;i<manoDeObra.length;i++){
          		  objSecciones.append("{\"Ano\":\"");
                  objSecciones.append(manoDeObra[i]);
                  objSecciones.append("\"},");
                  
                           
                  }
        	  objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
        	  
        	  
        	  objSecciones.append("}");
        	  
        	  
          }
          objSecciones.append(",\"Impuestos\":\"");
          objSecciones.append(finan.getImpuestos());
          objSecciones.append("\",\"ActivosFijos\":\"");
          objSecciones.append(finan.getInvActivosFijos());
          objSecciones.append("\",\"Medios\":\"");
          objSecciones.append(finan.getMedios());
          objSecciones.append("\",\"InversionPreoperativa\":\"");
          objSecciones.append(finan.getInversionPreoperativa());
          objSecciones.append("\",\"ComposicionCostos\":\"");
          objSecciones.append(plan.getOtrosLegales());
          objSecciones.append("\",\"EstadoResultados\":\"");
          objSecciones.append(plan.getOtrosLegales());
          objSecciones.append("\"},");
          
          Query query=manager.createNamedQuery("Riesgos_Contingencias.findByPlanId");
		  query.setParameter("id", id);
		  
		  List liste=query.getResultList();
		  
		  Vector<Riesgos_Contingencias> riesgosContingencias = new Vector<Riesgos_Contingencias>();
			for (Object o : liste) {
				Riesgos_Contingencias riesgoContingencia = (Riesgos_Contingencias) o;
				
				riesgosContingencias.add(riesgoContingencia);

			}
          
          
          for(int i=0;i<riesgosContingencias.size();i++){
      		  objSecciones.append("{\"id\":\"");
              objSecciones.append(riesgosContingencias.get(i).getRiesgoContingencia_id());
              objSecciones.append("\",\"riesgo\":\"");  		  
              objSecciones.append(riesgosContingencias.get(i).getRiesgo());
              objSecciones.append("\",\"contingencia\":\"");  		  
              objSecciones.append(riesgosContingencias.get(i).getContingencia());
              objSecciones.append("\"},");
              
                       
              }


                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}


	public String cargarAmigos() {
StringBuilder objSecciones;
		
			
		
		

          objSecciones = new StringBuilder("[");  
         
          
          
          Query query=manager.createNamedQuery("Amigos.findOkById");
		  query.setParameter("id", usuario.getUsuario_id());
		  
		  List liste=query.getResultList();
		  
		  Vector<Amigos> amigos = new Vector<Amigos>();
			for (Object o : liste) {
				Amigos amigo = (Amigos) o;
				if(amigo.getSolicitudEstado_id()==1){
				amigos.add(amigo);
				}

			}
          
          
          for(int i=0;i<amigos.size();i++){
        	  if(amigos.get(i).getAmigo_id()==usuario.getUsuario_id()){
      		  objSecciones.append("{\"Id\":\"");
              objSecciones.append(amigos.get(i).getUsuario_id());
              objSecciones.append("\",\"Nombre\":\"");  		  
              objSecciones.append(manager.find(Usuarios.class,amigos.get(i).getUsuario_id()).getNombre()+" "+manager.find(Usuarios.class,amigos.get(i).getUsuario_id()).getApellido());
              objSecciones.append("\",\"Imagen\":\"");  		  
              objSecciones.append(manager.find(Usuarios.class,amigos.get(i).getUsuario_id()).getFoto());
              objSecciones.append("\"},");
        	  }
        	  
        	  else{
        		  objSecciones.append("{\"Id\":\"");
                  objSecciones.append(amigos.get(i).getAmigo_id());
                  objSecciones.append("\",\"Nombre\":\"");  		  
                  objSecciones.append(manager.find(Usuarios.class,amigos.get(i).getAmigo_id()).getNombre()+" "+manager.find(Usuarios.class,amigos.get(i).getAmigo_id()).getApellido());
                  objSecciones.append("\",\"Imagen\":\"");  		  
                  objSecciones.append(manager.find(Usuarios.class,amigos.get(i).getAmigo_id()).getFoto());
                  objSecciones.append("\"},");
        	  }
              
                       
              }


                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}


	public String cargarDatosDeAmigo(int id) {
StringBuilder objSecciones;
		
			
		
		
		Usuarios amigo=manager.find(Usuarios.class, id);
		Privacidad priv=manager.find(Privacidad.class, amigo.getUsuario_id());
        //Crea un array de objetos JSON con cada tema de la tabla
		objSecciones = new StringBuilder("[");
		
		if(esAmigo(id)){
            
          
  		  objSecciones.append("{\"Id\":\"");
          objSecciones.append(amigo.getUsuario_id());
          if(priv.getNombre()==1){
          objSecciones.append("\",\"Nombre\":\"");
          objSecciones.append(amigo.getNombre());
          }
          if(priv.getApellido()==1){
          objSecciones.append("\",\"Apellido\":\"");
          objSecciones.append(amigo.getApellido());
          }
          if(priv.getDireccion()==1){
          objSecciones.append("\",\"Direccion\":\"");
          objSecciones.append(amigo.getDireccion());
          }
          if(priv.getPais()==1){
          objSecciones.append("\",\"Pais\":\"");
          objSecciones.append(manager.find(Paises.class, amigo.getPais()).getNombre());
          }
          if(priv.getGenero()==1){
          objSecciones.append("\",\"Genero\":\"");
          objSecciones.append(manager.find(Genero.class, amigo.getGenero_id()).getGenero());
          }
          if(priv.getCiudad()==1){
          objSecciones.append("\",\"Ciudad\":\"");
          objSecciones.append(amigo.getCiudad());
          }
          if(priv.getTelefono()==1){
          objSecciones.append("\",\"Telefono\":\"");
          objSecciones.append(amigo.getTelefono());
          }
          if(priv.geteMail()==1){
          objSecciones.append("\",\"Email\":\"");
          objSecciones.append(amigo.geteMail());
          }
          if(priv.getFechaNacimiento()==1){
          objSecciones.append("\",\"Fecha_Nacimiento\":\"");
          objSecciones.append(amigo.getFechaNacimiento());
          }
          if(priv.getReseñaPersonal()==1){
          objSecciones.append("\",\"Resena\":\"");
          objSecciones.append(amigo.getReseñaPersonal());
          }
          if(priv.getEstudios()==1){
          objSecciones.append("\",\"Estudio\":\"");
          objSecciones.append(manager.find(Estudios.class, amigo.getEstudios_id()).getEstudio());
          }
          if(priv.getEstudios()==1){
              objSecciones.append("\",\"Imagen\":\"");
              objSecciones.append(amigo.getFoto());
              }
          
          objSecciones.append("\"},");
		}
		
		else{
            
	          
	  		  objSecciones.append("{\"Id\":\"");
	          objSecciones.append(amigo.getUsuario_id());
	          if(priv.getNombre()==1){
	          objSecciones.append("\",\"Nombre\":\"");
	          objSecciones.append(amigo.getNombre());
	          }
	          if(priv.getApellido()==1){
	          objSecciones.append("\",\"Apellido\":\"");
	          objSecciones.append(amigo.getApellido());
	          }
	          if(priv.getDireccion()==1){
	          objSecciones.append("\",\"Direccion\":\"");
	          objSecciones.append(amigo.getDireccion());
	          }
	          if(priv.getPais()==1){
	          objSecciones.append("\",\"Pais\":\"");
	          objSecciones.append(manager.find(Paises.class, amigo.getPais()).getNombre());
	          }
	          if(priv.getGenero()==1){
	          objSecciones.append("\",\"Genero\":\"");
	          objSecciones.append(manager.find(Genero.class, amigo.getGenero_id()).getGenero());
	          }
	          if(priv.getCiudad()==1){
	          objSecciones.append("\",\"Ciudad\":\"");
	          objSecciones.append(amigo.getCiudad());
	          }
	          if(priv.getTelefono()==1){
	          objSecciones.append("\",\"Telefono\":\"");
	          objSecciones.append(amigo.getTelefono());
	          }
	          if(priv.geteMail()==1){
	          objSecciones.append("\",\"Email\":\"");
	          objSecciones.append(amigo.geteMail());
	          }
	          if(priv.getFechaNacimiento()==1){
	          objSecciones.append("\",\"Fecha_Nacimiento\":\"");
	          objSecciones.append(amigo.getFechaNacimiento());
	          }
	          if(priv.getReseñaPersonal()==1){
	          objSecciones.append("\",\"Resena\":\"");
	          objSecciones.append(amigo.getReseñaPersonal());
	          }
	          if(priv.getEstudios()==1){
	          objSecciones.append("\",\"Estudio\":\"");
	          objSecciones.append(manager.find(Estudios.class, amigo.getEstudios_id()).getEstudio());
	          }
	          if(priv.getEstudios()==1){
	              objSecciones.append("\",\"Imagen\":\"");
	              objSecciones.append(amigo.getFoto());
	              }
	          
	          objSecciones.append("\"},");
			}


                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}

	
	private boolean esAmigo(int id) {
        Query query=manager.createNamedQuery("Amigos.findOkById");
		  query.setParameter("id", usuario.getUsuario_id());
		  
		  List liste=query.getResultList();
		  
		  Vector<Amigos> amigos = new Vector<Amigos>();
			for (Object o : liste) {
				Amigos amigo = (Amigos) o;
				if(amigo.getSolicitudEstado_id()==1){
				amigos.add(amigo);
				}

			}
			
			for(int i=0;i<amigos.size();i++){
				if(amigos.get(i).getAmigo_id()==id){
					return true;
				}
				if(amigos.get(i).getUsuario_id()==id){
					return true;
				}
			}
		return false;
	}

	public String cargarAmigosDeAmigo(int id) {
		StringBuilder objSecciones;
		
		
		
		

        objSecciones = new StringBuilder("[");  
       
        
        
        Query query=manager.createNamedQuery("Amigos.findOkById");
		  query.setParameter("id", id);
		  
		  List liste=query.getResultList();
		  
		  Vector<Amigos> amigos = new Vector<Amigos>();
			for (Object o : liste) {
				Amigos amigo = (Amigos) o;
				if(amigo.getSolicitudEstado_id()==1){
				amigos.add(amigo);
				}

			}
        
        
        for(int i=0;i<amigos.size();i++){
      	  if(amigos.get(i).getAmigo_id()==id){
    		  objSecciones.append("{\"Id\":\"");
            objSecciones.append(amigos.get(i).getUsuario_id());
            objSecciones.append("\",\"Nombre\":\"");  		  
            objSecciones.append(manager.find(Usuarios.class,amigos.get(i).getUsuario_id()).getNombre()+" "+manager.find(Usuarios.class,amigos.get(i).getUsuario_id()).getApellido());
            objSecciones.append("\",\"Imagen\":\"");  		  
            objSecciones.append(manager.find(Usuarios.class,amigos.get(i).getUsuario_id()).getFoto());
            objSecciones.append("\"},");
      	  }
      	  
      	  else{
      		  objSecciones.append("{\"Id\":\"");
                objSecciones.append(amigos.get(i).getAmigo_id());
                objSecciones.append("\",\"Nombre\":\"");  		  
                objSecciones.append(manager.find(Usuarios.class,amigos.get(i).getAmigo_id()).getNombre()+" "+manager.find(Usuarios.class,amigos.get(i).getAmigo_id()).getApellido());
                objSecciones.append("\",\"Imagen\":\"");  		  
                objSecciones.append(manager.find(Usuarios.class,amigos.get(i).getAmigo_id()).getFoto());
                objSecciones.append("\"},");
      	  }
            
                     
            }


                    
      //Sustituye la última coma por el carácter de cierre del array
        objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}


	public String cargarGruposDeAmigo(int id) {
StringBuilder objSecciones;
		
		Query query=manager.createNamedQuery("Grupos_Usuarios.findMyId");
		  query.setParameter("id", id);
		  
		  List liste=query.getResultList();
		 Vector<Grupos_Usuarios> grupos=new Vector<Grupos_Usuarios>();
		 
		 
			for (Object o : liste) {
				Grupos_Usuarios grupo = (Grupos_Usuarios) o;
				
				grupos.add(grupo);

			}
		
		
		Grupos auxiliar;
        //Crea un array de objetos JSON con cada tema de la tabla
          objSecciones = new StringBuilder("[");  
          for(int i=0;i<grupos.size();i++){
          objSecciones.append("{\"Id\":\"");
          auxiliar=manager.find(Grupos.class,(grupos.get(i).getGrupo_id()));
          objSecciones.append(auxiliar.getGrupo_id());        
          objSecciones.append("\",\"Nombre\":\"");
          objSecciones.append(auxiliar.getNombre());
          objSecciones.append("\"},");
          }
          

                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}

	
	public String cargarIdeasDeAmigo(int id) {
		 StringBuilder objSecciones;
			
			Query query=manager.createNamedQuery("Idea_De_Negocio_Usuarios.findMyId");
			  query.setParameter("id", id);
			  
			  List liste=query.getResultList();
			 Vector<Idea_De_Negocio_Usuarios> ideas=new Vector<Idea_De_Negocio_Usuarios>();
			 
			 
				for (Object o : liste) {
					Idea_De_Negocio_Usuarios idea = (Idea_De_Negocio_Usuarios) o;
					
					ideas.add(idea);

				}
			
			
				Idea_De_Negocio auxiliar;
	        //Crea un array de objetos JSON con cada tema de la tabla
	          objSecciones = new StringBuilder("[");  
	          for(int i=0;i<ideas.size();i++){
	          objSecciones.append("{\"Id\":\"");
	          auxiliar=manager.find(Idea_De_Negocio.class,(ideas.get(i).getIdea_id()));
	          objSecciones.append(auxiliar.getIdea_id());        
	          objSecciones.append("\",\"Nombre\":\"");
	          objSecciones.append(auxiliar.getNombre());
	          objSecciones.append("\"},");
	          }
	          

	                      
	        //Sustituye la última coma por el carácter de cierre del array
	          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
			
			return objSecciones.toString();
	}


	public String cargarMensajes() {
		StringBuilder objSecciones;
		
		Query query=manager.createNamedQuery("Para.findMyId");
		  query.setParameter("id", usuario.getUsuario_id());
		  
		  List liste=query.getResultList();
		 Vector<Para> mensajes=new Vector<Para>();
		 
		 
			for (Object o : liste) {
				Para mensaje = (Para) o;
				
				mensajes.add(mensaje);

			}
		
		
			Mensajes auxiliar;
        //Crea un array de objetos JSON con cada tema de la tabla
          objSecciones = new StringBuilder("[");  
          for(int i=0;i<mensajes.size();i++){
        	  auxiliar=manager.find(Mensajes.class,(mensajes.get(i).getMensaje_id()));
          objSecciones.append("{\"Id\":\"");          
          objSecciones.append(auxiliar.getMensaje_id());   
          objSecciones.append("\",\"userId\":\"");
          objSecciones.append(auxiliar.getUsuario_id());
          objSecciones.append("\",\"De\":\"");
          objSecciones.append(manager.find(Usuarios.class, auxiliar.getUsuario_id()).getNombre()+" "+manager.find(Usuarios.class, auxiliar.getUsuario_id()).getApellido());
          objSecciones.append("\",\"Imagen\":\"");
          objSecciones.append(manager.find(Usuarios.class, auxiliar.getUsuario_id()).getFoto());
          objSecciones.append("\",\"Asunto\":\"");
          objSecciones.append(auxiliar.getAsunto());
          objSecciones.append("\",\"Leido\":\"");
          objSecciones.append(mensajes.get(i).isBandera());
          objSecciones.append("\",\"Fecha\":\"");
          objSecciones.append(auxiliar.getFecha().toString().substring(0, 10));
          objSecciones.append("\"},");
         
          }
          

                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}


	public void enviarMensaje(String para, String asunto, String cuerpo) {
	int paras[];
	String aux[];
	aux=para.split(";");
	paras=new int[aux.length];
	for(int i=0;i<aux.length;i++){
		paras[i]=Integer.parseInt(aux[i]);
	}
	Mensajes mensaje=new Mensajes();
	Query query=manager.createNamedQuery("Mensajes.findAll");
	 
	 List mensajes=query.getResultList();
	 Vector<Mensajes> mensajesV=new Vector<Mensajes>();
	 
	 
		for (Object o : mensajes) {
			Mensajes ideaa = (Mensajes) o;
			
			mensajesV.add(ideaa);

		}
		int mayor=0;
		if(mensajesV.size()>0)
		for(int i=0;i<mensajesV.size();i++){
			if(mayor<=mensajesV.get(i).getMensaje_id()){
				mayor=mensajesV.get(i).getMensaje_id();
			}
		}
		
		else{
			mayor=-1;
		}
		mensaje.setMensaje_id(mayor+1);
		mensaje.setAsunto(asunto);
		mensaje.setCuerpo(cuerpo);
		mensaje.setFecha(new Date());
		mensaje.setUsuario_id(usuario.getUsuario_id());
		mensaje.setTipoMensaje_id(0);
		mensaje.setGlobo(false);
		manager.persist(mensaje);
		
		
		
		for(int i=0;i<paras.length;i++){
			Para parasO=new Para();
			
			parasO.setBandera(false);
			parasO.setMensaje_id(mensaje.getMensaje_id());
			parasO.setUsuario_id(paras[i]);
			manager.persist(parasO);
		}
	
	
	
	
		
	}


	public String cargarMensajesEnviados() {
StringBuilder objSecciones;
		
		Query query=manager.createNamedQuery("Mensajes.findMyId");
		  query.setParameter("id", usuario.getUsuario_id());
		  
		  List liste=query.getResultList();
		 Vector<Mensajes> mensajes=new Vector<Mensajes>();
		 
		 
			for (Object o : liste) {
				Mensajes mensaje = (Mensajes) o;
				
				mensajes.add(mensaje);

			}
		
		
			
        //Crea un array de objetos JSON con cada tema de la tabla
          objSecciones = new StringBuilder("[");  
          for(int i=0;i<mensajes.size();i++){
        	  
          objSecciones.append("{\"Id\":\"");          
          objSecciones.append(mensajes.get(i).getMensaje_id());
          objSecciones.append("\",\"userId\":\"");
          objSecciones.append(usuario.getUsuario_id());
          objSecciones.append("\",\"De\":\"");
          objSecciones.append(usuario.getNombre()+" "+usuario.getApellido());
          objSecciones.append("\",\"Imagen\":\"");
          objSecciones.append(usuario.getFoto());
          objSecciones.append("\",\"Asunto\":\"");
          objSecciones.append(mensajes.get(i).getAsunto());
          objSecciones.append("\",\"Fecha\":\"");
          objSecciones.append(mensajes.get(i).getFecha().toString().substring(0, 10));
          objSecciones.append("\"},");
          }
          

                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}

	public String cargarMensaje(int id) {
		
StringBuilder objSecciones;
		
			
		
		
		Mensajes mensaje=manager.find(Mensajes.class, id);
        //Crea un array de objetos JSON con cada tema de la tabla
          objSecciones = new StringBuilder("[");  
         
          objSecciones.append("{\"IdUsuario\":\"");
          objSecciones.append(mensaje.getUsuario_id());
          objSecciones.append("\",\"NombreUsuario\":\"");
          objSecciones.append(manager.find(Usuarios.class, mensaje.getUsuario_id()).getNombre()+" "+manager.find(Usuarios.class, mensaje.getUsuario_id()).getApellido());
          objSecciones.append("\",\"Asunto\":\"");
          objSecciones.append(mensaje.getAsunto());
          objSecciones.append("\",\"Cuerpo\":\"");
          objSecciones.append(mensaje.getCuerpo());
          objSecciones.append("\",\"Id\":\"");
          objSecciones.append(mensaje.getMensaje_id());
          objSecciones.append("\"},");
          
          


                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}


	public String crearPlanFinanciero(String id) {
	
	StringBuilder objSecciones;
	Finanzas planF=manager.find(Finanzas.class, Integer.parseInt(id));
	if(planF==null){
		planF=new Finanzas();
		planF.setFinanzas_id(Integer.parseInt(id));
		planF.setPlanNegocio_id(Integer.parseInt(id));
		manager.persist(planF);
		
	}
	
	Datos_Planes_Financieros datos=manager.find(Datos_Planes_Financieros.class, 1);
	
    //Crea un array de objetos JSON con cada tema de la tabla
      objSecciones = new StringBuilder("[");    

		
		  Query query=manager.createNamedQuery("Productos.findByPlanId");
	      query.setParameter("id", Integer.parseInt(id));
	  
	  List liste=query.getResultList();
	  
	  Vector<Productos> productos = new Vector<Productos>();
		for (Object o : liste) {
			Productos producto = (Productos) o;
			
			productos.add(producto);

		}
		  
		  objSecciones.append("{\"dato\":\"");
      objSecciones.append("datosProductos");
      objSecciones.append("\",\"valor\":\"");  		  
      objSecciones.append(datos.getProductos());
      objSecciones.append("\",\"planId\":\"");  		  
      objSecciones.append("planId");
      objSecciones.append("\",\"valorPlanId\":\"");  		  
      objSecciones.append(id);
      objSecciones.append("\",\"nombrePlan\":\"");  		  
      objSecciones.append(manager.find(Planes_De_Negocios.class, Integer.parseInt(id)).getNombre());
      objSecciones.append("\"},");
      
      for(int i=0;i<productos.size();i++){
		  objSecciones.append("{\"id\":\"");
      objSecciones.append(productos.get(i).getProducto_id());
      objSecciones.append("\",\"Nombre\":\"");  		  
      objSecciones.append(productos.get(i).getNombre());
      objSecciones.append("\",\"Precio\":\"");  		  
      objSecciones.append(productos.get(i).getPrecioVenta());
      objSecciones.append("\",\"Contado\":\"");  		  
      objSecciones.append(productos.get(i).getContado());
      objSecciones.append("\",\"A30\":\"");  		  
      objSecciones.append(productos.get(i).getA30());
      objSecciones.append("\",\"A60\":\"");  		  
      objSecciones.append(productos.get(i).getA60());
      objSecciones.append("\",\"A90\":\"");  		  
      objSecciones.append(productos.get(i).getA90());
      objSecciones.append("\",\"A120\":\"");  		  
      objSecciones.append(productos.get(i).getA120());
      objSecciones.append("\",\"A150\":\"");  		  
      objSecciones.append(productos.get(i).getA150());
      objSecciones.append("\",\"Comision\":\"");  		  
      objSecciones.append(productos.get(i).getPorcentajeComision());
      objSecciones.append("\"},");
      
               
      }

                  
    //Sustituye la última coma por el carácter de cierre del array
      objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
	
	return objSecciones.toString();
	}

	
	public String guardarProductos(String[][] productos, String planId) {
		//Actividad
		for(int i=0;i<productos.length;i++){
			
			if(Integer.parseInt(productos[i][0])<100){
				
				Query query=manager.createNamedQuery("Productos.findAll");
				 List listaProductos=query.getResultList();
				 
				 Vector<Productos> productosV=new Vector<Productos>();
				 
				 
					for (Object o : listaProductos) {
						Productos rpro = (Productos) o;
						
						productosV.add(rpro);

					}
					int mayor=100;
					if(productosV.size()>0)
					for(int j=0;j<productosV.size();j++){
						if(mayor<=productosV.get(j).getProducto_id()){
							mayor=productosV.get(j).getProducto_id();
						}
					}
					else{
						mayor=99;
					}
				Productos prod=new Productos();
				prod.setProducto_id(mayor+1);
				prod.setNombre(productos[i][1].replace("\n", "<br>"));
				prod.setPrecioVenta(Integer.parseInt(productos[i][2]));
				prod.setPorcentajeComision(Integer.parseInt(productos[i][3]));
				prod.setContado(Integer.parseInt(productos[i][4]));
				prod.setA30(Integer.parseInt(productos[i][5]));
				prod.setA60(Integer.parseInt(productos[i][6]));
				prod.setA90(Integer.parseInt(productos[i][7]));
				prod.setA120(Integer.parseInt(productos[i][8]));
				prod.setA150(Integer.parseInt(productos[i][9]));
				prod.setFinanzas_id(Integer.parseInt(planId));
				manager.persist(prod);	
					
			}
			
			else{
				Productos prod= manager.find(Productos.class,Integer.parseInt(productos[i][0]));
				prod.setNombre(productos[i][1].replace("\n", "<br>"));
				prod.setPrecioVenta(Integer.parseInt(productos[i][2]));
				prod.setPorcentajeComision(Integer.parseInt(productos[i][3]));
				prod.setContado(Integer.parseInt(productos[i][4]));
				prod.setA30(Integer.parseInt(productos[i][5]));
				prod.setA60(Integer.parseInt(productos[i][6]));
				prod.setA90(Integer.parseInt(productos[i][7]));
				prod.setA120(Integer.parseInt(productos[i][8]));
				prod.setA150(Integer.parseInt(productos[i][9]));
				prod.setFinanzas_id(Integer.parseInt(planId));
				manager.persist(prod);	
			}
			
			
			

		}

		
		return planId;
	}


	public String cargarMediosComunicacion(String planId) {

			StringBuilder objSecciones;
		
				Query query=manager.createNamedQuery("Medios_Comunicacion.findByPlanId");
				query.setParameter("id", Integer.parseInt(planId));
				
				List listaMedios=query.getResultList();

				Vector<Medios_Comunicacion> mediosV=new Vector<Medios_Comunicacion>();


				for (Object o : listaMedios) {
					Medios_Comunicacion rmed = (Medios_Comunicacion) o;
		
					mediosV.add(rmed);

				}
        //Crea un array de objetos JSON con cada tema de la tabla
          objSecciones = new StringBuilder("[");    
          

  		
  		Datos_Planes_Financieros datos=manager.find(Datos_Planes_Financieros.class, 1);
  		  
  		  objSecciones.append("{\"dato\":\"");
          objSecciones.append("datosMediosComunicacion");
          objSecciones.append("\",\"valor\":\"");  		  
          objSecciones.append(datos.getMediosComunicacion());
          objSecciones.append("\",\"planId\":\"");  		  
          objSecciones.append("planId");
          objSecciones.append("\",\"valorPlanId\":\"");  		  
          objSecciones.append(planId);
          objSecciones.append("\"},");
          
          
          for(int i=0;i<mediosV.size();i++){
    		  objSecciones.append("{\"id\":\"");
          objSecciones.append(mediosV.get(i).getMedio_id());
          objSecciones.append("\",\"Nombre\":\"");  		  
          objSecciones.append(mediosV.get(i).getNombreInversion());
          objSecciones.append("\",\"Valor\":\"");  		  
          objSecciones.append(mediosV.get(i).getValorInversion());
          objSecciones.append("\"},");
          
                   
          }
                   
          

                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}


	public String guardarMedios(String[][] medios, String planId) {
		//Actividad
		for(int i=0;i<medios.length;i++){
			
			if(Integer.parseInt(medios[i][0])<100){
				
				Query query=manager.createNamedQuery("Medios_Comunicacion.findAll");
				 List listamedios=query.getResultList();
				 
				 Vector<Medios_Comunicacion> mediosV=new Vector<Medios_Comunicacion>();
				 
				 
					for (Object o : listamedios) {
						Medios_Comunicacion rmed = (Medios_Comunicacion) o;
						
						mediosV.add(rmed);

					}
					int mayor=100;
					if(mediosV.size()>0)
					for(int j=0;j<mediosV.size();j++){
						if(mayor<=mediosV.get(j).getMedio_id()){
							mayor=mediosV.get(j).getMedio_id();
						}
					}
					else{
						mayor=99;
					}
				Medios_Comunicacion medi=new Medios_Comunicacion();
				medi.setMedio_id(mayor+1);
				medi.setNombreInversion(medios[i][1].replace("\n", "<br>"));
				medi.setValorInversion(Integer.parseInt(medios[i][2]));
				medi.setFinanzas_id(Integer.parseInt(planId));
				manager.persist(medi);	
					
			}
			
			else{
				Medios_Comunicacion medi= manager.find(Medios_Comunicacion.class,Integer.parseInt(medios[i][0]));
				
				medi.setNombreInversion(medios[i][1].replace("\n", "<br>"));
				medi.setValorInversion(Integer.parseInt(medios[i][2]));
				medi.setFinanzas_id(Integer.parseInt(planId));
				manager.persist(medi);
			}
			
			
			

		}

		
		return planId;
	}


	public String cargarInsumosProcesos(String planId) {
		StringBuilder objSecciones;


		
		Datos_Planes_Financieros datos=manager.find(Datos_Planes_Financieros.class, 1);
		
	    //Crea un array de objetos JSON con cada tema de la tabla
	      objSecciones = new StringBuilder("[");    

			
			  Query query=manager.createNamedQuery("Productos.findByPlanId");
		      query.setParameter("id", Integer.parseInt(planId));
		  
		  List liste=query.getResultList();
		  
		  Vector<Productos> productos = new Vector<Productos>();
			for (Object o : liste) {
				Productos producto = (Productos) o;
				
				productos.add(producto);

			}
			  
			  objSecciones.append("{\"dato\":\"");
	      objSecciones.append("datosProductos");
	      objSecciones.append("\",\"valor\":\"");  		  
	      objSecciones.append(datos.getInsumosProcesos());
	      objSecciones.append("\",\"planId\":\"");  		  
	      objSecciones.append("planId");
	      objSecciones.append("\",\"valorPlanId\":\"");  		  
	      objSecciones.append(planId);
	      objSecciones.append("\",\"formasPago\":");  		  
	      query=manager.createNamedQuery("Forma_Pago_Insumo.findAll");
	      
	  
	  List listaa=query.getResultList();
	  
	  Vector<Forma_Pago_Insumo> formasPago = new Vector<Forma_Pago_Insumo>();
		for (Object o : listaa) {
			Forma_Pago_Insumo formaPago = (Forma_Pago_Insumo) o;
			
			formasPago.add(formaPago);

		}
		
		objSecciones.append("[");
		for(int j=0;j<formasPago.size();j++){
  		  objSecciones.append("{\"formaPagoId\":\"");
        objSecciones.append(formasPago.get(j).getForma_id());
        objSecciones.append("\",\"Descripcion\":\"");  		  
        objSecciones.append(formasPago.get(j).getDescripcion());
        objSecciones.append("\"},");
        
                 
        }
		objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
	      objSecciones.append("},");
	      
	      for(int i=0;i<productos.size();i++){
			  objSecciones.append("{\"productoId\":\"");
	      objSecciones.append(productos.get(i).getProducto_id());
	      objSecciones.append("\",\"Nombre\":\"");  		  
	      objSecciones.append(productos.get(i).getNombre());
	      objSecciones.append("\",\"Precio\":\"");  		  
	      objSecciones.append(productos.get(i).getPrecioVenta());
	      objSecciones.append("\",\"insumosProcesos\":");  
	      
		  query=manager.createNamedQuery("Insumos_Procesos_Al_Destajo.findByPlanId");
	      query.setParameter("id", productos.get(i).getProducto_id());
	  
	  List lista=query.getResultList();
	  
	  Vector<Insumos_Procesos_Al_Destajo> insumosProcesos = new Vector<Insumos_Procesos_Al_Destajo>();
		for (Object o : lista) {
			Insumos_Procesos_Al_Destajo insumoProceso = (Insumos_Procesos_Al_Destajo) o;
			
			insumosProcesos.add(insumoProceso);

		}
		
		objSecciones.append("[");
		for(int j=0;j<insumosProcesos.size();j++){
  		  objSecciones.append("{\"insumoProcesoId\":\"");
        objSecciones.append(insumosProcesos.get(j).getInsumoProceso_id());
        objSecciones.append("\",\"Nombre\":\"");  		  
        objSecciones.append(insumosProcesos.get(j).getNombre());
        objSecciones.append("\",\"Costo\":\"");  		  
        objSecciones.append(insumosProcesos.get(j).getCosto());
        objSecciones.append("\",\"Inventario\":\"");  		  
        objSecciones.append(insumosProcesos.get(j).getInventario());
        objSecciones.append("\",\"formaPago\":\"");  		  
        objSecciones.append(insumosProcesos.get(j).getFormaPago_id());
        objSecciones.append("\"},");
        
                 
        }
		if(insumosProcesos.size()>0){
		objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		 objSecciones.append("},");
		}
		else{
			objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"");
			 objSecciones.append("\"\"},");
		}

	     
	      
	               
	      }

	                  
	    //Sustituye la última coma por el carácter de cierre del array
	      objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}


	public String guardarInsumosProcesos(String[][] insumosProcesos,
			String planId) {

		//Actividad
		for(int i=0;i<insumosProcesos.length;i++){
			
			if(Integer.parseInt(insumosProcesos[i][1])<100){
				
				Query query=manager.createNamedQuery("Insumos_Procesos_Al_Destajo.findAll");
				 List listainsumosProcesos=query.getResultList();
				 
				 Vector<Insumos_Procesos_Al_Destajo> insumosProcesosV=new Vector<Insumos_Procesos_Al_Destajo>();
				 
				 
					for (Object o : listainsumosProcesos) {
						Insumos_Procesos_Al_Destajo rInsPro = (Insumos_Procesos_Al_Destajo) o;
						
						insumosProcesosV.add(rInsPro);

					}
					int mayor=100;
					if(insumosProcesosV.size()>0)
					for(int j=0;j<insumosProcesosV.size();j++){
						if(mayor<=insumosProcesosV.get(j).getInsumoProceso_id()){
							mayor=insumosProcesosV.get(j).getInsumoProceso_id();
						}
					}
					else{
						mayor=99;
					}
				Insumos_Procesos_Al_Destajo insPro=new Insumos_Procesos_Al_Destajo();
				insPro.setInsumoProceso_id(mayor+1);
				insPro.setNombre(insumosProcesos[i][2]);
				insPro.setProducto_id(Integer.parseInt(insumosProcesos[i][0]));
				insPro.setCosto(Integer.parseInt(insumosProcesos[i][3]));
				insPro.setInventario(Integer.parseInt(insumosProcesos[i][4]));
				insPro.setFormaPago_id(Integer.parseInt(insumosProcesos[i][5]));
				manager.persist(insPro);	
					
			}
			
			else{
				Insumos_Procesos_Al_Destajo insPro= manager.find(Insumos_Procesos_Al_Destajo.class,Integer.parseInt(insumosProcesos[i][1]));
				
				insPro.setNombre(insumosProcesos[i][2].replace("\n", "<br>"));
				insPro.setProducto_id(Integer.parseInt(insumosProcesos[i][0]));
				insPro.setCosto(Integer.parseInt(insumosProcesos[i][3]));
				insPro.setInventario(Integer.parseInt(insumosProcesos[i][4]));
				insPro.setFormaPago_id(Integer.parseInt(insumosProcesos[i][5]));
				manager.persist(insPro);
			}
			
			
			

		}

		
		return planId;
	}

	public String cargarActivosFijos(String planId) {
StringBuilder objSecciones;


		
		Datos_Planes_Financieros datos=manager.find(Datos_Planes_Financieros.class, 1);
		
	    //Crea un array de objetos JSON con cada tema de la tabla
	      objSecciones = new StringBuilder("[");    

			
			  Query query=manager.createNamedQuery("Inversion_Activo_Fijos.findByPlanId");
		      query.setParameter("id", Integer.parseInt(planId));
		  
		  List liste=query.getResultList();
		  
		  Vector<Inversion_Activo_Fijos> activosFijos = new Vector<Inversion_Activo_Fijos>();
			for (Object o : liste) {
				Inversion_Activo_Fijos activoFijo = (Inversion_Activo_Fijos) o;
				
				activosFijos.add(activoFijo);

			}
			  
			  objSecciones.append("{\"dato\":\"");
	      objSecciones.append("datosActivosFijos");
	      objSecciones.append("\",\"valor\":\"");  		  
	      objSecciones.append(datos.getActivosFijos());
	      objSecciones.append("\",\"planId\":\"");  		  
	      objSecciones.append("planId");
	      objSecciones.append("\",\"valorPlanId\":\"");  		  
	      objSecciones.append(planId);
	      objSecciones.append("\",\"formasPagos\":");  		  
	      query=manager.createNamedQuery("Forma_Pago.findAll");
	      
	  
	  List listaa=query.getResultList();
	  
	  Vector<Forma_Pago> formasPago = new Vector<Forma_Pago>();
		for (Object o : listaa) {
			Forma_Pago formaPago = (Forma_Pago) o;
			
			formasPago.add(formaPago);

		}
		
		objSecciones.append("[");
		for(int j=0;j<formasPago.size();j++){
  		  objSecciones.append("{\"formaPagoId\":\"");
        objSecciones.append(formasPago.get(j).getForma_id());
        objSecciones.append("\",\"Descripcion\":\"");  		  
        objSecciones.append(formasPago.get(j).getNombre());
        objSecciones.append("\"},");
        
                 
        }
		objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
	      
	      objSecciones.append(",\"Clasificacion\":");  		  
	      query=manager.createNamedQuery("Clasificacion_Activos.findAll");
	      
	  
	   listaa=query.getResultList();
	  
	  Vector<Clasificacion_Activos> clasificacion = new Vector<Clasificacion_Activos>();
		for (Object o : listaa) {
			Clasificacion_Activos clasi = (Clasificacion_Activos) o;
			
			clasificacion.add(clasi);

		}
		
		objSecciones.append("[");
		for(int j=0;j<clasificacion.size();j++){
  		  objSecciones.append("{\"clasificacionId\":\"");
        objSecciones.append(clasificacion.get(j).getActivo_id());
        objSecciones.append("\",\"Descripcion\":\"");  		  
        objSecciones.append(clasificacion.get(j).getNombre());
        objSecciones.append("\"},");
        
                 
        }
		objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
	      objSecciones.append("},");
	      
	      for(int i=0;i<activosFijos.size();i++){
			  objSecciones.append("{\"ActivoId\":\"");
	      objSecciones.append(activosFijos.get(i).getInversionActivoFijo_id());
	      objSecciones.append("\",\"Descripcion\":\"");  		  
	      objSecciones.append(activosFijos.get(i).getDescripcion());
	      objSecciones.append("\",\"Cantidad\":\"");  		  
	      objSecciones.append(activosFijos.get(i).getCantidad());
	      objSecciones.append("\",\"CostoUnitario\":\"");  		  
	      objSecciones.append(activosFijos.get(i).getCostoUnitario());
	      objSecciones.append("\",\"ClasificacionActivo\":\"");  		  
	      objSecciones.append(activosFijos.get(i).getClasificacionActivo_id());
	      objSecciones.append("\",\"FormaPagoActivo\":\"");  		  
	      objSecciones.append(activosFijos.get(i).getFormaPago_id());
	      objSecciones.append("\"},"); 
	      
	      }
	                  
	    //Sustituye la última coma por el carácter de cierre del array
	      objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}


	public String guardarActivosFijos(String[][] activosFijos, String planId) {
	
		//Actividad
		for(int i=0;i<activosFijos.length;i++){
			
			if(Integer.parseInt(activosFijos[i][0])<100){
				
				Query query=manager.createNamedQuery("Inversion_Activo_Fijos.findAll");
				 List listaActivosFijos=query.getResultList();
				 
				 Vector<Inversion_Activo_Fijos> activosFijosV=new Vector<Inversion_Activo_Fijos>();
				 
				 
					for (Object o : listaActivosFijos) {
						Inversion_Activo_Fijos activo = (Inversion_Activo_Fijos) o;
						
						activosFijosV.add(activo);

					}
					int mayor=100;
					if(activosFijosV.size()>0)
					for(int j=0;j<activosFijosV.size();j++){
						if(mayor<=activosFijosV.get(j).getInversionActivoFijo_id()){
							mayor=activosFijosV.get(j).getInversionActivoFijo_id();
						}
					}
					else{
						mayor=99;
					}
					Inversion_Activo_Fijos actFij=new Inversion_Activo_Fijos();
				actFij.setInversionActivoFijo_id(mayor+1);
				actFij.setClasificacionActivo_id(Integer.parseInt(activosFijos[i][1]));
				actFij.setFormaPago_id(Integer.parseInt(activosFijos[i][2]));
				actFij.setDescripcion((activosFijos[i][3]));
				actFij.setCantidad(Integer.parseInt(activosFijos[i][4]));
				actFij.setCostoUnitario(Integer.parseInt(activosFijos[i][5]));
				actFij.setFinanzas_id(Integer.parseInt(planId));
				manager.persist(actFij);	
					
			}
			
			else{
				Inversion_Activo_Fijos actFij= manager.find(Inversion_Activo_Fijos.class,Integer.parseInt(activosFijos[i][0]));
				
				actFij.setClasificacionActivo_id(Integer.parseInt(activosFijos[i][1]));
				actFij.setFormaPago_id(Integer.parseInt(activosFijos[i][2]));
				actFij.setDescripcion((activosFijos[i][3]));
				actFij.setCantidad(Integer.parseInt(activosFijos[i][4]));
				actFij.setCostoUnitario(Integer.parseInt(activosFijos[i][5]));
				actFij.setFinanzas_id(Integer.parseInt(planId));
				manager.persist(actFij);
			}
			
			
			

		}

		
		return planId;
	}

	
	public String cargarInversionPreoperativa(String planId) {
		StringBuilder objSecciones;


		
		Datos_Planes_Financieros datos=manager.find(Datos_Planes_Financieros.class, 1);
		
	    //Crea un array de objetos JSON con cada tema de la tabla
	      objSecciones = new StringBuilder("[");    

			
			  Query query=manager.createNamedQuery("Inversion_Preoperativa.findByPlanId");
		      query.setParameter("id", Integer.parseInt(planId));
		  
		  List liste=query.getResultList();
		  
		  Vector<Inversion_Preoperativa> inversionesPreoperativas = new Vector<Inversion_Preoperativa>();
			for (Object o : liste) {
				Inversion_Preoperativa inversion = (Inversion_Preoperativa) o;
				
				inversionesPreoperativas.add(inversion);

			}
			  
			  objSecciones.append("{\"dato\":\"");
	      objSecciones.append("datosInversionPreoperativa");
	      objSecciones.append("\",\"valor\":\"");  		  
	      objSecciones.append(datos.getInversionPreoperativa());
	      objSecciones.append("\",\"planId\":\"");  		  
	      objSecciones.append("planId");
	      objSecciones.append("\",\"valorPlanId\":\"");  		  
	      objSecciones.append(planId);
	      objSecciones.append("\",\"tipoPreoperativa\":");  		  
	      query=manager.createNamedQuery("Tipo_Preoperativa.findAll");
	      
	  
	  List listaa=query.getResultList();
	  
	  Vector<Tipo_Preoperativa> tiposPreoperativa = new Vector<Tipo_Preoperativa>();
		for (Object o : listaa) {
			Tipo_Preoperativa tipo = (Tipo_Preoperativa) o;
			
			tiposPreoperativa.add(tipo);

		}
		
		objSecciones.append("[");
		for(int j=0;j<tiposPreoperativa.size();j++){
  		  objSecciones.append("{\"tipoId\":\"");
        objSecciones.append(tiposPreoperativa.get(j).getTipo_id());
        objSecciones.append("\",\"Descripcion\":\"");  		  
        objSecciones.append(tiposPreoperativa.get(j).getNombre());
        objSecciones.append("\"},");
        
                 
        }
		objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
	      objSecciones.append("},");
	      
	      for(int i=0;i<inversionesPreoperativas.size();i++){
			  objSecciones.append("{\"Id\":\"");
	      objSecciones.append(inversionesPreoperativas.get(i).getInversionPreoperativa_id());
	      objSecciones.append("\",\"Tipo\":\"");  		  
	      objSecciones.append(inversionesPreoperativas.get(i).getTipoPreoperativa_id());
	      objSecciones.append("\",\"Valor\":\"");  		  
	      objSecciones.append(inversionesPreoperativas.get(i).getValorInversion());
	      objSecciones.append("\"},");
	      
	               
	      }
	      if(inversionesPreoperativas.size()>0){
	  		objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
	  		
	  		}
	  		else{
	  			objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"}");
	  			
	  		}
	                  
	    //Sustituye la última coma por el carácter de cierre del array
	      objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}

	
	public String guardarInversionPreoperativa(
			String[][] inversionPreoperativa, String planId) {
		//Actividad
		for(int i=0;i<inversionPreoperativa.length;i++){
			
			if(Integer.parseInt(inversionPreoperativa[i][0])<100){
				
				Query query=manager.createNamedQuery("Inversion_Preoperativa.findAll");
				 List listamedios=query.getResultList();
				 
				 Vector<Inversion_Preoperativa> invPreops=new Vector<Inversion_Preoperativa>();
				 
				 
					for (Object o : listamedios) {
						Inversion_Preoperativa inv = (Inversion_Preoperativa) o;
						
						invPreops.add(inv);

					}
					int mayor=100;
					if(invPreops.size()>0)
					for(int j=0;j<invPreops.size();j++){
						if(mayor<=invPreops.get(j).getInversionPreoperativa_id()){
							mayor=invPreops.get(j).getInversionPreoperativa_id();
						}
					}
					else{
						mayor=99;
					}
					Inversion_Preoperativa inver=new Inversion_Preoperativa();
				inver.setInversionPreoperativa_id(mayor+1);
				inver.setTipoPreoperativa_id(Integer.parseInt(inversionPreoperativa[i][1]));
				inver.setValorInversion(Integer.parseInt(inversionPreoperativa[i][2]));
				inver.setFinanzas_id(Integer.parseInt(planId));
				manager.persist(inver);	
					
			}
			
			else{
				Inversion_Preoperativa inver= manager.find(Inversion_Preoperativa.class,Integer.parseInt(inversionPreoperativa[i][0]));
				
				inver.setTipoPreoperativa_id(Integer.parseInt(inversionPreoperativa[i][1]));
				inver.setValorInversion(Integer.parseInt(inversionPreoperativa[i][2]));
				inver.setFinanzas_id(Integer.parseInt(planId));
				manager.persist(inver);	
			}
			
			
			

		}

		
		return planId;
	}

	
	public String cargarCostosFijos(String planId) {
        StringBuilder objSecciones;


		
		Datos_Planes_Financieros datos=manager.find(Datos_Planes_Financieros.class, 1);
		
	    //Crea un array de objetos JSON con cada tema de la tabla
	      objSecciones = new StringBuilder("[");    

			
			  Query query=manager.createNamedQuery("Costos_Fijos.findByPlanId");
		      query.setParameter("id", Integer.parseInt(planId));
		  
		  List liste=query.getResultList();
		  
		  Vector<Costos_Fijos> costosFijos = new Vector<Costos_Fijos>();
			for (Object o : liste) {
				Costos_Fijos costoF = (Costos_Fijos) o;
				
				costosFijos.add(costoF);

			}
			  
			  objSecciones.append("{\"dato\":\"");
	      objSecciones.append("datosCostosFijos");
	      objSecciones.append("\",\"valor\":\"");  		  
	      objSecciones.append(datos.getCostosFijos());
	      objSecciones.append("\",\"planId\":\"");  		  
	      objSecciones.append("planId");
	      objSecciones.append("\",\"valorPlanId\":\"");  		  
	      objSecciones.append(planId);
	      objSecciones.append("\",\"tipoPreoperativa\":");  		  
	      query=manager.createNamedQuery("Tipo_Costos_Fijos.findAll");
	      
	  
	  List listaa=query.getResultList();
	  
	  Vector<Tipo_Costos_Fijos> tiposCostosFijos = new Vector<Tipo_Costos_Fijos>();
		for (Object o : listaa) {
			Tipo_Costos_Fijos tipo = (Tipo_Costos_Fijos) o;
			
			tiposCostosFijos.add(tipo);

		}
		
		objSecciones.append("[");
		for(int j=0;j<tiposCostosFijos.size();j++){
  		  objSecciones.append("{\"tipoId\":\"");
        objSecciones.append(tiposCostosFijos.get(j).getTipo_id());
        objSecciones.append("\",\"Descripcion\":\"");  		  
        objSecciones.append(tiposCostosFijos.get(j).getNombre());
        objSecciones.append("\"},");
        
                 
        }
		objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
	      objSecciones.append("},");
	      
	      for(int i=0;i<costosFijos.size();i++){
			  objSecciones.append("{\"Id\":\"");
	      objSecciones.append(costosFijos.get(i).getCostoFijo_id());
	      objSecciones.append("\",\"Tipo\":\"");  		  
	      objSecciones.append(costosFijos.get(i).getTipoCostos_id());
	      objSecciones.append("\",\"Valor\":\"");  		  
	      objSecciones.append(costosFijos.get(i).getCostoMensual());
	      objSecciones.append("\"},");
	      
	               
	      }
	      if(costosFijos.size()>0){
	  		objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
	  		
	  		}
	  		else{
	  			objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"}");
	  			
	  		}
	                  
	    //Sustituye la última coma por el carácter de cierre del array
	      objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}

	
	public String guardarCostosFijos(String[][] costosFijos, String planId) {
		//Actividad
		for(int i=0;i<costosFijos.length;i++){
			
			if(Integer.parseInt(costosFijos[i][0])<100){
				
				Query query=manager.createNamedQuery("Costos_Fijos.findAll");
				 List listamedios=query.getResultList();
				 
				 Vector<Costos_Fijos> costFij=new Vector<Costos_Fijos>();
				 
				 
					for (Object o : listamedios) {
						Costos_Fijos inv = (Costos_Fijos) o;
						
						costFij.add(inv);

					}
					int mayor=100;
					if(costFij.size()>0)
					for(int j=0;j<costFij.size();j++){
						if(mayor<=costFij.get(j).getCostoFijo_id()){
							mayor=costFij.get(j).getCostoFijo_id();
						}
					}
					else{
						mayor=99;
					}
					Costos_Fijos costoFijo=new Costos_Fijos();
				costoFijo.setCostoFijo_id(mayor+1);
				costoFijo.setTipoCostos_id(Integer.parseInt(costosFijos[i][1]));
				costoFijo.setCostoMensual(Integer.parseInt(costosFijos[i][2]));
				costoFijo.setFinanzas_id(Integer.parseInt(planId));
				manager.persist(costoFijo);	
					
			}
			
			else{
				Costos_Fijos costoFijo= manager.find(Costos_Fijos.class,Integer.parseInt(costosFijos[i][0]));
				
				costoFijo.setTipoCostos_id(Integer.parseInt(costosFijos[i][1]));
				costoFijo.setCostoMensual(Integer.parseInt(costosFijos[i][2]));
				costoFijo.setFinanzas_id(Integer.parseInt(planId));
				manager.persist(costoFijo);	
			}
			
			
			

		}

		
		return planId;
	}


	public String cargarGastosFijosAdministracion(String planId) {
		 StringBuilder objSecciones;


			
			Datos_Planes_Financieros datos=manager.find(Datos_Planes_Financieros.class, 1);
			
		    //Crea un array de objetos JSON con cada tema de la tabla
		      objSecciones = new StringBuilder("[");    

				
				  Query query=manager.createNamedQuery("Gastos_Fijos_Administracion.findByPlanId");
			      query.setParameter("id", Integer.parseInt(planId));
			  
			  List liste=query.getResultList();
			  
			  Vector<Gastos_Fijos_Administracion> gastosFijosAdministracion = new Vector<Gastos_Fijos_Administracion>();
				for (Object o : liste) {
					Gastos_Fijos_Administracion gasto = (Gastos_Fijos_Administracion) o;
					
					gastosFijosAdministracion.add(gasto);

				}
				  
				  objSecciones.append("{\"dato\":\"");
		      objSecciones.append("datosGastoFijoAdministracion");
		      objSecciones.append("\",\"valor\":\"");  		  
		      objSecciones.append(datos.getGastosFijosAdministracion());
		      objSecciones.append("\",\"planId\":\"");  		  
		      objSecciones.append("planId");
		      objSecciones.append("\",\"valorPlanId\":\"");  		  
		      objSecciones.append(planId);
		      objSecciones.append("\",\"tipoGasto\":");  		  
		      query=manager.createNamedQuery("Tipo_Gastos_Administracion.findAll");
		      
		  
		  List listaa=query.getResultList();
		  
		  Vector<Tipo_Gastos_Administracion> tipoGastoAdministracion = new Vector<Tipo_Gastos_Administracion>();
			for (Object o : listaa) {
				Tipo_Gastos_Administracion tipo = (Tipo_Gastos_Administracion) o;
				
				tipoGastoAdministracion.add(tipo);

			}
			
			objSecciones.append("[");
			for(int j=0;j<tipoGastoAdministracion.size();j++){
	  		  objSecciones.append("{\"tipoId\":\"");
	        objSecciones.append(tipoGastoAdministracion.get(j).getTipo_id());
	        objSecciones.append("\",\"Descripcion\":\"");  		  
	        objSecciones.append(tipoGastoAdministracion.get(j).getNombre());
	        objSecciones.append("\"},");
	        
	                 
	        }
			objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		      objSecciones.append("},");
		      
		      for(int i=0;i<gastosFijosAdministracion.size();i++){
				  objSecciones.append("{\"Id\":\"");
		      objSecciones.append(gastosFijosAdministracion.get(i).getGastoFijo_id());
		      objSecciones.append("\",\"Tipo\":\"");  		  
		      objSecciones.append(gastosFijosAdministracion.get(i).getGasto_id());
		      objSecciones.append("\",\"Valor\":\"");  		  
		      objSecciones.append(gastosFijosAdministracion.get(i).getCostoMensual());
		      objSecciones.append("\"},");
		      
		               
		      }
		      if(gastosFijosAdministracion.size()>0){
		  		objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		  		
		  		}
		  		else{
		  			objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"}");
		  			
		  		}
		                  
		    //Sustituye la última coma por el carácter de cierre del array
		      objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
			
			return objSecciones.toString();
	}


	public String guardarGastosFijosAdministracion(
			String[][] gastosFijosAdministracion, String planId) {
		//Actividad
		for(int i=0;i<gastosFijosAdministracion.length;i++){
			
			if(Integer.parseInt(gastosFijosAdministracion[i][0])<100){
				
				Query query=manager.createNamedQuery("Gastos_Fijos_Administracion.findAll");
				 List listamedios=query.getResultList();
				 
				 Vector<Gastos_Fijos_Administracion> gastoFijo=new Vector<Gastos_Fijos_Administracion>();
				 
				 
					for (Object o : listamedios) {
						Gastos_Fijos_Administracion inv = (Gastos_Fijos_Administracion) o;
						
						gastoFijo.add(inv);

					}
					int mayor=100;
					if(gastoFijo.size()>0)
					for(int j=0;j<gastoFijo.size();j++){
						if(mayor<=gastoFijo.get(j).getGastoFijo_id()){
							mayor=gastoFijo.get(j).getGastoFijo_id();
						}
					}
					else{
						mayor=99;
					}
					Gastos_Fijos_Administracion gastoFijoAdm=new Gastos_Fijos_Administracion();
				gastoFijoAdm.setGastoFijo_id(mayor+1);
				gastoFijoAdm.setGasto_id(Integer.parseInt(gastosFijosAdministracion[i][1]));
				gastoFijoAdm.setCostoMensual(Integer.parseInt(gastosFijosAdministracion[i][2]));
				gastoFijoAdm.setFinanzas_id(Integer.parseInt(planId));
				manager.persist(gastoFijoAdm);	
					
			}
			
			else{
				Gastos_Fijos_Administracion gastoFijoAdm= manager.find(Gastos_Fijos_Administracion.class,Integer.parseInt(gastosFijosAdministracion[i][0]));
				
				gastoFijoAdm.setGasto_id(Integer.parseInt(gastosFijosAdministracion[i][1]));
				gastoFijoAdm.setCostoMensual(Integer.parseInt(gastosFijosAdministracion[i][2]));
				gastoFijoAdm.setFinanzas_id(Integer.parseInt(planId));
				manager.persist(gastoFijoAdm);		
			}
			
			
			

		}

		
		return planId;
	}

	
	public String cargarGastosVentas(String planId) {
StringBuilder objSecciones;


		
		Datos_Planes_Financieros datos=manager.find(Datos_Planes_Financieros.class, 1);
		
	    //Crea un array de objetos JSON con cada tema de la tabla
	      objSecciones = new StringBuilder("[");    

			
			  Query query=manager.createNamedQuery("Gastos_Ventas.findByPlanId");
		      query.setParameter("id", Integer.parseInt(planId));
		  
		  List liste=query.getResultList();
		  
		  Vector<Gastos_Ventas> gastosVentas = new Vector<Gastos_Ventas>();
			for (Object o : liste) {
				Gastos_Ventas gasto = (Gastos_Ventas) o;
				
				gastosVentas.add(gasto);

			}
			  
			  objSecciones.append("{\"dato\":\"");
	      objSecciones.append("datosGastoFijoAdministracion");
	      objSecciones.append("\",\"valor\":\"");  		  
	      objSecciones.append(datos.getGastosVentas());
	      objSecciones.append("\",\"planId\":\"");  		  
	      objSecciones.append("planId");
	      objSecciones.append("\",\"valorPlanId\":\"");  		  
	      objSecciones.append(planId);
	      objSecciones.append("\",\"tipoGasto\":");  		  
	      query=manager.createNamedQuery("Tipo_Gasto_Ventas.findAll");
	      
	  
	  List listaa=query.getResultList();
	  
	  Vector<Tipo_Gasto_Ventas> tipoGastosVenta = new Vector<Tipo_Gasto_Ventas>();
		for (Object o : listaa) {
			Tipo_Gasto_Ventas tipo = (Tipo_Gasto_Ventas) o;
			
			tipoGastosVenta.add(tipo);

		}
		
		objSecciones.append("[");
		for(int j=0;j<tipoGastosVenta.size();j++){
  		  objSecciones.append("{\"tipoId\":\"");
        objSecciones.append(tipoGastosVenta.get(j).getTipo_id());
        objSecciones.append("\",\"Descripcion\":\"");  		  
        objSecciones.append(tipoGastosVenta.get(j).getNombre());
        objSecciones.append("\"},");
        
                 
        }
		objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
	      objSecciones.append("},");
	      
	      for(int i=0;i<gastosVentas.size();i++){
			  objSecciones.append("{\"Id\":\"");
	      objSecciones.append(gastosVentas.get(i).getGastos_id());
	      objSecciones.append("\",\"Tipo\":\"");  		  
	      objSecciones.append(gastosVentas.get(i).getTipoGasto_id());
	      objSecciones.append("\",\"Valor\":\"");  		  
	      objSecciones.append(gastosVentas.get(i).getCostoMensual());
	      objSecciones.append("\"},");
	      
	               
	      }
	      if(gastosVentas.size()>0){
	  		objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
	  		
	  		}
	  		else{
	  			objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"}");
	  			
	  		}
	                  
	    //Sustituye la última coma por el carácter de cierre del array
	      objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}


	public String guardarGastosVentas(String[][] gastosVentas, String planId) {
		//Actividad
		for(int i=0;i<gastosVentas.length;i++){
			
			if(Integer.parseInt(gastosVentas[i][0])<100){
				
				Query query=manager.createNamedQuery("Gastos_Ventas.findAll");
				 List listamedios=query.getResultList();
				 
				 Vector<Gastos_Ventas> gastosVentasV=new Vector<Gastos_Ventas>();
				 
				 
					for (Object o : listamedios) {
						Gastos_Ventas inv = (Gastos_Ventas) o;
						
						gastosVentasV.add(inv);

					}
					int mayor=100;
					if(gastosVentasV.size()>0)
					for(int j=0;j<gastosVentasV.size();j++){
						if(mayor<=gastosVentasV.get(j).getGastos_id()){
							mayor=gastosVentasV.get(j).getGastos_id();
						}
					}
					else{
						mayor=99;
					}
					Gastos_Ventas gastVent=new Gastos_Ventas();
				gastVent.setGastos_id(mayor+1);
				gastVent.setTipoGasto_id(Integer.parseInt(gastosVentas[i][1]));
				gastVent.setCostoMensual(Integer.parseInt(gastosVentas[i][2]));
				gastVent.setFinanzas_id(Integer.parseInt(planId));
				manager.persist(gastVent);	
					
			}
			
			else{
				Gastos_Ventas gastVent= manager.find(Gastos_Ventas.class,Integer.parseInt(gastosVentas[i][0]));
				
				gastVent.setTipoGasto_id(Integer.parseInt(gastosVentas[i][1]));
				gastVent.setCostoMensual(Integer.parseInt(gastosVentas[i][2]));
				gastVent.setFinanzas_id(Integer.parseInt(planId));
				manager.persist(gastVent);		
			}
			
			
			

		}

		
		return planId;
	}


	public String cargarEmpleados(String planId) {
StringBuilder objSecciones;


		
		Datos_Planes_Financieros datos=manager.find(Datos_Planes_Financieros.class, 1);
		
	    //Crea un array de objetos JSON con cada tema de la tabla
	      objSecciones = new StringBuilder("[");    

			
			  Query query=manager.createNamedQuery("Empleados.findByPlanId");
		      query.setParameter("id", Integer.parseInt(planId));
		  
		  List liste=query.getResultList();
		  
		  Vector<Empleados> empleados = new Vector<Empleados>();
			for (Object o : liste) {
				Empleados empleado = (Empleados) o;
				
				empleados.add(empleado);

			}
			  
			  objSecciones.append("{\"dato\":\"");
	      objSecciones.append("datosEmpleados");
	      objSecciones.append("\",\"valor\":\"");  		  
	      objSecciones.append(datos.getEmpleados());
	      objSecciones.append("\",\"planId\":\"");  		  
	      objSecciones.append("planId");
	      objSecciones.append("\",\"valorPlanId\":\"");  		  
	      objSecciones.append(planId);
	      objSecciones.append("\",\"tipoCargo\":");  		  
	      query=manager.createNamedQuery("Tipo_De_Cargo.findAll");
	      
	  
	  List listaa=query.getResultList();
	  
	  Vector<Tipo_De_Cargo> tiposCargo = new Vector<Tipo_De_Cargo>();
		for (Object o : listaa) {
			Tipo_De_Cargo tipo = (Tipo_De_Cargo) o;
			
			tiposCargo.add(tipo);

		}
		
		objSecciones.append("[");
		for(int j=0;j<tiposCargo.size();j++){
  		  objSecciones.append("{\"tipoId\":\"");
        objSecciones.append(tiposCargo.get(j).getTipo_id());
        objSecciones.append("\",\"Descripcion\":\"");  		  
        objSecciones.append(tiposCargo.get(j).getNombre());
        objSecciones.append("\"},");
        
                 
        }
		objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
	      objSecciones.append("},");
	      
	      for(int i=0;i<empleados.size();i++){
			  objSecciones.append("{\"Id\":\"");
	      objSecciones.append(empleados.get(i).getEmpleado_id());
	      objSecciones.append("\",\"Tipo\":\"");  		  
	      objSecciones.append(empleados.get(i).getTipoCargo_id());
	      objSecciones.append("\",\"Sueldo\":\"");  		  
	      objSecciones.append(empleados.get(i).getSueldo());
	      objSecciones.append("\",\"NumeroEmpleados\":\"");  		  
	      objSecciones.append(empleados.get(i).getNumeroEmpleados());
	      objSecciones.append("\",\"Prestaciones\":\"");  		  
	      objSecciones.append(empleados.get(i).getPrestaciones());
	      objSecciones.append("\",\"ProcPrestaciones\":\"");  		  
	      objSecciones.append(empleados.get(i).getPorcPrestaciones());
	      objSecciones.append("\",\"Total\":\"");  		  
	      objSecciones.append(empleados.get(i).getTotalMensual());
	      objSecciones.append("\"},");
	      
	               
	      }
	      if(empleados.size()>0){
	  		objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
	  		
	  		}
	  		else{
	  			objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"}");
	  			
	  		}
	                  
	    //Sustituye la última coma por el carácter de cierre del array
	      objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}

	
	public String guardarEmpleados(String[][] empleados, String planId) {
		//Actividad
		for(int i=0;i<empleados.length;i++){
			
			if(Integer.parseInt(empleados[i][0])<100){
				
				Query query=manager.createNamedQuery("Empleados.findAll");
				 List listamedios=query.getResultList();
				 
				 Vector<Empleados> empleadosV=new Vector<Empleados>();
				 
				 
					for (Object o : listamedios) {
						Empleados inv = (Empleados) o;
						
						empleadosV.add(inv);

					}
					int mayor=100;
					if(empleadosV.size()>0)
					for(int j=0;j<empleadosV.size();j++){
						if(mayor<=empleadosV.get(j).getEmpleado_id()){
							mayor=empleadosV.get(j).getEmpleado_id();
						}
					}
					else{
						mayor=99;
					}
					Empleados empleado=new Empleados();
				empleado.setEmpleado_id(mayor+1);
				empleado.setTipoCargo_id(Integer.parseInt(empleados[i][1]));
				empleado.setSueldo(Integer.parseInt(empleados[i][2]));
				empleado.setNumeroEmpleados(Integer.parseInt(empleados[i][3]));
				empleado.setPrestaciones((empleados[i][4].equals("true")));
				empleado.setPorcPrestaciones(Integer.parseInt(empleados[i][5]));
				empleado.setTotalMensual(Integer.parseInt(empleados[i][6]));
				empleado.setFinanzas_id(Integer.parseInt(planId));
				manager.persist(empleado);	
					
			}
			
			else{
				Empleados empleado= manager.find(Empleados.class,Integer.parseInt(empleados[i][0]));
				
				empleado.setTipoCargo_id(Integer.parseInt(empleados[i][1]));
				empleado.setSueldo(Integer.parseInt(empleados[i][2]));
				empleado.setNumeroEmpleados(Integer.parseInt(empleados[i][3]));
				empleado.setPrestaciones((empleados[i][4].equals("true")));
				empleado.setPorcPrestaciones(Integer.parseInt(empleados[i][5]));
				empleado.setTotalMensual(Integer.parseInt(empleados[i][6]));
				empleado.setFinanzas_id(Integer.parseInt(planId));
				manager.persist(empleado);		
			}
			
			
			

		}

		
		return planId;
	}

	
	public String cargarImpuestos(String planId) {
		StringBuilder objSecciones;
		
		Query query=manager.createNamedQuery("Impuestos.findByPlanId");
		query.setParameter("id", Integer.parseInt(planId));
		
		List listaMedios=query.getResultList();

		Vector<Impuestos> impuestos=new Vector<Impuestos>();


		for (Object o : listaMedios) {
			Impuestos impuesto = (Impuestos) o;

			impuestos.add(impuesto);

		}
//Crea un array de objetos JSON con cada tema de la tabla
  objSecciones = new StringBuilder("[");    
  

	
	Datos_Planes_Financieros datos=manager.find(Datos_Planes_Financieros.class, 1);
	  
	  objSecciones.append("{\"dato\":\"");
  objSecciones.append("datosMediosComunicacion");
  objSecciones.append("\",\"valor\":\"");  		  
  objSecciones.append(datos.getImpuestos());
  objSecciones.append("\",\"planId\":\"");  		  
  objSecciones.append("planId");
  objSecciones.append("\",\"valorPlanId\":\"");  		  
  objSecciones.append(planId);
  objSecciones.append("\"},");
  
  
  for(int i=0;i<impuestos.size();i++){
	  objSecciones.append("{\"id\":\"");
  objSecciones.append(impuestos.get(i).getImpuesto_id());
  objSecciones.append("\",\"Nombre\":\"");  		  
  objSecciones.append(impuestos.get(i).getNombre());
  objSecciones.append("\",\"Porcentaje\":\"");  		  
  objSecciones.append(impuestos.get(i).getPorcentaje());
  objSecciones.append("\"},");
  
           
  }
           
  

              
//Sustituye la última coma por el carácter de cierre del array
  objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");

return objSecciones.toString();
	}


	public String guardarImpuestos(String[][] impuestos, String planId) {
		//Actividad
		for(int i=0;i<impuestos.length;i++){
			
			if(Integer.parseInt(impuestos[i][0])<100){
				
				Query query=manager.createNamedQuery("Impuestos.findAll");
				 List listamedios=query.getResultList();
				 
				 Vector<Impuestos> impuestosV=new Vector<Impuestos>();
				 
				 
					for (Object o : listamedios) {
						Impuestos rmed = (Impuestos) o;
						
						impuestosV.add(rmed);

					}
					int mayor=100;
					if(impuestosV.size()>0)
					for(int j=0;j<impuestosV.size();j++){
						if(mayor<=impuestosV.get(j).getImpuesto_id()){
							mayor=impuestosV.get(j).getImpuesto_id();
						}
					}
					else{
						mayor=99;
					}
					Impuestos impu=new Impuestos();
				impu.setImpuesto_id(mayor+1);
				impu.setNombre(impuestos[i][1].replace("\n", "<br>"));
				impu.setPorcentaje(Integer.parseInt(impuestos[i][2]));
				impu.setFinanzas_id(Integer.parseInt(planId));
				manager.persist(impu);	
					
			}
			
			else{
				Impuestos impu= manager.find(Impuestos.class,Integer.parseInt(impuestos[i][0]));
				
				impu.setNombre(impuestos[i][1].replace("\n", "<br>"));
				impu.setPorcentaje(Integer.parseInt(impuestos[i][2]));
				impu.setFinanzas_id(Integer.parseInt(planId));
				manager.persist(impu);
			}
			
			
			

		}

		
		return planId;
	}

	
	public String cargarProyeccion(String planId) {
		
     StringBuilder objSecciones;
		
		Proyecciones proyeccion=manager.find(Proyecciones.class, Integer.parseInt(planId));
		
		
	
		
		


//Crea un array de objetos JSON con cada tema de la tabla
  objSecciones = new StringBuilder("[");    
  

	
	Datos_Planes_Financieros datos=manager.find(Datos_Planes_Financieros.class, 1);
	  
	  objSecciones.append("{\"dato\":\"");
  objSecciones.append("datosProyeccion");
  objSecciones.append("\",\"valor\":\"");  		  
  objSecciones.append(datos.getDatosProyeccion());
  objSecciones.append("\",\"planId\":\"");  		  
  objSecciones.append("planId");
  objSecciones.append("\",\"valorPlanId\":\"");  		  
  objSecciones.append(planId);
  objSecciones.append("\",\"Meses\":");  		  
  Query query=manager.createNamedQuery("Meses.findAll");
  

  	List listaa=query.getResultList();

  		Vector<Meses> meses = new Vector<Meses>();
  			for (Object o : listaa) {
  				Meses mes = (Meses) o;
	
  						meses.add(mes);

  				}

  				objSecciones.append("[");
  				for(int j=0;j<meses.size();j++){
	  objSecciones.append("{\"Valor\":\"");
	  objSecciones.append(meses.get(j).getMes());
	  objSecciones.append("\"},");

         
  				}
  				objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
  				objSecciones.append(",\"Anos\":");  
  				objSecciones.append("[");
  				Calendar fecha=Calendar.getInstance();
  				 int año=fecha.get(Calendar.YEAR);
  				  System.out.println(año);
  				  
  				  for(int i=0;i<10;i++){
  					  
  					  objSecciones.append("{\"Valor\":\"");
  					  objSecciones.append(año+i);
  					  objSecciones.append("\"},");
  				  }
  				objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
  				objSecciones.append("},");
 
	if(proyeccion!=null){
	  objSecciones.append("{\"id\":\"");
  objSecciones.append(proyeccion.getProyeccion_id());
  objSecciones.append("\",\"Mes\":\"");  		  
  objSecciones.append(proyeccion.getMesInicio());
  objSecciones.append("\",\"Ano\":\"");  		  
  objSecciones.append(proyeccion.getAñoInicio());
  objSecciones.append("\",\"NumAno\":\"");  		  
  objSecciones.append(proyeccion.getNumeroAños());
  objSecciones.append("\"},");
	}
           
  
           
  

              
//Sustituye la última coma por el carácter de cierre del array
  objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");

return objSecciones.toString();
	}


	public String guardarProyeccion(String mes, int ano, int numAnos, int planId) {
		Proyecciones pro=manager.find(Proyecciones.class, planId);
		if(pro==null){
			pro=new Proyecciones();
			pro.setFinanzas_id(planId);
			pro.setProyeccion_id(planId);
			
		}
		pro.setMesInicio(mes);
		pro.setAñoInicio(ano);
		pro.setNumeroAños(numAnos);
		manager.persist(pro);
		
		Query query=manager.createNamedQuery("Productos.findByPlanId");
	      query.setParameter("id", planId);
	  
	  List liste=query.getResultList();
	  
	  Vector<Productos> productos = new Vector<Productos>();
		for (Object o : liste) {
			Productos producto = (Productos) o;
			
			productos.add(producto);

		}
		
		 query=manager.createNamedQuery("Costos_Fijos.findByPlanId");
	      query.setParameter("id", planId);
	  
	   liste=query.getResultList();
	  
	  Vector<Costos_Fijos> costosFijos = new Vector<Costos_Fijos>();
		for (Object o : liste) {
			Costos_Fijos costoF = (Costos_Fijos) o;
			
			costosFijos.add(costoF);

		}
		
		 query=manager.createNamedQuery("Gastos_Fijos_Administracion.findByPlanId");
	      query.setParameter("id", planId);
	  
	   liste=query.getResultList();
	  
	  Vector<Gastos_Fijos_Administracion> gastosFijosAdministracion = new Vector<Gastos_Fijos_Administracion>();
		for (Object o : liste) {
			Gastos_Fijos_Administracion gasto = (Gastos_Fijos_Administracion) o;
			
			gastosFijosAdministracion.add(gasto);

		}
		
		query=manager.createNamedQuery("Gastos_Ventas.findByPlanId");
	      query.setParameter("id", planId);
	  
	  liste=query.getResultList();
	  
	  Vector<Gastos_Ventas> gastosVentas = new Vector<Gastos_Ventas>();
		for (Object o : liste) {
			Gastos_Ventas gasto = (Gastos_Ventas) o;
			
			gastosVentas.add(gasto);

		}
		
		query=manager.createNamedQuery("Empleados.findByPlanId");
	      query.setParameter("id", planId);
	  
	  liste=query.getResultList();
	  
	  Vector<Empleados> empleados = new Vector<Empleados>();
		for (Object o : liste) {
			Empleados empleado = (Empleados) o;
			
			empleados.add(empleado);

		}
		
		for(int i=0;i<productos.size();i++){
			for(int j=0;j<numAnos;j++){
				Año_proyecciones_ventas año=manager.find(Año_proyecciones_ventas.class, new Año_proyecciones_ventasPK(j,productos.get(i).getProducto_id()));
				if(año==null){
				año=new Año_proyecciones_ventas();
				}
				año.setAñoId(j);
				año.setProductosId(productos.get(i).getProducto_id());
				año.setMes1(0);
				año.setMes2(0);
				año.setMes3(0);
				año.setMes4(0);
				año.setMes5(0);
				año.setMes6(0);
				año.setMes7(0);
				año.setMes8(0);
				año.setMes9(0);
				año.setMes10(0);
				año.setMes11(0);
				año.setMes12(0);
				manager.persist(año);
				
			}
			
		}
		for(int i=0;i<costosFijos.size();i++){
			for(int j=0;j<numAnos;j++){
				Año_Proyecciones_Costos_Fijos año=manager.find(Año_Proyecciones_Costos_Fijos.class, new Año_Proyecciones_Costos_FijosPK(j,costosFijos.get(i).getCostoFijo_id()));
				if(año==null){
				 año=new Año_Proyecciones_Costos_Fijos();
				}
				año.setAñoId(j);
				año.setCostosId(costosFijos.get(i).getCostoFijo_id());
				año.setMes1(true);
				año.setMes2(true);
				año.setMes3(true);
				año.setMes4(true);
				año.setMes5(true);
				año.setMes6(true);
				año.setMes7(true);
				año.setMes8(true);
				año.setMes9(true);
				año.setMes10(true);
				año.setMes11(true);
				año.setMes12(true);
				manager.persist(año);
				
			}
			
		}
		for(int i=0;i<gastosFijosAdministracion.size();i++){
			for(int j=0;j<numAnos;j++){
				Año_Proyecciones_Gastos_Fijos_Administracion año=manager.find(Año_Proyecciones_Gastos_Fijos_Administracion.class, new Año_Proyecciones_Gastos_Fijos_AdministracionPK(j,gastosFijosAdministracion.get(i).getGastoFijo_id()));
				if(año==null){
				 año=new Año_Proyecciones_Gastos_Fijos_Administracion();
				}
				año.setAñoId(j);
				año.setGastoFijoId(gastosFijosAdministracion.get(i).getGastoFijo_id());
				año.setMes1(true);
				año.setMes2(true);
				año.setMes3(true);
				año.setMes4(true);
				año.setMes5(true);
				año.setMes6(true);
				año.setMes7(true);
				año.setMes8(true);
				año.setMes9(true);
				año.setMes10(true);
				año.setMes11(true);
				año.setMes12(true);
				manager.persist(año);
			}
			
		}
		for(int i=0;i<gastosVentas.size();i++){
			for(int j=0;j<numAnos;j++){
				Año_Proyecciones_Gastos_Ventas año=manager.find(Año_Proyecciones_Gastos_Ventas.class, new Año_Proyecciones_Gastos_VentasPK(j,gastosVentas.get(i).getGastos_id()));
				if(año==null){
				 año=new Año_Proyecciones_Gastos_Ventas();
				}
				año.setAñoId(j);
				año.setGastoVentaId(gastosVentas.get(i).getGastos_id());
				año.setMes1(true);
				año.setMes2(true);
				año.setMes3(true);
				año.setMes4(true);
				año.setMes5(true);
				año.setMes6(true);
				año.setMes7(true);
				año.setMes8(true);
				año.setMes9(true);
				año.setMes10(true);
				año.setMes11(true);
				año.setMes12(true);
				manager.persist(año);
			}
			
		}
		for(int i=0;i<empleados.size();i++){
			for(int j=0;j<numAnos;j++){
				Año_Proyecciones_Empleados año=manager.find(Año_Proyecciones_Empleados.class, new Año_Proyecciones_EmpleadosPK(j,empleados.get(i).getEmpleado_id()));
				if(año==null){
				año=new Año_Proyecciones_Empleados();
				}
				año.setAñoId(j);
				año.setEmpleadoId(empleados.get(i).getEmpleado_id());
				año.setMes1(true);
				año.setMes2(true);
				año.setMes3(true);
				año.setMes4(true);
				año.setMes5(true);
				año.setMes6(true);
				año.setMes7(true);
				año.setMes8(true);
				año.setMes9(true);
				año.setMes10(true);
				año.setMes11(true);
				año.setMes12(true);
				manager.persist(año);
			}
			
		}
		
		return planId+"";
	}

	
	public String cargarProyeccionProductos(String planId) {
		 StringBuilder objSecciones;
			
			
			Query query=manager.createNamedQuery("Productos.findByPlanId");
			query.setParameter("id", Integer.parseInt(planId));
			
			List listaMedios=query.getResultList();

			Vector<Productos> productos=new Vector<Productos>();


			for (Object o : listaMedios) {
				Productos producto = (Productos) o;

				productos.add(producto);

			}
		
			
			


	//Crea un array de objetos JSON con cada tema de la tabla
	  objSecciones = new StringBuilder("[");    
	  

		
		Datos_Planes_Financieros datos=manager.find(Datos_Planes_Financieros.class, 1);
		  
		  objSecciones.append("{\"dato\":\"");
	  objSecciones.append("datosProyeccionProductos");
	  objSecciones.append("\",\"valor\":\"");  		  
	  objSecciones.append(datos.getProyeccionProductos());
	  objSecciones.append("\",\"planId\":\"");  		  
	  objSecciones.append("planId");
	  objSecciones.append("\",\"valorPlanId\":\"");  		  
	  objSecciones.append(planId);
	  objSecciones.append("\",\"Productos\":");  
		objSecciones.append("[");

		  
		  for(int i=0;i<productos.size();i++){
			  
			  objSecciones.append("{\"Nombre\":\"");
			  objSecciones.append(productos.get(i).getNombre());
			  objSecciones.append("\",\"Id\":\"");  		  
			  objSecciones.append(productos.get(i).getProducto_id());
			  objSecciones.append("\",\"Anos\":");  
				objSecciones.append("[");
				query=manager.createNamedQuery("Año_proyecciones_ventas.findByProductoId");
				query.setParameter("id",productos.get(i).getProducto_id());
				
				List lista=query.getResultList();

				Vector<Año_proyecciones_ventas> añosProyecciones=new Vector<Año_proyecciones_ventas>();


				for (Object o : lista) {
					Año_proyecciones_ventas año = (Año_proyecciones_ventas) o;

					añosProyecciones.add(año);

				}
				  
				  for(int j=0;j<añosProyecciones.size();j++){
					  
					  objSecciones.append("{\"anoId\":\"");
					  objSecciones.append(añosProyecciones.get(j).getAñoId());
					  objSecciones.append("\",\"DatoId\":\"");  		  
					  objSecciones.append(añosProyecciones.get(j).getProductosId());
					  if(añosProyecciones.get(j).getAñoId()==0){
					  objSecciones.append("\",\"mes1\":\"");  		  
					  objSecciones.append(añosProyecciones.get(j).getMes1());
					  objSecciones.append("\",\"mes2\":\"");  		  
					  objSecciones.append(añosProyecciones.get(j).getMes2());
					  objSecciones.append("\",\"mes3\":\"");  		  
					  objSecciones.append(añosProyecciones.get(j).getMes3());
					  objSecciones.append("\",\"mes4\":\"");  		  
					  objSecciones.append(añosProyecciones.get(j).getMes4());
					  objSecciones.append("\",\"mes5\":\"");  		  
					  objSecciones.append(añosProyecciones.get(j).getMes5());
					  objSecciones.append("\",\"mes6\":\"");  		  
					  objSecciones.append(añosProyecciones.get(j).getMes6());
					  objSecciones.append("\",\"mes7\":\"");  		  
					  objSecciones.append(añosProyecciones.get(j).getMes7());
					  objSecciones.append("\",\"mes8\":\"");  		  
					  objSecciones.append(añosProyecciones.get(j).getMes8());
					  objSecciones.append("\",\"mes9\":\"");  		  
					  objSecciones.append(añosProyecciones.get(j).getMes9());
					  objSecciones.append("\",\"mes10\":\"");  		  
					  objSecciones.append(añosProyecciones.get(j).getMes10());
					  objSecciones.append("\",\"mes11\":\"");  		  
					  objSecciones.append(añosProyecciones.get(j).getMes11());
					  objSecciones.append("\",\"mes12\":\"");  		  
					  objSecciones.append(añosProyecciones.get(j).getMes12());
					  }
					  else{
						  objSecciones.append("\",\"porcentajeIncremento\":\"");  		  
						  objSecciones.append(añosProyecciones.get(j).getPorcentajeIncremento());
					  }
					  objSecciones.append("\"},");
				  }
				objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
				objSecciones.append("},");
		  }
		objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		objSecciones.append("},");
	           
	  
	           
	  

	              
	//Sustituye la última coma por el carácter de cierre del array
	  objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");

	return objSecciones.toString();
	}

	
	public int getNumeroDeProductos(String planId) {
		Query query=manager.createNamedQuery("Productos.findByPlanId");
	      query.setParameter("id", Integer.parseInt(planId));
	  
	  List liste=query.getResultList();
	  
	  Vector<Productos> productos = new Vector<Productos>();
		for (Object o : liste) {
			Productos producto = (Productos) o;
			
			productos.add(producto);

		}
		
		return productos.size();
	}

	
	public int getNumeroDeAños(String planId) {
		Proyecciones pro=manager.find(Proyecciones.class, Integer.parseInt(planId));
		return pro.getNumeroAños();
	}

	
	public String guardarProyeccionProductos(String[][] proyProductos,String planId) {
		
		int numAnos=getNumeroDeAños(planId);
		for(int i=0;i<proyProductos.length;i++){
			for(int j=0;j<numAnos;j++){
			Año_proyecciones_ventas pro=manager.find(Año_proyecciones_ventas.class, new Año_proyecciones_ventasPK(j,Integer.parseInt(proyProductos[i][0])));
			if(j==0){
				pro.setMes1(Integer.parseInt(proyProductos[i][1]));
				pro.setMes2(Integer.parseInt(proyProductos[i][2]));
				pro.setMes3(Integer.parseInt(proyProductos[i][3]));
				pro.setMes4(Integer.parseInt(proyProductos[i][4]));
				pro.setMes5(Integer.parseInt(proyProductos[i][5]));
				pro.setMes6(Integer.parseInt(proyProductos[i][6]));
				pro.setMes7(Integer.parseInt(proyProductos[i][7]));
				pro.setMes8(Integer.parseInt(proyProductos[i][8]));
				pro.setMes9(Integer.parseInt(proyProductos[i][9]));
				pro.setMes10(Integer.parseInt(proyProductos[i][10]));
				pro.setMes11(Integer.parseInt(proyProductos[i][11]));
				pro.setMes12(Integer.parseInt(proyProductos[i][12]));
				
			}
			else{
				
					pro.setPorcentajeIncremento(Integer.parseInt(proyProductos[i][12+j]));
				
			}
			manager.persist(pro);
			}
		}
		return planId;
	}


	public String cargarProyeccionGastosFijos(String planId) {
		StringBuilder objSecciones;
		
		
		Query query=manager.createNamedQuery("Gastos_Fijos_Administracion.findByPlanId");
		query.setParameter("id", Integer.parseInt(planId));
		
		List listaMedios=query.getResultList();

		Vector<Gastos_Fijos_Administracion> gastosFijos=new Vector<Gastos_Fijos_Administracion>();


		for (Object o : listaMedios) {
			Gastos_Fijos_Administracion gasto = (Gastos_Fijos_Administracion) o;

			gastosFijos.add(gasto);

		}
	
		
		


//Crea un array de objetos JSON con cada tema de la tabla
  objSecciones = new StringBuilder("[");    
  

	
	Datos_Planes_Financieros datos=manager.find(Datos_Planes_Financieros.class, 1);
	  
	  objSecciones.append("{\"dato\":\"");
  objSecciones.append("datosProyeccionGastos");
  objSecciones.append("\",\"valor\":\"");  		  
  objSecciones.append(datos.getProyeccionGastos());
  objSecciones.append("\",\"planId\":\"");  		  
  objSecciones.append("planId");
  objSecciones.append("\",\"valorPlanId\":\"");  		  
  objSecciones.append(planId);
  objSecciones.append("\",\"GastosFijos\":");  
	objSecciones.append("[");

	  
	  for(int i=0;i<gastosFijos.size();i++){
		  
		  objSecciones.append("{\"Nombre\":\"");
		  objSecciones.append(manager.find(Tipo_Gastos_Administracion.class, gastosFijos.get(i).getGasto_id()).getNombre());
		  objSecciones.append("\",\"Id\":\"");  		  
		  objSecciones.append(gastosFijos.get(i).getGastoFijo_id());
		  objSecciones.append("\",\"Anos\":");  
			objSecciones.append("[");
			query=manager.createNamedQuery("Año_Proyecciones_Gastos_Fijos_Administracion.findByGastoId");
			query.setParameter("id",gastosFijos.get(i).getGastoFijo_id());
			
			List lista=query.getResultList();

			Vector<Año_Proyecciones_Gastos_Fijos_Administracion> añosProyecciones=new Vector<Año_Proyecciones_Gastos_Fijos_Administracion>();


			for (Object o : lista) {
				Año_Proyecciones_Gastos_Fijos_Administracion año = (Año_Proyecciones_Gastos_Fijos_Administracion) o;

				añosProyecciones.add(año);

			}
			  
			  for(int j=0;j<añosProyecciones.size();j++){
				  
				  objSecciones.append("{\"anoId\":\"");
				  objSecciones.append(añosProyecciones.get(j).getAñoId());
				  objSecciones.append("\",\"DatoId\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).getGastoFijoId());
				  objSecciones.append("\",\"mes1\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes1());
				  objSecciones.append("\",\"mes2\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes2());
				  objSecciones.append("\",\"mes3\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes3());
				  objSecciones.append("\",\"mes4\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes4());
				  objSecciones.append("\",\"mes5\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes5());
				  objSecciones.append("\",\"mes6\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes6());
				  objSecciones.append("\",\"mes7\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes7());
				  objSecciones.append("\",\"mes8\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes8());
				  objSecciones.append("\",\"mes9\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes9());
				  objSecciones.append("\",\"mes10\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes10());
				  objSecciones.append("\",\"mes11\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes11());
				  objSecciones.append("\",\"mes12\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes12());
				  objSecciones.append("\"},");
			  }
			objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
			objSecciones.append("},");
	  }
	objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
	objSecciones.append("},");
           
  
           
  

              
//Sustituye la última coma por el carácter de cierre del array
  objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");

return objSecciones.toString();
	}


	public int getNumeroDeGastosFijos(String planId) {
		Query query=manager.createNamedQuery("Gastos_Fijos_Administracion.findByPlanId");
	      query.setParameter("id", Integer.parseInt(planId));
	  
	  List liste=query.getResultList();
	  
	  Vector<Gastos_Fijos_Administracion> gastosFijos = new Vector<Gastos_Fijos_Administracion>();
		for (Object o : liste) {
			Gastos_Fijos_Administracion gastoFijo = (Gastos_Fijos_Administracion) o;
			
			gastosFijos.add(gastoFijo);

		}
		
		return gastosFijos.size();
	}

	
	public String guardarProyeccionGastosFijos(String[][] proyGastosFijos,
			String planId) {

		int numAnos=getNumeroDeAños(planId);
		for(int i=0;i<proyGastosFijos.length;i++){
			
			for(int j=0,h=0;j<numAnos;h+=12,j++){
				Año_Proyecciones_Gastos_Fijos_Administracion pro=manager.find(Año_Proyecciones_Gastos_Fijos_Administracion.class, new Año_Proyecciones_Gastos_Fijos_AdministracionPK(j,Integer.parseInt(proyGastosFijos[i][0])));
		
				pro.setMes1(Boolean.parseBoolean(proyGastosFijos[i][h+1]));
				pro.setMes2(Boolean.parseBoolean(proyGastosFijos[i][h+2]));
				pro.setMes3(Boolean.parseBoolean(proyGastosFijos[i][h+3]));
				pro.setMes4(Boolean.parseBoolean(proyGastosFijos[i][h+4]));
				pro.setMes5(Boolean.parseBoolean(proyGastosFijos[i][h+5]));
				pro.setMes6(Boolean.parseBoolean(proyGastosFijos[i][h+6]));
				pro.setMes7(Boolean.parseBoolean(proyGastosFijos[i][h+7]));
				pro.setMes8(Boolean.parseBoolean(proyGastosFijos[i][h+8]));
				pro.setMes9(Boolean.parseBoolean(proyGastosFijos[i][h+9]));
				pro.setMes10(Boolean.parseBoolean(proyGastosFijos[i][h+10]));
				pro.setMes11(Boolean.parseBoolean(proyGastosFijos[i][h+11]));
				pro.setMes12(Boolean.parseBoolean(proyGastosFijos[i][h+12]));
				
	
			manager.persist(pro);
			}
		}
		return planId;
		
	}


	public String cargarProyeccionCostosFijos(String planId) {
StringBuilder objSecciones;
		
		
		Query query=manager.createNamedQuery("Costos_Fijos.findByPlanId");
		query.setParameter("id", Integer.parseInt(planId));
		
		List listaMedios=query.getResultList();

		Vector<Costos_Fijos> costosFijos=new Vector<Costos_Fijos>();


		for (Object o : listaMedios) {
			Costos_Fijos costo = (Costos_Fijos) o;

			costosFijos.add(costo);

		}
	
		
		


//Crea un array de objetos JSON con cada tema de la tabla
  objSecciones = new StringBuilder("[");    
  

	
	Datos_Planes_Financieros datos=manager.find(Datos_Planes_Financieros.class, 1);
	  
	  objSecciones.append("{\"dato\":\"");
  objSecciones.append("datosProyeccionCostos");
  objSecciones.append("\",\"valor\":\"");  		  
  objSecciones.append(datos.getProyeccionCostos());
  objSecciones.append("\",\"planId\":\"");  		  
  objSecciones.append("planId");
  objSecciones.append("\",\"valorPlanId\":\"");  		  
  objSecciones.append(planId);
  objSecciones.append("\",\"CostosFijos\":");  
	objSecciones.append("[");

	  
	  for(int i=0;i<costosFijos.size();i++){
		  
		  objSecciones.append("{\"Nombre\":\"");
		  objSecciones.append(manager.find(Tipo_Costos_Fijos.class, costosFijos.get(i).getTipoCostos_id()).getNombre());
		  objSecciones.append("\",\"Id\":\"");  		  
		  objSecciones.append(costosFijos.get(i).getCostoFijo_id());
		  objSecciones.append("\",\"Anos\":");  
			objSecciones.append("[");
			query=manager.createNamedQuery("Año_Proyecciones_Costos_Fijos.findByCostoId");
			query.setParameter("id",costosFijos.get(i).getCostoFijo_id());
			
			List lista=query.getResultList();

			Vector<Año_Proyecciones_Costos_Fijos> añosProyecciones=new Vector<Año_Proyecciones_Costos_Fijos>();


			for (Object o : lista) {
				Año_Proyecciones_Costos_Fijos año = (Año_Proyecciones_Costos_Fijos) o;

				añosProyecciones.add(año);

			}
			  
			  for(int j=0;j<añosProyecciones.size();j++){
				  
				  objSecciones.append("{\"anoId\":\"");
				  objSecciones.append(añosProyecciones.get(j).getAñoId());
				  objSecciones.append("\",\"DatoId\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).getCostosId());
				  objSecciones.append("\",\"mes1\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes1());
				  objSecciones.append("\",\"mes2\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes2());
				  objSecciones.append("\",\"mes3\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes3());
				  objSecciones.append("\",\"mes4\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes4());
				  objSecciones.append("\",\"mes5\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes5());
				  objSecciones.append("\",\"mes6\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes6());
				  objSecciones.append("\",\"mes7\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes7());
				  objSecciones.append("\",\"mes8\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes8());
				  objSecciones.append("\",\"mes9\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes9());
				  objSecciones.append("\",\"mes10\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes10());
				  objSecciones.append("\",\"mes11\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes11());
				  objSecciones.append("\",\"mes12\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes12());
				  objSecciones.append("\"},");
			  }
			objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
			objSecciones.append("},");
	  }
	objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
	objSecciones.append("},");
           
  
           
  

              
//Sustituye la última coma por el carácter de cierre del array
  objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");

return objSecciones.toString();
	}

	
	public String cargarProyeccionVentas(String planId) {
StringBuilder objSecciones;
		
		
		Query query=manager.createNamedQuery("Gastos_Ventas.findByPlanId");
		query.setParameter("id", Integer.parseInt(planId));
		
		List listaMedios=query.getResultList();

		Vector<Gastos_Ventas> ventas=new Vector<Gastos_Ventas>();


		for (Object o : listaMedios) {
			Gastos_Ventas costo = (Gastos_Ventas) o;

			ventas.add(costo);

		}
	
		
		


//Crea un array de objetos JSON con cada tema de la tabla
  objSecciones = new StringBuilder("[");    
  

	
	Datos_Planes_Financieros datos=manager.find(Datos_Planes_Financieros.class, 1);
	  
	  objSecciones.append("{\"dato\":\"");
  objSecciones.append("datosProyeccionCostos");
  objSecciones.append("\",\"valor\":\"");  		  
  objSecciones.append(datos.getProyeccionVentas());
  objSecciones.append("\",\"planId\":\"");  		  
  objSecciones.append("planId");
  objSecciones.append("\",\"valorPlanId\":\"");  		  
  objSecciones.append(planId);
  objSecciones.append("\",\"Ventas\":");  
	objSecciones.append("[");

	  
	  for(int i=0;i<ventas.size();i++){
		  
		  objSecciones.append("{\"Nombre\":\"");
		  objSecciones.append(manager.find(Tipo_Gasto_Ventas.class, ventas.get(i).getTipoGasto_id()).getNombre());
		  objSecciones.append("\",\"Id\":\"");  		  
		  objSecciones.append(ventas.get(i).getGastos_id());
		  objSecciones.append("\",\"Anos\":");  
			objSecciones.append("[");
			query=manager.createNamedQuery("Año_Proyecciones_Gastos_Ventas.findByCostoId");
			query.setParameter("id",ventas.get(i).getGastos_id());
			
			List lista=query.getResultList();

			Vector<Año_Proyecciones_Gastos_Ventas> añosProyecciones=new Vector<Año_Proyecciones_Gastos_Ventas>();


			for (Object o : lista) {
				Año_Proyecciones_Gastos_Ventas año = (Año_Proyecciones_Gastos_Ventas) o;

				añosProyecciones.add(año);

			}
			  
			  for(int j=0;j<añosProyecciones.size();j++){
				  
				  objSecciones.append("{\"anoId\":\"");
				  objSecciones.append(añosProyecciones.get(j).getAñoId());
				  objSecciones.append("\",\"DatoId\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).getGastoVentaId());
				  objSecciones.append("\",\"mes1\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes1());
				  objSecciones.append("\",\"mes2\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes2());
				  objSecciones.append("\",\"mes3\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes3());
				  objSecciones.append("\",\"mes4\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes4());
				  objSecciones.append("\",\"mes5\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes5());
				  objSecciones.append("\",\"mes6\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes6());
				  objSecciones.append("\",\"mes7\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes7());
				  objSecciones.append("\",\"mes8\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes8());
				  objSecciones.append("\",\"mes9\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes9());
				  objSecciones.append("\",\"mes10\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes10());
				  objSecciones.append("\",\"mes11\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes11());
				  objSecciones.append("\",\"mes12\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes12());
				  objSecciones.append("\"},");
			  }
			objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
			objSecciones.append("},");
	  }
	objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
	objSecciones.append("},");
           
  
           
  

              
//Sustituye la última coma por el carácter de cierre del array
  objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");

return objSecciones.toString();
	}

	
	public String cargarProyeccionEmpleados(String planId) {
StringBuilder objSecciones;
		
		
		Query query=manager.createNamedQuery("Empleados.findByPlanId");
		query.setParameter("id", Integer.parseInt(planId));
		
		List listaMedios=query.getResultList();

		Vector<Empleados> empleados=new Vector<Empleados>();


		for (Object o : listaMedios) {
			Empleados costo = (Empleados) o;

			empleados.add(costo);

		}
	
		
		


//Crea un array de objetos JSON con cada tema de la tabla
  objSecciones = new StringBuilder("[");    
  

	
	Datos_Planes_Financieros datos=manager.find(Datos_Planes_Financieros.class, 1);
	  
	  objSecciones.append("{\"dato\":\"");
  objSecciones.append("datosProyeccionEmpleados");
  objSecciones.append("\",\"valor\":\"");  		  
  objSecciones.append(datos.getProyeccionVentas());
  objSecciones.append("\",\"planId\":\"");  		  
  objSecciones.append("planId");
  objSecciones.append("\",\"valorPlanId\":\"");  		  
  objSecciones.append(planId);
  objSecciones.append("\",\"Empleados\":");  
	objSecciones.append("[");

	  
	  for(int i=0;i<empleados.size();i++){
		  
		  objSecciones.append("{\"Nombre\":\"");
		  objSecciones.append(manager.find(Tipo_De_Cargo.class, empleados.get(i).getTipoCargo_id()).getNombre());
		  objSecciones.append("\",\"Id\":\"");  		  
		  objSecciones.append(empleados.get(i).getEmpleado_id());
		  objSecciones.append("\",\"Anos\":");  
			objSecciones.append("[");
			query=manager.createNamedQuery("Año_Proyecciones_Empleados.findByCostoId");
			query.setParameter("id",empleados.get(i).getEmpleado_id());
			
			List lista=query.getResultList();

			Vector<Año_Proyecciones_Empleados> añosProyecciones=new Vector<Año_Proyecciones_Empleados>();


			for (Object o : lista) {
				Año_Proyecciones_Empleados año = (Año_Proyecciones_Empleados) o;

				añosProyecciones.add(año);

			}
			  
			  for(int j=0;j<añosProyecciones.size();j++){
				  
				  objSecciones.append("{\"anoId\":\"");
				  objSecciones.append(añosProyecciones.get(j).getAñoId());
				  objSecciones.append("\",\"DatoId\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).getEmpleadoId());
				  objSecciones.append("\",\"mes1\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes1());
				  objSecciones.append("\",\"mes2\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes2());
				  objSecciones.append("\",\"mes3\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes3());
				  objSecciones.append("\",\"mes4\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes4());
				  objSecciones.append("\",\"mes5\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes5());
				  objSecciones.append("\",\"mes6\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes6());
				  objSecciones.append("\",\"mes7\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes7());
				  objSecciones.append("\",\"mes8\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes8());
				  objSecciones.append("\",\"mes9\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes9());
				  objSecciones.append("\",\"mes10\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes10());
				  objSecciones.append("\",\"mes11\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes11());
				  objSecciones.append("\",\"mes12\":\"");  		  
				  objSecciones.append(añosProyecciones.get(j).isMes12());
				  objSecciones.append("\"},");
			  }
			objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
			objSecciones.append("},");
	  }
	objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
	objSecciones.append("},");
           
  
           
  

              
//Sustituye la última coma por el carácter de cierre del array
  objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");

return objSecciones.toString();
	}

	
	public int getNumeroDeCostosFijos(String planId) {
		Query query=manager.createNamedQuery("Costos_Fijos.findByPlanId");
	      query.setParameter("id", Integer.parseInt(planId));
	  
	  List liste=query.getResultList();
	  
	  Vector<Costos_Fijos> costosFijos = new Vector<Costos_Fijos>();
		for (Object o : liste) {
			Costos_Fijos gastoFijo = (Costos_Fijos) o;
			
			costosFijos.add(gastoFijo);

		}
		
		return costosFijos.size();
	}


	public String guardarProyeccionCostosFijos(String[][] proyCostosFijos,
			String planId) {
		int numAnos=getNumeroDeAños(planId);
		for(int i=0;i<proyCostosFijos.length;i++){
			
			for(int j=0,h=0;j<numAnos;h+=12,j++){
				Año_Proyecciones_Costos_Fijos pro=manager.find(Año_Proyecciones_Costos_Fijos.class, new Año_Proyecciones_Costos_FijosPK(j,Integer.parseInt(proyCostosFijos[i][0])));
		
				pro.setMes1(Boolean.parseBoolean(proyCostosFijos[i][h+1]));
				pro.setMes2(Boolean.parseBoolean(proyCostosFijos[i][h+2]));
				pro.setMes3(Boolean.parseBoolean(proyCostosFijos[i][h+3]));
				pro.setMes4(Boolean.parseBoolean(proyCostosFijos[i][h+4]));
				pro.setMes5(Boolean.parseBoolean(proyCostosFijos[i][h+5]));
				pro.setMes6(Boolean.parseBoolean(proyCostosFijos[i][h+6]));
				pro.setMes7(Boolean.parseBoolean(proyCostosFijos[i][h+7]));
				pro.setMes8(Boolean.parseBoolean(proyCostosFijos[i][h+8]));
				pro.setMes9(Boolean.parseBoolean(proyCostosFijos[i][h+9]));
				pro.setMes10(Boolean.parseBoolean(proyCostosFijos[i][h+10]));
				pro.setMes11(Boolean.parseBoolean(proyCostosFijos[i][h+11]));
				pro.setMes12(Boolean.parseBoolean(proyCostosFijos[i][h+12]));
				
	
			manager.persist(pro);
			}
		}
		return planId;
	}

	
	public int getNumeroDeVentas(String planId) {
		Query query=manager.createNamedQuery("Gastos_Ventas.findByPlanId");
	      query.setParameter("id", Integer.parseInt(planId));
	  
	  List liste=query.getResultList();
	  
	  Vector<Gastos_Ventas> ventas = new Vector<Gastos_Ventas>();
		for (Object o : liste) {
			Gastos_Ventas gastoFijo = (Gastos_Ventas) o;
			
			ventas.add(gastoFijo);

		}
		
		return ventas.size();
	}

	
	public String guardarProyeccionVentas(String[][] proyVentas, String planId) {
		int numAnos=getNumeroDeAños(planId);
		for(int i=0;i<proyVentas.length;i++){
			
			for(int j=0,h=0;j<numAnos;h+=12,j++){
				Año_Proyecciones_Gastos_Ventas pro=manager.find(Año_Proyecciones_Gastos_Ventas.class, new Año_Proyecciones_Gastos_VentasPK(j,Integer.parseInt(proyVentas[i][0])));
		
				pro.setMes1(Boolean.parseBoolean(proyVentas[i][h+1]));
				pro.setMes2(Boolean.parseBoolean(proyVentas[i][h+2]));
				pro.setMes3(Boolean.parseBoolean(proyVentas[i][h+3]));
				pro.setMes4(Boolean.parseBoolean(proyVentas[i][h+4]));
				pro.setMes5(Boolean.parseBoolean(proyVentas[i][h+5]));
				pro.setMes6(Boolean.parseBoolean(proyVentas[i][h+6]));
				pro.setMes7(Boolean.parseBoolean(proyVentas[i][h+7]));
				pro.setMes8(Boolean.parseBoolean(proyVentas[i][h+8]));
				pro.setMes9(Boolean.parseBoolean(proyVentas[i][h+9]));
				pro.setMes10(Boolean.parseBoolean(proyVentas[i][h+10]));
				pro.setMes11(Boolean.parseBoolean(proyVentas[i][h+11]));
				pro.setMes12(Boolean.parseBoolean(proyVentas[i][h+12]));
				
	
			manager.persist(pro);
			}
		}
		return planId;
	}

	
	public int getNumeroDeEmpleados(String planId) {
		Query query=manager.createNamedQuery("Empleados.findByPlanId");
	      query.setParameter("id", Integer.parseInt(planId));
	  
	  List liste=query.getResultList();
	  
	  Vector<Empleados> empleados = new Vector<Empleados>();
		for (Object o : liste) {
			Empleados gastoFijo = (Empleados) o;
			
			empleados.add(gastoFijo);

		}
		
		return empleados.size();
	}

	@Override
	public String guardarProyeccionEmpleados(String[][] proyEmpleados,
			String planId) {
		int numAnos=getNumeroDeAños(planId);
		for(int i=0;i<proyEmpleados.length;i++){
			
			for(int j=0,h=0;j<numAnos;h+=12,j++){
				Año_Proyecciones_Empleados pro=manager.find(Año_Proyecciones_Empleados.class, new Año_Proyecciones_EmpleadosPK(j,Integer.parseInt(proyEmpleados[i][0])));
		
				pro.setMes1(Boolean.parseBoolean(proyEmpleados[i][h+1]));
				pro.setMes2(Boolean.parseBoolean(proyEmpleados[i][h+2]));
				pro.setMes3(Boolean.parseBoolean(proyEmpleados[i][h+3]));
				pro.setMes4(Boolean.parseBoolean(proyEmpleados[i][h+4]));
				pro.setMes5(Boolean.parseBoolean(proyEmpleados[i][h+5]));
				pro.setMes6(Boolean.parseBoolean(proyEmpleados[i][h+6]));
				pro.setMes7(Boolean.parseBoolean(proyEmpleados[i][h+7]));
				pro.setMes8(Boolean.parseBoolean(proyEmpleados[i][h+8]));
				pro.setMes9(Boolean.parseBoolean(proyEmpleados[i][h+9]));
				pro.setMes10(Boolean.parseBoolean(proyEmpleados[i][h+10]));
				pro.setMes11(Boolean.parseBoolean(proyEmpleados[i][h+11]));
				pro.setMes12(Boolean.parseBoolean(proyEmpleados[i][h+12]));
				
	
			manager.persist(pro);
			}
		}
		return "Felicitaciones ha terminado con el plan de negocio de su idea";
	}

	
	public String cargarCuaderno() {
        StringBuilder objSecciones;
		
		Query query=manager.createNamedQuery("Cuaderno_Apuntes.findById");
		  query.setParameter("id", usuario.getUsuario_id());
		  
		  List liste=query.getResultList();
		 Vector<Cuaderno_Apuntes> cuadernos=new Vector<Cuaderno_Apuntes>();
		 
		 
			for (Object o : liste) {
				Cuaderno_Apuntes pestaña = (Cuaderno_Apuntes) o;
				
				cuadernos.add(pestaña);

			}
		if(cuadernos.size()==0){
			Cuaderno_Apuntes aux=new Cuaderno_Apuntes();
			aux.setUsuario_id(usuario.getUsuario_id());
			aux.setCuaderno_id(1);
			aux.setNombre("Ideas");
			aux.setContenido("");
			manager.persist(aux);
			Cuaderno_Apuntes aux1=new Cuaderno_Apuntes();
			aux1.setCuaderno_id(2);
			aux1.setUsuario_id(usuario.getUsuario_id());
			aux1.setNombre("Clases");
			aux1.setContenido("");
			manager.persist(aux1);
			Cuaderno_Apuntes aux2=new Cuaderno_Apuntes();
			aux2.setCuaderno_id(3);
			aux2.setUsuario_id(usuario.getUsuario_id());
			aux2.setNombre("Charlas");
			aux2.setContenido("");
			manager.persist(aux2);
			  
			liste=query.getResultList();
			cuadernos=new Vector<Cuaderno_Apuntes>();
			 
			 
				for (Object o : liste) {
					Cuaderno_Apuntes pestaña = (Cuaderno_Apuntes) o;
					
					cuadernos.add(pestaña);

				}
		}
		
			
        //Crea un array de objetos JSON con cada tema de la tabla
          objSecciones = new StringBuilder("[");  
          for(int i=0;i<cuadernos.size();i++){
        	  
          objSecciones.append("{\"Id\":\"");          
          objSecciones.append(cuadernos.get(i).getCuaderno_id());        
          objSecciones.append("\",\"Nombre\":\"");
          objSecciones.append(cuadernos.get(i).getNombre());
          objSecciones.append("\"},");
          }
          

                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}

	
	public String cargarDatosDeCuaderno(int id) {
		 StringBuilder objSecciones=new StringBuilder("");
		 objSecciones.append("{\"Id\":\"");          
         objSecciones.append(id);        
         objSecciones.append("\",\"Contenido\":\"");
         objSecciones.append(manager.find(Cuaderno_Apuntes.class, new Cuaderno_ApuntesPK(id,usuario.getUsuario_id())).getContenido());
         objSecciones.append("\"}");
		return objSecciones.toString();
	}

	
	public void guardarCuaderno(int id, String contenido) {
		Cuaderno_Apuntes cuad=manager.find(Cuaderno_Apuntes.class, new Cuaderno_ApuntesPK(id,usuario.getUsuario_id()));
		cuad.setContenido(contenido.replace("\n", "<br>"));
		manager.persist(cuad);
		
	}

	
	public String cargarPreguntas() {
		
StringBuilder objSecciones;
		
		Query query=manager.createNamedQuery("Preguntas.findAll");
		  query.setParameter("id", usuario.getUsuario_id());
		  
		  List liste=query.getResultList();
		 Vector<Preguntas> preguntas=new Vector<Preguntas>();
		 
		 
			for (Object o : liste) {
				Preguntas pregunta = (Preguntas) o;
				
				preguntas.add(pregunta); 

			}
		
		

          objSecciones = new StringBuilder("[");  
          for(int i=0;i<preguntas.size();i++){
        	 
          objSecciones.append("{\"Id\":\"");          
          objSecciones.append(preguntas.get(i).getPregunta_id());        
          objSecciones.append("\",\"Contenido\":\"");
          objSecciones.append(preguntas.get(i).getContenido());
          objSecciones.append("\"},");
          }
          

                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}

	
	public String cargarRespuestaDePregunta(int id) {
StringBuilder objSecciones;


	      objSecciones = new StringBuilder("[");    

			
			  Query query=manager.createNamedQuery("Respuestas.findByMyPreg");
		      query.setParameter("id", id);
		  
		  List liste=query.getResultList();
		  
		  Vector<Respuestas> respuestas = new Vector<Respuestas>();
			for (Object o : liste) {
				Respuestas gasto = (Respuestas) o;
				
				respuestas.add(gasto);

			}
			  
			  objSecciones.append("{\"PreguntaId\":\"");
	      objSecciones.append(manager.find(Preguntas.class, id).getPregunta_id());
	      objSecciones.append("\",\"Contenido\":\"");  		  
	      objSecciones.append(manager.find(Preguntas.class, id).getContenido());
	      objSecciones.append("\",\"Respuestas\":");  		  

		objSecciones.append("[");
		for(int i=0;i<respuestas.size();i++){
  		  objSecciones.append("{\"RespuestaId\":\"");
        objSecciones.append(respuestas.get(i).getRespuesta_id());
        objSecciones.append("\",\"Contenido\":\"");  		  
        objSecciones.append(respuestas.get(i).getContenido());
        objSecciones.append("\",\"userId\":\"");  		  
        objSecciones.append(respuestas.get(i).getUsuario_id());
        objSecciones.append("\",\"Usuario\":\"");  		  
        objSecciones.append(manager.find(Usuarios.class, respuestas.get(i).getUsuario_id()).getNombre()+" "+manager.find(Usuarios.class, respuestas.get(i).getUsuario_id()).getApellido());
        objSecciones.append("\",\"Imagen\":\"");  		  
        objSecciones.append(manager.find(Usuarios.class, respuestas.get(i).getUsuario_id()).getFoto());
        objSecciones.append("\",\"Fecha\":\"");  		  
        objSecciones.append(respuestas.get(i).getFecha().toString().substring(0, 10));
        objSecciones.append("\",\"Mejor\":\"");  		  
        objSecciones.append(respuestas.get(i).isMejorRespuesta());
        objSecciones.append("\",\"Denunciada\":\"");  	
        Usuarios_Denuncias usDen=manager.find(Usuarios_Denuncias.class, new Usuarios_DenunciasPK(usuario.getUsuario_id(),respuestas.get(i).getRespuesta_id()));
        if(usDen==null){
        	objSecciones.append(false);
        }
        else{
        	objSecciones.append(true);
        }
        
        objSecciones.append("\"},");
        
                 
        }

	      if(respuestas.size()>0){
	  		objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]},");
	  		
	  		}
	  		else{
	  			objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"\"\"}]");
	  			
	  		}
	                  
	    //Sustituye la última coma por el carácter de cierre del array
	      objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}

	
	public void guardarRespuesta(int id, String respuesta) {
		Respuestas res=new Respuestas();
		res.setContenido(respuesta);
		res.setMejorRespuesta(false);
		res.setPregunta_id(id);
		res.setFecha(new Date());
		res.setUsuario_id(usuario.getUsuario_id());
		manager.persist(res);
		
	}


	public String cargarMisPreguntas() {
StringBuilder objSecciones;
		
		Query query=manager.createNamedQuery("Preguntas.findByMyId");
		  query.setParameter("id", usuario.getUsuario_id());
		  
		  List liste=query.getResultList();
		 Vector<Preguntas> preguntas=new Vector<Preguntas>();
		 
		 
			for (Object o : liste) {
				Preguntas pregunta = (Preguntas) o;
				
				preguntas.add(pregunta); 

			}
		
		

          objSecciones = new StringBuilder("[");  
          for(int i=0;i<preguntas.size();i++){
        	 
          objSecciones.append("{\"Id\":\"");          
          objSecciones.append(preguntas.get(i).getPregunta_id());        
          objSecciones.append("\",\"Contenido\":\"");
          objSecciones.append(preguntas.get(i).getContenido());
          objSecciones.append("\"},");
          }
          

                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}

	
	public String guardarPregunta(String pregunta, int area) {
		Preguntas pre=new Preguntas();
		pre.setAreaEstudio_id(area);
		pre.setContenido(pregunta);
		pre.setUsuario_id(usuario.getUsuario_id());
		pre.setMejorRespuesta(true);
		manager.persist(pre);
		
		return "listo";
	}

	
	public String cargarClases() {
StringBuilder objSecciones;
		
		Query query=manager.createNamedQuery("Clases.findAll");
		  
		  
		  List liste=query.getResultList();
		 Vector<Clases> clases=new Vector<Clases>();
		 
		 
			for (Object o : liste) {
				Clases clase = (Clases) o;
				
				clases.add(clase); 

			}
		
		

          objSecciones = new StringBuilder("[");  
          for(int i=0;i<clases.size();i++){
        	 
          objSecciones.append("{\"Id\":\"");          
          objSecciones.append(clases.get(i).getClase_id());        
          objSecciones.append("\",\"Nombre\":\"");
          objSecciones.append(clases.get(i).getNombre());
          objSecciones.append("\",\"Votos\":\"");
          objSecciones.append(clases.get(i).getCalificacion());
          objSecciones.append("\"},");
          }
          

                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}

	
	public String cargarDatosDeClase(int id) {
		Clases clas=manager.find(Clases.class,id );
		 StringBuilder objSecciones=new StringBuilder("");
		 objSecciones.append("{\"Id\":\"");          
         objSecciones.append(id);        
         objSecciones.append("\",\"Nombre\":\"");
         objSecciones.append(clas.getNombre());
         objSecciones.append("\",\"Profesor\":\"");
         objSecciones.append(clas.getNombreProfesor());
         objSecciones.append("\",\"Contenido\":\"");
         objSecciones.append(clas.getContenido());
         objSecciones.append("\",\"Descripcion\":\"");
         objSecciones.append(clas.getDescripcion());
         objSecciones.append("\",\"Votos\":\"");
         objSecciones.append(clas.getCalificacion());
         objSecciones.append("\",\"Negativo\":\"");
         if(manager.find(Negativos_Clases.class, new Negativos_ClasesPK(usuario.getUsuario_id(),id))==null){
         	objSecciones.append(false);
         }
         else{
         	objSecciones.append(true);
         }
         objSecciones.append("\",\"Votado\":\"");
         if(manager.find(Votos_Clase.class, new Votos_ClasePK(usuario.getUsuario_id(),id))==null){
         	objSecciones.append(false);
         }
         else{
         	objSecciones.append(true);
         }
         objSecciones.append("\",\"Comentarios\":[");
         
         
     	Query query=manager.createNamedQuery("Comentarios_Clases.findByClase");
		  query.setParameter("id", id);
		  
		  List liste=query.getResultList();
		 Vector<Comentarios_Clases> comentarios=new Vector<Comentarios_Clases>();
		 
		 
			for (Object o : liste) {
				Comentarios_Clases comentario = (Comentarios_Clases) o;
				
				comentarios.add(comentario);

			}
 
        for(int i=0;i<comentarios.size();i++){
        objSecciones.append("{\"Comentario\":\"");
        objSecciones.append(comentarios.get(i).getContenida());
        objSecciones.append("\",\"usuario\":\"");
        objSecciones.append(manager.find(Usuarios.class, (comentarios.get(i).getUsuario_id())).getNombre()+" "+manager.find(Usuarios.class, (comentarios.get(i).getUsuario_id())).getApellido());
        objSecciones.append("\",\"Imagen\":\"");
        objSecciones.append(manager.find(Usuarios.class, (comentarios.get(i).getUsuario_id())).getFoto());
        objSecciones.append("\",\"Fecha\":\"");
        objSecciones.append(comentarios.get(i).getFecha().toString().substring(0,10));
        objSecciones.append("\"},");
        }
        
        if(comentarios.size()==0){
        	objSecciones.append(",");
        }

                    
      //Sustituye la última coma por el carácter de cierre del array
        objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]}");
         
		return objSecciones.toString();
	}

	
	public String cargarCharlas() {
StringBuilder objSecciones;
		
		Query query=manager.createNamedQuery("Cursos.findAll");
		  
		  
		  List liste=query.getResultList();
		 Vector<Cursos> clases=new Vector<Cursos>();
		 
		 
			for (Object o : liste) {
				Cursos clase = (Cursos) o;
				
				clases.add(clase); 

			}
		
		

          objSecciones = new StringBuilder("[");  
          for(int i=0;i<clases.size();i++){
        	 
          objSecciones.append("{\"Id\":\"");          
          objSecciones.append(clases.get(i).getCurso_id());        
          objSecciones.append("\",\"Nombre\":\"");
          objSecciones.append(clases.get(i).getNombre());
          objSecciones.append("\",\"Votos\":\"");
          objSecciones.append(clases.get(i).getCalificacion());
          objSecciones.append("\"},");
          }
          

                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}

	
	public String cargarDatosDeCharla(int id) {
		Cursos curso=manager.find(Cursos.class,id );
		 StringBuilder objSecciones=new StringBuilder("");
		 objSecciones.append("{\"Id\":\"");          
        objSecciones.append(id);        
        objSecciones.append("\",\"Nombre\":\"");
        objSecciones.append(curso.getNombre());
        objSecciones.append("\",\"Profesor\":\"");
        objSecciones.append(curso.getNombreProfesor());
        objSecciones.append("\",\"ProfDesc\":\"");
        objSecciones.append(curso.getDescripcionConferencista());
        objSecciones.append("\",\"Contenido\":\"");
        objSecciones.append(curso.getContenido());
        objSecciones.append("\",\"Descripcion\":\"");
        objSecciones.append(curso.getDescripcion());
        objSecciones.append("\",\"Votos\":\"");
        objSecciones.append(curso.getCalificacion());
        objSecciones.append("\",\"Negativo\":\"");
        if(manager.find(Negativos_Charlas.class, new Negativos_CharlasPK(usuario.getUsuario_id(),id))==null){
        	objSecciones.append(false);
        }
        else{
        	objSecciones.append(true);
        }
        objSecciones.append("\",\"Votado\":\"");
        if(manager.find(Votos_Charla.class, new Votos_CharlaPK(usuario.getUsuario_id(),id))==null){
        	objSecciones.append(false);
        }
        else{
        	objSecciones.append(true);
        }
        objSecciones.append("\",\"Comentarios\":[");
        
        
     	Query query=manager.createNamedQuery("Comentarios_Charlas.findByCurso");
		  query.setParameter("id", id);
		  
		  List liste=query.getResultList();
		 Vector<Comentarios_Charlas> comentarios=new Vector<Comentarios_Charlas>();
		 
		 
			for (Object o : liste) {
				Comentarios_Charlas comentario = (Comentarios_Charlas) o;
				
				comentarios.add(comentario);

			}
 
        for(int i=0;i<comentarios.size();i++){
        objSecciones.append("{\"Comentario\":\"");
        objSecciones.append(comentarios.get(i).getContenida());
        objSecciones.append("\",\"usuario\":\"");
        objSecciones.append(manager.find(Usuarios.class, (comentarios.get(i).getUsuario_id())).getNombre()+" "+manager.find(Usuarios.class, (comentarios.get(i).getUsuario_id())).getApellido());
        objSecciones.append("\",\"Imagen\":\"");
        objSecciones.append(manager.find(Usuarios.class, (comentarios.get(i).getUsuario_id())).getFoto());
        objSecciones.append("\",\"Fecha\":\"");
        objSecciones.append(comentarios.get(i).getFecha().toString().substring(0,10));
        objSecciones.append("\"},");
        }
        
        if(comentarios.size()==0){
        	objSecciones.append(",");
        }

                    
      //Sustituye la última coma por el carácter de cierre del array
        objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]}");
         
		return objSecciones.toString();
	}

	
	public String getNombreId() {

		return usuario.getNombre()+usuario.getUsuario_id();
	}

	
	public String getDatosPlanDeNegocio(int planId) {
		Planes_De_Negocios plan=manager.find(Planes_De_Negocios.class, planId);
		
		StringBuilder objSecciones=new StringBuilder("");
		 objSecciones.append("Nombre del Plan\n");          
        objSecciones.append(plan.getNombre()+"\n");        
        objSecciones.append("Equipo\n");
        objSecciones.append(plan.getEquipo()+"\n");
        objSecciones.append("Resumen Ejecutivo\n");
        objSecciones.append(plan.getResumenEjecutivo()+"\n");
        objSecciones.append("Mercado\n");
        objSecciones.append(plan.getMercado()+"\n");
        objSecciones.append("Competencia\n");
        objSecciones.append(plan.getCompetencia()+"\n");
        objSecciones.append("Empresa\n");
        objSecciones.append(plan.getEmpresa()+"\n");
        objSecciones.append("Productos y/o Servicios\n");
        objSecciones.append(plan.getProductosServicios()+"\n");
        objSecciones.append("Produccion y/o Servuccion\n");
        objSecciones.append(plan.getProduccionServuccion()+"\n");
        objSecciones.append("Precio De los Producto y/o Servicios\n");
        objSecciones.append(plan.getPrecioProductosOServicios()+"\n");
        objSecciones.append("Mercado Objetivo\n");
        objSecciones.append(plan.getMercadoObjetivo()+"\n");
        objSecciones.append("Entrada al Mercado\n");
        objSecciones.append(plan.getEntradaMercado()+"\n");
        objSecciones.append("Potencial Mercado\n");
        objSecciones.append(plan.getPotencialMercado()+"\n");
        objSecciones.append("Viabilidad Juridica\n");
        objSecciones.append(plan.getViabilidadJuridica()+"\n");
        objSecciones.append("Obligaciones tributarias\n");
        objSecciones.append(plan.getObligacionesTributarias()+"\n");
        objSecciones.append("Otros Aspectos Legales\n");
        objSecciones.append(plan.getOtrosLegales()+"\n");
        objSecciones.append("\"}");
		return objSecciones.toString();
	}

	
	public void setFoto(String name) {
		usuario.setFoto(name);
		manager.merge(usuario);
	
		
	}

	
	public String cargarImagen() {
		
		return usuario.getFoto();
	}

	
	public String cargarDatosIdea(int id) {
StringBuilder objSecciones;
		
			
		
		
		Idea_De_Negocio idea=manager.find(Idea_De_Negocio.class, id);
        //Crea un array de objetos JSON con cada tema de la tabla
          objSecciones = new StringBuilder("");  
         
          objSecciones.append("{\"Nombre\":\"");
          objSecciones.append(idea.getNombre());
          objSecciones.append("\",\"Id\":\"");
          objSecciones.append(idea.getIdea_id());
          objSecciones.append("\",\"Idea\":\"");
          objSecciones.append(idea.getIdea());
          objSecciones.append("\",\"Nacimiento\":\"");
          objSecciones.append(idea.getNacimiento());
          objSecciones.append("\",\"Pais\":\"");
          objSecciones.append(idea.getPais());
          objSecciones.append("\",\"Ciudad\":\"");
          objSecciones.append(idea.getCiudad());
          objSecciones.append("\",\"Area\":\"");
          objSecciones.append(idea.getAreaEstudio_id());
          objSecciones.append("\",\"TipoInversionista\":\"");
          objSecciones.append(idea.getTipoInversionista_id());
          objSecciones.append("\",\"TipoInversion\":\"");
          objSecciones.append(idea.getTipoInversion_id());
          objSecciones.append("\",\"NivelInversion\":\"");
          objSecciones.append(idea.getNivelInversion_id());
          objSecciones.append("\",\"areasEstudio\":");
          objSecciones.append("[");
			Query query=manager.createNamedQuery("Areas_De_Estudio.findAll");

			
			List lista=query.getResultList();

			Vector<Areas_De_Estudio> areas=new Vector<Areas_De_Estudio>();


			for (Object o : lista) {
				Areas_De_Estudio area = (Areas_De_Estudio) o;

				areas.add(area);

			}
			  
			  for(int j=0;j<areas.size();j++){
				  
				  objSecciones.append("{\"Id\":\"");
				  objSecciones.append(areas.get(j).getAreaEstudio_id());
				  objSecciones.append("\",\"Nombre\":\"");  		  
				  objSecciones.append(areas.get(j).getNombre());
				  objSecciones.append("\"},");
			  }
			objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
          
			objSecciones.append(",\"paises\":");
	          objSecciones.append("[");
				 query=manager.createNamedQuery("Paises.findAll");

				
				 lista=query.getResultList();

				Vector<Paises> paises=new Vector<Paises>();


				for (Object o : lista) {
					Paises pais = (Paises) o;

					paises.add(pais);

				}
				  
				  for(int j=0;j<paises.size();j++){
					  
					  objSecciones.append("{\"Id\":\"");
					  objSecciones.append(paises.get(j).getFips());
					  objSecciones.append("\",\"Nombre\":\"");  		  
					  objSecciones.append(paises.get(j).getNombre());
					  objSecciones.append("\"},");
				  }
				objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
				
				objSecciones.append(",\"tiposInversionista\":");
		          objSecciones.append("[");
					 query=manager.createNamedQuery("Tipo_Inversionista.findAll");

					
					 lista=query.getResultList();

					Vector<Tipo_Inversionista> tiposInversionistas=new Vector<Tipo_Inversionista>();


					for (Object o : lista) {
						Tipo_Inversionista tipoInv = (Tipo_Inversionista) o;

						tiposInversionistas.add(tipoInv);

					}
					  
					  for(int j=0;j<tiposInversionistas.size();j++){
						  
						  objSecciones.append("{\"Id\":\"");
						  objSecciones.append(tiposInversionistas.get(j).getTipo_id());
						  objSecciones.append("\",\"Nombre\":\"");  		  
						  objSecciones.append(tiposInversionistas.get(j).getNombre());
						  objSecciones.append("\"},");
					  }
					objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
					objSecciones.append(",\"tiposInversion\":");
			          objSecciones.append("[");
						 query=manager.createNamedQuery("Tipo_Inversion.findAll");

						
						 lista=query.getResultList();

						Vector<Tipo_Inversion> tiposInversion=new Vector<Tipo_Inversion>();


						for (Object o : lista) {
							Tipo_Inversion tInv = (Tipo_Inversion) o;

							tiposInversion.add(tInv);

						}
						  
						  for(int j=0;j<tiposInversion.size();j++){
							  
							  objSecciones.append("{\"Id\":\"");
							  objSecciones.append(tiposInversion.get(j).getTipo_id());
							  objSecciones.append("\",\"Nombre\":\"");  		  
							  objSecciones.append(tiposInversion.get(j).getNombre());
							  objSecciones.append("\"},");
						  }
						objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
						
						objSecciones.append(",\"nivelesInversion\":");
				          objSecciones.append("[");
							 query=manager.createNamedQuery("Nivel_Inversion.findAll");

							
							 lista=query.getResultList();

							Vector<Nivel_Inversion> nivelesInversion=new Vector<Nivel_Inversion>();


							for (Object o : lista) {
								Nivel_Inversion nInv = (Nivel_Inversion) o;

								nivelesInversion.add(nInv);

							}
							  
							  for(int j=0;j<nivelesInversion.size();j++){
								  
								  objSecciones.append("{\"Id\":\"");
								  objSecciones.append(nivelesInversion.get(j).getNivel_id());
								  objSecciones.append("\",\"Nombre\":\"");  		  
								  objSecciones.append(nivelesInversion.get(j).getRango());
								  objSecciones.append("\"},");
							  }
							objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
          objSecciones.append("}");
          
          


                      

		
		return objSecciones.toString();
	}

	
	public void guardarModificacionIdea(int id, String nombre, String idea,
			String nacimiento, String pais, String ciudad, String area,
			String tipoInversion, String tipoInversionista,
			String nivelInversion) {
		
		Idea_De_Negocio ideaA=manager.find(Idea_De_Negocio.class, id);
		ideaA.setNombre(nombre.replace("\n"	, "<br>"));
		ideaA.setIdea(idea.replace("\n"	, "<br>"));
		ideaA.setNacimiento(nacimiento.replace("\n"	, "<br>"));
		ideaA.setPais(pais);
		ideaA.setCiudad(ciudad);
		ideaA.setAreaEstudio_id(Integer.parseInt(area));
		ideaA.setTipoInversion_id(Integer.parseInt(tipoInversion));
		ideaA.setTipoInversionista_id(Integer.parseInt(tipoInversionista));
		ideaA.setNivelInversion_id(Integer.parseInt(nivelInversion));
		manager.persist(ideaA);
	}

	
	public String crearPestana(String nombre) {
		Cuaderno_Apuntes cuad=new Cuaderno_Apuntes();
		cuad.setContenido("");
		boolean encontrado=false;
		Query query=manager.createNamedQuery("Cuaderno_Apuntes.findById");
		  query.setParameter("id", usuario.getUsuario_id());
		  
		  List liste=query.getResultList();
		 Vector<Cuaderno_Apuntes> cuadernos=new Vector<Cuaderno_Apuntes>();
		 
		 
			for (Object o : liste) {
				Cuaderno_Apuntes pestaña = (Cuaderno_Apuntes) o;
				if(pestaña.getNombre().equals(nombre)){
					encontrado=true;
				}
				cuadernos.add(pestaña);

			}
			if(encontrado){
				return "El nombre ya existe";
			}
			else{
				cuad.setUsuario_id(usuario.getUsuario_id());
				cuad.setNombre(nombre);
				manager.persist(cuad);
			}

		return "listo";
	}

	
	public String eliminarCuaderno(int id) {
     Cuaderno_Apuntes cuaderno=manager.find(Cuaderno_Apuntes.class, new Cuaderno_ApuntesPK(id,usuario.getUsuario_id()));
     System.out.println(cuaderno.getNombre());
     manager.remove(cuaderno);
		
		
		return null;
	}

	
	public String eliminarRiesgoContingencia(String id) {
		Riesgos_Contingencias riesCont=manager.find(Riesgos_Contingencias.class, Integer.parseInt(id));
		manager.remove(riesCont);
		return "eliminado";
	}

	
	public String eliminarProducto(String id) {
		Productos pro=manager.find(Productos.class, Integer.parseInt(id));
		manager.remove(pro);
		
		return "eliminado";
	}

	
	public String eliminarInsumo(String id) {
		String aux[]=id.split("_");
		Insumos_Procesos_Al_Destajo ins=manager.find(Insumos_Procesos_Al_Destajo.class, Integer.parseInt(aux[1]));
		manager.remove(ins);
		return "eliminado";
	}

	
	public String eliminarInversionPreoperativa(String id) {
		Inversion_Preoperativa inv=manager.find(Inversion_Preoperativa.class, Integer.parseInt(id));
		manager.remove(inv);
		return "eliminado";
	}

	
	public String eliminarActivo(String id) {
		Inversion_Activo_Fijos inv=manager.find(Inversion_Activo_Fijos.class, Integer.parseInt(id));
		manager.remove(inv);
		return "eliminado";
	}


	public String eliminarCostoFijo(String id) {
		Costos_Fijos cost=manager.find(Costos_Fijos.class, Integer.parseInt(id));
		manager.remove(cost);
		return "eliminado";
	}

	
	public String eliminarGastoFijo(String id) {
		Gastos_Fijos_Administracion gast=manager.find(Gastos_Fijos_Administracion.class, Integer.parseInt(id));
		manager.remove(gast);
		return "eliminado";
	}


	public String eliminarMedio(String id) {
		Medios_Comunicacion med=manager.find(Medios_Comunicacion.class, Integer.parseInt(id));
		manager.remove(med);
		return "eliminado";
	}

	
	public String eliminarGastoVenta(String id) {
		Gastos_Ventas gast=manager.find(Gastos_Ventas.class, Integer.parseInt(id));
		manager.remove(gast);
		return "eliminado";
	}

	
	public String eliminarEmpleado(String id) {
		Empleados empl=manager.find(Empleados.class, Integer.parseInt(id));
		manager.remove(empl);
		return "eliminado";
	}

	
	public String eliminarImpuesto(String id) {
		Impuestos imp=manager.find(Impuestos.class, Integer.parseInt(id));
		manager.remove(imp);
		return "eliminado";
	}

	
	public String cargarFormularioColaborador(int id) {
	
        StringBuilder objSecciones;
		
		Query query=manager.createNamedQuery("Idea_De_Negocio_Usuarios.findMyId");
		  query.setParameter("id", usuario.getUsuario_id());
		  
		  List liste=query.getResultList();
		 Vector<Idea_De_Negocio_Usuarios> ideas=new Vector<Idea_De_Negocio_Usuarios>();
		 
		 
			for (Object o : liste) {
				Idea_De_Negocio_Usuarios idea = (Idea_De_Negocio_Usuarios) o;
				
				ideas.add(idea);

			}
			objSecciones = new StringBuilder("[");
			objSecciones.append("{\"Id\":\"");	      
	        objSecciones.append(id);
			objSecciones.append("\"},");
		
			Idea_De_Negocio auxiliar;
        //Crea un array de objetos JSON con cada tema de la tabla
          objSecciones.append("[");  
          for(int i=0;i<ideas.size();i++){
          objSecciones.append("{\"Id\":\"");
          auxiliar=manager.find(Idea_De_Negocio.class,(ideas.get(i).getIdea_id()));
          objSecciones.append(auxiliar.getIdea_id());        
          objSecciones.append("\",\"Nombre\":\"");
          objSecciones.append(auxiliar.getNombre());
          objSecciones.append("\"},");
          }
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"],");
          query=manager.createNamedQuery("Tipo_Colaborador.findAll");
         liste=query.getResultList();
 		 Vector<Tipo_Colaborador> tiposCol=new Vector<Tipo_Colaborador>();
 		 
 		 
 			for (Object o : liste) {
 				Tipo_Colaborador tipo = (Tipo_Colaborador) o;
 				
 				tiposCol.add(tipo);

 			}
 			
 			  objSecciones.append("[");  
 	          for(int i=0;i<tiposCol.size();i++){
 	          objSecciones.append("{\"Id\":\"");
 	          objSecciones.append(tiposCol.get(i).getTipo_id());        
 	          objSecciones.append("\",\"Nombre\":\"");
 	          objSecciones.append(tiposCol.get(i).getNombre());
 	          objSecciones.append("\"},");
 	          }
                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]]");
		
		return objSecciones.toString();
	}

	
	public void guardarColaborador(int idea, int colaborador, int tipo) {
		Colaboradores col=new Colaboradores();
		col.setPrincipal_id(usuario.getUsuario_id());
		col.setColaborador_id(colaborador);
		col.setIdea_id(idea);
		col.setTipoColaborador_id(tipo);
		manager.persist(col);
		
	}

	
	public String cargarColaboradores() {
		
		StringBuilder objSecciones;
		
		
		
		

        objSecciones = new StringBuilder("[");  
       
        
        
        Query query=manager.createNamedQuery("Colaboradores.findColaboradores");
		  query.setParameter("id", usuario.getUsuario_id());
		  
		  List liste=query.getResultList();
		  
		  Vector<Colaboradores> colaboradores = new Vector<Colaboradores>();
			for (Object o : liste) {
				Colaboradores colaborador = (Colaboradores) o;
				
				colaboradores.add(colaborador);
				

			}
        
        
        for(int i=0;i<colaboradores.size();i++){
      	 
    		  objSecciones.append("{\"Id\":\"");
            objSecciones.append(colaboradores.get(i).getColaborador_id());
            objSecciones.append("\",\"Nombre\":\"");  		  
            objSecciones.append(manager.find(Usuarios.class,colaboradores.get(i).getColaborador_id()).getNombre()+" "+manager.find(Usuarios.class,colaboradores.get(i).getColaborador_id()).getApellido());
            objSecciones.append("\",\"Imagen\":\"");  		  
            objSecciones.append(manager.find(Usuarios.class,colaboradores.get(i).getColaborador_id()).getFoto());
            objSecciones.append("\",\"Tipo\":\"");  		  
            objSecciones.append(manager.find(Tipo_Colaborador.class,colaboradores.get(i).getTipoColaborador_id()).getNombre());
            objSecciones.append("\"},");
      	  
      	  

            
                     
            }


                    
      //Sustituye la última coma por el carácter de cierre del array
        objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}

	
	public String cargarColaborados() {
		
		StringBuilder objSecciones;
		
		
		
		

        objSecciones = new StringBuilder("[");  
       
        
        
        Query query=manager.createNamedQuery("Colaboradores.findColaborados");
		  query.setParameter("id", usuario.getUsuario_id());
		  
		  List liste=query.getResultList();
		  
		  Vector<Colaboradores> colaboradores = new Vector<Colaboradores>();
			for (Object o : liste) {
				Colaboradores colaborador = (Colaboradores) o;
				
				colaboradores.add(colaborador);
				

			}
        
        
        for(int i=0;i<colaboradores.size();i++){
      	 
    		  objSecciones.append("{\"Id\":\"");
            objSecciones.append(colaboradores.get(i).getPrincipal_id());
            objSecciones.append("\",\"Nombre\":\"");  		  
            objSecciones.append(manager.find(Usuarios.class,colaboradores.get(i).getPrincipal_id()).getNombre()+" "+manager.find(Usuarios.class,colaboradores.get(i).getPrincipal_id()).getApellido());
            objSecciones.append("\",\"Imagen\":\"");  		  
            objSecciones.append(manager.find(Usuarios.class,colaboradores.get(i).getPrincipal_id()).getFoto());
            objSecciones.append("\",\"Tipo\":\"");  		  
            objSecciones.append(manager.find(Tipo_Colaborador.class,colaboradores.get(i).getPrincipal_id()).getNombre());
            objSecciones.append("\"},");
      	  
      	  

            
                     
            }


                    
      //Sustituye la última coma por el carácter de cierre del array
        objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}

	private boolean esColaborador(int id){
		Query query=manager.createNamedQuery("Colaboradores.findColaboradores");
		query.setParameter("id", usuario.getUsuario_id());

		List liste=query.getResultList();

		Vector<Colaboradores> colaboradores = new Vector<Colaboradores>();
			for (Object o : liste) {
				Colaboradores colaborador = (Colaboradores) o;
				if(colaborador.getColaborador_id()==id){
				colaboradores.add(colaborador);
				}

			}	
			if(colaboradores.size()==0){
				return false;
			}
			return true;
	}
	private boolean esColaborado(int id){
		Query query=manager.createNamedQuery("Colaboradores.findColaborados");
		query.setParameter("id", usuario.getUsuario_id());

		List liste=query.getResultList();

		Vector<Colaboradores> colaborados = new Vector<Colaboradores>();
			for (Object o : liste) {
				Colaboradores colaborador = (Colaboradores) o;
				if(colaborador.getPrincipal_id()==id){
				colaborados.add(colaborador);
				}

			}
			if(colaborados.size()==0){
				return false;
			}
			return true;
		
	}
	public String cargarDatosDeUsuario(int id) {
		
StringBuilder objSecciones;


		
		Usuarios amigo=manager.find(Usuarios.class, id);
		
		Privacidad priv=manager.find(Privacidad.class, amigo.getUsuario_id());
        //Crea un array de objetos JSON con cada tema de la tabla
		objSecciones = new StringBuilder("[");
		
		if(esAmigo(id)){
            
          
  		  objSecciones.append("{\"Id\":\"");
          objSecciones.append(amigo.getUsuario_id());
          if(priv.getNombre()<3){
          objSecciones.append("\",\"Nombre\":\"");
          objSecciones.append(amigo.getNombre());
          }
          if(priv.getApellido()<3){
          objSecciones.append("\",\"Apellido\":\"");
          objSecciones.append(amigo.getApellido());
          }
          if(priv.getDireccion()<3){
          objSecciones.append("\",\"Direccion\":\"");
          objSecciones.append(amigo.getDireccion());
          }
          if(priv.getPais()<3){
          objSecciones.append("\",\"Pais\":\"");
          objSecciones.append(manager.find(Paises.class, amigo.getPais()).getNombre());
          }
          if(priv.getGenero()<3){
          objSecciones.append("\",\"Genero\":\"");
          objSecciones.append(manager.find(Genero.class, amigo.getGenero_id()).getGenero());
          }
          if(priv.getCiudad()<3){
          objSecciones.append("\",\"Ciudad\":\"");
          objSecciones.append(amigo.getCiudad());
          }
          if(priv.getTelefono()<3){
          objSecciones.append("\",\"Telefono\":\"");
          objSecciones.append(amigo.getTelefono());
          }
          if(priv.geteMail()<3){
          objSecciones.append("\",\"Email\":\"");
          objSecciones.append(amigo.geteMail());
          }
          if(priv.getFechaNacimiento()<3){
          objSecciones.append("\",\"Fecha_Nacimiento\":\"");
          objSecciones.append(amigo.getFechaNacimiento().toString().substring(0, 10));
          }
          if(priv.getReseñaPersonal()<3){
          objSecciones.append("\",\"Resena\":\"");
          objSecciones.append(amigo.getReseñaPersonal());
          }
          if(priv.getEstudios()<3){
          objSecciones.append("\",\"Estudio\":\"");
          objSecciones.append(manager.find(Estudios.class, amigo.getEstudios_id()).getEstudio());
          }
          if(priv.getEstudios()<3){
              objSecciones.append("\",\"Imagen\":\"");
              objSecciones.append(amigo.getFoto());
              }
          if(esColaborador(id)){
              objSecciones.append("\",\"Colaborador\":\"");
              objSecciones.append(true);
              }
          if(esColaborado(id)){
              objSecciones.append("\",\"Colaborado\":\"");
              objSecciones.append(true);
              }
          objSecciones.append("\",\"EsAmigo\":\"");
          objSecciones.append(true);
          objSecciones.append("\"},");
		}
		
		else{
            
	          
	  		  objSecciones.append("{\"Id\":\"");
	          objSecciones.append(amigo.getUsuario_id());
	          if(priv.getNombre()==1){
	          objSecciones.append("\",\"Nombre\":\"");
	          objSecciones.append(amigo.getNombre());
	          }
	          if(priv.getApellido()==1){
	          objSecciones.append("\",\"Apellido\":\"");
	          objSecciones.append(amigo.getApellido());
	          }
	          if(priv.getDireccion()==1){
	          objSecciones.append("\",\"Direccion\":\"");
	          objSecciones.append(amigo.getDireccion());
	          }
	          if(priv.getPais()==1){
	          objSecciones.append("\",\"Pais\":\"");
	          objSecciones.append(manager.find(Paises.class, amigo.getPais()).getNombre());
	          }
	          if(priv.getGenero()==1){
	          objSecciones.append("\",\"Genero\":\"");
	          objSecciones.append(manager.find(Genero.class, amigo.getGenero_id()).getGenero());
	          }
	          if(priv.getCiudad()==1){
	          objSecciones.append("\",\"Ciudad\":\"");
	          objSecciones.append(amigo.getCiudad());
	          }
	          if(priv.getTelefono()==1){
	          objSecciones.append("\",\"Telefono\":\"");
	          objSecciones.append(amigo.getTelefono());
	          }
	          if(priv.geteMail()==1){
	          objSecciones.append("\",\"Email\":\"");
	          objSecciones.append(amigo.geteMail());
	          }
	          if(priv.getFechaNacimiento()==1){
	          objSecciones.append("\",\"Fecha_Nacimiento\":\"");
	          objSecciones.append(amigo.getFechaNacimiento().toString().substring(0, 10));
	          }
	          if(priv.getReseñaPersonal()==1){
	          objSecciones.append("\",\"Resena\":\"");
	          objSecciones.append(amigo.getReseñaPersonal());
	          }
	          if(priv.getEstudios()==1){
	          objSecciones.append("\",\"Estudio\":\"");
	          objSecciones.append(manager.find(Estudios.class, amigo.getEstudios_id()).getEstudio());
	          }
	          if(priv.getEstudios()==1){
	              objSecciones.append("\",\"Imagen\":\"");
	              objSecciones.append(amigo.getFoto());
	              }
	          if(esColaborador(id)){
	              objSecciones.append("\",\"Colaborador\":\"");
	              objSecciones.append(true);
	              }
	          if(esColaborado(id)){
	              objSecciones.append("\",\"Colaborado\":\"");
	              objSecciones.append(true);
	              }
              objSecciones.append("\",\"EsAmigo\":\"");
              objSecciones.append(false);
              
	          objSecciones.append("\"},");
			}


                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}

	private String tipoColaborador(int id) {
		
		Query query=manager.createNamedQuery("Colaboradores.findColaboradores");
		query.setParameter("id", usuario.getUsuario_id());

		List liste=query.getResultList();

		Vector<Colaboradores> colaboradores = new Vector<Colaboradores>();
			for (Object o : liste) {
				Colaboradores colaborador = (Colaboradores) o;
				if(colaborador.getColaborador_id()==id){
				colaboradores.add(colaborador);
				}

			}	

			return manager.find(Tipo_Colaborador.class,colaboradores.get(0).getTipoColaborador_id()).getNombre();
	}

	
	public String cargarPlanesColaborados(String id) {
		StringBuilder objSecciones;
		
		Query query=manager.createNamedQuery("Colaboradores.findColaborados");
		query.setParameter("id", usuario.getUsuario_id());

		List liste=query.getResultList();
		Vector<Planes_De_Negocios> planes=new Vector<Planes_De_Negocios>();
		Vector<Colaboradores> colaborados = new Vector<Colaboradores>();
			for (Object o : liste) {
				Colaboradores colaborador = (Colaboradores) o;
				if(colaborador.getPrincipal_id()==Integer.parseInt(id)){
					query=manager.createNamedQuery("Planes_De_Negocios.findByIdea");
					query.setParameter("id", colaborador.getIdea_id());
				    List lista=query.getResultList();
				    for(Object ob : lista){
				    	Planes_De_Negocios plan=(Planes_De_Negocios) ob;
				    	planes.add(plan);
				    }
				
				}

			}
		
			Planes_De_Negocios auxiliar;
        //Crea un array de objetos JSON con cada tema de la tabla
          objSecciones = new StringBuilder("[");  
          for(int i=0;i<planes.size();i++){
        	  auxiliar=manager.find(Planes_De_Negocios.class,(planes.get(i).getPlan_id()));
          objSecciones.append("{\"Id\":\"");	          
          objSecciones.append(auxiliar.getPlan_id());        
          objSecciones.append("\",\"Nombre\":\"");
          objSecciones.append(auxiliar.getNombre());
          objSecciones.append("\",\"EscritoTerminado\":\"");
          objSecciones.append(auxiliar.isPlanTerminado());
          objSecciones.append("\"},");
          }
          

                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}

	
	public String cargarEdicionPerfil() {
		StringBuilder objSecciones;
		Privacidad privacidad=manager.find(Privacidad.class, usuario.getUsuario_id());
        //Crea un array de objetos JSON con cada tema de la tabla
          objSecciones = new StringBuilder("[");    
          objSecciones.append("{\"Ciudad\":\"");
          objSecciones.append(usuario.getCiudad());
          objSecciones.append("\",\"Pais\":\"");
          objSecciones.append(usuario.getPais());
          objSecciones.append("\",\"Direccion\":\"");
          objSecciones.append(usuario.getDireccion());
          objSecciones.append("\",\"Genero\":\"");
          objSecciones.append(usuario.getGenero_id());
          objSecciones.append("\",\"Telefono\":\"");
          objSecciones.append(usuario.getTelefono());
          objSecciones.append("\",\"Nombre\":\"");
          objSecciones.append(usuario.getNombre());
          objSecciones.append("\",\"Apellido\":\"");
          objSecciones.append(usuario.getApellido());
          objSecciones.append("\",\"Foto\":\"");
          objSecciones.append(usuario.getFoto());
          objSecciones.append("\",\"Reseña\":\"");
          objSecciones.append(usuario.getReseñaPersonal());
          objSecciones.append("\",\"EMail\":\"");
          objSecciones.append(usuario.geteMail());
          objSecciones.append("\",\"Experiencia\":\"");
          objSecciones.append(usuario.getExperiencia_id());
          
          objSecciones.append("\",\"resenap\":\"");
          objSecciones.append(privacidad.getReseñaPersonal());
          objSecciones.append("\",\"paisp\":\"");
          objSecciones.append(privacidad.getPais());
          objSecciones.append("\",\"ciudadp\":\"");
          objSecciones.append(privacidad.getCiudad());
          objSecciones.append("\",\"direccionp\":\"");
          objSecciones.append(privacidad.getDireccion());
          objSecciones.append("\",\"generop\":\"");
          objSecciones.append(privacidad.getGenero());
          objSecciones.append("\",\"telefonop\":\"");
          objSecciones.append(privacidad.getTelefono());
          objSecciones.append("\",\"emailp\":\"");
          objSecciones.append(privacidad.geteMail());
          objSecciones.append("\",\"fechap\":\"");
          objSecciones.append(privacidad.getFechaNacimiento());
          objSecciones.append("\",\"estudiosp\":\"");
          objSecciones.append(privacidad.getEstudios());
          objSecciones.append("\",\"TipoInversionista\":\"");
          if(usuario.getInversionistaTipo_id()!=0){
          objSecciones.append(usuario.getInversionistaTipo_id());
          }
          else{
              objSecciones.append("null");
              }
          objSecciones.append("\",\"TipoInversion\":\"");
          if(usuario.getTipoInversion_id()!=0){
          objSecciones.append(usuario.getTipoInversion_id());
          }
          else{
              objSecciones.append("null");
              }
          objSecciones.append("\",\"NivelInversion\":\"");
          if(usuario.getNivelInversion_id()!=0){
          objSecciones.append(usuario.getNivelInversion_id());
          }
          else{
              objSecciones.append("null");
              }
          objSecciones.append("\",\"Fecha\":\"");
          objSecciones.append(usuario.getFechaNacimiento().toString().substring(0, 10));
          
          objSecciones.append("\",\"Estudio\":\"");
          objSecciones.append(usuario.getEstudios_id());
          objSecciones.append("\"},");
          
          objSecciones.append("[");
          Query query=manager.createNamedQuery("Paises.findAll");
  		

  		List liste=query.getResultList();

  		Vector<Paises> paisesV = new Vector<Paises>();
  			for (Object o : liste) {
  				Paises auxPais = (Paises) o;
  				
  				paisesV.add(auxPais);
  			}	
  			for( int i=0;i<paisesV.size();i++ )
            { objSecciones.append("{\"Id\":\"");
            objSecciones.append(paisesV.get(i).getFips());
            objSecciones.append("\",\"Nombre\":\"");
            objSecciones.append(paisesV.get(i).getNombre());
            objSecciones.append("\"},");
            }
  			objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"],");
  			objSecciones.append("[");
  			query=manager.createNamedQuery("Genero.findAll");
  	  		

  	  		List lista=query.getResultList();

  	  		Vector<Genero> generos = new Vector<Genero>();
  	  			for (Object o : lista) {
  	  			Genero aux = (Genero) o;
  	  				
  	  				generos.add(aux);
  	  			}	
  	  			for( int i=0;i<generos.size();i++ )
  	            { objSecciones.append("{\"Id\":\"");
  	            objSecciones.append(generos.get(i).getGenero_id());
  	            objSecciones.append("\",\"Nombre\":\"");
  	            objSecciones.append(generos.get(i).getGenero());
  	            objSecciones.append("\"},");
  	            }
  	  			objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"],");
  	  			objSecciones.append("[");
  	  			query=manager.createNamedQuery("Estudios.findAll");
  	  		

  	  		List listea=query.getResultList();

  	  		Vector<Estudios> estudiosV = new Vector<Estudios>();
  	  			for (Object o : listea) {
  	  			Estudios aux = (Estudios) o;
  	  				
  	  				estudiosV.add(aux);
  	  			}	
  	  			for( int i=0;i<estudiosV.size();i++ )
  	            { objSecciones.append("{\"Id\":\"");
  	            objSecciones.append(estudiosV.get(i).getEstudio_id());
  	            objSecciones.append("\",\"Nombre\":\"");
  	            objSecciones.append(estudiosV.get(i).getEstudio());
  	            objSecciones.append("\"},");
  	            }
  	  			objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"],");
  	  			objSecciones.append("[");
  	  			query=manager.createNamedQuery("Areas_De_Estudio.findAll");
  	  		

  	  		List listaAE=query.getResultList();

  	  		Vector<Areas_De_Estudio> aDeEstudiosV = new Vector<Areas_De_Estudio>();
  	  			for (Object o : listaAE) {
  	  			Areas_De_Estudio aux = (Areas_De_Estudio) o;
  	  				
  	  				aDeEstudiosV.add(aux);
  	  			}	
  	  			for( int i=0;i<aDeEstudiosV.size();i++ )
  	            { objSecciones.append("{\"Id\":\"");
  	            objSecciones.append(aDeEstudiosV.get(i).getAreaEstudio_id());
  	            objSecciones.append("\",\"Nombre\":\"");
  	            objSecciones.append(aDeEstudiosV.get(i).getNombre());
  	            objSecciones.append("\"},");
  	            }
  	  			objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"],");
  	  		 if(usuario.getInversionistaTipo_id()!=0){
  	  			objSecciones.append("[");
  	  		   query=manager.createNamedQuery("Tipo_Inversionista.findAll");
  	  		

  	  		List listaTIa=query.getResultList();

  	  		Vector<Tipo_Inversionista> tipoInversionistaV = new Vector<Tipo_Inversionista>();
  	  			for (Object o : listaTIa) {
  	  			Tipo_Inversionista aux = (Tipo_Inversionista) o;
  	  				
  	  				tipoInversionistaV.add(aux);
  	  			}	
  	  			for( int i=0;i<tipoInversionistaV.size();i++ )
  	            { objSecciones.append("{\"Id\":\"");
  	            objSecciones.append(tipoInversionistaV.get(i).getTipo_id());
  	            objSecciones.append("\",\"Nombre\":\"");
  	            objSecciones.append(tipoInversionistaV.get(i).getNombre());
  	            objSecciones.append("\"},");
  	            }
  	  			objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"],");
  	  			objSecciones.append("[");
  	  			query=manager.createNamedQuery("Tipo_Inversion.findAll");
  	  	  		

  	  	  		List listaTI=query.getResultList();

  	  	  		Vector<Tipo_Inversion> tipoInv = new Vector<Tipo_Inversion>();
  	  	  			for (Object o : listaTI) {
  	  	  			Tipo_Inversion aux = (Tipo_Inversion) o;
  	  	  				
  	  	  				tipoInv.add(aux);
  	  	  			}	
  	  	  			for( int i=0;i<tipoInv.size();i++ )
  	  	            { objSecciones.append("{\"Id\":\"");
  	  	            objSecciones.append(tipoInv.get(i).getTipo_id());
  	  	            objSecciones.append("\",\"Nombre\":\"");
  	  	            objSecciones.append(tipoInv.get(i).getNombre());
  	  	            objSecciones.append("\"},");
  	  	            }
  	  	  			objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"],");
  	  	  			objSecciones.append("[");
  	  	  			query=manager.createNamedQuery("Nivel_Inversion.findAll");
  	  	  		

  	  	  		List listaNI=query.getResultList();

  	  	  		Vector<Nivel_Inversion> nivelInv = new Vector<Nivel_Inversion>();
  	  	  			for (Object o : listaNI) {
  	  	  			Nivel_Inversion aux = (Nivel_Inversion) o;
  	  	  				
  	  	  				nivelInv.add(aux);
  	  	  			}	
  	  	  			for( int i=0;i<nivelInv.size();i++ )
  	  	            { objSecciones.append("{\"Id\":\"");
  	  	            objSecciones.append(nivelInv.get(i).getNivel_id());
  	  	            objSecciones.append("\",\"Nombre\":\"");
  	  	            objSecciones.append(nivelInv.get(i).getRango());
  	  	            objSecciones.append("\"},");
  	  	            }
  	  	  			objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"],");
  	  		 }  			
  			
                      
        
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}

	
	public void guardarComentarioClase(int id, String comentario) {
		Comentarios_Clases comentarios=new Comentarios_Clases();
		comentarios.setContenida(comentario.replace("\n", "<br>"));
		comentarios.setFecha(new Date());
		comentarios.setClase_id(id);
		comentarios.setUsuario_id(usuario.getUsuario_id());
		manager.merge(comentarios);
		
	}

	
	public void guardarComentarioCharla(int id, String comentario) {
		Comentarios_Charlas comentarios=new Comentarios_Charlas();
		comentarios.setContenida(comentario.replace("\n", "<br>"));
		comentarios.setFecha(new Date());
		comentarios.setCurso_id(id);
		comentarios.setUsuario_id(usuario.getUsuario_id());
		manager.merge(comentarios);
		
	}

	
	public String votarCharla(int id) {
		if(manager.find(Votos_Charla.class, new Votos_CharlaPK(usuario.getUsuario_id(),id))!=null){
			return "ya existe un voto suyo en esta seccion";
		}
		
		Votos_Charla votos=new Votos_Charla();
		votos.setCurso_id(id);
		votos.setUsuario_id(usuario.getUsuario_id());
		manager.persist(votos);
		Cursos curso=manager.find(Cursos.class, id);
		curso.setCalificacion(curso.getCalificacion()+1);
		manager.persist(curso);
		
		return id+"";
	}

	
	public String votarClase(int id) {
		if(manager.find(Votos_Clase.class, new Votos_ClasePK(usuario.getUsuario_id(),id))!=null){
			return "ya existe un voto suyo en esta seccion";
		}
		
		Votos_Clase votos=new Votos_Clase();
		votos.setClase_id(id);
		votos.setUsuario_id(usuario.getUsuario_id());
		manager.persist(votos);
		Clases clase=manager.find(Clases.class, id);
		clase.setCalificacion(clase.getCalificacion()+1);
		manager.persist(clase);
		
		return id+"";
	}

	
	public String marcarMejor(int id) {
		boolean anterior=false;
		int anteriorId=0;
		Respuestas resp=manager.find(Respuestas.class, id);
		Preguntas pre=manager.find(Preguntas.class, resp.getPregunta_id());
		if(!pre.isMejorRespuesta()){
		    anterior=true;
			 Query query=manager.createNamedQuery("Respuestas.findByMyPreg");
		      query.setParameter("id", pre.getPregunta_id());
		  
		  List liste=query.getResultList();
		  
		  Vector<Respuestas> respuestas = new Vector<Respuestas>();
			for (Object o : liste) {
				Respuestas gasto = (Respuestas) o;				
				respuestas.add(gasto);
			}
			for(int i=0;i<respuestas.size();i++){
				if(respuestas.get(i).isMejorRespuesta()){
					Usuarios usuarioAnterior=manager.find(Usuarios.class, respuestas.get(i).getUsuario_id());
					usuarioAnterior.restarRankingRespuestas();
					manager.persist(usuarioAnterior);
					anteriorId=respuestas.get(i).getRespuesta_id();
					
					respuestas.get(i).setMejorRespuesta(false);
				}
			}
			
		}
		Usuarios usuarioActual=manager.find(Usuarios.class, resp.getUsuario_id());
		usuarioActual.sumarRankingRespuestas();
		manager.persist(usuarioActual);
		resp.setMejorRespuesta(true);
		pre.setMejorRespuesta(false);
		manager.persist(pre);
		manager.persist(resp);
		if(anterior){
			return id+"_"+anteriorId;
		}
		
		return id+"";
	}

	
	public String cargarRankingRespuestas() {
		
		Query query=manager.createNamedQuery("Usuarios.findUsuarioReankeadosRespuestas");
	      
	  
	  List liste=query.getResultList();
	  
	  Vector<Usuarios> usuarios = new Vector<Usuarios>();
		for (Object o : liste) {
			Usuarios usuario = (Usuarios) o;				
			usuarios.add(usuario);
		}
		
		
		StringBuilder objSecciones = new StringBuilder("[");  
        for(int i=0;i<usuarios.size();i++){
        objSecciones.append("{\"Id\":\"");
        objSecciones.append(usuarios.get(i).getUsuario_id());
        objSecciones.append("\",\"Nombre\":\"");
        objSecciones.append(usuarios.get(i).getNombre()+" "+usuarios.get(i).getApellido());
        objSecciones.append("\",\"Imagen\":\"");
        objSecciones.append(usuarios.get(i).getFoto());
        objSecciones.append("\",\"Votos\":\"");
        objSecciones.append(usuarios.get(i).getRanking());
        objSecciones.append("\"},");
        }
        objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		return objSecciones.toString();
	}

	
	public String denunciarRespuesta(int id) {
		Denuncias den=manager.find(Denuncias.class, id);
		if(den==null){
	 den=new Denuncias();
	den.setCantidadDenuncias(1);
		}
		else{
			den.setCantidadDenuncias(den.getCantidadDenuncias()+1);
			
		}
		den.setRespuesta_id(id);
		Usuarios_Denuncias usDen=new Usuarios_Denuncias();
		usDen.setDenuncia_id(id);
		usDen.setUsuario_id(usuario.getUsuario_id());
		manager.persist(den);
		manager.persist(usDen);
		
		return id+"";
	}

	
	public String cargarDenunciasRespuestas() {
		
		Query query=manager.createNamedQuery("Denuncias.findAll");
	      
		  
		  List liste=query.getResultList();
		  
		  Vector<Denuncias> denunicas = new Vector<Denuncias>();
			for (Object o : liste) {
				Denuncias denuncia = (Denuncias) o;				
				denunicas.add(denuncia);
			}
			
			
			StringBuilder objSecciones = new StringBuilder("[");  
	        for(int i=0;i<denunicas.size();i++){
	        objSecciones.append("{\"Id\":\"");
	        objSecciones.append(denunicas.get(i).getRespuesta_id());
	        objSecciones.append("\",\"Cantidad\":\"");
	        objSecciones.append(denunicas.get(i).getCantidadDenuncias());
	        objSecciones.append("\",\"Pregunta\":\"");
	        objSecciones.append(manager.find(Preguntas.class, manager.find(Respuestas.class,denunicas.get(i).getRespuesta_id()).getPregunta_id()).getContenido());
	        objSecciones.append("\",\"Respuesta\":\"");
	        objSecciones.append(manager.find(Respuestas.class,denunicas.get(i).getRespuesta_id()).getContenido());
	        objSecciones.append("\",\"Denunciada\":\"");  	
	        Usuarios_Denuncias usDen=manager.find(Usuarios_Denuncias.class, new Usuarios_DenunciasPK(usuario.getUsuario_id(),denunicas.get(i).getRespuesta_id()));
	        if(usDen==null){
	        	objSecciones.append(false);
	        }
	        else{
	        	objSecciones.append(true);
	        }
	        
	        objSecciones.append("\"},");
	        }
	        objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
			return objSecciones.toString();
	}

	
	public String cargarVotacionIdeas() {
		 StringBuilder objSecciones;
			
			Query query=manager.createNamedQuery("Idea_De_Negocio_Usuarios.findNoMyId");
			  query.setParameter("id", usuario.getUsuario_id());
			  
			  List liste=query.getResultList();
			 Vector<Idea_De_Negocio_Usuarios> ideas=new Vector<Idea_De_Negocio_Usuarios>();
			 
			 
				for (Object o : liste) {
					Idea_De_Negocio_Usuarios idea = (Idea_De_Negocio_Usuarios) o;
					
					ideas.add(idea);

				}
			
			
				Idea_De_Negocio auxiliar;
	        //Crea un array de objetos JSON con cada tema de la tabla
	          objSecciones = new StringBuilder("[");  
	          for(int i=0;i<ideas.size();i++){
	          objSecciones.append("{\"Id\":\"");
	          auxiliar=manager.find(Idea_De_Negocio.class,(ideas.get(i).getIdea_id()));
	          objSecciones.append(auxiliar.getIdea_id());        
	          objSecciones.append("\",\"Nombre\":\"");
	          objSecciones.append(auxiliar.getNombre());
	          objSecciones.append("\",\"Votos\":\"");
	          objSecciones.append(auxiliar.getCalificacion());
	          objSecciones.append("\"},");
	          }
	          

	                      
	        //Sustituye la última coma por el carácter de cierre del array
	          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
			
			return objSecciones.toString();
	}

	
	public String votarIdea(int id) {
		if(manager.find(Usuarios_Votacion_Ideas.class, new Usuarios_Votacion_IdeasPK(usuario.getUsuario_id(),id))!=null){
			return "ya existe un voto suyo en esta seccion";
		}
		
		Usuarios_Votacion_Ideas votos=new Usuarios_Votacion_Ideas();
		votos.setIdea_id(id);
		votos.setUsuario_id(usuario.getUsuario_id());
		manager.persist(votos);
		Idea_De_Negocio idea=manager.find(Idea_De_Negocio.class, id);
		idea.setCalificacion(idea.getCalificacion()+1);
		manager.persist(idea);
		
		return id+"";
	}

	
	public String cargarInvertirIdeas() {
		StringBuilder objSecciones;
		
		Query query=manager.createNamedQuery("Idea_De_Negocio.findAll");
		  
		  
		  List liste=query.getResultList();
		 Vector<Idea_De_Negocio> ideas=new Vector<Idea_De_Negocio>();
		 
		 
			for (Object o : liste) {
				Idea_De_Negocio idea = (Idea_De_Negocio) o;
				
				ideas.add(idea);

			}
		
		
			
        //Crea un array de objetos JSON con cada tema de la tabla
          objSecciones = new StringBuilder("[");  
          for(int i=0;i<ideas.size();i++){
          objSecciones.append("{\"Id\":\"");
          objSecciones.append(ideas.get(i).getIdea_id());        
          objSecciones.append("\",\"Nombre\":\"");
          objSecciones.append(ideas.get(i).getNombre());
          objSecciones.append("\",\"Inversion\":\"");
          objSecciones.append(ideas.get(i).getInversionRecogida());
          objSecciones.append("\"},");
          }
          

                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}

	
	public String invertirIdea(int id, int monto) {
		Usuarios_Inversion_Ideas votos=manager.find(Usuarios_Inversion_Ideas.class, new Usuarios_Inversion_IdeasPK(usuario.getUsuario_id(),id));
		
		if(votos==null){
			 votos=new Usuarios_Inversion_Ideas();
				votos.setIdea_id(id);
				votos.setUsuario_id(usuario.getUsuario_id());
				votos.setInversion(monto);
		}
		else{
			votos.setInversion(votos.getInversion()+monto);
		}
		
        
		manager.persist(votos);
		Idea_De_Negocio idea=manager.find(Idea_De_Negocio.class, id);
		idea.setInversionRecogida(idea.getInversionRecogida()+monto);
		manager.persist(idea);
		
		return idea.getInversionRecogida()+"";
	}

	public String buscarUsuarios(String texto) {
		
		StringBuilder objSecciones;
		
		
		
		

        objSecciones = new StringBuilder("[");  
       
        
        
        Query query=manager.createNamedQuery("Usuarios.findUsuarios");
		  query.setParameter("texto1", "%"+texto);
		  query.setParameter("texto2", texto+"%");
		  query.setParameter("texto3", "%"+texto+"%");
		  query.setParameter("texto4", texto+"%");
		  query.setParameter("texto5", "%"+texto);
		  query.setParameter("texto6", "%"+texto+"%");
		  
		  List liste=query.getResultList();
		  
		  Vector<Usuarios> usuarios = new Vector<Usuarios>();
			for (Object o : liste) {
				Usuarios usuario = (Usuarios) o;
				
				usuarios.add(usuario);
				

			}
        
        
        for(int i=0;i<usuarios.size();i++){

      		  objSecciones.append("{\"Id\":\"");
                objSecciones.append(usuarios.get(i).getUsuario_id());
                objSecciones.append("\",\"Nombre\":\"");  		  
                objSecciones.append(usuarios.get(i).getNombre()+" "+usuarios.get(i).getApellido());
                objSecciones.append("\",\"Imagen\":\"");  		  
                objSecciones.append(usuarios.get(i).getFoto());
                objSecciones.append("\"},");
      	  
            
                     
            }


                    
      //Sustituye la última coma por el carácter de cierre del array
        objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}

	
	public String buscarCharlas(String texto) {
StringBuilder objSecciones;
		
		Query query=manager.createNamedQuery("Cursos.findCharlas");
		  query.setParameter("texto1", "%"+texto);
		  query.setParameter("texto2", texto+"%");
		  query.setParameter("texto3", "%"+texto+"%");
		  query.setParameter("texto4", texto+"%");
		  query.setParameter("texto5", "%"+texto);
		  query.setParameter("texto6", "%"+texto+"%");
		  query.setParameter("texto7", texto+"%");
		  query.setParameter("texto8", "%"+texto);
		  query.setParameter("texto9", "%"+texto+"%");
		  
		  List liste=query.getResultList();
		 Vector<Cursos> clases=new Vector<Cursos>();
		 
		 
			for (Object o : liste) {
				Cursos clase = (Cursos) o;
				
				clases.add(clase); 

			}
		
		

          objSecciones = new StringBuilder("[");  
          for(int i=0;i<clases.size();i++){
        	 
          objSecciones.append("{\"Id\":\"");          
          objSecciones.append(clases.get(i).getCurso_id());        
          objSecciones.append("\",\"Nombre\":\"");
          objSecciones.append(clases.get(i).getNombre());
          objSecciones.append("\",\"Votos\":\"");
          objSecciones.append(clases.get(i).getCalificacion());
          objSecciones.append("\"},");
          }
          

                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}

	
	public String buscarClases(String texto) {
	
StringBuilder objSecciones;
		
		Query query=manager.createNamedQuery("Clases.findClases");
		  query.setParameter("texto1", "%"+texto);
		  query.setParameter("texto2", texto+"%");
		  query.setParameter("texto3", "%"+texto+"%");
		  query.setParameter("texto4", texto+"%");
		  query.setParameter("texto5", "%"+texto);
		  query.setParameter("texto6", "%"+texto+"%");
		  query.setParameter("texto7", texto+"%");
		  query.setParameter("texto8", "%"+texto);
		  query.setParameter("texto9", "%"+texto+"%");
		  
		  List liste=query.getResultList();
		 Vector<Clases> clases=new Vector<Clases>();
		 
		 
			for (Object o : liste) {
				Clases clase = (Clases) o;
				
				clases.add(clase); 

			}
		
		

          objSecciones = new StringBuilder("[");  
          for(int i=0;i<clases.size();i++){
        	 
          objSecciones.append("{\"Id\":\"");          
          objSecciones.append(clases.get(i).getClase_id());        
          objSecciones.append("\",\"Nombre\":\"");
          objSecciones.append(clases.get(i).getNombre());
          objSecciones.append("\",\"Votos\":\"");
          objSecciones.append(clases.get(i).getCalificacion());
          objSecciones.append("\"},");
          }
          

                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}

	
	public String buscarGrupos(String texto) {
StringBuilder objSecciones;
		
		Query query=manager.createNamedQuery("Grupos.findGrupos");
		  query.setParameter("texto1", "%"+texto);
		  query.setParameter("texto2", texto+"%");
		  query.setParameter("texto3", "%"+texto+"%");
		  query.setParameter("texto7", texto+"%");
		  query.setParameter("texto8", "%"+texto);
		  query.setParameter("texto9", "%"+texto+"%");
		  
		  List liste=query.getResultList();
		 Vector<Grupos> grupos=new Vector<Grupos>();
		 
		 
			for (Object o : liste) {
				Grupos grupo = (Grupos) o;
				
				grupos.add(grupo); 

			}
		
		

          objSecciones = new StringBuilder("[");  
          for(int i=0;i<grupos.size();i++){
        	 
          objSecciones.append("{\"Id\":\"");          
          objSecciones.append(grupos.get(i).getGrupo_id());        
          objSecciones.append("\",\"Nombre\":\"");
          objSecciones.append(grupos.get(i).getNombre());
          objSecciones.append("\"},");
          }
          

                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}

	
	public String buscarPreguntas(String texto) {
StringBuilder objSecciones;
		
		Query query=manager.createNamedQuery("Preguntas.findPreguntas");
		  query.setParameter("texto1", "%"+texto);
		  query.setParameter("texto2", texto+"%");
		  query.setParameter("texto3", "%"+texto+"%");
		  
		  List liste=query.getResultList();
		 Vector<Preguntas> preguntas=new Vector<Preguntas>();
		 
		 
			for (Object o : liste) {
				Preguntas pregunta = (Preguntas) o;
				
				preguntas.add(pregunta); 

			}
		
		

          objSecciones = new StringBuilder("[");  
          for(int i=0;i<preguntas.size();i++){
         	 
              objSecciones.append("{\"Id\":\"");          
              objSecciones.append(preguntas.get(i).getPregunta_id());        
              objSecciones.append("\",\"Contenido\":\"");
              objSecciones.append(preguntas.get(i).getContenido());
              objSecciones.append("\"},");
              }
          

                      
        //Sustituye la última coma por el carácter de cierre del array
          objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}

	
	public String agregarAmigo(int id) {
		Amigos amigo=manager.find(Amigos.class, new AmigosPK(id,usuario.getUsuario_id()));
		if(amigo!=null){
			
			amigo.setSolicitudEstado_id(3);
		}
		else{
		amigo=new Amigos();
		amigo.setAmigo_id(id);
		amigo.setSolicitudEstado_id(3);
		amigo.setUsuario_id(usuario.getUsuario_id());
		
		}
		manager.persist(amigo);
		return "solicitud enviada";
	}


	public String cargarSolicitudes() {
		StringBuilder objSecciones;
		
		
		
		

        objSecciones = new StringBuilder("[");  
       
        
        
        Query query=manager.createNamedQuery("Amigos.findSolicitudes");
		  query.setParameter("id", usuario.getUsuario_id());
		  
		  List liste=query.getResultList();
		  
		  Vector<Amigos> amigos = new Vector<Amigos>();
			for (Object o : liste) {
				Amigos amigo = (Amigos) o;
				if(amigo.getSolicitudEstado_id()==3){
				amigos.add(amigo);
				}

			}
        
        
        for(int i=0;i<amigos.size();i++){
      	 
    		  objSecciones.append("{\"Id\":\"");
            objSecciones.append(amigos.get(i).getUsuario_id());
            objSecciones.append("\",\"Nombre\":\"");  		  
            objSecciones.append(manager.find(Usuarios.class,amigos.get(i).getUsuario_id()).getNombre()+" "+manager.find(Usuarios.class,amigos.get(i).getUsuario_id()).getApellido());
            objSecciones.append("\",\"Imagen\":\"");  		  
            objSecciones.append(manager.find(Usuarios.class,amigos.get(i).getUsuario_id()).getFoto());
            objSecciones.append("\"},");

            
                     
            }


                    
      //Sustituye la última coma por el carácter de cierre del array
        objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
		
		return objSecciones.toString();
	}


	public String aceptarSolicitud(int id) {
		Amigos amigo=manager.find(Amigos.class, new AmigosPK(usuario.getUsuario_id(),id));
		amigo.setSolicitudEstado_id(1);
		amigo.setFechaCreacion(new Date());
		manager.persist(amigo);
		return "amistad creada";
	}

	
	public String rechazarSolicitud(int id) {
		Amigos amigo=manager.find(Amigos.class, new AmigosPK(usuario.getUsuario_id(),id));
		amigo.setSolicitudEstado_id(2);
		manager.persist(amigo);
		return "amistad rechazada";
	}

	
	public String votarNoCharla(int id) {
		if(manager.find(Negativos_Charlas.class, new Negativos_CharlasPK(usuario.getUsuario_id(),id))!=null){
			return "ya existe un voto negativo suyo en esta seccion";
		}
		
		Negativos_Charlas votos=new Negativos_Charlas();
		votos.setCurso_id(id);
		votos.setUsuario_id(usuario.getUsuario_id());
		manager.persist(votos);
		Cursos curso=manager.find(Cursos.class, id);
		curso.sumarNegativos();
		manager.persist(curso);
		
		return id+"";
	}

	public String votarNoClase(int id) {
		if(manager.find(Negativos_Clases.class, new Negativos_ClasesPK(usuario.getUsuario_id(),id))!=null){
			return "ya existe un voto negativo suyo en esta seccion";
		}
		
		Negativos_Clases votos=new Negativos_Clases();
		votos.setClase_id(id);
		votos.setUsuario_id(usuario.getUsuario_id());
		manager.persist(votos);
		Clases clase=manager.find(Clases.class, id);
		clase.sumarNegativos();
		manager.persist(clase);
		
		return id+"";
	}


	public String eliminarPlan(String id) {
	     Planes_De_Negocios plan=manager.find(Planes_De_Negocios.class, Integer.parseInt(id));
	     System.out.println(plan.getNombre());
	     manager.remove(plan);
			
		return null;
	}

	
	public String eliminarIdea(String id) {
	     Idea_De_Negocio idea=manager.find(Idea_De_Negocio.class, Integer.parseInt(id));
	     System.out.println(idea.getNombre());
	     manager.remove(idea);
		return null;
	}

	




}
