package server.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries( 
		{
		  @NamedQuery(name="Colaboradores.findColaboradores",query="SELECT d FROM Colaboradores d WHERE d.principal_id= :id"),
		  @NamedQuery(name = "Colaboradores.findColaborados", query = "SELECT n FROM Colaboradores n WHERE n.colaborador_id= :id ")
		}	
		
)
@IdClass(ColaboradoresPK.class)
@Table(name="COLABORADORES")
public class Colaboradores implements Serializable{
	private static final long serialVersionUID=1L;
	
	@Id
	@Column(name="IDEA_ID")
	private int idea_id;
	@Id
	@Column(name="USUARIO_PRINCIPAL_ID")
	private int principal_id;
	@Id
	@Column(name="USUARIOS_COLABORADOR_ID")
	private int colaborador_id;
	@Column(name="TIPO_COLABORADORTIPO_ID")
	private int tipoColaborador_id;
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
	public int getTipoColaborador_id() {
		return tipoColaborador_id;
	}
	public void setTipoColaborador_id(int tipoColaborador_id) {
		this.tipoColaborador_id = tipoColaborador_id;
	}
	public int getIdea_id() {
		return idea_id;
	}
	public void setIdea_id(int idea_id) {
		this.idea_id = idea_id;
	}
	
	
	

}
