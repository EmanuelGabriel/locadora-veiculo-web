package com.emanuelgabriel.curso.jpa2.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.emanuelgabriel.curso.jpa2.modelo.Motorista;
import com.emanuelgabriel.curso.jpa2.modelo.Sexo;
import com.emanuelgabriel.curso.jpa2.service.CadastroMotoristaService;
import com.emanuelgabriel.curso.jpa2.service.RegraNegocioException;
import com.emanuelgabriel.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroMotoristaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Motorista motorista;
	
	@Inject
	private CadastroMotoristaService cadastroMotoristaService;
	
	private List<Sexo> sexos;
	
	@PostConstruct
	public void inicializar() {
		this.limpar();
		this.sexos = Arrays.asList(Sexo.values());
	}
	
	public void salvar() {
		try {
			this.cadastroMotoristaService.salvar(motorista);
			FacesUtil.addSuccessMessage("Motorista salvo com sucesso!");
		} catch (RegraNegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
		
		this.limpar();
	}
	
	public void limpar() {
		this.motorista = new Motorista();
	}

	public Motorista getMotorista() {
		return motorista;
	}
	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}
	
	public List<Sexo> getSexos() {
		return sexos;
	}

}
