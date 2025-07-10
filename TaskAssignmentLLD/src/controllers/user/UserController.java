package controllers.user;

import user.NullUserImpl;
import user.User;

import java.util.List;

public interface UserController {
    public boolean addUser(User user, User doneBy);

    public boolean removeUser(String userId, User doneBy);

    public User getUser(String userId, User doneBy);

    public List<User> getUsers(User doneBy);
}
