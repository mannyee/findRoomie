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
    private User user;
    private String email;

    public String getEmail() {
        return email;
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
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        
        try {
            ec.redirect(ec.getRequestContextPath() + "/faces/forgotPassword.xhtml");
        } catch (IOException ex) {

        }
        
        return "";
    }
    
    public void logout() throws IOException {
        user = null;
        
        HttpServletRequest hsr = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        hsr.getSession().invalidate();
         
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        ec.redirect(ec.getRequestContextPath() + "/faces/" + "login.xhtml");
        
        
//        String result="/index?faces-redirect=true";
     
//    FacesContext context = FacesContext.getCurrentInstance();
//    HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
//     
//    try {
//      request.logout();
//    } catch (ServletException e) {
//      log.log(Level.SEVERE, "Failed to logout user!", e);
//      result = "/loginError?faces-redirect=true";
//    }
//     
//    return result;

    }
    
}
