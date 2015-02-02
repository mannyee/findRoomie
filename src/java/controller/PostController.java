/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.PostFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.Post;

/**
 *
 * @author Ashok Subedi
 */
@ManagedBean
@RequestScoped
public class PostController {
    
    @EJB
    private PostFacade postFacade;
    
    private Post myPost;
    

    /**
     * Creates a new instance of PostController
     */
    public PostController() {
        this.myPost = new Post();        
    }
    
    public Post getPost(){
        return myPost;
    }
    
    public String postThisPost(){
        this.postFacade.create(myPost);
        return "index";
    }    
}
