package server.entities;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@NamedQueries( 
		{
		  @NamedQuery(name="Año_proyecciones_ventas.findByProductoId",query="SELECT d FROM Año_proyecciones_ventas d WHERE d.productosId= :id"),
		  @NamedQuery(name = "Año_proyecciones_ventas.findAll", query = "SELECT n FROM Año_proyecciones_ventas n")
		}	
		
)
@IdClass(Año_proyecciones_ventasPK.class)
@Table(name="AÑO_PROYECCIONES_VENTAS")
public class Año_proyecciones_ventas implements Serializable {
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="AÑO_ID")
	private int añoId;
	@Id
	@Column(name="PRODUCTOSPRODUCTO_ID")
	private int productosId;
	@Column(name="MES_1")
	private int mes1;
	@Column(name="MES_2")
	private int mes2;
	@Column(name="MES_3")
	private int mes3;
	@Column(name="MES_4")
	private int mes4;
	@Column(name="MES_5")
	private int mes5;
	@Column(name="MES_6")
	private int mes6;
	@Column(name="MES_7")
	private int mes7;
	@Column(name="MES_8")
	private int mes8;
	@Column(name="MES_9")
	private int mes9;
	@Column(name="MES_10")
	private int mes10;
	@Column(name="MES_11")
	private int mes11;
	@Column(name="MES_12")
	private int mes12;
	@Column(name="PORCENTAJE_INCREMENTO")
	private int porcentajeIncremento;
	
	
	public int getPorcentajeIncremento() {
		return porcentajeIncremento;
	}
	public void setPorcentajeIncremento(int porcentajeIncremento) {
		this.porcentajeIncremento = porcentajeIncremento;
	}
	
	public int getAñoId() {
		return añoId;
	}
	public void setAñoId(int añoId) {
		this.añoId = añoId;
	}
	public int getProductosId() {
		return productosId;
	}
	public void setProductosId(int productosId) {
		this.productosId = productosId;
	}
	public int getMes1() {
		return mes1;
	}
	public void setMes1(int mes1) {
		this.mes1 = mes1;
	}
	public int getMes2() {
		return mes2;
	}
	public void setMes2(int mes2) {
		this.mes2 = mes2;
	}
	public int getMes3() {
		return mes3;
	}
	public void setMes3(int mes3) {
		this.mes3 = mes3;
	}
	public int getMes4() {
		return mes4;
	}
	public void setMes4(int mes4) {
		this.mes4 = mes4;
	}
	public int getMes5() {
		return mes5;
	}
	public void setMes5(int mes5) {
		this.mes5 = mes5;
	}
	public int getMes6() {
		return mes6;
	}
	public void setMes6(int mes6) {
		this.mes6 = mes6;
	}
	public int getMes7() {
		return mes7;
	}
	public void setMes7(int mes7) {
		this.mes7 = mes7;
	}
	public int getMes8() {
		return mes8;
	}
	public void setMes8(int mes8) {
		this.mes8 = mes8;
	}
	public int getMes9() {
		return mes9;
	}
	public void setMes9(int mes9) {
		this.mes9 = mes9;
	}
	public int getMes10() {
		return mes10;
	}
	public void setMes10(int mes10) {
		this.mes10 = mes10;
	}
	public int getMes11() {
		return mes11;
	}
	public void setMes11(int mes11) {
		this.mes11 = mes11;
	}
	public int getMes12() {
		return mes12;
	}
	public void setMes12(int mes12) {
		this.mes12 = mes12;
	}

}
