package com.emanuelgabriel.curso.jpa2.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.emanuelgabriel.curso.jpa2.dao.FabricanteDAO;
import com.emanuelgabriel.curso.jpa2.modelo.Fabricante;

public class CadastroFabricanteService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FabricanteDAO fabricanteDAO;

	public void criar(Fabricante fabricante) throws RegraNegocioException {

		Fabricante fabricanteExistente = this.fabricanteDAO.buscarPorNome(fabricante.getNome());
		if (fabricanteExistente != null && !fabricanteExistente.equals(fabricante)) {
			throw new RegraNegocioException("Já existe um Fabricante cadastrado com este nome");
		}

		if (fabricante.getNome() == null || fabricante.getNome().trim().equals("")) {
			throw new RegraNegocioException("O nome do fabricante é obrigatório");
		}

		this.fabricanteDAO.salvar(fabricante);
	}

	public Fabricante buscarPorNome(String nome) {
		return this.fabricanteDAO.buscarPorNome(nome);
	}

	public List<Fabricante> buscarTodos() {
		return this.fabricanteDAO.buscarTodos();
	}

	public void remover(Fabricante fabricante) throws RegraNegocioException {
		this.fabricanteDAO.excluir(fabricante);
	}

}
