package managedBean;

import hibernate.GenericWorker;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import modelo.Conversa;
import modelo.ConversaID;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.exception.ConstraintViolationException;

@SuppressWarnings("serial")
@ManagedBean(name = "conversaBean")
@RequestScoped
public class ConversaBean implements Serializable {
	Conversa conversa;
	private AcessoBean acessoBean;

	@SuppressWarnings("deprecation")
	public ConversaBean() {
		conversa = new Conversa();
		FacesContext fc = FacesContext.getCurrentInstance();
		acessoBean = (AcessoBean) fc.getApplication().getVariableResolver()
				.resolveVariable(fc, "acessoBean");
	}

	public String incluir(ActionEvent event) {
		if (acessoBean.isValid()) {
			try {
				GenericWorker<Conversa, ConversaID> regHBR = new GenericWorker<Conversa, ConversaID>(
						Conversa.class);
				if (regHBR.inclui(conversa)) {
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

	public String excluir(ActionEvent event) {
		if (acessoBean.isValid()) {
			try {
				GenericWorker<Conversa, ConversaID> regHBR1 = new GenericWorker<Conversa, ConversaID>(
						Conversa.class);
				conversa = (Conversa) regHBR1.consulta(conversa.getConversaPK());
				regHBR1.finalize();
				GenericWorker<Conversa, ConversaID> regHBR = new GenericWorker<Conversa, ConversaID>(
						Conversa.class);
				if (regHBR.exclui(conversa)) {
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

	public String alterar(ActionEvent event) {
		if (acessoBean.isValid()) {
			try {
				GenericWorker<Conversa, ConversaID> regHBR = new GenericWorker<Conversa, ConversaID>(
						Conversa.class);
				if (regHBR.altera(conversa)) {
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

	public String consultar(ActionEvent event) {
		if (acessoBean.isValid()) {
			try {
				GenericWorker<Conversa, ConversaID> regHBR = new GenericWorker<Conversa, ConversaID>(
						Conversa.class);
				conversa = (Conversa) regHBR.consulta(conversa.getConversaPK());
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

	public Conversa getConversa() {
		return conversa;
	}

	public void setConversa(Conversa conversa) {
		this.conversa = conversa;
	}

}
