/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.MemberFacade;
import ejb.UserFacade;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import model.Member;
import model.User;

/**
 *
 * @author rmsor_000
 */
@ManagedBean
@RequestScoped
public class SignupController {
    @Inject
    private MemberFacade memberFacade;
    @Inject
    private UserFacade userFacade;
    private User user;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String conPassword;
    
    /**
     * Creates a new instance of SignupController
     */
    public SignupController() {
    }
//    @PostConstruct
//    public void showPostConstructMessage(){
//        FacesMessage facesMessage;
//        facesMessage = new FacesMessage("RegControl EJB Postconstruct Complete");
//        facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
//        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//    }
    public void saveRegInfo() {
        user = userFacade.findUserByEmail(email);
        if (user == null) {
            user = new User();            
            user.setEmail(email);
            user.setUserName(email);
            user.setProfilePic("test.jpg");
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPassword(password);
            user.setGender("Male");
            user.setRegisteredDate(new Date());
            userFacade.create(user);
        } else {
            //this user already exists so they can not register again
            FacesMessage facesMessage = new FacesMessage("Registration Failed - user already exists");
            facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            System.out.println("Registration failed - user exists: " + user.toString());
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConPassword() {
        return conPassword;
    }

    public void setConPassword(String conPassword) {
        this.conPassword = conPassword;
    }

    public MemberFacade getMemberFacade() {
        return memberFacade;
    }

    public void setMemberFacade(MemberFacade memberFacade) {
        this.memberFacade = memberFacade;
    }

    public UserFacade getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }
    
    
}
