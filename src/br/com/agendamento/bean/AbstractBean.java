package br.com.agendamento.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Classe mãe dos Beans
 * 
 * @author lucas.machado
 * 
 */
public abstract class AbstractBean implements Serializable {

	private static final long serialVersionUID = -1853389075159092278L;
	private Date criacao;
	private String criador;
	private Date modificacao;
	private String modificador;

	/**
	 * Construtor padrão
	 */
	public AbstractBean() {
		super();
	}

	/**
	 * @return the criacao
	 */
	public Date getCriacao() {
		return criacao;
	}

	/**
	 * @param criacao
	 *            the criacao to set
	 */
	public void setCriacao(Date criacao) {
		this.criacao = criacao;
	}

	/**
	 * @return the criador
	 */
	public String getCriador() {
		return criador;
	}

	/**
	 * @param criador
	 *            the criador to set
	 */
	public void setCriador(String criador) {
		this.criador = criador;
	}

	/**
	 * @return the modificacao
	 */
	public Date getModificacao() {
		return modificacao;
	}

	/**
	 * @param modificacao
	 *            the modificacao to set
	 */
	public void setModificacao(Date modificacao) {
		this.modificacao = modificacao;
	}

	/**
	 * @return the modificador
	 */
	public String getModificador() {
		return modificador;
	}

	/**
	 * @param modificador
	 *            the modificador to set
	 */
	public void setModificador(String modificador) {
		this.modificador = modificador;
	}

}
