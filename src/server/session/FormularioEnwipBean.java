package server.session;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;


import javax.ejb.Stateless;
import javax.persistence.*;

import server.entities.*;
import server.util.MailUtils;


@Stateless
public class FormularioEnwipBean implements FormularioEnwipRemote {

	@PersistenceContext(unitName = "Enwip")
	
	private EntityManager manager;
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	public Vector<Paises> getPaises() {
		
		List liste= manager.createNamedQuery("Paises.findAll").getResultList();
		
		Vector<Paises> erg = new Vector<Paises>();
		for (Object o : liste) {
			Paises pais = (Paises) o;
			
			erg.add(pais);

		}
		
		return erg;
		
	}


	public Vector<Estudios> getEstudios() {
		List liste= manager.createNamedQuery("Estudios.findAll").getResultList();
		
		Vector<Estudios> erg = new Vector<Estudios>();
		for (Object o : liste) {
			Estudios estudio = (Estudios) o;
			
			erg.add(estudio);

		}
		
		return erg;
	}


	public Vector<Genero> getGeneros() {
		List liste= manager.createNamedQuery("Genero.findAll").getResultList();
		
		Vector<Genero> erg = new Vector<Genero>();
		for (Object o : liste) {
			Genero genero = (Genero) o;
			
			erg.add(genero);

		}
		
		return erg;
	}

	public Vector<Años> getAños() {
		List liste= manager.createNamedQuery("Años.findAll").getResultList();
		
		Vector<Años> erg = new Vector<Años>();
		for (Object o : liste) {
			Años año = (Años) o;
			
			erg.add(año);

		}
		
		return erg;
	}



	public Vector<Meses> getMeses() {
		List liste= manager.createNamedQuery("Meses.findAll").getResultList();
		
		Vector<Meses> erg = new Vector<Meses>();
		for (Object o : liste) {
			Meses mes = (Meses) o;
			
			erg.add(mes);

		}
		
		return erg;
	}



	public Vector<Dias> getDias() {
		List liste= manager.createNamedQuery("Dias.findAll").getResultList();
		
		Vector<Dias> erg = new Vector<Dias>();
		for (Object o : liste) {
			Dias dia = (Dias) o;
			
			erg.add(dia);

		}
		
		return erg;
	}
	




	public Vector<Areas_De_Estudio> getAreas() {
		List liste= manager.createNamedQuery("Areas_De_Estudio.findAll").getResultList();
		
		Vector<Areas_De_Estudio> erg = new Vector<Areas_De_Estudio>();
		for (Object o : liste) {
			Areas_De_Estudio area = (Areas_De_Estudio) o;
			
			erg.add(area);

		}
		
		return erg;
	}



	public Vector<Tipo_Inversionista> getTipoInversionista() {
		List liste= manager.createNamedQuery("Tipo_Inversionista.findAll").getResultList();
		
		Vector<Tipo_Inversionista> erg = new Vector<Tipo_Inversionista>();
		for (Object o : liste) {
			Tipo_Inversionista tInversionista = (Tipo_Inversionista) o;
			
			erg.add(tInversionista);

		}
		
		return erg;
	}



	public Vector<Tipo_Inversion> getTipoInversion() {
		List liste= manager.createNamedQuery("Tipo_Inversion.findAll").getResultList();
		
		Vector<Tipo_Inversion> erg = new Vector<Tipo_Inversion>();
		for (Object o : liste) {
			Tipo_Inversion tInversion = (Tipo_Inversion) o;
			
			erg.add(tInversion);

		}
		
		return erg;
	}



	public Vector<Nivel_Inversion> getNivelInversion() {
		List liste= manager.createNamedQuery("Nivel_Inversion.findAll").getResultList();
		
		Vector<Nivel_Inversion> erg = new Vector<Nivel_Inversion>();
		for (Object o : liste) {
			Nivel_Inversion nInversion = (Nivel_Inversion) o;
			
			erg.add(nInversion);

		}
		
		return erg;
	}



	public boolean comprobarCorreo(String parameter) {
		boolean esta=false;
		System.out.println("Comprobando correo.............."+parameter);
		Query query=manager.createNamedQuery("Usuarios.findUsuario");
		query.setParameter("correo", parameter);
		List lista=query.getResultList();
		if(!lista.isEmpty()){
			esta=true;
		}
		
		return esta;
	}


