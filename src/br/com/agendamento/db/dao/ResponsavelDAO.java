package br.com.agendamento.db.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.agendamento.bean.ResponsavelBean;
import br.com.agendamento.db.util.Dummy;

import com.google.code.morphia.mapping.Mapper;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;
import com.google.code.morphia.query.UpdateResults;

/**
 * @author lucasmachado
 * 
 */
public class ResponsavelDAO extends AbstractDAO {

	/**
	 * @param bean
	 * @return
	 */
	public static boolean insert(ResponsavelBean bean) {
		datastore.save(bean);
		return true;
	}

	/**
	 * @param bean
	 * @return
	 */
	public static boolean update(ResponsavelBean bean) {

		UpdateOperations<ResponsavelBean> ops;
		Query<ResponsavelBean> updateQuery = datastore
				.createQuery(ResponsavelBean.class).field(Mapper.ID_KEY)
				.equal(bean.getId());

		ops = datastore.createUpdateOperations(ResponsavelBean.class)
				.set("nome", bean.getNome()).set("email", bean.getEmail())
				.set("tipo", bean.getTipo())
				.set("password", bean.getPassword())
				.set("modificacao", bean.getModificacao())
				.set("modificador", bean.getModificador());
		if (bean.getDiasSemanaTrabalha() != null) {
			ops.set("diasSemanaTrabalha", bean.getDiasSemanaTrabalha());
		}
		if (bean.getTiposExames() != null) {
			ops.set("tiposExames", bean.getTiposExames());
		}

		UpdateResults<ResponsavelBean> results = datastore.update(updateQuery,
				ops);

		return !results.getHadError();
	}

	/**
	 * @param bean
	 * @return
	 */
	public static boolean delete(ResponsavelBean bean) {
		datastore.delete(bean);
		return true;
	}

	/**
	 * @return
	 */
	public static List<ResponsavelBean> loadResponsaveisList() {
		if (isDummyDatabase) {
			return Dummy.getResponsaveisList();
		}

		List<ResponsavelBean> list = datastore.find(ResponsavelBean.class)
				.asList();
		if (list != null) {
			return list;
		}
		return new ArrayList<ResponsavelBean>();
	}

	/**
	 * Retorna uma lista com todos os dias da semana
	 * 
	 * @return uma ArrayList
	 */
	public static List<String> getWeekDays() {
		List<String> list = new ArrayList<String>();

		list.add("Segunda-feira");
		list.add("Terça-feira");
		list.add("Quarta-feira");
		list.add("Quinta-feira");
		list.add("Sexta-feira");
		list.add("Sábado");
		list.add("Domingo");

		return list;
	}

}
