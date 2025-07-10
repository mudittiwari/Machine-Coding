package controllers.task;

import comment.Comment;
import task.NullTaskImpl;
import task.Task;
import user.User;

import java.util.List;

public interface TaskController {
    public boolean addTask(Task task, User user);

    public boolean removeTask(String taskId, User user);

    public Task getTask(String taskId, User user);

    public List<Task> getTasks(User user);

    public boolean updateTask(Task task ,String fieldName, Object newValue, User modifiedBy);

    public boolean addComment(Task task, User user, String message);

    public boolean addReply(Task task, User user, String commentId, String message);

    public List<Comment> getComments(Task task, User user);

}
