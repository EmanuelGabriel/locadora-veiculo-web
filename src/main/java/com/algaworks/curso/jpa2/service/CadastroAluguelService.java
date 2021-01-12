package com.algaworks.curso.jpa2.service;

import java.io.Serializable;
import java.util.Calendar;

import javax.inject.Inject;

import com.algaworks.curso.jpa2.dao.AluguelDAO;
import com.algaworks.curso.jpa2.modelo.Aluguel;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class CadastroAluguelService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private AluguelDAO aluguelDAO;
	
	@Transactional
	public void salvar(Aluguel aluguel) throws RegraNegocioException {
		
		if (aluguel.getCarro() == null) {
			throw new RegraNegocioException("O carro é obrigatório");
		}
		
		aluguel.setDataPedido(Calendar.getInstance());
		
		this.aluguelDAO.salvar(aluguel);
	}

}
