package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import server.session.EnwipSesionRemote;

public class GuardarProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EnwipSesionRemote esr;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuardarProductos() {
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
		
		
		
    
        

        
        	 try
             {   
        		
        		 Enumeration<String> nombres1 = request.getParameterNames();
        		 Vector<String>  nombres=new Vector();
        		 Vector<String>  datos=new Vector();
        	        while (nombres1.hasMoreElements())
        	        { 
        	        	String dato=nombres1.nextElement();
        	        	if(!dato.equals("planId")&&!dato.equals("datosProductos")){
        	            System.out.println("nombres "+dato+" dato "+request.getParameter(dato));	
        	        	nombres.add(dato);
        	        	datos.add(request.getParameter(dato));
        	        	}
        	        	else
        	        		System.out.println("quitando el planid");
        	        
        	        }
        		   System.out.println("tamaño nombres "+nombres.size());
        		   System.out.println("tamaño matriz "+(nombres.size())/9);
        	        String productos[][]=new String[(nombres.size())/9][10];
        	        for(int i=0,j=0;i<nombres.size();i+=9,j++){
        	        	
        	        	String aux[];
        	        	aux=nombres.get(i).split("_");
        	        	System.out.println("la i ="+i+" la j ="+j+" tipo ="+aux[0]);
        	        	productos[j][0]=aux[1];
        	        	productos[j][1]=datos.get(i);
        	        	productos[j][2]=datos.get(i+1);
        	        	productos[j][3]=datos.get(i+2);
        	        	productos[j][4]=datos.get(i+3);
        	        	productos[j][5]=datos.get(i+4);
        	        	productos[j][6]=datos.get(i+5);
        	        	productos[j][7]=datos.get(i+6);
        	        	productos[j][8]=datos.get(i+7);
        	        	productos[j][9]=datos.get(i+8);

        	        	


        	        }
        	        
        	        for(int i=0;i<productos.length;i++){
        	        	for(int j=0;j<productos [i].length;j++){
        	        		System.out.print(" "+productos[i][j]);
        	        	}
        	        	System.out.println();
        	        }
        	  
           
           
             String planId=request.getParameter("planId");
                 
           
                 out.print(esr.guardarProductos(productos,planId));
                 out.close();
                 
                 

                             
             }
             catch(Exception e){e.printStackTrace();}
             out.close();
          // System.out.println("service()... end...");
     	
         
     
        } catch(Exception e){e.printStackTrace();}
	}

}


