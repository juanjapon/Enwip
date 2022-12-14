package web;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;  

import javax.servlet.ServletContext;  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;
 
import org.apache.commons.fileupload.FileItem;  
import org.apache.commons.fileupload.FileUploadException;  
import org.apache.commons.fileupload.disk.DiskFileItemFactory;  
import org.apache.commons.fileupload.servlet.ServletFileUpload;  

import server.session.EnwipSesionRemote;
 
/** 
* Servlet implementation class UploadFichero 
*/  
public class SubirFoto extends HttpServlet {  
   private static final long serialVersionUID = 1L;  
   private EnwipSesionRemote esr;
   /** 
    * @see HttpServlet#HttpServlet() 
    */  
   public SubirFoto() {  
       super();  
       // TODO Auto-generated constructor stub  
   }  
 
   /** 
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) 
    */  
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
       doPost(request, response);  
   }  
 
   /** 
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) 
    */  
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
 
       ServletContext ctx = request.getSession().getServletContext();  
       PrintWriter out = response.getWriter();
       System.out.println(ctx.getRealPath("/"));  
 try{
       HttpSession session = request.getSession();
   	
 	     esr=(EnwipSesionRemote) session.getAttribute("EnwipSesionBean");
 	  if(esr==null){
 		  out.print("iniciar sesion");
 	  }
 	  else{
 			Enumeration<String> nombres1 = request.getParameterNames();
 	        while (nombres1.hasMoreElements())
 	        { System.out.println(request.getParameter(nombres1.nextElement()));
 	          
 	        
 	        }
          // construimos el objeto que es capaz de parsear la perici?n  
       // Create a factory for disk-based file items  
       DiskFileItemFactory factory = new DiskFileItemFactory();  
 
       // Set factory constraints  
       factory.setSizeThreshold(1024*512);  
       factory.setRepository(new File(ctx.getRealPath("/")));  
 
       // Create a new file upload handler  
       ServletFileUpload upload = new ServletFileUpload(factory);  
 
       // Set overall request size constraint  
       upload.setSizeMax(-1);  
 
       // Parse the request  
        
 
           List /* FileItem */ items = upload.parseRequest(request);  
           Iterator it = items.iterator();  
 
           FileItem item = null;  
 
           while(it.hasNext()) {  
                 item = (FileItem) it.next();  
                 System.out.println(ctx.getRealPath("/") + item.getName());
                 File file = new File(ctx.getRealPath("/") + item.getName());  
                 item.write(file);  
 
             
            }
           esr.setFoto(item.getName());
           out.print("listo");
 	     }
       } catch (FileUploadException e) {  
           // TODO Auto-generated catch block  
           e.printStackTrace();  
       } catch (Exception e) {  
           e.printStackTrace();  
       }  
 
   }  
 
}  