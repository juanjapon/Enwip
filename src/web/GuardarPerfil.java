package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import server.session.EnwipSesionRemote;
import server.session.FormularioEnwipRemote;

public class GuardarPerfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EnwipSesionRemote esr;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuardarPerfil() {
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
		
		
		Enumeration<String> nombres1 = request.getParameterNames();
        Enumeration<String> nombres = request.getParameterNames();
        while (nombres1.hasMoreElements())
        { System.out.println(request.getParameter(nombres1.nextElement()));
          
        
        }
    
        

        
        	 try
             {   
             String reseña = request.getParameter("reseñaPersonal");
             String nombre=request.getParameter("nombre");
             String apellidos=request.getParameter("apellido");
             String pais=request.getParameter("pais");
             String ciudad=request.getParameter("ciudad");
             String direccion=request.getParameter("direccion");
             String telefono=request.getParameter("telefono");
             String aux[]=(request.getParameter("fecha")).split("-");
             String fecha=aux[2]+"/"+aux[1]+"/"+aux[0];
             int genero=Integer.parseInt(request.getParameter("genero"));
             int estudios=Integer.parseInt(request.getParameter("estudio"));
             int area=Integer.parseInt(request.getParameter("experiencia"));
             int tipoInversionista=Integer.parseInt(request.getParameter("tipoInversionista"));
             int tipoInversion=Integer.parseInt(request.getParameter("tipoInversion"));
             int nivelInversion=Integer.parseInt(request.getParameter("nivelInversion"));
             int reseñap =Integer.parseInt(request.getParameter("reseñap"));
             int paisp =Integer.parseInt(request.getParameter("paisp"));
             int ciudadp =Integer.parseInt(request.getParameter("ciudadp"));
             int direccionp =Integer.parseInt(request.getParameter("direccionp"));
             int generop =Integer.parseInt(request.getParameter("generop"));
             int telefonop =Integer.parseInt(request.getParameter("telefonop"));
             int emailp =Integer.parseInt(request.getParameter("emailp"));
             int fechap =Integer.parseInt(request.getParameter("fechap"));
             int estudiosp =Integer.parseInt(request.getParameter("estudiosp"));
             
                
             	esr.actualizarPerfil(reseña,nombre,apellidos,pais,ciudad,direccion,telefono,fecha,genero,estudios,area,tipoInversionista,tipoInversion,nivelInversion,reseñap,paisp,ciudadp,direccionp,generop,telefonop,emailp,fechap,estudiosp);
             	
                 
              // Cierra la conexión a la B.D.   
               
                             
           
                 out.print("listo");
                 out.close();
                 
                 

                             
             }
             catch(Exception e){e.printStackTrace();}
             out.close();
          // System.out.println("service()... end...");
     	
         
     
        } catch(Exception e){e.printStackTrace();}
	}

}
