package managedBean;

import hibernate.GenericWorker;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import modelo.Acesso;
import modelo.Pessoa;

@SuppressWarnings("serial")
@ManagedBean(name = "acessoBean")
@SessionScoped
public class AcessoBean implements Serializable {
	private String email;
	private String senha;
	private String mensagemUsuario;
	private Acesso acesso;
	private Pessoa pessoa;

	public AcessoBean() {
		super();
		this.acesso = new Acesso();
		this.pessoa = this.acesso.getPessoa();
		this.email = "";
		this.senha = "";
		this.mensagemUsuario = "";
	}

	public String login() {
		try {
			GenericWorker<Acesso, String> regHBR2 = new GenericWorker<Acesso, String>(
					Acesso.class);
			acesso = (Acesso) regHBR2.consulta(this.email);
			regHBR2.finalize();
			pessoa = acesso.getPessoa();
			if (email.equals(acesso.getEmail())
					&& senha.equals(acesso.getSenha())) {
				this.mensagemUsuario = "";
				return "sucesso";
			} else {
				this.mensagemUsuario = "Dados inválidos.";
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "ERRO!",
						"Dados inválidos."));
				return "insucesso";
			}
		} catch (Exception e) {
			this.mensagemUsuario = "Dados não conferem.";
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO!", "Dados inválidos."));
			e.printStackTrace();
		}
		return "insucesso";
	}

	public String logout() {
		this.acesso = new Acesso();
		this.pessoa = this.acesso.getPessoa();
		this.email = "";
		this.senha = "";
		this.mensagemUsuario = "";
		return "insucesso";
	}

	public boolean isValid() {
		try {
			GenericWorker<Acesso, String> regHBR2 = new GenericWorker<Acesso, String>(
					Acesso.class);
			acesso = (Acesso) regHBR2.consulta(this.email);
			regHBR2.finalize();
			if (email.equals(acesso.getEmail())
					&& senha.equals(acesso.getSenha())) {
				return true;
			}
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERRO!", "Dados inválidos."));
			e.printStackTrace();
		}
		return false;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getMensagemUsuario() {
		return mensagemUsuario;
	}

	public void setMensagemUsuario(String mensagemUsuario) {
		this.mensagemUsuario = mensagemUsuario;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}