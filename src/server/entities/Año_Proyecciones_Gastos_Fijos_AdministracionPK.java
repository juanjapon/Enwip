package server.entities;

public class A�o_Proyecciones_Gastos_Fijos_AdministracionPK implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private int a�oId;
	private int gastoFijoId;
	public A�o_Proyecciones_Gastos_Fijos_AdministracionPK(){}
	public A�o_Proyecciones_Gastos_Fijos_AdministracionPK(int a�o,int tipo){
		super();
		this.a�oId=a�o;
		this.gastoFijoId=tipo;
	}
	public int getA�oId() {
		return a�oId;
	}
	public void setA�oId(int a�oId) {
		this.a�oId = a�oId;
	}
	public int getGastoFijoId() {
		return gastoFijoId;
	}
	public void setGastoFijoId(int gastoFijoId) {
		this.gastoFijoId = gastoFijoId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + a�oId;
		result = prime * result + gastoFijoId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		A�o_Proyecciones_Gastos_Fijos_AdministracionPK other = (A�o_Proyecciones_Gastos_Fijos_AdministracionPK) obj;
		if (a�oId != other.a�oId)
			return false;
		if (gastoFijoId != other.gastoFijoId)
			return false;
		return true;
	}
	

}
