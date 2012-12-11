package br.com.agendamento.view;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntrySelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import br.com.agendamento.bean.ExameBean;
import br.com.agendamento.bean.WebAppUserBean;
import br.com.agendamento.db.dao.ExameDAO;
import br.com.agendamento.util.LazyScheduleModelImpl;
import br.com.agendamento.util.LoginHelper;

/**
 * @author lucasmachado
 * 
 */
@ManagedBean
@SessionScoped
public class ScheduleController {

	private LazyScheduleModelImpl lazyEventModel;

	private ScheduleEvent event = new DefaultScheduleEvent();

	ExameBean editedBean;

	private static WebAppUserBean currentUser;

	/**
	 * Construtor padrão
	 */
	public ScheduleController() {
		lazyEventModel = new LazyScheduleModelImpl();
		loadCurrentUser();
	}

	/**
	 * Carrega o usuário atualmente logado da sessão do sistema
	 */
	private void loadCurrentUser() {
		if (currentUser == null) {
			LoginHelper loginHelper = (LoginHelper) FacesContext
					.getCurrentInstance().getExternalContext().getSessionMap()
					.get("loginHelper");
			currentUser = loginHelper.getCurrent();
		}
	}

	/**
	 * Listener para a selecão de um evento, que prepara o bean para mostrar
	 * detalhes
	 * 
	 * @param selectEvent
	 */
	public void onEventSelect(ScheduleEntrySelectEvent selectEvent) {
		editedBean = new ExameBean();
		event = selectEvent.getScheduleEvent();
		ExameBean bean = (ExameBean) event.getData();
		editedBean.setId(bean.getId());
		editedBean.setStatus(bean.getStatus());
		editedBean.setDataExame(bean.getDataExame());
		editedBean.setModificacaoStatus(bean.getModificacaoStatus());
		editedBean.setModificadorStatus(bean.getModificadorStatus());
	}

	/**
	 * Listener para a movimentação de um evento, que altera o evento de data.
	 * 
	 * @param event
	 */
	public void onEventMove(ScheduleEntryMoveEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Data de exame" + event.getScheduleEvent().getTitle()
						+ " alterada "
						+ event.getScheduleEvent().getStartDate(),
				"Alterada em" + event.getDayDelta() + " dias. Minute delta:"
						+ event.getMinuteDelta());

		addMessage(message);
	}

	/**
	 * Listener para atualizar um evento
	 * 
	 * @param actionEvent
	 */
	public void updateEvent(ActionEvent actionEvent) {
		if (this.event != null) {

			ExameBean bean = (ExameBean) event.getData();
			if (editedBean.getDataExame().equals(bean.getDataExame())
					&& editedBean.getStatus().equals(bean.getStatus())) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO, "Nenhum dado alterado",
						"Não houve mudanças a serem salvas.");
				addMessage(message);
				return;
			}

			Date now = new Date();
			editedBean.setModificacao(now);
			editedBean.setModificador(currentUser.getUsername());

			if (ExameDAO.updateFromSchedule(editedBean)) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO, "Sucesso!",
						"As alterações do exame foram salvas com sucesso");
				addMessage(message);
			} else {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Erro!",
						"Ocorreu um erro ao tentar atualizar os dados do exame");
				addMessage(message);
			}

		}
	}

	/**
	 * Listener para alteração do status do exame
	 * 
	 * @param valueChangeEvent
	 */
	public void updateEventStatus(ValueChangeEvent valueChangeEvent) {
		if (this.event != null) {
			Date now = new Date();
			editedBean.setModificacaoStatus(now);
			editedBean.setModificadorStatus(currentUser.getUsername());

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Status alterado", "Status:" + editedBean.getStatus());
			addMessage(message);

		}
	}

	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	/**
	 * @return the lazyEventModel
	 */
	public ScheduleModel getLazyEventModel() {
		return lazyEventModel;
	}

	/**
	 * @param lazyEventModel
	 *            the lazyEventModel to set
	 */
	public void setLazyEventModel(LazyScheduleModelImpl lazyEventModel) {
		this.lazyEventModel = lazyEventModel;
	}

	/**
	 * @return the event
	 */
	public ScheduleEvent getEvent() {
		return event;
	}

	/**
	 * @param event
	 *            the event to set
	 */
	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}

	/**
	 * @return the editedBean
	 */
	public ExameBean getEditedBean() {
		return editedBean;
	}

	/**
	 * @param editedBean
	 *            the editedBean to set
	 */
	public void setEditedBean(ExameBean editedBean) {
		this.editedBean = editedBean;
	}

}
