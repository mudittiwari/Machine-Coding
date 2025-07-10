package controllers.task;

import comment.Comment;
import comment.CommentNodeImpl;
import task.NullTaskImpl;
import task.Task;
import user.User;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TaskControllerImpl implements TaskController{
    private List<Task> tasks;
    public TaskControllerImpl(){
        this.tasks = new ArrayList<>();
    }

    @Override
    public boolean addTask(Task task, User user){
        return this.tasks.add(task);
    }

    @Override
    public boolean removeTask(String taskId, User user){
        for(int i = 0;i<tasks.size();i++){
            if(taskId.equals(tasks.get(i).getId())){
                tasks.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public Task getTask(String taskId, User user){
        for(int i = 0;i<tasks.size();i++){
            if(tasks.get(i).getId().equals(taskId))
                return tasks.get(i);
        }
        return new NullTaskImpl();
    }

    @Override
    public List<Task> getTasks(User user) {
        return tasks;
    }

    @Override
    public boolean updateTask(Task task ,String fieldName, Object newValue, User modifiedBy) {
        return task.updateField(fieldName, newValue, modifiedBy);
    }

    @Override
    public boolean addComment(Task task, User user, String message){
        return task.addComment(user, message);
    }

    @Override
    public boolean addReply(Task task, User user, String commentId, String message){
        return task.addReply(user, commentId, message);
    }

    @Override
    public List<Comment> getComments(Task task, User user) {
        return task.getComments();
    }
}
