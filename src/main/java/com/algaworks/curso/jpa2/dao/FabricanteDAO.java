package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.algaworks.curso.jpa2.modelo.Fabricante;
import com.algaworks.curso.jpa2.service.RegraNegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class FabricanteDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String REGRA_NEGOCIO_FABRICANTE = "Este Fabricante não pode ser excluído";

	@Inject
	private EntityManager manager;

	@Transactional
	public void salvar(Fabricante fabricante) {
		manager.merge(fabricante);
	}

	public List<Fabricante> buscarTodos() {
		TypedQuery<Fabricante> query = manager.createQuery("FROM Fabricante", Fabricante.class);
		return query.getResultList();
	}

	@Transactional
	public void excluir(Fabricante fabricante) throws RegraNegocioException {
		Fabricante fabricanteExistente = buscarPeloCodigo(fabricante.getCodigo());
		try {
			manager.remove(fabricanteExistente);
			manager.flush();

		} catch (PersistenceException e) {
			throw new RegraNegocioException(REGRA_NEGOCIO_FABRICANTE);
		}

	}

	public Fabricante buscarPeloCodigo(Long codigo) {
		return manager.find(Fabricante.class, codigo);
	}

}
