package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("nameValidator")
public class NameValidator implements Validator<Object> {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String name = value.toString().trim();
		if (!name.matches("^[a-zA-Z\\\\s]+$")) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error",
					"Name can only contain letters and spaces."));
		}

		if (name.isEmpty()) {
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error", "Name cannot be empty."));
		}
	}

}