package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import server.session.EnwipSesionRemote;

public class GuardarModificacionIdeaDeNegocio  extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EnwipSesionRemote esr;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuardarModificacionIdeaDeNegocio() {
        super();
        // TODO Auto-generated constructor stub
    }
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
      
        try{
        	 HttpSession session = request.getSession();
         	
       	     esr=(EnwipSesionRemote) session.getAttribute("EnwipSesionBean");
      //Recupera todos los nombre de parámetros recibidos en la petición.
		
       	  //Enumeration<String> nombres1 = request.getParameterNames();
         // Enumeration<String> nombres = request.getParameterNames();
         // while (nombres1.hasMoreElements())
        //  { System.out.println(request.getParameter(nombres1.nextElement()));
            
          
       //   }
		
    
        

        
        	 try
             {   
        		 int id=Integer.parseInt(request.getParameter("id"));
        	 String nombre=request.getParameter("nombre");
             String idea = request.getParameter("idea");
             String nacimiento = request.getParameter("nacimiento");
             String pais = request.getParameter("pais");
             String ciudad= request.getParameter("ciudad");
             String area = request.getParameter("area");
             String tipoInversion = request.getParameter("tipoInversion");
             String tipoInversionista = request.getParameter("tipoInversionista");
             String nivelInversion = request.getParameter("nivelInversion");
             
             
                
             	esr.guardarModificacionIdea(id,nombre,idea,nacimiento,pais,ciudad,area,tipoInversion,tipoInversionista,nivelInversion);
             	
             	
                out.print("listo");
                out.close();

                 
                 

                             
             }
             catch(Exception e){e.printStackTrace();}
             out.close();
          // System.out.println("service()... end...");
     	
         
     
        } catch(Exception e){e.printStackTrace();}
	}

}
