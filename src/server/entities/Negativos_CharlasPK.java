package server.entities;

public class Negativos_CharlasPK implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private int usuario_id;
	private int curso_id;
	
	public Negativos_CharlasPK(){}
	public Negativos_CharlasPK(int usuario,int clase){
		super();
		this.usuario_id=usuario;
		this.curso_id=clase;
	}
	public int getUsuario_id() {
		return usuario_id;
	}
	public int getCurso_id() {
		return curso_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
	public void setCurso_id(int curso_id) {
		this.curso_id = curso_id;
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + curso_id;
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
		Negativos_CharlasPK other = (Negativos_CharlasPK) obj;
		if (curso_id != other.curso_id)
			return false;
		if (usuario_id != other.usuario_id)
			return false;
		return true;
	}
	

}
