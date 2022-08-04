package server.entities;

public class A�o_Proyecciones_Gastos_VentasPK implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private int a�oId;
	private int gastoVentaId;
	public A�o_Proyecciones_Gastos_VentasPK(){}
	public A�o_Proyecciones_Gastos_VentasPK(int a�o,int tipo){
		super();
		this.a�oId=a�o;
		this.gastoVentaId=tipo;
	}
	public int getA�oId() {
		return a�oId;
	}
	public void setA�oId(int a�oId) {
		this.a�oId = a�oId;
	}
	public int getGastoVentaId() {
		return gastoVentaId;
	}
	public void setGastoVentaId(int gastoVentaId) {
		this.gastoVentaId = gastoVentaId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + a�oId;
		result = prime * result + gastoVentaId;
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
		A�o_Proyecciones_Gastos_VentasPK other = (A�o_Proyecciones_Gastos_VentasPK) obj;
		if (a�oId != other.a�oId)
			return false;
		if (gastoVentaId != other.gastoVentaId)
			return false;
		return true;
	}
	

}
