package server.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="AREAS_DE_ESTUDIO_NOTICIAS")
public class Areas_De_Estudio_Noticias implements Serializable{
	private static final long serialVersionUID=1L;

	@Id
	@Column(name="AREAS_DE_ESTUDIOAREA_ESTUDIO_ID")
	private int areaEstudio_id;
	@Column(name="NOTICIASNOTICIA_ID")
	private int noticia_id;
	
	
	public int getAreaEstudio_id() {
		return areaEstudio_id;
	}
	public void setAreaEstudio_id(int areaEstudio_id) {
		this.areaEstudio_id = areaEstudio_id;
	}
	public int getNoticia_id() {
		return noticia_id;
	}
	public void setNoticia_id(int noticia_id) {
		this.noticia_id = noticia_id;
	}
	
	

	
	

}
