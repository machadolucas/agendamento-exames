package br.com.agendamento.bean;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

/**
 * Bean para pacientes
 * 
 */
@Entity
public class PacienteBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 3231806233809937667L;

	@Id
	private ObjectId id;
	private String nome;
	private String CPF;
	private String RG;
	private Date dataNascimento;

	private String telefone;
	private String email;
	private String rua;
	private String numero;
	private String complemento;
	private String CEP;
	private String cidade;
	private String estado;

	private String nomeResponsavel;
	private String documentoResponsavel;
	private String emailResponsavel;

	private String nomeMedicoResponsavel;

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
	 * @return the cPF
	 */
	public String getCPF() {
		return CPF;
	}

	/**
	 * @param cPF
	 *            the cPF to set
	 */
	public void setCPF(String cPF) {
		CPF = cPF;
	}

	/**
	 * @return the rG
	 */
	public String getRG() {
		return RG;
	}

	/**
	 * @param rG
	 *            the rG to set
	 */
	public void setRG(String rG) {
		RG = rG;
	}

	/**
	 * @return the dataNascimento
	 */
	public Date getDataNascimento() {
		return dataNascimento;
	}

	/**
	 * @param dataNascimento
	 *            the dataNascimento to set
	 */
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	/**
	 * @return the telefone
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * @param telefone
	 *            the telefone to set
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/**
	 * @return the rua
	 */
	public String getRua() {
		return rua;
	}

	/**
	 * @param rua
	 *            the rua to set
	 */
	public void setRua(String rua) {
		this.rua = rua;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the complemento
	 */
	public String getComplemento() {
		return complemento;
	}

	/**
	 * @param complemento
	 *            the complemento to set
	 */
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	/**
	 * @return the cidade
	 */
	public String getCidade() {
		return cidade;
	}

	/**
	 * @param cidade
	 *            the cidade to set
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
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

	/**
	 * @return the nomeResponsavel
	 */
	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	/**
	 * @param nomeResponsavel
	 *            the nomeResponsavel to set
	 */
	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	/**
	 * @return the cEP
	 */
	public String getCEP() {
		return CEP;
	}

	/**
	 * @param cEP
	 *            the cEP to set
	 */
	public void setCEP(String cEP) {
		CEP = cEP;
	}

	/**
	 * @return the documentoResponsavel
	 */
	public String getDocumentoResponsavel() {
		return documentoResponsavel;
	}

	/**
	 * @param documentoResponsavel
	 *            the documentoResponsavel to set
	 */
	public void setDocumentoResponsavel(String documentoResponsavel) {
		this.documentoResponsavel = documentoResponsavel;
	}

	/**
	 * @return the emailResponsavel
	 */
	public String getEmailResponsavel() {
		return emailResponsavel;
	}

	/**
	 * @param emailResponsavel
	 *            the emailResponsavel to set
	 */
	public void setEmailResponsavel(String emailResponsavel) {
		this.emailResponsavel = emailResponsavel;
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
	 * @return the nomeMedicoResponsavel
	 */
	public String getNomeMedicoResponsavel() {
		return nomeMedicoResponsavel;
	}

	/**
	 * @param nomeMedicoResponsavel
	 *            the nomeMedicoResponsavel to set
	 */
	public void setNomeMedicoResponsavel(String nomeMedicoResponsavel) {
		this.nomeMedicoResponsavel = nomeMedicoResponsavel;
	}

}
