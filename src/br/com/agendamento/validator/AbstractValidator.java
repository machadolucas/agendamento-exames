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
	protected static final String requiredMessage = "Este campo é de preenchimento obrigatório.";

	/**
	 * Valor do atributo <i>invalidMessage</i>.
	 */
	protected static final String invalidMessage = "O valor informado é inválido.";

	/**
	 * Realiza a validação.
	 * 
	 * @param context
	 *            o {@link FacesContext} que representa o contexto
	 * @param component
	 *            o {@link UIComponent} que representa o componente
	 * @param value
	 *            o {@link Object} que representa o valor do componente
	 * @throws ValidatorException
	 *             caso a validação falhe
	 */
	protected abstract void execute(FacesContext context,
			UIComponent component, Object value) throws ValidatorException;

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		String inputValue = (String) value;
		if (inputValue == null || inputValue.length() == 0) {
			MessageUtils.showErrorInForm(
					"Há problemas de validação com os campos indicados.", null);
			throw new ValidatorException(
					MessageUtils.createErrorMessage(requiredMessage));
		}

		execute(context, component, value);

	}

}
