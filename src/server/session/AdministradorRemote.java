package server.session;

import java.util.Vector;

import javax.ejb.Remote;

import server.entities.Noticias;

@Remote
public interface AdministradorRemote {
	public String login(String user,String password);
	public void remove();
	public String cargarNombre();
	public void guardarImagenNoticia(String path);
	public Vector<Noticias> cargarNoticias();
	public void guardarContenidoNoticia(String contenido);
}
