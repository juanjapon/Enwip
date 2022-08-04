package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import server.entities.Noticias;
import server.session.AdministradorRemote;

public class CargarNoticiasAdministrador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdministradorRemote admin;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargarNoticiasAdministrador() {
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
    	
   	     admin=(AdministradorRemote) session.getAttribute("AdministradorBean");
   	  if(admin==null){
   		  out.print("iniciar sesion");
   	  }
   	  else{
   		  Vector<Noticias> noticias=admin.cargarNoticias();
		StringBuilder objSecciones;
          //Crea un array de objetos JSON con cada tema de la tabla
            objSecciones = new StringBuilder("[");
           for( int i=0;i<noticias.size();i++ )
            { objSecciones.append("{\"Noticia\":\"");
            objSecciones.append(noticias.get(i).getContenido());
            objSecciones.append("\",\"Imagen\":\"");
            objSecciones.append(noticias.get(i).getImagen());
            objSecciones.append("\"},");
            }            
            

                        
          //Sustituye la última coma por el carácter de cierre del array
            objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
            System.out.print(objSecciones.toString());
            out.println(objSecciones.toString());
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



