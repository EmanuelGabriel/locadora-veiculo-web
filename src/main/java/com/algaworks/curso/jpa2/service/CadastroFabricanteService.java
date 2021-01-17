package com.algaworks.curso.jpa2.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.algaworks.curso.jpa2.dao.FabricanteDAO;
import com.algaworks.curso.jpa2.modelo.Fabricante;

public class CadastroFabricanteService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FabricanteDAO fabricanteDAO;

	public void criar(Fabricante fabricante) throws RegraNegocioException {
		if (fabricante.getNome() == null || fabricante.getNome().trim().equals("")) {
			throw new RegraNegocioException("O nome do fabricante é obrigatório");
		}

		this.fabricanteDAO.salvar(fabricante);
	}

	public List<Fabricante> buscarTodos() {
		return this.fabricanteDAO.buscarTodos();
	}

	public void remover(Fabricante fabricante) throws RegraNegocioException {
		this.fabricanteDAO.excluir(fabricante);
	}

}
