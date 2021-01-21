package com.emanuelgabriel.curso.jpa2.criteria.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.emanuelgabriel.curso.jpa2.modelo.Carro;

public class ConsultaAcessorioPorModeloCarro {

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
	public void quantidadeAlugueisPorCarro() {

		// HAVING count(a) > 1
		String jpql = "SELECT c, count(a), max(a.valorTotal), avg(a.valorTotal) FROM Carro c JOIN c.alugueis a GROUP BY c";
		List<Object[]> resultados = this.manager.createQuery(jpql, Object[].class).getResultList();

		resultados.forEach(obj -> System.out.println("\n" + ((Carro) obj[0]).getModelo().getDescricao() 
				+ "\nQuantidade: " + obj[1] + "\nValor Máximo: " + obj[2] + "\nValor Médio: " + obj[3]));

	}

	/**
	 * Buscar os acessorios de um carro por sua descrição
	 */
	@Test
	public void buscarAcessoriosPorDescricaoModeloCarro() {

		String descricao = "Fiat";
		String sql = "SELECT a.descricao FROM Carro c JOIN c.acessorios a WHERE c.modelo.descricao = :descricao ORDER BY a.descricao";
		List<String> query = this.manager.createQuery(sql, String.class).setParameter("descricao", descricao)
				.getResultList();

		for (String acessorio : query) {
			System.out.println("Acessórios: " + acessorio);
		}
	}

}
