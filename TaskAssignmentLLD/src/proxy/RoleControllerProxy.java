package proxy;

import controllers.role.RoleController;
import controllers.role.RoleControllerImpl;
import enums.PermissionType;
import role.NullRoleImpl;
import role.Role;
import user.User;
import java.util.ArrayList;
import java.util.List;

public class RoleControllerProxy implements RoleController {

    private RoleController roleController;
    public RoleControllerProxy(){
        this.roleController = new RoleControllerImpl();
    }

    @Override
    public boolean addRole(Role role, User user) {
        if(user.getRole().getPermissions().contains(PermissionType.ADMIN)) {
            System.out.println(user.getName() + " is adding a new role to the system");
            return roleController.addRole(role, user);
        }
        else
            System.out.println(user.getName() + " does not have required permissions");
        return false;
    }

    @Override
    public boolean removeRole(String roleId, User user) {
        if(user.getRole().getPermissions().contains(PermissionType.ADMIN)) {
            System.out.println(user.getName() + " is removing a role from the system");
            return roleController.removeRole(roleId, user);
        }
        else
            System.out.println(user.getName() + " does not have required permissions");
        return false;
    }

    @Override
    public Role getRole(String roleId, User user) {
        if(user.getRole().getPermissions().contains(PermissionType.ADMIN)) {
            System.out.println(user.getName() + " is fetching a role from the system");
            return roleController.getRole(roleId, user);
        }
        else
            System.out.println(user.getName() + " does not have required permissions");
        return new NullRoleImpl();
    }

    @Override
    public List<Role> getRoles(User user) {
        if(user.getRole().getPermissions().contains(PermissionType.ADMIN)) {
            System.out.println("\u001B[31m" + user.getName() + " is fetching all the roles from the system");
            return roleController.getRoles(user);
        }
        else
            System.out.println(user.getName() + " does not have required permissions");
        return new ArrayList<>();
    }
}
