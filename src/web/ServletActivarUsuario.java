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

public class ServletActivarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	 private EnwipSesionRemote esr;
		
		
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public ServletActivarUsuario() {
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
	        	
	        	 esr=(EnwipSesionRemote) session.getAttribute("EnwipSesionBean");
	        	
	        	if(esr==null){
	        		System.out.println("si esta en null");
	        Properties p=new Properties();
	        p.put(Context.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
	        p.put(Context.URL_PKG_PREFIXES,"org.jboss.naming:org.jnp.interfaces");
			p.put(Context.PROVIDER_URL, "jnp://localhost:1099");
			Context ctx = new InitialContext(p);
			Object ref=ctx.lookup("EnwipSesionBean/remote");
			esr=(EnwipSesionRemote)PortableRemoteObject.narrow(ref, EnwipSesionRemote.class);
			session.setAttribute("EnwipSesionBean", esr);
	      
	        	}
	        	//Recupera todos los nombre de par?metros recibidos en la petici?n.
				
				
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
		  
		             String res;
		             
		                 
		                res= esr.activarCuenta(request.getParameter("clave"));

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
	        	
	        } catch(Exception e){e.printStackTrace();}
	        	

		}

	}




