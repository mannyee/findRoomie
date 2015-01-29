package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-01-28T22:18:58")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> firstName;
    public static volatile SingularAttribute<User, String> lastName;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> gender;
    public static volatile SingularAttribute<User, Date> registeredDate;
    public static volatile SingularAttribute<User, String> profilePic;
    public static volatile SingularAttribute<User, Long> id;
    public static volatile SingularAttribute<User, String> userName;
    public static volatile SingularAttribute<User, String> email;

}