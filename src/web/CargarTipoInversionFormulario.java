package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import server.entities.Tipo_Inversion;
import server.session.FormularioEnwipRemote;

public class CargarTipoInversionFormulario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargarTipoInversionFormulario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
      System.out.println("service()...");
        
        try
        {   Properties p=new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		p.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
		p.put(Context.PROVIDER_URL,"jnp://localhost:1099");
		Context ctx=new InitialContext(p);
		Object ref=ctx.lookup("FormularioEnwipBean/remote");
		FormularioEnwipRemote str=(FormularioEnwipRemote)PortableRemoteObject.narrow(ref,FormularioEnwipRemote.class);
		
		Vector<Tipo_Inversion> tInversion;
		tInversion=str.getTipoInversion();
		

		StringBuilder objSecciones;
         // Crea un array de objetos JSON con cada tema de la tabla
            objSecciones = new StringBuilder("[");
           for( int i=0;i<tInversion.size();i++ )
            { objSecciones.append("{\"Tipo\":\"");
            objSecciones.append(tInversion.get(i).getNombre());
            objSecciones.append("\"},");
            }            
            

                        
         // Sustituye la última coma por el carácter de cierre del array
            objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
            System.out.print(objSecciones.toString());
            out.println(objSecciones.toString());
                        
        }
        catch(Exception e){e.printStackTrace();}
        out.close();
     // System.out.println("service()... end...");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
