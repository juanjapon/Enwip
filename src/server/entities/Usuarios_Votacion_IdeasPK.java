package server.entities;

public class Usuarios_Votacion_IdeasPK implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private int usuario_id;
	private int idea_id;
	
	public Usuarios_Votacion_IdeasPK(){}
	public Usuarios_Votacion_IdeasPK(int usuario,int idea){
		super();
		
		this.usuario_id=usuario;
		this.idea_id=idea;
	}
	public int getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
	public int getIdea_id() {
		return idea_id;
	}
	public void setIdea_id(int idea_id) {
		this.idea_id = idea_id;
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idea_id;
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
		Usuarios_Votacion_IdeasPK other = (Usuarios_Votacion_IdeasPK) obj;
		if (idea_id != other.idea_id)
			return false;
		if (usuario_id != other.usuario_id)
			return false;
		return true;
	}
	

}
