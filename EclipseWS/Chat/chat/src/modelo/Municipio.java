package modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "municipio")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Municipio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idMunicipio", nullable = false)
	private Integer idMunicipio;

	@Column(name = "nomeMunicipio", length = 50, nullable = false)
	private String nomeMunicipio;

	@Column(name = "ufMunicipio", length = 2, nullable = false)
	private String ufMunicipio;

	@Column(name = "habitantes", nullable = true)
	private Integer habitantes;
	
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@OneToMany(targetEntity = Endereco.class, mappedBy = "municipio")
	private List<Endereco> enderecos;

	public Municipio() {
		super();
	}

	public Integer getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(Integer idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}

	public String getUfMunicipio() {
		return ufMunicipio;
	}

	public void setUfMunicipio(String ufMunicipio) {
		this.ufMunicipio = ufMunicipio;
	}

	public Integer getHabitantes() {
		return habitantes;
	}

	public void setHabitantes(Integer habitantes) {
		this.habitantes = habitantes;
	}

	@Override
	public String toString() {
		return "Municipio [idMunicipio=" + idMunicipio + ", nomeMunicipio="
				+ nomeMunicipio + ", ufMunicipio=" + ufMunicipio
				+ ", habitantes=" + habitantes + "]";
	}
}