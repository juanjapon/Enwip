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
import javax.servlet.http.HttpSession;

import nl.captcha.Captcha;
import server.entities.A?os;
import server.session.EnwipSesionRemote;
import server.session.FormularioEnwipRemote;

public class CargarDatosUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EnwipSesionRemote esr;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargarDatosUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
    
        
        try
        {   HttpSession session = request.getSession();
    	
   	     esr=(EnwipSesionRemote) session.getAttribute("EnwipSesionBean");
   	  if(esr==null){
   		  out.print("iniciar sesion");
   	  }
   	  else{
		String nombre;
		nombre=esr.cargarNombre();
		System.out.println(nombre);
		out.print(nombre);
		//StringBuilder objSecciones;
         // Crea un array de objetos JSON con cada tema de la tabla
          //  objSecciones = new StringBuilder("[");
          // for( int i=0;i<a?os.size();i++ )
          //  { objSecciones.append("{\"A?o\":\"");
          //  objSecciones.append(a?os.get(i).getA?o());
          //  objSecciones.append("\"},");
            //}            
            

                        
         // Sustituye la ?ltima coma por el car?cter de cierre del array
           // objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
            //out.println(objSecciones.toString());
   	  }           
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