package com.emanuelgabriel.curso.jpa2.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.emanuelgabriel.curso.jpa2.dao.AcessorioDAO;
import com.emanuelgabriel.curso.jpa2.modelo.Acessorio;
import com.emanuelgabriel.curso.jpa2.util.cdi.CDIServiceLocator;

// @FacesConverter("acessorioConverter")
// @FacesConverter(forClass = Acessorio.class, value = "acessorioConverter")
@FacesConverter(value = "acessorioConverter", forClass = Acessorio.class)
public class AcessorioConverter implements Converter {

	private AcessorioDAO acessorioDAO;

	public AcessorioConverter() {
		this.acessorioDAO = CDIServiceLocator.getBean(AcessorioDAO.class);

	}

	@SuppressWarnings("deprecation")
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Acessorio retorno = null;

		if (value != null) {
			retorno = this.acessorioDAO.buscarPeloCodigo(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Acessorio) value).getCodigo();
			String retorno = (codigo == null ? null : codigo.toString());

			return retorno;
		}

		return "";
	}

}