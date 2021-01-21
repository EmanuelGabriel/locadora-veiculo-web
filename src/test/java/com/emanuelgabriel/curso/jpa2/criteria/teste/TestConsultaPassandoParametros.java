package com.emanuelgabriel.curso.jpa2.criteria.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.emanuelgabriel.curso.jpa2.modelo.Categoria;
import com.emanuelgabriel.curso.jpa2.modelo.ModeloCarro;

public class TestConsultaPassandoParametros {

	private static EntityManagerFactory factory;

	private EntityManager manager;

	@BeforeClass
	public static void init() {
		factory = Persistence.createEntityManagerFactory("locadoraVeiculoPU");
	}

	@Before
	public void setUp() {
		this.manager = factory.createEntityManager();
	}

	@After
	public void tearDown() {
		this.manager.close();
	}

	@Test
	public void listarTodosModelosCarro() {

		String jpql = "SELECT mc FROM ModeloCarro mc JOIN FETCH mc.fabricante ORDER BY mc.codigo";
		TypedQuery<ModeloCarro> query = this.manager.createQuery(jpql, ModeloCarro.class);
		List<ModeloCarro> lista = query.getResultList();

		lista.forEach(mc -> {
			System.out.println(mc.getCodigo() + " - Tipo/Categoria: " + mc.getCategoria().name() + " - Descrição: " + mc.getDescricao() + " - Nome/Fabricante: "
					+ mc.getFabricante().getNome());

		});

	}

	@Test
	public void buscarTipoCategoriaPeloModeloCarro() {

		String jpql = "SELECT mc FROM ModeloCarro mc WHERE mc.categoria = :tipoCategoria";
		TypedQuery<ModeloCarro> query = this.manager.createQuery(jpql, ModeloCarro.class)
				.setParameter("tipoCategoria", Categoria.ESPORTIVO);

		List<ModeloCarro> carros = query.getResultList();
		carros.forEach(c -> System.out.println("Tipo de Categoria: " + c.getCategoria().name() + "\nFabricante: " + c.getDescricao()));

	}

	@Test
	public void buscarNomeFabricantePeloModeloCarro() {

		String jpql = "SELECT mc FROM ModeloCarro mc JOIN FETCH mc.fabricante f WHERE lower(mc.descricao) LIKE lower(concat('%', :descricao, '%'))";
		
		String descricaoModeloCarro = "corolla";
		TypedQuery<ModeloCarro> query = this.manager.createQuery(jpql, ModeloCarro.class)
				.setParameter("descricao", descricaoModeloCarro);
		
		var carros = query.getResultList();
		carros.forEach(c -> System.out.println("\nCód.: " + c.getCodigo() + "\nModelo do Carro: " + c.getDescricao() + "\nFabricante: " + c.getFabricante().getNome()));
		 
	}
	
	
}
