package proxy;

import controllers.user.UserController;
import controllers.user.UserControllerImpl;
import enums.PermissionType;
import user.NullUserImpl;
import user.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserControllerProxy implements UserController{
    UserController userController;

    public UserControllerProxy(){
        this.userController = new UserControllerImpl();
    }
    @Override
    public boolean addUser(User user, User doneBy) {
        if(doneBy.getRole().getPermissions().contains(PermissionType.ADMIN)){
            System.out.println(doneBy.getName() + " is adding a new user to the system");
            return userController.addUser(user, doneBy);
        }
        System.out.println(doneBy.getName() + " does not have the required permissions");
        return false;
    }

    @Override
    public boolean removeUser(String userId, User doneBy) {
        if(doneBy.getRole().getPermissions().contains(PermissionType.ADMIN)){
            System.out.println(doneBy.getName() + " is removing a user from the system");
            return userController.removeUser(userId, doneBy);
        }
        System.out.println(doneBy.getName() + " does not have the required permissions");
        return false;
    }

    @Override
    public User getUser(String userId, User doneBy) {
        if(doneBy.getRole().getPermissions().contains(PermissionType.ADMIN)){
            System.out.println(doneBy.getName() + " is fetching a user from the system");
            return userController.getUser(userId, doneBy);
        }
        System.out.println(doneBy.getName() + " does not have the required permissions");
        return new NullUserImpl();
    }

    @Override
    public List<User> getUsers(User doneBy) {
        if(doneBy.getRole().getPermissions().contains(PermissionType.ADMIN)){
            System.out.println(doneBy.getName() + " is fetching all users from the system");
            return userController.getUsers(doneBy);
        }
        System.out.println(doneBy.getName() + " does not have the required permissions");
        return new ArrayList<>();
    }
}
