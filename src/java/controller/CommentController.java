package controller;

import ejb.PostFacade;
import ejb.UserFacade;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import model.Comment;
import model.Post;
import model.User;

/**
 *
 * @author xtrememe
 */
@ManagedBean
@RequestScoped
public class CommentController extends BaseController {

    private Comment selected;
    @Inject
    private PostFacade postFacade;
    private UserFacade userFacade;

    public Comment getSelected() {
        return selected;
    }

    public void setSelected(Comment selected) {
        this.selected = selected;
    }

    public String saveComment() {
        String postid = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("postid");
        Long pid = Long.valueOf(postid);
        
        String email = getEc().getRemoteUser();
        User user = userFacade.findByEmail(email);
        getSelected().setUserid(user);
        
        Post selectedPost = postFacade.find(pid);
        selectedPost.getComments().add(getSelected());
        postFacade.edit(selectedPost);
        return "roomDetails.xhtml?postId=" + pid + "&faces-redirect=true";
    }

    @PostConstruct
    public void init() {
        selected = new Comment();
    }

}
