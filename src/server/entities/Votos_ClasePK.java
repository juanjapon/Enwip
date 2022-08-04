package server.entities;

public class Votos_ClasePK implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private int usuario_id;
	private int clase_id;
	
	public Votos_ClasePK(){}
	public Votos_ClasePK(int usuario,int clase){
		super();
		this.usuario_id=usuario;
		this.clase_id=clase;
	}
	public int getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
	public int getClase_id() {
		return clase_id;
	}
	public void setClase_id(int clase_id) {
		this.clase_id = clase_id;
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + clase_id;
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
		Votos_ClasePK other = (Votos_ClasePK) obj;
		if (clase_id != other.clase_id)
			return false;
		if (usuario_id != other.usuario_id)
			return false;
		return true;
	}
	
	

}
