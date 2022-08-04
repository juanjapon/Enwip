package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import server.session.EnwipSesionRemote;

public class BuscarContenidoCuaderno extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EnwipSesionRemote esr;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscarContenidoCuaderno (){
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
    
        
        try
        {   HttpSession session = request.getSession();
    	
   	     esr=(EnwipSesionRemote) session.getAttribute("EnwipSesionBean");
   	  if(esr==null){
   		  out.print("iniciar sesion");
   	  }
   	  else{
   		  
   		
    
  
		String datos;
		datos=esr.cargarDatosDeCuaderno(Integer.parseInt(request.getParameter("Id")));
		System.out.println(datos);
		out.print(datos);
		//out.print(datos);
		//StringBuilder objSecciones;
         // Crea un array de objetos JSON con cada tema de la tabla
          //  objSecciones = new StringBuilder("[");
          // for( int i=0;i<años.size();i++ )
          //  { objSecciones.append("{\"Año\":\"");
          //  objSecciones.append(años.get(i).getAño());
          //  objSecciones.append("\"},");
            //}            
            

                        
         // Sustituye la última coma por el carácter de cierre del array
           // objSecciones.replace(objSecciones.length()-1,objSecciones.length(),"]");
            //out.println(objSecciones.toString());
   	  }           
        }
        catch(Exception e){e.printStackTrace();}
        out.close();
     // System.out.println("service()... end...");
	}


}

