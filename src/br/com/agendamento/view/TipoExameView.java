package br.com.agendamento.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.agendamento.bean.TipoExameBean;
import br.com.agendamento.db.dao.TipoExameDAO;

/**
 * @author lucasmachado
 * 
 */
@ManagedBean(name = "tipoExameViewBean")
@SessionScoped
public class TipoExameView extends AbstractView<TipoExameBean> {

	private static final String mainOutcome = "/xhtml/tipo_exame/main.jsf?faces-redirect=true";

	private static final long serialVersionUID = -5499226039852842665L;

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
		this.bean = new TipoExameBean();
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
		TipoExameDAO.insert(bean);
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
		TipoExameDAO.update(bean);
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
		TipoExameDAO.delete(bean);
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
		this.list = TipoExameDAO.loadTiposExamesList();
		this.bean = new TipoExameBean();

	}

}
