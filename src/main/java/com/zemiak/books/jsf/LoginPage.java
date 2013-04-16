package com.zemiak.books.jsf;

import com.zemiak.books.domain.User;
import com.zemiak.books.service.UserManager;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class LoginPage {
    private String username;
    private String password;
    private User current;
    
    private boolean wasError;
    private String lastErrorMessage;

    @Inject
    private UserManager userManager;

    public String login() {
        current = userManager.find(username, password);
        
        if (current == null) {
            wasError = true;
            lastErrorMessage = "Bad email or password";
            
            System.out.println("Hashed password:" + new User().encryptPassword(password));
            
            return (username = password = null);
        } else {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.setAttribute("userManager", userManager);
            wasError = false;
            
            return "/phone/index.jsf";
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/";
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getCurrent() {
        return current;
    }

    public boolean isWasError() {
        return wasError;
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }
}
