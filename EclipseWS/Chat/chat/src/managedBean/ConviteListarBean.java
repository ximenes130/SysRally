package managedBean;

import hibernate.GenericWorker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import modelo.Convite;
import modelo.ConviteID;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

@SuppressWarnings("serial")
@ManagedBean(name = "listarConvitesBean")
@RequestScoped
public class ConviteListarBean implements Serializable {
	private List<Convite> listagemRecebidos = new ArrayList<Convite>();
	private List<Convite> listagemEnviados = new ArrayList<Convite>();
	private AcessoBean acessoBean;

	@SuppressWarnings("deprecation")
	public ConviteListarBean() {
		FacesContext fc = FacesContext.getCurrentInstance();
		acessoBean = (AcessoBean) fc.getApplication().getVariableResolver()
				.resolveVariable(fc, "acessoBean");
		listarConvitesRecebidos();
		listarConvitesEnviados();
	}

	private String listarConvitesRecebidos() {
		if (acessoBean.isValid()) {
			try {
				GenericWorker<Convite, ConviteID> regHBR = new GenericWorker<Convite, ConviteID>(
						Convite.class);
				Criterion clausula[] = new Criterion[1];
				clausula[0] = Restrictions.naturalId().set("pessoa2",
						acessoBean.getPessoa());
				listagemRecebidos = regHBR.listar(clausula);
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

	private String listarConvitesEnviados() {
		if (acessoBean.isValid()) {
			try {
				GenericWorker<Convite, ConviteID> regHBR = new GenericWorker<Convite, ConviteID>(
						Convite.class);
				Criterion clausula[] = new Criterion[1];
				clausula[0] = Restrictions.naturalId().set("pessoa1",
						acessoBean.getPessoa());
				listagemEnviados = regHBR.listar(clausula);
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

	public List<Convite> getListagemRecebidos() {
		return listagemRecebidos;
	}

	public void setListagemRecebidos(List<Convite> listagemRecebidos) {
		this.listagemRecebidos = listagemRecebidos;
	}

	public List<Convite> getListagemEnviados() {
		return listagemEnviados;
	}

	public void setListagemEnviados(List<Convite> listagemEnviados) {
		this.listagemEnviados = listagemEnviados;
	}

}
