package server.entities;

public class Cuaderno_ApuntesPK implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private int cuaderno_id;
	private int usuario_id;
	
	public Cuaderno_ApuntesPK(){}
	public Cuaderno_ApuntesPK(int cuaderno,int usuario){
		super();
		this.cuaderno_id=cuaderno;
		this.usuario_id=usuario;
	}
	public int getCuaderno_id() {
		return cuaderno_id;
	}
	public void setCuaderno_id(int cuaderno_id) {
		this.cuaderno_id = cuaderno_id;
	}
	public int getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cuaderno_id;
		result = prime * result + usuario_id;
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
		Cuaderno_ApuntesPK other = (Cuaderno_ApuntesPK) obj;
		if (cuaderno_id != other.cuaderno_id)
			return false;
		if (usuario_id != other.usuario_id)
			return false;
		return true;
	}
	

}
