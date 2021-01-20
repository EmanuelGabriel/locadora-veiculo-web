package com.emanuelgabriel.curso.jpa2.modelolazy;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.emanuelgabriel.curso.jpa2.dao.FabricanteDAO;
import com.emanuelgabriel.curso.jpa2.modelo.Fabricante;

public class LazyFabricanteDataModel extends LazyDataModel<Fabricante> implements Serializable {

	private static final long serialVersionUID = 1L;

	private FabricanteDAO fabricanteDAO;

	public LazyFabricanteDataModel(FabricanteDAO fabricanteDAO) {
		this.fabricanteDAO = fabricanteDAO;
	}

	@Override
	public List<Fabricante> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, String> filters) {
		List<Fabricante> fabricantes = this.fabricanteDAO.buscarComPaginacao(first, pageSize);
		this.setRowCount(this.fabricanteDAO.encontrarQuantidadeDeFabricantes().intValue());
		return fabricantes;
	}

}
