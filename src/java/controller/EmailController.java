package controller;

import ejb.EmailSessionBean;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author xtrememe
 */
@ManagedBean
@RequestScoped
public class EmailController {
    @EJB
    private EmailSessionBean emailSessionBean;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

    /**
     * Creates a new instance of EmailController
     */
    public EmailController() {
    }
    
    
    public void sendEmail(){
        
        
//        response.setContentType("text/html;charset=UTF-8");
//        
//        PrintWriter out = response.getWriter();
//        
//        try {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet EmailServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet EmailServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        } finally {
//            out.close();
//        }
        
        emailSessionBean.sendEmail(email);
        
        
    
    }
    
    public void forgotPassword() throws IOException{
         
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "/faces/" + "forgotPassword.xhtml");
    
    }
}
