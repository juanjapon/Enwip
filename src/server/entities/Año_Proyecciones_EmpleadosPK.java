package server.entities;

public class Año_Proyecciones_EmpleadosPK implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private int añoId;
	private int empleadoId;
	public Año_Proyecciones_EmpleadosPK(){}
	public Año_Proyecciones_EmpleadosPK(int año,int tipo){
		super();
		this.añoId=año;
		this.empleadoId=tipo;
	}
	public int getAñoId() {
		return añoId;
	}
	public void setAñoId(int añoId) {
		this.añoId = añoId;
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
		result = prime * result + añoId;
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
		Año_Proyecciones_EmpleadosPK other = (Año_Proyecciones_EmpleadosPK) obj;
		if (añoId != other.añoId)
			return false;
		if (empleadoId != other.empleadoId)
			return false;
		return true;
	}
	

}
