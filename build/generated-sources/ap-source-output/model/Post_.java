package model;

import java.util.List;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.User;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-02-12T22:08:25")
@StaticMetamodel(Post.class)
public class Post_ { 

    public static volatile SingularAttribute<Post, Integer> currentHolders;
    public static volatile SingularAttribute<Post, List> images;
    public static volatile SingularAttribute<Post, Double> pricePerMonth;
    public static volatile SingularAttribute<Post, String> addressState;
    public static volatile SingularAttribute<Post, Integer> maximumAge;
    public static volatile SingularAttribute<Post, String> requiredGender;
    public static volatile SingularAttribute<Post, String> rommieQualities;
    public static volatile SingularAttribute<Post, Integer> totalRooms;
    public static volatile SingularAttribute<Post, User> postedBy;
    public static volatile SingularAttribute<Post, String> roomDescription;
    public static volatile SingularAttribute<Post, String> requiredCountry;
    public static volatile SingularAttribute<Post, String> addressStreet;
    public static volatile SingularAttribute<Post, Integer> expectedRoomieNumber;
    public static volatile SingularAttribute<Post, Integer> minimumAge;
    public static volatile SingularAttribute<Post, Long> id;
    public static volatile SingularAttribute<Post, String> addressCity;

}