package controller;

import commonEnums.States;
import ejb.PostFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.Post;

/**
 *
 * @author xtrememe
 */
@ManagedBean
@RequestScoped
public class SearchController extends BaseController{
    @EJB
    private PostFacade postFacade;
    private String selectedState;
    private String selectedCity;
    private List<Post> allPosts;

    
    /**
     * Creates a new instance of SearchController
     */
    public SearchController() {
        
    }
    
    
    public String getSelectedState() {
        return selectedState;
    }

    public void setSelectedState(String state) {
        this.selectedState = state;
    }

    public String getSelectedCity() {
        return selectedCity;
    }

    public void setSelectedCity(String city) {
        this.selectedCity = city;
    }
    
    
    public States[] getStates(){
        return States.values();
    }
    
    
    public List<Post> getAllPosts(){
        return allPosts;
    }
    
    public String search(){
        System.out.println("selectedCity: " + selectedCity);
        System.out.println("size: " +  postFacade.search(selectedCity, selectedState).size());
        
        getFlash().put("results", postFacade.search(selectedCity, selectedState));
        
        return "searchResults?faces-redirect=true";
    }
    
}
