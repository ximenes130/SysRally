package modelo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "conversa")
public class Conversa implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ConversaID conversaID;
	
	@Column(name = "timestampAceite", nullable = true)
	private Timestamp timestampAceite;
	
	// @OneToMany(fetch = FetchType.EAGER)
	// @Fetch(FetchMode.JOIN)
	// @MapsId("conversaID")
	// @JoinColumns({
	// @JoinColumn(name = "idPessoa1", referencedColumnName = "idPessoa1"),
	// @JoinColumn(name = "idPessoa2", referencedColumnName = "idPessoa2"),
	// @JoinColumn(name = "timestampConversa", referencedColumnName = "timestampConversa") })
	// private List<Mensagem> mensagens;
	
	public Conversa() {
		super();
		this.conversaID = new ConversaID();
		// this.mensagens = new ArrayList<Mensagem>();
	}

	public ConversaID getConversaPK() {
		return conversaID;
	}

	public void setConversaPK(ConversaID conversaPK) {
		this.conversaID = conversaPK;
	}

	public Timestamp getTimestampAceite() {
		return timestampAceite;
	}

	public void setTimestampAceite(Timestamp timestampAceite) {
		this.timestampAceite = timestampAceite;
	}

	// public List<Mensagem> getMensagens() {
	// return mensagens;
	// }

	// public void setMensagens(List<Mensagem> mensagens) {
	// this.mensagens = mensagens;
	// }

	@Override
	public String toString() {
		return "Conversa [conversaPK=" + conversaID + ", timestampAceite="
				+ timestampAceite + "]";
	}

}
