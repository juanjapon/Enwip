package server.entities;

public class A�o_proyecciones_ventasPK implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private int a�oId;
	private int productosId;
	public A�o_proyecciones_ventasPK(){}
	public A�o_proyecciones_ventasPK(int a�o,int tipo){
		super();
		this.a�oId=a�o;
		this.productosId=tipo;
	}
	public int getA�oId() {
		return a�oId;
	}
	public void setA�oId(int a�oId) {
		this.a�oId = a�oId;
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
		result = prime * result + a�oId;
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
		A�o_proyecciones_ventasPK other = (A�o_proyecciones_ventasPK) obj;
		if (a�oId != other.a�oId)
			return false;
		if (productosId != other.productosId)
			return false;
		return true;
	}
	

}
