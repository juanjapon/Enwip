package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="IDEA_DE_NEGOCIO_INVERSIONISTAS")
public class Idea_De_Negocio_Inversionistas implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="IDEA_DE_NEGOCIOIDEA_ID")
	private int idea_id;
	@Column(name="INVERSIONISTASINVERSIONISTA_ID")
	private int inversionista_id;
	
	public int getIdea_id() {
		return idea_id;
	}
	public void setIdea_id(int idea_id) {
		this.idea_id = idea_id;
	}
	public int getInversionista_id() {
		return inversionista_id;
	}
	public void setInversionista_id(int inversionista_id) {
		this.inversionista_id = inversionista_id;
	}
	
	

}
