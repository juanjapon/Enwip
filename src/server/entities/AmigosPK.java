package server.entities;

public class AmigosPK implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private int amigo_id;
	private int usuario_id;
	
	public AmigosPK(){}
	public AmigosPK(int amigo,int usuario){
		super();
		this.amigo_id=amigo;
		this.usuario_id=usuario;
	}
	public int getAmigo_id() {
		return amigo_id;
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amigo_id;
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
		AmigosPK other = (AmigosPK) obj;
		if (amigo_id != other.amigo_id)
			return false;
		if (usuario_id != other.usuario_id)
			return false;
		return true;
	}
	public void setAmigo_id(int amigo_id) {
		this.amigo_id = amigo_id;
	}
	public int getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}

}
