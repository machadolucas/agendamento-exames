package br.com.agendamento.util;

import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.LazyScheduleModel;

import br.com.agendamento.bean.ExameBean;
import br.com.agendamento.bean.WebAppUserBean;
import br.com.agendamento.db.dao.ExameDAO;

/**
 * Implementação Lazy para a agenda de exames.
 * 
 * @author lucasmachado
 * 
 */
public class LazyScheduleModelImpl extends LazyScheduleModel {

	private static final long serialVersionUID = -7945822023262853346L;

	private static WebAppUserBean currentUser;

	@Override
	public void loadEvents(Date start, Date end) {
		clear();

		List<ExameBean> listExames = ExameDAO.loadExamesListWithinDate(start,
				end);

		loadCurrentUser();

		if (listExames != null) {
			for (ExameBean exame : listExames) {
				if (exame.getResponsavel().equals(currentUser.getUsername())) {
					addPersonalEvent(exame);
				} else {
					addOtherEvent(exame);
				}
			}
		}

	}

	/**
	 * Carrega o usuário atualmente logado da sessão do sistema
	 */
	private void loadCurrentUser() {
		if (currentUser == null) {
			LoginHelper loginHelper = (LoginHelper) FacesContext
					.getCurrentInstance().getExternalContext().getSessionMap()
					.get("loginHelper");
			setCurrentUser(loginHelper.getCurrent());
		}
	}

	/**
	 * Adiciona um evento no calendário para o usuário atualmente logado
	 * 
	 * @param bean
	 *            Exame que será exibido como evento
	 */
	public void addPersonalEvent(ExameBean bean) {

		StringBuilder eventName = new StringBuilder();
		eventName.append(bean.getTipo()).append(" - ")
				.append(bean.getPaciente()).append(" (")
				.append(bean.getResponsavel()).append(")");

		DefaultScheduleEvent event = new DefaultScheduleEvent();
		event.setTitle(eventName.toString());
		event.setStartDate(bean.getDataExame());
		event.setEndDate(bean.getDataExame());

		event.setAllDay(true);
		event.setStyleClass("eventPersonal");
		event.setData(bean);
		addEvent(event);
	}

	/**
	 * Adiciona um evento no calendário para demais usuários do sistema que não
	 * o logado
	 * 
	 * @param bean
	 *            Exame que será exibido como evento
	 */
	public void addOtherEvent(ExameBean bean) {

		StringBuilder eventName = new StringBuilder();
		eventName.append(bean.getTipo()).append(" - ")
				.append(bean.getPaciente()).append(" (")
				.append(bean.getResponsavel()).append(")");

		DefaultScheduleEvent event = new DefaultScheduleEvent();
		event.setTitle(eventName.toString());
		event.setStartDate(bean.getDataExame());
		event.setEndDate(bean.getDataExame());

		event.setAllDay(true);
		event.setStyleClass("eventOther");
		event.setEditable(false);
		event.setData(bean);
		addEvent(event);
	}

	/**
	 * @return the currentUser
	 */
	public static WebAppUserBean getCurrentUser() {
		return currentUser;
	}

	/**
	 * @param currentUser
	 *            the currentUser to set
	 */
	public static void setCurrentUser(WebAppUserBean currentUser) {
		LazyScheduleModelImpl.currentUser = currentUser;
	}
}
