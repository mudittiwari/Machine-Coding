package comment;

import user.User;

import java.util.Date;
import java.util.List;

public interface Comment {
    public void printComments();
    public List<Comment> getReplies();
    public User getUser();
    public Date getDate();
    public String getMessage();
    public boolean addReply(User user, String message);
    public String getId();
    public String toString();
}
