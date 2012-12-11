package br.com.agendamento.validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.agendamento.util.MessageUtils;

/**
 * @author lucas.machado
 * 
 */
public abstract class AbstractValidator implements Validator {

	/**
	 * Valor do atributo <i>requiredMessage</i>.
	 */
	protected static final String requiredMessage = "Este campo � de preenchimento obrigat�rio.";

	/**
	 * Valor do atributo <i>invalidMessage</i>.
	 */
	protected static final String invalidMessage = "O valor informado � inv�lido.";

	/**
	 * Realiza a valida��o.
	 * 
	 * @param context
	 *            o {@link FacesContext} que representa o contexto
	 * @param component
	 *            o {@link UIComponent} que representa o componente
	 * @param value
	 *            o {@link Object} que representa o valor do componente
	 * @throws ValidatorException
	 *             caso a valida��o falhe
	 */
	protected abstract void execute(FacesContext context,
			UIComponent component, Object value) throws ValidatorException;

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		String inputValue = (String) value;
		if (inputValue == null || inputValue.length() == 0) {
			MessageUtils.showErrorInForm(
					"H� problemas de valida��o com os campos indicados.", null);
			throw new ValidatorException(
					MessageUtils.createErrorMessage(requiredMessage));
		}

		execute(context, component, value);

	}

}
