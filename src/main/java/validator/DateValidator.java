package validator;

import java.time.LocalDate;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("dateValidator")
public class DateValidator implements Validator<Object> {
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) {
    	 try {
             LocalDate dob = (LocalDate) value;
             LocalDate currentDate = LocalDate.now();
             if (dob.isAfter(currentDate)) {
                 throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Date of Birth cannot be in the future", null));
             }
         } catch (Exception e) {
             throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Date format. Use yyyy-MM-dd", null));
         }
    }
}


