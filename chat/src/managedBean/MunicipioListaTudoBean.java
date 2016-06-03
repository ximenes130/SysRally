package managedBean;

import hibernate.GenericWorker;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import modelo.Municipio;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;

@SuppressWarnings("serial")
@ManagedBean(name = "listaTudoMunicipioBean")
@RequestScoped
public class MunicipioListaTudoBean implements Serializable {
	private List<Municipio> listagem;
	private AcessoBean acessoBean;

	@SuppressWarnings("deprecation")
	public MunicipioListaTudoBean() {
		listagem = null;
		FacesContext fc = FacesContext.getCurrentInstance();
		acessoBean = (AcessoBean) fc.getApplication().getVariableResolver()
				.resolveVariable(fc, "acessoBean");
		listarTudo();
	}

	private String listarTudo() {
		if (acessoBean.isValid()) {
			try {
				GenericWorker<Municipio, Integer> regHBR = new GenericWorker<Municipio, Integer>(
						Municipio.class);
				listagem = regHBR.listar(new Criterion[0]);
				regHBR.finalize();
			} catch (HibernateException e) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "ERRO!",
						"Falha na consulta aos dados."));
				e.printStackTrace();
			}
			return "sucesso";
		} else
			return "insucesso";
	}

	public List<Municipio> getListagem() {
		return listagem;
	}

	public void setListagem(List<Municipio> listagem) {
		this.listagem = listagem;
	}
}