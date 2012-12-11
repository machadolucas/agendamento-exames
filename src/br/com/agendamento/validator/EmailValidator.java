package br.com.agendamento.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

import br.com.agendamento.util.MessageUtils;

/**
 * Validador de campos de email
 * 
 * @author lucas.machado
 * 
 */
@FacesValidator("emailValidator")
public class EmailValidator extends AbstractValidator {

	private Pattern pattern;
	private Matcher matcher;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	@Override
	protected void execute(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		String inputValue = (String) value;

		pattern = Pattern.compile(EMAIL_PATTERN);

		matcher = pattern.matcher(inputValue);

		if (!matcher.matches()) {
			MessageUtils.showErrorInGrowl("Erro!",
					"Há problemas de validação com os campos indicados.");
			throw new ValidatorException(
					MessageUtils.createErrorMessage(invalidMessage));
		}

	}

}
