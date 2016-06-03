package modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Embeddable
public class MensagemID implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	@MapsId("conversaID")
	@JoinColumns({
			@JoinColumn(name = "idPessoa1", referencedColumnName = "idPessoa1"),
			@JoinColumn(name = "idPessoa2", referencedColumnName = "idPessoa2"),
			@JoinColumn(name = "timestampConversa", referencedColumnName = "timestampConversa")
			})
	private Conversa conversa;

	@Column(name = "seqMensagem", nullable = false)
	private Integer seqMensagem;

	

	
	
	
	public MensagemID() {
		this.conversa = new Conversa();
	}

	public Conversa getConversa() {
		return conversa;
	}

	public void setConversa(Conversa conversa) {
		this.conversa = conversa;
	}

	public Integer getSeqMensagem() {
		return seqMensagem;
	}

	public void setSeqMensagem(Integer seqMensagem) {
		this.seqMensagem = seqMensagem;
	}

	@Override
	public String toString() {
		return "MensagemPK [conversa=" + conversa + ", seqMensagem="
				+ seqMensagem + "]";
	}

	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof MensagemID)) {
			return false;
		}
		MensagemID other = (MensagemID) o;
		return true
				&& this.conversa.getConversaPK().equals(
						other.conversa.getConversaPK())
				&& (this.getSeqMensagem() == null ? other.getSeqMensagem() == null
						: this.getSeqMensagem().equals(other.getSeqMensagem()));
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.conversa.getConversaPK().hashCode();
		result = prime
				* result
				+ (this.getSeqMensagem() == null ? 0 : this.getSeqMensagem()
						.hashCode());
		return result;
	}
}
