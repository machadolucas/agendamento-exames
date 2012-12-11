package br.com.agendamento.db.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.agendamento.bean.ExameBean;
import br.com.agendamento.bean.MedicoBean;
import br.com.agendamento.bean.PacienteBean;
import br.com.agendamento.bean.ResponsavelBean;
import br.com.agendamento.bean.TipoExameBean;

@SuppressWarnings("javadoc")
/**
 * Classe que retorna dados bestas, apenas para testes
 * 
 * @author lucasmachado
 * 
 */
public class Dummy {

	public static List<String> getStringList() {
		List<String> list = new ArrayList<String>();
		char ch = 'a';
		for (int i = 1; i < 20; i++, ch++) {
			list.add(ch + (char) (ch + 1) + (char) (ch + 2) + (char) (ch + 3)
					+ i + "txt");
		}
		return list;
	}

	public static List<ExameBean> getExamesList() {
		List<ExameBean> list = new ArrayList<ExameBean>();
		char ch = 'a';
		for (int i = 1; i < 20; i++, ch++) {
			ExameBean bean = new ExameBean();
			bean.setCategoria("Normal");
			bean.setCriador("admin");
			bean.setCriacao(new Date(new Date().getTime() + i * 3000));
			bean.setDataExame(new Date(new Date().getTime() + i * 2000));
			bean.setDescricao("descrpt" + i + ch);
			bean.setStatus("Agendado");
			list.add(bean);
		}
		return list;
	}

	public static List<MedicoBean> getMedicosList() {
		List<MedicoBean> list = new ArrayList<MedicoBean>();
		char ch = 'a';
		for (int i = 1; i < 20; i++, ch++) {
			MedicoBean bean = new MedicoBean();
			bean.setNome((char) (ch + 1) + (char) (ch + 2) + (char) (ch + 3)
					+ "name");
			bean.setCrm("" + i + (i + 1) + (i + 2) + (i + 3));
			bean.setContato(bean.getNome() + "@mail.com");
			bean.setModificador("admin");
			bean.setModificacao(new Date());
			list.add(bean);
		}
		return list;
	}

	public static List<ResponsavelBean> getResponsaveisList() {
		List<ResponsavelBean> list = new ArrayList<ResponsavelBean>();
		char ch = 'a';
		for (int i = 1; i < 20; i++, ch++) {
			ResponsavelBean bean = new ResponsavelBean();
			bean.setNome((char) (ch + 1) + (char) (ch + 2) + (char) (ch + 3)
					+ "name");
			bean.setPassword("admin");
			bean.setEmail(bean.getNome() + "@mail.com");
			list.add(bean);
		}
		ResponsavelBean bean = new ResponsavelBean();
		bean.setNome("admin");
		bean.setPassword("admin");
		bean.setEmail("admin@mail.com");
		bean.setTipo("Administrador");
		list.add(bean);

		return list;
	}

	/**
	 * @return
	 */
	public static List<TipoExameBean> getTiposExameList() {
		List<TipoExameBean> list = new ArrayList<TipoExameBean>();
		char ch = 'a';
		for (int i = 1; i < 20; i++, ch++) {
			TipoExameBean bean = new TipoExameBean();
			bean.setNome((char) (ch + 1) + (char) (ch + 2) + (char) (ch + 3)
					+ "name");
			bean.setDescricao("description");
			list.add(bean);
		}

		return list;
	}

	/**
	 * @return
	 */
	public static List<PacienteBean> getPacientesList() {
		List<PacienteBean> list = new ArrayList<PacienteBean>();
		char ch = 'a';
		for (int i = 1; i < 20; i++, ch++) {
			PacienteBean bean = new PacienteBean();
			bean.setNome((char) (ch + 1) + (char) (ch + 2) + (char) (ch + 3)
					+ "name");
			bean.setEmail(bean.getNome() + "@mail.com");
			bean.setCPF(new BigDecimal(Math.random()).toPlainString());
			list.add(bean);
		}

		return list;
	}
}
