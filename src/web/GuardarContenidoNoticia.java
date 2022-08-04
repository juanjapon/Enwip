package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import server.session.AdministradorRemote;

public class GuardarContenidoNoticia extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdministradorRemote admin;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuardarContenidoNoticia() {
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
         	
       	     admin=(AdministradorRemote) session.getAttribute("AdministradorBean");
      //Recupera todos los nombre de par�metros recibidos en la petici�n.
		
		
		Enumeration<String> nombres1 = request.getParameterNames();
        Enumeration<String> nombres = request.getParameterNames();
        while (nombres1.hasMoreElements())
        { System.out.println(request.getParameter(nombres1.nextElement()));
          
        
        }
    
        

        
        	 try
             {   
             String contenido = request.getParameter("contenido");
       
             
             
                
             	admin.guardarContenidoNoticia(contenido);
             	
                 
              
               
                             
           
                 out.print("listo");
                 out.close();
                 
                 

                             
             }
             catch(Exception e){e.printStackTrace();}
             out.close();
          // System.out.println("service()... end...");
     	
         
     
        } catch(Exception e){e.printStackTrace();}
	}

}

