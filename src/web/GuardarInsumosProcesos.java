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

public class GuardarInsumosProcesos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EnwipSesionRemote esr;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuardarInsumosProcesos() {
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
        	        	String aux[]=dato.split("_");
        	        	if(!dato.equals("planId")&&!dato.equals("insumosProcesos")&&!aux[0].equals("nuevo")){
        	            System.out.println("nombres "+dato+" dato "+request.getParameter(dato));	
        	        	nombres.add(dato);
        	        	datos.add(request.getParameter(dato));
        	        	}
        	        	else
        	        		System.out.println("quitando el planid");
        	        
        	        }
        		   System.out.println("tamaño nombres "+nombres.size());
        		   System.out.println("tamaño matriz "+(nombres.size())/4);
        	        String insumosProcesos[][]=new String[(nombres.size())/4][6];
        	        for(int i=0,j=0;i<nombres.size();i+=4,j++){
        	        	
        	        	String aux[];
        	        	aux=nombres.get(i).split("_");
        	        	if(!aux[0].equals("nuevo")){
        	        	System.out.println("la i ="+i+" la j ="+j+" tipo ="+aux[0]);
        	        	insumosProcesos[j][0]=aux[1];
        	        	insumosProcesos[j][1]=aux[2];
        	        	insumosProcesos[j][2]=datos.get(i);
        	        	insumosProcesos[j][3]=datos.get(i+1);
        	        	insumosProcesos[j][4]=datos.get(i+2);
        	        	insumosProcesos[j][5]=datos.get(i+3);
        	        	}
        	        	


        	        }
        	        
        	        for(int i=0;i<insumosProcesos.length;i++){
        	        	for(int j=0;j<insumosProcesos [i].length;j++){
        	        		System.out.print(" "+insumosProcesos[i][j]);
        	        	}
        	        	System.out.println();
        	        }
        	  
           
           
             String planId=request.getParameter("planId");
                 
           
                 out.print(esr.guardarInsumosProcesos(insumosProcesos,planId));
                 out.close();
                 
                 

                             
             }
             catch(Exception e){e.printStackTrace();}
             out.close();
          // System.out.println("service()... end...");
     	
         
     
        } catch(Exception e){
        	e.printStackTrace();
        	System.out.println("entro aqui paila");
        
        }
	}

}



