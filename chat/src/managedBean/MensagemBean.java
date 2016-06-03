package managedBean;

import hibernate.GenericWorker;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import modelo.Mensagem;
import modelo.MensagemID;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.exception.ConstraintViolationException;

@SuppressWarnings("serial")
@ManagedBean(name = "mensagemBean")
@RequestScoped
public class MensagemBean implements Serializable {
	Mensagem mensagem;
	
	public MensagemBean() {
		mensagem = new Mensagem();
	}

	public void incluir(ActionEvent event) {
		try {
			GenericWorker<Mensagem, MensagemID> regHBR = new GenericWorker<Mensagem, MensagemID>(Mensagem.class);
			if (regHBR.inclui(mensagem)) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO!", "Registro incluido com sucesso."));
			}
			regHBR.finalize();
		} catch (ConstraintViolationException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!", "Chave primária já existe. Registro duplicado."));
			e.printStackTrace();
		} catch (HibernateException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!", "Falha na inclusão dos dados."));
			e.printStackTrace();
		}
	}

	public void excluir(ActionEvent event) {
		try {
			GenericWorker<Mensagem, MensagemID> regHBR = new GenericWorker<Mensagem, MensagemID>(Mensagem.class);
			if (regHBR.exclui(mensagem)) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO!","Registro excluído com sucesso."));
			}
			regHBR.finalize();
		} catch (ObjectNotFoundException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!", "Objeto não localizado."));
			e.printStackTrace();
		} catch (HibernateException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERRO!","Falha na exclusão dos dados."));
			e.printStackTrace();
		}
	}

	public void alterar(ActionEvent event) {
		try {
			GenericWorker<Mensagem, MensagemID> regHBR = new GenericWorker<Mensagem, MensagemID>(Mensagem.class);
			if (regHBR.altera(mensagem)) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO!", "Registro alterado com sucesso."));
			}
			regHBR.finalize();
		} catch (ObjectNotFoundException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!", "Objeto não localizado."));
			e.printStackTrace();
		} catch (HibernateException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!",	"Falha na exclusão dos dados."));
			e.printStackTrace();
		}
	}

	public void consultar(ActionEvent event) {
		try {
			GenericWorker<Mensagem, MensagemID> regHBR = new GenericWorker<Mensagem, MensagemID>(	Mensagem.class);
			mensagem = (Mensagem) regHBR.consulta(mensagem.getMensagemPK());
			regHBR.finalize();
		} catch (ObjectNotFoundException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!",	"Objeto não localizado."));
			e.printStackTrace();
		} catch (HibernateException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!", "Falha de consulta aos dados."));
			e.printStackTrace();
		}
	}

	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}

}
