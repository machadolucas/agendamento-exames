package br.com.agendamento.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.DualListModel;

import br.com.agendamento.bean.ResponsavelBean;
import br.com.agendamento.db.dao.ResponsavelDAO;
import br.com.agendamento.db.dao.TipoExameDAO;
import br.com.agendamento.util.Hasher;
import br.com.agendamento.util.LoginHelper;
import br.com.agendamento.util.MessageUtils;

/**
 * @author lucasmachado
 * 
 */
@ManagedBean(name = "responsavelViewBean")
@SessionScoped
public class ResponsavelView extends AbstractView<ResponsavelBean> {

	private static final String mainOutcome = "/xhtml/responsavel/main.jsf?faces-redirect=true";
	private static final String editOutcome = "/xhtml/responsavel/edit.jsf?faces-redirect=true";

	private static final long serialVersionUID = -668600224008085391L;

	private String[] days;

	private String oldPassword;
	private String newPassword;

	private DualListModel<String> dualListTipoExame;

	private DualListModel<String> dualListWeekdays;

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.agendamento.view.AbstractView#doInit()
	 */
	@Override
	void doInit() {
		reset();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.agendamento.view.AbstractView#doAdd()
	 */
	@Override
	String doAdd() {
		this.bean = new ResponsavelBean();

		this.dualListWeekdays = new DualListModel<String>(
				ResponsavelDAO.getWeekDays(), new ArrayList<String>());

		this.dualListTipoExame = new DualListModel<String>(
				TipoExameDAO.loadTiposExamesStringList(),
				new ArrayList<String>());

		return NavigationOutcomeTypes.ADD;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.agendamento.view.AbstractView#doEdit()
	 */
	@Override
	String doEdit() {
		this.dualListWeekdays = new DualListModel<String>(
				ResponsavelDAO.getWeekDays(), new ArrayList<String>());
		correctDaysDualList(this.dualListWeekdays);

		this.dualListTipoExame = new DualListModel<String>(
				TipoExameDAO.loadTiposExamesStringList(),
				new ArrayList<String>());
		correctTiposDualList(this.dualListTipoExame);
		return editOutcome;
	}

	/**
	 * @param dualListWeekdays
	 */
	private void correctDaysDualList(DualListModel<String> dualListWeekdays) {
		List<String> list = this.bean.getDiasSemanaTrabalha();
		if (list != null) {
			for (String day : list) {
				dualListWeekdays.getSource().remove(day);
				dualListWeekdays.getTarget().add(day);
			}
		}
	}

	/**
	 * @param dualListTipoExame
	 */
	private void correctTiposDualList(DualListModel<String> dualListTipoExame) {
		List<String> list = this.bean.getTiposExames();
		if (list != null) {
			for (String tipo : list) {
				dualListTipoExame.getSource().remove(tipo);
				dualListTipoExame.getTarget().add(tipo);
			}
		}
	}

	/**
	 * Método utilizado para o carregamento da tela de edição para o usuário
	 * logado.
	 * 
	 * @return o {@link String} que representa o <i>outcome</i> para a regra de
	 *         navegação definida em <a>/WEB-INF/faces-config.xml</a>
	 * @throws Exception
	 *             caso o carregamento da tela de edição falhe
	 */
	public String editCurrentUser() throws Exception {

		init();

		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext()
				.getSession(true);
		LoginHelper loginHelper = (LoginHelper) session
				.getAttribute("loginHelper");

		ResponsavelBean bean = new ResponsavelBean(loginHelper.getCurrent());

		int index = this.list.indexOf(bean);
		if (index >= 0) {
			setBean(this.list.get(index));
		}

		return edit();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.agendamento.view.AbstractView#doSave()
	 */
	@Override
	String doSave() {
		this.bean.setPassword(Hasher.hash(newPassword, bean.getEmail()));
		this.bean.setDiasSemanaTrabalha(dualListWeekdays.getTarget());
		this.bean.setTiposExames(dualListTipoExame.getTarget());
		ResponsavelDAO.insert(bean);
		reset();
		return mainOutcome;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.agendamento.view.AbstractView#doUpdate()
	 */
	@Override
	String doUpdate() {
		if (oldPassword != null && oldPassword.length() > 0) {
			if (Hasher.hash(oldPassword, bean.getEmail()).equals(
					bean.getPassword())) {
				if (newPassword != null && newPassword.length() > 0) {
					this.bean.setPassword(Hasher.hash(newPassword,
							bean.getEmail()));
				}
			} else {
				MessageUtils
						.showErrorInGrowl("Senha incorreta",
								"A senha informada como senha atual está incorreta. Por favor, verifique.");
				return null;
			}
		}
		this.bean.setDiasSemanaTrabalha(dualListWeekdays.getTarget());
		this.bean.setTiposExames(dualListTipoExame.getTarget());
		if (ResponsavelDAO.update(bean)) {
			reset();
			return mainOutcome;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.agendamento.view.AbstractView#doDelete()
	 */
	@Override
	void doDelete() {
		reset();
		ResponsavelDAO.delete(bean);
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
		this.bean = new ResponsavelBean();
		this.list = ResponsavelDAO.loadResponsaveisList();
	}

	/**
	 * @return the oldPassword
	 */
	public String getOldPassword() {
		return oldPassword;
	}

	/**
	 * @param oldPassword
	 *            the oldPassword to set
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	/**
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param newPassword
	 *            the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * @return the dualListTipoExame
	 */
	public DualListModel<String> getDualListTipoExame() {
		return dualListTipoExame;
	}

	/**
	 * @param dualListTipoExame
	 *            the dualListTipoExame to set
	 */
	public void setDualListTipoExame(DualListModel<String> dualListTipoExame) {
		this.dualListTipoExame = dualListTipoExame;
	}

	/**
	 * @return the days
	 */
	public String[] getDays() {
		return days;
	}

	/**
	 * @param days
	 *            the days to set
	 */
	public void setDays(String[] days) {
		this.days = days;
	}

	/**
	 * @return the dualListWeekdays
	 */
	public DualListModel<String> getDualListWeekdays() {
		return dualListWeekdays;
	}

	/**
	 * @param dualListWeekdays
	 *            the dualListWeekdays to set
	 */
	public void setDualListWeekdays(DualListModel<String> dualListWeekdays) {
		this.dualListWeekdays = dualListWeekdays;
	}

}
