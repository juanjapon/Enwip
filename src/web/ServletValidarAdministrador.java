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

import server.session.AdministradorRemote;


public class ServletValidarAdministrador extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	 private AdministradorRemote admin;
		
		
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public ServletValidarAdministrador() {
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
	        String resultado = "<b>Datos correctos</b>";
	        boolean listo=true;
	        try{
	        	HttpSession session = request.getSession();
	        	

	        	admin=(AdministradorRemote) session.getAttribute("AdministradorBean");
	        	 
	        	if(admin==null){
	        		System.out.println("si esta en null");
	        Properties p=new Properties();
	        p.put(Context.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
	        p.put(Context.URL_PKG_PREFIXES,"org.jboss.naming:org.jnp.interfaces");
			p.put(Context.PROVIDER_URL, "jnp://localhost:1099");
			Context ctx = new InitialContext(p);
			Object ref=ctx.lookup("AdministradorBean/remote");
			admin=(AdministradorRemote)PortableRemoteObject.narrow(ref, AdministradorRemote.class);
			session.setAttribute("AdministradorBean", admin);
	      //Recupera todos los nombre de parámetros recibidos en la petición.
			
			
			Enumeration<String> nombres1 = request.getParameterNames();
	        Enumeration<String> nombres = request.getParameterNames();
	        while (nombres1.hasMoreElements())
	        { System.out.println(request.getParameter(nombres1.nextElement()));
	          
	        
	        }
	    
	        while (nombres.hasMoreElements())
	        { if (request.getParameter(nombres.nextElement()).equals(""))
	          {   resultado ="<b>Error en los datos. ";
	              resultado += "Debe rellenar todos los campos</b>";
	              listo=false;
	          }
	        
	        }
	        

	        
	       
	      
	         if(listo){
	        	 try
	             {   
	             String user = request.getParameter("user");
	             String pass=request.getParameter("pass");
	             String res;
	             
	                 
	                res= admin.login(user, pass);
	                 if(res=="Correo y Contraseña incorrectos"){
	                	 System.out.println(res);
	 	        		admin.remove();
		        		session.invalidate();
	                 }
	                 
	              System.out.println(res);
	                 
	              
	               
	                             
	           
	                 out.print(res);
	                 out.close();
	                
	                 

	                             
	             }
	             catch(Exception e){e.printStackTrace();}
	             
	            
	             out.close();
	          // System.out.println("service()... end...");
	     	
	         }
	         else{
	        out.print(resultado);
	        out.close();
	         }
	        	}
	        	else{
	        		out.print("encontrado");
	        	}
	        	
	        } catch(Exception e){e.printStackTrace();}
	        	

		}

	}

