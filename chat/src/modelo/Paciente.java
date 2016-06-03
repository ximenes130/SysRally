package modelo;

import java.io.Serializable;
import javax.persistence.*;


import java.util.List;


/**
 * The persistent class for the paciente database table.
 * 
 */
@Entity
@Table(name="paciente")
public class Paciente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int idPaciente;

	@Column(nullable=false, length=45)
	private String nomePaciente;

	//bi-directional many-to-many association to Medico
	@ManyToMany
	@JoinTable(
		name="consulta"
		, joinColumns={
			@JoinColumn(name="idPaciente", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="idMedico", nullable=false)
			}
		)
	private List<Medico> medicos;

	public Paciente() {
	}

	public int getIdPaciente() {
		return this.idPaciente;
	}

	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getNomePaciente() {
		return this.nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public List<Medico> getMedicos() {
		return this.medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}

}