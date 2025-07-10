package controllers.user;

import user.NullUserImpl;
import user.User;

import java.util.ArrayList;
import java.util.List;

public class UserControllerImpl implements UserController{
    private List<User> users;
    public UserControllerImpl(){
        this.users = new ArrayList<>();
    }

    @Override
    public boolean addUser(User user,User doneBy){
        return this.users.add(user);
    }

    @Override
    public boolean removeUser(String userId, User doneBy){
        for(int i = 0;i<users.size();i++){
            if(userId.equals(users.get(i).getId())){
                users.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public User getUser(String userId, User doneBy){
        for(int i = 0;i<users.size();i++){
            if(users.get(i).getId().equals(userId))
                return users.get(i);
        }
        return new NullUserImpl();
    }

    @Override
    public List<User> getUsers(User doneBy) {
        return users;
    }
}
