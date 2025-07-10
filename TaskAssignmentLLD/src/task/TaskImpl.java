package task;

import comment.Comment;
import comment.CommentNodeImpl;
import task.history.TaskHistory;
import task.history.TaskHistoryImpl;
import user.Observer;
import user.User;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskImpl implements Task, Comparable<Task>, Observable{
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

    public TaskImpl(String id, String title, String desc, User createdBy, User assignedTo, User reviewedBy, boolean finalReviewed, Date deadlineDate, Date creationDate, Date modificationDate){
        this.title = title;
        this.desc = desc;
        this.createdBy = createdBy;
        this.assignedTo = assignedTo;
        this.reviewedBy = reviewedBy;
        this.finalReviewed = finalReviewed;
        this.deadlineDate = deadlineDate;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.id = id;
        this.comments = new ArrayList<>();
        this.taskHistoryList = new ArrayList<>();
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
        return id;
    }

    @Override
    public int compareTo(Task task) {
        if(this.id.equals(task.getId()))
            return 1;
        return 0;
    }

    @Override
    public boolean addComment(User user, String message){
        Comment comment = new CommentNodeImpl(user, message);
        return this.comments.add(comment);
    }

    @Override
    public boolean addReply(User user, String commentId, String message) {
        for (Comment comment : this.comments) {
            if (addReplyRecursive(comment, commentId, user, message)) {
                return true;
            }
        }
        return false;
    }

    private boolean addReplyRecursive(Comment comment, String commentId, User user, String message) {
        if (comment.getId().equals(commentId)) {
            return comment.addReply(user, message);
        }

        for (Comment reply : comment.getReplies()) {
            if (addReplyRecursive(reply, commentId, user, message)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void notifyObservers(Task task) {
        ((Observer)reviewedBy).getNotification((Observable) task);
        ((Observer)createdBy).getNotification((Observable) task);
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
        try {
            if(fieldName.equals("finalReviewed")){
                if(!modifiedBy.equals(this.reviewedBy))
                    return false;
            }
            Field field = this.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            Object oldValue = field.get(this);
            if ((oldValue == null && newValue != null) || (oldValue != null && !oldValue.equals(newValue))) {
                taskHistoryList.add(new TaskHistoryImpl(
                        this.id,
                        fieldName,
                        oldValue != null ? oldValue.toString() : "null",
                        newValue != null ? newValue.toString() : "null",
                        modifiedBy.getName()
                ));
                field.set(this, newValue);
                this.modificationDate = new Date();
                return true;
            }

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }
}
