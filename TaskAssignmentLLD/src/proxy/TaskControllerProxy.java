package proxy;

import comment.Comment;
import controllers.task.TaskController;
import controllers.task.TaskControllerImpl;
import enums.PermissionType;
import task.NullTaskImpl;
import task.Observable;
import task.Task;
import user.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskControllerProxy implements TaskController {

    private TaskController taskController;
    public TaskControllerProxy(){
        this.taskController = new TaskControllerImpl();
    }
    @Override
    public boolean addTask(Task task, User user) {
        if(user.getRole().getPermissions().contains(PermissionType.CREATE)){
            System.out.println(user.getName() + " is adding a new task to the system");
            boolean taskCreated = this.taskController.addTask(task, user);
            if(taskCreated){
                ((Observable) task).notifyObservers(task);
            }
            return taskCreated;
        }
        System.out.println(user.getName() + " does not have the required permissions.");
        return false;
    }

    @Override
    public boolean removeTask(String taskId, User user) {
        if(user.getRole().getPermissions().contains(PermissionType.DELETE)){
            System.out.println(user.getName() + " is removing a task from the system");
            Task task = this.taskController.getTask(taskId, user);
            boolean taskRemoved = this.taskController.removeTask(taskId, user);
            if(taskRemoved){
                ((Observable) task).notifyObservers(task);
            }
            return taskRemoved;
        }
        System.out.println(user.getName() + " does not have the required permissions.");
        return false;
    }

    @Override
    public Task getTask(String taskId, User user) {
        if(user.getRole().getPermissions().contains(PermissionType.READ)){
            System.out.println(user.getName() + " is fetching a task from the system");
            return this.taskController.getTask(taskId, user);
        }
        System.out.println(user.getName() + " does not have the required permissions.");
        return new NullTaskImpl();
    }

    @Override
    public List<Task> getTasks(User user) {
        if(user.getRole().getPermissions().contains(PermissionType.READ)){
            System.out.println(user.getName() + " is fetching all the tasks from the system");
            return this.taskController.getTasks(user);
        }
        System.out.println(user.getName() + " does not have the required permissions.");
        return new ArrayList<>();
    }

    @Override
    public boolean updateTask(Task task, String fieldName, Object newValue, User modifiedBy) {
        if(modifiedBy.getRole().getPermissions().contains(PermissionType.UPDATE)){
            if(taskController.updateTask(task, fieldName, newValue, modifiedBy)) {
                ((Observable) task).notifyObservers(task);
                return true;
            }
        }
        System.out.println(modifiedBy.getName() + " does not have the required permissions.");
        return false;
    }

    @Override
    public boolean addComment(Task task, User user, String message){
        if(user.getRole().getPermissions().contains(PermissionType.UPDATE)){
            if(taskController.addComment(task, user, message)) {
                ((Observable) task).notifyObservers(task);
                return true;
            }
        }
        System.out.println(user.getName() + " does not have the required permissions.");
        return false;
    }

    @Override
    public boolean addReply(Task task, User user, String commentId, String message){
        if(user.getRole().getPermissions().contains(PermissionType.UPDATE)){
            if(taskController.addReply(task, user, commentId, message)) {
                ((Observable) task).notifyObservers(task);
                return true;
            }
        }
        System.out.println(user.getName() + " does not have the required permissions.");
        return false;
    }

    @Override
    public List<Comment> getComments(Task task, User user){
        if(user.getRole().getPermissions().contains(PermissionType.READ)){
            List<Comment> comments = task.getComments();
            this.printComments(comments);
            return comments;
        }
        System.out.println(user.getName() + " does not have the required permissions.");
        return new ArrayList<>();
    }

    public void printComments(List<Comment> comments){
        comments.forEach(Comment::printComments);
    }
}
