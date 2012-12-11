package br.com.agendamento.db.util;

/**
 * Classe respons‡vel por instanciar objetos de conex‹o com a base de dados
 * 
 * @author lucasmachado
 * @param <N>
 *            Objeto que representa a conex‹o utilizada para manipular o banco
 *            de dados
 * 
 */
public abstract class AbstractDatabaseConnection<N> {

	/**
	 * Retorna o objeto repsons‡vel por manipular a base de dados.
	 * 
	 * @return um objeto de conex‹o de base de dados
	 */
	public abstract N getDatabaseObject();
}
