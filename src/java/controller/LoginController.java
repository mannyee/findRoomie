package controller;

import ejb.UserFacade;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
    private ExternalContext ec;

    
    public LoginController(){
        ec = FacesContext.getCurrentInstance().getExternalContext();
        
    }
    
    
    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }
    
    

    public String dashboard(){
    
        return "";
    }
    
    
    
    
    public String register(){
        try {
            ec.redirect(ec.getRequestContextPath() + "/faces/pages/user/signup.xhtml");
        } catch (IOException ex) {

        }
        
        return "";
    }
    
    public String displayName(){
        String email = ec.getRemoteUser();
        
        System.out.println("email: " + email);
        
        user = userFacade.findByEmail(email);
        
        return user.getFirstName();
    }
    
    
    public String forgotPassword(){
        
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
    
    
    public String updateProfile(){
        return "";
    }
    
    }

//http://www.pramati.com/docstore/1270002/index.htm
//    http://www.avajava.com/tutorials/lessons/how-do-i-create-a-login-module.html
//    http://stackoverflow.com/questions/20396276/jaas-exception-null-username-and-password
