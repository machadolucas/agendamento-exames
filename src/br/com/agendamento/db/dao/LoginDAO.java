package br.com.agendamento.db.dao;

import java.util.Date;

import br.com.agendamento.bean.ResponsavelBean;
import br.com.agendamento.bean.TipoResponsavelEnum;
import br.com.agendamento.bean.WebAppUserBean;
import br.com.agendamento.util.Hasher;

/**
 * Classe de DAO para o login da aplicação
 * 
 * @author lucasmachado
 * 
 */
public class LoginDAO extends AbstractDAO {

	/**
	 * Procura por um usu·rio na base de dados pelo email e retorna o possÌvel
	 * WebAppUserBean se ele existir. Caso contr·rio, retorna null
	 * 
	 * @param email
	 * @return
	 */
	public static WebAppUserBean select(String email) {
		if (isDummyDatabase) {
			WebAppUserBean user = new WebAppUserBean("admin", Hasher.hash(
					"admin", "admin"), "admin@mail.com");
			user.setAccess(TipoResponsavelEnum.ADMINISTRADOR.getValue());
			return user;
		}
		ResponsavelBean bean = datastore.find(ResponsavelBean.class, "email",
				email).get();
		if (bean == null) {
			return null;
		}

		WebAppUserBean user = new WebAppUserBean(bean.getNome(),
				bean.getPassword(), bean.getEmail());
		user.setId(bean.getId());
		user.setAccess(bean.getTipo());
		return user;
	}

	/**
	 * Certifica que existem usu·rios na base. Se n„o existir, cria um com os
	 * par‚metros dados.
	 * 
	 * @param username
	 * @param hashedPassword
	 */
	public static void assureInitialization(String username,
			String hashedPassword) {

		if (!isDummyDatabase) {
			ResponsavelBean bean = datastore.find(ResponsavelBean.class).get();
			if (bean == null) {

				System.out
						.println("There are no registered users in the system. Creating one with email "
								+ username);
				ResponsavelBean newUser = new ResponsavelBean();

				newUser.setCriacao(new Date());
				newUser.setCriador("Sistema");
				newUser.setNome(username);
				newUser.setEmail(username);
				newUser.setPassword(hashedPassword);
				newUser.setTipo(TipoResponsavelEnum.ADMINISTRADOR.getValue());

				datastore.save(newUser);
			}
		}
	}

}
