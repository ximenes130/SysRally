package modelo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Embeddable
public class ConversaID implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "idPessoa1", insertable = true, updatable = true)
	@Fetch(FetchMode.JOIN)
	private Pessoa pessoa1;

	@ManyToOne
	@JoinColumn(name = "idPessoa2", insertable = true, updatable = true)
	@Fetch(FetchMode.JOIN)
	private Pessoa pessoa2;

	@Column(name = "timestampConversa", nullable = false)
	private Timestamp timestampConversa;

	public ConversaID() {
		super();
		this.pessoa1 = new Pessoa();
		this.pessoa2 = new Pessoa();
	}

	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof ConversaID)) {
			return false;
		}
		ConversaID other = (ConversaID) o;
		return true
				&& this.pessoa1.getIdPessoa() == other.pessoa1.getIdPessoa()
				&& this.pessoa2.getIdPessoa() == other.pessoa2.getIdPessoa()
				&& (this.getTimestampConversa() == null ? other
						.getTimestampConversa() == null : this
						.getTimestampConversa().equals(
								other.getTimestampConversa()));
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.pessoa1.getIdPessoa();
		result = prime * result + this.pessoa2.getIdPessoa();
		result = prime
				* result
				+ (this.getTimestampConversa() == null ? 0 : this
						.getTimestampConversa().hashCode());
		return result;
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

	public Timestamp getTimestampConversa() {
		return timestampConversa;
	}

	public void setTimestampConversa(Timestamp timestampConversa) {
		this.timestampConversa = timestampConversa;
	}

	@Override
	public String toString() {
		return "ConversaPK [pessoa1=" + pessoa1 + ", pessoa2=" + pessoa2
				+ ", timestampConversa=" + timestampConversa + "]";
	}

}
