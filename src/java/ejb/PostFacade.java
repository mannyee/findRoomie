/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
    
    
    public List<Post> search(String city, String state){
        List<Post> posts = new ArrayList<>();
        
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Post> cq = cb.createQuery(Post.class);
        Root<Post> post = cq.from(Post.class);
        
        cq.select(post);
        
        
        List<Predicate> predicates = new ArrayList<Predicate>();

        if (city != null) {
            predicates.add(cb.like(
                    post.get("addressCity"), "%"+city+"%")
            );
        }
        
        
        if(state != null){
            predicates.add(cb.equal(
                    post.get("addressState"), "%" + state + "%"));
        }
        
        

        cq.where(predicates.toArray(new Predicate[0]));
        
        

        
        
        
        
        TypedQuery<Post> q = getEntityManager().createQuery(cq);
        
        
        return q.getResultList();
    }
}
