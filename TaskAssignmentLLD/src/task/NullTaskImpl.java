package task;

import comment.Comment;
import task.history.TaskHistory;
import task.history.TaskHistoryImpl;
import user.User;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

public class NullTaskImpl implements Task{
    private String id;
    private String title;
    private String desc;
    private User createdBy;
    private User assignedTo;
    private User reviewedBy;
    private boolean finalReviewed;
    private Date deadlineDate;
    private Date creationDate;
    private Date modificationDate;
    private List<Comment> comments;
    private List<TaskHistory> taskHistoryList;

    public NullTaskImpl(){
        this.title = "";
        this.desc = "";
        this.createdBy = null;
        this.assignedTo = null;
        this.reviewedBy = null;
        this.finalReviewed = false;
        this.deadlineDate = null;
        this.creationDate = null;
        this.modificationDate = null;
        this.id = null;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    @Override
    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    @Override
    public User getReviewedBy() {
        return reviewedBy;
    }

    @Override
    public void setReviewedBy(User reviewedBy) {
        this.reviewedBy = reviewedBy;
    }

    @Override
    public boolean isFinalReviewed() {
        return finalReviewed;
    }

    @Override
    public void setFinalReviewed(boolean finalReviewed) {
        this.finalReviewed = finalReviewed;
    }

    @Override
    public Date getModificationDate() {
        return modificationDate;
    }

    @Override
    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    @Override
    public User getCreatedBy() {
        return createdBy;
    }

    @Override
    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public Date getDeadlineDate() {
        return deadlineDate;
    }

    @Override
    public String getId() {
        return "";
    }

    @Override
    public boolean addComment(User user, String message) {
        System.out.println("this is a null task");
        return false;
    }

    @Override
    public boolean addReply(User user, String commentId, String message) {
        System.out.println("this is a null task");
        return false;
    }

    @Override
    public List<TaskHistory> getTaskHistoryList() {
        return taskHistoryList;
    }

    @Override
    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public boolean updateField(String fieldName, Object newValue, User modifiedBy) {
        System.out.println("this is a null task");
        return false;
    }
}
