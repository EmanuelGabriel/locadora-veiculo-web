package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.algaworks.curso.jpa2.modelo.Acessorio;
import com.algaworks.curso.jpa2.service.RegraNegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class AcessorioDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String REGRA_NEGOCIO_ACESSORIO = "Acessorio não pode ser excluído";

	@Inject
	private EntityManager manager;

	public Acessorio buscarPeloCodigo(Long codigo) {
		return manager.find(Acessorio.class, codigo);
	}

	public void salvar(Acessorio fabricante) {
		manager.merge(fabricante);
	}

	public List<Acessorio> buscarTodos() {
		TypedQuery<Acessorio> query = manager.createQuery("FROM Acessorio", Acessorio.class);
		return query.getResultList();
	}

	@Transactional
	public void excluir(Acessorio fabricante) throws RegraNegocioException {
		fabricante = buscarPeloCodigo(fabricante.getCodigo());
		try {
			manager.remove(fabricante);
			manager.flush();
		} catch (PersistenceException e) {
			throw new RegraNegocioException(REGRA_NEGOCIO_ACESSORIO);
		}
	}
}
