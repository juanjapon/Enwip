package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries( 
		{
		  @NamedQuery(name="Inversion_Preoperativa.findByPlanId",query="SELECT d FROM Inversion_Preoperativa d WHERE d.finanzas_id= :id"),
		  @NamedQuery(name = "Inversion_Preoperativa.findAll", query = "SELECT n FROM Inversion_Preoperativa n")
		}	
		
)
@Table(name="INVERSION_PREOPERATIVA")
public class Inversion_Preoperativa implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="INVERSION_PREOPERATIVA_ID")
	private int inversionPreoperativa_id;
	@Column(name="FINANZASFINANZAS_ID")
	private int finanzas_id;
	@Column(name="TIPO_PREOPERATIVATIPO_ID")
	private int tipoPreoperativa_id;
	@Column(name="VALOR_INVERSION")
	private int valorInversion;
	
	public int getInversionPreoperativa_id() {
		return inversionPreoperativa_id;
	}
	public void setInversionPreoperativa_id(int inversionPreoperativa_id) {
		this.inversionPreoperativa_id = inversionPreoperativa_id;
	}
	public int getFinanzas_id() {
		return finanzas_id;
	}
	public void setFinanzas_id(int finanzas_id) {
		this.finanzas_id = finanzas_id;
	}
	public int getTipoPreoperativa_id() {
		return tipoPreoperativa_id;
	}
	public void setTipoPreoperativa_id(int tipoPreoperativa_id) {
		this.tipoPreoperativa_id = tipoPreoperativa_id;
	}
	public int getValorInversion() {
		return valorInversion;
	}
	public void setValorInversion(int valorInversion) {
		this.valorInversion = valorInversion;
	}
	
	

}
