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
import modelo.Pessoa;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.exception.ConstraintViolationException;

@SuppressWarnings("serial")
@ManagedBean(name = "enviarConviteBean")
@RequestScoped
public class ConviteEnvioBean implements Serializable {
	private Convite convite;
	private ConviteID conviteID;
	private List<Pessoa> listaDePessoas;
	private Integer idSelecionado;
	private List<SelectItem> listaSelectItem = new ArrayList<SelectItem>();
	private AcessoBean acessoBean;

	@SuppressWarnings("deprecation")
	public ConviteEnvioBean() {
		FacesContext fc = FacesContext.getCurrentInstance();
		acessoBean = (AcessoBean) fc.getApplication().getVariableResolver()
				.resolveVariable(fc, "acessoBean");
		this.conviteID = new ConviteID();
		this.conviteID.setIdPessoa1(acessoBean.getPessoa().getIdPessoa());
		this.convite = new Convite();
		this.convite.setConviteID(this.conviteID);
		this.convite.setPessoa1(acessoBean.getPessoa());
		listarPessoasParaConvite();
	}

	private String listarPessoasParaConvite() {
		if (acessoBean.isValid()) {
			try {
				GenericWorker<Pessoa, Integer> regHBR = new GenericWorker<Pessoa, Integer>(
						Pessoa.class);
				this.listaDePessoas = regHBR.listar(new Criterion[0]);
				regHBR.finalize();
				int i = 0;
				for (Pessoa p : this.listaDePessoas)
					this.listaSelectItem.add(new SelectItem(i++, p.getNome()
							+ " (" + p.getIdPessoa() + ")"));
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

	public String incluir(ActionEvent event) {
		if (acessoBean.isValid()) {
			try {
				Calendar calendario = new GregorianCalendar();
				Timestamp tm = new Timestamp(calendario.getTimeInMillis());
				this.conviteID.setTimestampConvite(tm);
				this.conviteID.setIdPessoa2(this.listaDePessoas.get(
						this.idSelecionado).getIdPessoa());
				this.convite.setConviteID(this.conviteID);
				this.convite.setPessoa2(this.listaDePessoas
						.get(this.idSelecionado));
				GenericWorker<Convite, ConviteID> regHBR = new GenericWorker<Convite, ConviteID>(
						Convite.class);
				if (regHBR.inclui(this.convite)) {
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
