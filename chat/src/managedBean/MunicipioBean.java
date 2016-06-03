package managedBean;

import hibernate.GenericWorker;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import modelo.Municipio;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.exception.ConstraintViolationException;


@SuppressWarnings("serial")
@ManagedBean(name = "municipioBean")
@RequestScoped
public class MunicipioBean implements Serializable {
	private Municipio registro;
	private AcessoBean acessoBean;

	@SuppressWarnings("deprecation")
	public MunicipioBean() {
		registro = new Municipio();
		FacesContext fc = FacesContext.getCurrentInstance();
		acessoBean = (AcessoBean) fc.getApplication().getVariableResolver()
				.resolveVariable(fc, "acessoBean");
	}

	public String incluir() {
		if (acessoBean.isValid()) {
			try {
				GenericWorker<Municipio, Integer> regHBR = new GenericWorker<Municipio, Integer>(
						Municipio.class);
				if (regHBR.inclui(registro)) {
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_INFO, "INFO!",
							"Registro incluido com sucesso."));
				}
				regHBR.finalize();
			} catch (ConstraintViolationException e) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "ERRO!",
						"Chave primária já existe. Registro duplicado."));
				e.printStackTrace();
			} catch (HibernateException e) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "ERRO!",
						"Falha na inclusão dos dados."));
				e.printStackTrace();
			}
			return "sucesso";
		} else
			return "insucesso";
	}

	public String excluir() {
		if (acessoBean.isValid()) {
			try {
				GenericWorker<Municipio, Integer> regHBR1 = new GenericWorker<Municipio, Integer>(Municipio.class);
				registro = (Municipio) regHBR1.consulta(registro.getIdMunicipio());
				regHBR1.finalize();
				GenericWorker<Municipio, Integer> regHBR = new GenericWorker<Municipio, Integer>(
						Municipio.class);
				if (regHBR.exclui(registro)) {
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_INFO, "INFO!",
							"Registro excluído com sucesso."));
				}
				regHBR.finalize();
			} catch (ObjectNotFoundException e) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "ERRO!",
						"Objeto não localizado."));
				e.printStackTrace();
			} catch (HibernateException e) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "ERRO!",
						"Falha na exclusão dos dados."));
				e.printStackTrace();
			}
			return "sucesso";
		} else
			return "insucesso";

	}

	public String alterar() {
		if (acessoBean.isValid()) {
			try {
				GenericWorker<Municipio, Integer> regHBR = new GenericWorker<Municipio, Integer>(
						Municipio.class);
				if (regHBR.altera(registro)) {
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_INFO, "INFO!",
							"Registro alterado com sucesso."));
				}
				regHBR.finalize();
			} catch (ObjectNotFoundException e) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "ERRO!",
						"Objeto não localizado."));
				e.printStackTrace();
			} catch (HibernateException e) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "ERRO!",
						"Falha na exclusão dos dados."));
				e.printStackTrace();
			}
			return "sucesso";
		} else
			return "insucesso";

	}

	public String consultar() {
		if (acessoBean.isValid()) {
			try {
				GenericWorker<Municipio, Integer> regHBR = new GenericWorker<Municipio, Integer>(
						Municipio.class);
				registro = (Municipio) regHBR.consulta(registro
						.getIdMunicipio());
				regHBR.finalize();
			} catch (ObjectNotFoundException e) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "ERRO!",
						"Objeto não localizado."));
				e.printStackTrace();
			} catch (HibernateException e) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "ERRO!",
						"Falha de consulta aos dados."));
				e.printStackTrace();
			}
			return "sucesso";
		} else
			return "insucesso";

	}

	public Municipio getRegistro() {
		return registro;
	}

	public void setRegistro(Municipio registro) {
		this.registro = registro;
	}
}