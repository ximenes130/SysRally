package modelo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class ConviteID implements Serializable {

	@Column(name = "idPessoa1", nullable = false, insertable = false, updatable = false)
	private int idPessoa1;

	@Column(name = "idPessoa2", nullable = false, insertable = false, updatable = false)
	private int idPessoa2;

	@Column(name = "timestampConvite", nullable = false, insertable = false, updatable = false)
	private Timestamp timestampConvite;

	public ConviteID() {
		super();
	}

	public int getIdPessoa1() {
		return idPessoa1;
	}

	public void setIdPessoa1(int idPessoa1) {
		this.idPessoa1 = idPessoa1;
	}

	public int getIdPessoa2() {
		return idPessoa2;
	}

	public void setIdPessoa2(int idPessoa2) {
		this.idPessoa2 = idPessoa2;
	}

	public Timestamp getTimestampConvite() {
		return timestampConvite;
	}

	public void setTimestampConvite(Timestamp timestampConvite) {
		this.timestampConvite = timestampConvite;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPessoa1;
		result = prime * result + idPessoa2;
		result = prime
				* result
				+ ((timestampConvite == null) ? 0 : timestampConvite.hashCode());
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
		ConviteID other = (ConviteID) obj;
		if (idPessoa1 != other.idPessoa1)
			return false;
		if (idPessoa2 != other.idPessoa2)
			return false;
		if (timestampConvite == null) {
			if (other.timestampConvite != null)
				return false;
		} else if (!timestampConvite.equals(other.timestampConvite))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ConviteID [idPessoa1=" + idPessoa1 + ", idPessoa2=" + idPessoa2
				+ ", timestampConvite=" + timestampConvite + "]";
	}

}
