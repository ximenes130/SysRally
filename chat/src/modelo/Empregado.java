package modelo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@DiscriminatorValue("1")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Empregado extends Pessoa {
	private static final long serialVersionUID = 1L;

	@Column(name = "ctps", length = 10, nullable = false)
	private String ctps;

	@Column(name = "empresaContratante", length = 50, nullable = false)
	private String empresaContratante;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dataContratacao", nullable = false)
	private Calendar dataContratacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dataDemissao", nullable = true)
	private Calendar dataDemissao;

	public Empregado() {
		super();
	}

	public String getCtps() {
		return ctps;
	}

	public void setCtps(String ctps) {
		this.ctps = ctps;
	}

	public String getEmpresaContratante() {
		return empresaContratante;
	}

	public void setEmpresaContratante(String empresaContratante) {
		this.empresaContratante = empresaContratante;
	}

	public Calendar getDataContratacao() {
		return dataContratacao;
	}

	public void setDataContratacao(Calendar dataContratacao) {
		this.dataContratacao = dataContratacao;
	}

	public Calendar getDataDemissao() {
		return dataDemissao;
	}

	public void setDataDemissao(Calendar dataDemissao) {
		this.dataDemissao = dataDemissao;
	}

	@Override
	public String toString() {
		return "Empregado ["
				+ super.toString()
				+ " ctps="
				+ ctps
				+ ", empresaContratante="
				+ empresaContratante
				+ ", dataContratacao="
				+ new SimpleDateFormat("dd/MM/yyyy").format(dataContratacao.getTime())
				+ ", dataDemissao="
				+ new SimpleDateFormat("dd/MM/yyyy").format(dataDemissao.getTime()) + "]";
	}
}
