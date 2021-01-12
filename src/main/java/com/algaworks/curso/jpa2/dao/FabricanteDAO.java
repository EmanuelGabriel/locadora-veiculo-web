package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.algaworks.curso.jpa2.modelo.Fabricante;
import com.algaworks.curso.jpa2.service.RegraNegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class FabricanteDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public void salvar(Fabricante fabricante) {
		manager.merge(fabricante);
	}

	public List<Fabricante> buscarTodos() {
		TypedQuery<Fabricante> query = manager.createQuery("FROM Fabricante", Fabricante.class);
		return query.getResultList();
	}

	@Transactional
	public void excluir(Fabricante fabricante) throws RegraNegocioException {
		Fabricante fabricanteTemp = manager.find(Fabricante.class, fabricante.getCodigo());

		manager.remove(fabricanteTemp);
		manager.flush();
	}

	public Fabricante buscarPeloCodigo(Long codigo) {
		return manager.find(Fabricante.class, codigo);
	}

}
