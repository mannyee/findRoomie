/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import java.util.logging.*;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Post;
import model.User;

/**
 *
 * @author Ashok Subedi
 */
@Stateless
public class PostFacade extends AbstractFacade<Post> {
    @PersistenceContext(unitName = "findRoomiePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PostFacade() {
        super(Post.class);
    }
    
    public List<Post> findAllByUser(User userObj){
        /**
         * We don't need to have userObj passed here.We can simply fetch 
         * it again from the FacesContext object.
         */
        
        List<Post> posts = null;
    
        try {
            User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userObj");
            System.out.println("user.email: " + user.getEmail());
            
            String jpql = "SELECT p from Post p where p.postedBy = :user";
            Query query = getEntityManager().createQuery(jpql);
            query.setParameter("user", userObj);
            
            posts = query.getResultList();
            
        } catch (Exception e) {
            
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
        
        return posts;            
    }
}
