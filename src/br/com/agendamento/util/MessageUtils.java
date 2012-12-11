package br.com.agendamento.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

/**
 * Classe utilit‡ria para mandar mensagens ao usuário.
 * 
 * @author lucasmachado
 * 
 */
public class MessageUtils {

	/**
	 * Mostra uma mensagem para o usuário no growl
	 * 
	 * @param severity
	 *            Severidade da mensagem
	 * @param title
	 *            T’tulo da mensagem
	 * @param message
	 *            Texto da mensagem
	 */
	public static void showInGrowl(Severity severity, String title,
			String message) {

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, createMessage(severity, title, message));

	}

	/**
	 * Mostra uma mensagem para o usuário no form
	 * 
	 * @param severity
	 *            Severidade da mensagem
	 * @param title
	 *            T’tulo da mensagem
	 * @param message
	 *            Texto da mensagem
	 */
	public static void showInForm(Severity severity, String title,
			String message) {

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("formMessages",
				createMessage(severity, title, message));

	}

	/**
	 * Mostra uma mensagem para o usuário no growl
	 * 
	 * @param title
	 *            T’tulo da mensagem
	 * @param message
	 *            Texto da mensagem
	 */
	public static void showInfoInGrowl(String title, String message) {
		showInGrowl(FacesMessage.SEVERITY_INFO, title, message);

	}

	/**
	 * Mostra uma mensagem para o usuário no growl
	 * 
	 * @param title
	 *            T’tulo da mensagem
	 * @param message
	 *            Texto da mensagem
	 */
	public static void showWarnInGrowl(String title, String message) {
		showInGrowl(FacesMessage.SEVERITY_WARN, title, message);

	}

	/**
	 * Mostra uma mensagem para o usuário no growl
	 * 
	 * @param title
	 *            T’tulo da mensagem
	 * @param message
	 *            Texto da mensagem
	 */
	public static void showErrorInGrowl(String title, String message) {
		showInGrowl(FacesMessage.SEVERITY_ERROR, title, message);

	}

	/**
	 * Mostra uma mensagem para o usuário no growl
	 * 
	 * @param title
	 *            T’tulo da mensagem
	 * @param message
	 *            Texto da mensagem
	 */
	public static void showFatalInGrowl(String title, String message) {
		showInGrowl(FacesMessage.SEVERITY_FATAL, title, message);

	}

	/**
	 * Mostra uma mensagem para o usuário no form
	 * 
	 * @param title
	 *            T’tulo da mensagem
	 * @param message
	 *            Texto da mensagem
	 */
	public static void showInfoInForm(String title, String message) {
		showInForm(FacesMessage.SEVERITY_INFO, title, message);

	}

	/**
	 * Mostra uma mensagem para o usuário no form
	 * 
	 * @param title
	 *            T’tulo da mensagem
	 * @param message
	 *            Texto da mensagem
	 */
	public static void showWarnInForm(String title, String message) {
		showInForm(FacesMessage.SEVERITY_WARN, title, message);

	}

	/**
	 * Mostra uma mensagem para o usuário no form
	 * 
	 * @param title
	 *            T’tulo da mensagem
	 * @param message
	 *            Texto da mensagem
	 */
	public static void showErrorInForm(String title, String message) {
		showInForm(FacesMessage.SEVERITY_ERROR, title, message);

	}

	/**
	 * Mostra uma mensagem para o usuário no form
	 * 
	 * @param title
	 *            T’tulo da mensagem
	 * @param message
	 *            Texto da mensagem
	 */
	public static void showFatalInForm(String title, String message) {
		showInForm(FacesMessage.SEVERITY_FATAL, title, message);

	}

	/**
	 * Cria uma mensagem.
	 * 
	 * @param severity
	 *            o {@link Severity} que representa a severidade da mensagem
	 * @param summary
	 *            o {@link Severity} que representa o sumário da mensagem
	 * @return o {@link FacesMessage} que representa a mensagem
	 */
	public static FacesMessage createMessage(Severity severity, String summary) {
		return createMessage(severity, summary, null);
	}

	/**
	 * Cria uma mensagem.
	 * 
	 * @param severity
	 *            o {@link Severity} que representa a severidade da mensagem
	 * @param summary
	 *            o {@link Severity} que representa o sumário da mensagem
	 * @param detail
	 *            o {@link String} que representa os detalhes de uma mensagem
	 * @return o {@link FacesMessage} que representa a mensagem
	 */
	public static FacesMessage createMessage(Severity severity, String summary,
			String detail) {

		if (severity == null || summary == null || summary.length() == 0) {
			throw new IllegalArgumentException();
		}

		return new FacesMessage(severity, summary, detail);

	}

	/**
	 * Cria uma mensagem com severidade error.
	 * 
	 * @param summary
	 *            o {@link String} que representa o sumário de uma mensagem
	 * @return o {@link FacesMessage} que representa a mensagem
	 * @see #createMessage(Severity, String)
	 */
	public static FacesMessage createErrorMessage(String summary) {
		return createMessage(FacesMessage.SEVERITY_ERROR, summary);
	}

	/**
	 * Cria uma mensagem com severidade fatal.
	 * 
	 * @param summary
	 *            o {@link String} que representa o sumário de uma mensagem
	 * @return o {@link FacesMessage} que representa a mensagem
	 * @see #createMessage(Severity, String)
	 */
	public static FacesMessage createFatalMessage(String summary) {
		return createMessage(FacesMessage.SEVERITY_FATAL, summary);
	}

	/**
	 * Cria uma mensagem com severidade info.
	 * 
	 * @param summary
	 *            o {@link String} que representa o sumário de uma mensagem
	 * @return o {@link FacesMessage} que representa a mensagem
	 * @see #createMessage(Severity, String)
	 */
	public static FacesMessage createInfoMessage(String summary) {
		return createMessage(FacesMessage.SEVERITY_INFO, summary);
	}

	/**
	 * Cria uma mensagem com severidade warn.
	 * 
	 * @param summary
	 *            o {@link String} que representa o sumário de uma mensagem
	 * @return o {@link FacesMessage} que representa a mensagem
	 * @see #createMessage(Severity, String)
	 */
	public static FacesMessage createWarnMessage(String summary) {
		return createMessage(FacesMessage.SEVERITY_WARN, summary);
	}

}
