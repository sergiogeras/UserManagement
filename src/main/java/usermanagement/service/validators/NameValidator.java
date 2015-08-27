package usermanagement.service.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.ResourceBundle;


@FacesValidator("NameValidator")
public class NameValidator implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {

        ResourceBundle bundle = ResourceBundle.getBundle("locales.messages",
                FacesContext.getCurrentInstance().getViewRoot().getLocale());

        try {
            if (!Character.isLetter(value.toString().charAt(0))) {
                throw new IllegalArgumentException(bundle.getString("first_symbol_error"));
            }

            if(value.toString().length()<2){
                throw new IllegalArgumentException(bundle.getString("minimum_length2"));
            }

        } catch (IllegalArgumentException e){
            FacesMessage message = new FacesMessage(e.getMessage());
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}
