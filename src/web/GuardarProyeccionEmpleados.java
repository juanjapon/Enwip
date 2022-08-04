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

public class GuardarProyeccionEmpleados extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EnwipSesionRemote esr;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuardarProyeccionEmpleados() {
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
        	        String planId=request.getParameter("planId");
        	        int numEmpleados=esr.getNumeroDeEmpleados(planId);
        	        int numAnos=esr.getNumeroDeA�os(planId);
        		   System.out.println("tama�o nombres "+nombres.size());
        		   System.out.println("tama�o matriz "+numEmpleados);
        	        String proyEmpleados[][]=new String[numEmpleados][(12*numAnos)+1];
        	        for(int i=0,k=0;i<proyEmpleados.length;i++){
        	        	
        	        	String aux[];
        	        	aux=nombres.get(k).split("_");
        	        	System.out.println("la k ="+k+" la j = tipo ="+aux[0]);
        	        	proyEmpleados[i][0]=aux[1];
        	        	for(int j=0,h=0;j<numAnos;h+=12,j++){

        	        	proyEmpleados[i][h+1]=datos.get(k++);
        	        	proyEmpleados[i][h+2]=datos.get(k++);
        	        	proyEmpleados[i][h+3]=datos.get(k++);
        	        	proyEmpleados[i][h+4]=datos.get(k++);
        	        	proyEmpleados[i][h+5]=datos.get(k++);
        	        	proyEmpleados[i][h+6]=datos.get(k++);
        	        	proyEmpleados[i][h+7]=datos.get(k++);
        	        	proyEmpleados[i][h+8]=datos.get(k++);
        	        	proyEmpleados[i][h+9]=datos.get(k++);
        	        	proyEmpleados[i][h+10]=datos.get(k++);
        	        	proyEmpleados[i][h+11]=datos.get(k++);
        	        	proyEmpleados[i][h+12]=datos.get(k++);

        	        	}
        	        	


        	        }
        	        
        	        for(int i=0;i<proyEmpleados.length;i++){
        	        	for(int j=0;j<proyEmpleados [i].length;j++){
        	        		System.out.print(" "+proyEmpleados[i][j]);
        	        	}
        	        	System.out.println();
        	        }
        	  
           
           
           
           
                out.print(esr.guardarProyeccionEmpleados(proyEmpleados,planId));
        	      
                 out.close();
                 
                 

                             
             }
             catch(Exception e){e.printStackTrace();}
             out.close();
          // System.out.println("service()... end...");
     	
         
     
        } catch(Exception e){e.printStackTrace();}
	}

}



