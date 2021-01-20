package com.emanuelgabriel.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.emanuelgabriel.curso.jpa2.modelo.Funcionario;
import com.emanuelgabriel.curso.jpa2.service.RegraNegocioException;
import com.emanuelgabriel.curso.jpa2.util.jpa.Transactional;

public class FuncionarioDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Funcionario buscarPeloCodigo(Long codigo) {
		return manager.find(Funcionario.class, codigo);
	}

	public void salvar(Funcionario funcionario) {
		manager.merge(funcionario);
	}

	public List<Funcionario> buscarTodos() {
		TypedQuery<Funcionario> query = manager.createQuery("FROM Funcionario", Funcionario.class);
		return query.getResultList();
	}

	@Transactional
	public void excluir(Funcionario funcionario) throws RegraNegocioException {
		funcionario = buscarPeloCodigo(funcionario.getCodigo());
		try {
			manager.remove(funcionario);
			manager.flush();
		} catch (PersistenceException e) {
			throw new RegraNegocioException("Funcionario não pode ser excluído.");
		}
	}
}
