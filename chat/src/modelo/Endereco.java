package modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "endereco")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idEndereco", nullable = false)
	private Integer idEndereco;

	@Column(name = "logradouro", length = 50, nullable = false)
	private String logradouro;

	@Column(name = "complemento", length = 20, nullable = false)
	private String complemento;

	@Column(name = "bairro", length = 30, nullable = false)
	private String bairro;

	@Column(name = "cep", length = 8, nullable = false)
	private String cep;

	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@ManyToOne(targetEntity = Municipio.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "idMunicipio", nullable = false, insertable = true, updatable = true)
	@Fetch(FetchMode.JOIN)
	private Municipio municipio;
	
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@OneToMany(targetEntity = Pessoa.class, mappedBy = "endereco")
	private List<Pessoa> pessoas;

	public Endereco() {
		super();
		this.municipio = new Municipio();
	}

	public Integer getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Integer idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	@Override
	public String toString() {
		return "Endereco [idEndereco=" + idEndereco + ", logradouro="
				+ logradouro + ", complemento=" + complemento + ", bairro="
				+ bairro + ", cep=" + cep + ", municipio="
				+ municipio.getNomeMunicipio() + "-"
				+ municipio.getUfMunicipio() + "]";
	}
}
