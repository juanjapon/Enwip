package server.entities;

public class ColaboradoresPK implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private int idea_id;
	private int principal_id;
	private int colaborador_id;
	
	public ColaboradoresPK(){}
	public ColaboradoresPK(int idea,int usuario,int colaborador){
		super();
		this.idea_id=idea;
		this.principal_id=usuario;
		this.colaborador_id=colaborador;
	}
	
	
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + colaborador_id;
		result = prime * result + idea_id;
		result = prime * result + principal_id;
		return result;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ColaboradoresPK other = (ColaboradoresPK) obj;
		if (colaborador_id != other.colaborador_id)
			return false;
		if (idea_id != other.idea_id)
			return false;
		if (principal_id != other.principal_id)
			return false;
		return true;
	}
	
	public int getIdea_id() {
		return idea_id;
	}
	public void setIdea_id(int idea_id) {
		this.idea_id = idea_id;
	}
	public int getPrincipal_id() {
		return principal_id;
	}
	public void setPrincipal_id(int principal_id) {
		this.principal_id = principal_id;
	}
	public int getColaborador_id() {
		return colaborador_id;
	}
	public void setColaborador_id(int colaborador_id) {
		this.colaborador_id = colaborador_id;
	}
	


}
