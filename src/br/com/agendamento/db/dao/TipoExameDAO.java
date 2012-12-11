package br.com.agendamento.db.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.agendamento.bean.TipoExameBean;
import br.com.agendamento.db.util.Dummy;

import com.google.code.morphia.mapping.Mapper;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;
import com.google.code.morphia.query.UpdateResults;

/**
 * @author lucasmachado
 * 
 */
/**
 * @author lucasmachado
 * 
 */
public class TipoExameDAO extends AbstractDAO {

	/**
	 * @param bean
	 * @return
	 */
	public static boolean insert(TipoExameBean bean) {
		datastore.save(bean);
		return true;
	}

	/**
	 * @param bean
	 * @return
	 */
	public static boolean update(TipoExameBean bean) {
		UpdateOperations<TipoExameBean> ops;
		Query<TipoExameBean> updateQuery = datastore
				.createQuery(TipoExameBean.class).field(Mapper.ID_KEY)
				.equal(bean.getId());

		ops = datastore.createUpdateOperations(TipoExameBean.class)
				.set("descricao", bean.getDescricao())
				.set("modificacao", bean.getModificacao())
				.set("modificador", bean.getModificador());

		UpdateResults<TipoExameBean> results = datastore.update(updateQuery,
				ops);

		return !results.getHadError();
	}

	/**
	 * @param bean
	 * @return
	 */
	public static boolean delete(TipoExameBean bean) {
		datastore.delete(bean);
		return true;
	}

	/**
	 * @return
	 */
	public static List<TipoExameBean> loadTiposExamesList() {
		if (isDummyDatabase) {
			return Dummy.getTiposExameList();
		}
		List<TipoExameBean> list = datastore.find(TipoExameBean.class).asList();
		if (list != null) {
			return list;
		}
		return new ArrayList<TipoExameBean>();
	}

	/**
	 * @return
	 */
	public static List<String> loadTiposExamesStringList() {
		if (isDummyDatabase) {
			return Dummy.getStringList();
		}
		List<String> list = new ArrayList<String>();
		List<TipoExameBean> beans = datastore.find(TipoExameBean.class)
				.retrievedFields(true, "nome").asList();
		if (beans != null) {
			for (TipoExameBean bean : beans) {
				list.add(bean.getNome());
			}
		}
		return list;
	}

}
