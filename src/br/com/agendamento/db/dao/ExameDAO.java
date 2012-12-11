package br.com.agendamento.db.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.agendamento.bean.ExameBean;
import br.com.agendamento.db.util.Dummy;

import com.google.code.morphia.mapping.Mapper;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;
import com.google.code.morphia.query.UpdateResults;

/**
 * @author lucasmachado
 * 
 */
public class ExameDAO extends AbstractDAO {

	/**
	 * @param bean
	 * @return
	 */
	public static boolean insert(ExameBean bean) {
		datastore.save(bean);
		return true;
	}

	/**
	 * @param bean
	 * @return
	 */
	public static boolean update(ExameBean bean) {
		UpdateOperations<ExameBean> ops;
		Query<ExameBean> updateQuery = datastore.createQuery(ExameBean.class)
				.field(Mapper.ID_KEY).equal(bean.getId());

		ops = datastore.createUpdateOperations(ExameBean.class)
				.set("categoria", bean.getCategoria())
				.set("dataExame", bean.getDataExame())
				.set("tipo", bean.getTipo())
				.set("paciente", bean.getPaciente())
				.set("medico", bean.getMedico())
				.set("responsavel", bean.getResponsavel())
				.set("status", bean.getStatus())
				.set("modificacaoStatus", bean.getModificacaoStatus())
				.set("modificadorStatus", bean.getModificadorStatus())
				.set("descricao", bean.getDescricao())
				.set("modificacao", bean.getModificacao())
				.set("modificador", bean.getModificador());

		UpdateResults<ExameBean> results = datastore.update(updateQuery, ops);

		return !results.getHadError();
	}

	/**
	 * @param bean
	 * @return
	 */
	public static boolean updateFromSchedule(ExameBean bean) {
		UpdateOperations<ExameBean> ops;
		Query<ExameBean> updateQuery = datastore.createQuery(ExameBean.class)
				.field(Mapper.ID_KEY).equal(bean.getId());

		ops = datastore.createUpdateOperations(ExameBean.class)
				.set("dataExame", bean.getDataExame())
				.set("status", bean.getStatus())
				.set("modificacaoStatus", bean.getModificacaoStatus())
				.set("modificadorStatus", bean.getModificadorStatus())
				.set("modificacao", bean.getModificacao())
				.set("modificador", bean.getModificador());

		UpdateResults<ExameBean> results = datastore.update(updateQuery, ops);

		return !results.getHadError();
	}

	/**
	 * @param bean
	 * @return
	 */
	public static boolean delete(ExameBean bean) {
		datastore.delete(bean);
		return true;
	}

	/**
	 * @return
	 */
	public static List<ExameBean> loadExamesList() {
		if (isDummyDatabase) {
			return Dummy.getExamesList();
		}
		List<ExameBean> list = datastore.find(ExameBean.class).asList();
		if (list != null) {
			return list;
		}
		return new ArrayList<ExameBean>();
	}

	/**
	 * @param start
	 * @param end
	 * @return
	 */
	public static List<ExameBean> loadExamesListWithinDate(Date start, Date end) {
		if (isDummyDatabase) {
			return Dummy.getExamesList();
		}
		Query<ExameBean> query = datastore.createQuery(ExameBean.class);
		query.field("dataExame").greaterThanOrEq(start);
		query.field("dataExame").lessThanOrEq(end);

		List<ExameBean> list = query.asList();
		if (list != null) {
			return list;
		}
		return new ArrayList<ExameBean>();
	}

}
