package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import server.session.EnwipSesionRemote;

public class GuardarOtrosLegales  extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EnwipSesionRemote esr;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuardarOtrosLegales() {
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
      //Recupera todos los nombre de par�metros recibidos en la petici�n.
		
		
		Enumeration<String> nombres1 = request.getParameterNames();
        
        while (nombres1.hasMoreElements())
        { 
        	
        	System.out.println(request.getParameter(nombres1.nextElement()));
          
        
        }
    
        

        
        	 try
             {   
        		 
        	  
             String otrosLegales = request.getParameter("OtrosLegales");
             
             String planId=request.getParameter("planId");
                 
           
                 out.print(esr.guardarOtrosLegales(otrosLegales,planId));
                 out.close();
                 
                 

                             
             }
             catch(Exception e){e.printStackTrace();}
             out.close();
          // System.out.println("service()... end...");
     	
         
     
        } catch(Exception e){e.printStackTrace();}
	}

}


