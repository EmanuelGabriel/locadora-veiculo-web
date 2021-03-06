package com.emanuelgabriel.curso.jpa2.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.emanuelgabriel.curso.jpa2.dao.ModeloCarroDAO;
import com.emanuelgabriel.curso.jpa2.modelo.ModeloCarro;
import com.emanuelgabriel.curso.jpa2.util.jpa.Transactional;

public class CadastroModeloCarroService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	ModeloCarroDAO modeloCarroDAO;

	@Transactional
	public void salvar(ModeloCarro modeloCarro) throws RegraNegocioException {
		if (modeloCarro.getDescricao() == null || modeloCarro.getDescricao().trim().equals("")) {
			throw new RegraNegocioException("O nome do modelo é obrigatório.");
		}

		if (modeloCarro.getFabricante() == null) {
			throw new RegraNegocioException("O fabricante é obrigatório");
		}

		this.modeloCarroDAO.salvar(modeloCarro);
	}

	public List<ModeloCarro> buscarTodos() {
		return this.modeloCarroDAO.buscarTodos();
	}

	public void remover(ModeloCarro modeloCarro) throws RegraNegocioException {
		this.modeloCarroDAO.excluir(modeloCarro);
	}

}
