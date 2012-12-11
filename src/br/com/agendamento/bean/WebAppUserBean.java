package br.com.agendamento.bean;

import java.io.Serializable;

import org.bson.types.ObjectId;

/**
 * Bean de um usuário que está logado no sistema
 * 
 * @author lucas.machado
 * 
 */
public class WebAppUserBean implements Serializable {

	private static final long serialVersionUID = -1799294098213167723L;

	private ObjectId id;
	private String username;
	private String password;
	private String mailAddress;
	private String access;

	/**
	 * @param username
	 * @param password
	 * @param mailAddress
	 */
	public WebAppUserBean(String username, String password, String mailAddress) {
		super();
		this.username = username;
		this.password = password;
		this.mailAddress = mailAddress;
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
	 * @return the mailAddress
	 */
	public String getMailAddress() {
		return mailAddress;
	}

	/**
	 * @param mailAddress
	 *            the mailAddress to set
	 */
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WebAppUserBean [username=" + username + ", mailAddress="
				+ mailAddress + "]";
	}

	/**
	 * @return the access
	 */
	public String getAccess() {
		return access;
	}

	/**
	 * @param access
	 *            the access to set
	 */
	public void setAccess(String access) {
		this.access = access;
	}

	/**
	 * @return the id
	 */
	public ObjectId getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(ObjectId id) {
		this.id = id;
	}

}
