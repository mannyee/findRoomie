package controller;

import ejb.UserFacade;
import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import model.User;

/**
 *
 * @author xtrememe
 */
@ManagedBean
@SessionScoped
public class LoginController {
    @EJB
    private UserFacade userFacade;
    public User user;
    private String userName;
    private String password;
    private String email;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    

    
    
    
    
    public String register(){
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath() + "/faces/pages/user/signup.xhtml");
        } catch (IOException ex) {

        }
        
        return "";
    }
    
    
    public String forgotPassword(){
        return "";
    }
    
    public void logout() throws IOException {
        user = null;
        
//         getRequest().getSession().invalidate();
         HttpServletRequest hsr = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
         hsr.getSession().invalidate();
         
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        ec.redirect(ec.getRequestContextPath() + "/faces/" + "login.xhtml");

    }
    
}
