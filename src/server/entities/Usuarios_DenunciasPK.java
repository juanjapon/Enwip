package server.entities;

public class Usuarios_DenunciasPK implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private int usuario_id;
	private int denuncia_id;
	
	public Usuarios_DenunciasPK(){}
	public Usuarios_DenunciasPK(int usuario,int respuesta){
		super();
		
		this.usuario_id=usuario;
		this.denuncia_id=respuesta;
	}
	public int getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
	public int getRespuesta_id() {
		return denuncia_id;
	}
	public void setRespuesta_id(int respuesta_id) {
		this.denuncia_id = respuesta_id;
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + denuncia_id;
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
		Usuarios_DenunciasPK other = (Usuarios_DenunciasPK) obj;
		if (denuncia_id != other.denuncia_id)
			return false;
		if (usuario_id != other.usuario_id)
			return false;
		return true;
	}
	
	

}
