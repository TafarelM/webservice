package com.tafarelmello.webservice.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.omnifaces.util.Messages;

import com.tafarelmello.webservice.dao.TesteDAO;
import com.tafarelmello.webservice.model.Teste;

@ManagedBean
@ViewScoped
public class TesteController implements Serializable {
	private static final long serialVersionUID = 4950266705663733054L;

	private Teste teste;

	@EJB
	private TesteDAO testeDAO;

	@PostConstruct
	public void init() {
		teste = new Teste();
	}

	public void salvar() {
		try {
			testeDAO.salvar(teste);

			teste = new Teste();

			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage("Salvo com sucesso.", "Salvo com sucesso"));
		} catch (RuntimeException erro) {
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Salvar.", "Erro ao Salvar"));
		}
	}
	
	public void salvarOrEditar(){
		try{
			
			
			Messages.addGlobalInfo("Editado com sucesso");
			Messages.addGlobalInfo("Salvo com sucesso");
		}catch (RuntimeException erro) {
			Messages.addGlobalError("não implementando ainda ;/.");
		}
	}

	public List<Teste> listar() {
		return testeDAO.getListOrdenada();
	}

	public void editar() {
		Messages.addGlobalError("não implementando ainda ;/.");
	}

	public void excluir() {
		Messages.addGlobalError("não implementando ainda ;/.");
	}

	public Teste getTeste() {
		return teste;
	}

	public void setTeste(Teste teste) {
		this.teste = teste;
	}

}
