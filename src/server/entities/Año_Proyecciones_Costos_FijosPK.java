package server.entities;

public class A�o_Proyecciones_Costos_FijosPK implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private int a�oId;
	private int costosId;
	public A�o_Proyecciones_Costos_FijosPK(){}
	public A�o_Proyecciones_Costos_FijosPK(int a�o,int tipo){
		super();
		this.a�oId=a�o;
		this.costosId=tipo;
	}
	public int getA�oId() {
		return a�oId;
	}
	public void setA�oId(int a�oId) {
		this.a�oId = a�oId;
	}
	public int getCostosId() {
		return costosId;
	}
	public void setCostosId(int costosId) {
		this.costosId = costosId;
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + a�oId;
		result = prime * result + costosId;
		return result;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		A�o_Proyecciones_Costos_FijosPK other = (A�o_Proyecciones_Costos_FijosPK) obj;
		if (a�oId != other.a�oId)
			return false;
		if (costosId != other.costosId)
			return false;
		return true;
	}
	
	
}
