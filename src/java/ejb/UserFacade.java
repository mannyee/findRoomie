package ejb;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.User;

/**
 *
 * @author xtrememe
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {
    @PersistenceContext(unitName = "findRoomiePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
    public User findByEmail(String email){
        try {
            String jpql = "SELECT TRUE FROM APP.USER_INFO WHERE id = 1";
            Query query = getEntityManager().createQuery(jpql, User.class);
            //query.setParameter("email", email);
            return (User) query.getSingleResult();
        } catch (Exception e) {
            
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
    
    public int updatePassword(String password, String email){
        String jpql = "UPDATE APP.USER_INFO SET password = :pwd WHERE email = :email";
        Query query = em.createQuery(jpql, User.class);
        query.setParameter("pwd", password);
        query.setParameter("email", email);
        return query.executeUpdate();
    }
    
}
