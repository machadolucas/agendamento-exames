/**
 * 
 */
package br.com.agendamento.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.agendamento.bean.PacienteBean;
import br.com.agendamento.db.dao.MedicoDAO;
import br.com.agendamento.db.dao.PacienteDAO;

/**
 * @author tiagoklein
 * 
 */
@ManagedBean(name = "pacienteViewBean")
@SessionScoped
public class PacienteView extends AbstractView<PacienteBean> {

	private static final String mainOutcome = "/xhtml/paciente/main.jsf?faces-redirect=true";

	private static final long serialVersionUID = 1L;

	private List<String> medicosList;

	@Override
	void doInit() {
		reset();

	}

	@Override
	String doAdd() {
		this.bean = new PacienteBean();
		this.medicosList = MedicoDAO.loadMedicosStringList();
		return NavigationOutcomeTypes.ADD;
	}

	@Override
	String doEdit() {
		this.medicosList = MedicoDAO.loadMedicosStringList();
		return NavigationOutcomeTypes.EDIT;
	}

	@Override
	String doSave() {
		PacienteDAO.insert(bean);
		reset();
		return mainOutcome;
	}

	@Override
	String doUpdate() {
		PacienteDAO.update(bean);
		reset();
		return mainOutcome;
	}

	@Override
	void doDelete() {
		PacienteDAO.delete(bean);
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
		this.list = PacienteDAO.loadPacientesList();
		this.bean = new PacienteBean();
	}

	/**
	 * @return the medicosList
	 */
	public List<String> getMedicosList() {
		return medicosList;
	}

	/**
	 * @param medicosList
	 *            the medicosList to set
	 */
	public void setMedicosList(List<String> medicosList) {
		this.medicosList = medicosList;
	}

}
