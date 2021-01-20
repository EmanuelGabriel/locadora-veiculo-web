package com.emanuelgabriel.curso.jpa2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.emanuelgabriel.curso.jpa2.dao.FabricanteDAO;
import com.emanuelgabriel.curso.jpa2.modelo.Fabricante;
import com.emanuelgabriel.curso.jpa2.modelolazy.LazyFabricanteDataModel;
import com.emanuelgabriel.curso.jpa2.service.CadastroFabricanteService;
import com.emanuelgabriel.curso.jpa2.service.RegraNegocioException;
import com.emanuelgabriel.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaFabricanteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroFabricanteService cadastroFabricanteService;

	@Inject
	private FabricanteDAO fabricanteDAO;

	private LazyFabricanteDataModel lazyFabricanteDataModel;

	private List<Fabricante> fabricantes = new ArrayList<>();

	private Fabricante fabricanteSelecionado;

	@PostConstruct
	public void inicializar() {
		fabricantes = cadastroFabricanteService.buscarTodos();
		lazyFabricanteDataModel = new LazyFabricanteDataModel(fabricanteDAO);
	}

	public void excluir() {
		try {
			this.cadastroFabricanteService.remover(fabricanteSelecionado);
			this.fabricantes.remove(fabricanteSelecionado);
			FacesUtil.addSuccessMessage("Fabricante " + fabricanteSelecionado.getNome() + " excluído com sucesso.");
		} catch (RegraNegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}

	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public Fabricante getFabricanteSelecionado() {
		return fabricanteSelecionado;
	}

	public void setFabricanteSelecionado(Fabricante fabricanteSelecionado) {
		this.fabricanteSelecionado = fabricanteSelecionado;
	}

	public LazyFabricanteDataModel getLazyFabricanteDataModel() {
		return lazyFabricanteDataModel;
	}
}
