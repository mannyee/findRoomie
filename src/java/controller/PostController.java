/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.PostFacade;
import ejb.UserFacade;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import model.Comment;
import model.Post;
import model.User;

/**
 *
 * @author Ashok Subedi
 */
@ManagedBean
@SessionScoped
public class PostController extends BaseController{
    
    @EJB
    private PostFacade postFacade;    
    @EJB
    private UserFacade userFacade;
    private List<Post> allPosts;
    private Post myPost;
    private Comment comment;
        
    
    @PostConstruct
    public void setPosts(){
        System.out.println("post construct method called");
         HttpSession session = (HttpSession)getEc().getSession(false);
        
        allPosts = postFacade.findAllByUser((User)session.getAttribute("userObj"));
        getFlash().put("posts", allPosts);
    }

    

    /**
     * Creates a new instance of PostController
     */
    public PostController() {
        
        System.out.println("inside the post controller constructor");
        this.myPost = new Post();  
    }
    
    public Post getPost(){
        return myPost;
    }
    
    public String postThisPost(){
        String email = getEc().getRemoteUser();        
        User user = userFacade.findByEmail(email);
        
        myPost.setPostedBy(user);
        this.postFacade.create(myPost);
        
        return "dashboard?faces-redirect=true";
    }    
    
    
        
    public String updateProfile(){
        return "";
    }
    
    
    public String showPosts(){
        /**
         * We could do 
         * List<Post> allPosts = posts.findAll();
         *  
         * and then simply 
         * 
         * return "showPosts";
         * 
         * In showPosts.xhtml, we can easily have allPosts available through the postController bean
         * BUT this will cause the servlet to only forward the request not redirect.
         * 
         * And if we redirect, the bean will not be available. We will need flash scope to survive a
         * redirect.
         * 
         * More: maxkatz.org/2010/07/27/learning-jsf2-using-flash-scope/
         */
        HttpSession session = (HttpSession)getEc().getSession(false);
        
        allPosts = postFacade.findAllByUser((User)session.getAttribute("userObj"));
        getFlash().put("posts", allPosts);
        
        return "showPosts?faces-redirect=true";
    }

    public void helloWorld(){
        System.out.println("hello world!!");
    }
    
    public String roomDetails(){
        System.out.println("inside room details");
        return "roomDetails?faces-redirect=true";
    }
    
    public Comment getComment() {
        return comment;
    }

    
    public void preRenderView(String postId){
        System.out.println("postId: " + postId);
    
    }
    
    
    
    
    public String submitComment(){
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, this.comment.getDescription());
        
        System.out.println("inside the submit commment");
        
        return "dashboard.xhtml?faces-redirect=true";
    }
    

}
