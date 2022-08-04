package server.entities;

public class A�o_Proyecciones_EmpleadosPK implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private int a�oId;
	private int empleadoId;
	public A�o_Proyecciones_EmpleadosPK(){}
	public A�o_Proyecciones_EmpleadosPK(int a�o,int tipo){
		super();
		this.a�oId=a�o;
		this.empleadoId=tipo;
	}
	public int getA�oId() {
		return a�oId;
	}
	public void setA�oId(int a�oId) {
		this.a�oId = a�oId;
	}
	public int getEmpleadoId() {
		return empleadoId;
	}
	public void setEmpleadoId(int empleadoId) {
		this.empleadoId = empleadoId;
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + a�oId;
		result = prime * result + empleadoId;
		return result;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		A�o_Proyecciones_EmpleadosPK other = (A�o_Proyecciones_EmpleadosPK) obj;
		if (a�oId != other.a�oId)
			return false;
		if (empleadoId != other.empleadoId)
			return false;
		return true;
	}
	

}
