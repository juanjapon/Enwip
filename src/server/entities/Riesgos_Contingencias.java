package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries( 
		{
		  @NamedQuery(name="Riesgos_Contingencias.findByPlanId",query="SELECT d FROM Riesgos_Contingencias d WHERE d.plan_id= :id"),
		  @NamedQuery(name = "Riesgos_Contingencias.findAll", query = "SELECT n FROM Riesgos_Contingencias n")
		}	
		
)
@Table(name="RIESGOS_CONTINGENCIAS")
public class Riesgos_Contingencias implements Serializable{
	
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="RIESGO_CONTINGENCIA_ID")
	private int riesgoContingencia_id;
	@Column(name="PLANES_DE_NEGOCIOSPLAN_ID")
	private int plan_id;
	@Column(name="RIESGO")
	private String riesgo;
	@Column(name="CONTINGENCIA")
	private String contingencia;
	
	public int getRiesgoContingencia_id() {
		return riesgoContingencia_id;
	}
	public void setRiesgoContingencia_id(int riesgoContingencia_id) {
		this.riesgoContingencia_id = riesgoContingencia_id;
	}
	public int getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}
	public String getRiesgo() {
		return riesgo;
	}
	public void setRiesgo(String riesgo) {
		this.riesgo = riesgo;
	}
	public String getContingencia() {
		return contingencia;
	}
	public void setContingencia(String contingencia) {
		this.contingencia = contingencia;
	}
	

}
