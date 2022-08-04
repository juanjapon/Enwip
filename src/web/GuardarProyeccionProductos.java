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

public class GuardarProyeccionProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EnwipSesionRemote esr;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuardarProyeccionProductos() {
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
        	        String planId=request.getParameter("planId");
        	        int numProd=esr.getNumeroDeProductos(planId);
        	        int numAnos=esr.getNumeroDeAños(planId);
        		   System.out.println("tamaño nombres "+nombres.size());
        		   System.out.println("tamaño matriz "+numProd);
        	        String proyProductos[][]=new String[numProd][12+numAnos];
        	        for(int i=0,j=0;i<nombres.size();i+=12+numAnos-1,j++){
        	        	
        	        	String aux[];
        	        	aux=nombres.get(i).split("_");
        	        	System.out.println("la i ="+i+" la j ="+j+" tipo ="+aux[0]);
        	        	proyProductos[j][0]=aux[1];
        	        	proyProductos[j][1]=datos.get(i);
        	        	proyProductos[j][2]=datos.get(i+1);
        	        	proyProductos[j][3]=datos.get(i+2);
        	        	proyProductos[j][4]=datos.get(i+3);
        	        	proyProductos[j][5]=datos.get(i+4);
        	        	proyProductos[j][6]=datos.get(i+5);
        	        	proyProductos[j][7]=datos.get(i+6);
        	        	proyProductos[j][8]=datos.get(i+7);
        	        	proyProductos[j][9]=datos.get(i+8);
        	        	proyProductos[j][10]=datos.get(i+9);
        	        	proyProductos[j][11]=datos.get(i+10);
        	        	proyProductos[j][12]=datos.get(i+11);
        	        	for(int k=1;k<numAnos;k++){
        	        		proyProductos[j][12+k]=datos.get(i+11+k);
        	        	}
        	        	
        	        	


        	        }
        	        
        	        for(int i=0;i<proyProductos.length;i++){
        	        	for(int j=0;j<proyProductos [i].length;j++){
        	        		System.out.print(" "+proyProductos[i][j]);
        	        	}
        	        	System.out.println();
        	        }
        	  
           
           
           
           
                 out.print(esr.guardarProyeccionProductos(proyProductos,planId));
                 out.close();
                 
                 

                             
             }
             catch(Exception e){e.printStackTrace();}
             out.close();
          // System.out.println("service()... end...");
     	
         
     
        } catch(Exception e){e.printStackTrace();}
	}

}


