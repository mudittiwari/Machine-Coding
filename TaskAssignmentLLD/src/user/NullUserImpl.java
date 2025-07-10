package user;

import role.Role;
import task.Observable;

import java.util.UUID;

public class NullUserImpl implements User, Observer{
    private String name;
    private String email;
    private Role role;
    private String id;

    public NullUserImpl(){
        this.name = "";
        this.email = "";
        this.role = null;
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Role getRole() {
        return role;
    }

    @Override
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void getNotification(Observable task) {
//        System.out.println("received notification for :" + task);
        this.getChanges(task);
    }

    @Override
    public void getChanges(Observable task) {
        System.out.println("this is a null task");
    }
}
