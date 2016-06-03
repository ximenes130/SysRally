package modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "mensagem")
public class Mensagem implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId	
	private MensagemID mensagemPK;

	@Column(name = "textoMensagem", nullable = false)
	private String textoMensagem;

	public Mensagem() {
		super();
		this.mensagemPK = new MensagemID();
	}

	protected void finalize() throws Throwable {
		this.mensagemPK = null;
		this.textoMensagem= null; 
	}

	public MensagemID getMensagemPK() {
		return mensagemPK;
	}

	public void setMensagemPK(MensagemID mensagemPK) {
		this.mensagemPK = mensagemPK;
	}

	public String getTextoMensagem() {
		return textoMensagem;
	}

	public void setTextoMensagem(String textoMensagem) {
		this.textoMensagem = textoMensagem;
	}

	@Override
	public String toString() {
		return "Mensagem [mensagemPK=" + mensagemPK + ", textoMensagem="
				+ textoMensagem + "]";
	}

}
