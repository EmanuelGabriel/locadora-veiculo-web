package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.algaworks.curso.jpa2.modelo.Motorista;
import com.algaworks.curso.jpa2.service.RegraNegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class MotoristaDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String REGRA_NEGOCIO_MOTORISTA_EXCLUSAO = "Motorista não pode ser excluído";

	@Inject
	private EntityManager manager;

	public Motorista buscarPeloCodigo(Long codigo) {
		return manager.find(Motorista.class, codigo);
	}

	public void salvar(Motorista motorista) {
		manager.merge(motorista);
	}

	public List<Motorista> buscarTodos() {
		TypedQuery<Motorista> query = manager.createQuery("FROM Motorista", Motorista.class);
		return query.getResultList();
	}

	@Transactional
	public void excluir(Motorista motorista) throws RegraNegocioException {
		motorista = buscarPeloCodigo(motorista.getCodigo());
		try {
			manager.remove(motorista);
			manager.flush();
		} catch (PersistenceException e) {
			throw new RegraNegocioException(REGRA_NEGOCIO_MOTORISTA_EXCLUSAO);
		}
	}
}
