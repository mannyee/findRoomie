package ejb;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Post;
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

    public User findByEmail(String email) {
        try {
            if (email == null) {
                email = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
            }

            String jpql = "SELECT u from User u where u.email = :email";
            Query query = getEntityManager().createQuery(jpql, User.class);
            query.setParameter("email", email);
            return (User) query.getSingleResult();

        } catch (Exception e) {

            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    public int updatePassword(String password, String email) {
        String jpql = "UPDATE User SET password = :pwd WHERE email = :email";
        Query query = em.createQuery(jpql, User.class);
        query.setParameter("pwd", password);
        query.setParameter("email", email);
        return query.executeUpdate();
    }

    public int updateProfile(Long user_id, String email, String firstName, String lastName,
            String password, String phoneNumber, String addressLine1, String addressLine2,
            String gender, String age, String profilePic) {

        System.out.println(user_id + "=" + email + "=" + firstName + "=" + lastName + "=" + password);

        String jpql = "UPDATE User SET firstName= :firstName, "
                + "lastName= :lastName , email= :email"
                + " ,password = :pwd, "
                + "phoneNumber=:phoneNumber, addressLine1=:addressLine1, "
                + "addressLine2=:addressLine2, gender=:gender, age=:age,"
                + "profilePic=:profilePic WHERE id = :user_id";

        Query query = em.createQuery(jpql, User.class);

        query.setParameter("pwd", password);
        query.setParameter("email", email);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        query.setParameter("user_id", user_id);
        query.setParameter("phoneNumber", phoneNumber);
        query.setParameter("addressLine1", addressLine1);
        query.setParameter("addressLine2", addressLine2);
        query.setParameter("gender", gender);
        query.setParameter("age", age);
        query.setParameter("profilePic", profilePic);

        return query.executeUpdate();
    }
    
    
    
     //update post
    public int updatePost(Long post_id, String title, int totalRooms, int currentHolders,
            String addressStreet, String addressCity, String addressState,
            String roomDescription,
            int expectedRoomieNumber, double pricePerMonth,
            String requiredGender, String requiredCountry, int minimumAge,
            int maximumAge, String rommieQualities, List<String> images) {

//        String imagesPath = "";
//        for (String img : images) {
//            imagesPath += img + ";";
//        }

        String jpql = "UPDATE Post SET title= :title, "
                + "totalRooms= :totalRooms , currentHolders= :currentHolders"
                + " ,addressStreet = :addressStreet, "
                + "addressCity=:addressCity, addressState=:addressState, "
                + "roomDescription=:roomDescription, expectedRoomieNumber=:expectedRoomieNumber,"
                + " pricePerMonth=:pricePerMonth,"
                + "requiredGender=:requiredGender,"
                + "requiredCountry=:requiredCountry,"
                + "minimumAge=:minimumAge,"
                + "maximumAge=:maximumAge,"
                + "images=:images,"
                + "rommieQualities=:rommieQualities"
                + " WHERE id = :post_id";

        Query query = em.createQuery(jpql, Post.class);

        query.setParameter("post_id", post_id);
        query.setParameter("title", title);
        query.setParameter("totalRooms", totalRooms);
        query.setParameter("currentHolders", currentHolders);
        query.setParameter("addressStreet", addressStreet);
        query.setParameter("addressCity", addressCity);
        query.setParameter("addressState", addressState);
        query.setParameter("roomDescription", roomDescription);
        query.setParameter("expectedRoomieNumber", expectedRoomieNumber);
        query.setParameter("pricePerMonth", pricePerMonth);
        query.setParameter("requiredGender", requiredGender);
        query.setParameter("requiredCountry", requiredCountry);
        query.setParameter("minimumAge", minimumAge);
        query.setParameter("maximumAge", maximumAge);
        query.setParameter("rommieQualities", rommieQualities);
        query.setParameter("images", images);

        return query.executeUpdate();
    }

}
