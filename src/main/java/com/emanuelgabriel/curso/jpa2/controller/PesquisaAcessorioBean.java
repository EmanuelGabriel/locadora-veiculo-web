package com.emanuelgabriel.curso.jpa2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.emanuelgabriel.curso.jpa2.modelo.Acessorio;
import com.emanuelgabriel.curso.jpa2.service.CadastroAcessorioService;
import com.emanuelgabriel.curso.jpa2.service.RegraNegocioException;
import com.emanuelgabriel.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaAcessorioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroAcessorioService cadastroAcessorioService;

	private List<Acessorio> acessorios = new ArrayList<>();

	private Acessorio acessorioSelecionado;

	@PostConstruct
	public void inicializar() {
		acessorios = cadastroAcessorioService.buscarTodos();
	}

	public void excluir() {
		try {
			// acessorioDAO.excluir(acessorioSelecionado);
			this.cadastroAcessorioService.remover(acessorioSelecionado);
			this.acessorios.remove(acessorioSelecionado);
			FacesUtil.addSuccessMessage("Acessório " + acessorioSelecionado.getDescricao() + " excluído com sucesso.");
		} catch (RegraNegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public List<Acessorio> getAcessorios() {
		return acessorios;
	}

	public Acessorio getAcessorioSelecionado() {
		return acessorioSelecionado;
	}

	public void setAcessorioSelecionado(Acessorio acessorioSelecionado) {
		this.acessorioSelecionado = acessorioSelecionado;
	}

}
