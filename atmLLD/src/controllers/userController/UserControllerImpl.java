package controllers.userController;

import user.User;

import java.util.ArrayList;
import java.util.List;

public class UserControllerImpl implements UserController {
    List<User> users;

    public UserControllerImpl(){
        this.users = new ArrayList<>();
    }


    @Override
    public void addUser(User user) {
        this.users.add(user);
    }

    @Override
    public void deleteUser(User user) {
        for(int i = 0;i<users.size();i++){
            if(users.get(i).equals(user)){
                users.remove(i);
                break;
            }
        }
    }
}
