package ejb.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author xtrememe
 */
public class LoginFilter implements Filter{
//    @EJB(mappedName = "user")
//    private UserFacade user;
    
    @Override
    public void destroy(){}
    
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, 
                         FilterChain filterChain) throws IOException, ServletException {
        
//        System.out.println("inside the filter method, printing the user name: " );
//        while(servletRequest.getAttributeNames().hasMoreElements()){
//            System.out.println("next element: " + servletRequest.getAttributeNames().nextElement());
//        }
        // find out the firstname of the user and save that into the session object
        
                
        filterChain.doFilter(servletRequest, servletResponse);
    }
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

}
