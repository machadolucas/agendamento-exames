package br.com.agendamento.db.dao;

import br.com.agendamento.db.util.Dummy;
import br.com.agendamento.db.util.MorphiaDatastore;

import com.google.code.morphia.Datastore;

/**
 * @author lucasmachado
 * 
 */
public abstract class AbstractDAO {

	protected static Datastore datastore = new MorphiaDatastore()
			.getDatabaseObject();

	/**
	 * Identifica se a base deve ser populada com dados de teste da classe
	 * {@link Dummy}
	 */
	protected static boolean isDummyDatabase = false;
}
