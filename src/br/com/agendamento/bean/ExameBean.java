package br.com.agendamento.bean;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

/**
 * Bean para um exame
 * 
 * @author lucasmachado
 * 
 */
@Entity
public class ExameBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 4471384566276928434L;

	@Id
	private ObjectId id;
	private String categoria;
	private Date dataExame;

	private String tipo;
	private String paciente;
	private String medico;
	private String responsavel;

	private String descricao;

	private String status;
	private Date modificacaoStatus;
	private String modificadorStatus;

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the modificadorStatus
	 */
	public String getModificadorStatus() {
		return modificadorStatus;
	}

	/**
	 * @param modificadorStatus
	 *            the modificadorStatus to set
	 */
	public void setModificadorStatus(String modificadorStatus) {
		this.modificadorStatus = modificadorStatus;
	}

	/**
	 * @return the modificacaoStatus
	 */
	public Date getModificacaoStatus() {
		return modificacaoStatus;
	}

	/**
	 * @param modificacaoStatus
	 *            the modificacaoStatus to set
	 */
	public void setModificacaoStatus(Date modificacaoStatus) {
		this.modificacaoStatus = modificacaoStatus;
	}

	/**
	 * @return the categoria
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria
	 *            the categoria to set
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
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
		this.tipo = tipo;
	}

	/**
	 * @return the paciente
	 */
	public String getPaciente() {
		return paciente;
	}

	/**
	 * @param paciente
	 *            the paciente to set
	 */
	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}

	/**
	 * @return the medico
	 */
	public String getMedico() {
		return medico;
	}

	/**
	 * @param medico
	 *            the medico to set
	 */
	public void setMedico(String medico) {
		this.medico = medico;
	}

	/**
	 * @return the responsavel
	 */
	public String getResponsavel() {
		return responsavel;
	}

	/**
	 * @param responsavel
	 *            the responsavel to set
	 */
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	/**
	 * @return the dataExame
	 */
	public Date getDataExame() {
		return dataExame;
	}

	/**
	 * @param dataExame
	 *            the dataExame to set
	 */
	public void setDataExame(Date dataExame) {
		this.dataExame = dataExame;
	}

}
