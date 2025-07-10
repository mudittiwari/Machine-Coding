package task;
import comment.Comment;
import comment.CommentNodeImpl;
import task.history.TaskHistory;
import task.history.TaskHistoryImpl;
import user.User;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

public interface Task {
    public String getTitle();

    public void setTitle(String title);

    public String getDesc();

    public void setDesc(String desc);

    public User getAssignedTo();

    public void setAssignedTo(User assignedTo);

    public User getReviewedBy();

    public void setReviewedBy(User reviewedBy);

    public boolean isFinalReviewed();

    public void setFinalReviewed(boolean finalReviewed);

    public Date getModificationDate();

    public void setModificationDate(Date modificationDate);

    public User getCreatedBy();

    public Date getCreationDate();

    public Date getDeadlineDate();

    public String getId();

    public boolean addComment(User user, String message);

    public boolean addReply(User user, String commentId, String message);

    public List<TaskHistory> getTaskHistoryList() ;

    public List<Comment> getComments() ;

    public boolean updateField(String fieldName, Object newValue, User modifiedBy) ;
}