	public String crearCodigo(String correo){
		String codigo="";
		char[] trasformer=correo.toCharArray();
		for(int i=0;i<trasformer.length;i++){
			trasformer[i]=(char)(trasformer[i]+(Math.random()*10));
		}
		for(int i=0;i<trasformer.length;i++){
			codigo+=(trasformer[i])+"";
		}
		
		return codigo;
	}
	

	
	public void crearUsuario(String nombre, String apellido, String password,
			String pais, String ciudad, String direccion, String telefono,
			String email, String fecha, int genero, String estudios,
			String area, String tinversion, String tinversionista,
			String ninversion, int tusuario) {
		Usuarios usuario=new Usuarios();
		
		Date creacion;
		Date fechaNacimiento;
	
		int estudioId;
		boolean bandera=false;
		int calificaciones=4;
		boolean capacidadResponder=true;
		int inversionistaId;
		int tipoInversionId;
		int nivelInversionId;
		int areaId;
		String codigoActivacion=crearCodigo(email);
		
		
		
		
		
		 Query query=manager.createNamedQuery("Paises.findByNombre");
		 query.setParameter("nombre", pais);
		 System.out.println(pais);
		 List paises=query.getResultList();
		 Paises paisO=(Paises) (paises.get(0));
		 pais=paisO.getFips();
		 
		 try {
			 fechaNacimiento=dateFormat.parse(fecha);
			usuario.setFechaNacimiento(fechaNacimiento);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 query=manager.createNamedQuery("Estudios.findByNombre");
		 query.setParameter("nombre", estudios);
		 System.out.println(estudios);
		 List estudioss=query.getResultList();
		 Estudios estudioO=(Estudios) (estudioss.get(0));
		 estudioId=estudioO.getEstudio_id();
		 
		 query=manager.createNamedQuery("Areas_De_Estudio.findByNombre");
		 query.setParameter("nombre", area);
		 System.out.println(area);
		 List areas=query.getResultList();
		 Areas_De_Estudio areaa=(Areas_De_Estudio) (areas.get(0));
		 areaId=areaa.getAreaEstudio_id();
		 
		 creacion=new Date();
		
		 
			usuario.setApellido(apellido);
			usuario.setBandera(bandera);
			usuario.setCalificacionesPermitidas(calificaciones);
			usuario.setCapacidadResponder(capacidadResponder);
			usuario.setCiudad(ciudad);
			usuario.setCodigoActivacion(codigoActivacion);
			usuario.setDireccion(direccion);
			usuario.seteMail(email);
			usuario.setEstudios_id(estudioId);
			usuario.setFechaCreacion(creacion);
			usuario.setExperiencia_id(areaId);
			usuario.setGenero_id(genero);
			
			
			usuario.setNombre(nombre);
			usuario.setPais(pais);
			usuario.setPassword(password);
			usuario.setReseñaPersonal("");
			usuario.setTelefono(telefono);
			
			usuario.setTipoUsuario_id(tusuario);
		 if(tinversionista!=null){
		 query=manager.createNamedQuery("Tipo_Inversionista.findByNombre");
		 query.setParameter("nombre", tinversionista);
		 List inversionistas=query.getResultList();
		 Tipo_Inversionista inversionista=(Tipo_Inversionista) (inversionistas.get(0));
		 inversionistaId=inversionista.getTipo_id();
		 usuario.setInversionistaTipo_id(inversionistaId);
		 query=manager.createNamedQuery("Tipo_Inversion.findByNombre");
		 query.setParameter("nombre", tinversion);
		 List inversion=query.getResultList();
		 Tipo_Inversion inversionO=(Tipo_Inversion) (inversion.get(0));
		 tipoInversionId=inversionO.getTipo_id();
		 usuario.setTipoInversion_id(tipoInversionId);
		 query=manager.createNamedQuery("Nivel_Inversion.findByNombre");
		 query.setParameter("nombre", ninversion);
		 List nivelesInversion=query.getResultList();
		 Nivel_Inversion nivelInversion=(Nivel_Inversion) (nivelesInversion.get(0));
		 nivelInversionId=nivelInversion.getNivel_id();
		 usuario.setNivelInversion_id(nivelInversionId);
		 }
		 else{
			 usuario.setInversionistaTipo_id(0);
			 usuario.setTipoInversion_id(0);
			 usuario.setNivelInversion_id(0);
		 }
		 
		
		manager.persist(usuario);
		 
		 
	}


	



	public void enviarCorreo(String email) {
		String codigoActivacion=crearCodigo(email);
		 MailUtils correoActivacion=new MailUtils();
		 correoActivacion.send(email, "info@enwip.com", "Correo de Activación", "El siguiente codigo es el codigo generado para activar su cuenta\nCodigo: "+ codigoActivacion +"\nPor favor no responder ha este correo.", null);
	}



	

}
