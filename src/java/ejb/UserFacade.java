package ejb;

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
    public User findUserByEmail(String email) {
        try {
            Query userNameQuery = em.createNamedQuery("User.findByEmail");
            userNameQuery.setParameter("email", email);
            User foundUser = (User) userNameQuery.getSingleResult();
            return foundUser;
        } catch (Exception e) {
            return null;
        }
    }
//    public User findUserByEmail(String email) {
//        try {
//            String jpql = "SELECT u FROM User u WHERE  u.email = :email";
//            Query query = getEntityManager().createQuery(jpql, User.class);
//            query.setParameter("email", email);
//            User myUser=(User) query.getSingleResult();
//            return myUser;
//        } catch (Exception e) {
//            return null;
//        }
//    }
    
    
}
