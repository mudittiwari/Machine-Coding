package task.builder;
import enums.TaskSeverity;
import task.Task;
import task.TaskImpl;
import user.User;
import java.util.Date;
import java.util.UUID;

public class TaskBuilderAbstract {
    private String title;
    private String desc;
    private User createdBy;
    private User assignedTo;
    private User reviewedBy;
    private boolean finalReviewed;
    private Date deadlineDate;
    private Date creationDate;
    private Date modificationDate;
    private TaskSeverity taskSeverity;

    public TaskBuilderAbstract(){
        this.finalReviewed = false;
        this.title = "";
        this.desc = "";
        this.taskSeverity = TaskSeverity.NORMAL;
        this.creationDate = new Date();
    }

    public TaskBuilderAbstract setTitle(String title){
        this.title = title;
        return this;
    }

    public TaskBuilderAbstract setDesc(String desc){
        this.desc = desc;
        return this;
    }

    public TaskBuilderAbstract setCreatedBy(User user){
        this.createdBy = user;
        return this;
    }

    public TaskBuilderAbstract setAssignedTo(User user){
        this.assignedTo = user;
        return this;
    }

    public TaskBuilderAbstract setReviewedBy(User user){
        this.reviewedBy = user;
        return this;
    }

    public TaskBuilderAbstract setDeadlineDate(Date date){
        this.deadlineDate = date;
        return this;
    }

    public TaskBuilderAbstract setModificationDate(Date date){
        this.modificationDate = date;
        return this;
    }

    public TaskBuilderAbstract setSeverity(TaskSeverity severity){
        this.taskSeverity = severity;
        return this;
    }

    public Task buildTask(){
        String taskId = UUID.randomUUID().toString();
        return new TaskImpl(taskId, this.title, this.desc, this.createdBy, this.assignedTo, this.reviewedBy, this.finalReviewed, deadlineDate, creationDate, modificationDate);
    }


}
