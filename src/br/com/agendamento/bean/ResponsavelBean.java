package br.com.agendamento.bean;

import java.io.Serializable;
import java.util.List;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

/**
 * Bean para os responsáveis pelos exames e usuários do sistema
 * 
 */
@Entity
public class ResponsavelBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = -2962037262700352534L;

	@Id
	private ObjectId id;

	private String nome;
	private String password;
	private String email;
	private String tipo;
	private List<String> diasSemanaTrabalha;

	private List<String> tiposExames;

	/**
	 * Construtor usado para se obter uma entidade de Responsavel a partir do
	 * usuário logado no sistema
	 * 
	 * @param current
	 */
	public ResponsavelBean(WebAppUserBean current) {
		this.nome = current.getUsername();
		this.password = current.getPassword();
		this.email = current.getMailAddress();
	}

	/**
	 * Construtor padrão
	 */
	public ResponsavelBean() {
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResponsavelBean other = (ResponsavelBean) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo
	 *            the tipo to set
	 */
	public void setTipo(String tipo) {
		TipoResponsavelEnum tipoEnum = TipoResponsavelEnum.getTypeByValue(tipo);
		this.tipo = tipoEnum.getValue();
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

	/**
	 * @return the tiposExames
	 */
	public List<String> getTiposExames() {
		return tiposExames;
	}

	/**
	 * @param tiposExames
	 *            the tiposExames to set
	 */
	public void setTiposExames(List<String> tiposExames) {
		this.tiposExames = tiposExames;
	}

	/**
	 * @return the diasSemanaTrabalha
	 */
	public List<String> getDiasSemanaTrabalha() {
		return diasSemanaTrabalha;
	}

	/**
	 * @param diasSemanaTrabalha
	 *            the diasSemanaTrabalha to set
	 */
	public void setDiasSemanaTrabalha(List<String> diasSemanaTrabalha) {
		this.diasSemanaTrabalha = diasSemanaTrabalha;
	}

}
