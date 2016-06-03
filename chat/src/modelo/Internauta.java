package modelo;

import java.io.Serializable;
import javax.persistence.*;



/**
 * The persistent class for the internauta database table.
 * 
 */
@Entity
@Table(name="internauta")
public class Internauta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int idInternauta;

	@Column(nullable=false, length=45)
	private String nomeInternauta;

	//bi-directional one-to-one association to Acesso
	@OneToOne(mappedBy="acesso")
	private Acesso acesso;

	public Internauta() {
	}

	public int getIdInternauta() {
		return this.idInternauta;
	}

	public void setIdInternauta(int idInternauta) {
		this.idInternauta = idInternauta;
	}

	public String getNomeInternauta() {
		return this.nomeInternauta;
	}

	public void setNomeInternauta(String nomeInternauta) {
		this.nomeInternauta = nomeInternauta;
	}

	public Acesso getAcesso() {
		return this.acesso;
	}

	public void setAcesso(Acesso acesso) {
		this.acesso = acesso;
	}

}