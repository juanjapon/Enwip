package server.session;

import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import server.entities.*;

public class FinanzasProcesadas {
  
  private EntityManager manager;
  
  private double[] ventas;
  private double[] costosVentas;
  private double[] costosFijos;
  private double[] gastosAdministracion;
  private double[]manoDeObra;
  private double invActivosFijos;
  private double medios;
  private double impuestos;
  private double inversionPreoperativa;
  private int planId;
  private int numAnos;
  
  public FinanzasProcesadas(int id, EntityManager man){
	  this.planId=id;
	  System.out.println("antes de cargar las proyecciones "+planId);
	 
	  this.manager= man;
		Proyecciones pro=manager.find(Proyecciones.class, planId);
		System.out.println("despues de cargar las proyecciones");
		if(pro!=null){
		this.numAnos=pro.getNumeroA�os();
		
			ventas=new double[numAnos];
			costosVentas=new double[numAnos];
			costosFijos=new double[numAnos];
			gastosAdministracion=new double[numAnos];
			manoDeObra=new double[numAnos];
			for(int i=0;i<numAnos;i++){
				ventas[i]=0;
			}
			for(int i=0;i<numAnos;i++){
				costosVentas[i]=0;
			}
			for(int i=0;i<numAnos;i++){
				costosFijos[i]=0;
			}
			for(int i=0;i<numAnos;i++){
				gastosAdministracion[i]=0;
			}
			for(int i=0;i<numAnos;i++){
				manoDeObra[i]=0;
			}

		}
		else{
		this.numAnos=0;

		}

		
  }


public double[] getVentas() {
	if(numAnos!=0){
		System.out.println("entro a ventasssssssssssssss");
	Query query=manager.createNamedQuery("Productos.findByPlanId");
	query.setParameter("id", planId);
	
	List prods=query.getResultList();

	Vector<Productos> productos=new Vector<Productos>();


	for (Object o : prods) {
		Productos producto = (Productos) o;

		productos.add(producto);

	}
	for(int i=0;i<productos.size();i++){
	query=manager.createNamedQuery("A�o_proyecciones_ventas.findByProductoId");
	query.setParameter("id",productos.get(i).getProducto_id());
	
	List lista=query.getResultList();

	Vector<A�o_proyecciones_ventas> a�osProyecciones=new Vector<A�o_proyecciones_ventas>();


	for (Object o : lista) {
		A�o_proyecciones_ventas a�o = (A�o_proyecciones_ventas) o;
		
		a�osProyecciones.add(a�o);

	}
	for(int j=0,cantidadAnterior=0;j<a�osProyecciones.size();j++){
		
		if(j==0){
			cantidadAnterior=(a�osProyecciones.get(j).getMes1()+a�osProyecciones.get(j).getMes2()+a�osProyecciones.get(j).getMes3()+a�osProyecciones.get(j).getMes4()+a�osProyecciones.get(j).getMes5()+a�osProyecciones.get(j).getMes6()+a�osProyecciones.get(j).getMes7()+a�osProyecciones.get(j).getMes8()+a�osProyecciones.get(j).getMes9()+a�osProyecciones.get(j).getMes10()+a�osProyecciones.get(j).getMes11()+a�osProyecciones.get(j).getMes12());
			double valorVentas=(productos.get(i).getPrecioVenta()*(a�osProyecciones.get(j).getMes1()+a�osProyecciones.get(j).getMes2()+a�osProyecciones.get(j).getMes3()+a�osProyecciones.get(j).getMes4()+a�osProyecciones.get(j).getMes5()+a�osProyecciones.get(j).getMes6()+a�osProyecciones.get(j).getMes7()+a�osProyecciones.get(j).getMes8()+a�osProyecciones.get(j).getMes9()+a�osProyecciones.get(j).getMes10()+a�osProyecciones.get(j).getMes11()+a�osProyecciones.get(j).getMes12()));
			ventas[j]= ventas[j]+valorVentas;
			System.out.println("primer a�o:"+ventas[j]);
		}
		else{
			System.out.println("porcentaje incremento de la base"+a�osProyecciones.get(j).getPorcentajeIncremento());
			double porcBase=a�osProyecciones.get(j).getPorcentajeIncremento();
			double porcInc=porcBase/100.0;
			System.out.println("porcentaje incrementado"+porcInc);
			double cantidad=cantidadAnterior*porcInc;
			System.out.println("cantidad incrementado"+cantidad);
			double total=cantidad+cantidadAnterior;
			cantidadAnterior=(int)(total);
			System.out.println("total de ese a�o"+total);
			double valorVentas=total*productos.get(i).getPrecioVenta();
			ventas[j]=ventas[j]+(valorVentas);
			System.out.println("a�o"+(j+1)+" :"+ventas[j]);
		}
	}
	}
	return ventas;
	}
	return null;
	
}

public double[]getCostosVentas() {
	if(numAnos!=0){
		System.out.println("entro a Gastosssssventasssssssssssssss");
	Query query=manager.createNamedQuery("Gastos_Ventas.findByPlanId");
	query.setParameter("id", planId);
	
	List prods=query.getResultList();

	Vector<Gastos_Ventas> gastosVentas=new Vector<Gastos_Ventas>();


	for (Object o : prods) {
		Gastos_Ventas gastoVenta = (Gastos_Ventas) o;

		gastosVentas.add(gastoVenta);

	}
	for(int i=0;i<gastosVentas.size();i++){
	query=manager.createNamedQuery("A�o_Proyecciones_Gastos_Ventas.findByCostoId");
	query.setParameter("id",gastosVentas.get(i).getGastos_id());
	
	List lista=query.getResultList();

	Vector<A�o_Proyecciones_Gastos_Ventas> a�osProyecciones=new Vector<A�o_Proyecciones_Gastos_Ventas>();


	for (Object o : lista) {
		A�o_Proyecciones_Gastos_Ventas a�o = (A�o_Proyecciones_Gastos_Ventas) o;
		
		a�osProyecciones.add(a�o);

	}
	for(int j=0;j<a�osProyecciones.size();j++){
		

			
			double totalAno=0;
			if(a�osProyecciones.get(j).isMes1()){
				totalAno+=gastosVentas.get(i).getCostoMensual();
			}
			if(a�osProyecciones.get(j).isMes2()){
				totalAno+=gastosVentas.get(i).getCostoMensual();
			}
			if(a�osProyecciones.get(j).isMes3()){
				totalAno+=gastosVentas.get(i).getCostoMensual();
			}
			if(a�osProyecciones.get(j).isMes4()){
				totalAno+=gastosVentas.get(i).getCostoMensual();
			}
			if(a�osProyecciones.get(j).isMes5()){
				totalAno+=gastosVentas.get(i).getCostoMensual();
			}
			if(a�osProyecciones.get(j).isMes6()){
				totalAno+=gastosVentas.get(i).getCostoMensual();
			}
			if(a�osProyecciones.get(j).isMes7()){
				totalAno+=gastosVentas.get(i).getCostoMensual();
			}
			if(a�osProyecciones.get(j).isMes8()){
				totalAno+=gastosVentas.get(i).getCostoMensual();
			}
			if(a�osProyecciones.get(j).isMes9()){
				totalAno+=gastosVentas.get(i).getCostoMensual();
			}
			if(a�osProyecciones.get(j).isMes10()){
				totalAno+=gastosVentas.get(i).getCostoMensual();
			}
			if(a�osProyecciones.get(j).isMes11()){
				totalAno+=gastosVentas.get(i).getCostoMensual();
			}
			if(a�osProyecciones.get(j).isMes12()){
				totalAno+=gastosVentas.get(i).getCostoMensual();
			}
			
			costosVentas[j]=costosVentas[j]+(totalAno);
			System.out.println("a�o"+(j+1)+" :"+costosVentas[j]);
		
	
	}
	
		}
	}
	return costosVentas;
	

}

public double[]getCostosFijos() {
	if(numAnos!=0){
		System.out.println("entro a CostosFijossssssssssss");
	Query query=manager.createNamedQuery("Costos_Fijos.findByPlanId");
	query.setParameter("id", planId);
	
	List prods=query.getResultList();

	Vector<Costos_Fijos> costosFijosV=new Vector<Costos_Fijos>();


	for (Object o : prods) {
		Costos_Fijos costoFijo = (Costos_Fijos) o;

		costosFijosV.add(costoFijo);

	}
	for(int i=0;i<costosFijosV.size();i++){
	query=manager.createNamedQuery("A�o_Proyecciones_Costos_Fijos.findByCostoId");
	query.setParameter("id",costosFijosV.get(i).getCostoFijo_id());
	
	List lista=query.getResultList();

	Vector<A�o_Proyecciones_Costos_Fijos> a�osProyecciones=new Vector<A�o_Proyecciones_Costos_Fijos>();


	for (Object o : lista) {
		A�o_Proyecciones_Costos_Fijos a�o = (A�o_Proyecciones_Costos_Fijos) o;
		
		a�osProyecciones.add(a�o);

	}
	for(int j=0;j<a�osProyecciones.size();j++){
		

			
			double totalAno=0;
			if(a�osProyecciones.get(j).isMes1()){
				totalAno+=costosFijosV.get(i).getCostoMensual();
			}
			if(a�osProyecciones.get(j).isMes2()){
				totalAno+=costosFijosV.get(i).getCostoMensual();
			}
			if(a�osProyecciones.get(j).isMes3()){
				totalAno+=costosFijosV.get(i).getCostoMensual();
			}
			if(a�osProyecciones.get(j).isMes4()){
				totalAno+=costosFijosV.get(i).getCostoMensual();
			}
			if(a�osProyecciones.get(j).isMes5()){
				totalAno+=costosFijosV.get(i).getCostoMensual();
			}
			if(a�osProyecciones.get(j).isMes6()){
				totalAno+=costosFijosV.get(i).getCostoMensual();
			}
			if(a�osProyecciones.get(j).isMes7()){
				totalAno+=costosFijosV.get(i).getCostoMensual();
			}
			if(a�osProyecciones.get(j).isMes8()){
				totalAno+=costosFijosV.get(i).getCostoMensual();
			}
			if(a�osProyecciones.get(j).isMes9()){
				totalAno+=costosFijosV.get(i).getCostoMensual();
			}
			if(a�osProyecciones.get(j).isMes10()){
				totalAno+=costosFijosV.get(i).getCostoMensual();
			}
			if(a�osProyecciones.get(j).isMes11()){
				totalAno+=costosFijosV.get(i).getCostoMensual();
			}
			if(a�osProyecciones.get(j).isMes12()){
				totalAno+=costosFijosV.get(i).getCostoMensual();
			}
			
			costosFijos[j]=costosFijos[j]+(totalAno);
			System.out.println("a�o"+(j+1)+" :"+costosFijos[j]);
		
	
	}
	
		}
	}
	return costosFijos;
	

}

public double[] getGastosAdministracion() {
	if(numAnos!=0){
		System.out.println("entro a GastosAdministracionnnnnnnnn");
	Query query=manager.createNamedQuery("Gastos_Fijos_Administracion.findByPlanId");
	query.setParameter("id", planId);
	
	List prods=query.getResultList();

	Vector<Gastos_Fijos_Administracion> gastosFijosAdministracion=new Vector<Gastos_Fijos_Administracion>();


	for (Object o : prods) {
		Gastos_Fijos_Administracion gastoFijo = (Gastos_Fijos_Administracion) o;

		gastosFijosAdministracion.add(gastoFijo);

	}
	for(int i=0;i<gastosFijosAdministracion.size();i++){
	query=manager.createNamedQuery("A�o_Proyecciones_Gastos_Fijos_Administracion.findByGastoId");
	query.setParameter("id",gastosFijosAdministracion.get(i).getGastoFijo_id());
	
	List lista=query.getResultList();

	Vector<A�o_Proyecciones_Gastos_Fijos_Administracion> a�osProyecciones=new Vector<A�o_Proyecciones_Gastos_Fijos_Administracion>();


	for (Object o : lista) {
		A�o_Proyecciones_Gastos_Fijos_Administracion a�o = (A�o_Proyecciones_Gastos_Fijos_Administracion) o;
		
		a�osProyecciones.add(a�o);

	}
	for(int j=0;j<a�osProyecciones.size();j++){
		

			
			double totalAno=0;
			if(a�osProyecciones.get(j).isMes1()){
				totalAno+=gastosFijosAdministracion.get(i).getCostoMensual();
			}
			if(a�osProyecciones.get(j).isMes2()){
				totalAno+=gastosFijosAdministracion.get(i).getCostoMensual();
			}
			if(a�osProyecciones.get(j).isMes3()){
				totalAno+=gastosFijosAdministracion.get(i).getCostoMensual();
			}
			if(a�osProyecciones.get(j).isMes4()){
				totalAno+=gastosFijosAdministracion.get(i).getCostoMensual();
			}
			if(a�osProyecciones.get(j).isMes5()){
				totalAno+=gastosFijosAdministracion.get(i).getCostoMensual();
			}
			if(a�osProyecciones.get(j).isMes6()){
				totalAno+=gastosFijosAdministracion.get(i).getCostoMensual();
			}
			if(a�osProyecciones.get(j).isMes7()){
				totalAno+=gastosFijosAdministracion.get(i).getCostoMensual();
			}
			if(a�osProyecciones.get(j).isMes8()){
				totalAno+=gastosFijosAdministracion.get(i).getCostoMensual();
			}
			if(a�osProyecciones.get(j).isMes9()){
				totalAno+=gastosFijosAdministracion.get(i).getCostoMensual();
			}
			if(a�osProyecciones.get(j).isMes10()){
				totalAno+=gastosFijosAdministracion.get(i).getCostoMensual();
			}
			if(a�osProyecciones.get(j).isMes11()){
				totalAno+=gastosFijosAdministracion.get(i).getCostoMensual();
			}
			if(a�osProyecciones.get(j).isMes12()){
				totalAno+=gastosFijosAdministracion.get(i).getCostoMensual();
			}
			
			gastosAdministracion[j]=gastosAdministracion[j]+(totalAno);
			System.out.println("a�o"+(j+1)+" :"+gastosAdministracion[j]);
		
	
	}
	
		}
	}
	
	

	return gastosAdministracion;
}

public double[] getManoDeObra() {
	if(numAnos!=0){
		System.out.println("entro a ManodeObraaaaaaaaa");
	Query query=manager.createNamedQuery("Empleados.findByPlanId");
	query.setParameter("id", planId);
	
	List prods=query.getResultList();

	Vector<Empleados> empleados=new Vector<Empleados>();


	for (Object o : prods) {
		Empleados empleado = (Empleados) o;

		empleados.add(empleado);

	}
	for(int i=0;i<empleados.size();i++){
	query=manager.createNamedQuery("A�o_Proyecciones_Empleados.findByCostoId");
	query.setParameter("id",empleados.get(i).getEmpleado_id());
	
	List lista=query.getResultList();

	Vector<A�o_Proyecciones_Empleados> a�osProyecciones=new Vector<A�o_Proyecciones_Empleados>();


	for (Object o : lista) {
		A�o_Proyecciones_Empleados a�o = (A�o_Proyecciones_Empleados) o;
		
		a�osProyecciones.add(a�o);

	}
	for(int j=0;j<a�osProyecciones.size();j++){
		

			
			double totalAno=0;
			if(a�osProyecciones.get(j).isMes1()){
				totalAno+=empleados.get(i).getTotalMensual();
			}
			if(a�osProyecciones.get(j).isMes2()){
				totalAno+=empleados.get(i).getTotalMensual();
			}
			if(a�osProyecciones.get(j).isMes3()){
				totalAno+=empleados.get(i).getTotalMensual();
			}
			if(a�osProyecciones.get(j).isMes4()){
				totalAno+=empleados.get(i).getTotalMensual();
			}
			if(a�osProyecciones.get(j).isMes5()){
				totalAno+=empleados.get(i).getTotalMensual();
			}
			if(a�osProyecciones.get(j).isMes6()){
				totalAno+=empleados.get(i).getTotalMensual();
			}
			if(a�osProyecciones.get(j).isMes7()){
				totalAno+=empleados.get(i).getTotalMensual();
			}
			if(a�osProyecciones.get(j).isMes8()){
				totalAno+=empleados.get(i).getTotalMensual();
			}
			if(a�osProyecciones.get(j).isMes9()){
				totalAno+=empleados.get(i).getTotalMensual();
			}
			if(a�osProyecciones.get(j).isMes10()){
				totalAno+=empleados.get(i).getTotalMensual();
			}
			if(a�osProyecciones.get(j).isMes11()){
				totalAno+=empleados.get(i).getTotalMensual();
			}
			if(a�osProyecciones.get(j).isMes12()){
				totalAno+=empleados.get(i).getTotalMensual();
			}
			
			manoDeObra[j]=manoDeObra[j]+(totalAno);
			System.out.println("a�o"+(j+1)+" :"+manoDeObra[j]);
		
	
	}
	
		}
	}
	return manoDeObra;
}

public double getInvActivosFijos() {
	invActivosFijos=0;
	if(numAnos!=0){
		System.out.println("entro a inversion preoperativaaaaaaaaa");
	Query query=manager.createNamedQuery("Inversion_Activo_Fijos.findByPlanId");
	query.setParameter("id", planId);
	
	List prods=query.getResultList();

	Vector<Inversion_Activo_Fijos> iafijos=new Vector<Inversion_Activo_Fijos>();


	for (Object o : prods) {
		Inversion_Activo_Fijos af = (Inversion_Activo_Fijos) o;

		iafijos.add(af);

	}
	
	for(int i=0;i<iafijos.size();i++){
		invActivosFijos+=iafijos.get(i).getCantidad()*iafijos.get(i).getCostoUnitario();
	
		}
	}
	return invActivosFijos;
}

public double getMedios() {
	medios=0;
	if(numAnos!=0){
		System.out.println("entro a inversion preoperativaaaaaaaaa");
	Query query=manager.createNamedQuery("Medios_Comunicacion.findByPlanId");
	query.setParameter("id", planId);
	
	List prods=query.getResultList();

	Vector<Medios_Comunicacion> mComu=new Vector<Medios_Comunicacion>();


	for (Object o : prods) {
		Medios_Comunicacion med = (Medios_Comunicacion) o;

		mComu.add(med);

	}
	
	for(int i=0;i<mComu.size();i++){
		medios+=mComu.get(i).getValorInversion();
	
		}
	}
	return medios;
}

public double getImpuestos() {
	impuestos=0;
	if(numAnos!=0){
		System.out.println("entro a inversion preoperativaaaaaaaaa");
	Query query=manager.createNamedQuery("Impuestos.findByPlanId");
	query.setParameter("id", planId);
	
	List prods=query.getResultList();

	Vector<Impuestos> impuestosV=new Vector<Impuestos>();


	for (Object o : prods) {
		Impuestos imp = (Impuestos) o;

		impuestosV.add(imp);

	}
	
	for(int i=0;i<impuestosV.size();i++){
		impuestos+=impuestosV.get(i).getPorcentaje();
	
		}
	}
	return impuestos;
}

public double getInversionPreoperativa() {
	inversionPreoperativa=0;
	if(numAnos!=0){
		System.out.println("entro a inversion preoperativaaaaaaaaa");
	Query query=manager.createNamedQuery("Inversion_Preoperativa.findByPlanId");
	query.setParameter("id", planId);
	
	List prods=query.getResultList();

	Vector<Inversion_Preoperativa> inversionesPreoperativas=new Vector<Inversion_Preoperativa>();


	for (Object o : prods) {
		Inversion_Preoperativa invpre = (Inversion_Preoperativa) o;

		inversionesPreoperativas.add(invpre);

	}
	
	for(int i=0;i<inversionesPreoperativas.size();i++){
			inversionPreoperativa+=inversionesPreoperativas.get(i).getValorInversion();
	
		}
	}
	
	


	return inversionPreoperativa;
}

public int getPlanId() {
	return planId;
}


  
  
  
  
}
