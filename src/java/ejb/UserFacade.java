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
    
    public User findByUsernamePassword(String email, String password) {
        System.out.println("email: " + email + " password: " + password);
        try {
            String jpql = "SELECT u FROM User_Info u WHERE  u.email = :email AND u.password = :password";
            Query query = getEntityManager().createQuery(jpql, User.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
            System.out.println("inside if");
            return (User) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("inside catch");
            return null;
        }
    }
    
}
