package modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "pessoafisica")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoPessoa", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("0")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idPessoa", nullable = false)
	private Integer idPessoa;

	@Column(name = "nome", length = 50, nullable = false)
	private String nome;

	@Column(name = "telefone", length = 14, nullable = false)
	private String telefone;

	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@ManyToOne(targetEntity = Endereco.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "idEndereco", nullable = false, insertable = true, updatable = true)
	@Fetch(FetchMode.JOIN)
	private Endereco endereco;

	@Temporal(TemporalType.DATE)
	@Column(name = "dataNascimento", nullable = false)
	private Date dataNascimento;

	@Column(name = "rg", length = 10, nullable = false)
	private String rg;

	@Column(name = "orgaoExpedicao", length = 5, nullable = false)
	private String orgaoExpedicao;

	@Column(name = "cpf", length = 11, nullable = false)
	private String cpf;

	public Pessoa() {
		super();
	}

	public Integer getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getOrgaoExpedicao() {
		return orgaoExpedicao;
	}

	public void setOrgaoExpedicao(String orgaoExpedicao) {
		this.orgaoExpedicao = orgaoExpedicao;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "Pessoa [idPessoa=" + idPessoa + ", nome=" + nome
				+ ", telefone=" + telefone + ", endereco="
				+ endereco.getLogradouro() + ", " + endereco.getComplemento()
				+ ", " + endereco.getBairro() + ", "
				+ endereco.getMunicipio().getNomeMunicipio() + "-"
				+ endereco.getMunicipio().getUfMunicipio()
				+ ", dataNascimento="
				+ new SimpleDateFormat("dd/MM/yyyy").format(dataNascimento)
				+ ", rg=" + rg + ", orgaoExpedicao=" + orgaoExpedicao
				+ ", cpf=" + cpf + "]";
	}

}