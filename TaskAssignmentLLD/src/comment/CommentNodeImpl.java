package comment;

import user.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CommentNodeImpl implements Comment{
    private String id;
    private List<Comment> replies;
    private User user;
    private String message;
    private Date date;

    public CommentNodeImpl(User user, String message){
        this.replies = new ArrayList<>();
        this.user = user;
        this.message = message;
        this.date = new Date();
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public boolean addReply(User user, String message){
        Comment comment = new CommentNodeImpl(user, message);
        return replies.add(comment);
    }

    @Override
    public void printComments() {
        System.out.println("comment done by: "+this.user.getName()+"comment message is: " + this.message);
        for (int i = 0;i<replies.size();i++){
            replies.get(i).printComments();
        }
    }

    @Override
    public List<Comment> getReplies() {
        return replies;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "CommentNodeImpl{" +
                "id='" + id + '\'' +
                ", replies=" + replies +
                ", user=" + user +
                ", message='" + message + '\'' +
                ", date=" + date +
                '}';
    }
}
