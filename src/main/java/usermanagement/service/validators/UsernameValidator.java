package usermanagement.service.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import usermanagement.service.UserService;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.ResourceBundle;


@Component
@Scope("request")
public class UsernameValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
        ResourceBundle bundle = ResourceBundle.getBundle("locales.messages",
                FacesContext.getCurrentInstance().getViewRoot().getLocale());

        try{
            //проверка на уникальность username в БД
            if(!userService.isUsernameDuplicated(value.toString()).isEmpty()){
                throw new IllegalArgumentException(bundle.getString("username_already_exists"));
            }

            if (!Character.isLetter(value.toString().charAt(0))) {
                throw new IllegalArgumentException(bundle.getString("first_symbol_error"));
            }


        }catch (IllegalArgumentException e){
            FacesMessage message = new FacesMessage(e.getMessage());
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}
