package controllers.role;
import role.NullRoleImpl;
import role.Role;
import user.User;
import java.util.ArrayList;
import java.util.List;

public class RoleControllerImpl implements RoleController{
    private List<Role> roles;

    public RoleControllerImpl(){
        this.roles = new ArrayList<>();
    }

    @Override
    public boolean addRole(Role role, User user){
        return this.roles.add(role);
    }

    @Override
    public boolean removeRole(String roleId, User user){
        for(int i = 0;i<roles.size();i++){
            if(roleId.equals(roles.get(i).getId())){
                roles.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public Role getRole(String roleId, User user){
        for(int i = 0;i<roles.size();i++){
            if(roles.get(i).getId().equals(roleId))
                return roles.get(i);
        }
        return new NullRoleImpl();
    }

    @Override
    public List<Role> getRoles(User user) {
        return roles;
    }
}
