package br.com.agendamento.db.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.agendamento.bean.MedicoBean;
import br.com.agendamento.db.util.Dummy;

import com.google.code.morphia.mapping.Mapper;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;
import com.google.code.morphia.query.UpdateResults;

/**
 * @author lucasmachado
 * 
 */
public class MedicoDAO extends AbstractDAO {

	/**
	 * @param bean
	 * @return
	 */
	public static boolean insert(MedicoBean bean) {
		datastore.save(bean);
		return true;
	}

	/**
	 * @param bean
	 * @return
	 */
	public static boolean update(MedicoBean bean) {
		UpdateOperations<MedicoBean> ops;
		Query<MedicoBean> updateQuery = datastore.createQuery(MedicoBean.class)
				.field(Mapper.ID_KEY).equal(bean.getCrm());

		ops = datastore.createUpdateOperations(MedicoBean.class)
				.set("nome", bean.getNome()).set("contato", bean.getContato())
				.set("modificacao", bean.getModificacao())
				.set("modificador", bean.getModificador());

		UpdateResults<MedicoBean> results = datastore.update(updateQuery, ops);

		return !results.getHadError();
	}

	/**
	 * @param bean
	 * @return
	 */
	public static boolean delete(MedicoBean bean) {
		datastore.delete(bean);
		return true;
	}

	/**
	 * @return
	 */
	public static List<MedicoBean> loadMedicosList() {
		if (isDummyDatabase) {
			return Dummy.getMedicosList();
		}
		List<MedicoBean> list = datastore.find(MedicoBean.class).asList();
		if (list != null) {
			return list;
		}
		return new ArrayList<MedicoBean>();

	}

	/**
	 * @return
	 */
	public static List<String> loadMedicosStringList() {
		if (isDummyDatabase) {
			return Dummy.getStringList();
		}
		List<String> list = new ArrayList<String>();
		List<MedicoBean> beans = datastore.find(MedicoBean.class)
				.retrievedFields(true, "nome").asList();
		if (beans != null) {
			for (MedicoBean bean : beans) {
				list.add(bean.getNome());
			}
		}
		return list;
	}

}
