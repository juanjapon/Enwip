package web;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Properties;

import nl.captcha.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import server.session.FormularioEnwipRemote;



/**
 * Servlet implementation class ServletValidarDatosFormulario
 */
public class ServletCrearCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCrearCuenta() {
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
        Properties p=new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		p.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
		p.put(Context.PROVIDER_URL,"jnp://localhost:1099");
		Context ctx=new InitialContext(p);
		Object ref=ctx.lookup("FormularioEnwipBean/remote");
		FormularioEnwipRemote str=(FormularioEnwipRemote)PortableRemoteObject.narrow(ref,FormularioEnwipRemote.class);
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
        
        Captcha captcha = (Captcha) request.getSession().getAttribute(Captcha.NAME);
        //request.setCharacterEncoding("UTF-8");
        String answer = request.getParameter("answer");
        //System.out.println(captcha.getAnswer());
        if (!captcha.isCorrect(answer)) {
        	 resultado ="<b>las letras no coinciden con la imagen</b>";
             listo=false;
        }
        if(str.comprobarCorreo(request.getParameter("email"))){
        	resultado="<b>El email utilizado ya se encuentra registrado</b>";
        	listo=false;
        }
      
         if(listo){
        	 try
             {   
             String nombre = request.getParameter("nombre");
            // nombre=acomodarTildes(nombre);
             System.out.println(nombre);
             String apellido = request.getParameter("apellido");
            // apellido=acomodarTildes(apellido);
             String password = request.getParameter("pass");
             //password=acomodarTildes(password);
             String pais = request.getParameter("pais");
            // pais=acomodarTildes(pais);
             String ciudad = request.getParameter("ciudad");
            // ciudad=acomodarTildes(ciudad);
             String direccion = request.getParameter("direccion");
            // direccion=acomodarTildes(direccion);
             String telefono = request.getParameter("tel");
            // telefono=acomodarTildes(telefono);
             String email = request.getParameter("email");
            // email=acomodarTildes(email);
             int dia = Integer.parseInt(request.getParameter("dia"));
             String mes = request.getParameter("mes");
             int mesi=1;
             if(mes.equals("Enero")){
            	 mesi=1;
             }
             if(mes.equals("Febrero")){
            	 mesi=2;
             }
             if(mes.equals("Marzo")){
            	 mesi=3;
             }
             if(mes.equals("Abril")){
            	 mesi=4;
             }
             if(mes.equals("Mayo")){
            	 mesi=5;
             }
             if(mes.equals("Junio")){
            	 mesi=6;
             }
             if(mes.equals("Julio")){
            	 mesi=7;
             }
             if(mes.equals("Agosto")){
            	 mesi=8;
             }
             if(mes.equals("Septiembre")){
            	 mesi=9;
             }
             if(mes.equals("Octubre")){
            	 mesi=10;
             }
             if(mes.equals("Noviembre")){
            	 mesi=11;
             }
             if(mes.equals("Diciembre")){
            	 mesi=12;
             }
             int ano = Integer.parseInt(request.getParameter("ano"));
             int genero = (request.getParameter("genero").equals("Hombre")?2:1);
             String estudios =request.getParameter("estudios");
            // estudios=acomodarTildes(estudios);
             String area = request.getParameter("area");
            // area=acomodarTildes(area);
             String fecha = dia+"/"+mesi+"/"+ano;
             String tinversion=request.getParameter("tipoInversion");
           //  tinversion=acomodarTildes(tinversion);
             String tinversionista=request.getParameter("tipoInversionista");
            // tinversionista=acomodarTildes(tinversionista);
             String ninversion=request.getParameter("nivelInversion");
            // ninversion=acomodarTildes(ninversion);
             int tusuario=1;
             if(request.getParameter("tipoUsuario").equals("emprendedor")){
            	 tusuario=2;
             }
             if(request.getParameter("tipoUsuario").equals("premium")){
            	 tusuario=3;
             }
             
             str.crearUsuario(nombre, apellido, password, pais, ciudad, direccion, telefono, email, fecha, genero, estudios, area, tinversion, tinversionista, ninversion, tusuario);
                
                 
                 

              
                 
              // Cierra la conexión a la B.D.   
               
                             
           
                 out.print("listo");
                 out.close();
                 str.enviarCorreo(email);
                 

                             
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
