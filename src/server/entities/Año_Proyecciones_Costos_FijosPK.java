package server.entities;

public class Año_Proyecciones_Costos_FijosPK implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private int añoId;
	private int costosId;
	public Año_Proyecciones_Costos_FijosPK(){}
	public Año_Proyecciones_Costos_FijosPK(int año,int tipo){
		super();
		this.añoId=año;
		this.costosId=tipo;
	}
	public int getAñoId() {
		return añoId;
	}
	public void setAñoId(int añoId) {
		this.añoId = añoId;
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
		result = prime * result + añoId;
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
		Año_Proyecciones_Costos_FijosPK other = (Año_Proyecciones_Costos_FijosPK) obj;
		if (añoId != other.añoId)
			return false;
		if (costosId != other.costosId)
			return false;
		return true;
	}
	
	
}
