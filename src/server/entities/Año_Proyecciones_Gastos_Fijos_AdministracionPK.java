package server.entities;

public class Año_Proyecciones_Gastos_Fijos_AdministracionPK implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private int añoId;
	private int gastoFijoId;
	public Año_Proyecciones_Gastos_Fijos_AdministracionPK(){}
	public Año_Proyecciones_Gastos_Fijos_AdministracionPK(int año,int tipo){
		super();
		this.añoId=año;
		this.gastoFijoId=tipo;
	}
	public int getAñoId() {
		return añoId;
	}
	public void setAñoId(int añoId) {
		this.añoId = añoId;
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
		result = prime * result + añoId;
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
		Año_Proyecciones_Gastos_Fijos_AdministracionPK other = (Año_Proyecciones_Gastos_Fijos_AdministracionPK) obj;
		if (añoId != other.añoId)
			return false;
		if (gastoFijoId != other.gastoFijoId)
			return false;
		return true;
	}
	

}
