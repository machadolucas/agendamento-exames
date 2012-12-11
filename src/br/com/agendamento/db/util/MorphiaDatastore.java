package br.com.agendamento.db.util;

import java.net.UnknownHostException;

import br.com.agendamento.bean.ExameBean;
import br.com.agendamento.bean.MedicoBean;
import br.com.agendamento.bean.PacienteBean;
import br.com.agendamento.bean.ResponsavelBean;
import br.com.agendamento.bean.TipoExameBean;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;

/**
 * Classe responsável por instanciar e manter a Datastore do Morphia, que faz
 * comunicação como Mongodb
 * 
 * @author lucasmachado
 * 
 */
public class MorphiaDatastore extends AbstractDatabaseConnection<Datastore> {

	private static Datastore DS;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.agendamento.db.util.AbstractDatabaseConnection#getDatabaseObject()
	 */
	@Override
	public Datastore getDatabaseObject() {
		if (DS != null) {
			return DS;
		}

		Mongo mongo = null;
		try {
			mongo = new Mongo();
			Morphia morphia = new Morphia();

			morphia.map(ExameBean.class);
			morphia.map(MedicoBean.class);
			morphia.map(PacienteBean.class);
			morphia.map(ResponsavelBean.class);
			morphia.map(TipoExameBean.class);

			DS = morphia.createDatastore(mongo, "agendamento_exames");

		} catch (UnknownHostException e) {
			e.printStackTrace();
			return null;
		}
		return DS;
	}
}
