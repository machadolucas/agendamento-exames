package br.com.agendamento.view;

/**
 * Interface que representa os tipos de <i>outcome</i>.
 * 
 */
public interface NavigationOutcomeTypes {

	/**
	 * Representa o <i>outcome</i> de listagem.<br />
	 * Geralmente, associado � p�ginas <quote>main.xhtml</quote>.
	 */
	public static String DEFAULT = "main";

	/**
	 * Representa o <i>outcome</i> de adi��o.<br />
	 * Geralmente, associado � p�ginas <quote>add.xhtml</quote>.
	 */
	public static String ADD = "add";

	/**
	 * Representa o <i>outcome</i> de edi��o.<br />
	 * Geralmente, associado � p�ginas <quote>edit.xhtml</quote>.
	 */
	public static String EDIT = "edit";

}
