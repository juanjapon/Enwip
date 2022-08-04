package server.entities;

public class Año_Proyecciones_Gastos_VentasPK implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private int añoId;
	private int gastoVentaId;
	public Año_Proyecciones_Gastos_VentasPK(){}
	public Año_Proyecciones_Gastos_VentasPK(int año,int tipo){
		super();
		this.añoId=año;
		this.gastoVentaId=tipo;
	}
	public int getAñoId() {
		return añoId;
	}
	public void setAñoId(int añoId) {
		this.añoId = añoId;
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
		result = prime * result + añoId;
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
		Año_Proyecciones_Gastos_VentasPK other = (Año_Proyecciones_Gastos_VentasPK) obj;
		if (añoId != other.añoId)
			return false;
		if (gastoVentaId != other.gastoVentaId)
			return false;
		return true;
	}
	

}
