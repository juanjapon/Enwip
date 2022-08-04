package server.entities;

public class Año_proyecciones_ventasPK implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private int añoId;
	private int productosId;
	public Año_proyecciones_ventasPK(){}
	public Año_proyecciones_ventasPK(int año,int tipo){
		super();
		this.añoId=año;
		this.productosId=tipo;
	}
	public int getAñoId() {
		return añoId;
	}
	public void setAñoId(int añoId) {
		this.añoId = añoId;
	}
	public int getProductosId() {
		return productosId;
	}
	public void setProductosId(int productosId) {
		this.productosId = productosId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + añoId;
		result = prime * result + productosId;
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
		Año_proyecciones_ventasPK other = (Año_proyecciones_ventasPK) obj;
		if (añoId != other.añoId)
			return false;
		if (productosId != other.productosId)
			return false;
		return true;
	}
	

}
