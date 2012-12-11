package br.com.agendamento.util;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.agendamento.bean.TipoResponsavelEnum;
import br.com.agendamento.bean.WebAppUserBean;
import br.com.agendamento.db.dao.LoginDAO;

/**
 * Classe auxiliar do login no sistema
 * 
 * @author lucasmachado
 * 
 */
@ManagedBean(name = "loginHelper")
@SessionScoped
public class LoginHelper implements Serializable {

	private static final long serialVersionUID = 1870747339924269023L;

	private String username;
	private String password;
	private WebAppUserBean current;

	/**
	 * Realiza login
	 * 
	 * @return outcome
	 */
	public String login() {
		current = matchLogin(username, password);

		if (current == null) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Senha incorreta!", "Tente novamente"));
			password = null;
			return (username = password = null);
		}
		return "/xhtml/index.jsf?faces-redirect=true";
	}

	private WebAppUserBean matchLogin(String username, String password) {
		String hashedPassword = Hasher.hash(password, username);

		LoginDAO.assureInitialization(username, hashedPassword);

		WebAppUserBean user = LoginDAO.select(username);

		if (user != null && user.getPassword().equals(hashedPassword)) {
			return user;
		}
		System.out.println("Wrong Password. Cannot authenticate");
		return null;

	}

	/**
	 * Faz logout do usuårio
	 * 
	 * @return
	 */
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		return "/login.jsf?faces-redirect=true";
	}

	/**
	 * @return true se usuário está logado. Do contrário, false
	 */
	public boolean isLoggedIn() {
		return current != null;
	}

	/**
	 * @return true se usuário é administrador. Do contrário, false
	 */
	public boolean isAdmin() {
		return current.getAccess().equals(
				TipoResponsavelEnum.ADMINISTRADOR.getValue())
				|| current.getAccess().equals(
						TipoResponsavelEnum.COORDENADOR.getValue());
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the current
	 */
	public WebAppUserBean getCurrent() {
		return current;
	}
}
