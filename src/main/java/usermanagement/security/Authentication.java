package usermanagement.security;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;


@Component
@Scope("request")
public class Authentication {



    public String doLogin() throws ServletException, IOException {
        ExternalContext context=FacesContext.getCurrentInstance().getExternalContext();
        RequestDispatcher dispatcher=((ServletRequest)context.getRequest()).getRequestDispatcher("/j_spring_security_check");
        dispatcher.forward((ServletRequest)context.getRequest(), (ServletResponse)context.getResponse());
        FacesContext.getCurrentInstance().responseComplete();
        return null;
    }

    public String doLogout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "exit";
    }

}

