package com.emanuelgabriel.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.emanuelgabriel.curso.jpa2.modelo.ModeloCarro;
import com.emanuelgabriel.curso.jpa2.service.RegraNegocioException;
import com.emanuelgabriel.curso.jpa2.util.jpa.Transactional;

public class ModeloCarroDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String REGRA_NEGOCIO_MODELO_VEICULO = "Este modelo não pode ser excluído";

	@Inject
	private EntityManager manager;

	public void salvar(ModeloCarro modeloCarro) {
		manager.merge(modeloCarro);
	}

	public List<ModeloCarro> buscarTodos() {
		TypedQuery<ModeloCarro> query = manager.createQuery("SELECT mc FROM ModeloCarro mc JOIN FETCH mc.fabricante",
				ModeloCarro.class);
		return query.getResultList();

	}

	@Transactional
	public void excluir(ModeloCarro modeloCarro) throws RegraNegocioException {
		modeloCarro = buscarPeloCodigo(modeloCarro.getCodigo());
		try {
			manager.remove(modeloCarro);
			manager.flush();
		} catch (PersistenceException e) {
			throw new RegraNegocioException(REGRA_NEGOCIO_MODELO_VEICULO);
		}
	}

	public ModeloCarro buscarPeloCodigo(Long codigo) {
		return manager.find(ModeloCarro.class, codigo);
	}

}
