package user;

import task.Observable;

public interface Observer {
    public void getNotification(Observable task);
    public void getChanges(Observable task);
}
