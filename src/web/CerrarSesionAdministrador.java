package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import server.session.AdministradorRemote;


public class CerrarSesionAdministrador extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	 private AdministradorRemote admin;
		
		
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public CerrarSesionAdministrador() {
	        super();
	        // TODO Auto-generated constructor stub
	    }
	   

		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doPost(request,response);
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/plain;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
	        PrintWriter out = response.getWriter();
	        String resultado = "<b>Datos correctos</b>";
	        boolean listo=true;
	        try{
	        	HttpSession session = request.getSession();
	        	
	        	 admin=(AdministradorRemote) session.getAttribute("AdministradorBean");
	        	 System.out.println("entro al servlet pero por fuera");
	        	if(admin==null){
	        		System.out.println("esta en null antes de cerrar la sesion");
	        		session.invalidate();
	        	}
	        	else{
	        		admin.remove();
	        		session.invalidate();
	        	}
	        	//Recupera todos los nombre de parámetros recibidos en la petición.
				
				
	
		      
		             }
		             catch(Exception e){e.printStackTrace();}
		             out.close();
		          // System.out.println("service()... end...");
		     	
		         }
		         
	        	
	       
	        	

		}

	




