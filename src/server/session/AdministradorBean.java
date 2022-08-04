package server.session;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.ejb.*;
import javax.persistence.*;

import server.entities.*;


@Stateful
public class AdministradorBean implements AdministradorRemote {
	@PersistenceContext(unitName = "Enwip")
	private EntityManager manager;
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
	
	private Administradores administrador;
	private int ultimaNoticiaId;
	
	
	public String login(String user, String password) {
		String listo="encontrado";
		
		Query query=manager.createNamedQuery("Administradores.findUsuarioByNombreAndPass");
		query.setParameter("nombre", user);
		
		query.setParameter("clave", password);
		 List usuarioz=query.getResultList();
		 if(usuarioz.size()==0){
			 return "Correo y Contraseña incorrectos";
		 }
		
		  administrador=(Administradores) (usuarioz.get(0));
		 	 

		
		
		return listo;
	}
	
	@Remove
	public void remove(){
		System.out.println("estamos en el remove del bean");
	}

	public String cargarNombre() {
		
		return administrador.getNombre();
	}

	
	public void guardarImagenNoticia(String path) {
		Noticias noticia=new Noticias();
		noticia.setImagen(path);
		
		List liste= manager.createNamedQuery("Noticias.findAll").getResultList();
		int mayor=0;
		
		for (Object o : liste) {
			Noticias noti = (Noticias) o;
			
			if(mayor<=noti.getNoticia_id()){
				mayor=noti.getNoticia_id();
			}

		}
		this.ultimaNoticiaId=mayor+1;
		noticia.setNoticia_id(ultimaNoticiaId);
		noticia.setAdministrador_id(administrador.getAdministrador_id());
		manager.persist(noticia);
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


	public void guardarContenidoNoticia(String contenido) {
		Noticias noticia=manager.find(Noticias.class, this.ultimaNoticiaId);
		noticia.setContenido(contenido);
		manager.persist(noticia);
		
	}
}
