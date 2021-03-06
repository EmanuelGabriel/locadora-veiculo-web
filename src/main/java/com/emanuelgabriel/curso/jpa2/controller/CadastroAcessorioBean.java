package com.emanuelgabriel.curso.jpa2.controller;

import java.io.Serializable;

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
public class CadastroAcessorioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroAcessorioService cadastroAcessorioService;

	private Acessorio acessorio;

	@PostConstruct
	public void init() {
		this.limpar();
	}

	public void salvar() {
		try {
			this.cadastroAcessorioService.salvar(acessorio);
			FacesUtil.addSuccessMessage("Acessório salvo com sucesso!");
		} catch (RegraNegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}

		this.limpar();
	}

	public void limpar() {
		this.acessorio = new Acessorio();
	}

	public Acessorio getAcessorio() {
		return acessorio;
	}

	public void setAcessorio(Acessorio acessorio) {
		this.acessorio = acessorio;
	}

}
