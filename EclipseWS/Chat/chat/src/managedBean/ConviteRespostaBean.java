package managedBean;

import hibernate.GenericWorker;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import modelo.Convite;
import modelo.ConviteID;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

@SuppressWarnings("serial")
@ManagedBean(name = "responderConviteBean")
@RequestScoped
public class ConviteRespostaBean implements Serializable {
	private Convite convite;
	private ConviteID conviteID;
	private Integer idSelecionado;
	private List<SelectItem> listaSelectItem = new ArrayList<SelectItem>();
	private List<Convite> listagem = new ArrayList<Convite>();
	private AcessoBean acessoBean;

	@SuppressWarnings("deprecation")
	public ConviteRespostaBean() {
		FacesContext fc = FacesContext.getCurrentInstance();
		acessoBean = (AcessoBean) fc.getApplication().getVariableResolver()
				.resolveVariable(fc, "acessoBean");
		this.conviteID = new ConviteID();
		this.conviteID.setIdPessoa2(acessoBean.getPessoa().getIdPessoa());
		this.convite = new Convite();
		this.convite.setConviteID(this.conviteID);
		this.convite.setPessoa2(acessoBean.getPessoa());
		listarConvitesRecebidos();
	}

	private String listarConvitesRecebidos() {
		if (acessoBean.isValid()) {
			try {
				listagem.clear();
				listaSelectItem.clear();
				GenericWorker<Convite, ConviteID> regHBR = new GenericWorker<Convite, ConviteID>(
						Convite.class);
				Criterion clausula[] = new Criterion[3];
				clausula[0] = Restrictions.naturalId().set("pessoa2",
						acessoBean.getPessoa());
				clausula[1] = Restrictions.isNull("timestampRecebimento");
				clausula[2] = Restrictions.eq("aceiteConvite", false);
				listagem = regHBR.listar(clausula);
				regHBR.finalize();
				int i = 0;
				for (Convite obj : listagem)
					this.listaSelectItem.add(new SelectItem(i++, obj
							.getPessoa1().getNome()
							+ " ("
							+ obj.getPessoa1().getIdPessoa()
							+ ") -> "
							+ obj.getPessoa2().getNome()
							+ " ("
							+ obj.getPessoa2().getIdPessoa() + ")"));
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

	private String alterar(Boolean aceitar) {
		if (acessoBean.isValid()) {
			try {
				Calendar calendario = new GregorianCalendar();
				Timestamp tm = new Timestamp(calendario.getTimeInMillis());
				this.conviteID.setTimestampConvite(listagem.get(idSelecionado)
						.getConviteID().getTimestampConvite());
				this.conviteID.setIdPessoa1(listagem.get(idSelecionado)
						.getConviteID().getIdPessoa1());
				this.convite.setPessoa1(listagem.get(idSelecionado)
						.getPessoa1());
				this.convite.setConviteID(this.conviteID);
				this.convite.setTimestampRecebimento(tm);
				this.convite.setAceiteConvite(aceitar);
				GenericWorker<Convite, ConviteID> regHBR = new GenericWorker<Convite, ConviteID>(
						Convite.class);
				if (regHBR.altera(this.convite)) {
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage(
							FacesMessage.SEVERITY_INFO, "INFO!",
							"Registro alterado com sucesso."));
				}
				regHBR.finalize();
				// --------- Corrigir tela de aceites -------
				listarConvitesRecebidos();
				// ------------------------------------------
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

	public String aceitar(ActionEvent event) {
		if (alterar(true) == "sucesso") {
			return "sucesso";
		} else
			return "insucesso";
	}

	public String naoaceitar(ActionEvent event) {
		if (alterar(false) == "sucesso") {
			return "sucesso";
		} else
			return "insucesso";
	}

	public Convite getConvite() {
		return convite;
	}

	public void setConvite(Convite convite) {
		this.convite = convite;
	}

	public Integer getIdSelecionado() {
		return idSelecionado;
	}

	public void setIdSelecionado(Integer idSelecionado) {
		this.idSelecionado = idSelecionado;
	}

	public List<SelectItem> getListaSelectItem() {
		return listaSelectItem;
	}

	public void setListaSelectItem(List<SelectItem> listaSelectItem) {
		this.listaSelectItem = listaSelectItem;
	}
}
