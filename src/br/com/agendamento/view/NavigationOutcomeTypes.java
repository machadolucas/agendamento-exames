package br.com.agendamento.view;

/**
 * Interface que representa os tipos de <i>outcome</i>.
 * 
 */
public interface NavigationOutcomeTypes {

	/**
	 * Representa o <i>outcome</i> de listagem.<br />
	 * Geralmente, associado à páginas <quote>main.xhtml</quote>.
	 */
	public static String DEFAULT = "main";

	/**
	 * Representa o <i>outcome</i> de adição.<br />
	 * Geralmente, associado à páginas <quote>add.xhtml</quote>.
	 */
	public static String ADD = "add";

	/**
	 * Representa o <i>outcome</i> de edição.<br />
	 * Geralmente, associado à páginas <quote>edit.xhtml</quote>.
	 */
	public static String EDIT = "edit";

}
