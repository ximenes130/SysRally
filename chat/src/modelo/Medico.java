package modelo;

import java.io.Serializable;
import javax.persistence.*;


import java.util.List;


/**
 * The persistent class for the medico database table.
 * 
 */
@Entity
@Table(name="medico")
public class Medico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int idMedico;

	@Column(nullable=false, length=45)
	private String nomeMedico;

	//bi-directional many-to-many association to Paciente
	@ManyToMany(mappedBy="medicos")
	private List<Paciente> pacientes;

	public Medico() {
	}

	public int getIdMedico() {
		return this.idMedico;
	}

	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}

	public String getNomeMedico() {
		return this.nomeMedico;
	}

	public void setNomeMedico(String nomeMedico) {
		this.nomeMedico = nomeMedico;
	}

	public List<Paciente> getPacientes() {
		return this.pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

}