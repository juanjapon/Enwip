import java.util.Properties;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import server.session.FormularioEnwipRemote;
import server.session.UsuarioException;
import server.entities.*;



public class Test {
	public static void main(String[] args){
		try{
			Properties p=new Properties();
			p.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
			p.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
			p.put(Context.PROVIDER_URL,"jnp://localhost:1099");
			Context ctx=new InitialContext(p);
			Object ref=ctx.lookup("FormularioEnwipBean/remote");
			FormularioEnwipRemote str=(FormularioEnwipRemote)PortableRemoteObject.narrow(ref,FormularioEnwipRemote.class);
			Vector<Paises> paises;
			paises=str.getPaises();
			for(int i=0;i<paises.size();i++){
				Paises pais=paises.get(i);
				System.out.println(pais.getNombre());
			}
			
			Vector<Estudios> estudios;
			estudios=str.getEstudios();
			for(int i=0;i<estudios.size();i++){
				Estudios estudio=estudios.get(i);
				System.out.println(estudio.getEstudio());
			}
			
			Vector<Genero> genero;
			genero=str.getGeneros();
			for(int i=0;i<genero.size();i++){
				Genero gen=genero.get(i);
				System.out.println(gen.getGenero());
			}
			
			Vector<Años> años;
			años=str.getAños();
			for(int i=0;i<años.size();i++){
				Años año=años.get(i);
				System.out.println(año.getAño());
			}
			
			Vector<Meses> meses;
			meses=str.getMeses();
			for(int i=0;i<meses.size();i++){
				Meses mes=meses.get(i);
				System.out.println(mes.getMes());
			}
			
			Vector<Dias> dias;
			dias=str.getDias();
			for(int i=0;i<dias.size();i++){
				Dias dia=dias.get(i);
				System.out.println(dia.getDia());
			}
			
			System.out.println("ahora las areas");
			
			Vector<Areas_De_Estudio> areas;
			areas=str.getAreas();
			for(int i=0;i<areas.size();i++){
				Areas_De_Estudio area=areas.get(i);
				System.out.println(area.getNombre());
			}
			
			Vector<Nivel_Inversion> nivel;
			nivel=str.getNivelInversion();
			for(int i=0;i<nivel.size();i++){
				Nivel_Inversion niv=nivel.get(i);
				System.out.println(niv.getRango());
			}
			System.out.println("ahora si los tipos de inversion");
			Vector<Tipo_Inversion> Tipo;
			Tipo=str.getTipoInversion();
			for(int i=0;i<Tipo.size();i++){
				Tipo_Inversion niv=Tipo.get(i);
				System.out.println(niv.getNombre());
			}
		}
		catch(NamingException e){
			e.printStackTrace();
		}
		}
}
