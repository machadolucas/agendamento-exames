package br.com.agendamento.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.DateSelectEvent;

import br.com.agendamento.bean.ExameBean;
import br.com.agendamento.bean.MedicoBean;
import br.com.agendamento.bean.PacienteBean;
import br.com.agendamento.bean.ResponsavelBean;
import br.com.agendamento.bean.TipoExameBean;
import br.com.agendamento.db.dao.ExameDAO;
import br.com.agendamento.db.dao.MedicoDAO;
import br.com.agendamento.db.dao.PacienteDAO;
import br.com.agendamento.db.dao.ResponsavelDAO;
import br.com.agendamento.db.dao.TipoExameDAO;
import br.com.agendamento.util.MessageUtils;

/**
 * @author lucasmachado
 * 
 */
@ManagedBean(name = "exameViewBean")
@SessionScoped
public class ExameView extends AbstractView<ExameBean> {

	private static final long serialVersionUID = -8075236977405262215L;

	private static final String mainOutcome = "/xhtml/exame/main.jsf?faces-redirect=true";

	private Date selectedExameDate;
	private List<TipoExameBean> tipoExamesList = new ArrayList<TipoExameBean>();
	private List<PacienteBean> pacientesList = new ArrayList<PacienteBean>();
	private List<MedicoBean> medicosList = new ArrayList<MedicoBean>();
	private List<ResponsavelBean> responsaveisList = new ArrayList<ResponsavelBean>();
	private List<ResponsavelBean> originalResponsaveisList = new ArrayList<ResponsavelBean>();

	@Override
	void doInit() {
		reset();
	}

	@Override
	String doAdd() {
		this.bean = new ExameBean();
		this.tipoExamesList = TipoExameDAO.loadTiposExamesList();
		this.pacientesList = PacienteDAO.loadPacientesList();
		this.medicosList = MedicoDAO.loadMedicosList();
		this.originalResponsaveisList = ResponsavelDAO.loadResponsaveisList();
		return NavigationOutcomeTypes.ADD;
	}

	@Override
	String doEdit() {
		this.tipoExamesList = TipoExameDAO.loadTiposExamesList();
		this.pacientesList = PacienteDAO.loadPacientesList();
		this.medicosList = MedicoDAO.loadMedicosList();
		this.originalResponsaveisList = ResponsavelDAO.loadResponsaveisList();

		return NavigationOutcomeTypes.EDIT;
	}

	@Override
	String doSave() {
		ExameDAO.insert(bean);
		reset();
		return mainOutcome;
	}

	@Override
	String doUpdate() {
		ExameDAO.update(bean);
		reset();
		return mainOutcome;
	}

	@Override
	void doDelete() {
		ExameDAO.delete(bean);
		reset();

	}

	/**
	 * Listener para mudança de responsáveis a partir da data
	 * 
	 * @param event
	 */
	public void listenerChangeData(DateSelectEvent event) {
		if (event.getDate() != null) {
			if (this.bean.getCategoria() != null
					&& this.bean.getCategoria().equals("Vip")) {
				this.responsaveisList.clear();
				this.responsaveisList.addAll(this.originalResponsaveisList);
			} else {
				this.bean.setDataExame(event.getDate());
				this.responsaveisList = filterResponsaveisByDate(this.originalResponsaveisList);
				if (this.responsaveisList.isEmpty()) {
					MessageUtils
							.showWarnInGrowl(
									"Atenção",
									"Não há responsáveis disponíveis na data escolhida. Escolha outra data, ou se necessário, altere a categoria para VIP");
				} else {
					this.responsaveisList = filterResponsaveisByTipo(this.responsaveisList);
				}
			}
		}
	}

	/**
	 * Listener para mudança de categoria, que reage mudando os responsáveis
	 * disponíveis para todos, ou os restringidos ao horário selecionado.
	 */
	public void listenerChangeCategoria() {
		if (this.bean.getCategoria().equals("Vip")) {
			this.responsaveisList.clear();
			this.responsaveisList.addAll(this.originalResponsaveisList);
		} else if (this.bean.getCategoria().equals("Normal")) {
			this.responsaveisList = filterResponsaveisByDate(this.originalResponsaveisList);
			this.responsaveisList = filterResponsaveisByTipo(this.responsaveisList);
			if (this.responsaveisList.isEmpty()) {
				MessageUtils
						.showWarnInGrowl(
								"Atenção",
								"Não há responsáveis disponíveis para essa data e tipo de exame. Se for necessário agendar com esses dados, altere a categoria para VIP");
			}
		}
	}

