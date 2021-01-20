package com.emanuelgabriel.curso.jpa2.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.emanuelgabriel.curso.jpa2.dao.AcessorioDAO;
import com.emanuelgabriel.curso.jpa2.modelo.Acessorio;
import com.emanuelgabriel.curso.jpa2.util.jpa.Transactional;

public class CadastroAcessorioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AcessorioDAO acessorioDAO;

	@Transactional
	public void salvar(Acessorio acessorio) throws RegraNegocioException {

		Acessorio acesorioExistente = this.acessorioDAO.buscarPorDescricao(acessorio.getDescricao());
		if (acesorioExistente != null && !acesorioExistente.equals(acessorio)) {
			throw new RegraNegocioException("Já existe um acessório cadastrado com esta descrição");
		}

		if (acessorio.getDescricao() == null || acessorio.getDescricao().trim().equals("")) {
			throw new RegraNegocioException("A descrição do acessório é obrigatório");
		}

		this.acessorioDAO.salvar(acessorio);
	}

	public List<Acessorio> buscarTodos() {
		return this.acessorioDAO.buscarTodos();
	}

	public Acessorio buscarPorId(Long id) {
		return this.acessorioDAO.buscarPeloCodigo(id);
	}

	public void remover(Acessorio acessorio) throws RegraNegocioException {
		this.acessorioDAO.excluir(acessorio);
	}

}
