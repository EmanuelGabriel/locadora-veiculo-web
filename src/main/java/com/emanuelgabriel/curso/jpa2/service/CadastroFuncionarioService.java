package com.emanuelgabriel.curso.jpa2.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.emanuelgabriel.curso.jpa2.dao.FuncionarioDAO;
import com.emanuelgabriel.curso.jpa2.modelo.Funcionario;
import com.emanuelgabriel.curso.jpa2.util.jpa.Transactional;

public class CadastroFuncionarioService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FuncionarioDAO funcionarioDAO;
	
	@Transactional
	public void salvar(Funcionario funcionario) throws RegraNegocioException {
		
		if (funcionario.getNome() == null || funcionario.getNome().trim().equals("")) {
			throw new RegraNegocioException("O nome do funcionário é obrigatório");
		}
		
		this.funcionarioDAO.salvar(funcionario);
	}

}