	/**
	 * @param responsaveisList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<ResponsavelBean> filterResponsaveisByDate(
			List<ResponsavelBean> responsaveisList) {

		List<ResponsavelBean> result = new ArrayList<ResponsavelBean>();
		if (this.selectedExameDate != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(bean.getDataExame());
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
			String day = "";
			switch (dayOfWeek) {
			case 2:
				day = "Segunda-feira";
				break;
			case 3:
				day = "Terça-feira";
				break;
			case 4:
				day = "Quarta-feira";
				break;
			case 5:
				day = "Quinta-feira";
				break;
			case 6:
				day = "Sexta-feira";
				break;
			case 7:
				day = "Sábado";
				break;
			default:
				day = "Domingo";
				break;
			}

			for (int i = 0; i < responsaveisList.size(); i++) {
				if (responsaveisList.get(i).getDiasSemanaTrabalha() != null
						&& Arrays
								.asList(responsaveisList.get(i)
										.getDiasSemanaTrabalha()).contains(day)) {
					result.add(responsaveisList.get(i));
				}
			}

			return result;
		}
		result.addAll(responsaveisList);
		return result;
	}

	/**
	 * Listener para mudança de tipo de exame, que reage mudando os responsáveis
	 * que se relacionam com o tipo
	 */
	public void listenerChangeTipo() {
		if (this.bean.getCategoria() != null
				&& this.bean.getCategoria().equals("Vip")) {
			this.responsaveisList.clear();
			this.responsaveisList.addAll(this.originalResponsaveisList);
		} else {
			this.responsaveisList = filterResponsaveisByDate(this.originalResponsaveisList);
			this.responsaveisList = filterResponsaveisByTipo(this.responsaveisList);
			if (this.responsaveisList.isEmpty()) {
				MessageUtils.showWarnInGrowl("Atenção",
						"Não há responsáveis disponíveis nessa data para o exame do tipo "
								+ this.bean.getTipo());
			}
		}
	}

	/**
	 * @param responsaveisList
	 * @return
	 */
	private List<ResponsavelBean> filterResponsaveisByTipo(
			List<ResponsavelBean> responsaveisList) {
		List<ResponsavelBean> result = new ArrayList<ResponsavelBean>();
		if (this.bean.getTipo() != null) {
			for (int i = 0; i < responsaveisList.size(); i++) {
				if (responsaveisList.get(i).getTiposExames() != null
						&& responsaveisList.get(i).getTiposExames()
								.contains(bean.getTipo())) {
					result.add(responsaveisList.get(i));
				}
			}

			return result;
		}
		result.addAll(responsaveisList);
		return result;
	}

	/**
	 * Atualiza o registro de mudança de status
	 */
	public void updateStatus() {
		Date now = new Date();
		this.bean.setModificacaoStatus(now);
		this.bean.setModificadorStatus(loginHelper.getCurrent().getUsername());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.agendamento.view.AbstractView#cancel()
	 */
	@Override
	public String cancel() {
		reset();
		return mainOutcome;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.agendamento.view.AbstractView#loadMain()
	 */
	@Override
	public String loadMain() {
		reset();
		return mainOutcome;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.agendamento.view.AbstractView#reset()
	 */
	@Override
	public void reset() {
		this.list = ExameDAO.loadExamesList();
		this.bean = new ExameBean();
	}

	/**
	 * @return the tipoExamesList
	 */
	public List<TipoExameBean> getTipoExamesList() {
		return tipoExamesList;
	}

	/**
	 * @param tipoExamesList
	 *            the tipoExamesList to set
	 */
	public void setTipoExamesList(List<TipoExameBean> tipoExamesList) {
		this.tipoExamesList = tipoExamesList;
	}

	/**
	 * @return the pacientesList
	 */
	public List<PacienteBean> getPacientesList() {
		return pacientesList;
	}

	/**
	 * @param pacientesList
	 *            the pacientesList to set
	 */
	public void setPacientesList(List<PacienteBean> pacientesList) {
		this.pacientesList = pacientesList;
	}

	/**
	 * @return the medicosList
	 */
	public List<MedicoBean> getMedicosList() {
		return medicosList;
	}

	/**
	 * @param medicosList
	 *            the medicosList to set
	 */
	public void setMedicosList(List<MedicoBean> medicosList) {
		this.medicosList = medicosList;
	}

	/**
	 * @return the responsaveisList
	 */
	public List<ResponsavelBean> getResponsaveisList() {
		return responsaveisList;
	}

	/**
	 * @param responsaveisList
	 *            the responsaveisList to set
	 */
	public void setResponsaveisList(List<ResponsavelBean> responsaveisList) {
		this.responsaveisList = responsaveisList;
	}

	/**
	 * @return the originalResponsaveisList
	 */
	public List<ResponsavelBean> getOriginalResponsaveisList() {
		return originalResponsaveisList;
	}

	/**
	 * @param originalResponsaveisList
	 *            the originalResponsaveisList to set
	 */
	public void setOriginalResponsaveisList(
			List<ResponsavelBean> originalResponsaveisList) {
		this.originalResponsaveisList = originalResponsaveisList;
	}

	/**
	 * @return the selectedExameDate
	 */
	public Date getSelectedExameDate() {
		return selectedExameDate;
	}

	/**
	 * @param selectedExameDate
	 *            the selectedExameDate to set
	 */
	public void setSelectedExameDate(Date selectedExameDate) {
		this.selectedExameDate = selectedExameDate;
	}

}
