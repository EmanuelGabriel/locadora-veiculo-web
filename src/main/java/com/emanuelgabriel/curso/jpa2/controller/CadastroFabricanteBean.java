package com.emanuelgabriel.curso.jpa2.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.emanuelgabriel.curso.jpa2.modelo.Fabricante;
import com.emanuelgabriel.curso.jpa2.service.CadastroFabricanteService;
import com.emanuelgabriel.curso.jpa2.service.RegraNegocioException;
import com.emanuelgabriel.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroFabricanteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String MSG_FABRICANTE_SALVO = "Fabricante salvo com sucesso!";

	@Inject
	private CadastroFabricanteService cadastroFabricanteService;

	private Fabricante fabricante;

	@PostConstruct
	public void init() {
		this.limpar();
	}

	public void salvar() {
		try {
			this.cadastroFabricanteService.criar(fabricante);
			FacesUtil.addSuccessMessage(MSG_FABRICANTE_SALVO);

			this.limpar();
		} catch (RegraNegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public void limpar() {
		this.fabricante = new Fabricante();
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

}
