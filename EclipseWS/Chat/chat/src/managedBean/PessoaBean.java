package managedBean;

import hibernate.GenericWorker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import modelo.Endereco;
import modelo.Pessoa;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.criterion.Criterion;
import org.hibernate.exception.ConstraintViolationException;

@SuppressWarnings("serial")
@ManagedBean(name = "pessoaBean")
@RequestScoped
public class PessoaBean implements Serializable {
	private Pessoa registro;
	private List<Endereco> listaDeEnderecos;
	private Integer idSelecionado;
	private List<SelectItem> listaSelectItem = new ArrayList<SelectItem>();
	private AcessoBean acessoBean;

	@SuppressWarnings("deprecation")
	public PessoaBean() {
		registro = new Pessoa();
		FacesContext fc = FacesContext.getCurrentInstance();
		acessoBean = (AcessoBean) fc.getApplication().getVariableResolver()
				.resolveVariable(fc, "acessoBean");
		listarEnderecos();
	}

	private String listarEnderecos() {
		if (acessoBean.isValid()) {
			try {
				GenericWorker<Endereco, Integer> regHBR = new GenericWorker<Endereco, Integer>(
						Endereco.class);
				listaDeEnderecos = regHBR.listar(new Criterion[0]);
				regHBR.finalize();
				int i = 0;
				for (Endereco p : listaDeEnderecos)
					listaSelectItem.add(new SelectItem(i++, p.getLogradouro()
							+ " " + p.getComplemento() + " " + p.getBairro()
							+ " " + p.getMunicipio().getNomeMunicipio() + " "
							+ p.getMunicipio().getUfMunicipio()));
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
				GenericWorker<Pessoa, Integer> regHBR = new GenericWorker<Pessoa, Integer>(
						Pessoa.class);
				registro.setEndereco(listaDeEnderecos.get(idSelecionado));
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

	public String excluir(ActionEvent event) {
		if (acessoBean.isValid()) {
			try {
				GenericWorker<Pessoa, Integer> regHBR1 = new GenericWorker<Pessoa, Integer>(
						Pessoa.class);
				registro = (Pessoa) regHBR1.consulta(registro.getIdPessoa());
				regHBR1.finalize();
				GenericWorker<Pessoa, Integer> regHBR = new GenericWorker<Pessoa, Integer>(
						Pessoa.class);
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

	public String alterar(ActionEvent event) {
		if (acessoBean.isValid()) {
			try {
				GenericWorker<Pessoa, Integer> regHBR = new GenericWorker<Pessoa, Integer>(
						Pessoa.class);
				registro.setEndereco(listaDeEnderecos.get(idSelecionado));
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

	public String consultar(ActionEvent event) {
		if (acessoBean.isValid()) {
			try {
				GenericWorker<Pessoa, Integer> regHBR = new GenericWorker<Pessoa, Integer>(
						Pessoa.class);
				registro = (Pessoa) regHBR.consulta(registro.getIdPessoa());
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

	public Pessoa getRegistro() {
		return registro;
	}

	public void setRegistro(Pessoa registro) {
		this.registro = registro;
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