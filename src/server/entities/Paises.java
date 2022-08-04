package server.entities;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries( 
		{ @NamedQuery(name = "Paises.findAll", query = "SELECT p FROM Paises p"), 
		  @NamedQuery(name="Paises.findByNombre",query="SELECT p FROM Paises p WHERE p.nombre= :nombre"),
		  @NamedQuery(name="Paises.findById",query="SELECT p FROM Paises p WHERE p.fips= :id")}	

)

@Table(name="PAISES")
public class Paises implements Serializable{
	private static final long serialVersionUID=1L;
	
	@Id
	@Column(name="CC_FIPS")
	private String fips;
	@Column(name="CC_ISO")
	private String iso;
	@Column(name="TLD")
	private String tld;
	@Column(name="NOMBRE")
	private String nombre;
	
	public String getFips() {
		return fips;
	}
	public void setFips(String fips) {
		this.fips = fips;
	}
	public String getIso() {
		return iso;
	}
	public void setIso(String iso) {
		this.iso = iso;
	}
	public String getTld() {
		return tld;
	}
	public void setTld(String tld) {
		this.tld = tld;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

}
