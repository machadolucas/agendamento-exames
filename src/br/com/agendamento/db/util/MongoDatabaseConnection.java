package br.com.agendamento.db.util;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.Mongo;

/**
 * Classe respons‡vel por instanciar objetos de conex‹o com a base de dados para
 * o mongodb.
 * 
 * @author lucasmachado
 * 
 */
public class MongoDatabaseConnection extends AbstractDatabaseConnection<DB> {

	private static DB databaseObject = null;

	@Deprecated
	@Override
	public DB getDatabaseObject() {

		if (databaseObject != null) {
			return databaseObject;
		}

		Mongo mongo = null;
		try {
			mongo = new Mongo();
			databaseObject = mongo.getDB("agendamento_exames");
			return databaseObject;
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return null;
		}
	}
}
