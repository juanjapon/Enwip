package server.session;

import java.util.Vector;

import javax.ejb.*;
import server.entities.*;


@Remote
public interface FormularioEnwipRemote {

	public Vector<Paises> getPaises();
	public Vector<Estudios> getEstudios();
	public Vector<Genero> getGeneros();
	public Vector<Areas_De_Estudio> getAreas();
	public Vector<Años> getAños();
	public Vector<Meses> getMeses();
	public Vector<Tipo_Inversionista> getTipoInversionista();
	public Vector<Tipo_Inversion> getTipoInversion();
	public Vector<Nivel_Inversion> getNivelInversion();
	public Vector<Dias> getDias();
	public boolean comprobarCorreo(String parameter);
	public void crearUsuario(String nombre, String apellido, String password,
			String pais, String ciudad, String direccion, String telefono,
			String email, String fecha, int genero, String estudios,
			String area, String tinversion, String tinversionista,
			String ninversion, int tusuario);
	public void enviarCorreo(String email);

	
	
}
