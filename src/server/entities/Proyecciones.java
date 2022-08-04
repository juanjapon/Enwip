package server.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries( 
		{
		  @NamedQuery(name="Proyecciones.findByPlanId",query="SELECT d FROM Proyecciones d WHERE d.finanzas_id= :id"),
		  @NamedQuery(name = "Proyecciones.findAll", query = "SELECT n FROM Proyecciones n")
		}	
		
)
@Table(name="PROYECCIONES")
public class Proyecciones implements Serializable{
	private static final long serialVersionUID=1L;
	
	@Id
	@Column(name="PROYECCION_ID")
	private int proyeccion_id;
	@Column(name="MES_INICIO")
	private String mesInicio;
	@Column(name="AÑO_INICIO")
	private int añoInicio;
	@Column(name="NUMERO_AÑOS")
	private int numeroAños;
	@Column(name="FINANZASFINANZAS_ID")
	private int finanzas_id;
	
	public int getProyeccion_id() {
		return proyeccion_id;
	}
	public void setProyeccion_id(int proyeccion_id) {
		this.proyeccion_id = proyeccion_id;
	}
	public String getMesInicio() {
		return mesInicio;
	}
	public void setMesInicio(String mesInicio) {
		this.mesInicio = mesInicio;
	}
	public int getAñoInicio() {
		return añoInicio;
	}
	public void setAñoInicio(int añoInicio) {
		this.añoInicio = añoInicio;
	}
	public int getNumeroAños() {
		return numeroAños;
	}
	public void setNumeroAños(int numeroAños) {
		this.numeroAños = numeroAños;
	}
	public int getFinanzas_id() {
		return finanzas_id;
	}
	public void setFinanzas_id(int finanzas_id) {
		this.finanzas_id = finanzas_id;
	}

}
