package web;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import server.session.EnwipSesionRemote;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class CrearReporte extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EnwipSesionRemote esr;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrearReporte() {
        super();
        // TODO Auto-generated constructor stub
    }
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            	 String[] aux=request.getParameter("Id").split("_");
            	 int planId=Integer.parseInt(aux[1]);
            	 ServletContext ctx = request.getSession().getServletContext(); 
            	 System.out.println(ctx.getRealPath("/")); 
            	 
        		 String ruta=ctx.getRealPath("/");
        		 
        		 	String nombre=esr.getNombreId().replace(" ", "_")+planId+".pdf";
        		 	String rutaDescarga=(nombre);
        		 	System.out.println(ruta+nombre);
        		 	String datos=esr.getDatosPlanDeNegocio(planId);
        		    Document document=new Document();
        	        PdfWriter.getInstance(document, new FileOutputStream(ruta+nombre));
        	        
        	        document.open();
        	        
        	        document.add(new Paragraph(datos));
        	        
        	        document.close();
        	        
        	        
        	        out.print(rutaDescarga);

             
             
                
             	//esr.crearIdea(nombre,idea,nacimiento,pais,ciudad,area,tipoInversion,tipoInversionista,nivelInversion);
             	
             	
                
                out.close();

                 
                 

                             
             }
             catch(Exception e){e.printStackTrace();}
             out.close();
          // System.out.println("service()... end...");
     	
         
     
        } catch(Exception e){e.printStackTrace();}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
