package server.entities;

public class ParaPK implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private int mensaje_id;
	private int usuario_id;
	
	
	
	public ParaPK(){}
	public ParaPK(int mensaje,int usuario){
		super();
		this.mensaje_id=mensaje;
		this.usuario_id=usuario;
	}
	public int getMensaje_id() {
		return mensaje_id;
	}
	public void setMensaje_id(int mensaje_id) {
		this.mensaje_id = mensaje_id;
	}
	public int getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mensaje_id;
		result = prime * result + usuario_id;
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParaPK other = (ParaPK) obj;
		if (mensaje_id != other.mensaje_id)
			return false;
		if (usuario_id != other.usuario_id)
			return false;
		return true;
	}
}
