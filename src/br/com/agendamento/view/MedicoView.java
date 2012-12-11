package br.com.agendamento.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.agendamento.bean.MedicoBean;
import br.com.agendamento.db.dao.MedicoDAO;

/**
 * @author lucasmachado
 * 
 */
@ManagedBean(name = "medicoViewBean")
@SessionScoped
public class MedicoView extends AbstractView<MedicoBean> {

	private static final String mainOutcome = "/xhtml/medico/main.jsf?faces-redirect=true";

	private static final long serialVersionUID = -1189905181415458744L;

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
		this.bean = new MedicoBean();
		return NavigationOutcomeTypes.ADD;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.agendamento.view.AbstractView#doEdit()
	 */
	@Override
	String doEdit() {
		return NavigationOutcomeTypes.EDIT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.agendamento.view.AbstractView#doSave()
	 */
	@Override
	String doSave() {
		MedicoDAO.insert(bean);
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
		MedicoDAO.update(bean);
		reset();
		return mainOutcome;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.agendamento.view.AbstractView#doDelete()
	 */
	@Override
	void doDelete() {
		MedicoDAO.delete(bean);
		reset();

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
		this.list = MedicoDAO.loadMedicosList();
		this.bean = new MedicoBean();
	}

}
