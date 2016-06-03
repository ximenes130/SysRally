package managedBean;

import hibernate.GenericWorker;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import modelo.Municipio;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

@SuppressWarnings("serial")
@ManagedBean(name = "listaLikeMunicipioBean")
@RequestScoped
public class MunicipioListaLikeBean implements Serializable {
	private List<Municipio> listagem;
	private String filtro;
	private AcessoBean acessoBean;

	@SuppressWarnings("deprecation")
	public MunicipioListaLikeBean() {
		listagem = null;
		filtro = new String();
		FacesContext fc = FacesContext.getCurrentInstance();
		acessoBean = (AcessoBean) fc.getApplication().getVariableResolver()
				.resolveVariable(fc, "acessoBean");
	}

	public String listarLike(ActionEvent event) {
		if (acessoBean.isValid()) {
			try {
				GenericWorker<Municipio, Integer> regHBR = new GenericWorker<Municipio, Integer>(
						Municipio.class);
				Criterion clausula[] = new Criterion[1];
				clausula[0] = Restrictions.like("nomeMunicipio", filtro + "%");
				listagem = regHBR.listar(clausula);
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

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
}