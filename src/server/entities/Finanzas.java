package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="FINANZAS")
public class Finanzas implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="FINANZAS_ID")
	private int finanzas_id;
	@Column(name="PLANES_DE_NEGOCIOSPLAN_ID")
	private int planNegocio_id;
	@Column(name="DIFERIR_RECUPERACION")
	private int diferirRecuperacion;
	@Column(name="TAZA")
	private int taza;
	@Column(name="PLAZO")
	private int plazo;
	@Column(name="PERIODO_GRACIA")
	private int periodoGracia;
	@Column(name="FINANZAS_TERMINADA")
	private boolean finanzasTerminada;
	
	public int getFinanzas_id() {
		return finanzas_id;
	}
	public void setFinanzas_id(int finanzas_id) {
		this.finanzas_id = finanzas_id;
	}
	public int getPlanNegocio_id() {
		return planNegocio_id;
	}
	public void setPlanNegocio_id(int planNegocio_id) {
		this.planNegocio_id = planNegocio_id;
	}
	public int getDiferirRecuperacion() {
		return diferirRecuperacion;
	}
	public void setDiferirRecuperacion(int diferirRecuperacion) {
		this.diferirRecuperacion = diferirRecuperacion;
	}
	public int getTaza() {
		return taza;
	}
	public void setTaza(int taza) {
		this.taza = taza;
	}
	public int getPlazo() {
		return plazo;
	}
	public void setPlazo(int plazo) {
		this.plazo = plazo;
	}
	public int getPeriodoGracia() {
		return periodoGracia;
	}
	public void setPeriodoGracia(int periodoGracia) {
		this.periodoGracia = periodoGracia;
	}
	public boolean isFinanzasTerminada() {
		return finanzasTerminada;
	}
	public void setFinanzasTerminada(boolean finanzasTerminada) {
		this.finanzasTerminada = finanzasTerminada;
	}
	

}
