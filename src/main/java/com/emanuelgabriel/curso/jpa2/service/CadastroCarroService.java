package com.emanuelgabriel.curso.jpa2.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.emanuelgabriel.curso.jpa2.dao.CarroDAO;
import com.emanuelgabriel.curso.jpa2.modelo.Carro;
import com.emanuelgabriel.curso.jpa2.util.jpa.Transactional;

public class CadastroCarroService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CarroDAO carroDAO;
	
	@Transactional
	public void salvar(Carro carro) throws RegraNegocioException {
		
		if (carro.getPlaca() == null || carro.getPlaca().trim().equals("")) {
			throw new RegraNegocioException("A placa é obrigatória");
		}
		
		this.carroDAO.salvar(carro);
	}

}
