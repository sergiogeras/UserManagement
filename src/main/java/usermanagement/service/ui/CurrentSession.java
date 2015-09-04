package usermanagement.service.ui;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


@Service
@Scope("session")
public class CurrentSession {

    private String sessionId;
    private String currentUser;

    @PostConstruct
    public void getSessionNumber(){
        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
        sessionId = session.getId();
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        currentUser=userDetails.getUsername();
    }


    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }
}
