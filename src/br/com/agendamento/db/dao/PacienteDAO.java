package br.com.agendamento.db.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.agendamento.bean.PacienteBean;
import br.com.agendamento.db.util.Dummy;

import com.google.code.morphia.mapping.Mapper;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;
import com.google.code.morphia.query.UpdateResults;

/**
 * @author lucasmachado
 * 
 */
public class PacienteDAO extends AbstractDAO {

	/**
	 * @param bean
	 * @return
	 */
	public static boolean insert(PacienteBean bean) {
		datastore.save(bean);
		return true;
	}

	/**
	 * @param bean
	 * @return
	 */
	public static boolean update(PacienteBean bean) {
		UpdateOperations<PacienteBean> ops;
		Query<PacienteBean> updateQuery = datastore
				.createQuery(PacienteBean.class).field(Mapper.ID_KEY)
				.equal(bean.getId());

		ops = datastore.createUpdateOperations(PacienteBean.class)
				.set("nome", bean.getNome()).set("CPF", bean.getCPF())
				.set("RG", bean.getRG())
				.set("dataNascimento", bean.getDataNascimento())
				.set("telefone", bean.getTelefone())
				.set("email", bean.getEmail()).set("rua", bean.getRua())
				.set("numero", bean.getNumero())
				.set("complemento", bean.getComplemento())
				.set("CEP", bean.getCEP()).set("cidade", bean.getCidade())
				.set("estado", bean.getEstado())
				.set("nomeResponsavel", bean.getNomeResponsavel())
				.set("documentoResponsavel", bean.getDocumentoResponsavel())
				.set("emailResponsavel", bean.getEmailResponsavel())
				.set("nomeMedicoResponsavel", bean.getNomeMedicoResponsavel())
				.set("modificacao", bean.getModificacao())
				.set("modificador", bean.getModificador());

		UpdateResults<PacienteBean> results = datastore
				.update(updateQuery, ops);

		return !results.getHadError();
	}

	/**
	 * @param bean
	 * @return
	 */
	public static boolean delete(PacienteBean bean) {
		datastore.delete(bean);
		return true;
	}

	/**
	 * @return
	 */
	public static List<PacienteBean> loadPacientesList() {
		if (isDummyDatabase) {
			return Dummy.getPacientesList();
		}
		List<PacienteBean> list = datastore.find(PacienteBean.class).asList();
		if (list != null) {
			return list;
		}
		return new ArrayList<PacienteBean>();
	}

}
