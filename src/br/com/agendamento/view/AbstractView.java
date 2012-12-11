package br.com.agendamento.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedProperty;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import br.com.agendamento.bean.AbstractBean;
import br.com.agendamento.util.LoginHelper;
import br.com.agendamento.util.MessageUtils;

/**
 * Classe abstrata m�e das Views
 * 
 * @author lucas.machado
 * 
 * @param <T>
 *            um bean a ser manipulado
 */
public abstract class AbstractView<T extends AbstractBean> implements
		Serializable {

	/**
	 * Construtor padr�o da view
	 */
	public AbstractView() {
		super();
		init();
	}

	@ManagedProperty(value = "#{loginHelper}")
	protected LoginHelper loginHelper;

	private static final long serialVersionUID = 5597761734445212969L;

	protected T bean;

	protected List<T> list;

	abstract void doInit();

	/**
	 * Inicializa a view, carregando o que � preciso para a tela main
	 */
	public void init() {
		System.out.println("initializing view");
		doInit();
	}

	abstract String doAdd();

	/**
	 * Prepara e carrega a tela para adi��o
	 * 
	 * @return outcome para tela de adi��o
	 */
	public String add() {
		System.out.println("initializing add page");
		return doAdd();
	}

	abstract String doEdit();

	/**
	 * Prepara e carrega a tela para edi��o
	 * 
	 * @return outcome para tela de edi��o
	 */
	public String edit() {
		System.out.println("initializing edit page");
		String outcome = null;
		try {
			if (this.bean != null) {

				try {
					outcome = doEdit();
					if (outcome == null) {
						outcome = "edit";
					}
				} catch (Exception e) {
					System.out
							.println("doEdit() execution has thrown an exception...");
					throw e;
				}

			} else {
				MessageUtils.showWarnInForm("Atenc�o!",
						"A sele��o de um item � requerida para editar");
			}
		} catch (Throwable throwable) {
			MessageUtils.showFatalInGrowl("Erro!", "Erro tr�gico no sistema");
			throwable.printStackTrace();
		}

		return outcome;
	}

	abstract String doSave();

	/**
	 * Salva um item rec�m criado
	 * 
	 * @return outcome
	 */
	public String save() {
		System.out.println("trying to insert entity");
		updateCreatedMetadata();
		return doSave();
	}

	abstract String doUpdate();

	/**
	 * Atualiza um item ap�s uma edi��o
	 * 
	 * @return outcome
	 */
	public String update() {
		System.out.println("trying to update entity");
		updateModifiedMetadata();
		return doUpdate();
	}

	abstract void doDelete();

	/**
	 * Remove o item do sistema
	 */
	public void delete() {
		System.out.println("trying to delete entity");
		doDelete();
	}

	/**
	 * Executa as l�gicas de neg�cio associadas para o instante do cancelmento
	 * do salvamento da vis�o.
	 * 
	 * @return o <i>outcome</i> para a regra de navega��o
	 */
	public abstract String cancel();

	/**
	 * Executa as l�gicas de neg�cio associadas para o instante do carregamento
	 * da tela principal
	 * 
	 * @return o <i>outcome</i> para a regra de navega��o
	 */
	public abstract String loadMain();

	/**
	 * Executa as l�gicas de neg�cio associadas para o instante do carregamento
	 * da tela principal
	 * 
	 * @return o <i>outcome</i> para a regra de navega��o
	 */
	public abstract void reset();

	/**
	 * Mostra di�logo de confirma��o de exclus�o se houver bean selecionado; do
	 * contr�rio, mostra mensagem de aviso dizendo que a sele��o � requerida
	 * 
	 * @param actionEvent
	 */
	public void dialogIfSelected(ActionEvent actionEvent) {
		RequestContext context = RequestContext.getCurrentInstance();
		boolean canContinue = false;
		if (this.bean != null) {
			canContinue = true;
		} else {
			MessageUtils.showWarnInForm("Atenc�o!",
					"A sele��o de um item � requerida para excluir");
		}

		context.addCallbackParam("canContinue", canContinue);
	}

	/**
	 * Mostra di�logo de detalhes se houver bean selecionado; do contr�rio,
	 * mostra mensagem de aviso dizendo que a sele��o � requerida
	 * 
	 * @param actionEvent
	 */
	public void detailsIfSelected(ActionEvent actionEvent) {
		RequestContext context = RequestContext.getCurrentInstance();
		boolean canContinue = false;
		if (this.bean != null) {
			canContinue = true;
		} else {
			MessageUtils.showWarnInForm("Atenc�o!",
					"A sele��o de um item � requerida para mostrar detalhes");
		}

		context.addCallbackParam("canContinue", canContinue);
	}

	/**
	 * Executa c�digo <i>java script</i>.
	 * 
	 * @param script
	 *            o {@link String} que representa o c�digo a ser executado
	 */
	protected static void executeJS(String script) {
		RequestContext requestContext = RequestContext.getCurrentInstance();
		if (requestContext != null) {
			requestContext.execute(script);
		} else {
			System.out.println("could not execute javascript '" + script
					+ "' because the request context is null");
		}
	}

	/**
	 * @return the bean
	 */
	public T getBean() {
		return bean;
	}

	/**
	 * @param bean
	 *            the bean to set
	 */
	public void setBean(T bean) {
		this.bean = bean;
	}

	/**
	 * @return the list
	 */
	public List<T> getList() {
		return list;
	}

	/**
	 * @param list
	 *            the list to set
	 */
	public void setList(List<T> list) {
		this.list = list;
	}

	/**
	 * @return the loginHelper
	 */
	public LoginHelper getLoginHelper() {
		return loginHelper;
	}

	/**
	 * @param loginHelper
	 *            the loginHelper to set
	 */
	public void setLoginHelper(LoginHelper loginHelper) {
		this.loginHelper = loginHelper;
	}

	protected void updateModifiedMetadata() {
		Date now = new Date();
		this.bean.setModificacao(now);
		this.bean.setModificador(loginHelper.getCurrent().getUsername());
	}

	protected void updateCreatedMetadata() {
		Date now = new Date();
		this.bean.setCriacao(now);
		this.bean.setCriador(loginHelper.getCurrent().getUsername());
	}
}
