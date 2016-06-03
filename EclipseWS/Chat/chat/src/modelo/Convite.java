package modelo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@SuppressWarnings("serial")
@Entity
@Table(name = "convite")
public class Convite implements Serializable {

	@EmbeddedId
	private ConviteID conviteID;

	@ManyToOne
	@JoinColumn(name = "idPessoa1", insertable = false, updatable = false)
	@Fetch(FetchMode.JOIN)
	private Pessoa pessoa1;

	@ManyToOne
	@JoinColumn(name = "idPessoa2", insertable = false, updatable = false)
	@Fetch(FetchMode.JOIN)
	private Pessoa pessoa2;

	@Column(name = "timestampRecebimento", nullable = true)
	private Timestamp timestampRecebimento;

	@Column(name = "aceiteConvite", nullable = true)
	private boolean aceiteConvite;

	public Convite() {
		super();
		this.conviteID = new ConviteID();
		this.aceiteConvite = false;
	}

	public ConviteID getConviteID() {
		return conviteID;
	}

	public void setConviteID(ConviteID conviteID) {
		this.conviteID = conviteID;
	}

	public Pessoa getPessoa1() {
		return pessoa1;
	}

	public void setPessoa1(Pessoa pessoa1) {
		this.pessoa1 = pessoa1;
	}

	public Pessoa getPessoa2() {
		return pessoa2;
	}

	public void setPessoa2(Pessoa pessoa2) {
		this.pessoa2 = pessoa2;
	}

	public Timestamp getTimestampRecebimento() {
		return timestampRecebimento;
	}

	public void setTimestampRecebimento(Timestamp timestampRecebimento) {
		this.timestampRecebimento = timestampRecebimento;
	}

	public boolean isAceiteConvite() {
		return aceiteConvite;
	}

	public void setAceiteConvite(boolean aceiteConvite) {
		this.aceiteConvite = aceiteConvite;
	}

	@Override
	public String toString() {
		return "Convite [conviteID=" + conviteID + ", timestampRecebimento="
				+ timestampRecebimento + ", aceiteConvite=" + aceiteConvite
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (aceiteConvite ? 1231 : 1237);
		result = prime * result
				+ ((conviteID == null) ? 0 : conviteID.hashCode());
		result = prime
				* result
				+ ((timestampRecebimento == null) ? 0 : timestampRecebimento
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Convite other = (Convite) obj;
		if (aceiteConvite != other.aceiteConvite)
			return false;
		if (conviteID == null) {
			if (other.conviteID != null)
				return false;
		} else if (!conviteID.equals(other.conviteID))
			return false;
		if (timestampRecebimento == null) {
			if (other.timestampRecebimento != null)
				return false;
		} else if (!timestampRecebimento.equals(other.timestampRecebimento))
			return false;
		return true;
	}

}
