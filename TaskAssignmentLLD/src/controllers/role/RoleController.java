package controllers.role;
import role.Role;
import user.User;
import java.util.List;

public interface RoleController {
    public boolean addRole(Role role, User user);

    public boolean removeRole(String roleId, User user);

    public Role getRole(String roleId, User user);

    public List<Role> getRoles(User user);
}
